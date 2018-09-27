package com.epam.spring.hometask.config;

import com.epam.spring.hometask.domain.Auditorium;
import com.epam.spring.hometask.domain.Event;
import com.epam.spring.hometask.domain.EventRating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import java.time.LocalDateTime;
import java.util.*;

@Configuration
public class EventsConfig {
    @Autowired
    private Set<Auditorium> auditoriums;
    private LocalDateTime firstAirDate;
    private LocalDateTime secondAirDate;

    @Bean
    public List<Event> events() {
        Event event = new Event();
        event.setRating(EventRating.HIGH);
        event.setId(123456L);
        event.setName("Alien");
        event.setBasePrice(300);
        NavigableMap<LocalDateTime, Auditorium> auditoriumsMap = new TreeMap<>();
        firstAirDate = LocalDateTime.of(2017, 12, 25, 15, 40);
        secondAirDate = LocalDateTime.of(2017, 12, 25, 18, 30);
        NavigableSet<LocalDateTime> times = new TreeSet<>();
        times.add(firstAirDate);
        times.add(secondAirDate);
        Auditorium[] auditoriumsMas = new Auditorium[2];
        auditoriums.toArray(auditoriumsMas);
        auditoriumsMap.put(firstAirDate, auditoriumsMas[0]);
        auditoriumsMap.put(secondAirDate, auditoriumsMas[1]);
        event.setAuditoriums(auditoriumsMap);
        event.setAirDates(times);
        List<Event> events = new ArrayList<>();
        events.add(event);
        return events;
    }

    @Bean(name = "firstEventDateTimeOne")
    public LocalDateTime firstAirDate() {
        return firstAirDate;
    }

    @Bean(name = "firstEventDateTimeTwo")
    public LocalDateTime secondAirDate() {
        return secondAirDate;
    }

}
