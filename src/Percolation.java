import edu.princeton.cs.algs4.WeightedQuickUnionUF;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class Percolation {

    public int[][][] grid;
    public boolean[][] gridOpenStatus;
    public boolean[][] gridFullStatus;
    public int gridSize;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        grid = new int[n][n][2];
        gridOpenStatus = new boolean[n][n];
        gridFullStatus = new boolean[n][n];
        gridSize = n;    
        for (int r = 0; r < n; r++) {
            // Recreate these each loop or each row will be a shallow copy
            int[][] row = new int[n][2];
            boolean[] rowOpenStatus = new boolean[n];
            boolean[] rowFullStatus = new boolean[n];
            for (int c = 0; c < n; c++) {
                row[c][0] = r;
                row[c][1] = c;
                rowOpenStatus[c] = false;
                rowFullStatus[c] = false;
            }
            grid[r] = row;
            gridOpenStatus[r] = rowOpenStatus;
            gridFullStatus[r] = rowFullStatus;
        }
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        int inputRow = row - 1;
        int inputCol = col - 1;
        gridOpenStatus[inputRow][inputCol] = true;
        // Add code to connect with nearby open segments (union)
        connectSites(inputRow, inputCol);
        // Check if this makes that site full
        gridFullStatus[inputRow][inputCol] =
                this.isFull(inputRow+1, inputCol+1);
        // If that site is full find nearby sites that are open, but not full
        if (gridFullStatus[inputRow][inputCol]) {
            int[] farSite = findUnconnectedOpenSites(inputRow, inputCol);
            // If sites were found connect them to this full site.
            if (farSite[0] != inputRow || farSite[1] != inputCol) {
                inputRow = farSite[0];
                inputCol = farSite[1];
                connectSites(inputRow, inputCol);
            }
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        return gridOpenStatus[row-1][col-1];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        int startRow = row-1;
        int startCol = col-1;
        if (startRow == 0 && !gridOpenStatus[startRow][startCol]) {
            return false;
        }
        int nextRow = grid[startRow][startCol][0];
        int nextCol = grid[startRow][startCol][1];
        while (startRow != nextRow || startCol != nextCol) {
            connectSites(startRow, startCol);
            startRow = nextRow;
            startCol = nextCol;
//            nextRow = grid[startRow][startCol][0];
//            nextCol = grid[startRow][startCol][1];
        }
        if (startRow == 0) {
            return true;
        }
        return false;
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        int openSites = 0;
        for (int r = 0; r < gridSize; r++) {
            for (int c = 0; c < gridSize; c++) {
                if (gridOpenStatus[r][c]) {
                    openSites++;
                }
            }
        }
        return openSites;
    }

    // does the system percolate?
    public boolean percolates() {
        for (int c = 1;c <= gridSize; c++) {
            if (this.isFull(gridSize, c)) {
                return true;
            }
        }
        return false;
    }
    
    // returns the int[] stored in the site
    public int[] getSite(int row, int col) {
        int[] siteData = new int[2];
        siteData[0] = grid[row-1][col-1][0];
        siteData[1] = grid[row-1][col-1][1];
        return siteData;
    }
    
    // connect site to other nearby open sites
    private void connectSites(int row, int col) {
        if (row > 0) {
            if (gridOpenStatus[row-1][col]) {
                grid[row][col][0] = grid[row-1][col][0];
                grid[row][col][1] = grid[row-1][col][1];
                return;
            }
        }
        if (col > 0) {
            if (gridOpenStatus[row][col-1]) {
                grid[row][col][0] = grid[row][col-1][0];
                grid[row][col][1] = grid[row][col-1][1];
                return;
            }
        }
        if (col < 4) {
            if (gridOpenStatus[row][col+1]) {
                grid[row][col][0] = grid[row][col+1][0];
                grid[row][col][1] = grid[row][col+1][1];
                return;
            }
        }
        if (row < 4) {
            if (gridOpenStatus[row+1][col]) {
                grid[row][col][0] = grid[row+1][col][0];
                grid[row][col][1] = grid[row+1][col][1];
                return;
            }
        }
    }
    
    // find unconnected open sites around this site, return farthest open site
    private int[] findUnconnectedOpenSites(int row, int col) {
        boolean stillUnconnectedSites = true;
        int[] farthestUnconnectedSite = new int[2];
        while (stillUnconnectedSites) {
            if (row < 4) {
                if ((gridOpenStatus[row+1][col] && !gridFullStatus[row+1][col]) && (
                    grid[row+1][col][0] == grid[row+1][col][0] &&
                    grid[row+1][col][1] == grid[row+1][col][1])) {  
                    row++;
                    continue;
                }
            }
            if (col > 0) {
                if ((gridOpenStatus[row][col-1] && !gridFullStatus[row][col-1]) && (
                    grid[row][col-1][0] == grid[row][col-1][0] &&
                    grid[row][col-1][1] == grid[row][col-1][1])) {
                    col--;
                    continue;
                }
            }
            if (col < 4) {
                if ((gridOpenStatus[row][col+1] && !gridFullStatus[row][col+1]) && (
                    grid[row][col+1][0] == grid[row][col+1][0] &&
                    grid[row][col+1][1] == grid[row][col+1][1])) {
                    col++;
                    continue;
                }
            }
            if (row > 0) {
                if ((gridOpenStatus[row-1][col] && !gridFullStatus[row-1][col]) &&
                        (grid[row-1][col][0] == grid[row-1][col][0] &&
                         grid[row-1][col][1] == grid[row-1][col][1])) {
                    row--; 
                    continue;
                }
            }
            farthestUnconnectedSite[0] = row;
            farthestUnconnectedSite[1] = col;
            stillUnconnectedSites = false;
        }
        return farthestUnconnectedSite;
    }
    
    // returns string representation with Boolean values showing open status
    public String toString() {
        String output = "";
        for (int r = 0; r < 5; r++) {
            for (int c = 0; c < 5; c++) {
                output = output + (gridOpenStatus[r][c] + " ");
            }
            output = output + "\n";
        }
        return output;
    }

    // returns grid representation with int values showing segments
    public String getGrid() {
        String output = "";       
        for (int r = 0; r < 5; r++) {
            for (int c = 0; c < 5; c++) {
                int row = grid[r][c][0];
                int col = grid[r][c][1];
                output = output + ("[" + row + "," + col + "]");
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
        
//        test.open(1, 3);
//        System.out.println("output:" + "\n" + test.getGrid());
//        System.out.println("output:" + "\n" + test);
//        System.out.println("--------------");
        
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
        System.out.println(test.percolates());
    }

}
