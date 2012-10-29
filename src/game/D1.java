package game;

public enum D1 implements Displacement{
	NORTH(-1, 0), NORTHEAST(-1,1), EAST(0,1), SOUTHEAST(1,1), 
	SOUTH(1,0), SOUTHWEST(1,-1), WEST(0,-1), NORTHWEST(-1,-1);
	
	private int dx, dy;
	
	private D1(int row, int col){
		this.dx=row;
		this.dy=col;
	}
	public int getCol() {
		return dy;
	}
	public int getRow() {
		return dx;
	}
	@Override
	public Movement getMovement(Point from, Blob blob) {
		return new Movement(from, new Point(dx + from.getX(), dy + from.getY()), blob) {

			@Override
			public boolean makeMovement(BlobWars game) {
				return game.cloneBlob(this.to.getX(), this.to.getY(), Blob.PLAYER2);
			}

			@Override
			public boolean canBeMade(Board board) {
				return board.getBlob(to) == Blob.EMPTY;
			}

			@Override
			public Board tryMovement(Board board) {
				board.cloneBlob(to.getX(), to.getY(), myBlob);
				return board;
			}

		};
	}
	
}
