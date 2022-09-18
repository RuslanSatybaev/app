package com.edu.ulab.app.storage;

import java.util.HashMap;
import java.util.Map;

public class TestStorage {
    public static void main(String[] args) {
        Map<Integer, String> testMap = new HashMap<>();
        testMap.put(1, "ruslan");
        testMap.put(2, "alfia");
        testMap.put(3, "yasina");

        testMap.remove(1);

        System.out.println(testMap);

    }
}
