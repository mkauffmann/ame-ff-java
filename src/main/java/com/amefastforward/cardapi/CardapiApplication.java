package com.amefastforward.cardapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableWebMvc
@SpringBootApplication
public class CardapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(CardapiApplication.class, args);
	}

}
