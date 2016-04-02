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
}
