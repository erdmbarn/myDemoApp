package com.mycompany.app;
import org.junit.jupiter.api.Test;


import java.util.ArrayList;
import java.util.Arrays;

import static com.mycompany.app.App.processLists;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

/**
 * Unit test for simple App.
 */
public class AppTest {
    @Test
    void testWithCommonElementsWithinRange() {
        ArrayList<Integer> list1 = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        ArrayList<Integer> list2 = new ArrayList<>(Arrays.asList(3, 4, 5, 6, 7));
        assertArrayEquals(new int[]{12, 3}, processLists(list1, list2, 1, 5));
    }

    @Test
    void testWithCommonElementsOutsideRange() {
        ArrayList<Integer> list1 = new ArrayList<>(Arrays.asList(10, 20, 30));
        ArrayList<Integer> list2 = new ArrayList<>(Arrays.asList(20, 30, 40, 50));
        assertArrayEquals(new int[]{50, 2},processLists(list1, list2, 15, 35));
    }

    @Test
    void testWithNoCommonElements() {
        ArrayList<Integer> list1 = new ArrayList<>(Arrays.asList(1, 2, 3));
        ArrayList<Integer> list2 = new ArrayList<>(Arrays.asList(4, 5, 6));
        assertArrayEquals(new int[]{0, 0}, processLists(list1, list2, 1, 6));
    }

    @Test
    void testWithBothListsEmpty() {
        ArrayList<Integer> list1 = new ArrayList<>();
        ArrayList<Integer> list2 = new ArrayList<>();
        assertArrayEquals(new int[]{0, 0}, processLists(list1, list2, 1, 10));
    }

    @Test
    void testWithOneListEmpty() {
        ArrayList<Integer> list1 = new ArrayList<>();
        ArrayList<Integer> list2 = new ArrayList<>(Arrays.asList(1, 2, 3));
        assertArrayEquals(new int[]{0, 0}, processLists(list1, list2, 1, 3));
    }

    @Test
    void testWithMinMaxOutOfRange() {
        ArrayList<Integer> list1 = new ArrayList<>(Arrays.asList(10, 20, 30, 40, 50));
        ArrayList<Integer> list2 = new ArrayList<>(Arrays.asList(15, 25, 35, 45, 55));
        assertArrayEquals(new int[]{0, 0}, processLists(list1, list2, 60, 70));
    }
}