package com.tictactoe;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

public class GameTest {
    @Test
    public void visitStatusInformsThatItIsFirstPlayersTurnInTheBeginning() {
        Game game = new Game();
        StatusView view = mock(StatusView.class);
        game.visitStatus(view);
        verify(view, times(1)).informPlayer1();
    }
    @Test
    public void visitStatusInformsThatItIsSecondPlayersTurnAfterFirstMove() {
        Game game = new Game();
        StatusView view = mock(StatusView.class);
        game.recordMark(0);
        game.visitStatus(view);
        verify(view, times(1)).informPlayer2();
    }
    void markAll(Game game, int[] matrix){
        List<Integer> input  = new ArrayList<Integer>();
        List<Integer> x = new ArrayList<Integer>();
        List<Integer> y = new ArrayList<Integer>();
        for (int i = 0; i < 9; i++) {
            if(matrix[i]==0) x.add(i);
            if(matrix[i]==1) y.add(i);
        }
        while (!x.isEmpty()){
            input.add(x.remove(0));
            if(!y.isEmpty())
                input.add(y.remove(0));
        }
        for (Integer location : input) {
            game.recordMark(location);
        }
    }
    @Test
    public void visitStatusInformsThatItIsADrawAtTheEnd() {
        int[] markLocations = new int[]{
            0, 1, 0,
            0, 1, 1,
            1, 0, 0
        };
        Game game = new Game();
        StatusView view = mock(StatusView.class);
        markAll(game, markLocations);
        game.visitStatus(view);
        verify(view, times(1)).informGameDraw();
    }
    @Test
    public void visitStatusInformsThatFirstPlayerWins() {
        int[] markLocations = new int[]{
                0, 0, 0,
                8, 1, 1,
                8, 8, 8
        };
        Game game = new Game();
        StatusView view = mock(StatusView.class);
        markAll(game, markLocations);
        game.visitStatus(view);
        verify(view, times(1)).informPlayer1Win();
    }
    @Test
    public void visitStatusInformsThatSecondPlayerWins() {
        int[] markLocations = new int[]{
                0, 0, 1,
                0, 1, 8,
                1, 8, 8
        };
        Game game = new Game();
        StatusView view = mock(StatusView.class);
        markAll(game, markLocations);
        game.visitStatus(view);
        verify(view, times(1)).informPlayer2Win();
    }
}