package com.priyajit.project.socialnetwork.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Follow extends Model{

    @ManyToOne
    @JoinColumn(name = "followee_id", foreignKey = @ForeignKey(name = "FK_REPLY_FOLLOWEE"))
    private User followee;

    @ManyToOne
    @JoinColumn(name = "follower_id", foreignKey = @ForeignKey(name = "FK_REPLY_FOLLOWER"))
    private User follower;
}
