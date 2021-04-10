package me.gicheol.lambdastream.stream;

import java.util.Comparator;
import java.util.stream.Stream;

public class StreamOperator {

    public static void main(String[] args) {
        Stream<Student> studentStream = Stream.of(
                new Student("가나다", 3, 300),
                new Student("라마바", 1, 200),
                new Student("사아자", 2, 100),
                new Student("차카타", 3, 150),
                new Student("파하A", 1, 200),
                new Student("BCD", 2, 300),
                new Student("EFG", 1, 290),
                new Student("HIJ", 3, 180)
        );

        studentStream.sorted(Comparator.comparing(Student::getBan).reversed()
            .thenComparing(Comparator.naturalOrder()).reversed())
            .forEach(System.out::println);
    }

}

class Student implements Comparable<Student> {

    Student(String name, int ban, int totalScore) {
        this.name = name;
        this.ban = ban;
        this.totalScore = totalScore;
    }

    String name;
    int ban;
    int totalScore;

    @Override
    public int compareTo(Student s) {
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


