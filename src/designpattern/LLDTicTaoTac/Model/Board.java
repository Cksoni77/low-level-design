package designpattern.LLDTicTaoTac.Model;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;

public class Board {

    public int size;
    public PlayingPiece[][]board;

    public Board(int size){
        this.size = size;
        board = new PlayingPiece[size][size];

    }

    public boolean addPiece(int row, int column, PlayingPiece playingPiece){
        if(board[row][column]!=null){
            return false;
        }
        board[row][column] = playingPiece;
        return true;
    }

    public List<Pair<Integer, Integer>> getFreeCells(){
        List<Pair<Integer, Integer>> freeCells = new ArrayList<>();

        for(int i =0; i< size; i++){
            for(int j=0; j<size ; j++){
                if(board[i][j] == null){
                    Pair<Integer, Integer> rowCloumn = new Pair<>(i,j);
                    freeCells.add(rowCloumn);
                }
            }
        }
        return freeCells;
    }

    public void printBoard() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                // Print the piece if it exists, otherwise print a space
                if (board[i][j] == null) {
                    System.out.print(" . ");
                } else {
                    System.out.print(" " + board[i][j].pieceType + " ");
                }

                // Print a vertical separator
                if (j < size - 1) {
                    System.out.print("|");
                }
            }
            System.out.println();

            // Print a horizontal separator after each row
            if (i < size - 1) {
                System.out.println("---|---|---");
            }
        }
        System.out.println(); // Add a newline for better readability
    }

}
