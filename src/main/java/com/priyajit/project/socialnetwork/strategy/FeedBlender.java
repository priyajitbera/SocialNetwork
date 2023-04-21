package com.priyajit.project.socialnetwork.strategy;

import com.priyajit.project.socialnetwork.model.Post;
import com.priyajit.project.socialnetwork.model.User;

import java.util.List;

public interface FeedBlender {

    List<Post> getFeedPosts(User user);
}
