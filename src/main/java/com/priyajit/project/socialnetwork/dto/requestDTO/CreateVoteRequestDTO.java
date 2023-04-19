package com.priyajit.project.socialnetwork.dto.requestDTO;

import com.priyajit.project.socialnetwork.model.VoteType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CreateVoteRequestDTO {
    private Long voterId;
    private Long postId;
    private Long replyId;
    private VoteType voteType;
}
