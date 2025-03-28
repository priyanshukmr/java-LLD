package SnackAndLadder.service;

import SnackAndLadder.model.Board;
import SnackAndLadder.model.Dice;
import SnackAndLadder.model.Player;
import java.util.*;

public class Game {

    Board board;
    Dice dice;
    List<Player> players;

    public Game(Board board, Dice dice, List<Player>  players) {
        this.board = board;
        this.dice = dice;
        this.players = players;
    }

    boolean hasWon(Player player) {
        return player.getPosition() == board.getBoardSize();
    }

    public void startGame() {
        Deque<Player> turn = new ArrayDeque<>();
        for(Player player: players) {
            turn.add(player);
        }
        if(players.size()<=1) {
            System.out.println("Minimum 2 players required");
            return;
        }

        while(turn.size()>1) {
            Player current = turn.removeFirst();
            int diceValue = dice.roll();
            // outOfBounds
            if(current.getPosition() + diceValue <= board.getBoardSize()) {
                current.setPosition(current.getPosition() + diceValue);
                if(hasWon(current)) {
                    System.out.println(current.getName()+" has won!");
                    System.out.println(turn.size() +  " players left now..");
                    continue;
                }
            }
            // snakeOrLadder
            if(board.getSnakesAndLadders().getOrDefault(current.getPosition(), -1) != -1) {
                current.setPosition(board.getSnakesAndLadders().get(current.getPosition()));
            }
            turn.addLast(current);
        }
        System.out.println("Game ended with last player: "+turn.getFirst().getName());
    }
}
