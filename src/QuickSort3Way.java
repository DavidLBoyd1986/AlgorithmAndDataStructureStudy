import edu.princeton.cs.algs4.*;


public class QuickSort3Way {
    
    
    public static void quickSortThreeWay(Comparable[] array) {
        // Shuffle to improve performance
        StdRandom.shuffle(array);
        sort(array, 0, array.length-1);
    }
    
    
    private static void sort(Comparable[] array, int low, int high) {
        // Base Case
        if (low >= high) {
            return;
        }
        // LessThan, GreaterThan, and Equal indexes;
        int lt = low; // Everything to the left of this index is <
        int gt = high; // Everything to the right of this index is >
        int eq = low+1; // Stays ahead of lt, and everything b/w lt and eq are equals
        Comparable partition = array[low];
        
        while (eq <= gt) {
            // Always compare eq to the partition
            int cmp = array[eq].compareTo(partition);
            if (cmp < 0) {
                exchange(array, lt++, eq++); // If eq is less, move it to the left of lt
            } else if (cmp > 0) {
                exchange(array, eq, gt--); // if eq is greater move it to the right of gt
            } else {
                eq++; // Else they're equals and just increment equals
            }
        }
        // Recurse further down the array; the equals elements aren't included in recursion
        sort(array, low, lt-1); // Recurse left (lt) half
        sort(array, gt+1, high); // Recurse right (gt) half
    }
    
    private static void exchange(Comparable[] a, int low_item, int high_item) {
        Comparable holdItem = a[low_item];
        a[low_item] = a[high_item];
        a[high_item] = holdItem;
    }
    
    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }
 

    public static void main(String[] args) {
        String[] a = new String[26];
        a[0] = "q";
        a[1] = "w";
        a[2] = "e";
        a[3] = "r";
        a[4] = "t";
        a[5] = "y";
        a[6] = "u";
        a[7] = "i";
        a[8] = "o";
        a[9] = "p";
        a[10] = "a";
        a[11] = "s";
        a[12] = "d";
        a[13] = "f";
        a[14] = "g";
        a[15] = "h";
        a[16] = "j";
        a[17] = "k";
        a[18] = "l";
        a[19] = "z";
        a[20] = "x";
        a[21] = "c";
        a[22] = "v";
        a[23] = "b";
        a[24] = "n";
        a[25] = "m";
        
        for (String i : a) {
            System.out.print(i);
        }
        System.out.println("");
        quickSortThreeWay(a);
        for (String i : a) {
            System.out.print(i);
        }
    }

}
