package com.priyajit.project.socialnetwork.repository;

import com.priyajit.project.socialnetwork.model.Reply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReplyRepository extends JpaRepository<Reply, Long> {
}
