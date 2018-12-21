package com.example.raul.demoreversi;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

public class VsCPU extends Activity{

    boolean used1=false, used2=false;
    GridView gv;
    TextView tvW;
    TextView tvB;
    Board board = new Board();
    Button btnskp, btnnp;
    GridViewAdapter grid;
    boolean cpu = true;
    String player = "b";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vs_cpu);

        tvW = findViewById(R.id.whiteResult);
        tvB = findViewById(R.id.blackResult);

        btnskp = findViewById(R.id.btnskp);
        btnnp = findViewById(R.id.btnnp);

        grid = new GridViewAdapter(this, board);
        gv = findViewById(R.id.localBoard);
        gv.setAdapter(grid);

        board.setCPUplay(cpu);

        board.setMov(player);

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

                //CPUmove();
            }
        });

        tvW.setText("" + board.whiteCount());
        tvB.setText("" + board.blackCount());
    }

    public void CPUmove(){
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        board.CPUMov();
        grid.notifyDataSetChanged();
    }

    public void skipTurn(View v){
        board.skipTurn(v);
    }

    public void skipTurn2(View v){
        board.skipTurn2(v);
    }

}
