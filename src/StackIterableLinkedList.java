import java.util.Iterator;
import java.util.NoSuchElementException;

public class StackIterableLinkedList<Item> implements Iterable<Item>{

    private Node first;
    
    
    private class Node {
        private Item item;
        private Node next;
    }
    
    
    public boolean isEmpty() {
        return first == null;
    }

    
    public Item pop() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        Item item = first.item;
        first = first.next;
        return item;
    }
    
    
    public void push(Item item) {
        Node old_first = first;
        first = new Node();
        first.item = item;
        first.next = old_first;
    }
    
    
    public Iterator<Item> iterator() {
        return new ListIterator();
    }
    
    
    public class ListIterator implements Iterator<Item> {
        
        private Node current = first;
        
        
        public boolean hasNext() {
            return current != null;
        }
        
        
        public Item next() {
            Item item = current.item;
            current = current.next;
            return item;
        }
    }
    
    
    public static void main(String[] args) {
        StackIterableLinkedList<String> test = new StackIterableLinkedList<String>();
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
    }

}
