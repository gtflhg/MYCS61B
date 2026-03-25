import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    // TODO: Add any necessary instance variables.
    WeightedQuickUnionUF uf;
    private int N;
    int[] status;
    int open;

    public Percolation(int N) {
        // TODO: Fill in this constructor.
        this.N = N;
        this.open = 0;
        status = new int[N*N];
        uf = new WeightedQuickUnionUF(N*N);
    }

    public void open(int row, int col) {
        // TODO: Fill in this method.
        if (validate(row, col)) {
            if (!isOpen(row, col)) {
                open++;
                if (row == 0) {
                    setStatus(row, col, 2);
                }else{
                    setStatus(row, col, 1);
                }
                connect(row, col);
            }
        }

    }

    public boolean isOpen(int row, int col) {
        // TODO: Fill in this method.
        return getStatus(row, col) >= 1;
    }

    public boolean isFull(int row, int col) {
        // TODO: Fill in this method.
        return getStatus(row, col) == 2;
    }

    public int numberOfOpenSites() {
        // TODO: Fill in this method.
        return open;
    }

    public boolean percolates() {
        // TODO: Fill in this method.
        for (int i = 0; i < N; i++) {
            if (isFull(N-1, i)) {
                return true;
            }
        }
        return false;
    }

    private boolean validate(int row, int col) {
        return !(row < 0 || row >= N || col < 0 || col >= N);
    }

    private int getIndex(int row, int col) {
        return row * N + col;
    }

    private int getStatus(int row, int col) {
        if (!validate(row, col)) {
            return 0;
        }
        return status[uf.find(getIndex(row, col))];
    }

    private void setStatus(int row, int col, int value) {
        status[uf.find(getIndex(row, col))] = value;
    }

    private int getNeighboursStatus(int row, int col) {
        return Math.max(getStatus(row, col+1), Math.max(getStatus(row, col-1), Math.max(getStatus(row-1, col), getStatus(row+1, col))));
    }

    private void connect(int row, int col) {
        if (validate(row, col)) {
            int maxStatus = Math.max(getNeighboursStatus(row, col), getStatus(row, col));
            if (isOpen(row+1, col)) {
                uf.union(getIndex(row+1, col), getIndex(row, col));
            }
            if (isOpen(row, col+1)) {
                uf.union(getIndex(row, col+1), getIndex(row, col));
            }
            if (isOpen(row-1, col)) {
                uf.union(getIndex(row-1, col), getIndex(row, col));
            }
            if (isOpen(row, col-1)) {
                uf.union(getIndex(row, col-1), getIndex(row, col));
            }
            setStatus(row, col, maxStatus);
        }
    }


    // TODO: Add any useful helper methods (we highly recommend this!).
    // TODO: Remove all TODO comments before submitting.

}
