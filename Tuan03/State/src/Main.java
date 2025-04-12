import Entity.*;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        Scanner scanner = new Scanner(System.in);

        System.out.println("Nhập tên nhân viên:");
        String ten = scanner.nextLine();

        System.out.println("Nhập chức vụ (Đội trưởng, Giám đốc, NV Văn phòng, NV Xưởng, Kế toán trưởng):");
        String chucVu = scanner.nextLine();

        NhanVien nhanVien = null;
        State state = null;

        switch (chucVu.toLowerCase()) {
            case "đội trưởng":
                state = new DoiTruong(ten);
                break;
            case "giám đốc":
                state = new GiamDoc(ten);
                break;
            case "nv văn phòng":
                state = new NVVanPhong(ten);
                break;
            case "nv xưởng":
                state = new NVXuong(ten);
                break;
            case "kế toán trưởng":
                state = new KeToanTruong(ten);
                break;
            default:
                System.out.println("Chức vụ không hợp lệ.");
                return;
        }

        nhanVien = new NhanVien(ten, state);

        // In ra các công việc cần làm của nhân viên theo chức vụ
        nhanVien.printTasks();
    }
}