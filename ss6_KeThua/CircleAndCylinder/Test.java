package ss6_KeThua.CircleAndCylinder;

public class Test {
    public static void main(String[] args) {
        Circle c1 = new Circle(2.0, "Blue");
        System.out.println(c1);

        Cylinder c2 = new Cylinder(2.0, "yellow", 5.0);
        System.out.println(c2);

        //Thay đổi giá trị
        c2.setHeight(10);
        c2.setRadius(3.0);
        System.out.println("Cylinder updated: "+c2);

        //Thể tích
        System.out.println("Volumn of c2: "+c2.getVolumn());
    }
}
