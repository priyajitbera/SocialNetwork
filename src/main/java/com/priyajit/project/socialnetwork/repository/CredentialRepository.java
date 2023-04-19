package com.priyajit.project.socialnetwork.repository;

import com.priyajit.project.socialnetwork.model.Credential;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CredentialRepository extends JpaRepository<Credential, Long> {

}
