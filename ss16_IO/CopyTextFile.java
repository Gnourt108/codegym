package ss16_IO;

import common.InputHelper;

import java.io.*;
import java.util.Scanner;

public class CopyTextFile {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String sourceFile = InputHelper.inputString("Nhập tên tệp nguồn: ");

        String targetFile = InputHelper.inputString("Nhập tên tệp đích: ");

        File src = new File(sourceFile);
        File dest = new File(targetFile);

        if(!src.exists()){
            System.out.println("Tệp nguồn không tồn tại!");
            return;
        }

        if(dest.exists()){
            System.out.println("Tệp đích đã tồn tại! Vui lòng chọn tên khác!");
            return;
        }

        int charCount = 0;

        try(BufferedReader reader = new BufferedReader(new FileReader(src))) {
            BufferedWriter write = new BufferedWriter(new FileWriter(dest));
            int c;
            while ((c = reader.read()) != -1){
                write.write(c);
                charCount++;
            }

            System.out.println("Sao chép tệp thành công!");
            System.out.println("Số ký tự trong tệp: "+charCount);
        } catch (IOException e) {
            System.out.println("Lỗi khi đọc hoặc ghi file: "+e.getMessage());
        }
    }
}
