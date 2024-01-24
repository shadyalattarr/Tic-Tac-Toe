package main;
import static java.lang.Math.abs;

import StatePattern.EmptyState;

public class Game {
    
    private Square[][] boardSquare;
    private Turn currentTurn;
    private Boolean winner=false;


    public Game()
    {   
        //X always starts first
        currentTurn = Turn.X;
        initializeBoard();
        //Turn[][] board=initialize();
    }

    public Memento createMemento() {
        return new Memento(initializeBoard(this.boardSquare), currentTurn, winner);
    }

    public void restore(Memento memento) {
        this.boardSquare = memento.getBoardSquare();
        this.currentTurn = memento.getCurrentTurn();
        this.winner = memento.getWinner();
    }

    public Square[][] initializeBoard(Square board[][]) {
        Square[][] clone = new Square[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                clone[i][j] = new Square(i,j,board[i][j].getSquareState());
            }
        }
        return clone;
    }

    public void initializeBoard()
    {   
        boardSquare = new Square[3][3];
        for(int i =0;i<boardSquare.length;i++)
            for(int j =0;j<boardSquare[0].length;j++)
            {
                boardSquare[i][j] = new Square(i,j,new EmptyState());
            }
    }


    // //this removed cuz state
    // public Boolean isValid(int row, int col)
    // {
    //     if(board[row][col]!=null)
    //         return false;
    //     else 
    //         return true;
    // }

    public void makemove(int row,int col)
    {
        boardSquare[row][col].makeMove(currentTurn,this); 
        if(winningMove(row, col))
            {
                if(currentTurn != Turn.X){//cuz we switch in EmptyState 
                    System.out.println("X WINS!");
                    winner=true;
                }
                else{
                     System.out.println("O WINS!");
                     winner=true;
                }
            }
    }
    
    // public boolean makeMove(int row, int col, Turn turn)
    // {
    //     //stuff changed here?
    //     boolean success;
    //     if(isValid(row, col))
    //     {
    //         board[row][col]= turn;
    //         if(winningMove(row, col))
    //             {
    //                 if(turn == Turn.X){ 
    //                     System.out.println("X WINS!");
    //                     winner=true;
    //                 }
    //                 else{
    //                     System.out.println("O WINS!");
    //                     winner=true;
    //                 }
    //             }
    //         success = true;
    //     }else
    //     {
    //         JOptionPane.showMessageDialog(null, "Square is used ");
    //         success = false;
    //     }

    //     return success;
        
    // }

    public Boolean getWinner()
    {
        return winner;
    }

    public Boolean winningMove(int row, int col)
        {
            if(abs(row-col)==2 || row==col)// one of the corners + middle
                if(checkDiagonal(row,col))
                    return true;
            if(checkHorizontalVertical(row,col))
                return true;
            return false;
        }
        
        public boolean isSameState(Square sq1, Square sq2)
        {
            return sq1.isSameState(sq2);
        }
        private Boolean checkHorizontalVertical(int row, int col)
        {
            Boolean flag=true;
            for(int i=0;i<3;i++)
            {
                if(!isSameState(boardSquare[row][col],boardSquare[row][i])) //check horizontal
                    flag=false;
                if(!flag)
                    break;
            }
            if(flag)
                return true;
            flag=true;//if horizontal check failed
            for(int i=0;i<3;i++)
            {
                if(!isSameState(boardSquare[row][col],boardSquare[i][col])) //check vertical
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
                if(isSameState(boardSquare[row][col],boardSquare[0][0]) && isSameState(boardSquare[row][col],boardSquare[1][1]) && isSameState(boardSquare[row][col],boardSquare[2][2]))
                    return true;
                return false;
            }
            else
            {
                if(isSameState(boardSquare[row][col],boardSquare[0][2]) && isSameState(boardSquare[row][col],boardSquare[1][1]) && isSameState(boardSquare[row][col],boardSquare[2][0]))                    return true;
                return false;
            }
        }

        public void switchTurn()
        {
            if(currentTurn==Turn.O)
                currentTurn =  Turn.X;
            else
                currentTurn = Turn.O;
        }
    
    public Square[][] getBoard()
    {
        return boardSquare;
    }
    
}

