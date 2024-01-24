package StatePattern;
import main.Game;
import main.Square;
import main.Turn;

public interface SquareState {
    //Square(context)
    public void makeMove(Turn turn,Square square,Game g);
    public boolean isSameState(SquareState sq2);
}
