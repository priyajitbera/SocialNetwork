package com.priyajit.project.socialnetwork.service;

import com.priyajit.project.socialnetwork.model.Post;
import com.priyajit.project.socialnetwork.model.User;
import com.priyajit.project.socialnetwork.repository.PostRepository;
import com.priyajit.project.socialnetwork.repository.UserRepository;
import com.priyajit.project.socialnetwork.strategy.FeedBlender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl implements PostService{

    private UserRepository userRepository;

    private PostRepository postRepository;

    private FeedBlender feedBlender;

    @Autowired
    public PostServiceImpl(UserRepository userRepository, PostRepository postRepository, FeedBlender feedBlender) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
        this.feedBlender = feedBlender;
    }

    @Override
    public Post getById(Long id) {
        return postRepository.findById(id).orElseThrow();
    }

    @Override
    public List<Post> getAllByIds(List<Long> ids) {
        return postRepository.findAllById(ids);
    }

    @Override
    public Post create(Post post) {
        return postRepository.save(post);
    }

    @Override
    public List<Post> getFeedPosts(Long userId) {

        User user = userRepository.findById(userId).orElseThrow();
        return feedBlender.getFeedPosts(user);
    }
}
