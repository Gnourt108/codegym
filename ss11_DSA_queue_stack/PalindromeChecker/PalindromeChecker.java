package ss11_DSA_queue_stack.PalindromeChecker;

import common.InputHelper;

import java.util.*;

public class PalindromeChecker {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String text = InputHelper.inputString("Nhập chuỗi cần kiểm tra");
        String cleaned = text.toLowerCase().replaceAll("[^a-z0-9]", "");
        Stack<Character> stack = new Stack<>();
        Queue<Character> queue = new LinkedList<>();

        for(char c : cleaned.toCharArray()){
            stack.push(c);
            queue.add(c);
        }

        boolean isPalindrome = true;
        while (!stack.isEmpty()){
            if(stack.pop() != queue.remove()){
                isPalindrome = false;
                break;
            }
        }

        if(isPalindrome){
            System.out.println("Chuối vừa nhập là Palidrome");
        }else{
            System.out.println("Chuối vừa nhập không là Palidrome");
        }
    }
}
