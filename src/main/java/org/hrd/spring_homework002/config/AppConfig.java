package org.hrd.spring_homework002.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
public class AppConfig {

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource source = new DriverManagerDataSource();
        source.setUrl("jdbc:postgresql://localhost:5432/basic_mybatis");
        source.setUsername("visal");
        source.setPassword("reaksmey02");

        return source;
    }
}
