package ai;

import game.Movement;
import game.Point;

public abstract class AIMovement {

	protected Point from, to;

	public AIMovement(Point from, Point to) {
		this.from = from;
		this.to = to;
	}

	public abstract AIBoard makeMovement(AIBoard board, char blob);
	public abstract Movement getMovement();

	public Point getFrom() {
		return from;
	}

	public Point getTo() {
		return to;
	}

	public boolean check() {
		return false;
	}
}
