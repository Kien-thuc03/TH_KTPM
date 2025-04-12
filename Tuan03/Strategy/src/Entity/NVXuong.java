package Entity;

public class NVXuong implements Strategy {
    private String ten;

    public NVXuong(String ten) {
        this.ten = ten;
    }

    private void sanXuat(){
        System.out.println("1. Thực hiện công việc sản xuất theo kế hoạch.");
    }

    private void kiemTra(){
        System.out.println("2. Kiểm tra chất lượng sản phẩm.");
    }

    private void baoCao(){
        System.out.println("3. Báo cáo tình trạng sản xuất cho quản lý.");
    }
    @Override
    public void printTasks() {
        System.out.println(ten + " là nhân viên xưởng. Các công việc cần làm:");
        sanXuat();
        kiemTra();
        baoCao();
    }
}
