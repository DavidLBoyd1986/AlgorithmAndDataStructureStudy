import edu.princeton.cs.algs4.*;

public class QuickSelection {

    public static Comparable quickSelection(Comparable[] array, int key) {
        
        // Shuffle to guarantee improved performance
        StdRandom.shuffle(array);
        int low = 0;
        int high = array.length -1;
        // Always True: continues until the index is found and returned
        while (high > low) {
            // Partition array to see if the key index will be in the left or right array
            int partitionIndex = partition(array, low, high);
            // Repeat until the key isn't in the left or right array
            if (partitionIndex < key) {
                low = partitionIndex+1;
            } else if (partitionIndex > key) {
                high = partitionIndex-1;
            } else {
                // If key isn't on left or right half array, the partition is the key
                return array[key];
            }
        }
        // Since the partition will always be in it's correct position, just return array[key]
        return array[key];
        
    }

    private static int partition(Comparable[] array, int low, int high) {
        
        int lowIndex = low;
        int highIndex = high + 1;
        Comparable partition = array[low];
        
        while (true) {
            
            // Move left side index until a value larger than partition is found
            while (less(array[++lowIndex], partition)) {
                if (lowIndex == high) {
                    break;
                }
            }
            // Move right side index until a value less than partition is found
            while (less(partition, array[--highIndex])) {
                if (highIndex == low) {
                    break;
                }
            }
            if (lowIndex >= highIndex) {
                break;
            }
            // Swap indexes
            swap(array, lowIndex, highIndex);
        }
        // Swap partition into place
        swap(array, low, highIndex);
        return highIndex;
    }
    
    
    private static void swap(Comparable[] array, int low, int high) {
        Comparable holder = array[low];
        array[low] = array[high];
        array[high] = holder;
    }

    
    private static boolean less(Comparable a, Comparable b) {
        return a.compareTo(b) < 0;
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
        String result = (String) quickSelection(a, 3);
        System.out.println(result);
    }

}
