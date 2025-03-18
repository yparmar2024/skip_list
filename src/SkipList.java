/**
 * Name: Yash Parmar
 * Pledge: I pledge my honor that I have abided by the Stevens Honor System.
 *
 * Designed and optimized to allow manipulation, access, and speedy use of nodes in a
 * linked list of generic type.
 *
 * @param <E> the type of elements in the skip list
 *
 * @author Yash Parmar
 */

public class SkipList<E> {

    /**
     * A node containing data and forward references to other nodes at various floors.
     *
     * @param <E> the type of data held by the node
     */

    private static class Node<E> {
        E data;
        Node<E>[] forwards;

        public Node(E data, int floor) {
            this.data = data;
            this.forwards = new Node[Math.max(1, floor)];
        }
    }

    private Node<E> head;
    private int length;
    private int maxFloor;

    /**
     * Constructs an empty SkipList.
     */

    public SkipList() {
        this.head = new Node<>(null, 1);
        this.length = 0;
        this.maxFloor = 1;
    }

    /**
     * Gets the element at the specified index.
     *
     * @param index the index of the element
     * @return the element at the index
     */

    public E get(int index) {
        if (index < 0 || index >= this.size()) {
            throw new IndexOutOfBoundsException("Index " + index + " is out of bounds for size " + this.size());
        }
        return getNode(index).data;
    }

    /**
     * Sets the element at the specified index.
     *
     * @param index the index of the element
     * @param value the value to set
     */

    public void set(int index, E value) {
        if (index < 0 || index >= this.size()) {
            throw new IndexOutOfBoundsException("Index " + index + " is out of bounds for size " + this.size());
        }
        getNode(index).data = value;
    }

    private Node<E> getNode(int index) {
        Node<E> current = head;
        int currentIndex = -1;

        for (int i = head.forwards.length - 1; i >= 0; i--) {
            while(current.forwards[i] != null && currentIndex + (1 << i) < index) {
                current = current.forwards[i];
                currentIndex += (1 << i);
            }
        }
        return (current.forwards[0] != null) ? current.forwards[0] : null;
    }

    /**
     * Adds a new element at the specified index.
     *
     * @param index the index to insert the element at
     * @param value the value to insert
     */

    public void add(int index, E value) {
        if (index < 0 || index > this.size()){
            throw new IndexOutOfBoundsException("Index " + index + " is out of bounds for size " + this.size());
        }

        int newFloor = (int) (Math.log(this.length + 1) / Math.log(2)) + 1;
        newFloor = Math.min(newFloor, maxFloor + 1);

        if (newFloor > maxFloor) {
            maxFloor = newFloor;
            Node<E> newHead = new Node<>(null, maxFloor);
            System.arraycopy(head.forwards, 0, newHead.forwards, 0, head.forwards.length);
            head = newHead;
        }

        Node<E> newNode = new Node<>(value, newFloor);
        Node<E> current = head;
        Node<E>[] update = new Node[newFloor];
        int count = index;

        for (int i = newFloor - 1; i >= 0; i--) {
            while (current.forwards[i] != null && count > 0) {
                current = current.forwards[i];
                count--;
            }
            update[i] = current;
        }

        for (int i = 0; i < newFloor; i++) {
            newNode.forwards[i] = update[i].forwards[i];
            update[i].forwards[i] = newNode;
        }
        length++;
    }

    /**
     * Removes the element at the specified index.
     *
     * @param index the index of the element to remove
     */

    public void remove(int index) {
        if (index < 0 || index >= this.size()) {
            throw new IndexOutOfBoundsException("Index " + index + " is out of bounds for size " + this.size());
        }

        Node<E> current = head;
        Node<E>[] update = new Node[head.forwards.length];

        for (int i = head.forwards.length - 1; i >= 0; i--) {
            while (current.forwards[i] != null && index > 0) {
                current = current.forwards[i];
                index--;
            }
            update[i] = current;
        }

        Node<E> target = current.forwards[0];

        if (target == null) {
            return;
        }

        for (int i = 0; i < target.forwards.length; i++) {
            update[i].forwards[i] = target.forwards[i];
        }
        length--;
    }

    /**
     * Returns the size of the skip list.
     *
     * @return the number of elements in the skip list
     */

    public int size() {
        return this.length;
    }

    /**
     * Find the first occurence of the specified value.
     *
     * @param value the value to search for
     * @return the index of the first occurence, or -1, if not found
     */

    public int findFirst(E value) {
        Node<E> current = head;
        int index = 0;

        while (current.forwards[0] != null) {
            current = current.forwards[0];
            if (current.data != null & current.data.equals(value)) {
                return index;
            }
            index++;
        }
        return -1;
    }
}