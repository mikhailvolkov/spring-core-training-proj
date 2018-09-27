package com.epam.spring.hometask.dao.impl;

import com.epam.spring.hometask.dao.BookingDAO;
import com.epam.spring.hometask.domain.Event;
import com.epam.spring.hometask.domain.Ticket;
import com.epam.spring.hometask.domain.User;
import com.epam.spring.hometask.service.BookingService;
import com.epam.spring.hometask.service.DiscountService;
import com.epam.spring.hometask.service.UserService;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

/**
 * @author Volkov_Mikhail
 */
@Repository
class BookingDAOImpl implements BookingDAO {
    @Autowired
    private DiscountService discountService;
    @Autowired
    private UserService userService;
    @Override
    public double getTicketsPrice(@Nonnull Event event, @Nonnull LocalDateTime dateTime, @Nullable User user, @Nonnull Set<Long> seats) {
        final byte discount = discountService.getDiscount(user, event, dateTime, seats.size());
        final int divider = 100;
        final int vipSeatsCostMupliply = 2;
        final double basePrice = event.getBasePrice();
        final double ratePriceMultipy = event.getRating().getPriceMultiply();
        final long vipSeats = event.getAuditoriums().get(dateTime).countVipSeats(seats);
        final long numberOfRegularSets = seats.size() - vipSeats;
        double price = vipSeats * vipSeatsCostMupliply * basePrice;
        price += price + numberOfRegularSets * ratePriceMultipy * basePrice;
        return price - (price / divider) * discount;
    }

    @Override
    public void bookTickets(@Nonnull Set<Ticket> tickets) {
        tickets.forEach((ticket -> ticket.getUser().getTickets().add(ticket)));
    }

    @Nonnull
    @Override
    public Set<Ticket> getPurchasedTicketsForEvent(@Nonnull Event event, @Nonnull LocalDateTime dateTime) {
        Set<Ticket> tickets = new TreeSet<>();
        userService.getAll().forEach((user -> tickets.addAll(user.getTickets().stream().filter(
                (ticket -> ticket.getEvent().equals(event) && ticket.getDateTime().equals(dateTime))).collect(Collectors.toSet()))));
        return tickets;

    }
}
