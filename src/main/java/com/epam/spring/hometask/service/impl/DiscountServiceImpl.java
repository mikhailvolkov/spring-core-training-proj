package com.epam.spring.hometask.service.impl;

import com.epam.spring.hometask.discount.DiscountStrategy;
import com.epam.spring.hometask.domain.Event;
import com.epam.spring.hometask.domain.User;
import com.epam.spring.hometask.service.DiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Volkov_Mikhail
 */
@Service
public class DiscountServiceImpl implements DiscountService {
    @Autowired
    private List<DiscountStrategy> strategies;

    @Override
    public byte getDiscount(@Nullable User user, @Nonnull Event event, @Nonnull LocalDateTime airDateTime, long numberOfTickets) {
        byte discountPercent = 0;
        for (DiscountStrategy discountStrategy : strategies) {
            byte strategyPercent = discountStrategy.getDiscount(user, event, airDateTime, numberOfTickets);
            if (discountPercent < strategyPercent) {
                discountPercent = strategyPercent;
            }
        }
        return discountPercent;
    }
}
