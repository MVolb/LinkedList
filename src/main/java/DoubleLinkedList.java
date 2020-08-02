import java.util.Arrays;
import java.util.Comparator;

public class DoubleLinkedList<T> {
    private Element<T> head; // head of list.
    private Element<T> tail; // tail of the list.
    private int size = 0;

    /**
     * The number of elements in the list.
     *
     * @return the number of elements
     */
    public int getSize() {
        return size;
    }

    /**
     * Insert T at the beginning of the list.
     *
     * @param val
     */
    public void addFirst(T val) {
        if (val == null) {
            System.out.println("invalid input");
            return; // input invalid
        }
        Element<T> node = new Element<>(val);
        if (size == 0) {
            head = node;
            tail = node;
        } else {
            node.next = head;
            head.prev = node;
            head = node;
        }
        size++;
    }

    /**
     * Insert T at the end of the list.
     *
     * @param val
     */
    public void addLast(T val) {
        if (val == null) {
            System.out.println("invalid input");
            return; // input invalid
        }
        Element<T> node = new Element<>(val);
        if (size == 0) {
            head = node;
            tail = node;
        } else {
            tail.next = node;
            node.prev = tail;
            tail = node;
        }
        size++;
    }

    /**
     * Inserts the sublist at the specified position of the list.
     *
     * @param index
     * @param list
     */
    public void addAll(int index, DoubleLinkedList<T> list) {
        if (index > size || index < 0 || list == null || list.size == 0) {
            System.out.println("invalid input");
            return; // input invalid
        }
        if (size == 0) {
            head = list.head;
            tail = list.tail;
        } else if (index == 0) {
            head.prev = list.tail;
            list.tail.next = head;
            head = list.head;
        } else if (index == size) {
            tail.next = list.head;
            list.head.prev = tail;
            tail = list.tail;
        } else {
            Element<T> node;
            if (index > size / 2) {
                node = tail;
                for (int i = 0; i < size - index; i++) {
                    node = node.prev;
                }
            } else {
                node = head;
                for (int i = 0; i < index - 1; i++) {
                    node = node.next;
                }
            }
            list.tail.next = node.next;
            node.next.prev = list.tail;
            node.next = list.head;
            list.head.prev = node;
        }
        size += list.size;
    }

    /**
     * Inserts T at the given position.
     *
     * @param index
     * @param val
     */
    public void add(int index, T val) {
        if (index > size || index < 0 || val == null) {
            System.out.println("invalid input");
            return; // input invalid
        }
        if (index == 0) {
            addFirst(val);
            return;
        }
        if (index == size) {
            addLast(val);
            return;
        }
        Element<T> newNode = new Element<>(val);
        Element<T> node;
        if (index > size / 2) {
            node = tail;
            for (int i = 0; i < size - index; i++) {
                node = node.prev;
            }
        } else {
            node = head;
            for (int i = 0; i < index - 1; i++) {
                node = node.next;
            }
        }
        newNode.prev = node;
        newNode.next = node.next;
        if (node.next != null) {
            node.next.prev = newNode;
        }
        node.next = newNode;
        size++;
    }

    /**
     * Sorts the list.
     */
    public void sort(Comparator<T> comparator) {
        T[] sortArray = (T[]) new Object[size];
        Element<T> node = head;
        for (int i = 0; i < size; i++) {
            sortArray[i] = node.data;
            node = node.next;
        }
        Arrays.sort(sortArray, comparator);
        clear();
        for (T t : sortArray) {
            addLast(t);
        }
    }

    /**
     * Inserts T in ascending order.
     *
     * @param val
     */
    public void addSorted(T val, Comparator<T> comparator) {
        sort(comparator);
        for (int i = 0; i < size; i++) {
            if (comparator.compare(val, get(i)) < 0) {
                add(i, val);
                break;
            }
        }
    }

    /**
     * Removes the first list T and returns it.
     *
     * @return The removed element
     */
    public Element<T> removeFirst() {
        Element<T> node = head;
        head.next.prev = null;
        head = head.next;
        size--;
        if (size == 0) {
            clear();
        }
        return node;
    }

    /**
     * Removes the last list T and returns it.
     *
     * @return The removed element
     */
    public Element<T> removeLast() {
        Element<T> node = tail;
        tail.prev.next = null;
        tail = tail.prev;
        size--;
        if (size == 0) {
            clear();
        }
        return node;
    }

    /**
     * Removes the passed T once and returns true, if it is in the list.
     *
     * @param val
     * @return True if the T is in the list.
     */
    public boolean remove(T val) {
        Element<T> newNode = new Element<>(val);
        Element<T> node = head;
        for (int i = 0; i < size; i++) {
            if (node.data.equals(newNode.data)) {
                node.prev.next = node.next;
                node.next.prev = node.prev;
                size--;
                return true;
            }
            node = node.next;
        }
        return false;
    }

    /**
     * Removes all elements from the list.
     */
    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }

    /**
     * Returns true if the passed T is in the list.
     *
     * @param val
     * @return
     */
    public boolean contains(T val) {
        Element<T> newNode = new Element<>(val);
        Element<T> node = head;
        for (int i = 0; i < size; i++) {
            if (node.data.equals(newNode.data)) {
                return true;
            }
            node = node.next;
        }
        return false;
    }

    /**
     * Returns the first T in the list.
     *
     * @return The first element.
     */
    public T getFirst() {
        return head.data;
    }

    /**
     * Returns the last T in the list.
     *
     * @return The last element.
     */
    public T getLast() {
        return tail.data;
    }

    /**
     * Returns T at the specified index (0 <= index < getSize()).
     *
     * @param index
     * @return The element, null instead.
     */
    public T get(int index) {
        Element<T> node = head;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }
        return node.data;
    }

    /**
     * Reverses the list (with the help of pointer operations).
     */
    public void reverse() {
        Element<T> node = tail;
        int listSize = getSize();
        clear();
        for (int i = 0; i < listSize; i++) {
            Element<T> prev = node.prev;
            addLast(node.data);
            node = prev;
        }
    }

    /**
     * Rotates the list for the given positive/negative distance. T at position i is shifted by (i + distance) % getSize()
     *
     * @param distance
     */
    public void rotate(int distance) {
        head.prev = tail;
        tail.next = head;
        Element<T> node;
        if (distance < 0) {
            node = head;
            for (int i = 0; i < -distance % getSize(); i++) {
                node = node.next;
            }
            head = node;
            tail = node.prev;
        } else {
            node = tail;
            for (int i = 0; i < distance % getSize() - 1; i++) {
                node = node.prev;
            }
            head = node;
            tail = node.prev;
        }
        head.prev = null;
        tail.next = null;
    }

    public Object[] toArray() {
        Object[] array = new Object[size];
        Element<T> node = head;
        for (int i = 0; i < size; i++) {
            array[i] = node.data;
            node = node.next;
        }
        return array;
    }

    public String toString() {
        StringBuilder string = new StringBuilder();
        Element<T> node = head;
        for (int i = 0; i < size; i++) {
            string.append(node.data);
            if (i != size - 1) {
                string.append(", ");
            }
            node = node.next;
        }

        return string.toString();
    }
}
