package me.gicheol.lambdastream.lambda;

import java.util.function.Function;
import java.util.function.Supplier;

/*
    클래스 이름 :: 메서드 이름

    Integer method(String s) {
        return Integer.parseInt(s);
    }

    ->

    Function<String, Integer> f = (String s) -> Integer.parseInt(s);

    이미 입력, 반환 타입을 알고 있다.
    아래와 같이 바꿀 수 있다.

    Function<String, Integer> f = Integer::parseInt;

 */
public class MethodReference {

    public static void main(String[] args) {
        Function<String, Integer> f = (String s) -> Integer.parseInt(s);
        Function<String, Integer> f2 = Integer::parseInt;

        // 위 두 코드는 같다.

        System.out.println(f2.apply("100") + 200);


        // Supplier는 입력은 없고, 출력은 있고
        Supplier<MyClass> s = () -> new MyClass();
        System.out.println(s.get());

        Supplier<MyClass> s2 = MyClass::new;
        System.out.println(s2.get());


        Function<Integer, int[]> fun = (i) -> new int[i];
        System.out.println(fun.apply(100).length);

    }

}

class MyClass {}