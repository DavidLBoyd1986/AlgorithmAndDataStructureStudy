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
        validate(row, col);
        gridStatus[convertRowCol(row, col)] = true;
        connectSites(row, col);
        numOpenSites++;
        return;
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        validate(row, col);
        return gridStatus[convertRowCol(row, col)];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        validate(row, col);
        int rootCell = grid.find(convertRowCol(row, col));
        return (rootCell <= rowSize && gridStatus[rootCell]);
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
    
    // validates that (row, col) are valid numbers
    private void validate(int row, int col) {
        if (row < 1 || row > rowSize)  {
            throw new IllegalArgumentException("row " + row +
                    " is not between 1 and " + rowSize);
        }
        if (col < 1 || col > rowSize) {
            throw new IllegalArgumentException("col " + col +
                    " is not between 1 and " + rowSize);
        }
    }
    
    // connect site to other nearby open sites
    private void connectSites(int row, int col) {
        if (row > 1 && gridStatus[convertRowCol(row-1, col)]) {
                grid.union(convertRowCol(row-1, col), convertRowCol(row, col));
                return;
            }
        if (col > 1 && gridStatus[convertRowCol(row, col-1)]) {
                grid.union(convertRowCol(row, col-1), convertRowCol(row, col));
                return;
            }
        if (col < 5 && gridStatus[convertRowCol(row, col+1)]) {
                grid.union(convertRowCol(row, col+1), convertRowCol(row, col));
                return;
            }
        if (row < 5 && gridStatus[convertRowCol(row+1, col)]) {
                grid.union(convertRowCol(row+1, col), convertRowCol(row, col));
                return;
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
//        Percolation test = new Percolation(5);
//        System.out.println("output:" + "\n" + test);
//        System.out.println("--------------");
//        
//        System.out.println("output:" + "\n" + test.getGrid());
//        System.out.println("--------------");
//        
//        test.open(1, 5);
//        System.out.println("output:" + "\n" + test.getGrid());
//        System.out.println("output:" + "\n" + test);
//        System.out.println("--------------");
//        
//        test.open(2, 5);
//        System.out.println("output:" + "\n" + test.getGrid());
//        System.out.println("output:" + "\n" + test);
//        System.out.println("--------------");
//        
//        test.open(3, 5);
//        System.out.println("output:" + "\n" + test.getGrid());
//        System.out.println("output:" + "\n" + test);
//        System.out.println("--------------");
//        
//        test.open(4, 5);
//        System.out.println("output:" + "\n" + test.getGrid());
//        System.out.println("output:" + "\n" + test);
//        System.out.println("--------------");
//        
//        test.open(1, 4);
//        System.out.println("output:" + "\n" + test.getGrid());
//        System.out.println("output:" + "\n" + test);
//        System.out.println("--------------");
//        
//        test.open(1, 3);
//        System.out.println("output:" + "\n" + test.getGrid());
//        System.out.println("output:" + "\n" + test);
//        System.out.println("--------------");
//        
//        test.open(1, 2);
//        System.out.println("output:" + "\n" + test.getGrid());
//        System.out.println("output:" + "\n" + test);
//        System.out.println("--------------");
//              
//        test.open(1, 1);
//        System.out.println("output:" + "\n" + test.getGrid());
//        System.out.println("output:" + "\n" + test);
//        System.out.println("--------------");
//
//        test.open(2, 1);
//        System.out.println("output:" + "\n" + test.getGrid());
//        System.out.println("output:" + "\n" + test);
//        System.out.println("--------------");
//        
//        test.open(4, 1);
//        System.out.println("output:" + "\n" + test.getGrid());
//        System.out.println("output:" + "\n" + test);
//        System.out.println("--------------");
//        
//        test.open(5, 1);
//        System.out.println("output:" + "\n" + test.getGrid());
//        System.out.println("output:" + "\n" + test);
//        System.out.println("--------------");
//        
//        test.open(4, 4);
//        System.out.println("output:" + "\n" + test.getGrid());
//        System.out.println("output:" + "\n" + test);
//        System.out.println("--------------");
//        
//        test.open(5, 4);
//        System.out.println("output:" + "\n" + test.getGrid());
//        System.out.println("output:" + "\n" + test);
//        System.out.println("--------------");
//
//        test.open(3, 1);
//        System.out.println("output:" + "\n" + test.getGrid());
//        System.out.println("output:" + "\n" + test);
//        System.out.println("--------------");
//        
//        System.out.println(test.isFull(4, 4));
//        System.out.println(test.isFull(5, 3));
//        System.out.println("percolates: " + test.percolates());
        
        Percolation test = new Percolation(5);
        System.out.println("output:" + "\n" + test);
        System.out.println("--------------");
        
        System.out.println("output:" + "\n" + test.getGrid());
        System.out.println("--------------");
        
        test.open(5, 3);
        System.out.println("output:" + "\n" + test.getGrid());
        System.out.println("output:" + "\n" + test);
        System.out.println("--------------");
        
        test.open(4, 3);
        System.out.println("output:" + "\n" + test.getGrid());
        System.out.println("output:" + "\n" + test);
        System.out.println("--------------");
        
        test.open(3, 3);
        System.out.println("output:" + "\n" + test.getGrid());
        System.out.println("output:" + "\n" + test);
        System.out.println("--------------");
        
        test.open(2, 3);
        System.out.println("output:" + "\n" + test.getGrid());
        System.out.println("output:" + "\n" + test);
        System.out.println("--------------");
        
        test.open(1, 3);
        System.out.println("output:" + "\n" + test.getGrid());
        System.out.println("output:" + "\n" + test);
        System.out.println("--------------");
        
        System.out.println(test.isFull(4, 4));
        System.out.println(test.isFull(5, 3));
        System.out.println("percolates: " + test.percolates());
    }
    
}