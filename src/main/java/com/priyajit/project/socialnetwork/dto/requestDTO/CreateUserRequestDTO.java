package com.priyajit.project.socialnetwork.dto.requestDTO;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
public class CreateUserRequestDTO extends RequestDTO {

    private String handle;

    private String firstName;

    private String lastName;

    private String password;
}
