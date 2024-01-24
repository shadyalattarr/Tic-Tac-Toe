package main;
import StatePattern.SquareState;

public class Square {
    //this class serves for State pattern
    private SquareState state;
    int row,column;

    Square(int row,int column,SquareState initialState)
    {
        this.row = row;
        this.column = column;
        this.state = initialState;
    }

    public void changeState(SquareState newState)
    {
        this.state = newState;
    }

    public SquareState getSquareState()
    {
        return state;
    }

    //delete game
    public void makeMove(Turn turn,Game game)
    {
        state.makeMove(turn, this,game);
    }

    public boolean isSameState(Square sq2)
    {
        return state.isSameState(sq2.getSquareState());
    }
}
