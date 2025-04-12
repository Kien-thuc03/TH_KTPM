import Decorator.HuongDanDecorator;
import Decorator.KiemToanDecorator;
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
        if (chucVu.equalsIgnoreCase("Đội trưởng")) {
            nhanVien = new DoiTruong(ten);
        } else if (chucVu.equalsIgnoreCase("Giám đốc")) {
            nhanVien = new GiamDoc(ten);
        } else if(chucVu.equalsIgnoreCase("NV văn phòng")) {
            nhanVien = new NVVanPhong(ten);
        } else if(chucVu.equalsIgnoreCase("NV xưởng")) {
            nhanVien = new NVXuong(ten);
        }else if(chucVu.equalsIgnoreCase("Kế toán trưởng")) {
            nhanVien = new KeToanTruong(ten);
        }else{
            System.out.println("Chức vụ không hợp lệ.");
            return;
        }

        System.out.println("Nhân viên có muốn thêm công việc bổ sung không? (y/n)");
        String themCongViec = scanner.nextLine();

        if (themCongViec.equalsIgnoreCase("y")) {
            System.out.println("Nhập công việc bổ sung (1: Kiểm toán, 2: Hướng dẫn):");
            String congViecBoSung = scanner.nextLine();

            if (congViecBoSung.equals("1")) {
                nhanVien = new KiemToanDecorator(nhanVien);
            } else if (congViecBoSung.equals("2")) {
                nhanVien = new HuongDanDecorator(nhanVien);
            }
        }

        nhanVien.printTasks();
    }
}