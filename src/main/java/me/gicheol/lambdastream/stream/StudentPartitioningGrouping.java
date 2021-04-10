package me.gicheol.lambdastream.stream;

public class StudentPartitioningGrouping {
    String name;

    boolean isMale;
    int grade;
    int ban;
    int score;

    public StudentPartitioningGrouping(String name, boolean isMale, int grade, int ban, int score) {
        this.name = name;
        this.isMale = isMale;
        this.grade = grade;
        this.ban = ban;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public boolean isMale() {
        return isMale;
    }

    public int getGrade() {
        return grade;
    }

    public int getBan() {
        return ban;
    }

    public int getScore() {
        return score;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", isMale=" + isMale +
                ", grade=" + grade +
                ", ban=" + ban +
                ", score=" + score +
                '}';
    }

    enum Level { HIGH, MID, LOW }

}