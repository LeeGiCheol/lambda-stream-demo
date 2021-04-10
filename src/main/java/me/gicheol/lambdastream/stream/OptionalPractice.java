package me.gicheol.lambdastream.stream;

/*
    * Optional
    1. null을 직접 다루는 것은 위험하기 때문에 Optional을 통해 간접적으로 다룬다.
    2. 기존 방식은 if문을 사용해 null 체크를 하게 되어 코드가 지저분해진다.
    3. Optional 객체에 값을 저장하고, 사용 시 Optional 객체를 호출 하기 때문에 null을 호출하게 될 일이 없다.
 */

import java.util.Optional;
import java.util.OptionalInt;

public class OptionalPractice {

    public static void main(String[] args) {
        optionalPractice();

        optionalPractice2();
    }

    private static void optionalPractice2() {
        Optional<String> optStr = Optional.of("abcde");
        Optional<Integer> optInt = optStr.map(String::length);
        System.out.println("optStr = " + optStr.get());
        System.out.println("optInt = " + optInt.get());

        int result1 = Optional.of("123")
                .filter(x -> x.length() > 0)
                .map(Integer::parseInt).get();

        int result2 = Optional.of("")
                .filter(x -> x.length() > 0)
                .map(Integer::parseInt).orElse(-1);

        System.out.println("result1 = " + result1);
        System.out.println("result2 = " + result2);

        Optional.of("456").map(Integer::parseInt)
                .ifPresent(x -> System.out.printf("result3 = %d%n", x));

        OptionalInt optInt1 = OptionalInt.of(0);
        OptionalInt optInt2 = OptionalInt.empty();

        System.out.println("optInt1 = " + optInt1.isPresent());
        System.out.println("optInt2 = " + optInt2.isPresent());

        System.out.println("optInt1.getAsInt() = " + optInt1.getAsInt());
//        System.out.println("optInt2.getAsInt() = " + optInt2.getAsInt());
        System.out.println("optInt1.equals(optInt2) = " + optInt1.equals(optInt2));
    }

    private static void optionalPractice() {
        String str = "";
        int[] arr = new int[0];
        System.out.println("arr.length = " + arr.length);

        Optional<String> opt = Optional.empty();
        System.out.println("opt = " + opt);

        str = opt.orElse("Empty");
        System.out.println("str = " + str);

        str = opt.orElseGet(() -> "EMPTY");
        System.out.println("str = " + str);

        // Optional.of(0); Optional.empty(); 둘을 구분하기 위해 isPresent()를 사용
        OptionalInt optInt1 = OptionalInt.of(0);
        OptionalInt optInt2 = OptionalInt.empty();

        System.out.println("optInt1.isPresent() = " + optInt1.isPresent());
        System.out.println("optInt2.isPresent() = " + optInt2.isPresent());
    }

}
