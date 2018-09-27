package com.epam.spring.hometask.discount;

import com.epam.spring.hometask.domain.Event;
import com.epam.spring.hometask.domain.User;
import org.springframework.stereotype.Component;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

/**
 * @author Volkov_Mikhail
 */
@Component
public class BirthDayStrategy implements DiscountStrategy {

    @Override
    public byte getDiscount(@Nullable User user, @Nonnull Event event, @Nonnull LocalDateTime airDateTime, long numberOfTickets) {
        if (user != null && ChronoUnit.DAYS.between(user.getBirthDay().minusYears(user.getBirthDay().getYear()),
                airDateTime.minusYears(airDateTime.getYear())) <= 5) {
            return 5;
        }
        return 0;
    }
}
