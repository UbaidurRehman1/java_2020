package com.ubaid.ds;

import java.util.*;
import java.util.function.Consumer;

public class DataStructureDemo {

    public static void main(String [] args) {
        DataStructureDemo ds = new DataStructureDemo();
        ds.app();
    }

    private void app() {
        Deque<String> queue = new ArrayDeque<>();


    }

    private static class Filter  implements Consumer<Integer> {
        static void filter(Integer i) {
            if (i == 1) {
                System.out.println(i);
            }
        }

        @Override
        public void accept(Integer o) {
            if (o == 1)
                System.out.println(o);
        }
    }
}
