package com.epam.spring.hometask.config;

import com.epam.spring.hometask.domain.Auditorium;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

@Configuration
public class AuditoriumsConfig {
    @Value("${name.small}")
    private String smallName;
    @Value("${numberOfSeats.small}")
    private long smallNumberOfSeats;
    @Value("#{T(com.epam.spring.hometask.util.StringToSetConverter).convert('${vipSeats.small}',',')}")
    private Set<Long> smallVipSeats;
    @Value("${name.large}")
    private String largeName;
    @Value("${numberOfSeats.large}")
    private long largeNumberOfSeats;
    @Value("#{T(com.epam.spring.hometask.util.StringToSetConverter).convert('${vipSeats.large}',',')}")
    private Set<Long> largeVipSeats;

    @Bean
    public Set<Auditorium> auditoriums() {
        Set<Auditorium> auditoriums = new HashSet<>();
        auditoriums.add(new Auditorium(smallName, smallNumberOfSeats, smallVipSeats));
        auditoriums.add(new Auditorium(largeName, largeNumberOfSeats, largeVipSeats));
        return auditoriums;
    }
}
