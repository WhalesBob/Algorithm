package SWEA;

import java.util.LinkedList;

public class Queue<T>{

    LinkedList<T> queue;

    public Queue() {
        queue = new LinkedList<>();
    }

    void enqueue(T n){
        queue.addLast(n);
    }

    T dequeue(){
        T n = queue.get(0);
        queue.removeFirst();
        return n;
    }

    boolean isEmpty(){
        return queue.isEmpty();
    }
}