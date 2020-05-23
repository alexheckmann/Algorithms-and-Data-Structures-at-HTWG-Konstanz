import java.util.Iterator;
import java.util.NoSuchElementException;

public class Stack<E> implements Iterable<E> {

    private int n;          // size of the stack
    private Node first;     // top of stack

    // helper linked list class
    private class Node {

        private E e;
        private Node next;

    }

    /**
     * Initializes an empty stack.
     */
    public Stack() {

        first = null;
        n = 0;

    }

    /**
     * Returns true if this stack is empty.
     *
     * @return true if this stack is empty; false otherwise
     */
    public boolean isEmpty() {
        return first == null;
    }

    /**
     * Returns the number of items in this stack.
     *
     * @return the number of items in this stack
     */
    public int size() {
        return n;
    }

    /**
     * Adds the item to this stack.
     *
     * @param e the item to add
     */
    public void push(E e) {

        Node oldFirst = first;
        first = new Node();
        first.e = e;
        first.next = oldFirst;
        n++;

    }

    /**
     * Removes and returns the item most recently added to this stack.
     *
     * @return the item most recently added
     * @throws NoSuchElementException if this stack is empty
     */
    public E pop() {

        if (isEmpty()) {
            throw new NoSuchElementException("Stack underflow");
        }

        E e = first.e;        // save item to return
        first = first.next;            // delete first node
        n--;

        return e;                   // return the saved item

    }


    /**
     * Returns (but does not remove) the item most recently added to this stack.
     *
     * @return the item most recently added to this stack
     * @throws NoSuchElementException if this stack is empty
     */
    public E peek() {

        if (isEmpty()) {
            throw new NoSuchElementException("Stack underflow");
        }

        return first.e;

    }

    /**
     * Returns a string representation of this stack.
     *
     * @return the sequence of items in this stack in LIFO order, separated by spaces
     */
    public String toString() {

        StringBuilder s = new StringBuilder();

        for (E e : this) {

            s.append(e);
            s.append(' ');

        }

        return s.toString();

    }


    /**
     * Returns an iterator to this stack that iterates through the items in LIFO order.
     *
     * @return an iterator to this stack that iterates through the items in LIFO order
     */
    public Iterator<E> iterator() {
        return new ListIterator();
    }

    // an iterator, doesn't implement remove() since it's optional
    private class ListIterator implements Iterator<E> {

        private Node current = first;

        public boolean hasNext() {
            return current != null;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public E next() {

            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            E e = current.e;
            current = current.next;

            return e;

        }

    }

}
