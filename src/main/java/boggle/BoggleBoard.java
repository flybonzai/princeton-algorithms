package boggle;

import edu.princeton.cs.algs4.In;

public class BoggleBoard {
    char[][] board;

    // Initializes a random 4-by-4 Boggle board.
    // (by rolling the Hasbro dice)
    public BoggleBoard() {
        board = new char[4][4];
    }

    // Initializes a random m-by-n Boggle board.
    // (using the frequency of letters in the English language)
    public BoggleBoard(int m, int n) {
        board = new char[m][n];
    }

    // Initializes a Boggle board from the specified filename.
    public BoggleBoard(String filename) {
        In in = new In(filename);
        int x = in.readInt();
        int y = in.readInt();
        this.board = new char[x][y];
        populateBoardFrom(in, this.board);
    }

    private static void populateBoardFrom(In inputStream, char[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                char c;
                while (true) {
                    c = inputStream.readChar();
                    if (Character.isAlphabetic(c) && !(c == 'u'))
                        break;
                }
                board[i][j] = c;
            }
        }
    }

    // Initializes a Boggle board from the 2d char array.
    // (with 'Q' representing the two-letter sequence "Qu")
    public BoggleBoard(char[][] a) {
        board = a;
    }

    // Returns the number of rows.
    public int rows() {
        return this.board.length;
    }

    // Returns the number of columns.
    public int cols() {
        return this.board[0].length;
    }

    // Returns the letter in row i and column j.
    // (with 'Q' representing the two-letter sequence "Qu")
    public char getLetter(int i, int j) {
        return this.board[i][j];
    }

    // Returns a string representation of the board.
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < this.rows(); i++) {
            for (int j = 0; j < this.cols(); j++) {
                stringBuilder.append(getLetter(i, j)).append(" ");
            }
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }
}
