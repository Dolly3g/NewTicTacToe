package com.tictactoe;

public class Game {
    enum Mark {
        Free, One, Two
    }

    private Mark[] marks = new Mark[9];
    boolean isFirstPlayersTurn = true;

    public Game() {
        for (int i = 0; i < 9; i++) {
            marks[i] = Mark.Free;
        }
    }

    public void markOn(int location, CellView cell) {
        Mark mark = marks[location];
        boolean gameWon = hasPlayerWon(Mark.One) || hasPlayerWon(Mark.Two);

        if (mark == Mark.Free) cell.unMark(gameWon);
        if (mark == Mark.One) cell.markForPlayer1();
        if (mark == Mark.Two) cell.markForPlayer2();
    }

    public void recordMark(int location) {
        marks[location] = isFirstPlayersTurn ? Mark.One : Mark.Two;
        isFirstPlayersTurn = !isFirstPlayersTurn;
    }

    public void visitStatus(StatusView statusView) {
        if (hasPlayerWon(Mark.One)) {
            statusView.informPlayer1Win();
            return;
        }
        if (hasPlayerWon(Mark.Two)) {
            statusView.informPlayer2Win();
            return;
        }
        if (areAllMarked()) {
            statusView.informGameDraw();
            return;
        }

        if (isFirstPlayersTurn)
            statusView.informPlayer1();
        else statusView.informPlayer2();
    }

    private boolean hasPlayerWon(Mark playerMark) {
        if (checkRows(playerMark)) return true;
        if (checkColumns(playerMark)) return true;
        return checkDiagonals(playerMark);
    }

    private boolean checkDiagonals(Mark playerMark) {
        int la = 0, b = 4, lc = 8, ra = 2, rc = 6;
        if (playerMark == marks[b] && marks[la] == marks[b] && marks[b] == marks[lc])
            return true;
        if (playerMark == marks[b] && marks[ra] == marks[b] && marks[b] == marks[rc])
            return true;
        return false;
    }

    private boolean checkColumns(Mark playerMark) {
        for (int col = 0; col < 3; col++) {
            boolean won = true;
            for (int row = 0; row < 3; row++) {
                int location = row * 3 + col;
                won = won && (marks[location] == playerMark);
            }
            if (won) return true;
        }
        return false;
    }

    private boolean checkRows(Mark playerMark) {
        for (int row = 0; row < 3; row++) {
            boolean won = true;
            for (int col = 0; col < 3; col++) {
                int location = row * 3 + col;
                won = won && (marks[location] == playerMark);
            }
            if (won) return true;
        }
        return false;
    }

    private boolean areAllMarked() {
        for (int i = 0; i < 9; i++) {
            if (marks[i] == Mark.Free) return false;
        }
        return true;
    }
}
