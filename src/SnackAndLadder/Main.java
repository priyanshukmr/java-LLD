package SnackAndLadder;

import SnackAndLadder.model.Board;
import SnackAndLadder.model.Dice;
import SnackAndLadder.model.Player;
import SnackAndLadder.service.Game;

import java.io.IOException;
import java.util.*;

public class Main {
    public static void main(String args[]) throws IOException {
        Player Aamir = new Player("Aamir", 0);
        Player Shahrukh = new Player("Shahrukh", 0);
        Player Irfan = new Player("Irfan", 0);
        Map<Integer, Integer> snakesAndLadders = new TreeMap<>();

        // Ladders 🪜 (start → end)
        snakesAndLadders.put(3, 22);
        snakesAndLadders.put(5, 8);
        snakesAndLadders.put(11, 26);
        snakesAndLadders.put(20, 29);
        snakesAndLadders.put(66, 84);

        // Snakes 🐍 (start → end)
        snakesAndLadders.put(94, 4);
        snakesAndLadders.put(21, 9);
        snakesAndLadders.put(17, 4);

        Board board = new Board(snakesAndLadders, 100);
        Game game = new Game(board, new Dice(6), Arrays.asList(Aamir, Shahrukh));
        game.startGame();
    }
}
