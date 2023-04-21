package com.priyajit.project.socialnetwork.dto.requestDTO;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
public class CreateReplyRequestDTO extends RequestDTO {

    private Long replierId;

    private Long postId;

    private Long replyId;

    private String replyText;
}
