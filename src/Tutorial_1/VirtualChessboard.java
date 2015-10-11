
package Tutorial_1;

import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;

public class VirtualChessboard {
    public static enum Chessmen {
        WHITE_KING(		"\u2654", "King"		),
        WHITE_QUEEN(	"\u2655", "Queen"	),
        WHITE_ROOK(		"\u2656", "Rook"		),
        WHITE_BISHOP(	"\u2657", "Bishop"	),
        WHITE_KNIGHT(	"\u2658", "Knight"	),
        WHITE_PAWN(		"\u2659", "Pawn"		),
        BLACK_KING(		"\u265A", "King"		),
        BLACK_QUEEN(	"\u265B", "Queen"	),
        BLACK_ROOK(		"\u265C", "Rook"		),
        BLACK_BISHOP(	"\u265D", "Bishop"	),
        BLACK_KNIGHT(	"\u265E", "Knight"	),
        BLACK_PAWN(		"\u265F", "Pawn"		),
        EMPTY_WHITE(		"\u25A1", "Empty"	),
        EMPTY_BLACK(		"\u25A0", "Empty"	);

        String unicode;
        String type;

        private Chessmen(String unicodeString, String baseType) {
            this.unicode = unicodeString;
            this.type = baseType;
        }

        public String unicode() {
            return this.unicode;
        }

        public String type() {
            return this.type;
        }
    }

    static VirtualChessboard.Chessmen[][] chessboard = new VirtualChessboard.Chessmen[8][8];
    static HashMap<String, Integer> columns = new HashMap<String, Integer>() {
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
                System.out.println("Goodbye.");
                break;
            } else if (!input.isEmpty()) {
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
            out.print(" " + element);
        }
        System.out.println("\n");

        for (int i = 0; i < chessboard.length; ++i) {
            Chessmen[] row = chessboard[i];
            out.print(rows[i] + " ");

            for (Chessmen piece : row) {
                out.print(piece.unicode() + " ");
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
        System.out.println(fromRow+" "+fromCol+" "+toRow+" "+toCol);
        processMove(fromRow, fromCol, toRow, toCol);
    }

    public static void processMove(int fr, int fc, int tr, int tc) {
        fr -= 1;
        fc -= 1;
        tr -= 1;
        tc -= 1;
        System.out.println(fr+" "+fc+" "+tr+" "+tc);
        String pieceType = chessboard[fr][fc].type();
        if (pieceType.equals("Empty")) {
        	System.out.println("The starting location is empty.");
        } else if (!pieceType.equals("Empty")) {
        	boolean validMove = false;
        	switch (pieceType) {
        		case "Rook": 		validMove = validateRook(fr, fc, tr, tc);
        		case "Knight": 	validMove = validateKnight(fr, fc, tr, tc);
        		case "Bishop": 	validMove = validateBishop(fr, fc, tr, tc);
        		case "Queen": 	validMove = validateQueen(fr, fc, tr, tc);
        		case "King": 		validMove = validateKing(fr, fc, tr, tc);
        		case "Pawn": 	validMove = validatePawn(fr, fc, tr, tc);
        	}
        	if (validMove) {
        		chessboard[tr][tc] = chessboard[fr][fc];
        		chessboard[fr][fc] = BorW(fr, fc);
        	} else if (!validMove) {
        		System.out.println("That move is not valid. Please enter a valid move.");
        	}
        }

    }

    public static Chessmen BorW(int i, int j) {
        VirtualChessboard.Chessmen BorW = (i % 2 == 0 && j % 2 == 0) ^ (i % 2 != 0 && j % 2 != 0) ? Chessmen.EMPTY_BLACK : Chessmen.EMPTY_WHITE;
        return BorW;
    }

    public static boolean validateKnight(int fr, int fc, int tr, int tc) {
        if ((Math.abs(tc - fc) == 1 && Math.abs(tr - fr) == 2) ^ (Math.abs(tc - fc) == 2 && Math.abs(tr - fr) == 1)) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean validateRook(int fr, int fc, int tr, int tc) {
        if ((Math.abs(tc - fc) == 0 && Math.abs(tr - fr) > 0) ^ (Math.abs(tc - fc) > 0 && Math.abs(tr - fr) == 0)) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean validateBishop(int fr, int fc, int tr, int tc) {
        if (Math.abs(tc - fc) == Math.abs(tr - fr)) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean validateQueen(int fr, int fc, int tr, int tc) {
        if (validateBishop(fr, fc, tr, tc) ^ validateRook(fr, fc, tr, tc) ^ validateKnight(fr, fc, tr, tc)) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean validatePawn(int fr, int fc, int tr, int tc) {
    	int pawnDistance = (chessboard[fr][fc] == Chessmen.BLACK_PAWN) ? -1 : +1;
        if ((tr - fr == pawnDistance && tc - fc == 0) ^ (tr - fr == pawnDistance && Math.abs(tc - fc) == 1 && !chessboard[tr][tc].type().equals("Empty"))) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean validateKing(int fr, int fc, int tr, int tc) {
        if (Math.abs(tr - fr) == 1 || Math.abs(tc - fc) == 1) {
            return true;
        } else {
            return false;
        }
    }
}
