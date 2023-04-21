package com.priyajit.project.socialnetwork.dto.responseDTO;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class PostResponseDTO extends ResponseDTO {

    private Long postId;

    private String creatorName;

    private String creatorHandle;

    private String creationDate;

    private String captionText;

    private Long voteCount;

    private Long replyCount;

    private List<VoteResponseDTO> voteResponseDTOList;

    private List<ReplyResponseDTO> replyResponseDTOList;
}
