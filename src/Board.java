import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import edu.princeton.cs.algs4.In;

import java.lang.Math;

public class Board {
    
    private final int size;
    private final int[][] board;

    // create a board from an n-by-n array of tiles,
    // where tiles[row][col] = tile at (row, col)
    public Board(int[][] tiles) {
        size = tiles[0].length;
        board = new int[size][size];
        for (int row = 0; row < size; row++) {
            for (int column = 0; column < size; column++) {
                board[row][column] = tiles[row][column];
            }
        }
    }
                                           
    // string representation of this board
    public String toString() {
        StringBuilder output = new StringBuilder();
        output.append(size + "\n");
        for (int r = 0; r < size; r++) {
            output.append(" ");
            for (int c = 0; c < size; c++) {
                if (String.valueOf(board[r][c]).length() == 1) {
                    output.append(" ");
                }
                output.append(board[r][c]);
                output.append(" ");
            }
                output.append("\n");
        }
        return output.toString();
    }

    // board dimension n
    public int dimension() {
        int dimension = size;
        return dimension;
    }

    // number of tiles out of place
    public int hamming() {
        int validPosition = 1;
        int outOfPosition = 0;
        for (int r = 0; r < size; r++) {
            for (int c = 0; c < size; c++) {
                // If not the blank spot and not a valid position
                if (board[r][c] != 0 && board[r][c] != validPosition) {
                    outOfPosition++;
                }
                validPosition++;
                // If at last spot of board, break
                if (r == size - 1 && c == size -1) {
                    break;
                }
            }
        }
        return outOfPosition;
    }

    // sum of Manhattan distances between tiles and goal
    public int manhattan() {
        // For every position in board [r][c] count how far the value is from its position
        int position = 0;
        int manhattanCount = 0;
        for (int r = 0; r < size; r++) {
            for (int c = 0; c < size; c++) {
                int tileValue = board[r][c];
                // skip the blank space
                if (tileValue == 0) {
                    position++;
                    continue;
                }
                // If tile == position, continue; else, add the distance to manhattanCount
                if (tileValue == position+1) {
                    position++;
                } else {
                    // Get row position of the tile value
                    int valueRow;
                    if (tileValue%size == 0) {
                        valueRow = (tileValue/size)-1;
                    } else {
                        valueRow = Math.abs(tileValue/size);
                    }
                    // Get column position of the tile value
                    int valueColumn = Math.abs(tileValue-(size*valueRow))-1; 
                    // Calculate the difference, and add it to manhattanCount
                    int rowDifference = Math.abs(r - valueRow);
                    int colDifference = Math.abs(c - valueColumn);
                    int totalDifference = rowDifference + colDifference;
                    manhattanCount += totalDifference;
                    position++;
                }
            }
        }
        return manhattanCount;
    }

    // is this board the goal board?
    public boolean isGoal() {
        return hamming() == 0;
    }

    // does this board equal y?
    public boolean equals(Object y) {
        if (y == this) {
            return true;
        }
        if (y == null) {
            return false;
        }
        if (!(y instanceof Board)) {
            return false;
        }
        Board other = (Board) y;
        if (other.size != size) {
            return false;
        }
        // This might have to be changed to actually go through and compare each int in the arrays
        if (Arrays.deepEquals(board, other.getTiles())) {
            return true;
        }
        return false;
    }

    // all neighboring boards
    public Iterable<Board> neighbors() {
        ArrayList<Board> neighborBoards = new ArrayList<Board>();
        int[] blankPosition = findBlank();
        int blankRow = blankPosition[0];
        int blankCol = blankPosition[1];
        if (blankRow != 0) {
            Board copiedBoard = this.copyBoard();
            int[][] newBoard = copiedBoard.getTiles();
            newBoard[blankRow][blankCol] = board[blankRow-1][blankCol]; //Copy value to blank
            newBoard[blankRow-1][blankCol] = 0; //Make swapped position blank
            Board northNeighbor = new Board(newBoard);
            neighborBoards.add(northNeighbor);
        }
        if (blankRow != size - 1) {
            Board copiedBoard = this.copyBoard();
            int[][] newBoard = copiedBoard.getTiles();
            newBoard[blankRow][blankCol] = board[blankRow+1][blankCol]; //Copy value to blank
            newBoard[blankRow+1][blankCol] = 0; //Make swapped position blank
            Board southNeighbor = new Board(newBoard);
            neighborBoards.add(southNeighbor);
        }
        if (blankCol != 0) {
            Board copiedBoard = this.copyBoard();
            int[][] newBoard = copiedBoard.getTiles();
            newBoard[blankRow][blankCol] = board[blankRow][blankCol-1]; //Copy value to blank
            newBoard[blankRow][blankCol-1] = 0; //Make swapped position blank
            Board westNeighbor = new Board(newBoard);
            neighborBoards.add(westNeighbor);
        }
        if (blankCol != size - 1) {
            Board copiedBoard = this.copyBoard();
            int[][] newBoard = copiedBoard.getTiles();
            newBoard[blankRow][blankCol] = board[blankRow][blankCol+1]; //Copy value to blank
            newBoard[blankRow][blankCol+1] = 0; //Make swapped position blank
            Board eastNeighbor = new Board(newBoard);
            neighborBoards.add(eastNeighbor);
        }
        return neighborBoards;

    }
    
    // a board that is obtained by exchanging any pair of tiles
    public Board twin() {
        Board copiedBoard = this.copyBoard();
        int[][] newBoard = copiedBoard.getTiles();
        //Try to swap last 2 elements left and right, if they aren't blank
        int[] blankPosition = findBlank();
        int endPosition = size-1;
        if (blankPosition[0] != endPosition) {
            int leftValue = newBoard[endPosition][endPosition-1];
            int rightValue = newBoard[endPosition][endPosition];
            int tempValue = leftValue;
            newBoard[endPosition][endPosition-1] = rightValue;
            newBoard[endPosition][endPosition] = tempValue;
        // If the blank position is in the last row, swap elements in the 2nd to last row
        } else {
            int leftValue = newBoard[endPosition-1][endPosition-1];
            int rightValue = newBoard[endPosition-1][endPosition];
            int tempValue = leftValue;
            newBoard[endPosition-1][endPosition-1] = rightValue;
            newBoard[endPosition-1][endPosition] = tempValue;
        }
        Board twinBoard = new Board(newBoard);
        return twinBoard;
    }
    
    // Return arrays in board, to compare for equality
    private int[][] getTiles() {
        int[][] tiles = new int[size][size];
        for (int row = 0; row < size; row++) {
            for (int column = 0; column < size; column++) {
                tiles[row][column] = board[row][column];
            }
        }
        return tiles;
    }

    // copy the board, and return another Board with the same tile positions
    private Board copyBoard() {
        Board newBoard = new Board(this.getTiles());
        return newBoard;
    }
    
    // finds Blank spot
    private int[] findBlank() {
        int[] blankPosition = new int[2];
        for (int r = 0; r < size; r++) {
            for (int c = 0; c < size; c++) {
                if (board[r][c] == 0) {
                    blankPosition[0] = r;
                    blankPosition[1] = c;
                    break;
                }
            }
        }
        return blankPosition;
    }

    // unit testing (not graded)
    public static void main(String[] args) {
        // Test a finished board
        int size = 5;
        int[][] testTiles = new int[size][size];
        int position = 1;
        for (int r = 0; r < size; r++) {
            for (int c = 0; c < size; c++) {
                testTiles[r][c] = position;
                position++;
            }
        }
        testTiles[size-1][size-1] = 0;
        
        Board testBoard = new Board(testTiles);
        System.out.println(testBoard.toString());
        System.out.println("Hamming:");
        System.out.println(testBoard.hamming());
        System.out.println("Manhattan");
        System.out.println(testBoard.manhattan());
        System.out.println("Dimension");
        System.out.println(testBoard.dimension());
        System.out.println("isGoal");
        System.out.println(testBoard.isGoal());
        System.out.println("Twin");
        Board twinBoard = testBoard.twin();
        System.out.println(testBoard.toString());
        System.out.println("Equals");
        System.out.println(testBoard.equals(twinBoard));
        System.out.println("neighbors");
        Iterable<Board> neighborBoards = testBoard.neighbors();
        for (Board board : neighborBoards) {
            System.out.println(board.toString());
        }
        
        String[] files = new String[6];
        files[0] = "C:\\Users\\David\\Desktop\\IT_Coding\\Java\\Princeton_Class\\Code\\Inputs\\8puzzleSubset\\puzzle00.txt";
        files[1] = "C:\\Users\\David\\Desktop\\IT_Coding\\Java\\Princeton_Class\\Code\\Inputs\\8puzzleSubset\\puzzle01.txt";
        files[2] = "C:\\Users\\David\\Desktop\\IT_Coding\\Java\\Princeton_Class\\Code\\Inputs\\8puzzleSubset\\puzzle02.txt";
        files[3] = "C:\\Users\\David\\Desktop\\IT_Coding\\Java\\Princeton_Class\\Code\\Inputs\\8puzzleSubset\\puzzle2x2-unsolvable1.txt";
        files[4] = "C:\\Users\\David\\Desktop\\IT_Coding\\Java\\Princeton_Class\\Code\\Inputs\\8puzzleSubset\\puzzle2x2-unsolvable2.txt";
        files[5] = "C:\\Users\\David\\Desktop\\IT_Coding\\Java\\Princeton_Class\\Code\\Inputs\\8puzzleSubset\\puzzle2x2-unsolvable3.txt";

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
            System.out.println(initial.manhattan());
        }
    }

}