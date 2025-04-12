
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
        Strategy strategy = null;

        switch (chucVu.toLowerCase()) {
            case "đội trưởng":
                strategy = new DoiTruong(ten);
                break;
            case "giám đốc":
                strategy = new GiamDoc(ten);
                break;
            case "nv văn phòng":
                strategy = new NVVanPhong(ten);
                break;
            case "nv xưởng":
                strategy = new NVXuong(ten);
                break;
            case "kế toán trưởng":
                strategy = new KeToanTruong(ten);
                break;
            default:
                System.out.println("Chức vụ không hợp lệ.");
                return;
        }

        nhanVien = new NhanVien(ten, strategy);

        nhanVien.printTasks();
    }
}