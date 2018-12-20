package com.example.raul.demoreversi;

import android.graphics.Color;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class Board {

    private String[] board;

    private List<Integer> mov = new ArrayList<>();
    private List<Integer> col = new ArrayList<>();

    public boolean rePlay = false;
    public boolean turn1 = true;
    public boolean turn2 = false;
    String player = "b";
    public int counter = 1;
    public boolean CPUplay = false;

    boolean usedsk1 = false, usednp1 = false, usedsk2 = false, usednp2 = false;

    public Board() {
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

    public String[] getBoard() {
        return board;
    }

    public void setBoard(int pos, String color) {
        board[pos] = color;
    }

    public String getColor(int pos) {
        return board[pos];
    }

    public int whiteCount() {
        int count = 0;
        for (int i = 0; i < board.length; i++) {
            if (board[i].equals("w")) {
                count++;
            }
        }
        return count;
    }

    public int blackCount() {
        int count = 0;
        for (int i = 0; i < board.length; i++) {
            if (board[i].equals("b")) {
                count++;
            }
        }
        return count;
    }

    public int getLength() {
        return 64;
    }

    public boolean isInList(int pos) {
        for (int i = 0; i < mov.size(); i++) {
            if (pos == mov.get(i)) {
                return true;
            }
        }
        return false;
    }

    public String getOponent(String player) {
        if (player.equals("w")) {
            return "b";
        } else {
            return "w";
        }
    }

    public void setMov(String player) {
        for (int i = 0; i < board.length; i++) {
            if (setMovUp(i, player) && board[i].equals("")) {
                mov.add(i);
            }
            if (setMovDown(i, player) && board[i].equals("")) {
                mov.add(i);
            }
            if (setMovLeft(i, player) && board[i].equals("")) {
                mov.add(i);
            }
            if (setMovRigth(i, player) && board[i].equals("")) {
                mov.add(i);
            }
            if (setMovRigthUp(i, player) && board[i].equals("")) {
                mov.add(i);
            }
            if (setMovRigthDown(i, player) && board[i].equals("")) {
                mov.add(i);
            }
            if (setMovLeftUp(i, player) && board[i].equals("")) {
                mov.add(i);
            }
            if (setMovLeftDown(i, player) && board[i].equals("")) {
                mov.add(i);
            }
        }
    }


    public List<Integer> getColList(int pos, String player, List<Integer> list) {

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


    public List<Integer> getMovList() {
        return mov;
    }

    public void formatMovList() {
        mov = new ArrayList<>();
    }

    public void fromatColList() {
        col = new ArrayList<>();
    }

    /*
     *
     *
     * MOVEMENTS
     *
     *
     */


    public boolean setMovDown(int pos, String player) {
        if (pos + 8 >= 64) {
            return false;
        }
        if (getColor(pos + 8).equals(getOponent(player))) {
           return setMovDown(pos + 8, player);
        }

        if (getColor(pos + 8).equals(player) && getColor(pos).equals(getOponent(player))) {
            return true;
        }
        return false;
    }

    public boolean setMovUp(int pos, String player) {
        if (pos - 8 < 0) {
            return false;
        }
        if (getColor(pos - 8).equals(getOponent(player))) {
            return setMovUp(pos - 8, player);
        }

        if (getColor(pos - 8).equals(player) && getColor(pos).equals(getOponent(player))) {
            return true;
        }
        return false;
    }

    public boolean setMovLeft(int pos, String player) {
        if (pos + 1 >= 64) {
            return false;
        }
        if (getColor(pos + 1).equals(getOponent(player))) {
            return setMovLeft(pos + 1, player);
        }

        if (getColor(pos + 1).equals(player) && getColor(pos).equals(getOponent(player))) {
            return true;
        }
        return false;
    }

    public boolean setMovRigth(int pos, String player) {
        if (pos - 1 < 0) {
            return false;
        }
        if (getColor(pos - 1).equals(getOponent(player))) {
            return setMovRigth(pos - 1, player);
        }

        if (getColor(pos - 1).equals(player) && getColor(pos).equals(getOponent(player))) {
            return true;
        }
        return false;
    }

    public boolean setMovRigthUp(int pos, String player) {
        if (pos - 7 < 0) {
            return false;
        }
        if (getColor(pos - 7).equals(getOponent(player))) {
            return setMovRigthUp(pos - 7, player);
        }

        if (getColor(pos - 7).equals(player) && getColor(pos).equals(getOponent(player))) {
            return true;
        }
        return false;
    }

    public boolean setMovRigthDown(int pos, String player) {
        if (pos + 9 >= 64) {
            return false;
        }
        if (getColor(pos + 9).equals(getOponent(player))) {
            return setMovRigthDown(pos + 9, player);
        }

        if (getColor(pos + 9).equals(player) && getColor(pos).equals(getOponent(player))) {
            return true;
        }
        return false;
    }

    public boolean setMovLeftUp(int pos, String player) {
        if (pos - 9 < 0) {
            return false;
        }
        if (getColor(pos - 9).equals(getOponent(player))) {
            return setMovLeftUp(pos - 9, player);
        }

        if (getColor(pos - 9).equals(player) && getColor(pos).equals(getOponent(player))) {
            return true;
        }
        return false;
    }

    public boolean setMovLeftDown(int pos, String player) {
        if (pos + 7 >= 64) {
            return false;
        }
        if (getColor(pos + 7).equals(getOponent(player))) {
            return setMovLeftDown(pos + 7, player);
        }

        if (getColor(pos + 7).equals(player) && getColor(pos).equals(getOponent(player))) {
            return true;
        }
        return false;
    }


    /*
     *
     * Change Colors when move
     *
     */

    public boolean setColorsDown(int pos, String player, List<Integer> list) {
        //Log.d("CRETEING:list2", "Eentramos en: "+(pos));
        if (pos + 8 >= 64) {
            return false;
        }
        if (getColor(pos + 8).equals(getOponent(player))) {
            //Log.d("CRETEING:list2", "Esperamos en: "+(pos+8));
            if (setColorsDown(pos + 8, player, list)) {
                //Log.d("CRETEING:list2", "Metemos: "+(pos+8));
                list.add(pos + 8);
                return true;
            }
        }

        if (getColor(pos + 8).equals(player) && getColor(pos).equals(getOponent(player))) {
            return true;
        }
        return false;
    }

    public boolean setColorsUp(int pos, String player, List<Integer> list) {
        if (pos - 8 < 0) {
            return false;
        }
        if (getColor(pos - 8).equals(getOponent(player))) {
            if (setColorsUp(pos - 8, player, list)) {
                list.add(pos - 8);
                return true;
            }
        }

        if (getColor(pos - 8).equals(player) && getColor(pos).equals(getOponent(player))) {
            return true;
        }
        return false;
    }

    public boolean setColorsLeft(int pos, String player, List<Integer> list) {
        if ((pos - 1 < 0)|| (pos==0) || (pos==8) || (pos==16) || (pos==24) || (pos==32) || (pos==40) || (pos==48) || (pos==56)) {
            return false;
        }
        if (getColor(pos - 1).equals(getOponent(player))) {
            if (setColorsLeft(pos - 1, player, list)) {
                list.add(pos - 1);
                return true;
            }
        }

        if (getColor(pos - 1).equals(player) && getColor(pos).equals(getOponent(player))) {
            return true;
        }
        return false;
    }

    public boolean setColorsRigth(int pos, String player, List<Integer> list) {
        Log.d("CREATING: list2", "Entrando en: "+(pos));
        if ((pos + 1 >= 64)|| (pos==7) || (pos==15) || (pos==23) || (pos==31) || (pos==39) || (pos==47) || (pos==55) || (pos==63)) {
            return false;
        }
        if (getColor(pos + 1).equals(getOponent(player))) {
            Log.d("CREATING: list2", "Esperando en: "+(pos+1));
            if (setColorsRigth(pos + 1, player, list)) {
                Log.d("CREATING: list2", "Se mete: "+(pos+1));
                list.add(pos + 1);
                return true;
            }
        }

        if (getColor(pos + 1).equals(player) && getColor(pos).equals(getOponent(player))) {
            return true;
        }
        return false;
    }

    public boolean setColorsRigthUp(int pos, String player, List<Integer> list) {
        if ((pos - 7 < 0)|| (pos==7) || (pos==15) || (pos==23) || (pos==31) || (pos==39) || (pos==47) || (pos==55) || (pos==63)) {
            return false;
        }
        if (getColor(pos - 7).equals(getOponent(player))) {
            if (setColorsRigthUp(pos - 7, player, list)) {
                list.add(pos - 7);
                return true;
            }
        }

        if (getColor(pos - 7).equals(player) && getColor(pos).equals(getOponent(player))) {
            return true;
        }
        return false;
    }

    public boolean setColorsRigthDown(int pos, String player, List<Integer> list) {
        if ((pos + 9 >= 64)|| (pos==7) || (pos==15) || (pos==23) || (pos==31) || (pos==39) || (pos==47) || (pos==55) || (pos==63)) {
            return false;
        }
        if (getColor(pos + 9).equals(getOponent(player))) {
            if (setColorsRigthDown(pos + 9, player, list)) {
                list.add(pos + 9);
                return true;
            }
        }

        if (getColor(pos + 9).equals(player) && getColor(pos).equals(getOponent(player))) {
            return true;
        }
        return false;
    }

    public boolean setColorsLeftUp(int pos, String player, List<Integer> list) {
        if ((pos - 9 < 0) || (pos==0) || (pos==8) || (pos==16) || (pos==24) || (pos==32) || (pos==40) || (pos==48) || (pos==56)) {
            return false;
        }
        if (getColor(pos - 9).equals(getOponent(player))) {
            if (setColorsLeftUp(pos - 9, player, list)) {
                list.add(pos - 9);
                return true;
            }
        }

        if (getColor(pos - 9).equals(player) && getColor(pos).equals(getOponent(player))) {
            return true;
        }
        return false;
    }

    public boolean setColorsLeftDown(int pos, String player, List<Integer> list) {
        if ((pos + 7 >= 64) || (pos==0) || (pos==8) || (pos==16) || (pos==24) || (pos==32) || (pos==40) || (pos==48) || (pos==56)) {
            return false;
        }
        if (getColor(pos + 7).equals(getOponent(player))) {
            if (setColorsLeftDown(pos + 7, player, list)) {
                list.add(pos + 7);
                return true;
            }
        }

        if (getColor(pos + 7).equals(player) && getColor(pos).equals(getOponent(player))) {
            return true;
        }
        return false;
    }

    public void move(int position) {
        if (mov.contains(position)) {

            if (getColor(position).equals("w") || getColor(position).equals("b")) {
                return;
            } else if (turn1) {
                setBoard(position, "b");
                if (!rePlay) {
                    turn1 = false;
                    turn2 = true;
                }
                rePlay = false;
                col = getColList(position, player, col);
                Log.d("list2", col.toString());
                for (int i = 0; i < col.size(); i++) {
                    setBoard(col.get(i), "b");
                }

            } else if (turn2) {
                setBoard(position, "w");
                if (!rePlay) {
                    turn1 = true;
                    turn2 = false;
                }
                rePlay = false;
                col = getColList(position, player, col);
                Log.d("list2", col.toString());
                for (int i = 0; i < col.size(); i++) {
                    setBoard(col.get(i), "w");
                }
            }
            col = new ArrayList<>();
            changePlayer();
            formatMovList();
            setMov(player);
            counter++;
            Log.d("list", mov.toString());
        }
    }

    public boolean skipTurn(View v) {
        if (counter > 10) {
            if (getTurnOne()) {
                if (!usedsk1) {
                    usedsk1 = true;
                    setTurn(2);
                    return true;
                }
            }
        }
        return false;
    }

    public boolean skipTurn2(View v) {
        if (counter > 10) {
            if (!getTurnOne()) {
                if (!usedsk2) {
                    usedsk2 = true;
                    setTurn(1);
                    return true;
                }
            }
        }
        return false;
    }

    public boolean rePlay(View v) {
        if (counter > 10) {
            if (getTurnOne()) {
                if (!usednp1) {
                    usednp1 = true;
                    setRePlay();
                    return true;
                }
            }
        }
        return false;
    }

    public boolean rePlay2(View v) {
        if (counter > 10) {
            if (!getTurnOne()) {
                if (!usednp2) {
                    usednp2 = true;
                    setRePlay();
                    return true;
                }
            }
        }
        return false;
    }

    public void changePlayer() {
        if (player == "w") {
            player = "b";
        } else {
            player = "w";
        }
    }

    public boolean getTurnOne() {
        if (turn1) {
            return true;
        }
        return false;
    }

    public void setTurn(int player) {
        if (player == 1) {
            turn1 = true;
            turn2 = false;
            changePlayer();
            formatMovList();
            setMov("b");
            mov = getMovList();
        } else {
            turn2 = true;
            turn1 = false;
            changePlayer();
            formatMovList();
            setMov("w");
            mov = getMovList();
        }
    }


    public void setRePlay() {
        rePlay = true;
    }

    public void setCPUplay(boolean b) {
        if (b) {
            CPUplay = true;
        } else {
            CPUplay = false;
        }
    }

}
