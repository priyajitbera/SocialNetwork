package com.priyajit.project.socialnetwork.model;

import com.priyajit.project.socialnetwork.model.Model;
import com.priyajit.project.socialnetwork.model.User;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthToken extends Model {

    @Column(nullable = false)
    private String token;

    @Column(nullable = false)
    private Date issueDate;
}
