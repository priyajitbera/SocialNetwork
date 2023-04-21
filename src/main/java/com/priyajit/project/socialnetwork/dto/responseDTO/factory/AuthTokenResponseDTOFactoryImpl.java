package com.priyajit.project.socialnetwork.dto.responseDTO.factory;

import com.priyajit.project.socialnetwork.dto.responseDTO.AuthTokenResponseDTO;
import com.priyajit.project.socialnetwork.model.AuthToken;
import org.springframework.stereotype.Component;

@Component
public class AuthTokenResponseDTOFactoryImpl implements AuthTokenResponseDTOFactory {

    @Override
    public AuthTokenResponseDTO create(AuthToken authToken, Long userId) {
        return AuthTokenResponseDTO.builder()
                .userId(userId)
                .token(authToken.getToken())
                .build();
    }
}
