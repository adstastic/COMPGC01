
package Tutorial_1;

import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;

public class VirtualChessboard {
    public static enum Chessmen { 
        BLACK_KING(		"\u2654", "Black", "King"	),
        BLACK_QUEEN(	"\u2655", "Black", "Queen"	),
        BLACK_ROOK(		"\u2656", "Black", "Rook"	),
        BLACK_BISHOP(	"\u2657", "Black", "Bishop"	),
        BLACK_KNIGHT(	"\u2658", "Black", "Knight"	),
        BLACK_PAWN(		"\u2659", "Black", "Pawn"	),
        WHITE_KING(		"\u265A", "White", "King"	),
        WHITE_QUEEN(	"\u265B", "White", "Queen"	),
        WHITE_ROOK(		"\u265C", "White", "Rook"	),
        WHITE_BISHOP(	"\u265D", "White", "Bishop"	),
        WHITE_KNIGHT(	"\u265E", "White", "Knight"	),
        WHITE_PAWN(		"\u265F", "White", "Pawn"	),
        EMPTY_WHITE(	"\u25A1", "None", "Empty"	),
        EMPTY_BLACK(	"\u25A0", "None", "Empty"	);

        String unicode;
        String colour; 
        String type;

        private Chessmen(String unicodeString, String colour, String baseType) {
            this.unicode = unicodeString;
            this.colour = colour;
            this.type = baseType;
        }

        public String unicode() {
            return this.unicode;
        }
        
        public String colour() {
            return this.colour;
        }

        public String type() {
            return this.type;
        }
    }
    
    static Chessmen current;
    static Chessmen future;

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
    
    static int moveNumber = 1;
    static boolean endTurn = true;

    public static void main(String[] args) throws UnsupportedEncodingException {
        populateArrays();
        Scanner s = new Scanner(System.in);

        while (true) {
            printBoard();
            if (endTurn && moveNumber %2 == 0) {
            	System.out.println("You are playing as Black");
            } else if (endTurn && moveNumber %2 == 1) {
            	System.out.println("You are playing as White");
            }
            
            System.out.println("Enter a move or type \'exit\'");
            endTurn = false;
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
        System.out.println("populating pawns");
        Arrays.fill(chessboard[1], VirtualChessboard.Chessmen.WHITE_PAWN);
        Arrays.fill(chessboard[6], VirtualChessboard.Chessmen.BLACK_PAWN);
        System.out.println("populating other pieces");
        chessboard[0] = new VirtualChessboard.Chessmen[]{VirtualChessboard.Chessmen.WHITE_ROOK, VirtualChessboard.Chessmen.WHITE_BISHOP, VirtualChessboard.Chessmen.WHITE_KNIGHT, VirtualChessboard.Chessmen.WHITE_QUEEN, VirtualChessboard.Chessmen.WHITE_KING, VirtualChessboard.Chessmen.WHITE_KNIGHT, VirtualChessboard.Chessmen.WHITE_BISHOP, VirtualChessboard.Chessmen.WHITE_ROOK};
        chessboard[7] = new VirtualChessboard.Chessmen[]{VirtualChessboard.Chessmen.BLACK_ROOK, VirtualChessboard.Chessmen.BLACK_BISHOP, VirtualChessboard.Chessmen.BLACK_KNIGHT, VirtualChessboard.Chessmen.BLACK_QUEEN, VirtualChessboard.Chessmen.BLACK_KING, VirtualChessboard.Chessmen.BLACK_KNIGHT, VirtualChessboard.Chessmen.BLACK_BISHOP, VirtualChessboard.Chessmen.BLACK_ROOK};
    }

    public static void printBoard() throws UnsupportedEncodingException {
        PrintStream out = new PrintStream(System.out, true, "UTF-8");
        for (String element : columns.keySet()) {
            out.print("\t" + element);
        }
        System.out.println("\n");

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
    	System.out.println("parsing move");
        String[] inputArray = input.split(" ");
        String from = inputArray[0];
        String to = inputArray[2];
        int fromRow = Integer.parseInt(from.substring(1, 2));
        int toRow = Integer.parseInt(to.substring(1, 2));
        int fromCol = columns.get(from.substring(0, 1));
        int toCol = columns.get(to.substring(0, 1));
//        System.out.println(fromRow+" "+fromCol+" "+toRow+" "+toCol);
        if (toRow > 8 || toRow < 1) {
        	System.out.println("INVALID: The end location is outside the board.");
        } else if (fromRow > 8 || fromRow < 1) {
        	System.out.println("INVALID: The start location is outside the board.");
        } else {
        	System.out.println("start and end locations are on-board");
        	processMove(fromRow, fromCol, toRow, toCol);
        }
    }

    public static void processMove(int fr, int fc, int tr, int tc) {
    	System.out.println("processing move");
        fr -= 1;
        fc -= 1;
        tr -= 1;
        tc -= 1;
        current = chessboard[fr][fc];
        future = chessboard[tr][tc];
        if (validatePlayer()) {
	        String pieceType = chessboard[fr][fc].type();
	//        System.out.println(pieceType);
	        if (pieceType.equals("Empty")) {
	        	System.out.println("INVALID: The starting location is empty.");
	        } else if (chessboard[tr][tc].colour().equals(chessboard[fr][fc].colour())) {
	        	System.out.println("INVALID: The ending location is occupied by a piece of the same colour.");
	        } else if (!pieceType.equals("Empty")) {
	        	boolean validMove = false;
	        	switch (pieceType) {
	        		case "Rook": 		validMove = validateRook(fr, fc, tr, tc); break;
	        		case "Knight": 	validMove = validateKnight(fr, fc, tr, tc); break;
	        		case "Bishop": 	validMove = validateBishop(fr, fc, tr, tc); break;
	        		case "Queen": 	validMove = validateQueen(fr, fc, tr, tc); break;
	        		case "King": 		validMove = validateKing(fr, fc, tr, tc); break;
	        		case "Pawn": 	validMove = validatePawn(fr, fc, tr, tc); break;
	        	}
	        	if (validMove) {
	        		chessboard[tr][tc] = chessboard[fr][fc];
	        		chessboard[fr][fc] = BorW(fr, fc);
	        		System.out.println("Move is "+validMove);
	        		endTurn = true;
	        		moveNumber++;
	        	} else if (!validMove) {
	        		System.out.println("INVALID: The selected piece cannot move to that location.");
	        	}
	        }
        }

    }

    public static Chessmen BorW(int i, int j) {
    	System.out.println("populating empty square");
        VirtualChessboard.Chessmen BorW = (i % 2 == 0 && j % 2 == 0) ^ (i % 2 != 0 && j % 2 != 0) ? Chessmen.EMPTY_BLACK : Chessmen.EMPTY_WHITE;
        return BorW;
    }

    public static boolean validateKnight(int fr, int fc, int tr, int tc) {
    	System.out.println("validating knight");
        if ((Math.abs(tc - fc) == 1 && Math.abs(tr - fr) == 2) ^ (Math.abs(tc - fc) == 2 && Math.abs(tr - fr) == 1)) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean validateRook(int fr, int fc, int tr, int tc) {
    	System.out.println("validating rook");
        if ((Math.abs(tc - fc) == 0 && Math.abs(tr - fr) != 0) ^ (Math.abs(tc - fc) != 0 && Math.abs(tr - fr) == 0)) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean validateBishop(int fr, int fc, int tr, int tc) {
    	System.out.println("validating bishop");
        if (Math.abs(tc - fc) == Math.abs(tr - fr)) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean validateQueen(int fr, int fc, int tr, int tc) {
    	System.out.println("validating queen");
        if (validateBishop(fr, fc, tr, tc) ^ validateRook(fr, fc, tr, tc) ^ validateKnight(fr, fc, tr, tc)) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean validatePawn(int fr, int fc, int tr, int tc) {
    	System.out.println("validating pawn");
    	System.out.println("going from array position: "+fr+","+fc+" to "+tr+","+tc);
    	int pawnDistance = (current == Chessmen.BLACK_PAWN) ? -1 : +1;
		if (tr - fr == pawnDistance && tc - fc == 0) {
			return true;
		} else if (tr - fr == pawnDistance && Math.abs(tc - fc) == 1 && !future.type().equals("Empty")) {
			return true;
		} else if (tr - fr == pawnDistance*2) {
			System.out.println("validating pawn 2-square move");
			System.out.println(current.colour());
			if (current.colour().equals("White") && fr == 1) {
				return true;
			} else if (current.colour().equals("Black") && fr == 6) {
				return true;
			} else {
				System.out.println("INVALID: Pawn can only move 2 squares forward on it's first move.");
				return false;
			}
		} else {
			return false;
		}
    }

    public static boolean validateKing(int fr, int fc, int tr, int tc) {
    	System.out.println("validating king");
        if (Math.abs(tr - fr) == 1 || Math.abs(tc - fc) == 1) {
            return true;
        } else {
            return false;
        }
    }
    
    public static boolean validatePlayer() {
    	System.out.println("validating player");
    	String pieceColour = current.colour();
    	if (moveNumber %2 == 0 && pieceColour.equals("Black")) {
    		return true;
    	} else if (moveNumber %2 == 1 && pieceColour.equals("White")) {
    		return true;
    	} else {
    		System.out.println("INVALID: You are playing for "+pieceColour+". You cannot move pieces of the other player.");
    		return false;
    	}
    }
}
