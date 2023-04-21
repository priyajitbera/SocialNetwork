package com.priyajit.project.socialnetwork.dto.responseDTO;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserResponseDTO extends ResponseDTO {

    private Long userId;

    private String handle;

    private String name;
}
