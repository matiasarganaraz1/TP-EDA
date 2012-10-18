package game;

public class Point {

	private int x, y;

	public Point(int x, int y){
		this.x=x;
		this.y=y;
	}

	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	
	@Override
	public String toString() {
		return "("+x+", "+y+")";
	}

	public void setY(int y) {
		this.y = y;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public double distanceTo(Point point){
		int dx = point.getX() - this.getX();
		int dy = point.getY() - this.getY();
		return Math.sqrt(dx * dx + dy * dy);
	}

}
