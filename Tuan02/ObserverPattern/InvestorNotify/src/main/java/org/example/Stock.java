package org.example;

import java.util.ArrayList;
import java.util.List;

public class Stock implements Subject {
    private List<Observer> observers;
    private String symbol;
    private double price;

    public Stock(String symbol, double price) {
        this.observers = new ArrayList<>();
        this.symbol = symbol;
        this.price = price;
    }

    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(symbol, price);
        }
    }

    public void setPrice(double newPrice) {
        this.price = newPrice;
        notifyObservers();
    }

    public double getPrice() {
        return price;
    }

    public String getSymbol() {
        return symbol;
    }
}