import Entity.*;

import java.util.Scanner;
//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Nhập tên nhân viên:");
        String ten = scanner.nextLine();

        System.out.println("Nhập chức vụ (Đội trưởng, Giám đốc, NV Văn phòng, NV Xưởng, Kế toán trưởng):");
        String chucVu = scanner.nextLine();

        NhanVien nhanVien = null;

        switch (chucVu.toLowerCase()) {
            case "đội trưởng":
                nhanVien = new DoiTruong(ten);
                break;
            case "giám đốc":
                nhanVien = new GiamDoc(ten);
                break;
            case "nv văn phòng":
                nhanVien = new NVVanPhong(ten);
                break;
            case "nv xưởng":
                nhanVien = new NVXuong(ten);
                break;
            case "kế toán trưởng":
                nhanVien = new KeToanTruong(ten);
                break;
            default:
                System.out.println("Chức vụ không hợp lệ.");
                return;
        }

        nhanVien.printTasks();
    }
}