package ss10_DSA_StudentCRUD.view;

import java.util.Scanner;

public class MainView {
    public void mainView() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Chào mừng bạn đã đến với chúng tôi");
        while (true) {
            System.out.println("1. Student");
            System.out.println("0. Exit");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    new StudentView().view();
                    break;
                case 0:
                    System.exit(0);
                default:
                    System.out.println("Vui lòng chọn đúng nền văn minh");
            }
        }
    }

}
