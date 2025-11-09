package ss11_DSA_queue_stack.reserveUsingStack;

import common.InputHelper;

import java.util.Stack;

public class ReserveStringUsingStack {
    public static void main(String[] args) {
        String sentence = InputHelper.inputString("Nhập chuỗi cần đảo ngược: ");
        String[] words = sentence.split(" ");
        Stack<String> wordsStack = new Stack<>();
        for (String word : words){
            wordsStack.push(word);
        }

        StringBuilder reserved = new StringBuilder();
        while (!wordsStack.isEmpty()){
            reserved.append(wordsStack.pop());
            if(!wordsStack.isEmpty())
                reserved.append(" ");
        }

        System.out.println("Chuỗi sau khi đảo ngược: "+reserved.toString());
    }
}
