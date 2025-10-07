package introduction_to_java.ung_dung_doc_so_thanh_chu;

import java.util.Scanner;

public class ungDungDocSoThanhChu {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Nhập một số (0-999)");
        int number = sc.nextInt();
        if(number < 0 || number > 999){
            System.out.println("Out of ability");
        }else{
            System.out.println(convertWordToNNumber(number));
        }
    }

    private static String convertWordToNNumber(int number) {
        if(number == 0){
            return "zero";
        }
        String words = "";
        int hundreds = number / 100;
        int remainer = number % 100;

        //Đọc hàng trăm
        if(hundreds > 0){
            words += readOneNumber(hundreds) + " hundreds";
            if(remainer > 0){
                words +=  " and";
            }
        }
        if(remainer > 0){
            if(remainer < 10){
                words += readOneNumber(remainer);
            }else if (remainer < 20){
                words += readTwoNumber(remainer);
            }else{
                int tens = remainer/10;
                int ones = remainer%10;
                words += " " + readTensWord(tens);
                if(ones > 0){
                    words += " "+ readOneNumber(ones);
                }
            }
        }
        return  words;
    }

    private static String readOneNumber(int num) {
        switch (num) {
            case 0: return "zero";
            case 1: return "one";
            case 2: return "two";
            case 3: return "three";
            case 4: return "four";
            case 5: return "five";
            case 6: return "six";
            case 7: return "seven";
            case 8: return "eight";
            case 9: return "nine";
            default: return "";
        }
    }

    private static String readTwoNumber(int num) {
        switch (num){
            case 10: return "ten";
            case 11: return "eleven";
            case 12: return "twelve";
            case 13: return "thirteen";
            case 14: return "fourteen";
            case 15: return "fifteen";
            case 16: return "sixteen";
            case 17: return "seventeen";
            case 18: return "eighteen";
            case 19: return "nineteen";
            default: return "";
        }
    }

    private static String readTensWord(int num) {
        switch (num) {
            case 2: return "twenty";
            case 3: return "thirty";
            case 4: return "forty";
            case 5: return "fifty";
            case 6: return "sixty";
            case 7: return "seventy";
            case 8: return "eighty";
            case 9: return "ninety";
            default: return "";
        }
    }
}
