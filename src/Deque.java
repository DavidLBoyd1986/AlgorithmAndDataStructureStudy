import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    
    private Node first;
    private Node last;
    private int size;
    
    private class Node {
        Item item = null;
        Node next = null;
        Node previous = null;
    }

    // construct an empty deque
    public Deque() {
        first = null;
        last = null;
        size = 0;
    }

    // is the deque empty?
    public boolean isEmpty() {
        return (first == null && last == null);
    }

    // return the number of items on the deque
    public int size() {
        return size;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        // Add new Node like normal and increase size
        Node oldFirst = first;
        first = new Node();
        first.item = item;
        first.next = oldFirst;
        size++;
        // If it's the first Node make last Node = first Node
        if (last == null) {
            last = first;
        }
        // If first Node return, otherwise make the next Node.previous = new Node
        if (first.next == null) {
            return;
        }
        oldFirst.previous = first;
    }

    // add the item to the back
    public void addLast(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        // Add new Node like normal and increase size
        Node oldLast = last;
        last = new Node();
        last.item = item;
        last.previous = oldLast;
        size++;
        // If it's first node, make the first Node = last Node
        if (first == null) {
            first = last;
        }
        // If first Node return, otherwise make the previous Node.next = new Node
        if (last.previous == null) {
            return;
        }
        oldLast.next = last;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        // If this is the last element, verify last is null
        if (first.next == null) {
            last = null;
        }
        Item item = first.item;
        first = first.next;
        if (first != null) {
            first.previous = null;
        }
        size--;
        return item;
    }

    // remove and return the item from the back
    public Item removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        // I think this can be removed!!!!!!!!!!!!!!
        if (first == null) {
            last.previous = null;
        }
        // If this is the last element, verify first is null
        if (last.previous == null) {
            first = null;
        }
        Item item = last.item;
        last = last.previous;
        if (last != null) {
            last.next = null;
        }
        size--;
        return item;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new LinkedListIterator();
    }

    private class LinkedListIterator implements Iterator<Item> {
        
        private Node current = first;
        
        public boolean hasNext() {
            return current != null;
        }
        
        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = current.item;
            current = current.next;         
            return item;
        }
        
        public void remove(Item item) {
            throw new UnsupportedOperationException();
        }
    }
    // unit testing (required)
    public static void main(String[] args) {
        Deque<String> test = new Deque<String>();
        test.addFirst("Front");
        test.addLast("Last");
        System.out.println(test.removeFirst());
        System.out.println(test.removeLast());
        test.addLast("Last1");
        test.addLast("Last2");
        test.addLast("Last3");
        test.addLast("Last4");
        test.addLast("Last5");
        test.addLast("Last6");
        test.addLast("Last7");
        System.out.println(test.removeFirst());
        System.out.println(test.removeLast());
        for (String s : test) {
            System.out.println(s);
        }
        System.out.println(test.removeFirst());
        System.out.println(test.removeLast());
        System.out.println(test.removeLast());
        System.out.println(test.removeFirst());
        System.out.println(test.removeFirst());
        System.out.println(test.size());
        System.out.println(test.isEmpty());
        test.addFirst("First1");
        test.addLast("Last1");
        test.addFirst("First2");
        test.addLast("Last2");
        test.addFirst("First3");
        test.addLast("Last3");
        test.addFirst("First4");
        test.addLast("Last4");
        test.addFirst("First5");
        test.addLast("Last5");
        for (String s : test) {
            System.out.println(s);
        }
        System.out.println(test.size());
        System.out.println(test.isEmpty());
        test.addFirst("IsEmptyNo");
        System.out.println(test.isEmpty());
    }

}
