package main;

public class SinglePlayerStrategy implements StartGameStrategy {

    DifficultyStrategy difficulty;

    SinglePlayerStrategy(DifficultyStrategy difficulty) {
        this.difficulty = difficulty;
    }

    @Override
    public void makeMove(int row, int col, Game game) {

        game.makemove(row, col);// human

        // generate computer position
        if (game.isEmptySq()) {
            int newposition = difficulty.returnPosition(game);
            game.makemove(newposition / 3, newposition % 3);
        }
    }
}
