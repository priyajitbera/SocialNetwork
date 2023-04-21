package com.priyajit.project.socialnetwork.repository;

import com.priyajit.project.socialnetwork.model.Post;
import com.priyajit.project.socialnetwork.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findByCreator(User creator);

}
