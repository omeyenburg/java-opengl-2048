package org.main.core;

public class Board {
    private int[][] grid;
    private boolean state;

    public Board() {
        grid = new int[4][4];
        reset();
    }

    public void move() {}

    public boolean isRunning() {
        return state;
    }

    public void reset() {
        state = true;
    }
}
