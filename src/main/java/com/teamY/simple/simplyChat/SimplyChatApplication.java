package com.teamY.simple.simplyChat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = {"com.teamY.simple"})
@SpringBootApplication
public class SimplyChatApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(SimplyChatApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(SimplyChatApplication.class);
	}
}
