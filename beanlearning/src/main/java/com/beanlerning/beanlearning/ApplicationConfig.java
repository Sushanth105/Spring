package com.beanlerning.beanlearning;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration //Spring Scan the Class at the starting
public class ApplicationConfig {
    
    @Bean
    @Profile("dev") // This is only available in dev environment
    // @Profile("test") // Here the application gives the error
    public MyFirstClass myFirstClass(){
        return new MyFirstClass();
    }
}
