package SnackAndLadder.model;

public class Dice {
    int maxValue;

    public Dice(int maxValue) {
        this.maxValue = maxValue;
    }

    public int roll() {
        int roll =  1+(int) (Math.random()*6);
        System.out.println("Dice roll: " + roll);
        return roll;
    }
}
