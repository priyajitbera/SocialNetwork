package com.priyajit.project.socialnetwork.dto.responseDTO.factory;

import com.priyajit.project.socialnetwork.dto.responseDTO.UserResponseDTO;
import com.priyajit.project.socialnetwork.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserResponseDTOFactoryImpl implements UserResponseDTOFactory {

    @Override
    public UserResponseDTO create(User user) {

        return UserResponseDTO.builder()
                .userId(user.getId())
                .name(user.getFirstName() + " " + user.getLastName())
                .handle(user.getHandle())
                .build();
    }
}
