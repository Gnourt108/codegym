package ss13_search.DoPhucTapCuaThuatToan;

import common.InputHelper;

import java.util.Scanner;

public class DoPhucTapCuaThuatToan {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input = InputHelper.inputString("Nhập vào một chuỗi bất kì: ");
        int[] frequentChar = new int[255];
        for (int i = 0; i < input.length(); i++) {
            int ascii = (int) input.charAt(i);
            frequentChar[ascii] += 1;
        }

        int max = 0;
        char character = (char) 255;
        for (int j = 0; j < 255; j++) {
            if(frequentChar[j] > max){
                max = frequentChar[j];
                character = (char) j;
            }
        }

        System.out.println("Ký tự xuất hiện nhiều nhất là: '"+character+"' với tần suất "+max+" lần.");
    }
}
