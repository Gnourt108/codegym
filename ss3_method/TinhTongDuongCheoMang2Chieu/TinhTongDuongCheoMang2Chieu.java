package ss3_method.TinhTongDuongCheoMang2Chieu;

import java.util.InputMismatchException;
import java.util.Scanner;

public class TinhTongDuongCheoMang2Chieu {
    public static double[][] nhapMaTranVuong(Scanner sc){
        int n = 0;
        //Nhập kích thước của ma trận vuông
        while (true){
            try{
                System.out.println("Nhập kích thước của ma trận vuông (n*n): ");
                n = sc.nextInt();
                if(n <= 0){
                    System.out.println("Kích thước phải lớn hơn 0, vui lòng nhập lại!");
                    continue;
                }break;
            }catch (InputMismatchException e){
                System.out.println("Vui lòng nhập số nguyên hợp lệ!");
                sc.nextLine();
            }
        }
        double[][] mtv = new double[n][n];
        System.out.println("Nhập các phần tử của ma trận: ");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                while (true){
                    try{
                        System.out.printf("Nhập phần tử [%d][%d]: ",i, j);
                        mtv[i][j] = sc.nextDouble();
                        break;
                    }catch (InputMismatchException e){
                        System.out.println("Vui lòng nhập số thực hợp lệ");
                        sc.nextLine();
                    }
                }
            }
        }
        return mtv;
    }

    public static void inMaTran(double[][] matrix){
        System.out.printf("\nMa trận bạn đã nhập: \n");
        for (double[] row : matrix){
            for (double value : row){
                System.out.printf("%8.2f", value);
            }
            System.out.println();
        }
    }

    public static double tinhTongDongCheo(double[][] matrix){
        double sum = 0;
        for (int i = 0; i < matrix.length; i++) {
            sum += matrix[i][i];
        }
        return sum;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        double[][] arr = nhapMaTranVuong(sc);
        inMaTran(arr);
        System.out.println("Tổng các phần tử ở đường chéo chính là: "+tinhTongDongCheo(arr));
    }
}
