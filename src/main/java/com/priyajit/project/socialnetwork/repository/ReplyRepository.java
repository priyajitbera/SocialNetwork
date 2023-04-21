package com.priyajit.project.socialnetwork.repository;

import com.priyajit.project.socialnetwork.model.Post;
import com.priyajit.project.socialnetwork.model.Reply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReplyRepository extends JpaRepository<Reply, Long> {

    List<Reply> findAllByPost(Post post);

    List<Reply> findAllByReply(Reply reply);

    long countByPost(Post post);

    long countByReply(Reply reply);
}
