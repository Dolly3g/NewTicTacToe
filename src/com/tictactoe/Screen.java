package com.tictactoe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Screen implements GameView {
    private JFrame board = new JFrame("Board");
    private JPanel ticTacToeBoard = new JPanel(new GridLayout(3, 3));
    private JPanel bottomBoard = new JPanel(new GridLayout(1, 3));
    private GameController controller;
    private StatusLabel statusLabel = new StatusLabel();
    private CellButton[] cellButtons = new CellButton[9];

    public Screen() {
        setProperty();
        setContents();

    }

    public class MarkListener implements ActionListener {
        private int location;

        public MarkListener(int location) {

            this.location = location;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            controller.recordMark(location);
        }
    }

    void setProperty() {
        board.setVisible(true);
        board.setSize(400, 400);
        ticTacToeBoard.setVisible(true);
        ticTacToeBoard.setSize(350, 350);
        board.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        board.add(ticTacToeBoard);
        board.add(bottomBoard);
        board.setLayout(new GridLayout(2, 1));
    }

    void setContents() {

        statusLabel.setVisible(true);
        bottomBoard.add(new JLabel("  "));
        bottomBoard.add(statusLabel);
        bottomBoard.add(new JLabel("  "));

        for (int i = 0; i < 9; i++) {
            CellButton button = new CellButton();
            button.addActionListener(new MarkListener(i));
            ticTacToeBoard.add(button);
            cellButtons[i] = button;
        }
    }

    public void attachController(GameController controller) {
        this.controller = controller;
        controller.updateView();
    }

    @Override
    public void updateStatus() {
        controller.visitStatus(statusLabel);
    }

    @Override
    public void updateBoard() {
        for (int i = 0; i < 9; i++) {
            controller.visitCell(i, cellButtons[i]);
        }
    }
}
