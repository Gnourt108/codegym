package ss10_DSA_StudentCRUD.common;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class CheckInput {
    private static final Scanner sc = new Scanner(System.in);
    private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static int inputInt(String message){
        while (true){
            try {
                System.out.println(message);
                return Integer.parseInt(sc.nextLine());
            }catch (NumberFormatException e){
                System.out.println("Vui lòng nhập số nguyên hợp lệ");
            }
        }
    }

    public static double inputDouble(String message){
        while (true){
            try {
                System.out.println(message);
                return Double.parseDouble(sc.nextLine());
            }catch (NumberFormatException e){
                System.out.println("Vui lòng nhập số thực hợp lệ");
            }
        }
    }

    public static LocalDate inputDate(String message) {
        while (true) {
            System.out.println(message+" (dd/MM/yyyy): ");
            String input = sc.nextLine().trim();
            try {
                return LocalDate.parse(input, dateFormatter);
            } catch (DateTimeException e) {
                System.out.println("Định dạng ngày tháng không hợp lệ! Vui lòng nhập theo dạng dd/MM/yyyy (ví dụ: 25/12/2003).");
            }
        }
    }

    public static String inputStr(String message){
                System.out.println(message);
                return sc.nextLine();
    }

    public static String inputStringAllowEmpty(String prompt) {
        Scanner sc = new Scanner(System.in);
        System.out.print(prompt);
        return sc.nextLine().trim();
    }

    public static boolean confirmAction(String message) {
        System.out.print(message + "(y/n): ");
        String choice = sc.nextLine().trim().toLowerCase();
        return choice.equals("y") || choice.equals("yes");
    }

}
