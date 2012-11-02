package ai;

import game.Point;

public interface Displacement {
	public int getDy();
	public int getDx();
	public AIMovement getMovement(Point from);
}
