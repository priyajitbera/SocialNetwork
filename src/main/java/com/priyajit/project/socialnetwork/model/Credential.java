package com.priyajit.project.socialnetwork.model;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Credential extends Model{

    @OneToOne
    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "FK_CREDENTIAL_USER"))
    private User user;

    @Column(nullable = false)
    private String secret;
}
