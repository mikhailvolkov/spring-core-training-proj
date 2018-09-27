package com.epam.spring.hometask.aspects;

import com.epam.spring.hometask.domain.User;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Aspect
@Component
@EnableAspectJAutoProxy
public class DiscountAspect {
    private Map<String, Map<Class<?>, Integer>> infoMap = new HashMap<>();
    private Map<Class<?>, Integer> totalDiscountCounterMap = new HashMap<>();

    @Pointcut(value = "execution(* com.epam.spring.hometask.discount.DiscountStrategy.getDiscount(..)) && args(user)", argNames = "user")
    private void execGetDiscount(User user) {

    }

    @AfterReturning(
            pointcut = "execGetDiscount(user)",
            returning = "res",
            argNames = "jp,user,res")
    public void logAfterReturningDiscount(JoinPoint jp, User user, byte res) {
        if (res != 0) {
            String userEmail = user.getEmail();
            Class<?> discountType = jp.getTarget().getClass();
            calculateDiscountsCountersForUser(userEmail, discountType);
            calculateTotalDiscountsCounters(discountType);
        }
    }

    private void calculateDiscountsCountersForUser(String userEmail, Class<?> discountType) {
        Map<Class<?>, Integer> methodCounterMap = infoMap.get(userEmail);
        if (methodCounterMap == null) {
            methodCounterMap = new HashMap<>();
            methodCounterMap.put(discountType, 1);
            infoMap.put(userEmail, methodCounterMap);
        } else {
            Integer counter = methodCounterMap.get(discountType);
            if (counter != null) {
                methodCounterMap.put(discountType, counter + 1);
                infoMap.put(userEmail, methodCounterMap);
            } else {
                methodCounterMap = new HashMap<>();
                methodCounterMap.put(discountType, 1);
                infoMap.put(userEmail, methodCounterMap);
            }
        }
        System.out.println("*****************************************************");
        System.out.println("User email: " + userEmail);
        System.out.println("Discount type : " + discountType.getName());
        System.out.println("Summary counter: " + methodCounterMap.get(discountType));
        System.out.println("*****************************************************\n");
    }

    private void calculateTotalDiscountsCounters(Class<?> discountType) {
        if (totalDiscountCounterMap.get(discountType) == null) {
            totalDiscountCounterMap.put(discountType, 1);
        } else {
            Integer counter = totalDiscountCounterMap.get(discountType);
            totalDiscountCounterMap.put(discountType, counter + 1);
        }
        System.out.println("*****************************************************");
        System.out.println("Discount type : " + discountType.getName());
        System.out.println("Summary counter: " + totalDiscountCounterMap.get(discountType));
        System.out.println("*****************************************************\n");
    }
}
