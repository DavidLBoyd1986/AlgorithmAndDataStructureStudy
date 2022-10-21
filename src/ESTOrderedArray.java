import java.util.ArrayList;
import java.util.Iterator;

/** 
 UNFINISHED!!!! MIGHT TROUBLESHOOT AND FINISH ANOTHER TIME.
 */

public class ESTOrderedArray<Key extends Comparable<Key>, Value> implements Iterable<Key> {

    private Node first;
    private int size;

    public ESTOrderedArray() {
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
        // If empty list, just create first node
        if (isEmpty()) {
            first = new Node(key, value);
            size++;
            return;
        }
        // If new key is smaller than first key, just put it first
        if (first.key.compareTo(key) > 0) {
            Node temp = first;
            first = new Node(key, value);
            first.next = temp;
            temp.previous = first;
            size++;
            return;
        }
        // Start looping through list
        Node current = first;
        while (current != null) {
            // If key is in table, replace value
            if (current.key == key) {
                current.value = value;
                return;
            }
            // If not at end of list, and this key is smaller and next key is larger, insert new node here
            if ((current.next != null) && ((current.key.compareTo(key) < 0) && (current.next.key.compareTo(key) > 0))) {
                Node newNode = new Node(key, value);
                newNode.next = current.next;
                current.next.previous = newNode; // These two must be updated before current.next
                current.next = newNode;
                newNode.previous = current;
                size++;
                return;
            }
            // If at end of list, and last key is smaller than key to be inserted, put at end of list
            if ((current.next == null) && (current.key.compareTo(key) < 0)) {
                Node newNode = new Node(key,value);
                current.next = newNode;
                newNode.previous = current;
                size++;
                return;
            }
            // go to next node
            current = current.next;
        }
        // Should be unreachable, put in Exception
        System.out.println("Should be unreachable, end of put method, what the fuck...");
    }
    
    public Value get(Key key) {
        if (isEmpty()) {
            return null;
        }
        Key[] keys = (Key[]) new Object[size];
        Value[] vals = (Value[]) new Object[size];
        int pos = 0;
        Node current = first;
        while (current != null) {
            keys[pos] = current.key;
            vals[pos] = current.value;
            pos++;            
        }
        int i = rank(key);
        if (i < size && keys[i].compareTo(key) == 0) {
            return vals[i];
        } else {
            return null;
        }

    }
    
    private int rank(Key key) {
        Key[] keys = (Key[]) new Object[size];
        int pos = 0;
        for (Key iterKey : keys()) {
            keys[pos] = iterKey;
            pos++;
        }
        int lo = 0, hi = size-1;
        while (lo <= hi) {
            int mid = lo + (hi -lo) / 2;
            int cmp = key.compareTo(keys[mid]);
            if (cmp < 0) {
                hi = mid -1;
            } else if (cmp > 0) {
                lo = mid + 1;
            } else {
                return mid;
            }
        }
        return lo;
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
    
    public Key min() {
        return first.key;
    }
    
    public Key max() {
        Node current = first;
        while (current.next != null) {
            current = current.next;
        }
        return current.key;
    }
    
    public void deleteMin() {
        Node temp = first;
        first = temp.next;
        first.previous = null;
        size--;
    }
    
    public void deleteMax() {
        Node current = first;
        while (current.next != null) {
            current = current.next;
        }
        current.previous.next = null;
        current.previous = null;
        size--;
    }
        
    public Iterable<Key> keys() {
        ArrayList<Key> keys = new ArrayList<Key>();
        if (isEmpty()) {
            return keys;
        }
        Node iterNode = first;
        while (iterNode != null) {
            keys.add(iterNode.key);
            iterNode = iterNode.next;
        }
        return keys;
    }
    
    public Iterable<Key> keys(Key lo, Key hi) {
        ArrayList<Key> keys = new ArrayList<Key>();
        if (isEmpty()) {
            return keys;
        }
        Node iterNode = first;
        while (iterNode != null) {
            if (iterNode.key.compareTo(lo) < 0) {
                continue;
            } else if (iterNode.key.compareTo(hi) > 0) {
                break;
            }
            keys.add(iterNode.key);
            iterNode = iterNode.next;
        }
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
        ESTOrderedArray<String, Integer> test = new ESTOrderedArray<String, Integer>();
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
