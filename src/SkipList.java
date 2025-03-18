/**
 * Name: Yash Parmar
 * Pledge: I pledge my honor that I have abided by the Stevens Honor System.
 *
 * Designed and optimized to allow manipulation, access, and speedy use of nodes in a
 * linked list of generic type.
 * @author Yash Parmar
 */

public class SkipList<E> {
    private static class Node<E> {
        E data;
        Node<E>[] forwards;
    }

    private Node<E> head;
    private int length;

    public SkipList() {
        this.head = new Node<>();
        this.length = 0;
    }

    public E get(int index) {
        if (index < 0 || index >= length) {
            throw new IndexOutOfBoundsException("Index out of bounds: " + index);
        }
        else {
            return null;
        }
    }

    public void set(int index, E value) {
        if (index < 0 || index >= length) {
            throw new IndexOutOfBoundsException("Index out of bounds: " + index);
        }
        else {
            return null;
        }
    }

    public void add(int index, E value) {
        if (index < 0 || index >= length) {
            throw new IndexOutOfBoundsException("Index out of bounds: " + index);
        }
        else {
            return null;
        }
    }

    public void remove(int index) {
        if (index < 0 || index >= length) {
            throw new IndexOutOfBoundsException("Index out of bounds: " + index);
        }
        else {
            return null;
        }
    }

    public static void main(String[] args) {
        SkipList<Integer> list = new SkipList<>();
        list.add(-1);
    }
}