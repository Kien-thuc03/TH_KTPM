package Decorator;

import Entity.NhanVien;

public abstract class NhanVienDecorator implements NhanVien {
    protected NhanVien nhanVien;

    public NhanVienDecorator(NhanVien nhanVien) {
        this.nhanVien = nhanVien;
    }

    public void printTasks() {
        nhanVien.printTasks();
    }
}
