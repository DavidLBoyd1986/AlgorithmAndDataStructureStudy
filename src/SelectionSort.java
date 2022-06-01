import java.util.Comparator;

import edu.princeton.cs.algs4.StdRandom;

public class SelectionSort{

    public static void sort(Comparable[] a) {
        int arrayLength = a.length;
        for (int i = 0; i < arrayLength; i++) {
            int min = i;
            for (int j = i+1; j < arrayLength; j++) {
                if (less(a[j], a[min])) {
                    min = j;
                }
            }
            exch(a, i, min);
        }
    }
    
    // is v < w ?
    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    // is v < w ?
    private static boolean less(Comparator comparator, Object v, Object w) {
        return comparator.compare(v, w) < 0;
    }
    
    // exchange a[i] and a[j]
    private static void exch(Object[] a, int i, int j) {
        Object swap = a[i];
        a[i] = a[j];
        a[j] = swap;
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
        sort(a);
        for (String i : a) {
            System.out.print(i);
        }
        
        int[] int_array = new int[100];
        int pos = 0;
        for (int i = 1; i <= 100; i++) {
            int_array[pos++] = i;
        }
        StdRandom.shuffle(int_array);
        
        for (int item : int_array) {
            System.out.print(item);
        }
        
        System.out.println("");
        
        // Can't sort int array because it isn't an object that implements Comparable<T>. It's a primitive
        //sort(int_array);
        
        // String class implements Comparable<String> so Strings in an array can be compared
        
        
    }
}
