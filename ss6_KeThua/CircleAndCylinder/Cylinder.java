package ss6_KeThua.CircleAndCylinder;

import ss5_access_modifier.access_modifier.Circle;

public class Cylinder extends Circle {
    private double height;

    public Cylinder(){
        super();
        this.height = 1.0;
    }

    public Cylinder(double height) {
        this.height = height;
    }

    public Cylinder(double radius, double height) {
        super(radius);
        this.height = height;
    }

    public Cylinder(double radius, String color, double height) {
        super(radius, color);
        this.height = height;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getVolumn(){
        return getArea() * height;
    }

    @Override
    public String toString() {
        return String.format("Cyclinder: radius : %.2f | height: %.2f | color: %s | volumn: %.2f", getRadius(), height, getColor(), getVolumn());
    }
}
