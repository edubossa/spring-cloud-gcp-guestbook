package com.example.guestbook;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// ./mvnw -q spring-boot:run -Dserver.port=8081 -Dspring.profiles.active=cloud
@SpringBootApplication
public class GuestbookApplication {

	public static void main(String[] args) {
		SpringApplication.run(GuestbookApplication.class, args);
	}
}
