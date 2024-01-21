import static java.lang.Math.abs;

import javax.swing.JOptionPane;

public class Game {
    private Turn[][] board= {
                            {null,null,null},
                            {null,null,null},
                            {null,null,null}
                            };
    private Boolean winner=false;
    public Game()
    {
        //Turn[][] board=initialize();
    }

    /*public Turn[][] initialize() //I dont know why it doesnt update in variable after its called in constructor so just left it like this
    {
        Turn[][] board= {
                        {null,null,null},
                        {null,null,null},
                        {null,null,null}
                        };
        return board;
    }*/

    public Boolean isValid(int row, int col)
    {
        if(board[row][col]!=null)
            return false;
        else 
            return true;
    }

    public Turn[][] makeMove(int row, int col, Turn turn)
    {
        if(isValid(row, col))
        {
            board[row][col]= turn;
            if(winningMove(row, col))
                {
                    if(turn.getValue()==1){ 
                        System.out.println("X WINS!");
                        winner=true;
                    }
                    else{
                        System.out.println("O WINS!");
                        winner=true;
                    }
                }
            return board;
        }else
        {
            JOptionPane.showMessageDialog(null, "Square is used ");
            return null;
        }
        
    }

    public Boolean getWinner()
    {
        return winner;
    }

    private Boolean winningMove(int row, int col)
        {
            if(abs(row-col)==2 || row==col)// one of the corners + middle
                if(checkDiagonal(row,col))
                    return true;
            if(checkHorizontalVertical(row,col))
                return true;
            return false;
        }
        
        private Boolean checkHorizontalVertical(int row, int col)
        {
            Boolean flag=true;
            for(int i=0;i<3;i++)
            {
                if(board[row][col]!=board[row][i]) //check horizontal
                    flag=false;
                if(!flag)
                    break;
            }
            if(flag)
                return true;
            flag=true;//if horizontal check failed
            for(int i=0;i<3;i++)
            {
                if(board[row][col]!=board[i][col]) //check vertical
                    flag=false;
                if(!flag)
                    break;
            }
            if(flag)
                return true;
            return false;//if neither happens
        }
        
        private Boolean checkDiagonal(int row, int col)
        {
            if(row==col)
            {
                if(board[row][col]==board[0][0] && board[row][col]==board[1][1] && board[row][col]==board[2][2])
                    return true;
                return false;
            }
            else
            {
                if(board[row][col]==board[0][2] && board[row][col]==board[1][1] && board[row][col]==board[2][0])                    return true;
                return false;
            }
        }

        public Turn switchTurn(Turn turn)
        {
            if(turn==turn.O)
                return Turn.X;
            else
                return turn.O;
        }
}
