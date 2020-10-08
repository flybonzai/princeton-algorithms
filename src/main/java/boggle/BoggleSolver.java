package boggle;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.TST;

import java.util.Arrays;

public class BoggleSolver {
    TST<String> prefixSearch;

    public BoggleSolver(String[] dictionary) {
        prefixSearch = new TST<>();
        Arrays.asList(dictionary).forEach((word) -> {
            prefixSearch.put(word, word);
        });
    }

    public Iterable<String> getAllValidWords(BoggleBoard board) {
        BoggleDFS dfs = new BoggleDFS(board, prefixSearch);
        return dfs.getWords();
    }

    public int scoreOf(String word) {
        int score;
        switch(word.length()) {
            case 0:
            case 1:
            case 2:
                score = 0;
                break;
            case 3:
            case 4:
                score = 1;
                break;
            case 5:
                score = 2;
                break;
            case 6:
                score = 3;
                break;
            case 7:
                score = 5;
                break;
            default:
                score = 11;
        }
        return score;
    }

    public static void main(String[] args) {
        In in = new In(args[0]);
        String[] dictionary = in.readAllStrings();
        BoggleSolver solver = new BoggleSolver(dictionary);
        solver.prefixSearch.keysThatMatch("a").forEach(System.out::println);
        BoggleBoard board = new BoggleBoard(args[1]);
        System.out.println(board.toString());

        int score = 0;
        for (String word : solver.getAllValidWords(board)) {
            StdOut.println(word);
            score += solver.scoreOf(word);
        }
        StdOut.println("Score = " + score);
    }
}
