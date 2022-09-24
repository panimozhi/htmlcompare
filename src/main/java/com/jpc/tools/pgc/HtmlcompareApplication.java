package com.jpc.tools.pgc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableAutoConfiguration
@EnableScheduling
@ComponentScan(" com.jpc.tools.pgc")
public class HtmlcompareApplication {

	public static void main(String[] args) {
		SpringApplication.run(HtmlcompareApplication.class, args);
	}

}
