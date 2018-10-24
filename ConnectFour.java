import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class ConnectFour {
	public static void clearIt(List<String> strs) {
		strs.clear();
	}
	
	public static void initializeBoard(char[][] board) {
		for (int r = 0; r < board.length; r++) {
			char[] row = board[r];
			
			for (int c = 0; c < row.length; c++) {
				row[c] = '-';
			}
		}
	}
	
	public static void printBoard(char[][] board) {
		for (int r = board.length - 1; r >= 0; r--) {
			char[] row = board[r];
			
			for (int c = 0; c < row.length; c++) {
				if (c != 0) { //No space at the end of the line for neatness.
					System.out.print(' ');
				}
				
				System.out.print(row[c]);
			}
			
			System.out.println();
		}
		
		System.out.println();
	}
	
	public static int getChoice(Scanner s, int player) {
		System.out.print("Player " + player + ": Which column would you like to choose? ");
		return s.nextInt();
	}
	
	//Returns the winner after dropping the piece in.
	public static int insertChip(char[][] board, int col, char chip) {
		for (int r = board.length - 1; r >= 0; r--) {
			char[] row = board[r];
			
			if (r == 0 || board[r - 1][col] != '-') {
				row[col] = chip;
				return r;
			}
		}
		
		return 0;
	}
	
	//After starting to type out the if conditions in checkIfWinner I decided a function with some fancy multiplying could do it with less typing.
	public static boolean checkWinCondition(char[][] board, int col, int row, char chip, int rMult, int cMult) {
		return  board[row][col] == chip && 
				board[row][col] == board[row + (rMult * 1)][col + (cMult * 1)] && 
				board[row + (rMult * 1)][col + (cMult * 1)] == board[row + (rMult * 2)][col + (cMult * 2)] && 
				board[row + (rMult * 2)][col + (cMult * 2)] == board[row + (rMult * 3)][col + (cMult * 3)];
	}
	
	public static boolean checkIfWinner(char[][] board, int col, int row, char chip) {
		int height = board.length;
		int width = board[0].length;
		
		if (col + 4 <= width) { //Checking right side
			if (checkWinCondition(board, col, row, chip, 0, 1)) {
				return true;
			}

			if (row + 4 <= height) { //Checking bottom right
				if (checkWinCondition(board, col, row, chip, 1, 1)) {
					return true;
				}
			}
			
			if (row - 3 >= 0) { //Checking top right
				if (checkWinCondition(board, col, row, chip, -1, 1)) {
					return true;
				}
			}
		}
		
		if (col - 3 >= 0) { //Checking left
			if (checkWinCondition(board, col, row, chip, 0, -1)) {
				return true;
			}

			if (row + 4 <= height) { //Checking bottom right
				if (checkWinCondition(board, col, row, chip, 1, -1)) {
					return true;
				}
			}
			
			if (row - 3 >= 0) { //Checking top right
				if (checkWinCondition(board, col, row, chip, -1, -1)) {
					return true;
				}
			}
		}
		
		if (row + 4 <= height) { //Checking down
			if (checkWinCondition(board, col, row, chip, 1, 0)) {
				return true;
			}
		}
		
		if (row - 3 >= 0) { //Checking up.
			if (checkWinCondition(board, col, row, chip, -1, 0)) {
				return true;
			}
		}
		
		return false;
	}
	
	public static void main(String[] args) {
		boolean whichTurn = true; //true = 1, false = 2
		Scanner s = new Scanner(System.in);
		char[][] board; //[row][column]
		
		System.out.print("What would you like the height of the board to be? ");
		int height = s.nextInt();
		System.out.print("What would you like the length of the board to be? ");
		int width = s.nextInt();
		
		board = new char[height][width];
		initializeBoard(board);
		printBoard(board);
		
		System.out.println("Player 1: x");
		System.out.println("Player 2: o");
		System.out.println();
		
		int winner = 0;
		int counter = 0;
		
		while (winner == 0 && counter < height * width) {
			int whichPlayer = (whichTurn ? 1 : 2);
			char whichChip = (whichTurn ? 'x' : 'o');
			int choice = getChoice(s, whichPlayer);
			int whichRow = insertChip(board, choice, whichChip);
			
			winner = checkIfWinner(board, choice, whichRow, whichChip) ? whichPlayer : 0;
			whichTurn = !whichTurn;
			counter++;
			
			printBoard(board);
		}
		
		if (winner == 1) {
			System.out.println("Player 1 won the game!");
		} else if (winner == 2) {
			System.out.println("Player 2 won the game!");
		} else {
			System.out.println("Draw. Nobody wins.");
		}

		s.close();
	}
}
