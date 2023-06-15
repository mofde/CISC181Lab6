package cisc181.lab_4;

public class ActionAttack extends Action {

   // constructor
    public ActionAttack(Game181 game, int fromSpaceRow, int fromSpaceCol, int toSpaceRow, int toSpaceCol) {
        super(game, fromSpaceRow,fromSpaceCol,toSpaceRow,toSpaceCol);
    }

    // Check to see if this is valid Attack Action
    public boolean validAction() {

        // check if from space valid
        if(fromSpaceValid() ) {
            // get the piece that is in the from BoardSpace
            Piece fromPiece = game.getBoard().getSpaces()
                    [fromRow][fromCol].getPiece();
            // check to see if this piece has implemented the Attacker interface
            if (Attacker.class.isAssignableFrom(fromPiece.getClass())) {
                // if to space is valid - should NOT be empty so pass false to the method
                if (toSpaceValid(false)) {
                    return validActionPath();
                }
            } else {
                System.out.println("The piece on first space can't attack.");
                return false;
            }
        }
        return false;
    }

   // this method calls the Piece's attack method

    private void attack(){
        // Get the piece that is in the fromSpace
        Piece attPiece = game.getBoard()
                .getSpaces()[fromRow][fromCol].getPiece();
        // check to see which type of Piece we have
        // we can't call the attack method on all pieces in the game
        // we can only call these methods on pieces that have this method - ie - Pieces that have implemented the Attacker Interface
        // so we will cast the Piece to its subclass type so we can call attack
        if(attPiece instanceof PieceBlueHen){
            // cast and call BlueHen's attack method
             ((PieceBlueHen) attPiece)
                    .attack(fromRow,fromCol,toRow,toCol);
        }
        else if(attPiece instanceof PiecePenguin){
            // cast and call Penguin's attack method
            ((PiecePenguin) attPiece)
                    .attack(fromRow,fromCol,toRow,toCol);
        }
    }


    public void performAction() {
        attack();
        //game.getBoard().getSpaces()[toRow][toCol].removePiece();
        Piece attackedPiece = game.getBoard().getSpaces()[toRow][toCol].getPiece();
        game.getOpponentTeam().removePieceFromTeam(attackedPiece);
        Piece attackerPiece = game.getBoard().getSpaces()[fromRow][fromCol].getPiece();
        game.getBoard().getSpaces()[toRow][toCol].setPiece(attackerPiece);
        game.getBoard().getSpaces()[fromRow][fromCol].removePiece();
        game.changeTurn();
        return;
    }
}
