import edu.princeton.cs.algs4.StdIn;

public class Permutation {

    public static void main(String[] args) {
        int num_of_permutations = Integer.parseInt(args[0]);
        RandomizedQueue<String> queue = new RandomizedQueue<String>();
        while (StdIn.hasNextLine()) {
            queue.enqueue(StdIn.readString());
        }
        for (int i = 0; i < num_of_permutations; i++) {
            System.out.println(queue.dequeue());
        }
    }

}
