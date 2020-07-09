package com.ubaid.mapp;

import java.util.HashMap;
import java.util.Map;

public class MapDemo {
    public static void main(String [] args) {
        Map<Integer, Long> map = new HashMap<>();
        map.put(1, 100L);
        map.put(2, 200L);
        map.put(20, null);
        Long val = map.put(3, 300L);
        System.out.println(val);

        System.out.println(map.put(2, 2000L));
        System.out.println(map.put(20, 2000L));

    }
}
