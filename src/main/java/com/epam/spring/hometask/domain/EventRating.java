package com.epam.spring.hometask.domain;

/**
 * @author Volkov_Mikhail
 */
public enum EventRating {

    LOW(1),

    MID(1.2),

    HIGH(2);
    private final double priceMultiply;

    EventRating(double priceMultiply) {
        this.priceMultiply = priceMultiply;
    }

    public double getPriceMultiply() {
        return priceMultiply;
    }
}
