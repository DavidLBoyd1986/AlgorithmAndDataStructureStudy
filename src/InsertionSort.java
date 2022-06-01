import java.util.Comparator;

public class InsertionSort {

    public static void sort(Comparable[] a) {
        int length_of_array = a.length;
        for (int i = 0; i < length_of_array; i++) {
            for (int j = i; j > 0; j--) {
                if (less(a[j], a[j-1])) {
                    exch(a, j-1, j);
                } else {
                    break;
                }
            }
        }
    }
    
    public static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }
    
    // is v < w ?
    private static boolean less(Comparator comparator, Object v, Object w) {
        return comparator.compare(v, w) < 0;
    }

    public static void exch(Comparable[] a, int v, int w) {
        Comparable holder = a[v];
        a[v] = a[w];
        a[w] = holder;
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

    }

}
