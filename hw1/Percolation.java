import java.lang.*;

public class Percolation {
    private boolean grid[][];
    private int size;
    private WeightedQuickUnionUF uf;
    private int topidx;
    private int bottomidx;

    public Percolation(int N) {
        this.grid = new boolean[N][N];
        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                grid[i][j] = false;
            }
        }
        this.size = N;
        // Last two values are special sentinals for being connected to top or bottom respectively
        this.uf = new WeightedQuickUnionUF(N*N + 2);
        this.topidx = N*N;
        this.bottomidx = N*N + 1;
    }

    private int[] fromlinear(int i){
        int[] x = new int[2];
        x[0] = i/this.size;
        x[1] = i % this.size;
        return x;
    }

    private int tolinear(int i, int j){
        return i*this.size + j;
    }
    
    private void validateIdx(int i, int j){
        if(i < 1 || j < 1 || i > this.size || j > this.size){
            throw new IndexOutOfBoundsException("error " + Integer.toString(i) + ", " + Integer.toString(j));
        }
    }

    private void connectToTop(int i, int j)
    {
        uf.union(tolinear(i-1,j-1), this.topidx);
    }

    private void connectToBottom(int i, int j)
    {
        uf.union(tolinear(i-1,j-1), this.bottomidx);
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

        if(i==1) {
            connectToTop(i,j);
        } else if (i==this.size) {
            connectToBottom(i,j);
        }
    }

    public boolean isOpen(int i, int j) {
        validateIdx(i,j);
        return grid[i-1][j-1];
    }

    public boolean isFull(int i, int j) {
        validateIdx(i,j);
        return uf.connected(tolinear(i-1,j-1), this.topidx);
    }

    public boolean percolates() {
        return uf.connected(this.topidx, this.bottomidx);
    }
}
