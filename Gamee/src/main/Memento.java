package main;

public class Memento {


    private Square[][] boardSquare;
    private Turn currentTurn;
    private Boolean winner=false;
    
    public Memento( Square[][] boardSquare, Turn currentTurn,Boolean winner){
            this.boardSquare=boardSquare;
            this.currentTurn=currentTurn;
            this.winner=winner;
    }

    public Square[][] getBoardSquare(){
        return boardSquare;
    }

    public Turn getCurrentTurn(){
        return currentTurn;
    }

    public boolean getWinner(){
        return winner;
    }
}
