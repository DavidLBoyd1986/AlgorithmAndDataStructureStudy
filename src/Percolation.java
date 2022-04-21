import edu.princeton.cs.algs4.WeightedQuickUnionUF;


public class Percolation {

    private final WeightedQuickUnionUF grid;
    private final WeightedQuickUnionUF gridFull;
    private boolean[] gridStatus;
    private final int rowSize;
    private final int gridSize;
    private final int virtualTop;
    private final int virtualBottom;
    private int numOpenSites;
    
    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("n can't be less than 1.");
        }
        grid = new WeightedQuickUnionUF(n*n + 2);
        gridFull = new WeightedQuickUnionUF(n*n + 1);
        gridStatus = new boolean[n*n + 2];
        rowSize = n;
        gridSize = n*n;
        numOpenSites = 0;
        virtualTop = 0;
        virtualBottom = gridSize + 1;
        gridStatus[virtualTop] = true;
        gridStatus[virtualBottom] = true;
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
        int rootCell = gridFull.find(convertRowCol(row, col));
        return rootCell == gridFull.find(virtualTop);
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return numOpenSites;
    }

    // does the system percolate?
    public boolean percolates() {
        return grid.find(virtualBottom) == grid.find(virtualTop);
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
            gridFull.union(virtualTop, convertRowCol(row, col));
        }
        if (row == rowSize) {
            grid.union(virtualBottom, convertRowCol(row, col));
        }
        if (col > 1 && gridStatus[convertRowCol(row, col-1)]) {
            grid.union(convertRowCol(row, col), convertRowCol(row, col-1));
            gridFull.union(convertRowCol(row, col), convertRowCol(row, col-1));
            }
        if (col < rowSize && gridStatus[convertRowCol(row, col+1)]) {
            grid.union(convertRowCol(row, col), convertRowCol(row, col+1));
            gridFull.union(convertRowCol(row, col), convertRowCol(row, col+1));
            }
        if (row > 1 && gridStatus[convertRowCol(row-1, col)]) {
            grid.union(convertRowCol(row, col), convertRowCol(row-1, col));
            gridFull.union(convertRowCol(row, col), convertRowCol(row-1, col));
        }
        if (row < rowSize && gridStatus[convertRowCol(row+1, col)]) {
            grid.union(convertRowCol(row, col), convertRowCol(row+1, col));
            gridFull.union(convertRowCol(row, col), convertRowCol(row+1, col));
            }
    }

    public String toString() {  
        StringBuilder output = new StringBuilder();
        output.append("\nSource = [ ");
        output.append(gridStatus[0]);
        output.append(" ]\n");
        output.append("[");
        for (int r = 1; r <= gridSize; r++) { 
            boolean row = gridStatus[r];
            output.append(" ");
            output.append(row);
            output.append(" ");
            if ((r % (gridSize/rowSize)) == 0) {
                output.append("]\n");
                if (r != gridSize) {
                    output.append("[");
                }
            }
        }
        output.append("Sink = [ ");
        output.append(gridStatus[gridSize+1]);
        output.append(" ]");
        return output.toString();
    }

    // returns grid representation with int values showing segments
  private String getGrid() {
      StringBuilder output = new StringBuilder();
      output.append("\nSource = [ ");
      output.append(grid.find(0));
      output.append(" ]\n");
      output.append("[");
      for (int r = 1; r <= gridSize; r++) { 
          int row = grid.find(r);
          output.append(" ");
          output.append(row);
          output.append(" ");
          if ((r % (gridSize/rowSize)) == 0) {
              output.append("]\n");
              if (r != gridSize) {
                  output.append("[");
              }
          }
      }
      output.append("Sink = [ ");
      output.append(grid.find(gridSize+1));
      output.append(" ]");
      return output.toString();
  }

    // test client (optional)
    public static void main(String[] args) {
//        Percolation test = new Percolation(5);     
//        test.open(5, 3);
//        System.out.println("output:" + "\n" + test.getGrid());
//        System.out.println("output:" + "\n" + test);
//        System.out.println("--------------");
//        
//        test.open(2, 3);
//        System.out.println("output:" + "\n" + test.getGrid());
//        System.out.println("output:" + "\n" + test);
//        System.out.println("--------------");
//        
//        test.open(3, 3);
//        System.out.println("output:" + "\n" + test.getGrid());
//        System.out.println("output:" + "\n" + test);
//        System.out.println("--------------");
//        
//        test.open(4, 3);
//        System.out.println("output:" + "\n" + test.getGrid());
//        System.out.println("output:" + "\n" + test);
//        System.out.println("--------------");
//        
//        test.open(1, 3);
//        System.out.println("output:" + "\n" + test.getGrid());
//        System.out.println("output:" + "\n" + test);
//        System.out.println("--------------");
//        
//        System.out.println(test.isFull(4, 4));
//        System.out.println(test.isFull(5, 3));
//        System.out.println("percolates: " + test.percolates());
    }
}