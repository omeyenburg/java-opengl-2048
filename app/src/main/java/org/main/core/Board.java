package org.main.core;

public class Board {
    private int[][] grid;
    private boolean state;

    public Board() {
        grid = new int[4][4];
        reset();
    }

    public void move(int direction) {}

    public boolean isRunning() {
        return state;
    }

    public void reset() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                grid[i][j] = 0;
            }
        }
        state = true;
    }
}
