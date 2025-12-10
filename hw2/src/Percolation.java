import edu.princeton.cs.algs4.WeightedQuickUnionUF;


public class Percolation {
    private boolean[][] tf_grid; // initializes each cell to point to null(false) - internal structure to track OpenedSites
    private int topVirtualSite;
    private int bottomVirtualSite;
    int N;
    private int openSiteCount = 0;
    private WeightedQuickUnionUF uf;


    public Percolation(int N) {
        if(N<=0) throw new IllegalArgumentException("Specify a Grid Size(N) > 0");
        this.N = N;  //"Take the value passed into the constructor, and store it in the class field N."
        tf_grid = new boolean[N][N];
        uf = new WeightedQuickUnionUF(N*N + 2); //no_sites: n by n grid and the 2 virtual
        topVirtualSite = N*N; // it's top but Virtual because it is actually set to follow after the last initialized site
        bottomVirtualSite = N*N +1 ;
    }

    public void open(int row, int col) {
        // edges
        if(row < 0 || row >=N || col< 0 || col >= N) {
            throw new IndexOutOfBoundsException();
        }
        // opening the site
        if(tf_grid[row][col]) return;
        tf_grid[row][col] = true;
        openSiteCount ++;

        int index = row*N + col; //this is how we map the 2d grid to a 1d list as the WeightedQuickUnionUF expects .
        //after it's open, we must connect it to neighbour sites.
        if (row > 0 && isOpen(row-1, col)) uf.union(index,(row-1)*N + col ); // top neighbor
        if (row < N-1 &&  isOpen(row+1, col)) uf.union(index,(row+1)*N + col); // bottom neighbor
        if (col > 0 && isOpen(row,col-1)) uf.union(index, (row)*N +col-1); // left neighbor
        if (col < N-1 && isOpen(row,col+1)) uf.union(index, (row*N)+col+1); // right neighbor

        // union with conceptual virtual sites
        if (row == 0) uf.union(index, topVirtualSite );
        if (row == N-1) uf.union(index, bottomVirtualSite);
    }

    public boolean isOpen(int row, int col) {
        // edges
        if(row < 0 || row >=N || col< 0 || col >= N) {
            throw new IndexOutOfBoundsException();
        }

        return tf_grid[row][col];
    }

    public boolean isFull(int row, int col) {
        // edges
        if(row < 0 || row >=N || col< 0 || col >= N) {
            throw new IndexOutOfBoundsException();
        }
        if (!tf_grid[row][col]) return false; //
        int index = row*N + col;
        return isOpen(row, col) && uf.connected(index,topVirtualSite);
    }

    public int numberOfOpenSites() {
        return openSiteCount;
    }

    public boolean percolates() {
        return uf.connected(topVirtualSite, bottomVirtualSite);
    }

    // TODO: Add any useful helper methods (we highly recommend this!).
    // TODO: Remove all TODO comments before submitting.

}
