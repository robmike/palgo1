interface PercolationI {
    //public Percolation(int N);              // create N-by-N grid, with all sites blocked
    public void open(int i, int j); // open site (row i, column j) if it is not already
    public boolean isOpen(int i, int j);    // is site (row i, column j) open?
    public boolean isFull(int i, int j);    // is site (row i, column j) full?
    public boolean percolates(); // does the system percolate?
}

interface PercolationStatsI {
    //public PercolationStats(int N, int T);    // perform T independent computational experiments on an N-by-N grid
    public double mean();                     // sample mean of percolation threshold
    public double stddev();                   // sample standard deviation of percolation threshold
    public double confidenceLo();             // returns lower bound of the 95% confidence interval
    public double confidenceHi();             // returns upper bound of the 95% confidence interval
    // public static void main(String[] args);   // test client, described below
}

public class Percolation {
    public boolean grid[][];
    private int size;
    public Percolation(int N) {
        this.grid = new boolean[N][N];
        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                grid[i][j] = false;
            }
        }
        this.size = N;
    }

    public void open(int i, int j) {
        grid[i-1][j-1] = true;
    }

    public boolean isOpen(int i, int j) {
        return grid[i-1][j-1];
    }

    public boolean isFull(int i, int j) {
        return false;           // fixme: todo
    }

    public boolean percolates() {
        for(int j=0; j<this.size; j++){
            if(this.isFull(this.size-1, j)) {
                return true;
            }
        }
        return false;
    }
}
