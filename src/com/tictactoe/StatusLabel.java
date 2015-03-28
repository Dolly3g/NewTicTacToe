package com.tictactoe;

import javax.swing.*;

public class StatusLabel extends JLabel implements StatusView{

    @Override
    public void informPlayer1() {
        setText("Place X");
    }

    @Override
    public void informPlayer2() {
        setText("Place O");
    }

    @Override
    public void informPlayer1Win() {
        setText("X Wins");
    }

    @Override
    public void informPlayer2Win() {
        setText("O Wins");
    }

    @Override
    public void informGameDraw() {
        setText("Game Draw");
    }
}
