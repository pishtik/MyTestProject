package com.MySpringbootDemo.General;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.resource.ContentVersionStrategy;
import org.springframework.web.servlet.resource.ResourceUrlEncodingFilter;
import org.springframework.web.servlet.resource.VersionResourceResolver;

import com.MySpringbootDemo.Repositories.ProductDao;

@SpringBootApplication
@ComponentScan({ "com.MySpringbootDemo.*" })
@EntityScan("com.MySpringbootDemo.Models")
@EnableJpaRepositories("com.MySpringbootDemo.Repositories")

@EnableWebMvc
@Configuration
public class MySpringbootDemo implements WebMvcConfigurer   {
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		VersionResourceResolver versionResourceResolver = new VersionResourceResolver()
				.addVersionStrategy(new ContentVersionStrategy(), "/**");

		registry.addResourceHandler("/javascript/*.js", "/*.css","/images/**","/uploads/**", "/js/**")
				.addResourceLocations("classpath:/static/")
				.addResourceLocations("classpath:/static/images/")
				.addResourceLocations("classpath:/static/uploads/")
				.addResourceLocations("classpath:/static/js/")
				.setCachePeriod(1)/*60 * 60 * 24 * 365) one year */
				.resourceChain(true)
				.addResolver(versionResourceResolver);
	}

	@Bean
	public ResourceUrlEncodingFilter resourceUrlEncodingFilter() {
		return new ResourceUrlEncodingFilter();
	}

	@Autowired
	ProductDao productDao;

	public static void main(String[] args) {
		SpringApplication.run(MySpringbootDemo.class, args);
	}
}
