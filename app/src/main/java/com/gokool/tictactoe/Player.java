package com.gokool.tictactoe;

/**
 * Created by gokul on 10/24/2017.
 */

public final class Player {
    final String p1 = "X", p2 = "0";
    String player ;
    public Player() {
        setPlayer(p1);
    }

    public String getPlayer() {
        return player;
    }

    public void setPlayer(String player) {
        this.player = player;
    }

    public String next() {
        switch (this.player) {
            case p1: {
                setPlayer(p2);
                return p2;
            }
            case p2: {

                setPlayer(p1);
                return p1;
            }
            default:
                return "empty";
        }
    }
}
