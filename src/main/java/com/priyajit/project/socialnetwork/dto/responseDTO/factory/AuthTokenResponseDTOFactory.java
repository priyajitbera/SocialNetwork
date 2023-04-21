package com.priyajit.project.socialnetwork.dto.responseDTO.factory;

import com.priyajit.project.socialnetwork.dto.responseDTO.AuthTokenResponseDTO;
import com.priyajit.project.socialnetwork.model.AuthToken;

public interface AuthTokenResponseDTOFactory {

    AuthTokenResponseDTO create(AuthToken authToken, Long userId);
}
