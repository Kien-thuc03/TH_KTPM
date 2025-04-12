package org.example;

public class Main {
    public static void main(String[] args) {
        Stock appleStock = new Stock("AAPL", 150.0);

        Investor investor1 = new Investor("John Doe");
        Investor investor2 = new Investor("Jane Smith");

        appleStock.registerObserver(investor1);
        appleStock.registerObserver(investor2);

        System.out.println("Changing stock price to 155.00");
        appleStock.setPrice(155.00);

        System.out.println("\nRemoving investor1 from the observers list");
        appleStock.removeObserver(investor1);

        System.out.println("\nChanging stock price to 160.00");
        appleStock.setPrice(160.00);
    }
}