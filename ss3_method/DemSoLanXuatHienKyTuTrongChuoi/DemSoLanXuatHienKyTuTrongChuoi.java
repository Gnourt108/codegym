package ss3_method.DemSoLanXuatHienKyTuTrongChuoi;

import java.util.InputMismatchException;
import java.util.Scanner;

public class DemSoLanXuatHienKyTuTrongChuoi {
    public static void main(String[] args) {
        try{
            String str = nhapChuoi();
            char ch = nhapKyTu();
            int count = demKyTu(str, ch);
            System.out.println("------------------------------------");
            System.out.println("Chuỗi: "+str);
            System.out.println("Ký tự: '"+ch+"' xuất hiện "+count+" lần trong chuỗi");
        }catch (Exception e){
            System.out.println("Đã xảy ra lỗi: "+e.getMessage());
        }
    }
    public static String nhapChuoi(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Nhập chuỗi cần kiểm tra: ");
        return sc.nextLine();
    }

    public static char nhapKyTu(){
        Scanner sc = new Scanner(System.in);
        char ch = 0;
        try{
            System.out.println("Nhập ký tự cần đếm: ");
            String input = sc.nextLine();
            if(input.length() != 1){
                throw new IndexOutOfBoundsException("Vui lòng chỉ nhập 1 ký tự!");
            }
            ch = input.charAt(0);
        }catch (InputMismatchException e){
            System.out.println("Lỗi: "+e.getMessage());
            return nhapKyTu();
        }
        return ch;
    }

    public static int demKyTu(String str, char ch){
        int count = 0;
        for (int i = 0; i < str.length(); i++) {
            if(str.charAt(i) == ch){
                count++;
            }
        }
        return count;
    }

}
