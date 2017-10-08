package com.MySpringbootDemo.General;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.MySpringbootDemo.Repositories.ProductDao;



@SpringBootApplication
@ComponentScan({"com.MySpringbootDemo.*"})
@EntityScan("com.MySpringbootDemo.Models")
@EnableJpaRepositories("com.MySpringbootDemo.Repositories")


public class SpringbootIn10StepsApplication extends SpringBootServletInitializer{



	@Autowired
    ProductDao productDao;

	
	public static void main(String[] args) {
		SpringApplication.run(SpringbootIn10StepsApplication.class, args);
	}
}
