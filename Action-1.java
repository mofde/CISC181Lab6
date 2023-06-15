package cisc181.lab_4;

public abstract class Action {

    //member fields
    public Game181 game;
    public int fromRow;
    public int fromCol;
    public int toRow;
    public int toCol;


    public Action(Game181 game, int fromRow, int fromCol, int toRow, int toCol){
        this.game = game;
        this.fromRow = fromRow;
        this.fromCol = fromCol;
        this.toRow = toRow;
        this.toCol = toCol;
    }

    //Accessor Methods
    public boolean fromSpaceValid(){
        if (!game.getBoard().inBounds(fromRow, fromCol)){
            return false;
        }
        else {
            Piece fromPiece = game.getBoard().getSpaces()[fromRow][fromCol].getPiece();
            boolean returnVal2 = game.getCurrentTeam().getTeamPieces().contains(fromPiece);
            return (returnVal2);
        }
    }

    public boolean toSpaceValid(boolean isEmpty){
        if (game.getBoard().inBounds(toRow, toCol)){
            if (isEmpty == true){
                if (game.getBoard().getSpaces()[toRow][toCol].isEmpty()){
                    return true;
                }
                else{
                    return false;
                }
            }
            else{
                Piece toPiece = game.getBoard().getSpaces()[toRow][toCol].getPiece();
                boolean returnVal = game.getOpponentTeam().getTeamPieces().contains(toPiece);
                return returnVal;
            }
        }
        return false;
    }
    public boolean validActionPath(){
        return (game.getBoard().getSpaces()[fromRow][fromCol].getPiece().validPath(fromRow, fromCol, toRow, toCol));
    }

    //Abstract methods
    public abstract boolean validAction();
    public abstract void performAction();


}
