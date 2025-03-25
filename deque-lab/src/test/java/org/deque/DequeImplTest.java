package org.deque;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class DequeImplTest {
    private DequeImpl<Integer> deque;

    @BeforeEach
    void setUp() {
        deque = new DequeImpl<>();
    }

    @Test
    void testAddFirst() {
        deque.addFirst(10);
        deque.addFirst(20);

        assertEquals(20, deque.getFirst());
        assertEquals(10, deque.getLast());
    }

    @Test
    void testAddLast() {
        deque.addLast(10);
        deque.addLast(20);

        assertEquals(10, deque.getFirst());
        assertEquals(20, deque.getLast());
    }

    @Test
    void testRemoveFirst() {
        deque.addLast(10);
        deque.addLast(20);

        assertEquals(10, deque.removeFirst());
        assertEquals(20, deque.getFirst());
    }

    @Test
    void testRemoveLast() {
        deque.addLast(10);
        deque.addLast(20);

        assertEquals(20, deque.removeLast());
        assertEquals(10, deque.getFirst());
    }

    @Test
    void testRemoveFirstOnEmptyDeque() {
        assertThrows(IllegalStateException.class, () -> deque.removeFirst());
    }

    @Test
    void testRemoveLastOnEmptyDeque() {
        assertThrows(IllegalStateException.class, () -> deque.removeLast());
    }

    @Test
    void testContains() {
        deque.addLast(10);
        deque.addLast(20);

        assertTrue(deque.contains(10));
        assertFalse(deque.contains(30));
    }

    @Test
    void testRemove() {
        deque.addLast(10);
        deque.addLast(20);

        assertTrue(deque.remove(10));
        assertFalse(deque.contains(10));
    }

    @Test
    void testRemoveOnEmptyDeque() {
        assertFalse(deque.remove(10));
    }

    @Test
    void testRemoveNonExistentElement() {
        deque.addLast(10);
        deque.addLast(20);

        assertFalse(deque.remove(30));
    }

    @Test
    void testAddAll() {
        List<Integer> list = List.of(10, 20, 30);
        assertTrue(deque.addAll(list));
        assertEquals(10, deque.getFirst());
        assertEquals(30, deque.getLast());
    }

    @Test
    void testSize() {
        assertEquals(0, deque.size());
        deque.addLast(10);
        deque.addLast(20);

        assertEquals(2, deque.size());
    }

}
