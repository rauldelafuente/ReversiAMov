package com.example.raul.demoreversi;

import android.app.Activity;
import android.os.Bundle;
import android.widget.GridView;
import android.widget.TextView;

public class VsLocal extends Activity {

    GridViewAdapter grid;
    GridView gv;
    TextView tvW;
    TextView tvB;
    Board board = new Board();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vs_local);

        tvW = findViewById(R.id.whiteResult);
        tvB = findViewById(R.id.blackResult);

        grid = new GridViewAdapter(this, board);
        gv = findViewById(R.id.localBoard);

        gv.setAdapter(grid);

        tvW.setText("" + grid.getWhite());
        tvB.setText("" + grid.getBlack());

        /*
        <ImageView
        (...)
        android:adjustViewBounds="true" />
         */
    }

    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }



}
