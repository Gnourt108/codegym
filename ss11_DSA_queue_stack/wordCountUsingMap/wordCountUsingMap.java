package ss11_DSA_queue_stack.wordCountUsingMap;

import common.InputHelper;

import java.util.Map;
import java.util.TreeMap;

public class wordCountUsingMap {
    public static void main(String[] args) {
        String text = InputHelper.inputString("Nhập một đoạn văn bản: ");

        text = text.toLowerCase()
                .replaceAll("[^a-zàáạảãâầấậẩẫăằắặẳẵèéẹẻẽêềếệểễ"
                        + "ìíịỉĩòóọỏõôồốộổỗơờớợởỡùúụủũưừứựửữ"
                        + "ỳýỵỷỹđ\\s]", "");
        String[] words = text.trim().split("\\s+");

        TreeMap<String, Integer> wordMap = new TreeMap<>();

        for (String word : words){
            if(word.isEmpty()){
                continue;
            }
            wordMap.put(word, wordMap.getOrDefault(word, 0)+1);
        }

        System.out.println("Số từ trong đoạn văn bạn vừa nhập là: ");
        for (Map.Entry<String, Integer> entry : wordMap.entrySet()){
            System.out.println(entry.getKey() + " : "+ entry.getValue());
        }
    }
}
