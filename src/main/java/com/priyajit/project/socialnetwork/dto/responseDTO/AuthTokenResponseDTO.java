package com.priyajit.project.socialnetwork.dto.responseDTO;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AuthTokenResponseDTO extends ResponseDTO {

    private Long userId;

    private String token;
}
