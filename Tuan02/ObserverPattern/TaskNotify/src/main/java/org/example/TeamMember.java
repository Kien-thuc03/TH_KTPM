package org.example;

public class TeamMember implements TaskObserver {
    private String name;

    public TeamMember(String name) {
        this.name = name;
    }

    @Override
    public void update(String taskStatus) {
        System.out.println(name + " received notification: Task status changed to '" + taskStatus + "'");
    }
}