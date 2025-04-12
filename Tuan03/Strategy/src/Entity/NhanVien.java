package Entity;

public class NhanVien {
    private String ten;
    private Strategy strategy;

    public NhanVien(String ten, Strategy strategy) {
        this.ten = ten;
        this.strategy = strategy;
    }

    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }

    public void printTasks() {
        strategy.printTasks();
    }
}
