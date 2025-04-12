public class Main {
    public static void main(String[] args) {
        ClassRoom classRoom1 = new ClassRoom("KTPM2021");
        ClassRoom classRoom2 = new ClassRoom("KTPM2022");
        ClassRoom classRoom3 = new ClassRoom("KTPM2023");

        Student student1 = new Student("Nguyen Van A", "001");
        Student student2 = new Student("Tran Thi B", "002");
        Student student3 = new Student("Le Van C", "003");

        classRoom1.attach(student1);
        classRoom1.attach(student2);
        classRoom1.attach(student3);

        classRoom2.attach(student1);
        classRoom2.attach(student2);

        classRoom3.attach(student2);
        classRoom3.attach(student3);

        System.out.println("Class monitor sends a notification to class " + classRoom1.getClassName());
        classRoom1.notifyObservers("Meeting tomorrow at 8AM in Room 305");

        System.out.println("\nClass monitor sends a notification to class " + classRoom2.getClassName());
        classRoom2.notifyObservers("Project presentation next Monday");

        System.out.println("\nClass monitor sends a notification to class " + classRoom3.getClassName());
        classRoom3.notifyObservers("Online quiz this Friday");
    }
}