package game;

public enum Direction {
	NORTH(-1, 0), NORTHEAST(-1,1), EAST(0,1), SOUTHEAST(1,1), SOUTH(1,0), SOUTHWEST(1,-1), WEST(0,-1), NORTHWEST(-1,-1);
	
	private int row, col;
	
	private Direction(int row, int col){
		this.row=row;
		this.col=col;
	}
	public int getCol() {
		return col;
	}
	public int getRow() {
		return row;
	}
	
	public Direction getOpposite(){
		switch (this){
			case NORTH:
				return SOUTH;
			case SOUTH:
				return NORTH;
			case EAST:
				return WEST;
			case WEST:
				return EAST;
			case NORTHEAST:
				return SOUTHWEST;
			case SOUTHWEST:
				return NORTHEAST;
			case SOUTHEAST:
				return NORTHWEST;
			default:
				return SOUTHEAST;
		}
	}
}
