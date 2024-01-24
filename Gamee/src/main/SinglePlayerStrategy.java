package main;

public class SinglePlayerStrategy implements StartGameStrategy {

    DifficultyStrategy difficulty;
    @Override
    public void startGame() {

    }
    public void setStrategy(DifficultyStrategy difficulty)
    {
        this.difficulty=difficulty;
    }
}
