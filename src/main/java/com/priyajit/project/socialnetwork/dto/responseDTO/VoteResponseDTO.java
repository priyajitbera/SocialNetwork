package com.priyajit.project.socialnetwork.dto.responseDTO;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class VoteResponseDTO extends ResponseDTO {

    private Long voteId;

    private String voterName;

    private String voterHandle;

    private String creationDate;

    private String voteType;
}
