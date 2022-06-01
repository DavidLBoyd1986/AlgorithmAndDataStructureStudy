import edu.princeton.cs.algs4.StdRandom;


public class Shuffle {

    public static void shuffle(Object[] a) {
        int arrayLength = a.length;    
        for (int i = 0; i < arrayLength; i++) {
            int randomSwap = StdRandom.uniform(i+1);
            // below can be turned into function exch()
            Object swap = a[randomSwap];
            a[randomSwap] = a[i];
            a[i] = swap;
        }
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
        shuffle(a);
        for (String i : a) {
            System.out.print(i);
        }
    }

}
