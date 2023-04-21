package com.priyajit.project.socialnetwork.dto.requestDTO;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
public class CreatePostRequestDTO extends RequestDTO {

    private Long creatorId;

    private String captionText;
}
