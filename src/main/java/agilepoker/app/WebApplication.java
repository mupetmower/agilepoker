package agilepoker.app;

import org.hibernate.Hibernate;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import agilepoker.domain.UserStatistics;

@SpringBootApplication
@ComponentScan(basePackages = { "agilepoker.*"})
public class WebApplication {

	
	public static void main(String[] args) {
		SpringApplication.run(WebApplication.class, args);

		
	}
}
