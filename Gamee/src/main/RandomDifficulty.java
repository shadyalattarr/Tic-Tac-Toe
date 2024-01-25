package main;

import StatePattern.EmptyState;

public class RandomDifficulty implements DifficultyStrategy {

    @Override
    public int returnPosition(Game game) {
        Square[][] temp;
        int row, col, randomNum=0,range = 8;
        
        Boolean flag = false;
        while (!flag) {
            randomNum = (int) (Math.random() * range);
            temp = game.getBoard();
            row = randomNum / 3;
            col = randomNum % 3;
            if (temp[row][col].getSquareState() instanceof EmptyState) 
                flag=true;
        }
        return randomNum;
    }

}
