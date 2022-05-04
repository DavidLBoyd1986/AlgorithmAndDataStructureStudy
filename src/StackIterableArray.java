import java.util.Iterator;
import java.util.NoSuchElementException;


public class StackIterableArray<Item> implements Iterable<Item>{

    
    // initial capacity of underlying resizing array
    private static final int INIT_CAPACITY = 8;
    private Item[] array;
    private int array_size;
    
    public StackIterableArray() {
        array = (Item[]) new Object[INIT_CAPACITY];
        array_size = 0;
    }
    
    public boolean isEmpty() {
        return (array_size == 0);
    }
    
    public int size() {
        return array_size;
    }
    
    public void push(Item item) {
        if (array_size == array.length) {
            resize(2*array.length);
        }
        array[array_size++] = item;
    }
    
    public Item pop() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        Item item = array[array_size-1];
        array[array_size-1] = null;
        array_size--;
        if (array_size > 0 && array_size == array.length/4) {
            resize(array.length/2);
        }
        return item;
    }
    
    private void resize(int capacity) {
        
        assert capacity >= array_size;
        
        Item[] copy = (Item[]) new Object[capacity];
        for (int i = 0; i < array.length; i++) {
            copy[i] = array[i];
        }
        array = copy;
    }
    
    
    public Iterator<Item> iterator() {
        return new ReverseArrayIterator();
    }
    
    public class ReverseArrayIterator implements Iterator<Item> {
        private int iterator_position = array_size;
        
        public boolean hasNext() {
            return iterator_position > 0;
        }
        
        public Item next() {
            return array[--iterator_position];
        }
    }
    
    
    public static void main(String[] args) {
        StackIterableArray<String> test = new StackIterableArray<String>();
        test.push("Iterators");
        test.push(" seem ");
        test.push("complicated");
        test.push(" but ");
        test.push("are");
        test.push(" actually ");
        test.push("simple;");
        test.push(" just ");
        test.push("have");
        test.push(" to ");
        test.push("learn");
        test.push(" the ");
        test.push("required");
        test.push(" stuff ");
        test.push("that");
        test.push(" must ");
        test.push("be");
        test.push(" implemented.");
        for (String s : test) {
            System.out.print(s);
        }
        test.push("Iterators");
        test.push(" seem ");
        test.push("complicated");
        System.out.println(test.pop());
    }

}
