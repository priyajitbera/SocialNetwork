package com.priyajit.project.socialnetwork.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class User extends Model{

    @Column(nullable = false)
    private String handle;
    @Column(nullable = false)
    private String firstName;
    private String lastName;
}
