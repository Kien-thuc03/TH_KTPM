package Entity;

// Lớp cơ sở NhanVien
public abstract class NhanVien {
    String ten;

    NhanVien(String ten) {
        this.ten = ten;
    }

    public abstract void printTasks();
}

