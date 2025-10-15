package ss4_OOP.Fan;

public class Main {
    public static void main(String[] args) {
        Fan fan1 = new Fan();
        fan1.setSpeed(Fan.FAST);
        fan1.setRadius(10);
        fan1.setColor("yellow");
        fan1.setOn(true);

        Fan fan2 = new Fan();
        fan2.setSpeed(Fan.MEDIUM);
        fan2.setRadius(5);
        fan2.setColor("green");
        fan2.setOn(false);

        Fan fan3 = new Fan();
        fan3.setSpeed(Fan.SLOW);
        fan3.setRadius(3);
        fan3.setOn(true);

        System.out.println("Fan 1: "+fan1.toString());
        System.out.println("Fan 2: "+fan2.toString());
        System.out.println("Fan 3: "+fan3.toString());
    }
}
