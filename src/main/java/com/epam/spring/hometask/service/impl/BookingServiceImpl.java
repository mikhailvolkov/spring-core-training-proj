package com.epam.spring.hometask.service.impl;

import com.epam.spring.hometask.dao.BookingDAO;
import com.epam.spring.hometask.domain.Event;
import com.epam.spring.hometask.domain.Ticket;
import com.epam.spring.hometask.domain.User;
import com.epam.spring.hometask.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.time.LocalDateTime;
import java.util.Set;

/**
 * @author Volkov_Mikhail
 */
@Service
public class BookingServiceImpl implements BookingService {
    @Autowired
    private BookingDAO bookingDAO;

    public BookingServiceImpl(BookingDAO bookingDAO) {
        this.bookingDAO = bookingDAO;
    }

    @Override
    public double getTicketsPrice(@Nonnull Event event, @Nonnull LocalDateTime dateTime, @Nullable User user, @Nonnull Set<Long> seats) {
        return bookingDAO.getTicketsPrice(event, dateTime, user, seats);
    }

    @Override
    public void bookTickets(@Nonnull Set<Ticket> tickets) {
        bookingDAO.bookTickets(tickets);
    }

    @Nonnull
    @Override
    public Set<Ticket> getPurchasedTicketsForEvent(@Nonnull Event event, @Nonnull LocalDateTime dateTime) {
        return bookingDAO.getPurchasedTicketsForEvent(event, dateTime);
    }
}
