import java.lang.*;

interface PercolationI {
    //public Percolation(int N);              // create N-by-N grid, with all sites blocked
    public void open(int i, int j); // open site (row i, column j) if it is not already
    public boolean isOpen(int i, int j);    // is site (row i, column j) open?
    public boolean isFull(int i, int j);    // is site (row i, column j) full?
    public boolean percolates(); // does the system percolate?
}

public class Percolation implements PercolationI {
    public boolean grid[][];
    private int size;
    private WeightedQuickUnionUF uf;

    public Percolation(int N) {
        this.grid = new boolean[N][N];
        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                grid[i][j] = false;
            }
        }
        this.size = N;
        this.uf = new WeightedQuickUnionUF(N*N);
    }

    public int[] fromlinear(int i){
        int[] x = new int[2];
        x[0] = i/this.size;
        x[1] = i % this.size;
        return x;
    }

    public int tolinear(int i, int j){
        return i*this.size + j;
    }
    
    private void validateIdx(int i, int j){
        if(i < 1 || j < 1 || i > this.size || j > this.size){
            throw new IndexOutOfBoundsException("error " + Integer.toString(i) + ", " + Integer.toString(j));
        }
    }

    // check if second tuple is open and connect if so
    private void connectIfOpen(int i, int j, int x, int y){
        if(isOpen(x,y)){
            uf.union(tolinear(i-1,j-1), tolinear(x-1,y-1));
        }
    }

    public void open(int i, int j) {
        validateIdx(i,j);
        grid[i-1][j-1] = true;
        connectIfOpen(i, j, Math.max(i-1,1), j);
        connectIfOpen(i, j, Math.min(i+1,this.size), j);
        connectIfOpen(i, j, i, Math.max(j-1,1));
        connectIfOpen(i, j, i, Math.min(j+1,this.size));
    }

    public boolean isOpen(int i, int j) {
        validateIdx(i,j);
        return grid[i-1][j-1];
    }

    public boolean isFull(int i, int j) {
        validateIdx(i,j);
        for(int k=0; k<this.size; k++){
            if(uf.connected(tolinear(i-1,j-1), tolinear(0, k))){
                return true;
            }
        }
        return false;
    }

    public boolean percolates() {
        for(int j=0; j<this.size; j++){
            if(this.isFull(this.size, j+1)) {
                return true;
            }
        }
        return false;
    }
}
