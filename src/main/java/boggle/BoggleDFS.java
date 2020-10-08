package boggle;

import edu.princeton.cs.algs4.TST;

import java.util.HashSet;

public class BoggleDFS {
    BoggleBoard board;
    TST<String> dictionary;
    boolean[][] visited;
    int rows;
    int cols;
    HashSet<String> words;

    public BoggleDFS(BoggleBoard board, TST<String> dictionary) {
        this.board = board;
        this.dictionary = dictionary;
        this.rows = board.rows();
        this.cols = board.cols();
        this.visited = new boolean[this.rows][this.cols];
        words = new HashSet<>();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                dfs(i, j, this.words, "");
            }
        }
    }

    public void dfs(int i, int j, HashSet<String> words, String prefix) {
        if (visited[i][j]) {
            return;
        }

        char c = board.getLetter(i, j);
        if (c == 'Q') {
            prefix = prefix + "QU";
        } else prefix = prefix + c;

        if (hasNoMatches(prefix)) {
            return;
        }

        if (isKnownWord(prefix)) {
            words.add(prefix);
        }

        // So we don't revisit this node during the dfs on adjacent nodes
        visited[i][j] = true;

        visitAdjacentNodes(i, j, words, prefix);

        // So that future searches can find permutations with this node
        visited[i][j] = false;
    }

    private boolean hasNoMatches(String prefix) {
        Iterable<String> matches = dictionary.keysWithPrefix(prefix);
        return !matches.iterator().hasNext();
    }

    private boolean isKnownWord(String prefix) {
        return prefix.length() >= 3 && dictionary.contains(prefix);
    }

    private void visitAdjacentNodes(int i, int j, HashSet<String> words, String prefix) {
        if (hasLeftNeighbor(j)) {
            dfs(i, j - 1, words, prefix);
        }

        if (hasTopLeftNeighbor(i, j)) {
            dfs(i - 1, j - 1, words, prefix);
        }

        if (hasTopNeighbor(i)) {
            dfs(i - 1, j, words, prefix);
        }

        if (hasTopRightNeighbor(i, j)) {
            dfs(i - 1, j + 1, words, prefix);
        }

        if (hasRightNeighbor(j)) {
            dfs(i, j + 1, words, prefix);
        }

        if (hasBottomRightNeighbor(i, j)) {
            dfs(i + 1, j + 1, words, prefix);
        }

        if (hasBottomNeighbor(i)) {
            dfs(i + 1, j, words, prefix);
        }

        if (hasBottomLeftNeighbor(i, j)) {
            dfs(i + 1, j - 1, words, prefix);
        }
    }

    protected boolean hasLeftNeighbor(int j) {
        return j > 0;
    }

    protected boolean hasTopLeftNeighbor(int i, int j) {
        return i > 0 && j > 0;
    }

    protected boolean hasTopNeighbor(int i) {
        return i > 0;
    }

    protected boolean hasTopRightNeighbor(int i, int j) {
        return i > 0 && j < cols - 1;
    }

    protected boolean hasRightNeighbor(int j) {
        return j < cols - 1;
    }

    protected boolean hasBottomRightNeighbor(int i, int j) {
        return i < rows - 1 && j < cols - 1;
    }

    protected boolean hasBottomNeighbor(int i) {
        return i < rows - 1;
    }

    protected boolean hasBottomLeftNeighbor(int i, int j) {
        return i < rows - 1 && j > 0;
    }

    public Iterable<String> getWords() {
        return words;
    }
}
