package Decorator;

import Entity.NhanVien;

public class HuongDanDecorator extends NhanVienDecorator {
    public HuongDanDecorator(NhanVien nhanVien) {
        super(nhanVien);
    }
    private void huongDan(){
        System.out.println("4. Hướng dẫn các nhân viên mới.");
    }
    @Override
    public void printTasks() {
        super.printTasks();
        huongDan();
    }
}
