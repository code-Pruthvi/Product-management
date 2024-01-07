package com.litmus.product.management;

import org.springframework.beans.factory.annotation.Value;
//H2Config.java
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories(basePackages = "com.litmus.product")
//@Profile("dev")  // Use this configuration only in the "dev" profile
public class H2Config {

	@Value("${spring.datasource.driverClassName}")
	private String driverClassName;

	@Value("${spring.datasource.url}")
	private String url;

	@Value("${spring.datasource.username}")
	private String username;

	@Value("${spring.datasource.password}")
	private String password;

 @Bean
 public DataSource dataSource() {
     DriverManagerDataSource dataSource = new DriverManagerDataSource();
     dataSource.setDriverClassName(driverClassName);
     dataSource.setUrl(url);
     dataSource.setUsername(username);
     dataSource.setPassword(password);
     return dataSource;
 }
}

