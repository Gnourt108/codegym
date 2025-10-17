package ss5_access_modifier.access_modifier;

public class TestCircle {
    public static void main(String[] args) {
        Circle c1 = new Circle();
        Circle c2 = new Circle(2.5);

        System.out.println("Radius c1: "+c1.getRadius());
        System.out.println("Area c1: "+c1.getArea());

        System.out.println("Radius c2: "+c2.getRadius());
        System.out.println("Area c2 : "+c2.getArea());
    }
}
