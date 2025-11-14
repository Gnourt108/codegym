package ss14_sort;

import common.InputHelper;

public class MinhHoaThuatToanSapXepChen {
    public static void insertionSort(int[] array){
        int pos, x;
        System.out.println("Mảng ban đầu: ");
        printArray(array);
        System.out.println("---------------------------");

        for (int i = 1; i < array.length; i++) {
            x = array[i];
            pos = i;

            System.out.println("Lần chèn thứ "+i+" : x = "+x);

            while(pos > 0 && x < array[pos - 1]){
                array[pos] = array[pos - 1];
                pos--;
                printArray(array);
            }
            array[pos] = x;
            System.out.println("Sau khi chèn x = "+x+" vào vị trí "+pos+": ");
            printArray(array);
            System.out.println("-------------------------");
        }

        System.out.println("Mảng sau khi săp xếp: ");
        printArray(array);
    }

    public static void printArray(int[] array){
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i]+" ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] array = {5,3,4,1,2};
        insertionSort(array);
    }
}
