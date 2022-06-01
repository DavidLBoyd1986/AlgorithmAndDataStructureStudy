
public class MergeSort {

    
    public static void mergesort(Comparable[] main) {
        int array_length = main.length;
        int high = array_length - 1;
        // Need an extra array because merge sort can't sort in place
        Comparable[] aux = new Comparable[main.length];
        // this method will be recursively called in it's implementation until main is sorted
        sort(main, aux, 0, high);
    }
    
    private static void sort(Comparable[] main, Comparable[] aux, int low, int high) {    
        // Basecase - continue until array is size of 1
        if (high <= low) {
            return; }
        // Deprecate mid each time to reach basecase
        int mid = low + (high - low) / 2;
        // Deprecated mid becomes high, recurses until reach bottom left most base case
        sort(main, aux, low, mid);
        // Deprecated mid becomes low, called as recursion goes back up (continues function)
        sort(main, aux, mid+1, high); // gets the right half of an array segment
        // Once basecase is reached it merges the bottom two, then works it's way back up
        merge(main, aux, low, mid, high);
        
    }
    
    private static void merge(Comparable[] main, Comparable[] aux, int low, int mid, int high) {
        // Copy the section of main array being merged into aux array
        for (int k = low; k <= high; k++) {
            aux[k] = main[k];
        }
        // create increment variables (i) for both, left and right, halves of the array
        int left_side_i = low;
        int right_side_i = mid+1;
        // increment variable for main array, then compare left/right array values
        for (int main_i = low; main_i <= high; main_i++) {
            // If left side is done, take value from right side
            if (left_side_i > mid) {
                main[main_i] = aux[right_side_i++];}
            // If right side is done, take value from left side
            else if (right_side_i > high) {
                main[main_i] = aux[left_side_i++];}
            // If right side is less than left side, take right side
            else if (less(aux[right_side_i], aux[left_side_i])) {
                main[main_i] = aux[right_side_i++];}
            // else left side is less, so take left side
            else {main[main_i] = aux[left_side_i++];}
        }
    }
    
    public static boolean less(Comparable v, Comparable w) {
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
        mergesort(a);
        for (String i : a) {
            System.out.print(i);
        }

    }

}
