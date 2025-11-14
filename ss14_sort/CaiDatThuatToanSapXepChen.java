package ss14_sort;

public class CaiDatThuatToanSapXepChen {
    public static void insertionSort(int[] list){
        int pos, x;

        for (int i = 1; i < list.length ; i++) {
            x = list[i];
            pos = i;

            while(pos > 0 && x < list[pos - 1]){
                list[pos] = list[pos - 1];
                pos--;
            }

            list[pos] = x;
        }
    }

    public static void printArray(int[] list){
        for (int i = 0; i < list.length; i++) {
            System.out.print(list[i]+" ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] list = {5, 2, 4, 6, 1, 3};

        System.out.println("Mảng ban đầu: ");
        printArray(list);
        insertionSort(list);
        System.out.println("Mảng sau khi sắp xếp: ");
        printArray(list);
    }
}
