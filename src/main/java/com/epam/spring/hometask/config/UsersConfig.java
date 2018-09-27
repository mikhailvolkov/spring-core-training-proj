package com.epam.spring.hometask.config;

import com.epam.spring.hometask.domain.Event;
import com.epam.spring.hometask.domain.Ticket;
import com.epam.spring.hometask.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class UsersConfig {
    @Autowired
    private List<Event> events;

    @Bean
    public List<User> users() {
        List<User> users = new ArrayList<>();
        User user = new User();
        user.setId(123456L);
        user.setEmail("ivan@gmail.com");
        user.setFirstName("Ivan");
        user.setLastName("Petrov");
        user.setBirthDay(LocalDate.of(1993, 12, 23));
        user.getTickets().add(new Ticket(user, events.get(0), LocalDateTime.of(2017, 12, 25, 15, 40), 10));
        users.add(user);
        return users;
    }
}
