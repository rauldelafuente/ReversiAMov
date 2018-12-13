package com.example.raul.demoreversi;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class Board {

    private String []board;

    private List<Integer> mov = new ArrayList<>();

    public Board(){
        this.board = new String[]{
                "", "", "", "", "", "", "", "",
                "", "", "", "", "", "", "", "",
                "", "", "", "", "", "", "", "",
                "", "", "", "b", "w", "", "", "",
                "", "", "", "w", "b", "", "", "",
                "", "", "", "", "", "", "", "",
                "", "", "", "", "", "", "", "",
                "", "", "", "", "", "", "", ""
        };
    }

    public String[]getBoard(){
        return board;
    }

    public void setBoard(int pos, String color){
        board[pos] = color;
    }

    public String getColor(int pos){
        return board[pos];
    }

    public int whiteCount(){
        int count = 0;
        for(int i = 0; i<board.length; i++){
            if(board[i].equals("w")){
                count++;
            }
        }
        return count;
    }

    public int blackCount(){
        int count = 0;
        for(int i = 0; i<board.length; i++){
            if(board[i].equals("b")){
                count++;
            }
        }
        return count;
    }

    public int getLength(){
        return 64;
    }

    public boolean isInList(int pos) {
        for (int i = 0; i < mov.size(); i++) {
            if (pos==mov.get(i)) {
                return true;
            }
        }
        return false;
    }

    public String getOponent(String player){
        if (player.equals("w")){
            return "b";
        }
        else{
            return "w";
        }
    }

    public void setMov(String player){
        for(int i = 0;  i < board.length; i++){
            if(setMovUp(i, player) && board[i].equals("")){
                mov.add(i);
            }
            if(setMovDown(i, player)&& board[i].equals("")){
                mov.add(i);
            }
            if(setMovLeft(i, player)&& board[i].equals("")){
                mov.add(i);
            }
            if(setMovRigth(i, player)&& board[i].equals("")){
                mov.add(i);
            }
        }
    }

    public List<Integer> getList(){
        return mov;
    }

    public void formatList(){
        mov = new ArrayList<>();
    }

    /*
    *
    *
    * MOVEMENTS
    *
    *
     */


    public boolean setMovDown(int pos, String player){
        if(pos+8>=64){
            //Log.d("Te pasaste bajando", ""+pos);
            return false;
        }
        if(getColor(pos+8).equals(getOponent(player))){
            //Log.d("Recursivity", ""+pos);
            return setMovDown(pos+8, player);
        }

        if(getColor(pos+8).equals(player) && getColor(pos).equals(getOponent(player))){
            //Log.d("True", ""+pos);
            return true;
        }
        //Log.d("False", ""+pos);
        return false;
    }

    public boolean setMovUp(int pos, String player){
        if(pos-8<0){
            return false;
        }
        if(getColor(pos-8).equals(getOponent(player))){
            return setMovUp(pos-8, player);
        }

        if(getColor(pos-8).equals(player) && getColor(pos).equals(getOponent(player))){
            return true;
        }
        return false;
    }

    public boolean setMovLeft(int pos, String player) {
        if(pos+8>=64){
            return false;
        }
        if(getColor(pos+1).equals(getOponent(player))){
            return setMovLeft(pos+1, player);
        }

        if(getColor(pos+1).equals(player) && getColor(pos).equals(getOponent(player))){
            return true;
        }
        return false;
    }

    public boolean setMovRigth(int pos, String player){
        if(pos-1<0){
            return false;
        }
        if(getColor(pos-1).equals(getOponent(player))){
            return setMovRigth(pos-1, player);
        }

        if(getColor(pos-1).equals(player) && getColor(pos).equals(getOponent(player))){
            return true;
        }
        return false;
    }

}
