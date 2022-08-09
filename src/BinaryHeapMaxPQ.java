
public class BinaryHeapMaxPQ<Key extends Comparable<Key>> {

    private Key[] pq;
    private int count;
    
    public BinaryHeapMaxPQ() {
        pq = (Key[]) new Comparable[1];
        count = 0;
    }
    
    
    public boolean isEmpty() {
        return count == 0;
    }

    
    public void insert(Key key) {
        if (count == pq.length - 1) {
            resize(pq.length * 2);
        }
        pq[++count] = key;
        swim(count);
    }
    
    
    private void swim(int position) {
        // While position isn't at top and there are parent values less than child value at position
        while (position > 1 && less(position/2, position)) {
            // Exchange larger child with smaller parent, and make the new larger parent the position value, continue while loop
            exch(position/2, position);
            position = position/2;
        }
    }
    
    
    public int size() {
        int size = count;
        return size;
    }
    
    
    public Key delMax() {
        Key max = pq[1];
        exch(1, count--);
        sink(1);
        pq[count+1] = null; //It's count+1 because we already deprecated count by 1 above
        // Resize array if it's 1/4 full
        if (count > 0 && count == (pq.length - 1) / 4) {
            resize(pq.length/2);
        }
        return max;
    }
    
    
    private void sink(int position) {
        // While there are still child nodes, keep verifying there are no greater values in child nodes
        while (2*position <= count) {
            int child = 2*position; // get first child
            // if 2nd child is larger, then make child equal to that
            if (child < count && less(child, child+1)) {
                child++;
            }
            // If parent isn't less than child, then no need to sink further
            if (!less(position, child)) {
                break;
            }
            // Else exchange parent with child, and make the child the new parent, and continue to sink in while loop
            exch(position, child);
            position = child;
        }
    }
    
    
    private void resize(int newSize) {
        Key[] pqCopy = (Key[]) new Comparable[newSize];
        for (int i = 1; i <= count; i++) {
            pqCopy[i] = pq[i];
        }
        pq = pqCopy;
    }
    private boolean less(int a, int b) {
        // This less passes the Array, and positions to be compared as ints. Others just pass the two objects from the array
        return pq[a].compareTo(pq[b]) < 0;
    }
    
    
    private void exch(int a, int b) {
        Key temp = pq[a];
        pq[a] = pq[b];
        pq[b] = temp;
    }
    
    
    public static void main(String[] args) {
        BinaryHeapMaxPQ<String> a = new BinaryHeapMaxPQ<String>();
        a.insert("q");
        a.insert("w");
        a.insert("e");
        a.insert("r");
        a.insert("t");
        a.insert("y");
        a.insert("u");
        a.insert("i");
        a.insert("o");
        a.insert("p");
        a.insert("a");
        a.insert("s");
        a.insert("d");
        a.insert("f");
        a.insert("g");
        a.insert("h");
        a.insert("j");
        a.insert("k");
        a.insert("l");
        a.insert("z");
        a.insert("x");
        a.insert("c");
        a.insert("v");
        a.insert("b");
        a.insert("n");
        a.insert("m");
        System.out.println(a.size());
        while (!a.isEmpty()){
            System.out.println(a.delMax());
        }
        System.out.println(a.size());
    }
}
