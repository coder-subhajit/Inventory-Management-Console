package collection;

import java.util.LinkedList;

public class MyStack<T> {
    private LinkedList<T> stack;

    public MyStack() {
        stack =new LinkedList<>();
    }
    //Create push(adds something to the top of the stack)
    public void push(T item){
        stack.addLast(item);
    }
    //Create pop(removes the top element)
    public T pop(){
        if(stack.isEmpty()){
            return null;
        }
        return stack.removeLast();
    }
    //Create peek(looks at the top without removing it)
    public T peek(){
        if(stack.isEmpty()){
            return null;
        }
        return stack.getLast();
    }
    //Checks is Empty
    public boolean isEmpty() {
        return stack.isEmpty();
    }
    //Create size
    public int size() {
        return stack.size();
    }
}

