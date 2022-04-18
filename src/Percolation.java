import edu.princeton.cs.algs4.WeightedQuickUnionUF;
import edu.princeton.cs.algs4.QuickFindUF;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class Percolation {

    private WeightedQuickUnionUF grid;
    private boolean[] gridStatus;
    
    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        WeightedQuickUnionUF grid = new WeightedQuickUnionUF(n*n + 2);
        boolean[] gridStatus = new boolean[n*n + 2];
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        gridStatus[row * col] = true;
        connectSites(row, col);
        return;
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        return gridStatus[row * col];
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
    
    // connect site to other nearby open sites
    private void connectSites(int row, int col) {
        if (row > 1) {
            if (gridStatus[(row-1)*col]) {
                grid.union(row * col, (row-1)*col);
                return;
            }
        }
        if (col > 1) {
            if (gridStatus[row*(col-1)]) {
                grid.union(row * col, row*(col-1));
                return;
            }
        }
        if (col < 5) {
            if (gridStatus[row*(col+1)]) {
                grid.union(row * col, row*(col+1));
                return;
            }
        }
        if (row < 5) {
            if (gridStatus[(row+1)*col]) {
                grid.union(row * col, (row+1)*col);
                return;
            }
        }
    }

    // test client (optional)
    public static void main(String[] args) {
        
    }
    
}