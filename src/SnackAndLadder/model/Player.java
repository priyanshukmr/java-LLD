package SnackAndLadder.model;


public class Player {
    String name;
    int position;

    public Player(String name, int position) {
        this.name = name;
        this.position = position;
    }

    public int getPosition() {
        return this.position;
    }

    public void setPosition(int position) {
        this.position = position;
        System.out.println(this.name + " reached " + this.position);
    }

    public String getName() {
        return this.name;
    }
}
