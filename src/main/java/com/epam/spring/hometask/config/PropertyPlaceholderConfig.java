package com.epam.spring.hometask.config;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.IOException;

@Configuration
public class PropertyPlaceholderConfig {
    @Bean
    public static PropertyPlaceholderConfigurer ppc() throws IOException {
        PropertyPlaceholderConfigurer ppc = new PropertyPlaceholderConfigurer();
        Resource[] resources = new ClassPathResource[]
                {new ClassPathResource("auditorium_large.properties"),
                        new ClassPathResource("auditorium_small.properties"),
                        new ClassPathResource("jdbc.properties"),
                        new ClassPathResource("hibernate.properties")};
        ppc.setLocations(resources);
        ppc.setIgnoreUnresolvablePlaceholders(true);
        return ppc;
    }
}