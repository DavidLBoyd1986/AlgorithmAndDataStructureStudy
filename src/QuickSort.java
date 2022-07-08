import edu.princeton.cs.algs4.StdRandom;


public class QuickSort {

    public static void quickSort(Comparable[] a) {
        // Have to randomly shuffle array to prevent worst case run-times
        StdRandom.shuffle(a);
        sort(a, 0, a.length-1);
    }
    
    private static void sort(Comparable[] a, int low, int high) {
        // If array is size 1 or less, no need to sort
        if (high <= low) return;
        // partition array so left half is < partition and right half is > partition.
        int partition = partition(a, low, high);
        // Recurse down until base case above is reached, sorting all the way
        sort(a, low, partition-1);
        sort(a, partition+1, high);
        
    }
    
    private static int partition(Comparable[] a, int low, int high) {
        // Set left and right indexes to start at each low and high ends
        int lowIndex = low; // low is partition, index is incremented before being analyzed
        int highIndex = high + 1; // Add 1 because it's decremented before being analyzed
        // Continue until done
        while (true) {
            // Go until the left index finds an item that is greater than the partition
            while (less(a[++lowIndex], a[low])) {
                // left index reached end of array, sort finished.
                if (lowIndex == high) {
                    break;
                }
            }
            // Go until the right index finds an item that is lesser than the partition
            while (less(a[low], a[--highIndex])) {
                // right index reached start of array, sort finished
                if (highIndex == low) {
                    break;
                }
            }
            // If pointers cross partition() is finished
            if (lowIndex >= highIndex) { break; }
            // Swap the items at the low and high indexes
            exchange(a, lowIndex, highIndex);            
        }
        // Swap partitioning item into partition spot so < are left and > are right of it
        exchange(a, low, highIndex);
        // Return index of item known to be in place (partition item index)
        return highIndex;
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
        quickSort(a);
        for (String i : a) {
            System.out.print(i);
        }
    }
}
