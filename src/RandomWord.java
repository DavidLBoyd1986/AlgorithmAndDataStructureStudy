import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdRandom;

public class RandomWord {

    public static void main(String[] args) {
        double probability = 1;
        double position = 1;
        String champion = " ";
        while (!StdIn.isEmpty()) {
            String userInput = StdIn.readString();
            if (StdRandom.bernoulli(probability))
                champion = userInput;
            position++;
            probability = 1/position;
        }
        System.out.println(champion);
    }
}
