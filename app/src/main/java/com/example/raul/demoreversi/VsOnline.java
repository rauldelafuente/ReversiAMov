package com.example.raul.demoreversi;

import android.app.Activity;
import android.os.Bundle;
import android.widget.GridView;
import android.widget.TextView;

public class VsOnline extends Activity {
    public static final int  SERVER = 0;
    public static final int  CLIENT = 1;

    GridViewAdapter grid;
    GridView gv;
    TextView tvW;
    TextView tvB;
    Board board = new Board();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vs_online);
    }
}
