package com.example.springbootb2;

import com.example.springbootb2.repository.UserRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringBootB2Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootB2Application.class, args);
	}

//	@Bean
//	public CommandLineRunner commandLineRunner(UserRepo userRepo) {
//		return args -> System.out.println(userRepo.findAll());
//	}
}