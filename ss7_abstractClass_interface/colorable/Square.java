package ss7_abstractClass_interface.colorable;

public class Square extends Rectangle implements Colorable{


    public Square(double side) {
        super(side, side);
    }


    @Override
    public void howToColor() {
        System.out.println("Màu sắc ở 4 phía");
    }

    @Override
    public String toString() {
        return String.format("Square có side = %.2f", getWidth());
    }
}
