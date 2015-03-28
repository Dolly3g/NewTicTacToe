package com.tictactoe;

public class GameController {
    private GameView view;
    private Game game;

    public GameController(GameView view, Game game) {
        this.view = view;
        this.game = game;
    }

    public void updateView() {
        view.updateBoard();
        view.updateStatus();
    }

    public void visitCell(int location, CellView cell) {
        game.markOn(location, cell);
    }

    public void recordMark(int location) {
        game.recordMark(location);
        updateView();
    }

    public void visitStatus(StatusView statusView) {
        game.visitStatus(statusView);
    }
}
