package com.priyajit.project.socialnetwork.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CreateUserRequestDTO {
    private String handle;
    private String firstName;
    private String lastName;
    private String password;
}
