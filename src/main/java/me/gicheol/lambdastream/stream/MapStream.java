package me.gicheol.lambdastream.stream;

import java.util.HashMap;
import java.util.Map;

public class MapStream {
    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>();
        map.put("1", "1");
        map.put("2", "2");
        map.put("3", "3");
        map.put("4", "4");
        map.put("5", "5");

        map.forEach((k, v) -> System.out.println("{" + k + ", " + k + "}"));
    }
}
