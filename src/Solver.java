import java.util.Comparator;
import java.util.LinkedList;
import edu.princeton.cs.algs4.MinPQ;


public class Solver {

    LinkedList<Board> solution;
    int moves;
    boolean isSolvable;

    // find a solution to the initial board (using the A* algorithm)
    public Solver(Board initial) {
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
                solution = new LinkedList<Board>();
                solution.add(minNode.board);
                while (minNode.previousNode != null) {
                    solution.add(minNode.previousNode.board);
                    minNode = minNode.previousNode;
                }
                isSolvable = true;
                break;
            }
            moves += 1;
            for (Board neighbor : minNode.board.neighbors()) {
                SearchNode newNode = new SearchNode(neighbor, minNode, moves);
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
                SearchNode newNode = new SearchNode(neighbor, minTwinNode, moves);
                twinpq.insert(newNode);
            }
        }
    }
    
    
    private class SearchNode implements Comparable<SearchNode> {
        
        Board board;
        SearchNode previousNode;
        int moves;
        
        private SearchNode(Board newBoard, SearchNode lastNode, int numOfMoves) {
            board = newBoard;
            previousNode = lastNode;
            moves = numOfMoves;
        }
        
        public int compareTo(SearchNode that) {
            if (that == null) {
                throw new NullPointerException("Argument for compareTo() can't be null");
            }
            
            int aBoardHP = this.board.hamming() + this.moves;
            int aBoardH = that.board.hamming() + that.moves;
            // Reference point to same object
            if (this == that) {
                return 0;
            } else if (aBoardH < aBoardH) {
                return -1;
            } else if (aBoardH > aBoardH) {
                return 1;
            }
            return 0;
        }

        
        public int compare(SearchNode a, SearchNode b) {
            int aBoardHP = a.board.hamming() + a.moves;
            int bBoardHP = b.board.hamming() + b.moves;
            if (aBoardHP < bBoardHP) {
                return -1;
            } else if (aBoardHP > bBoardHP) {
                return 1;
            } else {
                return 0;
            }
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
        PuzzleCheckerUpdate.main(args);    
    }
}
