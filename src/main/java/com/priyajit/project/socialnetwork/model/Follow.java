package com.priyajit.project.socialnetwork.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(uniqueConstraints = {
        // constraint ensuring only one record is inserted for a unique followee and follower
        @UniqueConstraint(name = "UQ_FOLLOW_FOLLOWEE_ID_FOLLOWER_ID", columnNames = {"followee_id", "follower_id"})
})
public class Follow extends Model {

    @ManyToOne
    @JoinColumn(name = "followee_id", foreignKey = @ForeignKey(name = "FK_REPLY_FOLLOWEE"))
    private User followee;

    @ManyToOne
    @JoinColumn(name = "follower_id", foreignKey = @ForeignKey(name = "FK_REPLY_FOLLOWER"))
    private User follower;
}
