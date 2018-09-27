package com.epam.spring.hometask.aspects;

import com.epam.spring.hometask.domain.Event;
import com.epam.spring.hometask.domain.Ticket;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Aspect
@Component
@EnableAspectJAutoProxy
public class CounterAspect {
    private Map<String, Map<String, Integer>> infoMap = new HashMap<>();

    @AfterReturning(
            pointcut = "execution(* com.epam.spring.hometask.service.EventService.getByName(..))",
            returning = "event")
    public void logAfterReturningName(JoinPoint jp, Event event) {
        printCounterInfoForEventAndMethod(event, jp);
    }

    @Pointcut(value = "execution(* com.epam.spring.hometask.dao.BookingDAO.getTicketsPrice(..)) && args(event)", argNames = "event")
    private void execGetTicketsPrice(Event event) {

    }

    @Pointcut(value = "execution(* com.epam.spring.hometask.dao.BookingDAO.bookTickets(..)) && args(tickets)", argNames = "tickets")
    private void execBookTickets(Set<Ticket> tickets) {

    }

    @Before(value = "execGetTicketsPrice(event)",
            argNames = "jp,event")
    public void logBeforeGetTicketsPrice(JoinPoint jp, Event event) {
        printCounterInfoForEventAndMethod(event, jp);
    }

    @Before(value = "execBookTickets(tickets)",
            argNames = "jp, tickets")
    public void logBeforeBookTickets(JoinPoint jp, Set<Ticket> tickets) {
        printCounterInfoForEventAndMethod(((Ticket) tickets.toArray()[0]).getEvent(), jp);
    }

    private void printCounterInfoForEventAndMethod(Event event, JoinPoint joinPoint) {
        String eventName = event.getName();
        String methodName = joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName();
        Map<String, Integer> counterMap = infoMap.get(eventName);
        Integer counter = null;
        if (counterMap != null) {
            counter = counterMap.get(methodName);
        } else {
            counterMap = new HashMap<>();
        }
        if (counter != null) {
            counterMap.put(methodName, counter + 1);
            infoMap.put(eventName, counterMap);
        } else {
            counterMap.put(methodName, 1);
            infoMap.put(event.getName(), counterMap);
        }
        System.out.println("*****************************************************");
        System.out.println("Event name: " + eventName);
        System.out.println("Method call : " + methodName);
        System.out.println("Summary counter: " + counterMap.get(methodName));
        System.out.println("*****************************************************\n");
    }
}
