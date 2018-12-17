package com.example.raul.demoreversi;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

public class VsLocal extends Activity {

    GridViewAdapter grid;
    GridView gv;
    TextView tvW;
    TextView tvB;
    Board board = new Board();
    boolean usedsk1=false, usednp1=false, usedsk2=false, usednp2=false;
    Button btnskp, btnnp, btnskp2, btnnp2;


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

        grid = new GridViewAdapter(this, board, false);
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

    public void skipTurn(View v) {
        if(grid.getCounter()>10) {
            if (grid.getTurnOne()) {
                if (!usedsk1) {
                    btnskp.setBackgroundColor(Color.GREEN);
                    usedsk1 = true;
                    grid.setTurn(2);
                }
            }
        }
    }
    public void skipTurn2(View v) {
        if(grid.getCounter()>10) {
            if (!grid.getTurnOne()) {
                if (!usedsk2) {
                    btnskp2.setBackgroundColor(Color.GREEN);
                    usedsk2 = true;
                    grid.setTurn(1);
                }
            }
        }
    }

    public void rePlay(View v) {
        if(grid.getCounter()>10) {
            if (grid.getTurnOne()) {
                if (!usednp1) {
                    btnnp.setBackgroundColor(Color.GREEN);
                    usednp1 = true;
                    grid.setRePlay();
                }
            }
        }
    }
    public void rePlay2(View v) {
        if(grid.getCounter()>10) {
            if (!grid.getTurnOne()) {
                if(!usednp2) {
                    btnnp2.setBackgroundColor(Color.GREEN);
                    usednp2 = true;
                    grid.setRePlay();
                }
            }
        }
    }

}
