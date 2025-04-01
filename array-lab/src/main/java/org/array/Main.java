package org.array;

import java.util.Collection;

public class Main {
    public static void main(String[] args) {
        List<Integer> list = new ListImpl<>();
        list.add(1);
        list.add(2);
        list.add(3);
        System.out.println(list.size());
        list.remove(2);
        System.out.println(list.size());
        Collection<Integer> otherList = java.util.List.of(10, 20, 30);

        list.addAll(otherList);
        System.out.println(list.size());
    }
}