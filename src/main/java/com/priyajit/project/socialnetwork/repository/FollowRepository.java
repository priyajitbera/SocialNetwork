package com.priyajit.project.socialnetwork.repository;

import com.priyajit.project.socialnetwork.model.Follow;
import com.priyajit.project.socialnetwork.model.User;
import org.hibernate.metamodel.model.convert.spi.JpaAttributeConverter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FollowRepository extends JpaRepository<Follow, Long> {

    List<Follow> findAllByFollower(User follower);
}
