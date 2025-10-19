package ss6_KeThua.Point2D_3D;

public class Test {
    public static void main(String[] args) {
        Point2D point2d = new Point2D(3.5f, 2.0f);
        System.out.println("Point 2D: "+point2d);

        Point3D point3D = new Point3D(1.0f, 2.0f, 3.0f);
        System.out.println("Point 3D: "+point3D);

        //Thay đổi giá trị
        point3D.setXYZ(5.5f, 6.5f, 7.5f);
        System.out.println("Point 3D updated: "+point3D);

        float[] coords = point3D.getXYZ();
        System.out.printf("Array = {%.1f, %.1f, %.1f}%n", coords[0], coords[1], coords[2]);
    }
}
