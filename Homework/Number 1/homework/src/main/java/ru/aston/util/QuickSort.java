package ru.aston.util;

import ru.aston.model.MyList;

/**
 * The class implements an algorithm for quick sorting of elements.
 * In this case, it works with the elements of the collection, where we must specify a comparator (sorting rules).
 * Thanks to this, the elements in the collection are sorted quite quickly.
 * @author Ru6ik
 */
public class QuickSort  {

    private QuickSort() {}

    /**
     * A way to sort a collection using the quick sort method
     * @param <T> the type of items in the collections
     * @param list an array with collectionable objects
     * @param comparator Comparator (rules) for sorting a collection
     */
    public static <T extends Comparable<? super T>> void quicksort(MyList<T> list) {

        if (list.isEmpty() || list.size() == 1) {
            return;
        } else if (list.size() == 2) {
            if (list.get(0).compareTo(list.get(1)) > 0) {
                swap(list, 0, 1);
            }
            return;
        } else {
            quicksort(list, 0, list.size() - 1);
        }
    }

    private static <T extends Comparable<? super T>> void quicksort(MyList<T> list, int from, int to) {

        int left = from;
        int right = to;

        T pivot = list.get(pivotSelect(list, from, to));

        while (left <= right) {
            while (list.get(left).compareTo(pivot) < 0) {
                left++;
            }
            while (list.get(right).compareTo(pivot) > 0) {
                right--;
            }
            if (left <= right) {
                swap(list, left, right);
                left++;
                right--;
            }
        }
        if (from < right) {
            quicksort(list, from, right);
        }
        if (left < to) {
            quicksort(list, left, to);
        }
    }

    private static <T extends Comparable<? super T>> int pivotSelect(MyList<T> list, int from, int to) {

        int pivot = -1;

        int middle = (from + to) / 2;

        if (list.get(middle).compareTo(list.get(from)) > 0 && list.get(middle).compareTo(list.get(to)) > 0) {

            if (list.get(from).compareTo(list.get(to)) > 0) {
                pivot = from;
            } else {
                pivot = to;
            }
        } else if (list.get(from).compareTo(list.get(middle)) > 0 && list.get(from).compareTo(list.get(to)) > 0) {
            if (list.get(middle).compareTo(list.get(to)) > 0) {
                pivot = middle;
            } else {
                pivot = to;
            }
        } else {
            if (list.get(from).compareTo(list.get(middle)) > 0) {
                pivot = from;
            } else {
                pivot = middle;
            }
        }

        return pivot;
    }

    private static <T> void swap(MyList<T> list, int i, int j) {
        T temp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, temp);
    }
    
}
