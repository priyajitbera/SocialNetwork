package com.priyajit.project.socialnetwork.dto.responseDTO;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class FollowResponseDTO extends ResponseDTO {

    private UserResponseDTO followee;

    private UserResponseDTO follower;
}
