package com.priyajit.project.socialnetwork.dto.requestDTO;

import com.priyajit.project.socialnetwork.dto.responseDTO.AuthTokenResponseDTO;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
public abstract class RequestDTO {

    private AuthTokenResponseDTO authTokenResponseDTO;
}
