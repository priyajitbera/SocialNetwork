package com.priyajit.project.socialnetwork.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User extends Model{

    @Column(nullable = false, unique = true)
    private String handle;
    @Column(nullable = false)
    private String firstName;
    private String lastName;

    @OneToOne
    @JoinColumn(name = "credential_id", foreignKey = @ForeignKey(name = "FK_CREDENTIAL_USER"))
    private Credential credential;
}
