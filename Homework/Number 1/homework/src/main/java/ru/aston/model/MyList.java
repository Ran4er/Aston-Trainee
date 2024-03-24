package ru.aston.model;

/**
 * Interface for collections representing a list
 * 
 * @param <T> type of objets of the my list
 * 
 * @author Ru6ik
 */

public interface MyList<T>  {

    /**
     * Method for returning size of the collection
     * @return collection size
     */
    public int size();

    /**
     * The method adds an element to the collection, but at the end of it
     * @param element the element that we want to add to the collection
     * @return however, if the item is successfully added
     */
    public boolean add(T element);

    /**
     * The method adds an element to the specified location of the collection
     * @param index where in the collection do we want to add the collection item
     * @param element the element that we want to add to the collection
     */
    public void add(int index, T element);

    /**
     * A method for getting a collection item by its index
     * @param index the place of the item in the collection
     * @return an element of the collection, at the specified index
     */
    public T get(int index);

    /**
     * The method that sets the element we specified by index to the collection
     * @param index the location of the element that we will replace with a new element
     * @param element the element itself, which we will replace
     */
    public void set(int index, T element);
    
    /**
     * Method for cleaning the collection (removes all the elements of the collection)
     */
    public void clear();

    /**
     * Method for deleting a collection item by index
     * @param index the location of the collection item that we want to delete
     */
    public void remove(int index);

    /**
     * This method returns {@code true}, if this custom list contains no elements
     *
     * @return this method returns {@code true}, if this custom list contains no elements
     */
    public boolean isEmpty();

    public boolean equals(Object o);

    public int hashCode();
    
}
