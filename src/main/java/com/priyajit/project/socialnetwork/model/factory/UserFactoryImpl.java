package com.priyajit.project.socialnetwork.model.factory;

import com.priyajit.project.socialnetwork.dto.requestDTO.CreateUserRequestDTO;
import com.priyajit.project.socialnetwork.model.Credential;
import com.priyajit.project.socialnetwork.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserFactoryImpl {

    public User create(CreateUserRequestDTO dto){
        Credential credential = Credential.builder()
                .secret(dto.getPassword())
                .build();
        return User.builder()
                .handle(dto.getHandle())
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .credential(credential)
                .build();
    }
}
