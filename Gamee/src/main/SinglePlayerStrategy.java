package main;

public class SinglePlayerStrategy implements StartGameStrategy {

    
    DifficultyStrategy difficulty;
    


    public void setStrategy(DifficultyStrategy difficulty)
    {
        this.difficulty=difficulty;
    }
    @Override
    public void makeMove(int row, int col,Game game) {
        game.makemove(row, col);//human
        //generate computer position
        //game.makemove(newposiiotns)
    }
}
