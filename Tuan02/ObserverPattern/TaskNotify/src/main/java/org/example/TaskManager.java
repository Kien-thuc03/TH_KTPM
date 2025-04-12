package org.example;

import java.util.ArrayList;
import java.util.List;

public class TaskManager implements TaskSubject {
    private List<TaskObserver> observers;
    private String taskStatus;

    public TaskManager() {
        observers = new ArrayList<>();
    }

    @Override
    public void registerObserver(TaskObserver observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(TaskObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(String taskStatus) {
        for (TaskObserver observer : observers) {
            observer.update(taskStatus);
        }
    }

    public void setTaskStatus(String taskStatus) {
        this.taskStatus = taskStatus;
        notifyObservers(taskStatus);
    }
}