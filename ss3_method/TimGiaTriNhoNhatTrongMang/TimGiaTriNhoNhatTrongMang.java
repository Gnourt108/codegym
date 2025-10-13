package ss3_method.TimGiaTriNhoNhatTrongMang;

import java.util.InputMismatchException;
import java.util.Scanner;

public class TimGiaTriNhoNhatTrongMang {
    public static int[] nhapMang(Scanner sc){
        int size = 0;
        while(true){
            try{
                System.out.println("Nhập số phần tử của mảng: ");
                size = sc.nextInt();
                if(size <= 0){
                    System.out.println("Số phần tử phải lớn hơn 0, vui lòng nhập lại!");
                    continue;
                }
                break;
            }catch (InputMismatchException e){
                System.out.println("Vui lòng nhập số nguyên hợp lệ!");
                sc.nextLine();
            }
        }
        int[] arr = new int[size];

        //nhập từng phần tử
        for (int i = 0; i < arr.length; i++) {
            while (true){
                try{
                    System.out.printf("Nhập phần tử thứ %d: ", (i+1));
                    arr[i] = sc.nextInt();
                    break;
                }catch (InputMismatchException e){
                    System.out.println("Sai định dạng, vui lòng nhập số nguyên");
                    sc.nextLine();
                }
            }
        }
        return arr;
    }

    public static void inMang(int[] arr){
        System.out.print("\nMảng vừa nhập: ");
        for (int value : arr) {
            System.out.print(value+" ");
        }
        System.out.println();
    }

    public static int timMin(int[] arr){
        int min = arr[0];
        for (int i = 0; i < arr.length; i++) {
            if(arr[i] < min){
                min = arr[i];
            }
        }
        return min;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] arr = nhapMang(sc);
        inMang(arr);
        int min = timMin(arr);
        System.out.println("Giá trị nhỏ nhất trong mảng là: "+min);
    }
}
