package com.priyajit.project.socialnetwork.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CreateReplyRequestDTO {
    private Long replierId;
    private Long postId;
    private Long replyId;
    private String replyText;
}
