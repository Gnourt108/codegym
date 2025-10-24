package ss7_abstractClass_interface.resizeable;

public class Rectangle implements Resizeable{

    private double height;
    private double width;

    public Rectangle(double height, double width) {
        this.height = height;
        this.width = width;
    }

    public double getArea(){
        return height * width;
    }

    @Override
    public void resize(double percent) {
        height += height * percent/100;
        width += width * percent/100;
    }

    @Override
    public String toString() {
        return String.format("Rectangle has width = %.2f and height = %.2f", width, height);
    }
}
