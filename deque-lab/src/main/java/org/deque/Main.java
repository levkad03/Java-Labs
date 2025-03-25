package org.deque;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        DequeImpl<Integer> deque = new DequeImpl<>();
        List<Integer> list = List.of(10, 20, 30);
        deque.addAll(list);
        System.out.println(deque.getFirst());
        System.out.println(deque.getLast());
        System.out.println(deque.size());

        deque.addFirst(40);
        deque.addLast(50);
        System.out.println(deque.getFirst());
        System.out.println(deque.getLast());
        System.out.println(deque.size());

        int first = deque.removeFirst();
        System.out.println(first);
        System.out.println(deque.size());

        int last = deque.removeLast();
        System.out.println(last);
        System.out.println(deque.size());

        System.out.println(deque.remove(300));
    }
}