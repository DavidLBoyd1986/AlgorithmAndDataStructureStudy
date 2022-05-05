import edu.princeton.cs.algs4.StdRandom;
import java.util.Iterator;
import java.util.NoSuchElementException;


public class RandomizedQueue<Item> implements Iterable<Item> {

    private Item[] queue;
    private int start_position = 0;
    private int end_position = 0;
    private int size = 0;
    
    // construct an empty randomized queue
    public RandomizedQueue() {
        queue = (Item[]) new Object[1];
        start_position = 0;
        end_position = 0;
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
        queue[end_position] = item;
        end_position++;
        size++;
        if (end_position == queue.length) {
            resize(queue.length * 2);
        }
    }

    // remove and return a random item
    public Item dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty, nothing to deque");
        }
        int item_position = StdRandom.uniform(start_position, end_position);
        Item return_item = queue[item_position];
        queue[item_position] = queue[end_position-1];
        queue[end_position-1] = null;
        end_position--;
        size--;
        if (size <= queue.length/4) {
            resize(queue.length/2);
        }
        return return_item;
    }
    
    public void resize(int new_size) {
        Item[] copy = (Item[]) new Object[new_size];
        for (int i = 0; i < size; i++) {
            copy[i] = queue[start_position];
            start_position++;
        }
        start_position = 0;
        end_position = size;
        queue = copy;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty, nothing to deque");
        }
        int item_position = StdRandom.uniform(end_position) + start_position;
        Item return_item = queue[item_position];
        return return_item;
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RandomArrayIterator();
    }
    
    private class RandomArrayIterator implements Iterator<Item> {

        private int copy_size = size;
        Item [] copy_queue = shuffle(queue, start_position, end_position);
        
        
        public boolean hasNext() {
            return !(copy_size == 0);
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            return copy_queue[--copy_size];
        }
        
        public Item[] shuffle(Item[] copy_queue, int start, int end) {
          StdRandom.shuffle(copy_queue, start, end);
          return copy_queue;
        }
        
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
        System.out.println(" ");
        for (String s : test) {
            System.out.println(s);
        }
    }
}
