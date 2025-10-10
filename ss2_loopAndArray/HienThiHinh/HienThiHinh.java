package ss2_loopAndArray.HienThiHinh;

import java.util.Scanner;

public class HienThiHinh {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice;
        do {
            System.out.println("""
                MENU
                1. Vẽ hình chữ nhật
                2. Vẽ hình tam giác vuông (4 góc khác nhau)
                3. Vẽ hình tam giác cân
                4. Thoát!""");
            choice = sc.nextInt();
            sc.nextLine();
            switch (choice) {
                case 1:
                    printRetangle();
                    break;
                case 2:
                    printSquareTriangle();
                    break;
                case 3:
                    printIsoscelesTriangle();
                    break;
                case 4:
                    System.out.println("Đã thoát chương trình!");
                    break;
                default:
                    System.out.println("Vui lòng chọn 1 trong 4 option!");
            }
        }while (choice != 4);


    }

    private static void printIsoscelesTriangle() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Nhập chiều cao");
        int height = sc.nextInt();
        for (int i = 1; i <= height ; i++) {
            for (int j = i; j < height; j++) {
                System.out.print(" ");
            }
            for (int j = 1; j <= (2*i-1); j++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }

    private static void printSquareTriangle() {
        Scanner sc = new Scanner(System.in);
        System.out.println("""
                Chọn góc
                1. bottom-left
                2. top-left
                3. bottom-right
                4. top-right""");
        int type = sc.nextInt();
        int n = 5;
        switch (type){
            case 1:
                for (int i = 1; i <= n; i++) {
                    for (int j = 1; j <= i ; j++) {
                        System.out.print("* ");
                    }
                    System.out.println();
                }
                break;
            case 2:
                for (int i = n; i >= 1; i--) {
                    for (int j = 1; j <= i ; j++) {
                        System.out.print("* ");
                    }
                    System.out.println();
                }
                break;
            case 3:
                for (int i = 1; i <= n; i++) {
                    for (int j = i; j < n; j++) {
                        System.out.print("  ");
                    }
                    for (int j = 1; j <= i; j++) {
                        System.out.print("* ");
                    }
                    System.out.println();
                }
                break;
            case 4:
                for (int i = n; i >= 1; i--) {
                    for (int j = n; j > i; j--) {
                        System.out.print("  ");
                    }
                    for (int j = 1; j <= i; j++) {
                        System.out.print("* ");
                    }
                    System.out.println();
                }
                break;
            default:
                System.out.println("Lựa chọn không hợp lệ!");
        }
    }

    private static void printRetangle() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Nhập chiều cao: ");
        int height = sc.nextInt();
        System.out.println("Nhập chiều dài");
        int weight = sc.nextInt();
        for (int i = 0; i < height ; i++) {
            for (int j = 0; j < weight; j++) {
                System.out.print("* ");
            }
            System.out.println();
        }               
    }
}
