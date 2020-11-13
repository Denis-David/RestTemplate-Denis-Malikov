package net.basil.resttemplate;

import net.basil.resttemplate.service.RestService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class ResttemplateApplication {

	public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(ResttemplateApplication.class, args);
 //       context.getBean(RestService.class).getAllUsers();
    }

}
