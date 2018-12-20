package com.example.raul.demoreversi;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class VsLocal extends Activity {

    GridViewAdapter grid;
    GridView gv;
    TextView tvW;
    TextView tvB;
    Board board = new Board();
    Button btnskp, btnnp, btnskp2, btnnp2;
    String player = "b";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vs_local);

        tvW = findViewById(R.id.whiteResult);
        tvB = findViewById(R.id.blackResult);

        btnskp = findViewById(R.id.btnskp);
        btnnp = findViewById(R.id.btnnp);
        btnskp2 = findViewById(R.id.btnskp2);
        btnnp2 = findViewById(R.id.btnnp2);

        grid = new GridViewAdapter(this, board);
        gv = findViewById(R.id.localBoard);

        board.setMov(player);

        gv.setAdapter(grid);
        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                board.move (position);
                grid.notifyDataSetChanged();
                tvW.setText("" + board.whiteCount());
                tvB.setText("" + board.blackCount());
            }
        });

        tvW.setText("" + board.whiteCount());
        tvB.setText("" + board.blackCount());

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onMenuItemSelected(int featureId, MenuItem item) {
        return super.onMenuItemSelected(featureId, item);
    }

    public void skipTurn(View v){
        board.skipTurn(v);
    }

    public void skipTurn2(View v){
        board.skipTurn2(v);
    }

    public void rePlay(View v){
        board.rePlay(v);
    }

    public void rePlay2(View v){
        board.rePlay2(v);
    }

}
