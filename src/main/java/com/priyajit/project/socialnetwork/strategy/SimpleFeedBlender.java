package com.priyajit.project.socialnetwork.strategy;

import com.priyajit.project.socialnetwork.model.*;
import com.priyajit.project.socialnetwork.repository.FollowRepository;
import com.priyajit.project.socialnetwork.repository.PostRepository;
import com.priyajit.project.socialnetwork.repository.ReplyRepository;
import com.priyajit.project.socialnetwork.repository.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class SimpleFeedBlender implements FeedBlender {

    private FollowRepository followRepository;
    private PostRepository postRepository;
    private VoteRepository voteRepository;
    private ReplyRepository replyRepository;

    @Autowired
    public SimpleFeedBlender(FollowRepository followRepository, PostRepository postRepository, VoteRepository voteRepository, ReplyRepository replyRepository) {
        this.followRepository = followRepository;
        this.postRepository = postRepository;
        this.voteRepository = voteRepository;
        this.replyRepository = replyRepository;
    }

    @Override
    public List<Post> getFeedPosts(User user) {
        // get followees : list of users this user follows
        List<Follow> followList = followRepository.findAllByFollower(user);
        Set<Long> followeeIds = followList.stream().map(Follow::getFollowee).map(Model::getId).collect(Collectors.toSet());

        List<Post> allPosts = postRepository.findAll();

        // fetch voteScoreCount for all posts
        Map<Long, Long> voteScoreMap = buildVoteScoreMap(allPosts);

        // fetch replyCount for all posts
        Map<Long, Long> replyCountMap = buildReplyCountMap(allPosts);


        allPosts.sort((post1, post2) -> {
            // priority 1: post of followee
            if (followeeIds.contains(post1.getCreator().getId()) && !followeeIds.contains(post2.getCreator().getId())) {
                return -1;
            }
            if (!followeeIds.contains(post1.getCreator().getId()) && followeeIds.contains(post2.getCreator().getId())) {
                return 1;
            }
            // priority 2: voteScoreCount
            if (!Objects.equals(voteScoreMap.get(post1.getId()), voteScoreMap.get(post2.getId()))) {
                return voteScoreMap.get(post1.getId()) > voteScoreMap.get(post2.getId()) ? -1 : 1;
            }
            // priority 3: replyCount
            if (!Objects.equals(replyCountMap.get(post1.getId()), replyCountMap.get(post2.getId()))) {
                return replyCountMap.get(post1.getId()) > replyCountMap.get(post2.getId()) ? -1 : 1;
            }
            return post2.getCreationDate().compareTo(post1.getCreationDate());
        });
        return allPosts;
    }

    private Map<Long, Long> buildVoteScoreMap(List<Post> allPosts){

        // fetch voteScoreCount for all posts
        // voteScoreCount : (upVoteCount - downVoteCount)
        Map<Long, Long> voteScoreMap = new HashMap<>();
        allPosts.forEach(post -> {
            List<Vote> votes = voteRepository.findAllByPost(post);
            Long upvoteCount = votes.stream().filter(vote -> vote.getVoteType() == VoteType.UP).count();
            Long downvoteCount = votes.stream().filter(vote -> vote.getVoteType() == VoteType.DOWN).count();
            voteScoreMap.put(post.getId(), upvoteCount - downvoteCount);
        });
        return voteScoreMap;
    }

    private Map<Long, Long> buildReplyCountMap(List<Post> allPosts){

        // fetch replyCount for all posts
        Map<Long, Long> replyCountMap = new HashMap<>();
        allPosts.forEach(post -> {
            replyCountMap.put(post.getId(), replyRepository.countByPost(post));
        });
        return replyCountMap;
    }
}
