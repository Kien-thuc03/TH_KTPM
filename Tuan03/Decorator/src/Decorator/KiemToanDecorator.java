package Decorator;

import Entity.NhanVien;

public class KiemToanDecorator extends NhanVienDecorator{
    public KiemToanDecorator(NhanVien nhanVien) {
        super(nhanVien);
    }

    private void kiemTra(){
        System.out.println("4. Kiểm tra báo cáo tài chính.");
    }
    @Override
    public void printTasks() {
        super.printTasks();
        kiemTra();
    }
}
