package ru.aston.MyCollections;

import java.util.Comparator;

public class QuickSort<T> implements SortAlgorithm<T>, Comparator<T>  {

    public void sort(T[] array) {
        if(array == null || array.length == 0)
            return ;
        quickSort(array, 0, array.length - 1);
    }

    private void quickSort(T[] array, int low, int high) {

        if(low < high) {
            int pi = partition(array, low, high);

            quickSort(array, low, pi - 1);
            quickSort(array, pi + 1, high);
        }

    }

    private int partition(T[] array, int low, int high) {

        int middle = low + (high - low) / 2;
        T pivot = array[middle];

        swap(array, middle, high);

        int i = low - 1;
        for(int j = low; j < high; j++) {
            
            if(array[j].compareTo(pivot) < 0){
                i++;
                swap(array, i, j);
            }

        }
        swap(array, i + 1, high);
        return i + 1;

    }

    private void swap(T[] array, int i, int j) {
        T temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    @Override
    public int compare(T arg0, T arg1) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'compare'");
    }
    
}
