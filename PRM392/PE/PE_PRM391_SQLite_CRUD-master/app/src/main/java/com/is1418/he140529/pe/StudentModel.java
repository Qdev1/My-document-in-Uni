package com.is1418.he140529.pe;

public class StudentModel {
    private int id;
    private String fullName;
    private String dob;
    private float averageScore;

    public StudentModel(int id, String fullName, String dob, float averageScore) {
        this.id = id;
        this.fullName = fullName;
        this.dob = dob;
        this.averageScore = averageScore;
    }

    public StudentModel() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public float getAverageScore() {
        return averageScore;
    }

    public void setAverageScore(float averageScore) {
        this.averageScore = averageScore;
    }

    @Override
    public String toString() {
        return "StudentModel{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", dob='" + dob + '\'' +
                ", averageScore=" + averageScore +
                '}';
    }
}
