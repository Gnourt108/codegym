package ss3_method.TimPhanTuLonNhatTrongMangHaiChieu;

import java.util.InputMismatchException;
import java.util.Scanner;

public class TimPhanTuLonNhatTrongMangHaiChieu {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        double[][] matrix = nhapMangHaiChieu(sc);
        inMang(matrix);
        double max = matrix[0][0];
        int row = 0, col = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if(matrix[i][j] > max){
                    max = matrix[i][j];
                    row = i;
                    col = j;
                }
            }
        }
        System.out.println("Giá trị lớn nhất trong mảng hai chiều là: "+max);
        System.out.println("Tọa độ hàng: "+(row+1)+" cột: "+(col+1));
    }
    public static double[][] nhapMangHaiChieu(Scanner sc){
        int rows = 0, cols = 0;
        while(true){
            try{
                System.out.println("Nhập số hàng: ");
                rows = sc.nextInt();
                if(rows <= 0){
                    System.out.println("Số hàng phải lớn hơn 0, vui lòng nhập lại!");
                    continue;
                }
                break;
            }catch (InputMismatchException e){
                System.out.println("Vui lòng nhập số nguyên hợp lệ!");
                sc.close();
            }
        }
        while(true){
            try{
                System.out.println("Nhập số cột: ");
                cols = sc.nextInt();
                if(cols <= 0){
                    System.out.println("Số cột phải lớn hơn 0, vui lòng nhập lại!");
                    continue;
                }
                break;
            }catch (InputMismatchException e){
                System.out.println("Vui lòng nhập số nguyên hợp lệ!");
                sc.close();
            }
        }

        double[][] matrix = new double[rows][cols];

        System.out.println("Nhập các phần tử của ma trận: ");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                while(true){
                    try{
                        System.out.printf("Nhập phần tử [%d][%d]: ", i, j);
                        matrix[i][j] = sc.nextDouble();
                        break;
                    }catch (InputMismatchException e){
                        System.out.println("Sai định dạng, vui lòng nhập số thực!");
                        sc.nextLine();
                    }
                }
            }
        }
        return matrix;
    }
    public static void inMang(double[][] matrix){
        System.out.println("\n Ma trận bạn đã nhập: ");
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.printf("%8.2f", matrix[i][j]);
            }
            System.out.println();
        }
    }
}
