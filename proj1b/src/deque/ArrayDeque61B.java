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
        if(size == items.length){
            resize(size*2);
        }
        items[nextFirst] = (T) x;
        nextFirst = Math.floorMod(nextFirst - 1, items.length);
        size++;
    }

    @Override
    public void addLast(Object x) {
        if(size == items.length){
            resize(size*2);
        }
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
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public T removeFirst() {
        if(isEmpty()){
            return null;
        }
        int first_index = Math.floorMod(nextFirst + 1, items.length);
        T result = items[first_index];
        items[first_index] = null;
        nextFirst = first_index;
        size--;

        if (items.length >= 16 && size < items.length / 4) {
            resize(items.length / 2);
        }

        return result;
    }

    @Override
    public T removeLast() {
        if(isEmpty()){
            return null;
        }
        int last_index = Math.floorMod(nextLast -1 , items.length);
        T result = items[last_index];
        items[last_index] = null;
        size--;
        nextLast = last_index;

        if (items.length >= 16 && size < items.length / 4) {
            resize(items.length / 2);
        }

        return result;
    }

    @Override
    public T get(int index) {
        if(index > size || index < 0){
            return null;
        }
        return items[Math.floorMod(nextFirst+1 +index,items.length)];
    }

    @Override
    public T getRecursive(int index) {
        throw new UnsupportedOperationException("No need. Ude `T get(int index)`");
    }

    @SuppressWarnings("unchecked")
    public void resize(int capacity){
        T[] newItems = (T[]) new Object[capacity]; // new bigger list
        int start = Math.floorMod(nextFirst+1, items.length);

        for(int i = 0; i < size; i++){
            newItems[i] = items[Math.floorMod(start+i,items.length)];
        }

        items = newItems;
        nextLast = size ;
        nextFirst = capacity -1 ;
    }
}
