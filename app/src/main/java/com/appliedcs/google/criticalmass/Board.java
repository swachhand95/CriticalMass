package com.appliedcs.google.criticalmass;

/**
 * Created by swachhand on 4/2/16.
 */
public class Board {

    public static final int DEFAULT_ROWS = 6;
    public static final int DEFAULT_COLS = 8;

    private static int numRows = DEFAULT_ROWS;
    private static int numCols = DEFAULT_COLS;

    private static final int DEFAULT_PLAYERS = 2;

    Tile[][] matrix = null;

    private int numPlayers = DEFAULT_PLAYERS;
    private int[] numPlayerTiles;
    private boolean[] playerStarted;

    public Board(int nRows, int nCols, int nPlayers) {
        numRows = nRows;
        numCols = nCols;
        numPlayers = nPlayers;

        matrix = new Tile[numRows][numCols];

        for (int i = 0; i < nRows; ++i)
            for (int j = 0; j < nCols; ++j)
                matrix[i][j] = new Tile(0, 0);

        numPlayerTiles = new int[numPlayers];
        playerStarted = new boolean[numPlayers];
    }

    public static int getNumRows() {
        return numRows;
    }

    public static int getNumCols() {
        return numCols;
    }

    public void placeTile(int x, int y, int player) {
        matrix[y][x] = new Tile(1, player);
        numPlayerTiles[player]++;
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

    public boolean isPlayerLost(int player) {
        return playerStarted[player] && numPlayerTiles[player] == 0;
    }

    public void explodeTile(int x, int y, int player) {
        if (x < 0 || y < 0 || x >= numCols || y >= numRows)
            return;

        playerStarted[player] = true;

        Tile tile = matrix[y][x];
        if (tile == null) {
            placeTile(x, y, player);
        }
        else if (tile.getCount() == 0) {
            ++numPlayerTiles[player];
            tile.incCount();
            tile.setPlayer(player);
        }
        else if (tile.getCount() == 1) {
            int currentPlayer = tile.getPlayer();
            if (currentPlayer != player) {
                numPlayerTiles[currentPlayer]--;
                numPlayerTiles[player]++;
            }
            if (isAtCorner(x, y)) {
                numPlayerTiles[player]--;
                tile.setCount(0);
                explodeNeighbors(x, y, player);
            }
            else {
                tile.incCount();
                tile.setPlayer(player);
            }
        }
        else if (tile.getCount() == 2) {
            int currentPlayer = tile.getPlayer();
            if (currentPlayer != player) {
                numPlayerTiles[currentPlayer]--;
                numPlayerTiles[player]++;
            }
            if (isAtEdge(x, y)) {
                numPlayerTiles[player]--;
                tile.setCount(0);
                explodeNeighbors(x, y, player);
            }
            else {
                tile.incCount();
                tile.setPlayer(player);
            }
        }
        else if (tile.getCount() == 3) {
            int currentPlayer = tile.getPlayer();
            if (currentPlayer != player) {
                numPlayerTiles[currentPlayer]--;
                numPlayerTiles[player]++;
            }
            numPlayerTiles[player]--;
            tile.setCount(0);
            explodeNeighbors(x, y, player);
        }

    }

    public int getPlayer(int x, int y) {
        return matrix[y][x].getPlayer();
    }

    public int getCount(int x, int y) {
        return matrix[y][x].getCount();
    }

    public boolean isTileEmpty(int x, int y) {
        if (x < 0 || y < 0 || x >= numCols || y >= numRows)
            return false;
        if (matrix[y][x] == null)
            return true;
        if (matrix[y][x].getCount() == 0)
            return true;
        return false;
    }
}
