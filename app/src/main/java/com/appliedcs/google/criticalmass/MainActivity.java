package com.appliedcs.google.criticalmass;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
{

    private static final int NUM_COLUMNS = 6;
    private static final int NUM_ROWS = 8;

    private static final int DEFAULT_PLAYERS = 2;

    private int currentPlayerNumber = 0;
    private int numPlayers = DEFAULT_PLAYERS;

    private TextView currentPlayerView;
    private GridView criticalGridView;
    private Button resetButton;

    private Board gameBoard;
    private boolean[] playerOutOfGame = new boolean[numPlayers];
    private int playersInGame = numPlayers;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        criticalGridView = (GridView) findViewById(R.id.criticalGridView);
        currentPlayerView = (TextView) findViewById(R.id.currentPlayerView);
        resetButton = (Button) findViewById(R.id.resetButton);

        currentPlayerView.setText("" + (currentPlayerNumber + 1));
        currentPlayerView.setTextColor(getResources().getColor(R.color.green));
        gameBoard = new Board(NUM_ROWS, NUM_COLUMNS, numPlayers);

        criticalGridView.setNumColumns(NUM_COLUMNS);
        criticalGridView.setGravity(Gravity.CENTER);
        final TileAdapter tileAdapter = new TileAdapter(this, NUM_ROWS, NUM_COLUMNS, gameBoard);
        criticalGridView.setAdapter(tileAdapter);

        criticalGridView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {

                Log.d("HELLO3", currentPlayerNumber + "");

                // currentPlayerView.setText("" + (currentPlayerNumber + 1));

                if (playersInGame == 1)
                    return;

                int x = position % NUM_COLUMNS;
                int y = position / NUM_COLUMNS;

                int winner = 0;

                if (gameBoard.isPlayerLost(currentPlayerNumber))
                {
                    playerOutOfGame[currentPlayerNumber] = true;
                    playersInGame--;
                    Toast.makeText(MainActivity.this, "Player " + (currentPlayerNumber + 1) + " lost", Toast.LENGTH_SHORT).show();

                    if (playersInGame == 1)
                    {
                        for (int i = 0; i < playerOutOfGame.length; ++i)
                        {
                            if (!playerOutOfGame[i])
                            {
                                winner = i;
                                break;
                            }
                        }
                        currentPlayerView.setText("" + (winner + 1) + " Wins!!");
                        currentPlayerView.setTextColor(getResources().getColor(winner == 0 ? R.color.green : R.color.blue));
                        Toast.makeText(MainActivity.this, "Player " + (winner + 1) + " Wins!!", Toast.LENGTH_SHORT).show();
                    }

                    return;
                }

                if (gameBoard.isTileEmpty(x, y))
                {
                    gameBoard.explodeTile(x, y, currentPlayerNumber);
                    do
                    {
                        currentPlayerNumber = (currentPlayerNumber + 1) % numPlayers;
                    } while (playerOutOfGame[currentPlayerNumber]);
                } else if (gameBoard.getPlayer(x, y) == currentPlayerNumber)
                {
                    gameBoard.explodeTile(x, y, currentPlayerNumber);
                    do
                    {
                        currentPlayerNumber = (currentPlayerNumber + 1) % numPlayers;
                    } while (playerOutOfGame[currentPlayerNumber]);
                }

                String str = "";
                for (int i = 0; i < NUM_ROWS; ++i)
                {
                    String row = "";
                    for (int j = 0; j < NUM_COLUMNS; ++j)
                    {
                        row += ("(" + gameBoard.getPlayer(j, i) + "," + gameBoard.getCount(j, i) + ") ");
                    }
                    str += (row + "\n");
                }
                // Log.d("HELLO1", str);

                for (int i = 0; i < NUM_ROWS * NUM_COLUMNS; ++i)
                {
                    TextView textView = (TextView) tileAdapter.getItem(i);
                    int p = i % NUM_COLUMNS;
                    int q = i / NUM_COLUMNS;

                    int count = gameBoard.getCount(p, q);
                    int player = gameBoard.getPlayer(p, q);

                    if (count == 0)
                    {
                        textView.setText("0");
                        textView.setTextColor(parent.getResources().getColor(R.color.black));
                    } else
                    {
                        textView.setText("" + count);
                        if (player == 0)
                            textView.setTextColor(parent.getResources().getColor(R.color.green));
                        else if (player == 1)
                            textView.setTextColor(parent.getResources().getColor(R.color.blue));
                    }
                }

                currentPlayerView.setText("" + (currentPlayerNumber + 1));
                currentPlayerView.setTextColor(getResources().getColor(currentPlayerNumber == 0 ? R.color.green : R.color.blue));

//                Toast.makeText(MainActivity.this, position + " clicked", Toast.LENGTH_SHORT).show();
            }
        });

        resetButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                gameBoard = new Board(NUM_ROWS, NUM_COLUMNS, numPlayers);
                playerOutOfGame = new boolean[numPlayers];
                currentPlayerNumber = 0;
                playersInGame = numPlayers;
                currentPlayerView.setText("" + (currentPlayerNumber + 1));
                currentPlayerView.setTextColor(getResources().getColor(R.color.green));

                for (int i = 0; i < NUM_ROWS * NUM_COLUMNS; ++i)
                {
                    TextView textView = (TextView) tileAdapter.getItem(i);
                    textView.setText("0");
                    textView.setTextColor(getResources().getColor(R.color.black));
                }
            }
        });
    }
}
