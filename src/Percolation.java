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
    private int virtualTop;
    private int virtualBottom;
    
    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        grid = new WeightedQuickUnionUF(n*n + 2);
        gridStatus = new boolean[n*n + 2];
        rowSize = n;
        gridSize = n*n;
        numOpenSites = 0;
        virtualTop = 0;
        virtualBottom = gridSize + 1;
        gridStatus[virtualTop] = true;
        gridStatus[virtualBottom] = true;
        //connectSourceAndSink();
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        validate(row, col);
        if (isOpen(row, col)) {
            return;
        }
        gridStatus[convertRowCol(row, col)] = true;
        numOpenSites++;
        connectSites(row, col);
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
        return rootCell == virtualTop;
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return numOpenSites;
    }

    // does the system percolate?
    public boolean percolates() {
        return grid.find(virtualBottom) == virtualTop;
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
        
        if (row == 1) {
            grid.union(virtualTop, convertRowCol(row, col));
        }
        if (row == rowSize) {
            grid.union(virtualBottom, convertRowCol(row, col));
        }
        if (col > 1 && gridStatus[convertRowCol(row, col-1)]) {
                grid.union(convertRowCol(row, col), convertRowCol(row, col-1));
            }
        if (col < 5 && gridStatus[convertRowCol(row, col+1)]) {
                grid.union(convertRowCol(row, col), convertRowCol(row, col+1));
            }
        if (row > 1 && gridStatus[convertRowCol(row-1, col)]) {
            grid.union(convertRowCol(row, col), convertRowCol(row-1, col));
        }
        if (row < 5 && gridStatus[convertRowCol(row+1, col)]) {
                grid.union(convertRowCol(row, col), convertRowCol(row+1, col));
            }
    }
    
    // start from 0 and connect sites downward until get to the requested site
    private void connectSourceAndSink() {
        for (int c = 1; c <= rowSize; c++) {
            grid.union(0, c);
        }
        int finalRow = (gridSize - rowSize)+1;
//        for (int r = finalRow; r <= gridSize; r++) {
//            grid.union(gridSize+1, r);
//        }
        gridStatus[0] = true;
        gridStatus[gridSize+1] = true;
        return;
    }
    
    // returns grid representation with int values showing segments
    public String getGrid() {
        String output = "";
        output = output + ("[");
        for (int r = 1; r <= gridSize; r++) { 
            int row = grid.find(r);
            output = output + (" " + row + " ");
            if ((r%(gridSize/rowSize)) == 0 ) { output = output + ("]" + "\n" + "["); }
        }
        output = output + "\n" + "Source = [ " + grid.find(0) + " ]";
        output = output + "\n" + "Sink = [ " + grid.find(gridSize+1) + " ]";
        return output;
    }

    public String toString() {  
        String output = "";
        output = output + ("[");
        for (int r = 1; r <= gridSize; r++) { 
            boolean row = gridStatus[r];
            output = output + (" " + row + " ");
            if ((r%(gridSize/rowSize)) == 0 ) { output = output + ("]" + "\n" + "["); }
        }
        output = output + "\n" + "Source = [ " + gridStatus[0] + " ]";
        output = output + "\n" + "Sink = [ " + gridStatus[gridSize+1] + " ]";
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
        
        test.open(1, 3);
        System.out.println("output:" + "\n" + test.getGrid());
        System.out.println("output:" + "\n" + test);
        System.out.println("--------------");
        
        test.open(2, 3);
        System.out.println("output:" + "\n" + test.getGrid());
        System.out.println("output:" + "\n" + test);
        System.out.println("--------------");
        
        test.open(3, 3);
        System.out.println("output:" + "\n" + test.getGrid());
        System.out.println("output:" + "\n" + test);
        System.out.println("--------------");
        
        test.open(4, 3);
        System.out.println("output:" + "\n" + test.getGrid());
        System.out.println("output:" + "\n" + test);
        System.out.println("--------------");
        
        test.open(5, 3);
        System.out.println("output:" + "\n" + test.getGrid());
        System.out.println("output:" + "\n" + test);
        System.out.println("--------------");
        
        System.out.println(test.isFull(4, 4));
        System.out.println(test.isFull(5, 3));
        System.out.println("percolates: " + test.percolates());
    }
    
}