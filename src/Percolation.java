import edu.princeton.cs.algs4.WeightedQuickUnionUF;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class Percolation {

    public int[][][] grid;
    public boolean[][] gridStatus;
    public int gridSize;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        grid = new int[n][n][2];
        gridStatus = new boolean[n][n];
        gridSize = n;
        
        for (int r = 0; r < n; r++) {
            // Recreate these each loop or each row will be a shallow copy
            int[][] row = new int[n][2];
            boolean[] rowStatus = new boolean[n];
            for (int c = 0; c < n; c++) {
                row[c][0] = r;
                row[c][1] = c;
                rowStatus[c] = false;
            }
            grid[r] = row;
            gridStatus[r] = rowStatus;
        }
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        gridStatus[row][col] = true;
        // Add code to connect with nearby open segments (union)
        if (row > 0) {
            System.out.println(gridStatus[row-1][col]);
            if (gridStatus[row-1][col]) {
                System.out.println("test");
                grid[row][col][0] = grid[row-1][col][0];
                grid[row][col][1] = grid[row-1][col][1];
                return;
            }
        }
        if (col > 0) {
            if (gridStatus[row][col-1]) {
                grid[row][col][0] = grid[row][col-1][0];
                grid[row][col][1] = grid[row][col-1][1];
                return;
            }
        }
        if (col < 4) {
            if (gridStatus[row][col+1]) {
                grid[row][col][0] = grid[row][col+1][0];
                grid[row][col][1] = grid[row][col+1][1];
                return;
            }
        }
        if (row < 4) {
            if (gridStatus[row+1][col]) {
                grid[row][col][0] = grid[row+1][col][0];
                grid[row][col][1] = grid[row+1][col][1];
                return;
            }
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        return gridStatus[row][col];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        int startRow = row;
        int startCol = col;
        int nextRow = grid[row][col][0];
        int nextCol = grid[row][col][1];
        while (startRow != nextRow && startCol != nextCol) {
            startRow = nextRow;
            startCol = nextCol;
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
                if (gridStatus[r][c]) {
                    openSites++;
                }
            }
        }
        return openSites;
    }

    // does the system percolate?
    public boolean percolates() {
        for (int c = 0;c < gridSize; c++) {
            if (this.isFull(gridSize, c)) {
                return true;
            }
        }
        return false;
    }
    
    // returns the int[] stored in the site
    public int[] getSite(int row, int col) {
        int[] siteData = new int[2];
        siteData[0] = grid[row][col][0];
        siteData[1] = grid[row][col][1];
        return siteData;
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
        
        test.open(0, 4);
        System.out.println("output:" + "\n" + test.getGrid());
        System.out.println("output:" + "\n" + test);
        System.out.println("--------------");
        
        test.open(1, 4);
        System.out.println("output:" + "\n" + test.getGrid());
        System.out.println("output:" + "\n" + test);
        System.out.println("--------------");
        
        test.open(2, 4);
        System.out.println("output:" + "\n" + test.getGrid());
        System.out.println("output:" + "\n" + test);
        System.out.println("--------------");
        
        test.open(3, 4);
        System.out.println("output:" + "\n" + test.getGrid());
        System.out.println("output:" + "\n" + test);
        System.out.println("--------------");
        
        test.open(0, 3);
        System.out.println("output:" + "\n" + test.getGrid());
        System.out.println("output:" + "\n" + test);
        System.out.println("--------------");
        
        test.open(0, 2);
        System.out.println("output:" + "\n" + test.getGrid());
        System.out.println("output:" + "\n" + test);
        System.out.println("--------------");
        
        test.open(0, 1);
        System.out.println("output:" + "\n" + test.getGrid());
        System.out.println("output:" + "\n" + test);
        System.out.println("--------------");
        
        System.out.println(test.numberOfOpenSites());
        
        test.open(3, 3);
        System.out.println("output:" + "\n" + test.getGrid());
        System.out.println("output:" + "\n" + test);
        System.out.println("--------------");
        
        test.open(4, 3);
        System.out.println("output:" + "\n" + test.getGrid());
        System.out.println("output:" + "\n" + test);
        System.out.println("--------------");
        
        System.out.println(test.isFull(4, 1));
        
        System.out.println(test.percolates());
    }

}
