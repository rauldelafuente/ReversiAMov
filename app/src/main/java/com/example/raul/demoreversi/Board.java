package com.example.raul.demoreversi;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class Board {

    private String []board;

    private List<Integer> mov = new ArrayList<>();
    private List<Integer> col = new ArrayList<>();

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
            if(setMovRigthUp(i, player)&& board[i].equals("")){
                mov.add(i);
            }
            if(setMovRigthDown(i, player)&& board[i].equals("")){
                mov.add(i);
            }
            if(setMovLeftUp(i, player)&& board[i].equals("")){
                mov.add(i);
            }
            if(setMovLeftDown(i, player)&& board[i].equals("")){
                mov.add(i);
            }
        }
    }


    /*
    public List<Integer> getColList(int pos, String player, List<Integer>list) {

        setColorsUp(pos, player, list);
        setColorsDown(pos, player, list);
        setColorsLeft(pos, player, list);
        setColorsRigth(pos, player, list);
        setColorsLeftDown(pos, player, list);
        setColorsLeftUp(pos, player, list);
        setColorsRigthDown(pos, player, list);
        setColorsRigthUp(pos, player, list);

        return list;
    }
    */


    public List<Integer> getMovList(){
        return mov;
    }

    public void formatMovList(){
        mov = new ArrayList<>();
    }

    public void fromatColList(){
        col = new ArrayList<>();
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
            return false;
        }
        if(getColor(pos+8).equals(getOponent(player))){
            return setMovDown(pos+8, player);
        }

        if(getColor(pos+8).equals(player) && getColor(pos).equals(getOponent(player))){
            return true;
        }
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

    public boolean setMovRigthUp(int pos, String player){
        if(pos-7<0){
            return false;
        }
        if(getColor(pos-7).equals(getOponent(player))){
            return setMovRigthUp(pos-7, player);
        }

        if(getColor(pos-7).equals(player) && getColor(pos).equals(getOponent(player))){
            return true;
        }
        return false;
    }

    public boolean setMovRigthDown(int pos, String player){
        if(pos+9>=64){
            return false;
        }
        if(getColor(pos+9).equals(getOponent(player))){
            return setMovRigthDown(pos+9, player);
        }

        if(getColor(pos+9).equals(player) && getColor(pos).equals(getOponent(player))){
            return true;
        }
        return false;
    }

    public boolean setMovLeftUp(int pos, String player){
        if(pos-9<0){
            return false;
        }
        if(getColor(pos-9).equals(getOponent(player))){
            return setMovLeftUp(pos-9, player);
        }

        if(getColor(pos-9).equals(player) && getColor(pos).equals(getOponent(player))){
            return true;
        }
        return false;
    }

    public boolean setMovLeftDown(int pos, String player){
        if(pos+7>=64){
            return false;
        }
        if(getColor(pos+7).equals(getOponent(player))){
            return setMovLeftDown(pos+7, player);
        }

        if(getColor(pos+7).equals(player) && getColor(pos).equals(getOponent(player))){
            return true;
        }
        return false;
    }


    /*
    *
    * Change Colors when move
    *


    public boolean setColorsDown(int pos, String player, List<Integer> list){
        if(pos+8>=64){
            return false;
        }
        if(getColor(pos+8).equals(getOponent(player))){
            Log.d("CREATINGlist2", "Se Espera: "+(pos+8));
            if(setColorsDown(pos+8, player, list)){
                Log.d("CREATINGlist2", "Se mete: "+(pos+8));
                list.add(pos+8);
            }
        }

        if(getColor(pos+8).equals(player) && getColor(pos).equals(getOponent(player))){
            Log.d("CREATINGlist2", "Se Acepeta: "+(pos+8));
            return true;
        }
        return false;
    }

    public boolean setColorsUp(int pos, String player, List<Integer> list){
        if(pos-8<0){
            return false;
        }
        if(getColor(pos-8).equals(getOponent(player))){
            if(setColorsUp(pos-8, player, list)){
                list.add(pos-8);
            }
        }

        if(getColor(pos-8).equals(player) && getColor(pos).equals(getOponent(player))){
            return true;
        }
        return false;
    }

    public boolean setColorsLeft(int pos, String player, List<Integer>list) {
        if(pos+8>=64){
            return false;
        }
        if(getColor(pos+1).equals(getOponent(player))){
            if(setColorsLeft(pos+1, player, list)){
                list.add(pos+1);
            }
        }

        if(getColor(pos+1).equals(player) && getColor(pos).equals(getOponent(player))){
            return true;
        }
        return false;
    }

    public boolean setColorsRigth(int pos, String player, List<Integer>list){
        if(pos-1<0){
            return false;
        }
        if(getColor(pos-1).equals(getOponent(player))){
            if(setColorsRigth(pos-1, player, list)){
                list.add(pos-1);
            }
        }

        if(getColor(pos-1).equals(player) && getColor(pos).equals(getOponent(player))){
            return true;
        }
        return false;
    }

    public boolean setColorsRigthUp(int pos, String player, List<Integer>list){
        if(pos-7<0){
            return false;
        }
        if(getColor(pos-7).equals(getOponent(player))){
            if(setColorsRigthUp(pos-7, player, list)){
                list.add(pos-7);
            }
        }

        if(getColor(pos-7).equals(player) && getColor(pos).equals(getOponent(player))){
            return true;
        }
        return false;
    }

    public boolean setColorsRigthDown(int pos, String player, List<Integer>list){
        if(pos+9>=64){
            return false;
        }
        if(getColor(pos+9).equals(getOponent(player))){
            if(setColorsRigthDown(pos+9, player, list)){
                list.add(pos+9);
            }
        }

        if(getColor(pos+9).equals(player) && getColor(pos).equals(getOponent(player))){
            return true;
        }
        return false;
    }

    public boolean setColorsLeftUp(int pos, String player, List<Integer>list){
        if(pos-9<0){
            return false;
        }
        if(getColor(pos-9).equals(getOponent(player))){
            if(setColorsLeftUp(pos-9, player, list)){
                list.add(pos-9);
            }
        }

        if(getColor(pos-9).equals(player) && getColor(pos).equals(getOponent(player))){
            return true;
        }
        return false;
    }

    public boolean setColorsLeftDown(int pos, String player, List<Integer>list){
        if(pos+7>=64){
            return false;
        }
        if(getColor(pos+7).equals(getOponent(player))){
            if(setColorsLeftDown(pos+7, player, list)){
                list.add(pos+7);
            }
        }

        if(getColor(pos+7).equals(player) && getColor(pos).equals(getOponent(player))){
            return true;
        }
        return false;
    }
    */

}
