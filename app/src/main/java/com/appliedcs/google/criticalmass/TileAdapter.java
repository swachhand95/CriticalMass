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

import java.util.ArrayList;


/**
 * Created by Sandesh on 02-04-2016.
 */
public class TileAdapter extends BaseAdapter {

    private Context context;
    private int numTiles;
    private int numRows;
    private int numCols;
    private Board gameBoard;
    private ArrayList<TextView> list;

    public TileAdapter(Context context, int numRows, int numCols, Board gameBoard) {
        this.context = context;
        this.numRows = numRows;
        this.numCols = numCols;
        this.numTiles = numCols * numRows;
        this.gameBoard = gameBoard;
        this.list = new ArrayList<>();

        for (int i = 0; i < numTiles; ++i)
            list.add(new TextView(context));
    }

    @Override
    public int getCount() {
        return numTiles;
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
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
            textView = list.get(position);

            int x = position % numCols;
            int y = position / numCols;

            // Log.d("HELLO", x + " " + y);

            textView.setText("0");
            textView.setTextColor(parent.getResources().getColor(R.color.black));

            textView.setLayoutParams(new GridView.LayoutParams(100, 100));
            textView.setTextSize(24.5f);
            textView.setPadding(0, 8, 0, 8);
            textView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

        } else {
            textView = (TextView) convertView;
        }
        return textView;
    }



}
