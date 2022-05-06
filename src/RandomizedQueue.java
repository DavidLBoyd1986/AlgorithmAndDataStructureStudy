import edu.princeton.cs.algs4.StdRandom;
import java.util.Iterator;
import java.util.NoSuchElementException;


public class RandomizedQueue<Item> implements Iterable<Item> {

    private Item[] queue;
    private int startPosition = 0;
    private int endPosition = 0;
    private int size = 0;
    
    // construct an empty randomized queue
    public RandomizedQueue() {
        queue = (Item[]) new Object[1];
        startPosition = 0;
        endPosition = 0;
        size = 0;
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return size;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("enqued Item can't be null");
        }
        queue[endPosition] = item;
        endPosition++;
        size++;
        if (endPosition == queue.length) {
            resize(queue.length * 2);
        }
    }

    // remove and return a random item
    public Item dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty, nothing to deque");
        }
        int itemPosition = StdRandom.uniform(startPosition, endPosition);
        Item returnItem = queue[itemPosition];
        queue[itemPosition] = queue[endPosition-1];
        queue[endPosition-1] = null;
        endPosition--;
        size--;
        if (size <= queue.length/4) {
            resize(queue.length/2);
        }
        return returnItem;
    }
    
    private void resize(int newSize) {
        Item[] copy = (Item[]) new Object[newSize];
        for (int i = 0; i < size; i++) {
            copy[i] = queue[startPosition];
            startPosition++;
        }
        startPosition = 0;
        endPosition = size;
        queue = copy;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty, nothing to deque");
        }
        int itemPosition = StdRandom.uniform(endPosition) + startPosition;
        Item returnItem = queue[itemPosition];
        return returnItem;
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RandomArrayIterator();
    }
    
    private class RandomArrayIterator implements Iterator<Item> {

        int copySize;
        Item [] copyQueue;
        
        private RandomArrayIterator() {
        copySize = size;
        copyQueue = queue.clone();
        StdRandom.shuffle(copyQueue, startPosition, endPosition);
        }
        
        
        public boolean hasNext() {
            return copySize > 0;
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            return copyQueue[--copySize];
        }
        
//        public Item[] shuffle(Item[] copy_queue, int start, int end) {
//          StdRandom.shuffle(copy_queue, start, end);
//          return copy_queue;
//        }
        
        public void remove() {
            throw new UnsupportedOperationException();
        }
        
    }

    // unit testing (required)
    public static void main(String[] args) {
        RandomizedQueue<String> test = new RandomizedQueue<String>();
        test.enqueue("First");
        test.enqueue("Second");
        test.enqueue("Third");
        test.enqueue("Fourth");
        test.enqueue("Fifth");
        test.enqueue("Sixth");
        test.enqueue("Seventh");
        test.enqueue("Eight");
        test.enqueue("Ninth");
        test.enqueue("Tenth");
        System.out.println(" ");
        for (String s : test) {
            System.out.println(s);
            for (String z : test) {
                System.out.println(z);
            }
        }
        System.out.println(" ");
        System.out.println("----------------------");
        System.out.println(" ");
        for (String z : test) {
            System.out.println(z);
        }
        System.out.println("-----------------------------");
        System.out.println(" ");
        System.out.println(test.dequeue());
        System.out.println(test.dequeue());
        System.out.println(test.dequeue());
        System.out.println(test.dequeue());
        System.out.println(test.dequeue());
        System.out.println(test.dequeue());
        System.out.println(test.size());
        test.enqueue("Random_One");
        test.enqueue("Random_Two");
        test.enqueue("Random_Three");
        for (String s : test) {
            System.out.println(s);
        }
        System.out.println(" ");
        for (String s : test) {
            System.out.println(s);
        }

    }
}
