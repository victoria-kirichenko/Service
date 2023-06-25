package org.project.service.config;

import org.project.service.controller.ControllerImpl;
import org.project.service.repository.RepositoryImpl;
import org.project.service.service.BusinessLogicImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "org.project.service.config")
public class AppConfig {

    @Bean
    public RepositoryImpl userRepository() {
        return new RepositoryImpl();
    }

    @Bean
    public BusinessLogicImpl businessLogic() {
        return new BusinessLogicImpl();
    }

    @Bean
    public ControllerImpl controller() {
        return new ControllerImpl();
    }

}
