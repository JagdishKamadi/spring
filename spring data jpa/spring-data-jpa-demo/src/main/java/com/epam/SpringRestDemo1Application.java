package com.epam;

import com.epam.welcome.Greeting;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class SpringRestDemo1Application {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(SpringRestDemo1Application.class, args);
		Greeting greeting = context.getBean(Greeting.class);
		greeting.sayHello();
	}

}
