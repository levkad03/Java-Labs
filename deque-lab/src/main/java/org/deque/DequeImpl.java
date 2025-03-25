package org.deque;

import java.util.Collection;

public class DequeImpl<E> implements Deque<E> {

    private static class Node<E> {
        E data;
        Node<E> prev, next;

        Node(E data) {
            this.data = data;
        }
    }

    private Node<E> head, tail;
    private int size;

    @Override
    public void addFirst(E e) {
        Node<E> newNode = new Node<>(e);
        if (head == null) {
            head = tail = newNode;
        } else {
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
        }
        size++;
    }

    @Override
    public void addLast(E e) {
        Node<E> newNode = new Node<>(e);

        if (tail == null) {
            head = tail = newNode;
        } else {
            newNode.prev = tail;
            tail.next = newNode;
            tail = newNode;
        }
        size++;
    }

    @Override
    public E removeFirst() {
        if (head == null) {
            throw new IllegalStateException("Deque is empty");
        }
        E data = head.data;
        head = head.next;
        if (head == null) {
            tail = null;
        } else {
            head.prev = null;
        }
        size--;
        return data;
    }

    @Override
    public E removeLast() {
        if (tail == null) {
            throw new IllegalStateException("Deque is empty");
        }
        E data = tail.data;
        tail = tail.prev;

        if (tail == null) {
            head = null;
        } else {
            tail.next = null;
        }
        size--;
        return data;
    }

    @Override
    public E getFirst() {
        if (head == null) {
            throw new IllegalStateException("Deque is empty");
        }
        return head.data;
    }

    @Override
    public E getLast() {
        if (tail == null) {
            throw new IllegalStateException("Deque is empty");
        }
        return tail.data;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        boolean changed = false;
        for (E e : c) {
            addLast(e);
            changed = true;
        }
        return changed;
    }

    @Override
    public boolean remove(Object o) {
        if (head == null) {
            return false;
        }
        Node<E> current = head;
        while (current != null) {
            if (current.data.equals(o)) {
                if (current.prev != null) {
                    current.prev.next = current.next;
                } else {
                    head = current.next;
                }
                if (current.next != null) {
                    current.next.prev = current.prev;
                } else {
                    tail = current.prev;
                }
                size--;
                return true;
            }
            current = current.next;
        }
        return false;
    }

    @Override
    public boolean contains(Object o) {
        Node<E> current = head;
        while (current != null) {
            if (current.data.equals(o)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    @Override
    public int size() {
        return size;
    }
}
