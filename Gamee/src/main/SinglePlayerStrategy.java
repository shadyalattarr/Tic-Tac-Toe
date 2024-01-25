package main;

import StatePattern.EmptyState;

public class SinglePlayerStrategy implements StartGameStrategy {

    DifficultyStrategy difficulty;

    SinglePlayerStrategy(DifficultyStrategy difficulty) {
        this.difficulty = difficulty;
    }

    @Override
    public void makeMove(int row, int col, Game game) {
        boolean temp=game.getBoard()[row][col].getSquareState() instanceof EmptyState;

        game.makemove(row, col);// human

        if (temp) {

            // generate computer position
            if (game.isEmptySq() && game.getWinner() == false) {
                int newposition = difficulty.returnPosition(game);
                game.makemove(newposition / 3, newposition % 3);
            }
        }
    }
}
