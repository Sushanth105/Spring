package com.beanlerning.beanlearning;

// import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

@SpringBootApplication
public class BeanlearningApplication {

	public static void main(String[] args) {
		var appCtx = SpringApplication.run(BeanlearningApplication.class, args);

		MyFirstService myFirstService = appCtx.getBean(MyFirstService.class);
		System.out.println(myFirstService.tellStory());
		Environment environment = appCtx.getBean(Environment.class);
		System.out.println(environment.getProperty("java.version"));
		System.out.println(environment.getProperty("os.name"));
		System.out.println(environment.getProperty("spring.application.name"));
	}

	// @Bean
	// public MyFirstClass myFirstClass(){
	// 	return new MyFirstClass();
	// }

}
