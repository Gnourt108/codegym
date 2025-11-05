package ss10_DSA_StudentCRUD.entity;

import java.time.LocalDate;

public class Student extends Person {

    private Double score;

    public Student( ) {

    }

    public Student(Integer id, String name, LocalDate dob, Double score) {
        super(id, name, dob);
        this.score = score;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }


}
