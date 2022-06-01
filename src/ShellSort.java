
public class ShellSort {

    public static void sort(Comparable[] a) {
        
        // Set Iteration value as large as it can be, which is up to 1/3 of the array length
        int array_length = a.length; 
        int iteration_step = 1;
        while (iteration_step < array_length/3) {
            iteration_step = (iteration_step * 3) + 1;
        }
        // Sort until the iteration step is equal to 1, so it performs a final Insertion Sort
        while (iteration_step >= 1) {
            // Start loop at iteration step so you swap starting with value at 0
            for (int i = iteration_step; i < array_length; i++) {
                // Keep swapping values if their is a next iteration step
                // and the value at the next iteration step is higher
                for (int j = i;
                     // Two Conditionals in for loop which are described in comment above
                     j >= iteration_step && less(a[j], a[j-iteration_step]);
                     // 2nd conditional (less) could be moved to if statement in loop
                     j -= iteration_step){
                    exch(a, j, j-iteration_step);
                }
            }
            // deprecate the iteration_step to it's previous value,
            // and loop again until it gets back to 1
            iteration_step = iteration_step / 3;
        }       
    }
    
    public static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }
    
    public static void exch(Comparable[] a, int v, int w) {
        Comparable swap = a[v];
        a[v] = a[w];
        a[w] = swap;
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
