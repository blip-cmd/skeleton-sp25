import net.sf.saxon.functions.ConstantFunction;
import org.checkerframework.checker.units.qual.K;

import java.util.Iterator;
import java.util.Set;

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K,V> {
    @Override
    public Iterator<K> iterator() {
        return null;
    }

    private class Node {
        K key;
        V value;
        Node left;
        Node right;

        Node(K k, V v){
            key = k;
            value = v;
        }
    }

    private Node root;
    private int size;

    public BSTMap() {
        root = null;
        size = 0;
    }

    @Override
    public void clear(){
        root = null;
        size = 0;
    }

    @Override
    public Set<K> keySet() {
        return Set.of();
    }

    @Override
    public V remove(K key) {
        return null;
    }

    @Override
    public void put(K key, V value) {
        root = putHelper(root, key, value);
    }
    private Node putHelper(Node n, K key, V value){
        // traverse till null before adding
        if(n==null){
            size += 1;
            return new Node(key,value);
        }
        int cmp = key.compareTo( n.key);
        if (cmp<0){
            n.left = putHelper(n.left, key, value);
        }
        else if (cmp>0){
            n.right = putHelper(n.right, key, value);
        }
        else { //cmp==0: key already exits
            n.value = value;
        }
        return n; // ensures path back up
    }

    @Override
    public V get(K key) {
        return getHelper(root, key);
    }

    private V getHelper(Node n, K key){
    // empty BSTMap
        if (n == null) {return null;}

    // comparing and traversing
        int cmp = key.compareTo( n.key);
                if (cmp < 0){
                    return (V) getHelper(n.left, key);
                }
                else if (cmp > 0){
                    return (V) getHelper(n.right, key);
                } else {
                    return (V) n.value;
                }
    }

    @Override
    public boolean containsKey(K key) {
        return containsKeyHelper(root, key);
    }

    private boolean containsKeyHelper(Node n, K key){
        if (n == null){
            return false;
        }
        int cmp = key.compareTo(n.key);
        if (n==null){
            return false;
        }
        if (cmp < 0){
            return containsKeyHelper(n.left,key);
        }
        else if(cmp > 0){
            return containsKeyHelper(n.right,key);
        }
        else{
            return true;
        }
    }

    public int size(){
        return size;
    }
}
