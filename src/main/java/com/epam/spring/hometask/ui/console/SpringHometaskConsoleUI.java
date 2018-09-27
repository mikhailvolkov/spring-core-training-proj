package com.epam.spring.hometask.ui.console;

import java.time.LocalDateTime;
import java.util.Collections;

import com.epam.spring.hometask.config.*;
import com.epam.spring.hometask.service.impl.AuditoriumServiceImpl;
import com.epam.spring.hometask.service.impl.BookingServiceImpl;
import com.epam.spring.hometask.service.impl.EventServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;

import com.epam.spring.hometask.domain.Auditorium;
import com.epam.spring.hometask.domain.Ticket;
import com.epam.spring.hometask.domain.User;
import com.epam.spring.hometask.service.AuditoriumService;
import com.epam.spring.hometask.service.BookingService;
import com.epam.spring.hometask.service.EventService;
import com.epam.spring.hometask.ui.console.state.MainState;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.support.ClassPathXmlApplicationContext;
public class SpringHometaskConsoleUI {
    private AnnotationConfigApplicationContext context;

    public static void main(String[] args) {
        SpringHometaskConsoleUI ui = new SpringHometaskConsoleUI();
        ui.initContext();
        ui.run();
    }

    private void initContext() {
        context = new AnnotationConfigApplicationContext();
        context.scan("com.epam.spring.hometask.config", "com.epam.spring.hometask.dao.impl",
                "com.epam.spring.hometask.service.impl", "com.epam.spring.hometask.discount", "com.epam.spring.hometask.aspects");
        context.refresh();
    }

    private void run() {
        System.out.println("Welcome to movie theater console service");
        fillInitialData();
        MainState state = new MainState(context);
        state.run();
        System.out.println("Exiting.. Thank you.");
    }

    private void fillInitialData() {
        AuditoriumService auditoriumService = context.getBean(AuditoriumService.class);
        EventService eventService = context.getBean(EventService.class);
        BookingService bookingService = context.getBean(BookingService.class);
        Auditorium auditorium = auditoriumService.getAll().iterator().next();
        if (auditorium == null) {
            throw new IllegalStateException("Failed to fill initial data - no auditoriums returned from AuditoriumService");
        }
        if (auditorium.getNumberOfSeats() <= 0) {
            throw new IllegalStateException("Failed to fill initial data - no seats in the auditorium " + auditorium.getName());
        }
        LocalDateTime airDate = (LocalDateTime) context.getBean("firstEventDateTimeOne");
        if (auditorium.getNumberOfSeats() > 1) {
            User userNotRegistered = new User();
            userNotRegistered.setEmail("somebody@a.b");
            userNotRegistered.setFirstName("A");
            userNotRegistered.setLastName("Somebody");
            Ticket ticket2 = new Ticket(userNotRegistered, eventService.getAll().stream().findFirst().get(), airDate, 2);
            bookingService.bookTickets(Collections.singleton(ticket2));
        }
    }
}
