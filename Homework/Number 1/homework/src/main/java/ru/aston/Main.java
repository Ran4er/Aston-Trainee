package ru.aston;

import ru.aston.MyCollections.MyArrayList;

public class Main {
    public static void main(String[] args) {
        MyArrayList<Integer> list = new MyArrayList<>();
        list.add(1);
        list.add(3);
        list.add(2);
        list.add(0, 0);
        System.out.println("List: " + list);
        list.sort();
        System.out.println("List: " + list + "\n");

        MyArrayList<Integer> listQuickSort = new MyArrayList<>();
        for(int i = 5; i > 0; i--) {
            list.add(i);
        }
        listQuickSort.quickSort();
    }
}