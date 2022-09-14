
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class PuzzleCheckerUpdate {

    public static void main(String[] args) {

        String[] files = new String[3];
        files[0] = "C:\\Users\\David\\Desktop\\IT_Coding\\Java\\Princeton_Class\\Code\\Inputs\\8puzzleSubset\\puzzle00.txt";
        files[1] = "C:\\Users\\David\\Desktop\\IT_Coding\\Java\\Princeton_Class\\Code\\Inputs\\8puzzleSubset\\puzzle01.txt";
        files[2] = "C:\\Users\\David\\Desktop\\IT_Coding\\Java\\Princeton_Class\\Code\\Inputs\\8puzzleSubset\\puzzle02.txt";

        // for each command-line argument
        for (String filename : files) {

            // read in the board specified in the filename
            In in = new In(filename);
            int n = in.readInt();
            int[][] tiles = new int[n][n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    tiles[i][j] = in.readInt();
                }
            }

            // solve the slider puzzle
            Board initial = new Board(tiles);
            Solver solver = new Solver(initial);
            StdOut.println(filename + ": " + solver.moves());
        }
    }
}