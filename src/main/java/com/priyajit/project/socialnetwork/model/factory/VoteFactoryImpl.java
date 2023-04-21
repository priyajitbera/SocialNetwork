package com.priyajit.project.socialnetwork.model.factory;

import com.priyajit.project.socialnetwork.dto.requestDTO.CreateVoteRequestDTO;
import com.priyajit.project.socialnetwork.model.Post;
import com.priyajit.project.socialnetwork.model.Reply;
import com.priyajit.project.socialnetwork.model.User;
import com.priyajit.project.socialnetwork.model.Vote;
import org.springframework.stereotype.Component;

@Component
public class VoteFactoryImpl implements VoteFactory {

    @Override
    public Vote create(CreateVoteRequestDTO dto, User voter, Post post) {
        return Vote.builder()
                .voter(voter)
                .post(post)
                .voteType(dto.getVoteType())
                .build();
    }

    @Override
    public Vote create(CreateVoteRequestDTO dto, User voter, Reply reply) {
        return Vote.builder()
                .voter(voter)
                .reply(reply)
                .voteType(dto.getVoteType())
                .build();
    }
}
