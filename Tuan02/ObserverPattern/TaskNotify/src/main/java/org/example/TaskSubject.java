package org.example;

public interface TaskSubject {
    void registerObserver(TaskObserver observer);
    void removeObserver(TaskObserver observer);
    void notifyObservers(String taskStatus);
}