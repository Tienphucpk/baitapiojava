package baptapiostream__java;

import java.io.File;

public class FileSize {
    public static long getFileSize(String filePath) {
        File file = new File(filePath);
        if (file.exists() && file.isFile()) {
            return file.length();
        } else {
            return -1; // Trả về -1 nếu file không tồn tại hoặc không phải là file
        }
    }

    public static void main(String[] args) {
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        System.out.print("Nhập đường dẫn của file: ");
        String filePath = scanner.nextLine();
        long size = getFileSize(filePath);
        if (size != -1) {
            System.out.println("Độ lớn của file là: " + size + " bytes");
        } else {
            System.out.println("File không tồn tại.");
        }
        scanner.close();
    }
}