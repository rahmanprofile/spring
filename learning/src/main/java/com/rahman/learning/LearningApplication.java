package com.rahman.learning;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.Async;

@Async
@SpringBootApplication
public class LearningApplication {

	public static void main(String[] args) {
        SpringApplication.run(LearningApplication.class, args);
	}

}
