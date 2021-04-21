package com.sms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

//@SpringBootApplication
//@ComponentScan ({"com.server", "com.server.config"})
//@EnableJpaRepositories(basePackages = { "fr.investstore.repositories" })

@SpringBootApplication
@ComponentScan(basePackages = "com.sms")
public class sms {

	public static void main(String[] args) {
		SpringApplication.run(sms.class, args);
	}

}
