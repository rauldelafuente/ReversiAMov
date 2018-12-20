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
    Bitmap mov_cell;

    private Context mContext;
    private LayoutInflater thisInflater;

    private Board board;

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

        return view;
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }

}