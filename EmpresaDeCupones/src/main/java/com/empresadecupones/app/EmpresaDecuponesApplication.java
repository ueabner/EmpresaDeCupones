package com.empresadecupones.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins="*")
@EnableAutoConfiguration
@EnableConfigurationProperties
@SpringBootApplication
public class EmpresaDecuponesApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmpresaDecuponesApplication.class, args);
	}

}
