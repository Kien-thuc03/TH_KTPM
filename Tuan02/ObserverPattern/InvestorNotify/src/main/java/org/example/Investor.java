package org.example;

public class Investor implements Observer {
    private String name;

    public Investor(String name) {
        this.name = name;
    }

    @Override
    public void update(String stockSymbol, double price) {
        System.out.printf("%s received update: %s stock price changed to %.2f%n", name, stockSymbol, price);
    }
}