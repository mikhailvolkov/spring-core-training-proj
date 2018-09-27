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
@Component
public class Every10thTicketStrategy implements DiscountStrategy {
    @Override
    public byte getDiscount(@Nullable User user, @Nonnull Event event, @Nonnull LocalDateTime airDateTime, long numberOfTickets) {
        long summaryTicketsCount = numberOfTickets + user.getTickets().size();
        if (summaryTicketsCount >= 10) {
            return (byte) (((summaryTicketsCount % 10) > 0) ? 50 : 0);
        }
        return 0;
    }
}
