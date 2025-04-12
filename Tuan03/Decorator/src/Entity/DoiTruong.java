package Entity;

public class DoiTruong implements NhanVien {
    private String ten;

    public DoiTruong(String ten) {
        this.ten = ten;
    }

    private void quanLy(){
        System.out.println("1. Quản lý các đội viên.");
    }

    private void lenKeHoach(){
        System.out.println("2. Lên kế hoạch công việc cho đội.");
    }

    private void baoCao(){
        System.out.println("3. Báo cáo công việc với cấp trên.");
    }
    @Override
    public void printTasks() {
        System.out.println(ten + " là Đội trưởng. Các công việc cần làm:");
        quanLy();
        lenKeHoach();
        baoCao();

    }
}