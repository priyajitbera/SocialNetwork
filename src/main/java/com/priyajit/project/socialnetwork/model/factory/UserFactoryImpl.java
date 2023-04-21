package com.priyajit.project.socialnetwork.model.factory;

import com.priyajit.project.socialnetwork.dto.requestDTO.CreateUserRequestDTO;
import com.priyajit.project.socialnetwork.model.AuthToken;
import com.priyajit.project.socialnetwork.model.Credential;
import com.priyajit.project.socialnetwork.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;

@Component
public class UserFactoryImpl implements UserFactory{

    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserFactoryImpl(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User create(CreateUserRequestDTO dto){

        Credential credential = Credential.builder()
                .secret(passwordEncoder.encode(dto.getPassword()))
                .build();
        AuthToken authToken = AuthToken.builder()
                .token(UUID.randomUUID().toString())
                .issueDate(new Date())
                .build();
        return User.builder()
                .handle(dto.getHandle())
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .credential(credential)
                .authToken(authToken)
                .build();
    }
}
