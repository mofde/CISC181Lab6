package cisc181.lab_4;
import java.util.Scanner;
import java.util.ArrayList;

public class PlayGame {

    public Game181 theGame;

    public PlayGame(Game181 theGame){
        this.theGame = theGame;
    }

    private char getValidActionType(){
        System.out.println("Type \'M\' to move your piece.");
        System.out.println("Type \'R\' to recruit your opponent's piece.");
        System.out.println("Type \'A\' to attack your opponent's piece.");
        Scanner scnr = new Scanner(System.in);
        String userInput = scnr.next();
        while (!(userInput.charAt(0)== 'M' || userInput.charAt(0)== 'R' || userInput.charAt(0)== 'A')){
            System.out.print("Type \'M\', \'R\', or \'A\'.");
            userInput = scnr.next();
        }
        return userInput.charAt(0);
    }

    public void nextPlayersAction(){
        System.out.println(theGame.getCurrentTeam());
        boolean valid = true;
        while (valid) {
            char actionType = getValidActionType();
            System.out.println("Enter the row index of the starting space.");
            Scanner scnr = new Scanner(System.in);
            int fromRow = scnr.nextInt();
            System.out.println("Enter the column index of the starting space.");
            int fromCol = scnr.nextInt();
            System.out.println("Enter the row index of the ending space.");
            int toRow = scnr.nextInt();
            System.out.println("Enter the column index of the ending space.");
            int toCol = scnr.nextInt();
            if (actionType == 'M') {
                ActionMove newMove = new ActionMove(theGame, fromRow, fromCol, toRow, toCol);
                boolean validAction = newMove.validAction();
                if (validAction) {
                    newMove.performAction();
                    valid = false;
                }
                else{
                    System.out.println("Invalid action");
                }
            } else if (actionType == 'R') {
                ActionRecruit newRecruit = new ActionRecruit(theGame, fromRow, fromCol, toRow, toCol);
                boolean validAction = newRecruit.validAction();
                if (validAction) {
                    newRecruit.performAction();
                    valid = false;
                }
                else{
                    System.out.println("Invalid action");
                }
            } else if (actionType == 'A') {
                ActionAttack newAttack = new ActionAttack(theGame, fromRow, fromCol, toRow, toCol);
                boolean validAction = newAttack.validAction();
                if (validAction) {
                    newAttack.performAction();
                    valid = false;
                }
                else{
                    System.out.println("Invalid action");
                }
            }
        }
    }


    public void playOurGame(){
        nextPlayersAction();
        System.out.println(theGame);
        boolean gameOver = theGame.isGameEnded();
        while (!gameOver){
            nextPlayersAction();
            System.out.println(theGame);
            gameOver = theGame.isGameEnded();
        }
        System.out.println("The game has ended.");
        if (theGame.isAWinner()){
            System.out.print("The winner is: " + theGame.getWinner());
        }
    }

    public static void main(String[] args) {
        // Create 3 pieces for Team A
        Piece nemoA = new PieceSharkBait("Nemo","Red");
        Piece blueHenA = new PieceBlueHen("Hen ","Red",0,0);
        Piece penguinA = new PiecePenguin("Peng","Red",0,0);
        // Create an array list to hold Team A&#39;s pieces
        ArrayList<Piece> piecesTeamA = new ArrayList<>();
        piecesTeamA.add(nemoA);
        piecesTeamA.add(blueHenA);
        piecesTeamA.add(penguinA);
        // Create 3 pieces for Team B
        Piece nemoB = new PieceSharkBait("Nemo","Green");
        Piece blueHenB = new PieceBlueHen("Hen ","Green",0,0);
        Piece penguinB = new PiecePenguin("Peng","Green",0,0);
        // Create an array list to hold Team B&#39;s pieces
        ArrayList<Piece> piecesTeamB = new ArrayList<>();

        piecesTeamB.add(nemoB);
        piecesTeamB.add(blueHenB);
        piecesTeamB.add(penguinB);
    // Create TeamA and TeamB objects and pass them the array lists of pieces
        Team teamA = new Team("A", "Red",piecesTeamA);
        Team teamB = new Team("B", "Green",piecesTeamB);
    // Create an instance of the game
        Game181 ourGame = new Game181(4, 4,teamA, teamB);
    // Print Board at start of game
        System.out.println(ourGame.getBoard().toString());
    // Create PlayGame object and play the game
        PlayGame play = new PlayGame(ourGame);
        play.playOurGame();

    }
}

