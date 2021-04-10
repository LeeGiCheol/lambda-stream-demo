package me.gicheol.lambdastream.stream;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/*

    Stream
    다양한 데이터 소스를 표준화된 방법으로 다루기 위한 것
    (List, Set, Map, Array ...)

    1. 스트림 만들기
    2. 중간 연산 (n번)
    3. 최종 연산 (1번)
    4. 결과

    * 특징
    1. Stream은 데이터 소스로부터 데이터를 읽기만 할 뿐 변경하지 않는다.
    2. Iterator처럼 일회용이다. 필요하면 다시 스트림을 생성해야한다.
    최종 연산 후 스트림이 닫히기 때문
    3. 최종 연산 전까지 중간 연산이 수행되지 않는다. - 지연된 연산
    4. 작업을 내부 반복으로 처리한다.
    5. parallel 메서드를 사용하면 병렬 스트림, sequential 메서드는 기본
    6. 오토박싱 언박싱의 비효율을 제거하기 위해 Stream<Integer> 대신 IntStream을 쓰는 것이 효율적이다 (기본형)
    7. IntStream은 타입이 Integer인 것을 알기 때문에 Stream<T> 보다 숫자 관련 메서드가 많이 내장되어있다.

 */
public class StreamMake {

    public static void main(String[] args) {
        listStream();

        stringStream();

        intArrStream();

        integerStream();

        infiniteStream();

        iterateGenerate();

    }


    private static void listStream() {
        List<Integer> list = List.of(1, 2, 3, 4, 5);

        // list를 데이터 소스로 하는 새로운 스트림 생성
        Stream<Integer> intStream = list.stream();
        // 최종연산. 이 과정에서 스트림이 닫힌다.
        intStream.forEach(System.out::println);

        // 다시 사용하기 위해선 스트림을 하나 더 만든다.
        intStream = list.stream();
        intStream.forEach(System.out::println);
    }

    private static void stringStream() {
        String[] strArr = {"a", "b", "c"};
        Stream<String> strStream = Arrays.stream(strArr);
        strStream.forEach(System.out::println);
    }


    private static void intArrStream() {
        int[] intArr = {1, 2, 3, 4, 5};
        IntStream intStream = Arrays.stream(intArr);
        System.out.println("intStream.max() = " + intStream.max());
//        intStream.forEach(System.out::println);
    }

    private static void integerStream() {
        Integer[] intArr = {1, 2, 3, 4, 5};
        Stream<Integer> intStream = Arrays.stream(intArr);
        intStream.forEach(System.out::println);
    }

    private static void infiniteStream() {
        IntStream intStream = new Random().ints();
        intStream.limit(5).forEach(System.out::println); // limit을 하지 않으면 무한 스트림이 된다.
    }

    private static void iterateGenerate() {
        Stream<Integer> intStream = Stream.iterate(1, n -> n + 2);
        intStream.limit(10).forEach(System.out::println);

        Stream<Integer> oneStream = Stream.generate(() -> 1);
        oneStream
                .limit(10)
                .forEach(System.out::println);
    }

}
