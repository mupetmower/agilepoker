package agilepoker.configuration;

import org.h2.server.web.WebServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import agilepoker.services.GameSessionService;
import agilepoker.services.GameSessionServiceImpl;
import agilepoker.services.UserService;
import agilepoker.services.UserServiceImpl;
import agilepoker.services.UserStatisticsService;
import agilepoker.services.UserStatisticsServiceImpl;

@Configuration
public class WebConfiguration {
    @Bean
    ServletRegistrationBean h2servletRegistration(){
        ServletRegistrationBean registrationBean = new ServletRegistrationBean( new WebServlet());
        registrationBean.addUrlMappings("/console/*");
        return registrationBean;
    }
    

    
    @Bean
    GameSessionService gameSessionService(){
        return new GameSessionServiceImpl();
    }
    
    @Bean
    UserService userService(){
        return new UserServiceImpl();
    }
    
    @Bean
    UserStatisticsService userStatisticsService(){
        return new UserStatisticsServiceImpl();
    }
}
