import java.util.ArrayList;
import java.util.List;

public class ClassRoom implements Subject {
    private String className;
    private List<Observer> students;

    public ClassRoom(String className) {
        this.className = className;
        this.students = new ArrayList<>();
    }

    @Override
    public void attach(Observer observer) {
        students.add(observer);
    }

    @Override
    public void detach(Observer observer) {
        students.remove(observer);
    }

    @Override
    public void notifyObservers(String message) {
        for (Observer student : students) {
            student.update(message);
        }
    }

    public String getClassName() {
        return className;
    }
}