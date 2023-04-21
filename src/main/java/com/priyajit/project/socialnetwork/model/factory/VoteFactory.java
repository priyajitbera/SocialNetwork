package com.priyajit.project.socialnetwork.model.factory;

import com.priyajit.project.socialnetwork.dto.requestDTO.CreateVoteRequestDTO;
import com.priyajit.project.socialnetwork.model.Post;
import com.priyajit.project.socialnetwork.model.Reply;
import com.priyajit.project.socialnetwork.model.User;
import com.priyajit.project.socialnetwork.model.Vote;

public interface VoteFactory {

    Vote create(CreateVoteRequestDTO dto, User voter, Post post);

    Vote create(CreateVoteRequestDTO dto, User voter, Reply reply);
}
