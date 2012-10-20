package game;

public abstract class Movement {

	protected Point from, to;
	
	public Movement(Point from, Point to){
		this.from = from;
		this.to = to;
	}
	
	public abstract boolean makeMovement(BlobWars game);
	
	public Point getFrom(){
		return from;
	}
	
	public Point getTo(){
		return to;
	}
}
