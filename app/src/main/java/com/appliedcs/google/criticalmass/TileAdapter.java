package com.appliedcs.google.criticalmass;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;


/**
 * Created by Sandesh on 02-04-2016.
 */
public class TileAdapter extends BaseAdapter {

    private Context context;
    private int numTiles;
    private int numRows;
    private int numCols;
    private Board gameBoard;

    public TileAdapter(Context context, int numRows, int numCols, Board gameBoard) {
        this.context = context;
        this.numRows = numRows;
        this.numCols = numCols;
        this.numTiles = numCols * numRows;
        this.gameBoard = gameBoard;
    }

    @Override
    public int getCount() {
        return numTiles;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView textView;
        if(convertView == null) {
            textView = new TextView(context);

            int x = position % numCols;
            int y = position % numRows;

            Log.d("HELLO", x + " " + y);

            int count = gameBoard.getCount(x, y);
            int player = gameBoard.getPlayer(x, y);

            if (count == 0) {
                textView.setText("0");
            }
            else {
                textView.setText("" + count);
                if (player == 0)
                    textView.setTextColor(parent.getResources().getColor(R.color.green));
                else if (player == 1)
                    textView.setTextColor(parent.getResources().getColor(R.color.blue));
            }

            textView.setLayoutParams(new GridView.LayoutParams(100, 100));
            textView.setTextSize(24.5f);
            // textView.setBackgroundResource(R.color.black);
            textView.setPadding(0, 8, 0, 8);
            textView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

        } else {
            textView = (TextView) convertView;
        }
        return textView;
    }
}
