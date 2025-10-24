package ss7_abstractClass_interface.resizeable;

import java.util.Random;

public class Test {
    public static void main(String[] args) {
        Random rand = new Random();

        Resizeable[] shapes = new Resizeable[3];
        shapes[0] = new Circle(5);
        shapes[1] = new Rectangle(4,6);
        shapes[2] = new Square(5);

        for (Resizeable shape : shapes){
            double percent = rand.nextInt(100) + 1; // từ 1 đến 100
            double oldArea;
            if(shape instanceof Circle){
                Circle c = (Circle) shape;
                oldArea = c.getArea();
                c.resize(percent);
                System.out.printf("%s tăng %.0f%% -> Diện tích: %.2f -> %.2f%n", c, percent, oldArea, c.getArea());
            }else if(shape instanceof Rectangle && !(shape instanceof Square)){
                Rectangle r = (Rectangle) shape;
                oldArea = r.getArea();
                r.resize(percent);
                System.out.printf("%s tăng %.0f%% -> Diện tích: %.2f -> %.2f%n", r, percent, oldArea, r.getArea());
            } else if (shape instanceof Square) {
                Square s = (Square) shape;
                oldArea = s.getArea();
                s.resize(percent);
                System.out.printf("%s tăng %.0f%% -> Diện tích: %.2f -> %.2f%n", s, percent, oldArea, s.getArea());

            }
        }
    }
}
