package com.korea.WhereToGo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class WhereToGoApplication {

	public static void main(String[] args) {
		SpringApplication.run(WhereToGoApplication.class, args);
	}

}
