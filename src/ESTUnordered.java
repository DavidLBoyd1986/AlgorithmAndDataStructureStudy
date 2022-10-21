import java.util.ArrayList;
import java.util.Iterator;


public class ESTUnordered<Key, Value> implements Iterable<Key>{

    private Node first;
    private int size;

    public ESTUnordered() {
        size = 0;
    }

    private class Node{

        private Key key;
        private Value value;
        private Node next;
        private Node previous;
        
        private Node(Key _key, Value _value) {
            key = _key;
            value = _value;
        }
    }
    
    public void put (Key key, Value value) {
        if (isEmpty()) {
            first = new Node(key, value);
            size++;
            return;
        }
        Node current = first;
        while (current != null) {
            if (current.key == key) {
                current.value = value;
                return;
            } else {
                current = current.next;
            }
        }
        Node temp = first;
        first = new Node(key, value);
        first.next = temp;
        temp.previous = first;
        size++;
    }
    
    public Value get(Key key) {
        Node iterNode = first;
        while (iterNode != null) {
            if (iterNode.key == key) {
                return iterNode.value;
            }
            iterNode = iterNode.next;
        }
        return null;
    }
    
    public void delete(Key key) {
        // If empty return;
        if (isEmpty()) {
            return;
        }
        // If only one node, and key matches, set first to null
        if (first.next == null && first.key == key) {
            first = null;
            size--;
            return;
        }
        Node iterNode = first;
        while (iterNode != null) {
            if (iterNode.key == key) {
                // If first node, set 2nd node to first, and remove it's previous pointer
                if (iterNode == first) {
                    first = first.next;
                    first.previous = null;
                    size--;
                    return;
                // If last node, set 2nd to last node next pointer to null
                } else if (iterNode.next == null) {
                    iterNode.previous.next = null;
                    size--;
                // All else, remove deleted node from previous.next and next.previous
                } else {
                    iterNode.previous.next = iterNode.next;
                    iterNode.next.previous = iterNode.previous;
                    size--;
                    return;
                }
            }
            iterNode = iterNode.next;
        }
    }
    
    public boolean contains(Key key) {
        for (Key iterKey : this.keys()) {
            if (iterKey == key) {
                return true;
            }
        }
        return false;
    }
    
    public boolean isEmpty() {
        return first == null;
    }
    
    public int size() {
        return size;
    }
    
    public Iterable<Key> keys() {
        ArrayList<Key> keys = new ArrayList<Key>();
        if (isEmpty()) {
            return keys;
        }
        for (Key key : this) {
            keys.add(key);
        }
//        while (iterator().hasNext()) {
//            keys.add(iterator().next());
//        }
        return keys;
    }
    
    public Iterator<Key> iterator() {
        return new KeyIterator();
    }
    
    private class KeyIterator implements Iterator<Key> {
        
        private Node current = first;
        
        public boolean hasNext() {
            return current != null;
        }
        
        public Key next() {
            Key key = current.key;
            current = current.next;
            return key;
        }
    }
   
    public String toString() {
        int tempSize = size;
        StringBuilder output = new StringBuilder();
        output.append("{");
        for (Key key : this.keys()) {
            output.append("(");
            output.append(key);
            output.append(",");
            output.append(this.get(key));
            output.append(")");
            tempSize--;
            if (tempSize != 0) {
                output.append(",");
            }
        }
        output.append("}");
        return output.toString();
    }
    
    public static void main(String[] args) {
        ESTUnordered<String, Integer> test = new ESTUnordered<String, Integer>();
        test.put("a", 1);
        test.put("b", 2);
        test.put("c", 3);
        test.put("d", 4);
        test.put("e", 5);
        test.put("f", 6);
        test.put("g", 7);
        System.out.println(test.keys());
        System.out.println(test.size());
        System.out.println(test);
        test.delete("d");
        System.out.println(test);
        test.put("d", 4);
        System.out.println(test);
        test.put("b", 10);
        System.out.println(test);
        for (String key : test.keys()) {
            test.delete(key);
            System.out.println(test);
        }
        System.out.println(test);
        test.put("a", 1);
        test.put("b", 2);
        test.put("c", 3);
        test.put("d", 4);
        test.put("e", 5);
        test.put("f", 6);
        test.put("g", 7);
        System.out.println(test.keys());
        System.out.println(test.size());
        System.out.println(test);
    }
}
