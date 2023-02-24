package com.example.notes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.event.ValidatingRepositoryEventListener;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@SpringBootApplication
@EnableEurekaClient
public class NotesApplication implements RepositoryRestConfigurer {

	public static void main(String[] args) {
		SpringApplication.run(NotesApplication.class, args);
	}

        @Bean
        Validator validator() {
            return new LocalValidatorFactoryBean();
        }

        @Override
        public void configureValidatingRepositoryEventListener(final ValidatingRepositoryEventListener validatingListener) {
            validatingListener.addValidator("beforeCreate", validator());
        }
}
