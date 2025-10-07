package introduction_to_java.ExchangeCurrency;

import java.util.Scanner;

public class ExchangeCurrency {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        final double rate = 23000;
        double usd;
        do {
            System.out.println("Vui lòng nhập số tiền (USD)");
            usd= sc.nextDouble();
            if(usd < 0){
                System.out.println("Số tiền phải lớn hơn 0");
            }
        }while(usd<0);

        double vnd = usd * rate;

        System.out.printf("%.2f USD = %.2f VNĐ\n", usd, vnd);
    }
}
