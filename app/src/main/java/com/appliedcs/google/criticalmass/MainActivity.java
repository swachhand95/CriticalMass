package com.appliedcs.google.criticalmass;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final int NUM_COLUMNS = 8;
    private static final int NUM_ROWS = 6;

    private static final int DEFAULT_PLAYERS = 2;

    private int currentPlayerNumber = 0;
    private int numPlayers = DEFAULT_PLAYERS;

    private TextView currentPlayerView;
    private GridView criticalGridView;

    private Board gameBoard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        criticalGridView = (GridView) findViewById(R.id.criticalGridView);
        currentPlayerView = (TextView) findViewById(R.id.currentPlayerView);

        currentPlayerView.setText("" + (currentPlayerNumber + 1));
        gameBoard = new Board(NUM_ROWS, NUM_COLUMNS);

        criticalGridView.setNumColumns(NUM_COLUMNS);
        criticalGridView.setAdapter(new TileAdapter(this, NUM_ROWS, NUM_COLUMNS, gameBoard));

        criticalGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                int x = position % NUM_COLUMNS;
                int y = position % NUM_ROWS;

                if (gameBoard.isTileEmpty(x, y)) {
                    gameBoard.explodeTile(x, y, currentPlayerNumber);
                    currentPlayerNumber = (currentPlayerNumber + 1) % numPlayers;
                }
                else if (gameBoard.getPlayer(x, y) == currentPlayerNumber) {
                    gameBoard.explodeTile(x, y, currentPlayerNumber);
                    currentPlayerNumber = (currentPlayerNumber + 1) % numPlayers;
                }

                Toast.makeText(MainActivity.this, position + " clicked", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
