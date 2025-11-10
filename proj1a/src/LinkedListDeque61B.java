import java.util.List;
import java.util.ArrayList; // import the ArrayList class

public class LinkedListDeque61B<T> implements Deque61B<T>{
    private Node sentinel;
    private int size;

    private class Node {
        T item;
        Node prev;
        Node next;

        Node(T item, Node prev, Node next){
            this.item = item;
            this.next = next;
            this. prev = prev;
        }
    }

    public LinkedListDeque61B(){
    sentinel = new Node(null,null,null);
    sentinel.next = sentinel;
    sentinel.prev = sentinel;
    size = 0;
    }

    @Override
    public void addFirst(T x) {
    Node oldFirst = sentinel.next;
    Node newNode = new Node(x ,sentinel, oldFirst);
    sentinel.next = newNode;
    oldFirst.prev = oldFirst;
    size++;
    }

    @Override
    public void addLast(T x) {
    Node oldLast = sentinel.prev;
    Node newNode = new Node(x, oldLast, sentinel);
    oldLast.next = newNode;
    sentinel.prev = newNode;
    size++;
    }

    @Override
    public List<T> toList() {
        List<T> returnList = new ArrayList<>();
        Node current = sentinel.next;
        while (current != sentinel){
            returnList.add(current.item);
            current = current.next;
        }
        return returnList;
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
        return null;
    }

    @Override
    public T removeLast() {
        return null;
    }

    @Override
    public T get(int index) {
        if(index < 0 || index>=size){
        return null;
        }
        Node current = sentinel.next;
        for(int i = 0;  i < index; i++){
            current = current.next;
        }
        return current.item;
    }

    @Override
    public T getRecursive(int index) {
        return null;
    }
}
