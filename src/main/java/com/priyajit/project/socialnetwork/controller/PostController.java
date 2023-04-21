package com.priyajit.project.socialnetwork.controller;

import com.priyajit.project.socialnetwork.dto.requestDTO.*;
import com.priyajit.project.socialnetwork.dto.responseDTO.PostResponseDTO;
import com.priyajit.project.socialnetwork.dto.responseDTO.ReplyResponseDTO;
import com.priyajit.project.socialnetwork.dto.responseDTO.VoteResponseDTO;
import com.priyajit.project.socialnetwork.dto.responseDTO.factory.PostResponseDTOFactory;
import com.priyajit.project.socialnetwork.dto.responseDTO.factory.ReplyResponseDTOFactory;
import com.priyajit.project.socialnetwork.dto.responseDTO.factory.VoteResponseDTOFactory;
import com.priyajit.project.socialnetwork.model.Post;
import com.priyajit.project.socialnetwork.model.Reply;
import com.priyajit.project.socialnetwork.model.User;
import com.priyajit.project.socialnetwork.model.Vote;
import com.priyajit.project.socialnetwork.model.factory.PostFactory;
import com.priyajit.project.socialnetwork.model.factory.ReplyFactory;
import com.priyajit.project.socialnetwork.model.factory.VoteFactory;
import com.priyajit.project.socialnetwork.service.PostService;
import com.priyajit.project.socialnetwork.service.ReplyService;
import com.priyajit.project.socialnetwork.service.UserService;
import com.priyajit.project.socialnetwork.service.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PostController {

    private final PostFactory postFactory;
    private final PostService postService;
    private final PostResponseDTOFactory postResponseDTOFactory;
    private final ReplyFactory replyFactory;
    private final ReplyService replyService;
    private final ReplyResponseDTOFactory replyResponseDTOFactory;
    private final VoteFactory voteFactory;
    private final VoteResponseDTOFactory voteResponseDTOFactory;
    private final VoteService voteService;
    private final UserService userService;

    private final Authenticator authenticator;

    @Autowired
    public PostController(
            PostFactory postFactory,
            PostService postService,
            PostResponseDTOFactory postResponseDTOFactory,
            ReplyFactory replyFactory,
            ReplyService replyService,
            ReplyResponseDTOFactory replyResponseDTOFactory,
            VoteFactory voteFactory,
            VoteResponseDTOFactory voteResponseDTOFactory,
            VoteService voteService,
            UserService userService,
            Authenticator authenticator) {

        this.postFactory = postFactory;
        this.postService = postService;
        this.postResponseDTOFactory = postResponseDTOFactory;
        this.replyFactory = replyFactory;
        this.replyService = replyService;
        this.replyResponseDTOFactory = replyResponseDTOFactory;
        this.voteFactory = voteFactory;
        this.voteResponseDTOFactory = voteResponseDTOFactory;
        this.voteService = voteService;
        this.userService = userService;
        this.authenticator = authenticator;
    }

    public PostResponseDTO createPost(CreatePostRequestDTO dto) {

        if (!authenticator.authenticate(dto.getAuthTokenResponseDTO())) return null;

        // create model obj from request dto and persist
        Post post = postFactory.create(dto);
        Post savedPost = postService.create(post);

        // map to response dto and return
        return postResponseDTOFactory.create(savedPost);
    }

    public PostResponseDTO getPost(GetPostRequestDTO dto) {

        if (!authenticator.authenticate(dto.getAuthTokenResponseDTO())) return null;

        Post post = postService.getById(dto.getPostId());

        // list of votes made to this post
        List<Vote> voteList = voteService.getAllByPost(post);

        // list of replies made to this post
        List<Reply> replyList = replyService.getAllByPost(post);

        // map to response dto and return
        return postResponseDTOFactory.create(post, voteList, replyList);
    }

    public ReplyResponseDTO createReplyToPost(CreateReplyRequestDTO dto) {

        if (!authenticator.authenticate(dto.getAuthTokenResponseDTO())) return null;

        User replier = userService.getById(dto.getReplierId());

        // post to which this is reply is made
        Post post = postService.getById(dto.getPostId());

        // create model obj from request dto and persist
        Reply reply = replyFactory.create(dto, replier, post);
        Reply savedReply = replyService.create(reply);

        // map to response dto and return
        return replyResponseDTOFactory.create(savedReply);
    }

    public ReplyResponseDTO createReplyToReply(CreateReplyRequestDTO dto) {

        if (!authenticator.authenticate(dto.getAuthTokenResponseDTO())) return null;

        User replier = userService.getById(dto.getReplierId());

        // reply to which this is reply is made
        Reply parentReply = replyService.getById(dto.getReplyId());

        // create model obj from request dto and persist
        Reply reply = replyFactory.create(dto, replier, parentReply);
        Reply savedReply = replyService.create(reply);

        // map to response dto and return
        return replyResponseDTOFactory.create(savedReply);
    }

    public ReplyResponseDTO getReply(GetReplyRequestDTO dto) {

        if (!authenticator.authenticate(dto.getAuthTokenResponseDTO())) return null;

        Reply reply = replyService.getById(dto.getReplyId());

        // list of votes made to this reply
        List<Vote> voteList = voteService.getAllByReply(reply);

        // list of replies made to this reply
        List<Reply> replyList = replyService.getAllByReply(reply);

        // map to response dto and return
        return replyResponseDTOFactory.create(reply, voteList, replyList);
    }

    public VoteResponseDTO createVoteToPost(CreateVoteRequestDTO dto) {

        if (!authenticator.authenticate(dto.getAuthTokenResponseDTO())) return null;

        User voter = userService.getById(dto.getVoterId());
        Post post = postService.getById(dto.getPostId());

        // create model obj from request dto and persist
        Vote vote = voteFactory.create(dto, voter, post);
        Vote savedVote = voteService.create(vote);

        // map to response dto and return
        return voteResponseDTOFactory.create(savedVote);
    }

    public VoteResponseDTO createVoteToReply(CreateVoteRequestDTO dto) {

        if (!authenticator.authenticate(dto.getAuthTokenResponseDTO())) return null;

        User voter = userService.getById(dto.getVoterId());
        Reply reply = replyService.getById(dto.getReplyId());

        // create model obj from request dto and persist
        Vote vote = voteFactory.create(dto, voter, reply);
        Vote savedVote = voteService.create(vote);

        // map to response dto and return
        return voteResponseDTOFactory.create(savedVote);
    }

    public VoteResponseDTO getVote(GetVoteRequestDTO dto) {

        if (!authenticator.authenticate(dto.getAuthTokenResponseDTO())) return null;

        Vote vote = voteService.getById(dto.getVoteId());

        // map to response dto and return
        return voteResponseDTOFactory.create(vote);
    }

    public List<PostResponseDTO> getFeed(GetFeedRequestDTO dto) {

        if (!authenticator.authenticate(dto.getAuthTokenResponseDTO())) return null;

        List<Post> postList = postService.getFeedPosts(dto.getUserId());

        // map each post to its response dto
        // fetching voteCount and replyCount for each post
        return postList.stream().map(
                post -> postResponseDTOFactory.create(
                        post,
                        voteService.getCountByPost(post), // count of votes made to the post
                        replyService.getCountByPost(post)) // count of replies made to the post
        ).toList();
    }
}