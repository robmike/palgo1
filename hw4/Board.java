public class Board {
    private int[][] tiles;
    private int size;

    public Board(int[][] blocks){
        // construct a board from an N-by-N array of blocks
        // (where blocks[i][j] = block in row i, column j)
        this.size = blocks.length;
        this.tiles = new int[size][size];
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                tiles[i][j] = blocks[i][j]
            }
        }
    }
                                           
    public int dimension() {
        // board dimension N
        return this.size;
    }

    public int hamming() {
        int count = 0;
        int N = size;
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N-1; j++) {
                if(tiles[i][j] != i*N + j + 1) count++; 
            }
        }
        if(tiles[N-1][N-1] != 0) count++;
        return count;
    }

    public int manhattan() {
        // sum of Manhattan distances between blocks and goal
        int count = 0;
        int N = size;
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N-1; j++) {
                int t = tiles[i][j] - 1;
                if(t < 0) t = N*N - 1;
                if(t != i*N + j) {
                    count += Math.abs((t % N) - j);
                    count += Math.abs((t/N) - i);
                }
            }
        }
        return count;
    }
    public boolean isGoal() {
        // is this board the goal board?
        return hamming() == 0;
    }

    private int[][] swapTiles(i, j, x, y) {
        int[][] t = tiles.clone();
        int tmp = tiles[i][j];
        tiles[i][j] = tiles[x][y];
        tiles[x][y] = tmp;
        return
    }

    public Board twin() {
        // a board obtained by exchanging two adjacent blocks in the same row
        for(int i=0; i<size; i++) {
            for(int j=0; j<size-1; j++) {
                if(tiles[i][j] != 0 && tiles[i][j+1] != 0) {
                    swapTiles(i, j, i, j+1);
                } 
            }
        }
    }

    public boolean equals(Object y) {
        if (this == y) return true;
        if (y == null) return false;
        if (x.getClass() != this.getClass()) return false;
        Board that = (Board) y;
        int N = this.dimension()
        if (y.dimension() != N) return false;
        return x.toString() == y.toString;
    }

    public Iterable<Board> neighbors() {
        // all neighboring boards
        Queue<Board> q = new Queue<Board>();
        
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(N + "\n");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                s.append(String.format("%2d ", tiles[i][j]));
            }
            s.append("\n");
        }
        return s.toString();
    }
}