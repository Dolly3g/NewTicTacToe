package com.tictactoe;

public class Main {

    public static void main(String[] args) {
        Screen screen = new Screen();
        Game game = new Game();
        GameController controller = new GameController(screen, game);
        screen.attachController(controller);
    }
}
