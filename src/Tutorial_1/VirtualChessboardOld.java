package Tutorial_1;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by Ads on 08/10/15.
 */
public class VirtualChessboardOld {
    public enum Chessmen {
        WHITE_KING ("\u2654"),
        WHITE_QUEEN ("\u2655"),
        WHITE_ROOK ("\u2656"),
        WHITE_BISHOP ("\u2657"),
        WHITE_KNIGHT ("\u2658"),
        WHITE_PAWN ("\u2659"),
        BLACK_KING ("\u265A"),
        BLACK_QUEEN ("\u265B"),
        BLACK_ROOK ("\u265C"),
        BLACK_BISHOP ("\u265D"),
        BLACK_KNIGHT ("\u265E"),
        BLACK_PAWN ("\u265F"),
        EMPTY_WHITE ("\u2610"),
        EMPTY_BLACK ("\u25A0");

        String value;

        Chessmen(String s) { value = s;}

        public String value() {return this.value;}
    }

    public static Chessmen[][] chessboard =  new Chessmen[8][8];

    public static void main(String[] args) {
        populateArrays();
        Scanner s = new Scanner(System.in);
        while (true) {
            printBoard();
            System.out.println("Enter a move or type 'exit'");
            String input = s.nextLine();
            if (input.equals("exit")) { break; }
            else if (!input.isEmpty()) {
                processMove(input);
            }
        }
    }

    public static void populateArrays() {
        for (int i=2; i<6; i++) {
            for (int j=0; j<8; j++) {
                chessboard[i][j] = ((i % 2 == 0 && j % 2 == 0) ^ (i % 2 != 0 && j % 2 != 0)) ? Chessmen.EMPTY_BLACK : Chessmen.EMPTY_WHITE;
            }
        }

        Arrays.fill(chessboard[1], Chessmen.WHITE_PAWN);
        Arrays.fill(chessboard[6], Chessmen.BLACK_PAWN);

        chessboard[0] = new Chessmen[] {
                Chessmen.WHITE_ROOK,
                Chessmen.WHITE_BISHOP,
                Chessmen.WHITE_KNIGHT,
                Chessmen.WHITE_QUEEN,
                Chessmen.WHITE_KING,
                Chessmen.WHITE_KNIGHT,
                Chessmen.WHITE_BISHOP,
                Chessmen.WHITE_ROOK
        };

        chessboard[7] = new Chessmen[] {
                Chessmen.BLACK_ROOK,
                Chessmen.BLACK_BISHOP,
                Chessmen.BLACK_KNIGHT,
                Chessmen.BLACK_QUEEN,
                Chessmen.BLACK_KING,
                Chessmen.BLACK_KNIGHT,
                Chessmen.BLACK_BISHOP,
                Chessmen.BLACK_ROOK
        };
    }

    public static void printBoard() {
        char[] columns = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h'};
        char[] rows = {'1', '2', '3', '4', '5', '6', '7', '8'};

        for (char element : columns) {
            System.out.print("\t" + element);
        }
        System.out.println("\n");
        for (int i = 0; i < chessboard.length; i++) {
            Chessmen[] row = chessboard[i];
            System.out.print(rows[i] + "\t");
            for (Chessmen piece : row) {
                System.out.print(piece.value() + "\t");
            }
            System.out.println("\n");
        }
    }

    public static void processMove(String input) {
        String[] inputArray = input.split(" ");
        String from = inputArray[0];
        String to = inputArray[2];
    }
}

