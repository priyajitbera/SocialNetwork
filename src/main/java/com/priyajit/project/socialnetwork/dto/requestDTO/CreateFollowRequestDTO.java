package com.priyajit.project.socialnetwork.dto.requestDTO;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
public class CreateFollowRequestDTO extends RequestDTO {

    private Long followerId;

    private Long followeeId;
}
