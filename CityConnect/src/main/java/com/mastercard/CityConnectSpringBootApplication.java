package com.mastercard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("com.mastercard")
@SpringBootApplication
public class CityConnectSpringBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(CityConnectSpringBootApplication.class, args);
	}
}
