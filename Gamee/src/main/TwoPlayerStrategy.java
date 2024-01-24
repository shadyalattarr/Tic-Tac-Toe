package main;

public class TwoPlayerStrategy implements StartGameStrategy {

    
    
    @Override
    public void makeMove(int row, int col,Game game) {
        game.makemove(row, col);
    }

}
