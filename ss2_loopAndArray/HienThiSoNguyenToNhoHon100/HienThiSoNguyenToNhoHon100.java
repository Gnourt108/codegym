package ss2_loopAndArray.HienThiSoNguyenToNhoHon100;

import java.util.Scanner;

public class HienThiSoNguyenToNhoHon100 {
    public static void main(String[] args) {
        int n = 2;
        String soNguyenTo = "";
        while(n < 100){
            if(kiemTraSoNguyenTo(n)){
                soNguyenTo += n + " ";
            }
            n++;
        }
        System.out.println("Các số nguyên tố nhỏ hơn 100 là: ");
        System.out.println(soNguyenTo);
    }
    private static boolean kiemTraSoNguyenTo(int number){
        if(number == 2) return true;
        for (int i = 2; i <= Math.sqrt(number) ; i++) {
            if(number % i == 0) return false;
        }
        return true;
    }
}
