package com.cross.chain.payment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import springfox.documentation.oas.annotations.EnableOpenApi;

@EnableMongoAuditing
@SpringBootApplication
public class CrossPayApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrossPayApplication.class, args);
	}

}
