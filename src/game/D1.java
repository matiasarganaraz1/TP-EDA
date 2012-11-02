package game;



public enum D1 implements Displacement{
	NORTH(-1, 0), NORTHEAST(-1,1), EAST(0,1), SOUTHEAST(1,1), 
	SOUTH(1,0), SOUTHWEST(1,-1), WEST(0,-1), NORTHWEST(-1,-1);
	
	private int dx, dy;
	
	private D1(int dx, int dy){
		this.dx=dx;
		this.dy=dy;
	}

	public int getDx() {
		return dx;
	}

	public int getDy() {
		return dy;
	}
}
