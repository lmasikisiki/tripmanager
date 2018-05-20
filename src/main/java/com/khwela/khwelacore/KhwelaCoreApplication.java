package com.khwela.khwelacore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "com.khwela.khwelacore")
public class KhwelaCoreApplication {

    public static void main(String[] args) {
		SpringApplication.run(KhwelaCoreApplication.class, args);
	}
}
