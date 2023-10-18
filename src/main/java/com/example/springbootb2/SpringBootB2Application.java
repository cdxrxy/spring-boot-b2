package com.example.springbootb2;

import com.example.springbootb2.repository.UserRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class SpringBootB2Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootB2Application.class, args);
	}

//	@Bean
//	public CommandLineRunner commandLineRunner(UserRepo userRepo) {
//		return args -> System.out.println(userRepo.findAll());
//	}

	@Bean
	public WebMvcConfigurer contentTypeConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
				configurer.ignoreAcceptHeader(true).defaultContentType(MediaType.APPLICATION_JSON);
			}
		};
	}
}