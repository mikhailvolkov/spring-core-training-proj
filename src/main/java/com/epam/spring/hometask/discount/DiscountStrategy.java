package com.epam.spring.hometask.discount;


import com.epam.spring.hometask.domain.Event;
import com.epam.spring.hometask.domain.User;
import org.springframework.stereotype.Component;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.time.LocalDateTime;

/**
 * @author Volkov_Mikhail
 */
public interface DiscountStrategy {
    public byte getDiscount(@Nullable User user, @Nonnull Event event, @Nonnull LocalDateTime airDateTime, long numberOfTickets);
}
