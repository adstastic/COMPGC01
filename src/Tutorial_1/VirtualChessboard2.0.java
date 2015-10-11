
package Tutorial_1;

import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;

public class VirtualChessboard {
    public static enum Chessmen {
        WHITE_KING("\u2654", true),
        WHITE_QUEEN("\u2655", true),
        WHITE_ROOK("\u2656", true),
        WHITE_BISHOP("\u2657", true),
        WHITE_KNIGHT("\u2658", true),
        WHITE_PAWN("\u2659", true),
        BLACK_KING("\u265A", true),
        BLACK_QUEEN("\u265B", true),
        BLACK_ROOK("\u265C", true),
        BLACK_BISHOP("\u265D", true),
        BLACK_KNIGHT("\u265E", true),
        BLACK_PAWN("\u265F", true),
        EMPTY_WHITE("\u25A1", false),
        EMPTY_BLACK("\u25A0", false);

        String unicode;
        boolean isPiece;

        private Chessmen(String s, boolean isPiece) {
            this.unicode = s;
            this.isPiece = isPiece;
        }

        public String unicode() {
            return this.unicode;
        }

        public boolean isPiece() {
            return this.isPiece;
        }
    }

    static VirtualChessboard.Chessmen[][] chessboard = new VirtualChessboard.Chessmen[8][8];
    static HashMap<String, Integer> columns = new HashMap() {
        {
            this.put("a", Integer.valueOf(1));
            this.put("b", Integer.valueOf(2));
            this.put("c", Integer.valueOf(3));
            this.put("d", Integer.valueOf(4));
            this.put("e", Integer.valueOf(5));
            this.put("f", Integer.valueOf(6));
            this.put("g", Integer.valueOf(7));
            this.put("h", Integer.valueOf(8));
        }
    };

    static String[] rows = new String[]{"1", "2", "3", "4", "5", "6", "7", "8"};

    public static void main(String[] args) throws UnsupportedEncodingException {
        populateArrays();
        Scanner s = new Scanner(System.in);

        while (true) {
            printBoard();
            System.out.println("Enter a move or type \'exit\'");
            String input = s.nextLine();
            if (input.equals("exit")) {
                return;
            }

            if (!input.isEmpty()) {
                parseMove(input);
            }
        }
    }

    public static void populateArrays() {
        for (int i = 2; i < 6; ++i) {
            for (int j = 0; j < 8; ++j) {
                chessboard[i][j] = BorW(i, j);
            }
        }

        Arrays.fill(chessboard[1], VirtualChessboard.Chessmen.WHITE_PAWN);
        Arrays.fill(chessboard[6], VirtualChessboard.Chessmen.BLACK_PAWN);
        chessboard[0] = new VirtualChessboard.Chessmen[]{VirtualChessboard.Chessmen.WHITE_ROOK, VirtualChessboard.Chessmen.WHITE_BISHOP, VirtualChessboard.Chessmen.WHITE_KNIGHT, VirtualChessboard.Chessmen.WHITE_QUEEN, VirtualChessboard.Chessmen.WHITE_KING, VirtualChessboard.Chessmen.WHITE_KNIGHT, VirtualChessboard.Chessmen.WHITE_BISHOP, VirtualChessboard.Chessmen.WHITE_ROOK};
        chessboard[7] = new VirtualChessboard.Chessmen[]{VirtualChessboard.Chessmen.BLACK_ROOK, VirtualChessboard.Chessmen.BLACK_BISHOP, VirtualChessboard.Chessmen.BLACK_KNIGHT, VirtualChessboard.Chessmen.BLACK_QUEEN, VirtualChessboard.Chessmen.BLACK_KING, VirtualChessboard.Chessmen.BLACK_KNIGHT, VirtualChessboard.Chessmen.BLACK_BISHOP, VirtualChessboard.Chessmen.BLACK_ROOK};
    }

    public static void printBoard() throws UnsupportedEncodingException {
        PrintStream out = new PrintStream(System.out, true, "UTF-8");
        for (String element : columns.keySet()) {
            out.print("\t" + element);
        }

        for (int i = 0; i < chessboard.length; ++i) {
            Chessmen[] row = chessboard[i];
            out.print(rows[i] + "\t");

            for (Chessmen piece : row) {
                out.print(piece.unicode() + "\t");
            }

            out.println("\n");
        }

    }

    public static void parseMove(String input) {
        String[] inputArray = input.split(" ");
        String from = inputArray[0];
        String to = inputArray[2];
        int fromRow = Integer.parseInt(from.substring(1, 2));
        int toRow = Integer.parseInt(to.substring(1, 2));
        int fromCol = columns.get(from.substring(0, 1));
        int toCol = columns.get(to.substring(0, 1));
        processMove(fromRow, fromCol, toRow, toCol);
    }

    public static void processMove(int fr, int fc, int tr, int tc) {
        --fr;
        --fc;
        --tr;
        --tr;
        if (chessboard[fr][fc].isPiece()) {
            chessboard[tr][tc] = chessboard[fr][fc];
            chessboard[fr][fc] = BorW(fr, fc);
        } else if (!chessboard[fr][fc].isPiece()) {
            System.out.println("The starting location is empty.");
        }

    }

    public static VirtualChessboard.Chessmen BorW(int i, int j) {
        VirtualChessboard.Chessmen BorW = (i % 2 == 0 && j % 2 == 0) ^ (i % 2 != 0 && j % 2 != 0) ? Chessmen.EMPTY_BLACK : Chessmen.EMPTY_WHITE;
        return BorW;
    }

    public boolean validateKnight(int fr, int fc, int tr, int tc) {
        if ((Math.abs(tc - fc) == 1 && Math.abs(tr - fr) == 2) ^ (Math.abs(tc - fc) == 2 && Math.abs(tr - fr) == 1)) {
            return true;
        } else {
            return false;
        }
    }

    public boolean validateRook(int fr, int fc, int tr, int tc) {
        if ((Math.abs(tc - fc) == 0 && Math.abs(tr - fr) > 0) ^ (Math.abs(tc - fc) > 0 && Math.abs(tr - fr) == 0)) {
            return true;
        } else {
            return false;
        }
    }

    public boolean validateBishop(int fr, int fc, int tr, int tc) {
        if (Math.abs(tc - fc) == Math.abs(tr - fr)) {
            return true;
        } else {
            return false;
        }
    }

    public boolean validateQueen(int fr, int fc, int tr, int tc) {
        if (validateBishop(fr, fc, tr, tc) ^ validateRook(fr, fc, tr, tc) ^ validateKnight(fr, fc, tr, tc)) {
            return true;
        } else {
            return false;
        }
    }

    public boolean validatePawn(int fr, int fc, int tr, int tc) {
        if ((tr - fr == 1 && tc - fc == 0) ^ (tr - fr == 1 && Math.abs(tc - fc) == 1 && chessboard[tr][tc].isPiece())) {
            return true;
        } else {
            return false;
        }
    }

    public boolean validateKing(int fr, int fc, int tr, int tc) {
        if (Math.abs(tr - fr) == 1 || Math.abs(tc - fc) == 1) {
            return true;
        } else {
            return false;
        }
    }
}
