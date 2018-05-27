package com.khwela.khwelacore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;

import javax.servlet.ServletRegistration;

@SpringBootApplication
@EntityScan(basePackages = "com.khwela.khwelacore")
public class KhwelaCoreApplication {

	//@Bean
	public ServletRegistrationBean servletRegistration(){
		ServletRegistrationBean registration = new ServletRegistrationBean();
		registration.addUrlMappings("/console/*");
		return  registration;
	}
    public static void main(String[] args) {
		SpringApplication.run(KhwelaCoreApplication.class, args);
	}


}
