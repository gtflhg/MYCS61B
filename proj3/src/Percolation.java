import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private final WeightedQuickUnionUF uf;
    private final WeightedQuickUnionUF uf2;
    private final boolean[] openSites;
    private final int N;
    int open;

    public Percolation(int N) {
        if (N <= 0) {
            throw new IllegalArgumentException();
        }
        this.N = N;
        this.open = 0;
        this.uf = new WeightedQuickUnionUF(N * N + 1);
        this.uf2 = new WeightedQuickUnionUF(N * N + 2);
        this.openSites = new boolean[N * N];
    }

    public void open(int row, int col) {
        validate(row, col);
        int index = getIndex(row, col);
        if (!isOpen(row, col)) {
            open++;
            openSites[index] = true;
            if (row == 0) {
                uf.union(index, N * N);
                uf2.union(index, N * N);
            }
            if (row == N - 1) {
                uf2.union(index, N * N + 1);
            }
            if (row > 0 && isOpen(row - 1, col)) {
                uf.union(getIndex(row - 1, col), index);
                uf2.union(getIndex(row - 1, col), index);
            }
            if (row < N - 1 && isOpen(row + 1, col)) {
                uf.union(getIndex(row + 1, col), index);
                uf2.union(getIndex(row + 1, col), index);
            }
            if (col > 0 && isOpen(row, col - 1)) {
                uf.union(getIndex(row, col - 1), index);
                uf2.union(getIndex(row, col - 1), index);
            }
            if (col < N - 1 && isOpen(row, col + 1)) {
                uf.union(getIndex(row, col + 1), index);
                uf2.union(getIndex(row, col + 1), index);
            }

        }
    }

    public boolean isOpen(int row, int col) {
        return openSites[getIndex(row, col)];
    }

    public boolean isFull(int row, int col) {
        return openSites[getIndex(row, col)] && uf.connected(getIndex(row, col), N * N);
    }

    public int numberOfOpenSites() {
        return open;
    }

    public boolean percolates() {
        return uf2.connected(N * N, N * N + 1);
    }

    private void validate(int row, int col) {
        if (row < 0 || row >= N || col < 0 || col >= N) {
            throw new IndexOutOfBoundsException("index out of range");
        }
    }

    private int getIndex(int row, int col) {
        return row * N + col;
    }
}
