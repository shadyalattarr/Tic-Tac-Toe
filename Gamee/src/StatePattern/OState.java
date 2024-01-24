package StatePattern;

import javax.swing.JOptionPane;

import main.Game;
import main.Square;
import main.Turn;

public class OState implements SquareState{

    @Override
    public void makeMove(Turn turn,Square square,Game g) {
        JOptionPane.showMessageDialog(null, "Square is used ");

    }

    @Override
    public boolean isSameState(SquareState sq2) {
        if(sq2 instanceof OState)
            return true;
        return false;
    }
    
    
}
