package com.epam.spring.hometask.config;

import com.epam.spring.hometask.discount.BirthDayStrategy;
import com.epam.spring.hometask.discount.DiscountStrategy;
import com.epam.spring.hometask.discount.Every10thTicketStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class DiscountsConfig {
    @Bean
    public List<DiscountStrategy> discounts() {
        List<DiscountStrategy> strategies = new ArrayList<>();
        strategies.add(new BirthDayStrategy());
        strategies.add(new Every10thTicketStrategy());
        return strategies;
    }

}
