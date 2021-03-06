package tictactoe;

/**
 * The Game class represents a single game of Tic-Tac-Toe.
 *
 * @author Ljubo Raicevic <rljubo90@gmail.com>
 */
public class Game {

    /**
     * Board on which the game is played.
     */
    protected final Board board;

    /**
     * Array of players.
     */
    protected final Player[] players;

    /**
     * How many moves have been played so far in the game.
     */
    protected int movesPlayed;

    /**
     * Initializes a new game.
     *
     * @param b Board to be played on
     * @param first First player (vertical)
     * @param second Second player (horizontal)
     */
    public Game(Board b, Player first, Player second) {
        this.movesPlayed = 0;
        this.players = new Player[2];
        this.players[0] = first;
        this.players[1] = second;
        this.board = b;
    }

    /**
     * Checks who won.
     *
     * @return 0 if game is still active, 1 or 2 if first or second player won,
     * respectively
     */
    public byte whoWon() {
        if      (MonteCarlo.didPlayerWin(board, (byte)1)) { return 1; }
        else if (MonteCarlo.didPlayerWin(board, (byte)2)) { return 2; }
        else                                              { return 0; }
    }

    /**
     * Starts the game. Players take turns until one of them wins, or until the
     * board is full.
     * 
     * @return Returns the winning player
     */
    public int play() {
        int winningPlayer = 0;

        //while game isn't over
        while (winningPlayer == 0 
                && movesPlayed < board.getSize() * board.getSize()) {
            //players take turns based on number of moves played so far
            Coordinate move = players[movesPlayed % 2].makeMove(board);

            //players[0]'s mark is 1 and player[1]'s mark is 2
            board.putMark(move, (byte) (movesPlayed % 2 + 1));

            movesPlayed++;
            winningPlayer = whoWon();
        }

        System.out.println(board);
        System.out.println("Player " + winningPlayer + " wins!");
        return winningPlayer;
    }
}