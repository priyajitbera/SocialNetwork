package com.priyajit.project.socialnetwork.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Post extends Model{

    @ManyToOne
    @JoinColumn(name = "creator_id", foreignKey = @ForeignKey(name = "FK_POST_USER"))
    private User creator;

    @Column(nullable = false)
    private String captionText;
}
