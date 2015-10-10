package Tutorial_1;
import java.util.Arrays;

/**
 * Created by Ads on 08/10/15.
 */
public class VirtualChessboard {
    public enum Chessmen {
        WHITE_KING,
        WHITE_QUEEN,
        WHITE_ROOK,
        WHITE_BISHOP,
        WHITE_KNIGHT,
        WHITE_PAWN,
        BLACK_KING,
        BLACK_QUEEN,
        BLACK_ROOK,
        BLACK_BISHOP,
        BLACK_KNIGHT,
        BLACK_PAWN,
        EMPTY
    }
    public static Chessmen[][] chessboard =  new Chessmen[8][8];

    public static void main(String[] args) {
//        chessboard = populate(chessboard);
        populateArrays();
        printBoard(chessboard);
        for (Chessmen[] row : chessboard) {
            System.out.println(Arrays.toString(row));
        }
    }

    public static void populateArrays() {
        for (int i=2; i<6; i++) {
            Arrays.fill(chessboard[i], Chessmen.EMPTY);
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

    public static void printBoard(Chessmen[][] chessboard) {
        char[] columns = {'\t', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h'};
        char[] rows = {'1', '2', '3', '4', '5', '6', '7', '8'};
        StringBuilder axes = new StringBuilder();

        for (int i = 0; i < 8; i++) {
            StringBuilder sb = new StringBuilder();
            sb.append(String.valueOf(rows[i]).append('\t'));
            for (int j = 0; j < 8; j++) {
                sb.append(chessboard[i][j].append('\t'));
            }
            System.out.println(sb.toString());
        }
    }
}
