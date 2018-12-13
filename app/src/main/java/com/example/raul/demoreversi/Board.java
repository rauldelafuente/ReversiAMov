package com.example.raul.demoreversi;

import java.util.ArrayList;
import java.util.List;

public class Board {

    private String [] board;

    private List<Integer> mov = new ArrayList<>();

    public Board(){
        this.board = new String[]{
                "", "", "", "", "", "", "", "",
                "", "", "", "", "", "", "", "",
                "", "", "", "", "", "", "", "",
                "", "", "", "w", "b", "", "", "",
                "", "", "", "b", "w", "", "", "",
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
            if(setMovUp(i, player)!=-1){
                mov.add(i);
            }
            if(setMovDown(i, player)!=-1){
                mov.add(i);
            }
            if(setMovLeft(i, player)!=-1){
                mov.add(i);
            }
            if(setMovRigth(i, player)!=-1){
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


    public int setMovDown(int pos, String player){
        if(pos+8>=64){
            return -1;
        }
        if(getColor(pos+8).equals(getOponent(player))){
            setMovDown(pos+8, player);
        }

        if(getColor(pos+8).equals(player) && getColor(pos).equals(getOponent(player))){
            return pos;
        }
        return -1;
    }

    public int setMovUp(int pos, String player){
        if(pos-8<0){
            return -1;
        }
        if(getColor(pos-8).equals(getOponent(player))){
            setMovUp(pos-8, player);
        }

        if(getColor(pos-8).equals(player) && getColor(pos).equals(getOponent(player))){
            return pos;
        }
        return -1;
    }

    public int setMovLeft(int pos, String player) {
        if(pos+8>=64){
            return -1;
        }
        if(getColor(pos+1).equals(getOponent(player))){
            setMovLeft(pos+1, player);
        }

        if(getColor(pos+1).equals(player) && getColor(pos).equals(getOponent(player))){
            return pos;
        }
        return -1;
    }

    public int setMovRigth(int pos, String player){
        if(pos-1<0){
            return -1;
        }
        if(getColor(pos-1).equals(getOponent(player))){
            setMovRigth(pos-1, player);
        }

        if(getColor(pos-1).equals(player) && getColor(pos).equals(getOponent(player))){
            return pos;
        }
        return -1;
    }

}
