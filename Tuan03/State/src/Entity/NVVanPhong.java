package Entity;

// Lớp NV Văn phòng
public class NVVanPhong implements State  {
    private String ten;

    public NVVanPhong(String ten) {
        this.ten = ten;
    }

    private void xuLyGiayTo(){
        System.out.println("1. Tiếp nhận và xử lý các giấy tờ, hồ sơ.");
    }

    private void luuTruDuLieu(){
        System.out.println("2. Lưu trữ dữ liệu, quản lý tài liệu.");
    }

    private void hoTro(){
        System.out.println("3. Hỗ trợ công việc hành chính.");
    }
    @Override
    public void printTasks() {
        System.out.println(ten + " là nhân viên văn phòng. Các công việc cần làm:");
        xuLyGiayTo();
        luuTruDuLieu();
        hoTro();
    }
}