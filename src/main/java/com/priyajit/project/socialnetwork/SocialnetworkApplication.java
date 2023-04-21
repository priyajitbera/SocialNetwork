package com.priyajit.project.socialnetwork;

import com.priyajit.project.socialnetwork.client.ApplicationClient;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class SocialnetworkApplication implements CommandLineRunner {

	private final ApplicationClient applicationClient;

	@Autowired
	public SocialnetworkApplication(ApplicationClient applicationClient) {
		this.applicationClient = applicationClient;
	}

	public static void main(String[] args) {
		SpringApplication.run(SocialnetworkApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		applicationClient.start();
	}
}
