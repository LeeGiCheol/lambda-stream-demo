package me.gicheol.lambdastream.stream;

public class StudentStreamOperator implements Comparable<StudentStreamOperator> {

    StudentStreamOperator(String name, int ban, int totalScore) {
        this.name = name;
        this.ban = ban;
        this.totalScore = totalScore;
    }

    String name;
    int ban;
    int totalScore;

    @Override
    public int compareTo(StudentStreamOperator s) {
        return s.totalScore - this.totalScore;
    }

    public String getName() {
        return name;
    }

    public int getBan() {
        return ban;
    }

    public int getTotalScore() {
        return totalScore;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", ban=" + ban +
                ", totalScore=" + totalScore +
                '}';
    }



}


