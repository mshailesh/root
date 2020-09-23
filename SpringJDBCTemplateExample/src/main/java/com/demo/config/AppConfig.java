package com.demo.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
//
@Configuration
@PropertySource("classpath:database.properties")
public class AppConfig {

    @Autowired
    Environment environment;

    private final String URL = "jdbc:mysql://localhost/school";
    private final String USER = "root";
    private final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private final String PASSWORD = "root";

    @Bean
    DataSource dataSource() {
        return DataSourceBuilder
                .create()
                .username(USER)
                .password(PASSWORD)
                .url(URL)
                .driverClassName(DRIVER)
                .build();
    }
}
