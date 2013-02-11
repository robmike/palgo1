import java.lang.*;

public class PercolationStats {
    private int results[];
    private int gridsize;
    private int nsamples;

    public PercolationStats(int N, int T) {
        if(N <= 0 || T <= 0){
            throw new IndexOutOfBoundsException();
        }

        this.gridsize = N;
        this.nsamples = T;
        this.results = new int[T]; 
    }

    private int trial(){
        Percolation perc = new Percolation(this.gridsize);
        int x,y;
        int i;
        for(i=0; i<this.gridsize*this.gridsize; i++){
            do {
                x = StdRandom.uniform(this.gridsize) + 1;
                y = StdRandom.uniform(this.gridsize) + 1;
            }while(perc.isOpen(x, y));
            perc.open(x,y);
            if(perc.percolates()){
                return i;
            }
        }
        throw new IndexOutOfBoundsException("Opened all sites, but no percolation (?!)");
    }

    private void runtrials(){
        for(int i=0; i<this.nsamples; i++){
            this.results[i] = trial(); 
        }
    }

    public double mean() {
        return StdStats.mean(this.results);
    }
    public double stddev(){ return StdStats.stddev(this.results);}

    public double confidenceLo(){
        return this.mean() - 1.96*this.stddev()/Math.sqrt(this.nsamples);
    }
    public double confidenceHi(){
        return this.mean() + 1.96*this.stddev()/Math.sqrt(this.nsamples);
    }
    public static void main(String[] args){
        int N = Integer.parseInt(args[0]);
        int T = Integer.parseInt(args[1]);
        PercolationStats ps = new PercolationStats(N,T);
        ps.runtrials();
        System.out.println("Mean: " + Double.toString(ps.mean()));
        System.out.println("std: " + Double.toString(ps.stddev()));
        System.out.println("conflow: " + Double.toString(ps.confidenceLo()));
        System.out.println("confhi: " + Double.toString(ps.confidenceHi()));
        System.out.println("avg threshold: " + Double.toString(ps.mean()/N));
    }
}
