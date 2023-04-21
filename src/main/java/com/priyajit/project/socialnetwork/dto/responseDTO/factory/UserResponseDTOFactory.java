package com.priyajit.project.socialnetwork.dto.responseDTO.factory;

import com.priyajit.project.socialnetwork.dto.responseDTO.UserResponseDTO;
import com.priyajit.project.socialnetwork.model.User;

public interface UserResponseDTOFactory {
    UserResponseDTO create(User user);
}
