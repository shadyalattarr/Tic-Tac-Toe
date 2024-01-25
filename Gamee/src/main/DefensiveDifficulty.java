package main;

import StatePattern.EmptyState;
import StatePattern.XState;

public class DefensiveDifficulty implements DifficultyStrategy {

    @Override
    public int returnPosition(Game game) {
        DifficultyStrategy random= new RandomDifficulty(); 
        Square[][] board;
        board=game.getBoard();
        int row, col;
        for(row=0;row<3;row++)
        {
            for(col=0;col<3;col++)
            {
                if(board[row][col].getSquareState() instanceof EmptyState)
                {
                    board[row][col].changeState(new XState());
                    if(game.winningMove(row,col))
                    {
                        board[row][col].changeState(new EmptyState());
                        return (row*3) + col;
                    }
                    board[row][col].changeState(new EmptyState());
                }
            }
        }
        return random.returnPosition(game);
        
    }

}
