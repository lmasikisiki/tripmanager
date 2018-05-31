package com.khwela.khwelacore;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.khwela.khwelacore.commons.DateDeSerializerAdaper;
import com.khwela.khwelacore.commons.DateSerializerAdaper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;

import javax.servlet.ServletRegistration;
import java.time.LocalDate;

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


	@Bean
	 	public Gson jsonConverter(){
		GsonBuilder builder = new GsonBuilder();
		//builder.registerTypeAdapter(LocalDate.class, new DateSerializerAdaper());
	 builder.registerTypeAdapter(LocalDate.class, new DateDeSerializerAdaper());
		return builder.create();
 	 }
}
