package com.app;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
@ComponentScan("com")
public class ProductAppConfig {

	@Bean
	public JdbcTemplate getJdbcTemplate() {

		DriverManagerDataSource dataSource = new DriverManagerDataSource(
				"jdbc:mysql://localhost:3306/pinventory?useSSL=false&allowPublicKeyRetrieval=true");
		dataSource.setUsername("root");
		dataSource.setPassword("secret");

		return new JdbcTemplate(dataSource);
	}

}
