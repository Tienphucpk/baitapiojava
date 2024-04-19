package baptapiostream__java;
import java.io.File;
import java.util.Scanner;

public class XoaFile {

    public static void deleteFileOrDirectory(String path) {
        try {
            File fileOrDir = new File(path);
            if (fileOrDir.exists()) {
                if (fileOrDir.isFile()) {
                    fileOrDir.delete();
                    System.out.println("Đã xoá file: " + path);
                } else if (fileOrDir.isDirectory()) {
                    fileOrDir.delete();
                    System.out.println("Đã xoá thư mục: " + path);
                }
            } else {
                System.out.println("Đường dẫn không tồn tại.");
            }
        } catch (Exception e) {
            System.out.println("Lỗi: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("Nhập đường dẫn của thư mục hoặc file bạn muốn xoá (nhập 't' để thoát): ");
            String path = scanner.nextLine();
            if (path.equalsIgnoreCase("t")) {
                break;
            } else {
                deleteFileOrDirectory(path);
            }
        }
        scanner.close();
    }
}
