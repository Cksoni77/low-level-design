package designpattern.LLDTicTaoTac;

import designpattern.LLDTicTaoTac.Model.*;
import javafx.util.Pair;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class TicTaoTacGame {

    Deque<Player> players;
    Board gameBoard;

    public TicTaoTacGame() {
        initializeGame();
    }

    public void initializeGame() {

        //creating two players
        players = new LinkedList<>();
        PlayingPieceX pieceX = new PlayingPieceX();
        Player player1 = new Player("Player1", pieceX);


        PlayingPiece pieceO = new PlayingPieceO();
        Player player2 = new Player("player2", pieceO);

        players.add(player1);
        players.add(player2);

        gameBoard = new Board(3);
    }

    public String startgame() {
        boolean noWinner = true;

        while (noWinner) {
            Player playerTurn = players.removeFirst();
            gameBoard.printBoard();
            List<Pair<Integer, Integer>> freeSpace = gameBoard.getFreeCells();
            if (freeSpace.isEmpty()) {
                noWinner = false;
                continue;
            }

            // Read the user input
            System.out.print("Players: " + playerTurn.name + ", enter row and column (comma separated): ");
            Scanner inputScanner = new Scanner(System.in); // Moved outside the loop
            String s = inputScanner.nextLine();
            String[] values = s.split(",");
            int inputRow = Integer.parseInt(values[0]);
            int inputColumn = Integer.parseInt(values[1]);

            // Place the piece
            boolean pieceAddedSuccessfully = gameBoard.addPiece(inputRow, inputColumn, playerTurn.playingPiece);
            if (!pieceAddedSuccessfully) {
                System.out.println("Incorrect position chosen, try again");
                players.addFirst(playerTurn);
                continue;
            }
            players.addLast(playerTurn);

            boolean winner = isThereWinner(inputRow, inputColumn, playerTurn.playingPiece.pieceType);
            if (winner) {
                return playerTurn.name;
            }
        }
        return "tie";
    }

    public boolean isThereWinner(int row, int column, PieceType pieceType) {
        boolean rowMatch = true;
        boolean columnMatch = true;
        boolean diagonalMatch = true;
        boolean antiDiagonalMatch = true;

        // Check the row
        for (int i = 0; i < gameBoard.size; i++) {
            if (gameBoard.board[row][i] == null || gameBoard.board[row][i].pieceType != pieceType) {
                rowMatch = false;
            }
        }

        // Check the column
        for (int i = 0; i < gameBoard.size; i++) {
            if (gameBoard.board[i][column] == null || gameBoard.board[i][column].pieceType != pieceType) {
                columnMatch = false;
            }
        }

        // Check the diagonal
        for (int i = 0, j =0; i < gameBoard.size; i++, j++) {
            if (gameBoard.board[i][j] == null || gameBoard.board[i][j].pieceType != pieceType) {
                diagonalMatch = false;
            }
        }

        // Check the anti-diagonal
        for (int i = 0, j = gameBoard.size - 1; i < gameBoard.size; i++, j--) {
            if (gameBoard.board[i][j] == null || gameBoard.board[i][j].pieceType != pieceType) {
                antiDiagonalMatch = false;
            }
        }

        return rowMatch || columnMatch || diagonalMatch || antiDiagonalMatch;
    }
}
