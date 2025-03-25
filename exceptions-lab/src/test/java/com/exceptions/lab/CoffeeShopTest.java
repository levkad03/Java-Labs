package com.exceptions.lab;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CoffeeShopTest {
    private CoffeeShopImpl coffeeShop = new CoffeeShopImpl(3);
    private CoffeeShopImpl noCoffee = new CoffeeShopImpl(0);

    @Test
    void buyCoffee() throws NoCoffeeException {
        int initialAmount = coffeeShop.coffeeAmount();
        coffeeShop.buyCoffee();
        assertEquals(initialAmount-1, coffeeShop.coffeeAmount());


        NoCoffeeException exception = assertThrows(NoCoffeeException.class, () -> {
            noCoffee.buyCoffee();
        });

        assertEquals("There is no coffee available", exception.getMessage());
    }

    @Test
    void buyCoffeeIfPresent() {
        assertTrue(coffeeShop.buyCoffeeIfPresent());
        assertFalse(noCoffee.buyCoffeeIfPresent());
    }

    @Test
    void giveFeedback() {
        coffeeShop.giveFeedback("Everything is fine");
        List<String> feedbacks = coffeeShop.readFeedbacks();

        assertEquals(1, feedbacks.size());
        assertEquals("Everything is fine", feedbacks.get(0));

        assertThrows(BadFeedBackException.class, () -> {
            coffeeShop.giveFeedback("This coffee is bad");
        });

        assertThrows(BadFeedBackException.class, () -> {
            coffeeShop.giveFeedback("This coffee is worst");
        });

        assertThrows(BadFeedBackException.class, () -> {
            coffeeShop.giveFeedback("This coffee is not tasty");
        });

        assertThrows(BadFeedBackException.class, () -> {
            coffeeShop.giveFeedback("This coffee is not drinkable");
        });

        assertThrows(BadFeedBackException.class, () -> {
            coffeeShop.giveFeedback("");
        });

        assertThrows(BadFeedBackException.class, () -> {
            coffeeShop.giveFeedback(" ");
        });

    }

    @Test
    void readFeedbacks() {

        assertEquals(0, coffeeShop.readFeedbacks().size());

        coffeeShop.giveFeedback("Everything is fine");
        List<String> feedbacks = coffeeShop.readFeedbacks();

        assertEquals(1, feedbacks.size());
        assertEquals("Everything is fine", feedbacks.get(0));
    }

    @Test
    void coffeeAmount() throws NoCoffeeException {
        assertEquals(3, coffeeShop.coffeeAmount());
        coffeeShop.buyCoffee();
        assertEquals(2, coffeeShop.coffeeAmount());
    }
}