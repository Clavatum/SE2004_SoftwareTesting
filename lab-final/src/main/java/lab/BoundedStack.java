package lab;

import java.util.NoSuchElementException;

/**
 * Task 3 – BoundedStack
 *
 * A generic stack with a fixed maximum capacity.
 * DO NOT modify this file.
 */
public class BoundedStack<T> {

    private final Object[] data;
    private int top = -1;

    /**
     * Creates a new BoundedStack with the given capacity.
     *
     * @param capacity maximum number of elements (must be > 0)
     * @throws IllegalArgumentException if capacity <= 0
     */
    public BoundedStack(int capacity) {
        if (capacity <= 0)
            throw new IllegalArgumentException("capacity must be > 0");
        this.data = new Object[capacity];
    }

    /**
     * Pushes an item onto the top of the stack.
     *
     * @throws IllegalStateException if the stack is full
     */
    public void push(T item) {
        if (isFull())
            throw new IllegalStateException("Stack is full");
        data[++top] = item;
    }

    /**
     * Removes and returns the item at the top of the stack.
     *
     * @throws NoSuchElementException if the stack is empty
     */
    @SuppressWarnings("unchecked")
    public T pop() {
        if (isEmpty())
            throw new NoSuchElementException("Stack is empty");
        T item    = (T) data[top];
        data[top--] = null;   // help GC
        return item;
    }

    /**
     * Returns (without removing) the item at the top of the stack.
     *
     * @throws NoSuchElementException if the stack is empty
     */
    @SuppressWarnings("unchecked")
    public T peek() {
        if (isEmpty())
            throw new NoSuchElementException("Stack is empty");
        return (T) data[top];
    }

    /** Returns the number of elements currently in the stack. */
    public int size() { return top + 1; }

    /** Returns {@code true} if the stack contains no elements. */
    public boolean isEmpty() { return top == -1; }

    /** Returns {@code true} if the stack has reached its capacity. */
    public boolean isFull() { return top == data.length - 1; }

    /** Returns the maximum number of elements this stack can hold. */
    public int capacity() { return data.length; }
}
