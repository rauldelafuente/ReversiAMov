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

    //Create the board logic, this is to say to the system where are the white, black or empty cells
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

    //To change the cell color
    public void setBoard(int pos, String color) {
        board[pos] = color;
    }

    //Get the color of a psotion to see who is there
    public String getColor(int pos) {
        return board[pos];
    }

    //Count white cells to cahnge the scoreboard
    public int whiteCount() {
        int count = 0;
        for (int i = 0; i < board.length; i++) {
            if (board[i].equals("w")) {
                count++;
            }
        }
        return count;
    }

    //Count black cells to cahnge the scoreboard
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

    //check who is the opponent, this is used to play, so we gan use the same algorithm to both colors
    public String getOponent(String player) {
        if (player.equals("w")) {
            return "b";
        } else {
            return "w";
        }
    }

    /*
    Set the possible movements
    What we do is chech the empty cells, and there we see up, down, left... to see if there is a possible movement in that empty cell
     */
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


    /*
    Change the colors after a movement
    After a movement we have to check the cells that has to change the color
    So we enter the current position, the player who has move and the list in which we want to store the cells that change color
     */

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


    //Get the list of movements
    public List<Integer> getMovList() {
        return mov;
    }

    //Format the list of movemetns
    public void formatMovList() {
        mov = new ArrayList<>();
    }

    //Format the list of change colors
    public void fromatColList() {
        col = new ArrayList<>();
    }

    /*
     *
     *
     * MOVEMENTS
     *
     *The same almortithm is used in all the directions (Only explined first one for that reason)
     */


    public boolean setMovDown(int pos, String player) {
        //If the next postion is out the board, false
        if (pos + 8 >= 64) {
            return false;
        }
        if (getColor(pos + 8).equals(getOponent(player))) {
            //If the next position is an opponent cell, continue
           return setMovDown(pos + 8, player);
        }

        if (getColor(pos + 8).equals(player) && getColor(pos).equals(getOponent(player))) {
            //If the next position is your color and the current postion is a opponent one, true
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

    public boolean setMovRigth(int pos, String player) {
        if (pos + 1 >= 64) {
            return false;
        }
        if (getColor(pos + 1).equals(getOponent(player))) {
            if((pos==7) || (pos==15) || (pos==23) || (pos==31) || (pos==39) || (pos==47) || (pos==55) || (pos==63)){
                return false;
            }
            return setMovRigth(pos + 1, player);
        }

        if (getColor(pos + 1).equals(player) && getColor(pos).equals(getOponent(player))) {
            return true;
        }
        return false;
    }

    public boolean setMovLeft(int pos, String player) {
        if (pos - 1 < 0) {
            return false;
        }
        if (getColor(pos - 1).equals(getOponent(player))) {
            if((pos==0) || (pos==8) || (pos==16) || (pos==24) || (pos==32) || (pos==40) || (pos==48) || (pos==56)){
                return false;
            }
            return setMovLeft(pos - 1, player);
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
            if((pos==7) || (pos==15) || (pos==23) || (pos==31) || (pos==39) || (pos==47) || (pos==55) || (pos==63)){
                return false;
            }
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
            if((pos==7) || (pos==15) || (pos==23) || (pos==31) || (pos==39) || (pos==47) || (pos==55) || (pos==63)){
                return false;
            }
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
            if((pos==0) || (pos==8) || (pos==16) || (pos==24) || (pos==32) || (pos==40) || (pos==48) || (pos==56)){
                return false;
            }
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
            if((pos==0) || (pos==8) || (pos==16) || (pos==24) || (pos==32) || (pos==40) || (pos==48) || (pos==56)){
                return false;
            }
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
     * The same as before, all of that follows the same logic, only first one
     */

    public boolean setColorsDown(int pos, String player, List<Integer> list) {
        //If the next position is out of bounds, false
        if (pos + 8 >= 64) {
            return false;
        }
        if (getColor(pos + 8).equals(getOponent(player))) {
            //If the next position is an opponent one, continue
            if (setColorsDown(pos + 8, player, list)) {
                // if the previous is true, add to the list to change color and return true
                list.add(pos + 8);
                return true;
            }
        }

        if (getColor(pos + 8).equals(player) && getColor(pos).equals(getOponent(player))) {
            //If the next position is yours and you are in a opponent one, true
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
        if ((pos + 1 >= 64)|| (pos==7) || (pos==15) || (pos==23) || (pos==31) || (pos==39) || (pos==47) || (pos==55) || (pos==63)) {
            return false;
        }
        if (getColor(pos + 1).equals(getOponent(player))) {
            if (setColorsRigth(pos + 1, player, list)) {
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



    //Move logic

    public void move(int position) {
        //If the position is in possible movements, continue
        if (mov.contains(position)) {
            //If the position already has a color, return
            if (getColor(position).equals("w") || getColor(position).equals("b")) {
                return;
            }
            //If it is turn 1 (black), set the position to black
            else if (turn1) {
                setBoard(position, "b");
                //Change turn, before check if the replay button has been clicked
                if (!rePlay || !CPUplay) {
                    turn1 = false;
                    turn2 = true;
                }
                //Create the change color list
                col = getColList(position, player, col);
                Log.d("list2", col.toString());
                //Loop for change the color of all the position inside the list
                for (int i = 0; i < col.size(); i++) {
                    setBoard(col.get(i), "b");
                }
                //The same as before but with white cells
                } else if (turn2) {
                setBoard(position, "w");
                if (!rePlay) {
                    turn1 = true;
                    turn2 = false;
                }
                col = getColList(position, player, col);
                Log.d("list2", col.toString());
                for (int i = 0; i < col.size(); i++) {
                    setBoard(col.get(i), "w");
                }
            }
            //Format the color list to re calculate in the next turn
            fromatColList();
            //Change the current player to the other one in case the replay button has not been clicked
            if(!rePlay) {
                changePlayer();
            }
            //in case it was clicked, now is false
            rePlay = false;
            //Format the possible moviments list to re calculate later
            formatMovList();
            //Calculate the mossible movements of the current player
            setMov(player);
            //add one to the counter to coun the movements (The buttons can be only clicked after count == 10)
            counter++;
            Log.d("list", mov.toString());
        }
    }


    public void CPUMov(){
        int index = (int)(Math.random() * (mov.size() + 1));
        int pos = mov.get(index);
        setBoard(pos, "w");
        col = getColList(pos, player, col);
        Log.d("list2", col.toString());
        for (int i = 0; i < col.size(); i++) {
            setBoard(col.get(i), "w");
        }
        changePlayer();
        formatMovList();
        setMov(player);
        counter++;
    }

    //Logic of the buttons (player cards)
    public boolean skipTurn(View v) {
        //Check if the minimum turn has been done
        if (counter > 10) {
            //Check who is playing (player 1 or 2)
            if (getTurnOne()) {
                //If the button has not been clicked, continue
                if (!usedsk1) {
                    //Put that the button has been clicked and set the new turn
                    usedsk1 = true;
                    setTurn(2);
                    return true;
                }
            }
        }
        return false;
    }

    //The same as before but to the other player
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

    //The same as before but with the replay button
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

    //Change the turn
    public void changePlayer() {
        if (player == "w") {
            player = "b";
        } else {
            player = "w";
        }
    }

    //Get the current player turn
    public boolean getTurnOne() {
        if (turn1) {
            return true;
        }
        return false;
    }

    //Set the turn to the other player, changing all the moviment lists and everyting
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


    //Replay = true
    public void setRePlay() {
        rePlay = true;
    }

    //For when we develop the cpu play
    public void setCPUplay(boolean b) {
        if (b) {
            CPUplay = true;
        } else {
            CPUplay = false;
        }
    }

}
