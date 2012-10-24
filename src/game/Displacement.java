package game;

public interface Displacement {
	public int getCol();
	public int getRow();
	public Movement getMovement(Point from);
}
