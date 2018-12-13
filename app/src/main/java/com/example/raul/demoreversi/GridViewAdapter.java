package com.example.raul.demoreversi;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class GridViewAdapter extends BaseAdapter {

    Bitmap empty_cell;
    Bitmap white_cell;
    Bitmap black_cell;
    Bitmap mov_cell;

    public boolean calculated = false;

    public boolean turn1 = true;
    public boolean turn2 = false;

    private Context mContext;
    private LayoutInflater thisInflater;

    private Board board;

    String player;

    public List<Integer>mov = new ArrayList<>();

    public GridViewAdapter(Context mContext, Board board){
        this.mContext=mContext;
        this.thisInflater = LayoutInflater.from(mContext);
        this.board = board;
    }

    @Override
    public int getCount() {
        return board.getLength();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        if(turn1){
            player = "b";
        }

        if(turn2){
            player = "w";
        }

        View view= thisInflater.inflate(R.layout.cell_layout, parent, false);
        final ImageView img = view.findViewById(R.id.image);
        empty_cell = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.green_square);
        white_cell = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.white_board);
        black_cell = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.black_board);
        //mov_cell = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.grey_square);

        img.setImageBitmap(empty_cell);

        if (board.getColor(position).equals("w")){
            img.setImageBitmap(white_cell);
        }
        if (board.getColor(position).equals("b")) {
            img.setImageBitmap(black_cell);
        }

        if(calculated==false) {
            board.setMov(player);

            mov = board.getList();

            calculated=true;
        }

        Log.d("list", mov.toString());

        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mov.contains(position)) {

                    if (board.getColor(position).equals("w") || board.getColor(position).equals("b")) {
                        return;
                    } else if (turn1) {
                        img.setImageBitmap(black_cell);
                        turn1 = false;
                        turn2 = true;
                        board.setBoard(position, "b");
                    } else if (turn2) {
                        img.setImageBitmap(white_cell);
                        turn1 = true;
                        turn2 = false;
                        board.setBoard(position, "w");
                    }

                    board.formatList();
                    calculated = false;
                    changePlayer();
                    if (calculated == false) {
                        board.setMov(player);

                        mov = board.getList();

                        calculated = true;
                    }
                    Log.d("list", mov.toString());
                }
            }

        });

        return view;
    }

    public int getWhite(){
        return board.whiteCount();
    }

    public int getBlack(){
        return board.blackCount();
    }

    public void changePlayer(){
        if(player=="w"){
            player = "b";
        }
        else{
            player = "w";
        }
    }

}