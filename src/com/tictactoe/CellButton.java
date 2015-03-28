package com.tictactoe;

import javax.swing.*;

public class CellButton extends JButton implements CellView{

    @Override
    public void markForPlayer1() {
        setText("X");
        setEnabled(false);
    }

    @Override
    public void markForPlayer2() {
        setText("O");
        setEnabled(false);
    }

    @Override
    public void unMark(boolean gameOver) {
        setText(" ");
        setEnabled(!gameOver);
    }
}
