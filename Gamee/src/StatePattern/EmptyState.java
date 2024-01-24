package StatePattern;
import main.Game;
import main.Square;
import main.Turn;

public class EmptyState implements SquareState{

    @Override
    public void makeMove(Turn turn,Square square,Game g) {
        //this if ok?
        SquareState newState = (turn == Turn.X ? new XState() : new OState());
        square.changeState(newState);
        g.switchTurn();

    }

    @Override
    public boolean isSameState(SquareState sq2) {
        return false;
    }

}
