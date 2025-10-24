package ss7_abstractClass_interface.colorable;

public class Test {
    public static void main(String[] args) {
        Shape[] shapes = new Shape[3];
        shapes[0] = new Circle(3);
        shapes[1] = new Rectangle(3, 4);
        shapes[2] = new Square(3);

        for (Shape shape : shapes){
            System.out.printf("%s có diện tích %.2f%n", shape, shape.getArea());

            if(shape instanceof Colorable){
                ((Colorable) shape).howToColor();
            }
            System.out.println("----------------------------");
        }
    }
}
