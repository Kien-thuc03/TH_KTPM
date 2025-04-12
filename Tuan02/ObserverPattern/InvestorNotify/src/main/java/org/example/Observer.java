package org.example;

public interface Observer {
    void update(String stockSymbol, double price);
}