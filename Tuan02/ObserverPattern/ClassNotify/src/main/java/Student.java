import java.util.ArrayList;
import java.util.List;

public class Student implements Observer {
    private String name;
    private String studentId;
    private List<ClassRoom> enrolledClasses;

    public Student(String name, String studentId) {
        this.name = name;
        this.studentId = studentId;
        this.enrolledClasses = new ArrayList<>();
    }

    @Override
    public void update(String message) {
        System.out.println("Student " + name + " (ID: " + studentId + ") received message: " + message);
    }

    public void enrollInClass(ClassRoom classRoom) {
        enrolledClasses.add(classRoom);
    }

    public void unenrollFromClass(ClassRoom classRoom) {
        enrolledClasses.remove(classRoom);
    }
}