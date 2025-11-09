package com.gtalent.projectSpringR2DBC;

import io.r2dbc.spi.ConnectionFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.r2dbc.connection.init.ConnectionFactoryInitializer;
import org.springframework.r2dbc.connection.init.ResourceDatabasePopulator;
import org.springframework.web.reactive.config.EnableWebFlux;

@EnableWebFlux
@SpringBootApplication
public class ProjectSpringR2DbcApplication {

	@Bean
	public ConnectionFactoryInitializer connectionFactoryInitializer(ConnectionFactory connectionFactory){
		ResourceDatabasePopulator populator = new ResourceDatabasePopulator(new ClassPathResource("schema.sql"));
		ConnectionFactoryInitializer init = new ConnectionFactoryInitializer();
		init.setConnectionFactory(connectionFactory);
		init.setDatabasePopulator(populator);
		return  init;
	}

	public static void main(String[] args) {
		SpringApplication.run(ProjectSpringR2DbcApplication.class, args);
	}

}
