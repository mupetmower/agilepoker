package agilepoker.demo;

import org.hibernate.Hibernate;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import agilepoker.domain.UserStatistics;

@SpringBootApplication
@ComponentScan(basePackages = { "agilepoker.*"})
public class DemoApplication {

	
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);

		
	}
}
