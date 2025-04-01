package org.array;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

public class ListImplTest {
    private List<Integer> list;

    @BeforeEach
    void setUp() {
        list = new ListImpl<>();
    }

    @Test
    void testAdd() {
        list.add(1);
        assertEquals(1, list.size());
        assertTrue(list.contains(1));
    }

    @Test
    void testPut() {
        list.add(1);
        list.add(2);

        int oldValue = list.put(0, 3);
        assertEquals(1, oldValue);
        assertTrue(list.contains(3));
    }

    @Test
    void testRemoveByIndex() {
        list.add(1);
        list.add(2);

        int removedElement = list.remove(0);
        assertEquals(1, removedElement);
        assertEquals(1, list.size());
        assertFalse(list.contains(1));
    }

    @Test
    void testRemoveNonExistentIndex() {
        list.add(1);
        assertThrows(IndexOutOfBoundsException.class, () -> list.remove(1));
    }

    @Test
    void testRemoveByObject() {
        list.add(1);
        list.add(2);

        boolean removed = list.remove(Integer.valueOf(1));
        assertTrue(removed);
        assertEquals(1, list.size());
        assertFalse(list.contains(1));
    }

    @Test
    void testRemoveNonExistentElement() {
        list.add(1);
        list.add(2);

        boolean removed = list.remove(Integer.valueOf(3));
        assertFalse(removed);
        assertEquals(2, list.size());
    }

    @Test
    void testAddAll() {
        Collection<Integer> otherList = java.util.List.of(1, 2, 3);

        boolean modified = list.addAll(otherList);
        assertTrue(modified);
        assertEquals(3, list.size());
        assertTrue(list.contains(1));
    }

    @Test
    void testContains() {
        list.add(1);

        assertTrue(list.contains(1));
        assertFalse(list.contains(3));
    }

    @Test
    void testSize() {
        assertEquals(0, list.size());
        list.add(1);
        list.add(2);

        assertEquals(2, list.size());
    }

    @Test
    void testIndexOutOfBoundsException() {
        assertThrows(IndexOutOfBoundsException.class, () -> list.put(0, 1));
        assertThrows(IndexOutOfBoundsException.class, () -> list.remove(0));
    }
}
