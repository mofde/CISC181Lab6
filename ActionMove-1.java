package cisc181.lab_4;

public class ActionMove extends Action {

    public ActionMove(Game181 game, int fromRow, int fromCol, int toRow, int toCol){
        super(game, fromRow, fromCol, toRow, toCol);
    }

    public boolean validAction() {
        return (super.fromSpaceValid() && super.toSpaceValid(true) && super.validActionPath());
    }

    public void performAction() {
        game.getBoard().getSpaces()[toRow][toCol].removePiece();
        Piece startPiece = game.getBoard().getSpaces()[fromRow][fromCol].getPiece();
        game.getBoard().getSpaces()[toRow][toCol].setPiece(startPiece);
        game.getBoard().getSpaces()[fromRow][fromCol].removePiece();
        game.changeTurn();
    }
}
