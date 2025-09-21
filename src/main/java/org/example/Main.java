package org.example;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        PerformanceTestForLists test = new PerformanceTestForLists(100000, 10000000);
        test.PerformanceTest();
    }
}