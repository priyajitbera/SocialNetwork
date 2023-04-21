package com.priyajit.project.socialnetwork.model.factory;

import com.priyajit.project.socialnetwork.dto.requestDTO.CreateUserRequestDTO;
import com.priyajit.project.socialnetwork.model.User;

public interface UserFactory {
    User create(CreateUserRequestDTO dto);
}
