package ss4_OOP.PhuongTrinhBac2;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        try{
            System.out.println("Nhập a: ");
            double a = sc.nextDouble();
            System.out.println("Nhập b: ");
            double b = sc.nextDouble();
            System.out.println("Nhập c: ");
            double c = sc.nextDouble();

            PhuongTrinhBac2 pt = new PhuongTrinhBac2(a, b, c);
            double delta = pt.delta();
            if(delta>0){
                System.out.println("Phương trình có 2 nghiệm phân biệt: ");
                System.out.println("x1 = "+pt.nghiem1());
                System.out.println("x2 = "+pt.nghiem2());
            } else if (delta == 0) {
                System.out.println("Phương trình có nghiệm kép x = "+pt.nghiem1());
            }else{
                System.out.println("Phương trình vô nghiệm");
            }
        }catch (Exception e){
            System.out.println("Lỗi. Vui lòng nhập số khác hợp lệ!");
        }
    }
}
