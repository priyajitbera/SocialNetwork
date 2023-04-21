package com.priyajit.project.socialnetwork.dto.requestDTO;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
public class LoginRequestDTO extends RequestDTO {

    private Long userId;

    private String handle;

    private String password;
}
