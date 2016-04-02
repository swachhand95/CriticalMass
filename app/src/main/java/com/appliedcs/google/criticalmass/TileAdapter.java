package com.appliedcs.google.criticalmass;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
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
    int numTiles;

    public TileAdapter(Context context, int numTiles) {
        this.context = context;
        this.numTiles = numTiles;
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
            textView.setText("" + position);
            textView.setLayoutParams(new GridView.LayoutParams(100, 100));
 //           textView.setBackgroundResource(R.color.black);
            textView.setTextSize(24.5f);
            textView.setPadding(0, 8, 0, 8);

            textView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

        } else {
            textView = (TextView) convertView;
        }
        return textView;
    }
}
