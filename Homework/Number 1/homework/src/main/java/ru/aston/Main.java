package ru.aston;

import ru.aston.model.MyArrayList;
import ru.aston.util.QuickSort;

public class Main {
    public static void main(String[] args) {
        MyArrayList<Integer> list = new MyArrayList<>();
        for(Integer i = 5; i > 0; i--) {
            list.add(i);
        }
        list.add(0, (Integer) 0);
        System.out.println("List without sort: " + list);
        list.sort();
        System.out.println("List with base sort: " + list + "\n");

        MyArrayList<Integer> listQuickSort = new MyArrayList<>();
        for(Integer i = 5; i > 0; i--) {
            listQuickSort.add(i);
        }
        QuickSort.quicksort(listQuickSort);
        System.out.println("List with Quick sort: " + listQuickSort);
    }
}