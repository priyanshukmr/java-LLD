package SnackAndLadder.model;
import java.util.*;

public class Board {
    Map<Integer, Integer> snakesAndLadders;
    int boardSize;

    public Board(Map<Integer, Integer> snakesAndLadders, int boardSize) {
        this.snakesAndLadders = snakesAndLadders;
        this.boardSize = boardSize;
    }

    public int getBoardSize() {
        return this.boardSize;
    }

    public Map<Integer, Integer> getSnakesAndLadders() {
        return this.snakesAndLadders;
    }
}
