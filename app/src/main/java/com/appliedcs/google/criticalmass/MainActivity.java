package com.appliedcs.google.criticalmass;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private int NUM_TILES = 48;
    private int NUM_COLUMNS = 8;

    private int currentPlayerNumber = 0;

    private TextView currentPlayerView;
    private GridView criticalGridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        criticalGridView = (GridView) findViewById(R.id.criticalGridView);
        currentPlayerView = (TextView) findViewById(R.id.currentPlayerView);

        criticalGridView.setNumColumns(NUM_COLUMNS);
        criticalGridView.setAdapter(new TileAdapter(this, NUM_TILES));

        currentPlayerView.setText("" + (currentPlayerNumber+1));

        criticalGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                int x = position % NUM_COLUMNS;
                int y = position % NUM_TILES;

                Toast.makeText(MainActivity.this, position + " clicked", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
