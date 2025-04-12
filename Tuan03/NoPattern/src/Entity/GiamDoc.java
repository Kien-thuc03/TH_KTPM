package Entity;

public class GiamDoc extends NhanVien {
    public GiamDoc(String ten) {
        super(ten);
    }
     private void quanLy(){
         System.out.println("1. Quản lý toàn bộ công ty.");
     }

     private void chienLuoc(){
         System.out.println("2. Đưa ra chiến lược phát triển công ty.");
     }

     private void quyetDinh(){
         System.out.println("3. Đưa ra quyết định quan trọng.");
     }
    @Override
    public void printTasks() {
        System.out.println(ten + " là Giám đốc. Các công việc cần làm:");
        quanLy();
        chienLuoc();
        quyetDinh();
    }
}