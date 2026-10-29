/*
 * Name: Zhiyuan Huang
 * Email: zhh087@ucsd.edu
 * PID: 200336800
 * Sources used: PA3 write-up and starter code, Claude (AI assistant)
 *
 * This file contains MyLinkedList, a generic doubly linked list that uses
 * sentinel head and tail nodes, along with its inner Node class.
 */

import java.util.AbstractList;

/**
 * A generic LinkedList that is linked in both directions.
 * Extends the AbstractList class.
 *
 * Instance variables:
 * head - the leading sentinel node
 * tail - the ending sentinel node
 * size - the number of valid (non-sentinel) nodes
 */
public class MyLinkedList<E> extends AbstractList<E> {

    private static final String INDEX_ERROR_MSG = "index out of bounds";
    private static final String NULL_DATA_MSG = "data is null";

    int size;
    Node head;
    Node tail;

    /**
     * A Node class that holds data and references to previous and next Nodes.
     *
     * Instance variables:
     * data - the data stored in the node
     * next - the next node this node links to
     * prev - the previous node this node links to
     */
    protected class Node {
        E data;
        Node next;
        Node prev;

        /**
         * Constructor to create singleton Node
         * @param element Element to add, can be null
         */
        public Node(E element) {
            // Initialize the instance variables
            this.data = element;
            this.next = null;
            this.prev = null;
        }

        /**
         * Set the parameter prev as the previous node
         * @param prev new previous node
         */
        public void setPrev(Node prev) {
            this.prev = prev;
        }

        /**
         * Set the parameter next as the next node
         * @param next new next node
         */
        public void setNext(Node next) {
            this.next = next;
        }

        /**
         * Set the parameter element as the node's data
         * @param element new element
         */
        public void setElement(E element) {
            this.data = element;
        }

        /**
         * Accessor to get the next Node in the list
         * @return the next node
         */
        public Node getNext() {
            return this.next;
        }

        /**
         * Accessor to get the prev Node in the list
         * @return the previous node
         */
        public Node getPrev() {
            return this.prev;
        }

        /**
         * Accessor to get the Nodes Element
         * @return this node's data
         */
        public E getElement() {
            return this.data;
        }
    }

    /**
     * Creates an empty list whose head and tail sentinels point to
     * each other.
     */
    public MyLinkedList() {
        size = 0;
        head = new Node(null);
        tail = new Node(null);
        head.setNext(tail);
        tail.setPrev(head);
    }

    /**
     * Finds the node at the given index by walking forward from head.
     * An index equal to size returns the tail sentinel.
     *
     * @param index the position of the node to find
     * @return the node at that index
     */
    protected Node getNth(int index) {
        Node cur = head.getNext();
        while (index-- > 0) {
            cur = cur.getNext();
        }
        return cur;
    }

    /**
     * Returns the number of elements in the list.
     *
     * @return the number of elements, not counting sentinels
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Returns the element at the given index.
     *
     * @param index the position of the element
     * @return the element at that index
     * @throws IndexOutOfBoundsException if index is outside [0, size)
     */
    @Override
    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException(INDEX_ERROR_MSG);
        }
        return getNth(index).getElement();
    }

    /**
     * Inserts data at the given index, shifting later elements back.
     *
     * @param index the position the new element will have
     * @param data the element to insert
     * @throws NullPointerException if data is null
     * @throws IndexOutOfBoundsException if index is outside [0, size]
     */
    @Override
    public void add(int index, E data) {
        if (data == null) {
            throw new NullPointerException(NULL_DATA_MSG);
        }
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException(INDEX_ERROR_MSG);
        }

        // Link the new node between cur and the node before it
        Node cur = getNth(index);
        Node prev = cur.getPrev();
        Node newNode = new Node(data);
        newNode.setPrev(prev);
        newNode.setNext(cur);
        prev.setNext(newNode);
        cur.setPrev(newNode);

        size++;
    }

    /**
     * Appends data to the end of the list.
     *
     * @param data the element to append
     * @return true once the element is added
     * @throws NullPointerException if data is null
     */
    @Override
    public boolean add(E data) {
        if (data == null) {
            throw new NullPointerException(NULL_DATA_MSG);
        }

        // Link the new node between the last node and tail
        Node prev = tail.getPrev();
        Node newNode = new Node(data);
        newNode.setPrev(prev);
        newNode.setNext(tail);
        prev.setNext(newNode);
        tail.setPrev(newNode);

        size++;
        return true;
    }

    /**
     * Replaces the element at the given index.
     *
     * @param index the position of the element to replace
     * @param data the new element
     * @return the element that was replaced
     * @throws NullPointerException if data is null
     * @throws IndexOutOfBoundsException if index is outside [0, size)
     */
    @Override
    public E set(int index, E data) {
        if (data == null) {
            throw new NullPointerException(NULL_DATA_MSG);
        }
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException(INDEX_ERROR_MSG);
        }

        Node cur = getNth(index);
        E oldData = cur.getElement();
        cur.setElement(data);
        return oldData;
    }

    /**
     * Removes the element at the given index.
     *
     * @param index the position of the element to remove
     * @return the element that was removed
     * @throws IndexOutOfBoundsException if index is outside [0, size)
     */
    @Override
    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException(INDEX_ERROR_MSG);
        }

        // Unlink cur by connecting its neighbors to each other
        Node cur = getNth(index);
        cur.getPrev().setNext(cur.getNext());
        cur.getNext().setPrev(cur.getPrev());

        size--;
        return cur.getElement();
    }

    /**
     * Removes every element, leaving only the sentinels.
     */
    @Override
    public void clear() {
        head.setNext(tail);
        tail.setPrev(head);
        size = 0;
    }

    /**
     * Checks whether the list has no elements.
     *
     * @return true if the list is empty, false otherwise
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }
}
