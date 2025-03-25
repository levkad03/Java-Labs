package com.exceptions.lab;

import java.util.List;
import java.util.Scanner;

public class Main {
    private static CoffeeShop coffeeShop;
    private static Scanner scanner;

    public static void main(String[] args) {
        coffeeShop = new CoffeeShopImpl(10);
        scanner = new Scanner(System.in);

        displayMenu();
        while (true) {
            System.out.print("Enter your choice: ");
            int choice = getMenuChoice();

            switch (choice) {
                case 1:
                    try {
                        coffeeShop.buyCoffee();
                        System.out.println("You bought coffee");
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }

                    break;
                case 2:
                    boolean success = coffeeShop.buyCoffeeIfPresent();
                    if (success) {
                        System.out.println("You can buy coffee!");
                    } else {
                        System.out.println("Sorry, we're out of coffee.");
                    }
                    break;
                case 3:
                    System.out.print("Enter your feedback: ");
                    String feedback = scanner.nextLine();

                    try {
                        coffeeShop.giveFeedback(feedback);
                        System.out.println("Thank you for your feedback!");
                    } catch (RuntimeException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 4:
                    List<String> feedbacks = coffeeShop.readFeedbacks();

                    if (feedbacks.isEmpty()) {
                        System.out.println("No feedbacks yet.");
                    } else {
                        System.out.println("\n===== Feedbacks =====");
                        for (int i = 0; i < feedbacks.size(); i++) {
                            System.out.println((i + 1) + ". " + feedbacks.get(i));
                        }
                    }
                    break;
                case 5:
                    int amount = coffeeShop.coffeeAmount();
                    System.out.println("Current coffee amount: " + amount);
                    break;
                case 0:
                    System.out.println("Thank you for visiting our Coffee Shop!");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }

        }

    }

    private static void displayMenu() {
        System.out.println("\n===== Coffee Shop Menu =====");
        System.out.println("1. Buy coffee");
        System.out.println("2. Buy coffee if available");
        System.out.println("3. Give feedback");
        System.out.println("4. Read feedbacks");
        System.out.println("5. Check coffee amount");
        System.out.println("0. Exit");
    }

    private static int getMenuChoice() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}