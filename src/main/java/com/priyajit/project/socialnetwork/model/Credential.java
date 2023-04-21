package com.priyajit.project.socialnetwork.model;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Credential extends Model{

    @Column(nullable = false)
    private String secret;
}
