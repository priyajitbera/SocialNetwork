package com.priyajit.project.socialnetwork.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CreatePostRequestDTO {
    private Long creatorId;
    private String captionText;
}
