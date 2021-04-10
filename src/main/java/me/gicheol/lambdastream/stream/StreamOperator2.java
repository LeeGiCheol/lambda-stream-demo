package me.gicheol.lambdastream.stream;

import java.io.File;
import java.util.Arrays;
import java.util.stream.Stream;

/*
    map()     : 변환
    peek()    : forEach() 비슷하나 스트림 요소를 소비하지 않고 볼 수 있다. (디버깅 할때 유용)
    flatmap() : 변환   스트림의 스트림 -> 스트림
 */
public class StreamOperator2 {

    public static void main(String[] args) {
        mapPeek();

        flatMapMethod();

    }

    private static void mapPeek() {
        File[] fileArr = { new File ("Ex1.java"), new File("Ex1.bak"),
                new File("Ex2.java"), new File("Ex1"), new File("Ex1.txt") };

        Stream<File> fileStream = Stream.of(fileArr);
        Stream<String> fileNameStream = fileStream.map(File::getName);
        fileNameStream.forEach(System.out::println);

        fileStream = Stream.of(fileArr);
        fileStream.map(File::getName)
                .filter(s -> s.contains("."))
                .map(s -> s.substring(s.indexOf(".") + 1))
                .map(String::toUpperCase)
                .peek(s -> System.out.printf("filename=%s%n", s))
                .distinct()
                .forEach(System.out::println);
    }

    private static void flatMapMethod() {
        Stream<String[]> strArrStrm = Stream.of(new String[]{"abc", "def", "ghi"},
                new String[]{"ABC", "DEF", "GHI"});

//        Stream<Stream<String>> streamStream = strArrStrm.map(Arrays::stream);
        Stream<String> strStrm = strArrStrm.flatMap(Arrays::stream);
        strStrm.map(String::toLowerCase)
                .distinct()
                .sorted()
                .forEach(System.out::println);

        System.out.println();

        String[] lineArr = {
                "Believe or not It is true",
                "Do or do not There is no try"
        };

        Stream<String> lineStream = Arrays.stream(lineArr);
        lineStream.flatMap(line -> Stream.of(line.split(" +")))
                .map(String::toLowerCase)
                .distinct()
                .sorted()
                .forEach(System.out::println);
    }

}
