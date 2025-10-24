package ss7_abstractClass_interface.colorable;

public class Rectangle extends Shape{

    private double height, width;

    public Rectangle(double height, double width) {
        this.height = height;
        this.width = width;
    }

    @Override
    public double getArea() {
        return height * width;
    }

    public double getWidth() {
        return width;
    }

    @Override
    public String toString() {
        return String.format("Rectangle có width = %.2f, height = %.2f", width, height);
    }
}
