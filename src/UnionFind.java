
/**
 * 
 * @author David
 *
 * This is also known as a disjoint-sets data type
 */

public class UnionFind {

    private int[] id_array;
    private int[] segment_size;
    private int num_of_components;

    public UnionFind(int size) {
        id_array = new int[size];
        segment_size = new int[size];
        for (int i = 0; i < size; i++) {
            id_array[i] = i;
            segment_size[i] = 1;
        }
        num_of_components = size;
    }

    public int find(int site) {
        validate(site);
        while (id_array[site] != site) {
            // This is path compression, it sets this site to it's grandparent.
            id_array[site] = id_array[id_array[site]];
            site = id_array[site];
        }
        return site;
    }
    
    public boolean connected(int site_one, int site_two) {
        return (this.find(site_one) == this.find(site_two));
    }
    
    public void union(int site_one, int site_two) {
        int root_one = this.find(site_one);
        int root_two = this.find(site_two);
        if (root_one == root_two) {
            return;
        }
        if (segment_size[root_one] > segment_size[root_two]) {
            id_array[root_two] = root_one;
            segment_size[root_one] += segment_size[root_two];
            
        } else {
            id_array[root_one] = root_two;
            segment_size[root_two] += segment_size[root_one];
        }
        num_of_components--;
    }
    
    public int count() {
        return num_of_components;
    }
    
    // validate that p is a valid index
    private void validate(int site) {
        int n = id_array.length;
        if (site < 0 || site >= n) {
            throw new IllegalArgumentException("index " + site +
                    " is not between 0 and " + (n-1));  
        }
    }
    
    public void printArray() {
        for (int i : id_array) {
            System.out.print(i);
            System.out.print(" ");
        }
    }

    public static void main(String[] args) {
        UnionFind test = new UnionFind(20);
        test.union(0,5);
        test.union(1,5);
        test.union(2,5);
        test.union(3,6);
        test.union(4,7);
        test.union(5,8);
        test.union(6,9);
        test.union(11,5);
        System.out.println(test.find(5));
        System.out.println(test.find(0));
        System.out.println(test.find(1));
        System.out.println(test.find(2));
        System.out.println(test.find(11));
        test.printArray();
        System.out.println(" ");
        System.out.println(test.count());
        System.out.println(test.connected(0, 11));
        System.out.println(test.connected(0, 18));
        test.union(7,6);
        test.union(7,10);
        test.union(7,12);
        test.union(7,13);
        test.union(14,7);
        test.union(7,15);
        test.union(7,0);
        test.printArray();
        System.out.println(" ");
        System.out.println(test.connected(1, 11));
    }

}
