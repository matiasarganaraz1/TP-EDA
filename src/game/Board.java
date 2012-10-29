package game;



public class Board implements Cloneable {

	public static final int SIZE = 8;

	private Blob[][] board = new Blob[SIZE][SIZE];
	
	public Board(){
		resetBoard();
	}
	
	public Board(Blob[][] board) {
		this.board = board;
	}

	public void resetBoard(){
		for(int i=0; i<Board.SIZE; i++){
			for(int j=0; j<Board.SIZE; j++){
				board[i][j]=Blob.EMPTY;
			}
		}
		board[0][0] = board[Board.SIZE-1][0] = Blob.PLAYER1;
		board[0][Board.SIZE-1] = board[Board.SIZE-1][Board.SIZE-1] = Blob.PLAYER2;
	}
	
	public Blob getBlob(int row, int col) {
		return board[row][col];
	}
	
	public Blob getBlob(Point position) {
		return board[position.getX()][position.getY()];
	}

	
	public int countBlobs(Blob blob){
		int count=0;
		for(int row=0; row<Board.SIZE; row++){
			for(int col=0; col<Board.SIZE; col++){
				if(board[row][col]==blob){
					count++;
				}
			}
		}
		return count;
	}
	
//	public Set<Point> getPossiblePositions(Chip chip) {
//		int actualRow, actualCol;
//		Set<Point> set = new HashSet<Point>();
//		for (int row = 0; row < SIZE; row++) {
//			for (int col = 0; col < SIZE; col++) {
//				if (board[row][col] == chip.getOpposite()) {
//					for (Direction dir : Direction.values()) {
//						actualRow = row + dir.getRow();
//						actualCol = col + dir.getCol();
//						if (!(actualRow < 0 || actualCol < 0
//								|| actualRow >= SIZE || actualCol >= SIZE)
//								&& board[actualRow][actualCol] == Chip.EMPTY) {
//							if (possibleChange(actualRow, actualCol, chip,
//									dir.getOpposite())) {
//								set.add(new Point(actualRow, actualCol));
//							}
//
//						}
//					}
//				}
//			}
//		}
//		return set;
//	}

	public Board clone() {
		Blob[][] clonedBoard = new Blob[SIZE][SIZE];
		for (int row = 0; row < SIZE; row++) {
			for (int col = 0; col < SIZE; col++) {
				clonedBoard[row][col] = board[row][col];
			}
		}
		return new Board(clonedBoard);
	}

	public Blob[][] getBoard() {
		return board;
	}

//	@Override
//	public String toString() {
//		String s = "";
//		for (int i = 0; i < SIZE; i++) {
//			for (int j = 0; j < SIZE; j++) {
//				s += board[i][j];
//			}
//			s += "\n";
//		}
//		return s;
//	}

	public boolean playerHasMoves() {
		Displacement[][] displacements = {D1.values(), D2.values()};
		for(int i=0; i < Board.SIZE; i++){
			for(int j=0; j<Board.SIZE;j++){
				if(board[i][j] == Blob.PLAYER1){
					for(Displacement[] disps:displacements){
						for(Displacement d: disps){
							Point to = new Point(i + d.getRow(), j + d.getCol());
							if(contains(to) && getBlob(to) == Blob.EMPTY)
								return true;
						}
					}
				}
			}
		}
		return false;
	}

	public Point selectBlob(int row, int col) {
		return (board[row][col] == Blob.PLAYER1) ? new Point(row, col) : null;
	}

	public boolean cloneBlob(int row, int col, Blob blob) {
		if(board[row][col] == Blob.EMPTY){
			board[row][col] = blob;
			attack(new Point(row,col), blob);
			return true;
		}
		return false;
	}

	public boolean moveBlob(Point selectedBlob, int row, int col, Blob blob) {
		if(cloneBlob(row, col, blob)){
			clearCell(selectedBlob);
			return true;
		}
		return false;
	}
	
	public void putBlob(int row, int col, Blob blob){
		if(board[row][col] == Blob.EMPTY)
			board[row][col] = blob;
	}
	

	private void attack(Point position, Blob blob) {
		int imin,imax,jmin,jmax;
		int x = position.getX();
		int y = position.getY();
		imin=Math.max(0, x-1);
		imax=Math.min(x+1, board.length-1);
		jmin=Math.max(0, y-1);
		jmax=Math.min(y+1, board[0].length-1);
		for(int i=imin; i <= imax; i++){
			for(int j=jmin; j<=jmax; j++){
				if(board[i][j] == blob.getOpposite()){
					board[i][j] = blob;
				}
			}
		}
		
	}

	private void clearCell(Point position) {
		board[position.getX()][position.getY()] = Blob.EMPTY;
	}


	public boolean contains(Point to) {
		return contains(to.getX(), to.getY()); 
	}

	public boolean contains(int row, int col) {
		return row >= 0 && row < SIZE && col >=0 && col < SIZE;
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

}