
import java.util.ArrayList;
import java.util.LinkedList;
import edu.princeton.cs.algs4.MinPQ;


public class Solver {

    private ArrayList<Board> tempSolution;
    private ArrayList<Board> solution;
    private int moves;
    private boolean isSolvable;

    // find a solution to the initial board (using the A* algorithm)
    public Solver(Board initial) {
        if (initial == null) {
            throw new IllegalArgumentException("Board argument can't be null.");
        }
        moves = 0;
        // Create priorty queue to hold starting Node (initial board)
        MinPQ<SearchNode> pq = new MinPQ<SearchNode>();
        SearchNode start = new SearchNode(initial, null, moves);
        pq.insert(start);
        // Do the same with a twin board
        MinPQ<SearchNode> twinpq = new MinPQ<SearchNode>();
        SearchNode twin = new SearchNode(initial.twin(), null, moves);
        twinpq.insert(twin);

        // Dequeue node with lowest priority, add all it's neighbors, repeat until dequeued node is goal board
        while (true) {
            // Increment actual game board
            SearchNode minNode = pq.delMin();
            if (minNode.board.isGoal()) {
                moves = minNode.moves;
                // List will be in reverse order
                tempSolution = new ArrayList<Board>();
                for (int i = moves; i >= 0; i--) {
                    tempSolution.add(minNode.board);
                    minNode = minNode.previousNode;
                }
                solution = new ArrayList<Board>();
                // Put list in correct order
                for (int i = tempSolution.size()-1; i >= 0; i--) {
                    solution.add(tempSolution.remove(i));
                }
                isSolvable = true;
                break;
            }
            moves += 1; // This can be deleted and changed to a boolean that checks if it's the first pass
            for (Board neighbor : minNode.board.neighbors()) {
                if (moves > 1) {
                    if (neighbor.equals(minNode.previousNode.board)) {
                        continue;
                    }
                }
                SearchNode newNode = new SearchNode(neighbor, minNode, minNode.moves+1);
                pq.insert(newNode);
            }
            // Increment twin game board
            SearchNode minTwinNode = twinpq.delMin();
            if (minTwinNode.board.isGoal()) {
                moves = -1;
                solution = null;
                isSolvable = false;
                break;
            }
            for (Board neighbor : minTwinNode.board.neighbors()) {
                if (moves > 1) {
                    if (neighbor.equals(minTwinNode.previousNode.board)) {
                        continue;
                    }
                }
                SearchNode newNode = new SearchNode(neighbor, minTwinNode, minTwinNode.moves+1);
                twinpq.insert(newNode);
            }
        }
    }
    
    
    private class SearchNode implements Comparable<SearchNode> {
        
        Board board;
        SearchNode previousNode;
        int moves;
        int manhattan;
        
        private SearchNode(Board newBoard, SearchNode lastNode, int numOfMoves) {
            board = newBoard;
            previousNode = lastNode;
            moves = numOfMoves;
            manhattan = this.board.manhattan();
        }
        
        public int compareTo(SearchNode that) {
            if (that == null) {
                throw new NullPointerException("Argument for compareTo() can't be null");
            }
            
            int aBoardMP = this.manhattan + this.moves;
            int bBoardMP = that.manhattan + that.moves;
            // Reference point to same object
            if (this == that) {
                return 0;
            } else if (aBoardMP < bBoardMP) {
                return -1;
            } else if (aBoardMP > bBoardMP) {
                return 1;
            }
            return 0;
        }
    }

    // is the initial board solvable? (see below)
    public boolean isSolvable() {
        return isSolvable;
    }

    // min number of moves to solve initial board; -1 if unsolvable
    public int moves() {
        return moves;
    }

    // sequence of boards in a shortest solution; null if unsolvable
    public Iterable<Board> solution() {
        return solution;
    }

    // test client (see below) 
    public static void main(String[] args) {
        //PuzzleCheckerUpdate.main(args);    
    }
}
