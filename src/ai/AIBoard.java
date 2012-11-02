package ai;

import game.Blob;
import game.Board;
import game.Point;

public class AIBoard implements Cloneable{

	public final int SIZE = Board.SIZE;
	private char[][] board = new char[SIZE][SIZE];
	
	public AIBoard(Board board){
		for(int i = 0; i<SIZE; i++) {
			for(int j = 0; j<SIZE; j++){
				this.board[i][j] = convertBlob(board.getBlob(i,j));
			}
		}
	}

	public AIBoard(char[][] board) {
		this.board = board;
	}

	public static char convertBlob(Blob blob) {
		switch (blob){
			case PLAYER1:
				return 'p';
			case PLAYER2:
				return 'c';
			case ROCK:
				return 'r';
			default:
				return 'e';
		}
	}
	
	public static char getOppositeBlob(char blob){
		switch (blob){
			case 'c':
				return 'p';
			case 'p':
				return 'c';
			default:
				return blob;
		}
	}
	
	public char getBlob(int row, int col) {
		return board[row][col];
	}
	
	public char getBlob(Point position) {
		return board[position.getX()][position.getY()];
	}
	
	public boolean contains(Point to) {
		return contains(to.getX(), to.getY()); 
	}
	
	public boolean contains(int row, int col) {
		return row >= 0 && row < SIZE && col >=0 && col < SIZE;
	}
	
	public AIBoard clone() {
		char[][] clonedBoard = new char[SIZE][SIZE];
		for (int row = 0; row < SIZE; row++) {
			for (int col = 0; col < SIZE; col++) {
				clonedBoard[row][col] = board[row][col];
			}
		}
		return new AIBoard(clonedBoard);
	}
	
	public AIBoard cloneBlob(int row, int col, char blob) {
		board[row][col] = blob;
		attack(new Point(row,col), blob);
		return this;
	}

	public AIBoard moveBlob(Point from, int row, int col, char blob) {
		cloneBlob(row, col, blob);
		clearCell(from);
		return this;		
	}
	
	private void attack(Point position, char blob) {
		int x = position.getX();
		int y = position.getY();
		for(int i = Math.max(0, x-1); i <= Math.min(x+1, board.length-1); i++){
			for(int j = Math.max(0, y-1); j <= Math.min(y+1, board[0].length-1); j++){
				if(board[i][j] == AIBoard.getOppositeBlob(blob)){
					board[i][j] = blob;
				}
			}
		}
	}

	private void clearCell(Point position) {
		board[position.getX()][position.getY()] = 'e';
	}

}
