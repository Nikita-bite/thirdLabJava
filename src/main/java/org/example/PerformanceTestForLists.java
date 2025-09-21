package org.example;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class PerformanceTestForLists {

    public int iterations;

    public int iterationsCreationLists;

    public PerformanceTestForLists() {
        this.iterations = 1000000;
        this.iterationsCreationLists = 1000000;
    }

    public PerformanceTestForLists(int iterations, int iterationsCreationLists) {
        this.iterations = iterations;
        this.iterationsCreationLists = iterationsCreationLists;
    }

    private long testAdd(List<Integer> list) {
        long startTime = System.currentTimeMillis();

        for (int i = 0; i < iterations; i++) {
            list.add(i);
        }

        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;
        System.out.println("add(): " + duration + " мс");
        return duration;
    }

    private long testGet(List<Integer> list) {
        for (int i = 0; i < iterations; i++) {
            list.add(i);
        }

        long startTime = System.currentTimeMillis();

        for (int i = 0; i < iterations; i++) {
            list.get(i);
        }

        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;
        System.out.println("get(): " + duration + " мс");
        return duration;
    }

    private long testDeleteFirst(List<Integer> list) {
        for (int i = 0; i < iterations; i++) {
            list.add(i);
        }

        long startTime = System.currentTimeMillis();

//        for (int i = iterations - 1; i >= 0; i--) {
//            list.remove(i);
//        }

        for (int i = 0; i < iterations; i++) {
            list.remove(0);
        }

        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;
        System.out.println("delete() первого элемента по индексу: " + duration + " мс");
        return duration;
    }

    private long testDeleteLast(List<Integer> list) {
        for (int i = 0; i < iterations; i++) {
            list.add(i);
        }

        long startTime = System.currentTimeMillis();

        for (int i = iterations - 1; i >= 0; i--) {
            list.remove(i);
        }

        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;
        System.out.println("delete() последнего элемента по индексу: " + duration + " мс");
        return duration;
    }

    @SuppressWarnings("unchecked")
    private long testArrayListCreation(int size) {
        long startTime = System.currentTimeMillis();

        ArrayList<Integer>[] arrayOfArrayLists = new ArrayList[iterationsCreationLists];

        for (int j = 0; j < iterationsCreationLists; j++) {
            arrayOfArrayLists[j] = new ArrayList<>();
            for (int k = 0; k < size; k++) {
                arrayOfArrayLists[j].add(k);
            }
        }

        long endTime = System.currentTimeMillis();
        System.out.println("Тестирование инициализации ArrayList: " + (endTime - startTime));
        return endTime - startTime;
    }

    @SuppressWarnings("unchecked")
    private long testLinkedListCreation(int size) {
        long startTime = System.currentTimeMillis();

        LinkedList<Integer>[] arrayOfLinkedLists = new LinkedList[iterationsCreationLists];

        for (int j = 0; j < iterationsCreationLists; j++) {
            arrayOfLinkedLists[j] = new LinkedList<>();
            for (int k = 0; k < size; k++) {
                arrayOfLinkedLists[j].add(k);
            }
        }

        long endTime = System.currentTimeMillis();
        System.out.println("Тестирование инициализации LinkedList:" + (endTime - startTime));
        return endTime - startTime;
    }

    private void printResults(long arrayListAdd, long arrayListGet, long arrayListDeleteFirst, long arrayListDeleteLast, long arrayListTime,
                              long linkedListAdd, long linkedListGet, long linkedListDeleteFirst, long linkedListDeleteLast, long linkedListTime) {

        System.out.println("\nРезультаты: ");
        System.out.println("+------------------------------------------+------------------+------------------+------------------+");
        System.out.println("|                  Метод                   |   Количество     |  ArrayList (мс)  | LinkedList (мс)  |");
        System.out.println("+------------------------------------------+------------------+------------------+------------------+");
        System.out.printf("| %-40s | %-16d | %-16d | %-16d |\n", "add()", iterations, arrayListAdd, linkedListAdd);
        System.out.printf("| %-40s | %-16d | %-16d | %-16d |\n", "get()", iterations, arrayListGet, linkedListGet);
        System.out.printf("| %-40s | %-16d | %-16d | %-16d |\n", "delete() первого элемента по индексу", iterations, arrayListDeleteFirst, linkedListDeleteFirst);
        System.out.printf("| %-40s | %-16d | %-16d | %-16d |\n", "delete() последнего элемента по индексу", iterations, arrayListDeleteLast, linkedListDeleteLast);
        System.out.printf("| %-40s | %-16d | %-16d | %-16d |\n", "инициализация", iterationsCreationLists, arrayListTime, linkedListTime);
        System.out.println("+------------------------------------------+------------------+------------------+------------------+");

        System.out.println("\nВыводы: ");
        if (arrayListAdd < linkedListAdd) {
            System.out.println("add(): ArrayList быстрее");
        } else if (arrayListAdd > linkedListAdd) {
            System.out.println("add(): LinkedList быстрее");
        } else {
            System.out.println("add(): Скорость одинаковая");
        }

        if (arrayListGet < linkedListGet) {
            System.out.println("get(): ArrayList быстрее");
        } else if (arrayListGet > linkedListGet) {
            System.out.println("get(): LinkedList быстрее");
        } else {
            System.out.println("get(): Скорость одинаковая");
        }

        if (arrayListDeleteFirst < linkedListDeleteFirst) {
            System.out.println("delete() первого элемента по индексу: ArrayList быстрее");
        } else if (arrayListDeleteFirst > linkedListDeleteFirst) {
            System.out.println("delete() первого элемента по индексу: LinkedList быстрее");
        } else {
            System.out.println("delete() первого элемента по индексу: Скорость одинаковая");
        }

        if (arrayListDeleteLast < linkedListDeleteLast) {
            System.out.println("delete() последнего элемента по индексу: ArrayList быстрее");
        } else if (arrayListDeleteLast > linkedListDeleteLast) {
            System.out.println("delete() последнего элемента по индексу: LinkedList быстрее");
        } else {
            System.out.println("delete() последнего элемента по индексу: Скорость одинаковая");
        }

        if (arrayListTime < linkedListTime) {
            System.out.println("инициализация: ArrayList быстрее");
        } else if (arrayListTime > linkedListTime) {
            System.out.println("инициализация: LinkedList быстрее");
        } else {
            System.out.println("инициализация: Скорость одинаковая");
        }
    }

    public void PerformanceTest() {
        System.out.println("Сравнение производительности ArrayList и LinkedList");
        System.out.println("Количество итераций: " + iterations);
        System.out.println();

        List<Integer> arrayList = new ArrayList<>();
        System.out.println("ArrayList:");
        long arrayListAddTime = testAdd(arrayList);
        long arrayListGetTime = testGet(arrayList);
        long arrayListDeleteFirstTime = testDeleteFirst(arrayList);
        long arrayListDeleteLastTime = testDeleteLast(arrayList);

        List<Integer> linkedList = new LinkedList<>();
        System.out.println("LinkedList:");
        long linkedListAddTime = testAdd(linkedList);
        long linkedListGetTime = testGet(linkedList);
        long linkedListDeleteFirstTime = testDeleteFirst(linkedList);
        long linkedListDeleteLastTime = testDeleteLast(linkedList);

        int len = 1;
        System.out.println("Тест создания массивов списков");
        System.out.println("Количество списков: " + iterationsCreationLists);
        System.out.println("Размер каждого списка: " + len);
        System.out.println();

        long arrayListTime = testArrayListCreation(len);

        long linkedListTime = testLinkedListCreation(len);

        printResults(arrayListAddTime, arrayListGetTime, arrayListDeleteFirstTime, arrayListDeleteLastTime, arrayListTime,
                linkedListAddTime, linkedListGetTime, linkedListDeleteFirstTime, linkedListDeleteLastTime, linkedListTime);
    }
}
