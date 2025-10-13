package ss3_method.TinhTongPhanTuTheoCotMang2Chieu;

import java.util.InputMismatchException;
import java.util.Scanner;

public class TinhTongPhanTuCuaMangTheoCotMang2Chieu {
    public static double[][] nhapMangHaiChieu(Scanner sc){
        int rows = 0, cols = 0;
        while (true){
            try{
                System.out.println("Nhập số hàng: ");
                rows = sc.nextInt();
                if(rows <= 0){
                    System.out.println("Vui lòng nhập số hàng lớn hơn 0");
                    continue;
                }
                break;
            }catch (InputMismatchException e){
                System.out.println("Vui lòng nhập số nguyên hợp lệ!");
                sc.nextLine();
            }
        }
        while (true){
            try{
                System.out.println("Nhập số cột: ");
                cols = sc.nextInt();
                if(cols <= 0){
                    System.out.println("Vui lòng nhập số cột lớn hơn 0");
                    continue;
                }
                break;
            }catch (InputMismatchException e){
                System.out.println("Vui lòng nhập số nguyên hợp lệ!");
                sc.nextLine();
            }
        }

        double[][] matrix = new double[rows][cols];
        //Nhập từng phần tử
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                while (true){
                    try{
                        System.out.printf("Nhập phần tử thứ [%d][%d]: ", i, j);
                        matrix[i][j] = sc.nextInt();
                        break;
                    }catch (InputMismatchException e){
                        System.out.println("Sai định dạng, vui lòng nhập số nguyên");
                        sc.nextLine();
                    }
                }
            }
        }
        return matrix;
    }

    public static void inMang(double[][] matrix) {
        System.out.println("\nMa trận bạn đã nhập:");
        for (double[] row : matrix) {
            for (double value : row) {
                System.out.printf("%8.2f", value);
            }
            System.out.println();
        }
    }

    public static double tinhTongCot(double[][] matrix, int column){
        double sum = 0;
        for (int i = 0; i < matrix.length; i++) {
            sum += matrix[i][column];
        }
        return sum;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        double[][] arr = nhapMangHaiChieu(sc);
        inMang(arr);
        int column;
        while (true){
            try{
                System.out.println("Nhập chỉ số cột muốn tính tổng: ");
                column = sc.nextInt();
                if(column < 0 || column >= arr[0].length){
                    System.out.println("Cột không tồn tại, vui lòng nhập lại");
                    continue;
                }
                break;
            }catch (InputMismatchException e){
                System.out.println("Vui lòng nhập số nguyên hợp lệ");
                sc.nextLine();
            }
        }

        double sum = tinhTongCot(arr, column);
        System.out.println("Tổng các phần tử ở cột: "+column+" là: "+sum);

    }
}
