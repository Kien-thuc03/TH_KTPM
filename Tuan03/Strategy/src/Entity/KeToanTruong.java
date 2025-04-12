package Entity;

public class KeToanTruong implements Strategy {
    private String ten;

    public KeToanTruong(String ten) {
        this.ten = ten;
    }

    private void quanLyTaiChinh(){
        System.out.println("1. Quản lý tài chính của công ty.");
    }

    private void lapBaoCao(){
        System.out.println("2. Lập báo cáo tài chính.");
    }

    private void kiemSoatChiPhi(){
        System.out.println("3. Kiểm soát chi phí và thu chi.");
    }
    @Override
    public void printTasks() {
        System.out.println(ten + " là Kế toán trưởng. Các công việc cần làm:");
        quanLyTaiChinh();
        lapBaoCao();
        kiemSoatChiPhi();
    }
}