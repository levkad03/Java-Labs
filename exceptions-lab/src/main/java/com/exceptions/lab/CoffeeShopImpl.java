package com.exceptions.lab;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CoffeeShopImpl implements CoffeeShop {

    private int coffeeCount;
    private List<String> feedbacks;
    private static final List<String> BAD_WORDS = Arrays.asList("bad", "worst", "not tasty", "not drinkable");

    public CoffeeShopImpl(int initialCoffeeAmount) {
        this.coffeeCount = initialCoffeeAmount;
        this.feedbacks = new ArrayList<String>();
    }

    @Override
    public void buyCoffee() throws NoCoffeeException {

        if (!buyCoffeeIfPresent()) {
            throw new NoCoffeeException("There is no coffee available");
        }
        coffeeCount--;

    }

    @Override
    public boolean buyCoffeeIfPresent() {
        return coffeeCount > 0;
    }

    @Override
    public void giveFeedback(String feedback) throws BadFeedBackException {
        if (feedback.isBlank() || containsBadWords(feedback)) {
            throw new BadFeedBackException("We cannot accept this feedback");
        }
        feedbacks.add(feedback);
    }

    private boolean containsBadWords(String feedback) {
        String lowerCaseFeedBack = feedback.toLowerCase();

        for (String word:BAD_WORDS){
            if (lowerCaseFeedBack.contains(word)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public List<String> readFeedbacks() {
        return feedbacks;
    }

    @Override
    public int coffeeAmount() {
        return coffeeCount;
    }
}
