package com.appliedcs.google.criticalmass;

/**
 * Created by swachhand on 4/2/16.
 */
public class Tile {

    private int count = 0;
    private int player = 0;

    public Tile(int count, int player) {
        this.count = count;
        this.player = player;
    }

    public int getCount() {
        return count;
    }

    public int getPlayer() {
        return player;
    }

    public void setCount(int c) {
        count = c;
    }

    public void incCount() {
        count++;
    }

    public void setPlayer(int p) {
        player = p;
    }

}
