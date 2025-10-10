package ss2_loopAndArray.GopMang;

import java.util.Arrays;
import java.util.Scanner;

public class GopMang {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Nhập số phần tử trong mảng thứ nhất: ");
        int n1 = sc.nextInt();
        System.out.println("Nhập số phần tử trong mảng thứ hai: ");
        int n2 = sc.nextInt();
        int[] arr1 = new int[n1];
        int[] arr2 = new int[n2];
        System.out.println("Nhập liệu mảng 1");
        for (int i = 0; i < arr1.length; i++) {
            System.out.println("Nhập phần tử thứ "+(i+1)+" :");
            arr1[i] = sc.nextInt();
        }
        System.out.println("Mảng 1 bạn vừa nhập là: "+ Arrays.toString(arr1));

        System.out.println("Nhập liệu mảng 2");
        for (int i = 0; i < arr2.length; i++) {
            System.out.println("Nhập phần tử thứ "+(i+1)+" :");
            arr2[i] = sc.nextInt();
        }
        System.out.println("Mảng 2 bạn vừa nhập là: "+ Arrays.toString(arr2));

        int[] arr3 = new int[n1 + n2];

        for (int i = 0; i < n1; i++) {
            arr3[i] = arr1[i];
        }

        for (int i = 0; i < n2; i++) {
            arr3[n1 + i] = arr2[i];
        }

        System.out.println("Mảng sau khi gộp là: " + Arrays.toString(arr3));
    }
}
