import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

    private double[] results;
    private double numOfOpenSites;
    private double doubleN;
    private double doubleTrials;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        validate(n, trials);
        doubleN = n;
        doubleTrials = trials;
        results = new double[trials];
        for (int t = 0; t < trials; t++) {
            Percolation test = new Percolation(n);

            while (!test.percolates()) {
                int row = StdRandom.uniform(n) + 1;
                int col = StdRandom.uniform(n) + 1;
                test.open(row, col);
            }
            numOfOpenSites = test.numberOfOpenSites();
            results[t] = (numOfOpenSites / (doubleN * doubleN));
        }       
    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(results);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(results);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return (this.mean() - ((1.96 * this.stddev()) / doubleTrials));
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return (this.mean() + ((1.96 * this.stddev()) / doubleTrials));
    }
    
    // validate input
    private void validate(int n, int trials) {
        if (n <= 0) {
            throw new IllegalArgumentException("n must be greater than 0.");
        }
        if (trials <= 0) {
            throw new IllegalArgumentException("trials must be greater than 0");
        }
    }

   // test client (see below)
   public static void main(String[] args) {
       int n = Integer.parseInt(args[0]);
       int T = Integer.parseInt(args[1]);
       PercolationStats test = new PercolationStats(n, T);
       System.out.println("mean = " + test.mean());
       System.out.println("stddev = " + test.stddev());
       System.out.println("95% confidence interval = [" + test.confidenceLo() + 
                           ", " + test.confidenceHi() + "]");
       
   }

}
