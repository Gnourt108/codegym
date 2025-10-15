package ss4_OOP.PhuongTrinhBac2;

public class PhuongTrinhBac2 {
    private double a;
    private double b;
    private double c;

    public PhuongTrinhBac2(double a, double b, double c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public double getA() {
        return a;
    }

    public void setA(double a) {
        this.a = a;
    }

    public double getB() {
        return b;
    }

    public void setB(double b) {
        this.b = b;
    }

    public double getC() {
        return c;
    }

    public void setC(double c) {
        this.c = c;
    }

    //Tính delta
    public double delta(){
        return b*b - 4*a*c;
    }

    //Tính nghiệm 1
    public double nghiem1(){
        double delta = delta();
        if(delta < 0){
            return 0;
        }
        return (-b + Math.sqrt(delta)) / (2*a);
    }

    //Tính nghiệm 2
    public double nghiem2(){
        double delta = delta();
        if(delta < 0){
            return 0;
        }
        return (-b - Math.sqrt(delta)) / (2*a);
    }
}
