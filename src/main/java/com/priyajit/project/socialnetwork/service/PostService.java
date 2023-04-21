package com.priyajit.project.socialnetwork.service;

import com.priyajit.project.socialnetwork.model.Post;
import com.priyajit.project.socialnetwork.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostService extends CommonService<Post, Long> {

    List<Post> getFeedPosts(Long userId);
}
