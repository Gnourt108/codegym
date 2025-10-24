package ss7_abstractClass_interface.resizeable;

public class Square extends Rectangle{

    private double side;

    public Square(double side) {
        super(side, side);
        this.side = side;
    }


    @Override
    public double getArea() {
        return side * side;
    }

    @Override
    public void resize(double percent) {
        side += side * percent/100;
        super.resize(percent); // Cập nhật width và height của lớp cha
    }

    @Override
    public String toString() {
        return String.format("Square has side = %.2f", side);
    }
}
