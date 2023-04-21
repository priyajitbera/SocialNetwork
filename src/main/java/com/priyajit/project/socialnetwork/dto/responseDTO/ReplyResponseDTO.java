package com.priyajit.project.socialnetwork.dto.responseDTO;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class ReplyResponseDTO extends ResponseDTO {

    private Long replyId;

    private String replierName;

    private String replierHandle;

    private String creationDate;

    private String replyText;

    private Long voteCount;

    private Long replyCount;

    // List of votes to this reply
    private List<VoteResponseDTO> votes;

    // List of replies to this reply
    private List<ReplyResponseDTO> replies;
}
