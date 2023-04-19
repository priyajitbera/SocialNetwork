package com.priyajit.project.socialnetwork.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@MappedSuperclass
@Getter
@Setter
public class Model {

    // primary key, generation type auto increment
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    // indicate the date the record was created
    private Date creationDate = new Date();
}
