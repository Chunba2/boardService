package com.example.exam_board;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
//@EnableJpaAuditing
public class ExamBoardApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExamBoardApplication.class, args);
	}

}
