package com.priyajit.project.socialnetwork.dto.requestDTO;

import com.priyajit.project.socialnetwork.model.VoteType;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
public class CreateVoteRequestDTO extends RequestDTO {

    private Long voterId;

    private Long postId;

    private Long replyId;

    private VoteType voteType;
}
