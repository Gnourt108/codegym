package ss11_DSA_queue_stack.decimalToBinary;

import common.InputHelper;

import java.util.Scanner;
import java.util.Stack;

public class DecimalToBinary {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int number = InputHelper.inputInt("Nhập số thập phân: ");

        Stack<Integer> stack = new Stack<>();

        while (number > 0) {
            int remainder = number % 2;
            stack.push(remainder);
            number = number / 2;
        }

        System.out.println("Số nhị phân tương ứng là: ");
        while (!stack.isEmpty()){
            System.out.print(stack.pop());
        }
    }
}
