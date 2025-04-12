package org.example;

public class Main {
    public static void main(String[] args) {
        TaskManager taskManager = new TaskManager();

        TeamMember member1 = new TeamMember("John");
        TeamMember member2 = new TeamMember("Alice");
        TeamMember member3 = new TeamMember("Bob");

        taskManager.registerObserver(member1);
        taskManager.registerObserver(member2);
        taskManager.registerObserver(member3);

        System.out.println("Changing task status to 'In Progress'...");
        taskManager.setTaskStatus("In Progress");

        System.out.println("\nRemoving member2 from observers...");
        taskManager.removeObserver(member2);

        System.out.println("\nChanging task status to 'Completed'...");
        taskManager.setTaskStatus("Completed");
    }
}