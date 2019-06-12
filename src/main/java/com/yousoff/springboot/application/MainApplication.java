package com.yousoff.springboot.application;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/*
 * References :
 * 1. http://websystique.com/spring-boot/spring-boot-rest-api-example/ <br>
 * 2. https://dzone.com/articles/using-mysql-jdbc-driver-with-spring-boot <br>
 * 3. https://spring.io/guides/gs/actuator-service/
 * 4. https://spring.io/guides/gs/spring-boot/
 */

/**
 * 
 * @author Yousoff Effendy
 * 
 */
@SpringBootApplication(scanBasePackages="com.yousoff.springboot")
public class MainApplication implements CommandLineRunner {
	
	private static final Logger logger = LoggerFactory.getLogger(MainApplication.class);
	
    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);
    }

	@Override
	public void run(String... arg0) throws Exception {
		logger.info("Swagger UI --> http://localhost:8080/swagger-ui.html ");
		
	}
	
}
