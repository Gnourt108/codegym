package ss5_access_modifier.LopChiGhiTrongJava;

public class Student {
    private String name = "John";
    private String classes = "C02";

    public Student() {
    }

    public void setClasses(String classes) {
        this.classes = classes;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void displayInfo(){
        System.out.println("Name: "+name);
        System.out.println("Class: "+classes);
    }
}
