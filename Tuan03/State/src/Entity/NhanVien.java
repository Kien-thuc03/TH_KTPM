package Entity;

// Lớp cơ sở NhanVien
public class NhanVien {
    String ten;
    private State state;

    public NhanVien(String ten, State state) {
        this.ten = ten;
        this.state = state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public void printTasks() {
        state.printTasks();
    }
}

