package game;

public enum D1 implements Displacement{
	NORTH(-1, 0), NORTHEAST(-1,1), EAST(0,1), SOUTHEAST(1,1), 
	SOUTH(1,0), SOUTHWEST(1,-1), WEST(0,-1), NORTHWEST(-1,-1);
	
	private int row, col;
	
	private D1(int row, int col){
		this.row=row;
		this.col=col;
	}
	public int getCol() {
		return col;
	}
	public int getRow() {
		return row;
	}
	@Override
	public Movement getMovement(Point from) {
		return new Movement(from, new Point(row + from.getX(), col + from.getY())) {

			@Override
			public boolean makeMovement(BlobWars game) {

				return game.cloneBlob(this.to.getX(), this.to.getY(), Blob.PLAYER2);
			}

		};
	}
	
}
