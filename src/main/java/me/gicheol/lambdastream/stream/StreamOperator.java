package me.gicheol.lambdastream.stream;

import java.util.Comparator;
import java.util.stream.Stream;

public class StreamOperator {

    public static void main(String[] args) {
        Stream<StudentStreamOperator> studentStream = Stream.of(
                new StudentStreamOperator("가나다", 3, 300),
                new StudentStreamOperator("라마바", 1, 200),
                new StudentStreamOperator("사아자", 2, 100),
                new StudentStreamOperator("차카타", 3, 150),
                new StudentStreamOperator("파하A", 1, 200),
                new StudentStreamOperator("BCD", 2, 300),
                new StudentStreamOperator("EFG", 1, 290),
                new StudentStreamOperator("HIJ", 3, 180)
        );

        studentStream.sorted(Comparator.comparing(StudentStreamOperator::getBan).reversed()
            .thenComparing(Comparator.naturalOrder()).reversed())
            .forEach(System.out::println);
    }

}

