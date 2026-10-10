package edu.cec.todo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "students")
public class Student extends User {
    @Column(length = 100)
    private String course;
    @Column(length = 30)
    private String yearLevel;

    protected Student() {}
    public Student(String name, String username, String password, String course, String yearLevel) {
        super(name, username, password);
        this.course = course; this.yearLevel = yearLevel;
    }
    public String getCourse() { return course; }
    public void setCourse(String course) { this.course = course; }
    public String getYearLevel() { return yearLevel; }
    public void setYearLevel(String yearLevel) { this.yearLevel = yearLevel; }
    @Override public String profileLabel() {
        return getName() + (course == null || course.isBlank() ? "" : " — " + course);
    }
}
