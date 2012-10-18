package game;

import java.util.HashSet;
import java.util.Set;


public class Board implements Cloneable {

	public static final int SIZE = 8;
	public static final Chip[][] DEFAULT_FIELD = {
		{ Chip.PLAYER1, Chip.EMPTY, Chip.EMPTY, Chip.EMPTY, Chip.EMPTY,
				Chip.EMPTY, Chip.EMPTY, Chip.PLAYER2 },
		{ Chip.EMPTY, Chip.EMPTY, Chip.EMPTY, Chip.EMPTY, Chip.EMPTY,
				Chip.EMPTY, Chip.EMPTY, Chip.EMPTY },
		{ Chip.EMPTY, Chip.EMPTY, Chip.EMPTY, Chip.EMPTY, Chip.EMPTY,
				Chip.EMPTY, Chip.EMPTY, Chip.EMPTY },
		{ Chip.EMPTY, Chip.EMPTY, Chip.EMPTY, Chip.EMPTY, Chip.EMPTY,
				Chip.EMPTY, Chip.EMPTY, Chip.EMPTY },
		{ Chip.EMPTY, Chip.EMPTY, Chip.EMPTY, Chip.EMPTY, Chip.EMPTY,
				Chip.EMPTY, Chip.EMPTY, Chip.EMPTY },
		{ Chip.EMPTY, Chip.EMPTY, Chip.EMPTY, Chip.EMPTY, Chip.EMPTY,
				Chip.EMPTY, Chip.EMPTY, Chip.EMPTY },
		{ Chip.EMPTY, Chip.EMPTY, Chip.EMPTY, Chip.EMPTY, Chip.EMPTY,
				Chip.EMPTY, Chip.EMPTY, Chip.EMPTY },
		{ Chip.PLAYER1, Chip.EMPTY, Chip.EMPTY, Chip.EMPTY, Chip.EMPTY,
				Chip.EMPTY, Chip.EMPTY, Chip.PLAYER2 } };

	private Chip[][] board;
	
	public Board(){
		this(DEFAULT_FIELD);
	}
	
	public Board(Chip[][] board) {
		this.board = board;
	}

	public Chip getChip(int row, int col) {
		return board[row][col];
	}
	
	public Chip getChip(Point position) {
		return board[position.getX()][position.getY()];
	}

	public Board putChip(int row, int col, Chip chip) {

		Board clone = this.clone();
		int count = 0;
		if (clone.board[row][col] == Chip.EMPTY) {
			clone.board[row][col] = chip;
			for (Direction dir : Direction.values()) {
				int rta;
				rta = clone.spread(row + dir.getRow(), col + dir.getCol(),
						chip, dir);
				if (rta > 0) {
					count += rta;
				}
			}
			if (count > 0) {
				return clone;
			}
		}
		return this;
	}
	
	public int countChips(Chip chip){
		int count=0;
		for(int row=0; row<Board.SIZE; row++){
			for(int col=0; col<Board.SIZE; col++){
				if(board[row][col]==chip){
					count++;
				}
			}
		}
		return count;
	}
	
	public Set<Point> getPossiblePositions(Chip chip) {
		int actualRow, actualCol;
		Set<Point> set = new HashSet<Point>();
		for (int row = 0; row < SIZE; row++) {
			for (int col = 0; col < SIZE; col++) {
				if (board[row][col] == chip.getOpposite()) {
					for (Direction dir : Direction.values()) {
						actualRow = row + dir.getRow();
						actualCol = col + dir.getCol();
						if (!(actualRow < 0 || actualCol < 0
								|| actualRow >= SIZE || actualCol >= SIZE)
								&& board[actualRow][actualCol] == Chip.EMPTY) {
							if (possibleChange(actualRow, actualCol, chip,
									dir.getOpposite())) {
								set.add(new Point(actualRow, actualCol));
							}

						}
					}
				}
			}
		}
		return set;
	}

	public boolean possibleChange(int row, int col, Chip Chip, Direction dir) {
		if (board[row][col] != game.Chip.EMPTY) {
			return false;
		}
		return possibleChangeR(row + dir.getRow(), col + dir.getCol(), Chip,
				dir) > 0;
	}

	private int possibleChangeR(int row, int col, Chip chip, Direction dir) {
		Chip thisChip;
		if ((row < 0 || row >= SIZE || col < 0 || col >= SIZE)
				|| (thisChip = board[row][col]) == Chip.EMPTY) {
			return -1;
		}
		if (thisChip == chip.getOpposite()) {
			int rta;
			rta = possibleChangeR(row + dir.getRow(), col + dir.getCol(), chip,
					dir);
			if (rta >= 0) {
				return ++rta;
			}
			return rta;
		}
		return 0;

	}

	private int spread(int row, int col, Chip tile, Direction dir) {
		Chip thisTile;
		if ((row < 0 || row >= SIZE || col < 0 || col >= SIZE)
				|| (thisTile = board[row][col]) == Chip.EMPTY) {
			return -1;
		}
		if (thisTile == tile.getOpposite()) {
			int rta;
			rta = spread(row + dir.getRow(), col + dir.getCol(), tile, dir);
			if (rta >= 0) {
				board[row][col] = thisTile.getOpposite();
				return ++rta;
			}
			return rta;
		}
		return 0;
	}

	public Board clone() {
		Chip[][] clonedField = new Chip[SIZE][SIZE];
		for (int row = 0; row < SIZE; row++) {
			for (int col = 0; col < SIZE; col++) {
				clonedField[row][col] = board[row][col];
			}
		}
		return new Board(clonedField);
	}

	public Chip[][] getField() {
		return board;
	}

	@Override
	public String toString() {
		String s = "";
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				s += board[i][j];
			}
			s += "\n";
		}
		return s;
	}

	public boolean playerHasMoves() {
		int myRow, myCol, possibleMoves = 0;
		for (int row = 0; row < Board.SIZE; row++) {
			for (int col = 0; col < Board.SIZE; col++) {
				if (getChip(row, col) == Chip.PLAYER2) {
					for (Direction dir : Direction.values()) {
						myRow = row + dir.getRow();
						myCol = col + dir.getCol();
						if (!(myRow < 0 || myCol < 0 || myRow >= Board.SIZE || myCol >= Board.SIZE)
								&& getChip(myRow, myCol) == Chip.EMPTY) {
							if (possibleChange(myRow, myCol, Chip.PLAYER1,
									dir.getOpposite())) {
								possibleMoves++;
							}

						}
					}
				}
			}
		}
		return possibleMoves >= 1;
	}

	public Point selectChip(int row, int col) {
		return (board[row][col] == Chip.PLAYER1) ? new Point(row, col) : null;
	}

	public boolean cloneChip(int row, int col, Chip chip) {
		System.out.println("CloneChip");
		if(board[row][col] == Chip.EMPTY){
			board[row][col] = chip;
			return true;
		}
		return false;
	}

	public boolean moveChip(Point selectedChip, int row, int col, Chip chip) {
		System.out.println("MoveChips");
		if(cloneChip(row, col, chip)){
			clearCell(selectedChip);
			return true;
		}
		return false;
	}

	private void clearCell(Point position) {
		System.out.println("clearCell  " + position);
		board[position.getX()][position.getY()] = Chip.EMPTY;
	}

	public void attack(Point position, Chip chip) {
		int imin,imax,jmin,jmax;
		int x = position.getX();
		int y = position.getY();
		imin=Math.max(0, x-1);
		imax=Math.min(x+1, board.length-1);
		jmin=Math.max(0, y-1);
		jmax=Math.min(y+1, board[0].length-1);
		for(int i=imin; i <= imax; i++){
			for(int j=jmin; j<=jmax; j++){
				if(board[i][j] == chip.getOpposite()){
					board[i][j] = chip;
				}
			}
		}
		
	}

}
