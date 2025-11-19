package deque;

import java.util.ArrayList;
import java.util.List;

//@SuppressWarnings("unchecked")

public class ArrayDeque61B<T> implements Deque61B<T>{
    private int size;
    private T[] items = (T[]) new Object[8];
    private int nextFirst;
    private int nextLast;

    // Variant: size is the number of non-null items not length of array
    public ArrayDeque61B(){
        size = 0;
        items = (T[]) new Object[8];
        nextFirst = 3;
        nextLast = 4;
    }

    @Override
    public void addFirst(Object x) {
        items[nextFirst] = (T) x;
        nextFirst = Math.floorMod(nextFirst - 1, items.length);
        size++;
    }

    @Override
    public void addLast(Object x) {
        items[nextLast] = (T) x;
        nextLast = Math.floorMod(nextLast + 1, items.length);
        size++;
    }

    @Override
    public List<T> toList() {
        List<T> result = new ArrayList<>();

        for(int i = 0; i < size; i++){
            result.add(items[ Math.floorMod(nextFirst+1 +i,items.length) ]);
        }

        return result;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public T removeFirst() {
        return null;
    }

    @Override
    public T removeLast() {
        return null;
    }

    @Override
    public T get(int index) {
        return null;
    }

    @Override
    public T getRecursive(int index) {
        return null;
    }
}
