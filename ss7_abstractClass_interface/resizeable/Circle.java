package ss7_abstractClass_interface.resizeable;

public class Circle implements Resizeable{

    private double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public double getArea(){
        return Math.PI * radius * radius;
    }

    @Override
    public void resize(double percent) {
        radius += radius * percent/100;
    }

    @Override
    public String toString() {
        return String.format("Circle has radius = %.2f", radius);
    }
}
