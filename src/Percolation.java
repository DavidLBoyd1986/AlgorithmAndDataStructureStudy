import edu.princeton.cs.algs4.WeightedQuickUnionUF;
import edu.princeton.cs.algs4.QuickFindUF;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class Percolation {

    private WeightedQuickUnionUF grid;
    private boolean[] gridStatus;
    private int rowSize;
    private int gridSize;
    private int numOpenSites;
    
    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        grid = new WeightedQuickUnionUF(n*n + 2);
        gridStatus = new boolean[n*n + 2];
        rowSize = n;
        gridSize = n*n;
        numOpenSites = 0;
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        gridStatus[convertRowCol(row, col)] = true;
        connectSites(row, col);
        numOpenSites++;
        return;
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        return gridStatus[convertRowCol(row, col)];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        int rootCell = grid.find(convertRowCol(row, col));
        return (rootCell <= gridSize && gridStatus[rootCell]);
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return numOpenSites;
    }

    // does the system percolate?
    public boolean percolates() {
        for (int r = 1; r <= rowSize; r++) {
            if (this.isFull(5, r)) {
                return true;
            }
        }
        return false;
    }
    
    // convert (row, int) into just (row)
    private int convertRowCol(int row, int col) {
        return (((row-1) * rowSize) + col);
    }
    
    // connect site to other nearby open sites
    private void connectSites(int row, int col) {
        if (row > 1) {
            if (gridStatus[convertRowCol(row-1, col)]) {
                grid.union(convertRowCol(row, col), convertRowCol(row-1, col));
                return;
            }
        }
        if (col > 1) {
            if (gridStatus[convertRowCol(row, col-1)]) {
                grid.union(convertRowCol(row, col), convertRowCol(row, col-1));
                return;
            }
        }
        if (col < 5) {
            if (gridStatus[convertRowCol(row, col+1)]) {
                grid.union(convertRowCol(row, col), convertRowCol(row, col+1));
                return;
            }
        }
        if (row < 5) {
            if (gridStatus[convertRowCol(row+1, col)]) {
                grid.union(convertRowCol(row, col), convertRowCol(row+1, col));
                return;
            }
        }
    }
    
    // returns grid representation with int values showing segments
    public String getGrid() {
        String output = "";
        output = output + ("[");
        for (int r = 1; r <= gridSize; r++) { 
            int row = grid.find(r);
            output = output + (" " + row + ", ");
            if ((r%(gridSize/rowSize)) == 0 ) { output = output + ("]" + "\n" + "["); }
        }
        return output;
    }

    public String toString() {  
        String output = "";
        output = output + ("[");
        for (int r = 1; r <= gridSize; r++) { 
            boolean row = gridStatus[r];
            output = output + (" " + row + ", ");
            if ((r%(gridSize/rowSize)) == 0 ) { output = output + ("]" + "\n" + "["); }
        }
        return output;
    }

    // test client (optional)
    public static void main(String[] args) {
        Percolation test = new Percolation(5);
        System.out.println("output:" + "\n" + test);
        System.out.println("--------------");
        
        System.out.println("output:" + "\n" + test.getGrid());
        System.out.println("--------------");
        
        test.open(1, 5);
        System.out.println("output:" + "\n" + test.getGrid());
        System.out.println("output:" + "\n" + test);
        System.out.println("--------------");
        
        test.open(2, 5);
        System.out.println("output:" + "\n" + test.getGrid());
        System.out.println("output:" + "\n" + test);
        System.out.println("--------------");
        
        test.open(3, 5);
        System.out.println("output:" + "\n" + test.getGrid());
        System.out.println("output:" + "\n" + test);
        System.out.println("--------------");
        
        test.open(4, 5);
        System.out.println("output:" + "\n" + test.getGrid());
        System.out.println("output:" + "\n" + test);
        System.out.println("--------------");
        
        test.open(1, 4);
        System.out.println("output:" + "\n" + test.getGrid());
        System.out.println("output:" + "\n" + test);
        System.out.println("--------------");
        
        test.open(1, 3);
        System.out.println("output:" + "\n" + test.getGrid());
        System.out.println("output:" + "\n" + test);
        System.out.println("--------------");
        
        test.open(1, 2);
        System.out.println("output:" + "\n" + test.getGrid());
        System.out.println("output:" + "\n" + test);
        System.out.println("--------------");
              
        test.open(1, 1);
        System.out.println("output:" + "\n" + test.getGrid());
        System.out.println("output:" + "\n" + test);
        System.out.println("--------------");

        test.open(2, 1);
        System.out.println("output:" + "\n" + test.getGrid());
        System.out.println("output:" + "\n" + test);
        System.out.println("--------------");
        
        test.open(4, 1);
        System.out.println("output:" + "\n" + test.getGrid());
        System.out.println("output:" + "\n" + test);
        System.out.println("--------------");
        
        test.open(5, 1);
        System.out.println("output:" + "\n" + test.getGrid());
        System.out.println("output:" + "\n" + test);
        System.out.println("--------------");
        
        test.open(4, 4);
        System.out.println("output:" + "\n" + test.getGrid());
        System.out.println("output:" + "\n" + test);
        System.out.println("--------------");
        
//        test.open(5, 4);
//        System.out.println("output:" + "\n" + test.getGrid());
//        System.out.println("output:" + "\n" + test);
//        System.out.println("--------------");

        System.out.println(test.numberOfOpenSites());
     
        System.out.println(test.isFull(1, 2));
        
        System.out.println(test.percolates());

        test.open(3, 1);
        System.out.println("output:" + "\n" + test.getGrid());
        System.out.println("output:" + "\n" + test);
        System.out.println("--------------");
        
        System.out.println(test.isFull(4, 4));
        System.out.println("percolates: " + test.percolates());
        
        System.out.println(test.isFull(5, 3));
    }
    
}