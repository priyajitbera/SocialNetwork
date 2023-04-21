package com.priyajit.project.socialnetwork.repository;

import com.priyajit.project.socialnetwork.model.Post;
import com.priyajit.project.socialnetwork.model.Reply;
import com.priyajit.project.socialnetwork.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Long> {

    List<Vote> findAllByPost(Post post);

    List<Vote> findAllByReply(Reply reply);

    long countByPost(Post post);

    long countByReply(Reply reply);

}
