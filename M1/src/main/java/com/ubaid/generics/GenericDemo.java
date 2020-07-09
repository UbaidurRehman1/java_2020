package com.ubaid.generics;

import java.util.*;
import java.util.stream.Stream;

public class GenericDemo {
    public static void main(String [] args) {
//        Container<String> store = new Store<>();
//        store.set("Its Ubaids Store");
//        System.out.println(store.get());
//
//        Bound<List<A>, A> b = new Bound<>();
//        b.foo(Arrays.asList("Hello", "Array"));
//        typeArgInference(22.0);
//        typeArgInference("Java");
//
//        String val1 = typeArgInference1("Java");
//
//        String val2 = typeArgInferenceFromTarget();

        List<Integer> list = new LinkedList<>();
        list.add(1);
        list.add(2);

//        displayData2(list);

        List<Integer> list1 = new LinkedList<>();

        Collections.addAll(list1, 15, 13, 14, 16);

//        System.out.println(list1);

        List<Number> dest = new ArrayList<>();
        Stream.of(1, 2, 3, 4, 5).forEach(dest::add);


        Collections.copy(dest, list1);

        System.out.println(dest);

    }


    public static void displayData(List<? extends Number> list) {
        for (Number number : list) {
            System.out.println("The Number is: " + number);
        }
    }

    public static <T extends  Number> void displayData2(List<T> list) {
        for (Number number : list) {
            System.out.println("The Number is: " + number);
        }
    }

    public static void dd2(List<? super Number> list) {
        list.add(2);
    }

    public static <T extends Number> void dd3(List<T> list) {
    }

    public static <T> void typeArgInference(T object) {
        System.out.println("Type Argument: " + object.getClass().getName());
    }

    public static <T> T typeArgInference1(T object) {
        System.out.println("Type Argument: " + object.getClass().getName());
        return object;
    }

    public static <T> void arrayToCollection(T[] a, Collection<T> c) {
        for (T val : a) {
            c.add(val);
        }
    }

    public static <T> T typeArgInferenceFromTarget() {
        return (T) "abc";
    }

}

interface Container<T> {
    void set(T t);
    T get();
}

class Store<T> implements Container<T> {

    private T t;

    @Override
    public void set(T t) {
        this.t = t;
    }

    @Override
    public T get() {
        return t;
    }
}

class Bound <T extends A> {
    public void foo(List<T> list) {
        System.out.println("The size of list is " + list.size());
    }
}

class A {

}

class SubClass extends A {

}

