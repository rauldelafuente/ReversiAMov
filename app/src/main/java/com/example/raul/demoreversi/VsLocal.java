package com.example.raul.demoreversi;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

public class VsLocal extends Activity {

    GridViewAdapter grid;
    GridView gv;
    TextView tvW;
    TextView tvB;
    TextView tvt;
    Board board = new Board();
    Button btnskp, btnnp, btnskp2, btnnp2;
    String player = "b";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vs_local);

        //Get all hte views from the layout
        tvW = findViewById(R.id.whiteResult);
        tvB = findViewById(R.id.blackResult);
        tvt = findViewById(R.id.turn);

        btnskp = findViewById(R.id.btnskp);
        btnnp = findViewById(R.id.btnnp);
        btnskp2 = findViewById(R.id.btnskp2);
        btnnp2 = findViewById(R.id.btnnp2);

        //initialize adapter and the grid view
        grid = new GridViewAdapter(this, board);
        gv = findViewById(R.id.localBoard);

        //Set the movements of the first movement
        board.setMov(player);

        //set adapter
        gv.setAdapter(grid);

        tvt.setText(getResources().getString(R.string.black));

        //When you click the grid view
        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //do the move to that postion
                board.move (position);
                //Notify to the adapter that something changed
                grid.notifyDataSetChanged();
                //set the scoreboard of the activity
                tvW.setText("" + board.whiteCount());
                tvB.setText("" + board.blackCount());


                if(board.isFinish()){
                    finished();
                }

                if(board.getTurnOne()){
                    tvt.setText(getResources().getString(R.string.black));
                }
                else{
                    tvt.setText(getResources().getString(R.string.white));
                }
            }
        });

        //Set the first scoreboard
        tvW.setText("" + board.whiteCount());
        tvB.setText("" + board.blackCount());

        /*
        <ImageView
        (...)
        android:adjustViewBounds="true" />
         */
    }

    public void finished(){
        String winner;
        if(board.whiteCount()>board.blackCount()){
            winner = "White";
        }
        else{
            winner = "Black";
        }
        AlertDialog d = new AlertDialog.Builder(this)
                .setTitle(getResources().getString(R.string.game_finished))
                .setMessage(getResources().getString(R.string.winner) + winner)
                .create();
        d.show();
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


    //Buttons methods. Those methods are referenced to the board ones
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
