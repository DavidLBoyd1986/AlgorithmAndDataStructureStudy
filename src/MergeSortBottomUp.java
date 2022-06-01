
public class MergeSortBottomUp {

    
    public static void mergeSortBU(Comparable[] main) {    
        sort(main);
    }
    
    
    private static void sort(Comparable[] main) {
        int main_length = main.length;
        Comparable[] aux = new Comparable[main_length];
        //increment merge size: 2, 4, 8, 16... merge size is a full array: arrayHalf+arrayHalf
        for (int arrayHalf = 1; arrayHalf < main_length; arrayHalf = arrayHalf+arrayHalf) {
            // loop through array by merge size (mergeSize is arrayHalf+arrayHalf)
            for (int low = 0; low < main_length-arrayHalf; low += arrayHalf+arrayHalf) {
                //mid= current pos + arrayHalf-1
                //high= current pos + arrayHalf+arrayHalf-1 || end of array : whichever is smaller
                merge(main, aux, low, low+arrayHalf-1, Math.min(low+arrayHalf+arrayHalf-1, main_length-1));  
            }
        }
    }
    
    
    private static void merge(Comparable[] main, Comparable[] aux, int low, int mid, int high) {
        // Copy elements to aux array
        for (int i = low; i <= high; i++) {
            aux[i] = main[i];
        }
        // Set increment values for each half of aux array
        int left_side_i = low;
        int right_side_i = mid+1;
        // Start main increment value for main array
        for (int main_i = low; main_i <= high; main_i++) {
            // If left side of array is done, return value from right side
            if (left_side_i > mid) {
                main[main_i] = aux[right_side_i++];
            }
            // If right side of array is done, return value from left side
            else if (right_side_i > high) {
                main[main_i] = aux[left_side_i++];
            }
            // If right side is less than left side, return value from right side
            else if (less(aux[right_side_i], aux[left_side_i])) {
                main[main_i] = aux[right_side_i++];
            }
            // Else, left side is less or equal, return value from left side
            else {
                main[main_i] = aux[left_side_i++];
            }
        }
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
        mergeSortBU(a);
        for (String i : a) {
            System.out.print(i);
        }

    }
}
