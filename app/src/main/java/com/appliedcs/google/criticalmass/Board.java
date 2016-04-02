package com.appliedcs.google.criticalmass;

/**
 * Created by swachhand on 4/2/16.
 */
public class Board {

    public static final int DEFAULT_ROWS = 6;
    public static final int DEFAULT_COLS = 8;

    private static int numRows = DEFAULT_ROWS;
    private static int numCols = DEFAULT_COLS;

    Tile[][] matrix = null;

    public Board(int nRows, int nCols) {
        numRows = nRows;
        numCols = nCols;

        matrix = new Tile[numRows][numCols];
    }

    public static int getNumRows() {
        return numRows;
    }

    public static int getNumCols() {
        return numCols;
    }

    public void placeTile(int x, int y, int player) {
        matrix[y][x] = new Tile(1, player);
    }

    public boolean isAtCorner(int x, int y) {
        return (x == 0 || x == numCols - 1) && (y == 0 || y == numRows - 1);
    }

    public boolean isAtEdge(int x, int y) {
        return (x == 0 || x == numCols - 1 || y == 0 || y == numRows - 1);
    }

    private void explodeNeighbors(int x, int y, int player) {
        explodeTile(x, y-1, player);
        explodeTile(x, y+1, player);
        explodeTile(x-1, y, player);
        explodeTile(x+1, y, player);
    }

    public void explodeTile(int x, int y, int player) {
        if (x < 0 || y < 0 || x >= numCols || y >= numRows)
            return;

        Tile tile = matrix[y][x];
        if (tile == null) {
            placeTile(x, y, player);
        }
        else if (tile.getCount() == 0) {
            tile.incCount();
            tile.setPlayer(player);
        }
        else if (tile.getCount() == 1) {
            if (isAtCorner(x, y)) {
                tile.setCount(0);
                explodeNeighbors(x, y, player);
            }
            else {
                tile.incCount();
                tile.setPlayer(player);
            }
        }
        else if (tile.getCount() == 2) {
            if (isAtEdge(x, y)) {
                tile.setCount(0);
                explodeNeighbors(x, y, player);
            }
            else {
                tile.incCount();
                tile.setPlayer(player);
            }
        }
        else if (tile.getCount() == 3) {
            tile.setCount(0);
            explodeNeighbors(x, y, player);
        }

    }
}
