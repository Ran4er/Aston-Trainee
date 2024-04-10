package ru.aston.model;

import java.util.Arrays;

/**
 * This is the ArrayList implementation class from Java Collections, 
 * which has all the basic methods of the original collection. 
 * The class is supplemented with some other methods
 * @param <T> type of element in collection
 * @author Ru6ik
 */

public class MyArrayList<T> implements MyList<T> {
    
    private static final int DEFAULT_CAPACITY = 10;
    private Object[] array;
    private int size;

    public MyArrayList(){
        array = new Object[DEFAULT_CAPACITY];
    }

    public MyArrayList(int initialCapacity){
        if (initialCapacity < 0)
            throw new IllegalArgumentException("Illegal Capacity: " + initialCapacity);
        array = new Object[initialCapacity];
    }

    /**
     * Method for adding an itemo to a collection
     * @param element is element for paste in collection
     * @return if it is successfully added to the collection, it returns true, otherwise false
     */
    @Override
    public boolean add(T element){
        ensureCapacity(size + 1);
        array[size++] = element;
        return true;
    }

    /**
     * Another method for adding an item to a collection by index (without replacement)
     * @param index the place where we would like to add an item to the collection
     * @param element the element to add
     * @throws IndexOutOfBoundsException if the index goes beyond the boundaries of the collection
     */
    @Override
    public void add(int index, T element){
        if (index < 0 || index > size)
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        ensureCapacity(size + 1);
        System.arraycopy(array, index, array, index + 1, size - index);
        array[index] = element;
        size++;
    }

    /**
     * Method for getting an item from a collection by index
     * @param index the location of the collection item that we want to get
     * @throws IndexOutOfBoundsException if the index goes beyond the boundaries of the collection
     * @return returns a collection item of the specified type
     */
    @SuppressWarnings("unchecked")
    public T get(int index) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        return (T) array[index];
    }

    /**
     * Method for removing element from collection
     * @param index the location of the collection item that we want to remove
     * @throws IndexOutOfBoundsException if the index goes beyond the boundaries of the collection
     */
    @Override
    public void remove(int index) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        int numMoved = size - index - 1;
        if (numMoved > 0)
            System.arraycopy(array, index + 1, array, index, numMoved);
        array[--size] = null;

    }

    /**
     * Method for removing all elements from collection (cleaning collection)
     * @return if it is successfully cleaning collection, return true, otherwise false (and return false if size collection equals zero)
     */
    @Override
    public void clear() {
        for(int i = 0; i < size; i++) {
            array[i] = null;
        }
        size = 0;
    }

    public void removeRange(int fromIndex, int toIndex) {
        if(fromIndex < 0 || toIndex >= size || fromIndex > toIndex) {
            throw new IndexOutOfBoundsException("Invalid range: fromIndex = " + fromIndex + ", toIndex = " + toIndex);
        }
        int numToRemove = toIndex - fromIndex + 1;
        int numMoved = size - toIndex - 1;
        System.arraycopy(array, toIndex + 1, array, fromIndex, numMoved);
        for(int i = 1; i <= numToRemove; i++) {
            array[--size] = null;
        }
    }

    /**
     * Method for returning the size of the collection
     * @return return the size of the collection
     */
    @Override
    public int size() {
        return size;
    }
    
    /**
     * This method is implementation method {@code isEmpty()} of {@code MyList}
     * Returns true, if this custom list contains no elements
     *
     * @return this method returns true, if this custom list contains no elements
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Method of sorting the collection item
     */
    public void sort() {
        Arrays.sort(array, 0, size);
     }

    private void ensureCapacity(int minCapacity){
        int oldCapacity = array.length;
        if (minCapacity > oldCapacity){
            int newCapacity = oldCapacity + (oldCapacity >> 1);
            if (newCapacity < minCapacity)
                newCapacity = minCapacity;
            array = Arrays.copyOf(array, newCapacity);
        }
    }

    /**
     * Method for setting the value of a collection item with replacement
     * @param index specifying the position of the element that we want to setting
     * @param element the element(value) that we want to set 
     */

    public void set(int index, T element) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        array[index] = element;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < size; i++) {
            sb.append(array[i]);
            if (i < size - 1)
                sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        MyArrayList<?> other = (MyArrayList<?>) obj;
        return size == other.size && Arrays.equals(array, other.array);
    }

    @Override
    public int hashCode() {
        int prime = 31;
        int result = 1;
        result = prime * result + Arrays.hashCode(array);
        result = prime * result + size;
        return result;
    }

}
