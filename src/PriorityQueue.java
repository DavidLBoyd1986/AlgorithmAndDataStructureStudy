
public class PriorityQueue<Key extends Comparable<Key>> {

    private Key[] pq;
    private int count;
    
    public PriorityQueue() {
        pq = (Key[]) new Comparable[1];
        count = 0;
    }
    
    
    public boolean isEmpty() {
        return count == 0;
    }

    
    public void add(Key key) {
        if (count == pq.length) {
            resize(count*2);
        }
        pq[count++] = key;
    }
    
    
    public int size() {
        int size = count;
        return size;
    }
    
    
    public Key removeMax() {
        int max = 0;
        for (int i = 1; i < count; i++) {
            if (less(pq[i], pq[max])) {
                max = i;
            }
        }
        exch(max, count-1);
        Key returnItem = pq[--count];
        // Resize array if it's 1/4 full
        if (count > 0 && count == pq.length/4) {
            resize(pq.length/2);
        }
        return returnItem;
    }
    
    
    private void resize(int newSize) {
        Key[] pqCopy = (Key[]) new Comparable[newSize];
        for (int i = 0; i < count; i++) {
            pqCopy[i] = pq[i];
        }
        pq = pqCopy;
    }
    private boolean less(Key a, Key b) {
        return a.compareTo(b) < 0;
    }
    
    
    private void exch(int a, int b) {
        Key temp = pq[a];
        pq[a] = pq[b];
        pq[b] = temp;
    }
    
    public static void main(String[] args) {
        PriorityQueue<String> a = new PriorityQueue<String>();
        a.add("q");
        a.add("w");
        a.add("e");
        a.add("r");
        a.add("t");
        a.add("y");
        a.add("u");
        a.add("i");
        a.add("o");
        a.add("p");
        a.add("a");
        a.add("s");
        a.add("d");
        a.add("f");
        a.add("g");
        a.add("h");
        a.add("j");
        a.add("k");
        a.add("l");
        a.add("z");
        a.add("x");
        a.add("c");
        a.add("v");
        a.add("b");
        a.add("n");
        a.add("m");
        System.out.println(a.size());
        int aSize = a.size();
        for (int i = 0; i < aSize; i++) {
            System.out.println(a.removeMax());
        }
        System.out.println(a.size());
    }
}
