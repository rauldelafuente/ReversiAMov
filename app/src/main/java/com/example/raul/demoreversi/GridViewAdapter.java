package com.example.raul.demoreversi;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

public class GridViewAdapter extends BaseAdapter {

    Bitmap empty_cell;
    Bitmap white_cell;
    Bitmap black_cell;

    private Context mContext;
    private LayoutInflater thisInflater;

    private Board board;

    //Constructor
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

    //To change the view of the grid, look in the board the names and change the view depending of it its w, b or empty
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        View view= thisInflater.inflate(R.layout.cell_layout, parent, false);
        final ImageView img = view.findViewById(R.id.image);
        empty_cell = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.green_square);
        white_cell = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.white_board);
        black_cell = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.black_board);

        img.setImageBitmap(empty_cell);

        if (board.getColor(position).equals("w")){
            img.setImageBitmap(white_cell);
        }
        if (board.getColor(position).equals("b")) {
            img.setImageBitmap(black_cell);
        }

        return view;
    }

    //For notify a change and that everything has to be recalculated
    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }

}