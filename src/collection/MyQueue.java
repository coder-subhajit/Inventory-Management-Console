package collection;

import java.util.LinkedList;

public class MyQueue<T> {

    //Create MyQueue<T>
    private final LinkedList<T> queue;

    public MyQueue() {
        queue = new LinkedList<>();
    }

    //Add an element(Add an item to the back of the queue)
    public void enqueue(T item) {
        queue.addLast(item);
    }

    //Remove an element(Remove the element from the front)
    public T dequeue() {

        if (queue.isEmpty()) {
            return null;
        }

        return queue.removeFirst();
    }

    //See the first element
    public T peek() {

        if (queue.isEmpty()) {
            return null;
        }

        return queue.getFirst();
    }

    //Check if empty
    public boolean isEmpty() {
        return queue.isEmpty();
    }

    //Get queue size
    public int size() {
        return queue.size();
    }
}