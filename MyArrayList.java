/*
 * Name: Zhiyuan Huang
 * Email: zhh087@ucsd.edu
 * PID: 200336800
 * Sources used: pa2-starter
 *
 * My implementation of ArrayList.
 */

/**
 * A generic, ArrayList that grows its capacity as needed(2x).
 * Implements the abstract MyList interface.
 *
 * Instance variables:
 * data - the array storing the elements
 * size - the length of valid elements
 */
public class MyArrayList<E> implements MyList<E>{
    private static final int DEFAULT_CAP_SIZE_VAL = 5;
    
    // data
    int size = 0;
    Object[] data;

    // constructors
    /**
     * Creates an empty list with the default capacity.
     */
    public MyArrayList(){
        data = new Object[DEFAULT_CAP_SIZE_VAL];
    }
    /**
     * Creates an empty list with the given capacity len.
     *
     * @param len the given capacity
     * @throws IllegalArgumentException if len is strictly smaller than 0
     */
    public MyArrayList(int len){
        if(len<0)
            throw new IllegalArgumentException("Illegal Argument! Received: "+len);

        data = new Object[len];
    }
    /**
     * Creates a list being a copy of the given Java native array's elements.
     *
     * @param arr the given array
     */
    public MyArrayList(E[] arr){
        if(arr==null){
            data = new Object[DEFAULT_CAP_SIZE_VAL];
            return;
        }
        size = arr.length;
        data = new Object[size];
        for(int i = 0;i<size;i++){
            data[i] = arr[i];
        }
    }

    // public methods
    /**
     * Increases the capacity of the underlying array, each step is x2
     *
     * @param len the minimum capacity after expanding
     * @throws IllegalArgumentException if requiredCapacity is less than the current capacity
     */
    public void expandCapacity (int len){
        if(len<data.length)
            throw new IllegalArgumentException("Small Target Capacity! Current: "+data.length+"Target: "+len);

        int cap = Math.max(data.length == 0 ? DEFAULT_CAP_SIZE_VAL : data.length * 2, len);

        Object[] tgt_data = new Object[cap];
        for(int i = 0;i<size;i++)
            tgt_data[i]=data[i];
        data = tgt_data;
    }
    /**
     * Returns the number of elements can be hold in current data.
     *
     * @return the capacity of the list
     */
    public int getCapacity(){
        return data.length;
    }
    /**
     * Inserts an element at the given index.
     *
     * @param index the index to insert at
     * @param a the element to insert
     * @throws IndexOutOfBoundsException if index is invalid
     *         than size
     */
    public void add(int index, E a){
        if(index<0||index>size)
            throw new IndexOutOfBoundsException("Illegal Index! Received: "+index);
        
        int cap = data.length == size? data.length == 0 ? DEFAULT_CAP_SIZE_VAL : data.length * 2 : data.length;

        Object[] tgt_data = new Object[cap];
        for(int i = 0;i<index;i++)
            tgt_data[i] = data[i];
        tgt_data[index] = a; size++;
        for(int i = index + 1;i<size;i++)
            tgt_data[i] = data[i-1];
        data = tgt_data;
    }
    /**
     * Appends an element to the end of the list.
     *
     * @param a the element to append
     */
    public void append(E a){
        if(size == data.length)
            expandCapacity(size+1);
        data[size++]=a;
    }
    /**
     * Returns the element at the given position.
     *
     * @param index the position of the element
     * @return the element at index
     * @throws IndexOutOfBoundsException if index is negative or not less than size
     */
    public E get(int index){
        if(index<0||index>=size)
            throw new IndexOutOfBoundsException("Illegal Index! Received: "+index);
        return (E)data[index];
    }
    /**
     * Replaces the element at the given index.
     *
     * @param index the position to replace
     * @param a the new element
     * @return the element previously at index
     * @throws IndexOutOfBoundsException if index is negative or not less than size
     */
    public E set(int index, E a){
        if(index<0||index>=size)
            throw new IndexOutOfBoundsException("Illegal Index! Received: "+index);
        Object ans= data[index];
        data[index]=a;
        return (E)ans;
    }
    /**
     * Removes the element at the given index.
     *
     * @param index the position of the element to remove
     * @return the removed element
     * @throws IndexOutOfBoundsException if index is negative or not less than size
     */
    public E remove(int index){
        if(index<0||index>=size)
            throw new IndexOutOfBoundsException("Illegal Index! Received: "+index);

        Object ans= data[index];
        Object[] tgt_data = new Object[data.length];
        for(int i = 0;i<index;i++)
            tgt_data[i]=data[i];
        size--;
        for(int i = index;i<size;i++)
            tgt_data[i]=data[i+1];
        data=tgt_data;

        return (E)ans;
    }
    /**
     * Returns the number of active elements in the list.
     *
     * @return the size of the list
     */
    public int size(){
        return size;
    }
}