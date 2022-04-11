import edu.princeton.cs.algs4.WeightedQuickUnionUF;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class Percolation {

    public int[][] grid;
    public boolean[][] gridStatus;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        grid = new int[n][n];
        gridStatus = new boolean[n][n];
        int[] row = new int[n];
        boolean[] rowStatus = new boolean[n];

        for (int c = 0; c < n; c++) {
            row[c] = c;
            rowStatus[c] = false;
        }
        for (int r = 0; r < n; r++) {
            grid[r] = row.clone();
            gridStatus[r] = rowStatus.clone();
        }
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        gridStatus[row][col] = true;
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        return gridStatus[row][col];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        
    }

    // does the system percolate?
    public boolean percolates() {
        
    }
    
    // returns string representation of Percolation()
    public String toString() {
        String output = "";
        for (int r = 0; r < 5; r++) {
            for (int c = 0; c < 5; c++) {
                output = output + (gridStatus[r][c] + " ");
            }
            output = output + "\n";
        }
        return output;
    }

    // test client (optional)
    public static void main(String[] args) {
        Percolation test = new Percolation(5);
        System.out.println("output:" + "\n" + test);
        System.out.println("--------------");
        
        test.open(4, 4);
        
        System.out.println("output:" + "\n" + test);
        System.out.println("--------------");
    }

}
