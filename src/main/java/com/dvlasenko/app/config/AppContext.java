package com.dvlasenko.app.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.util.Objects;


@Configuration
@ComponentScan("com.dvlasenko.app")
@PropertySource("classpath:app.properties")
public class AppContext {

    @Autowired
    Environment environment;

    @Bean
    DataSource dataSource() {
        DriverManagerDataSource driverManagerDataSource =
                new DriverManagerDataSource();
        driverManagerDataSource.setUrl(environment.getProperty("dbUrl"));
        driverManagerDataSource.setUsername(environment.getProperty("dbUser"));
        driverManagerDataSource.setPassword(environment.getProperty("dbPass"));
        driverManagerDataSource.setDriverClassName(
                Objects.requireNonNull(environment.getProperty("jdbcDriver")));
        return driverManagerDataSource;
    }
}
