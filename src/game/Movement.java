package game;

public abstract class Movement {

	protected Point from, to;
	protected Blob myBlob;
	
	public Movement(Point from, Point to, Blob blob){
		this.from = from;
		this.to = to;
		myBlob = blob;
	}
	
	public abstract boolean makeMovement(BlobWars game);
	public abstract boolean canBeMade(Board board);
	public abstract Board tryMovement(Board board);
	
	public Point getFrom(){
		return from;
	}
	
	public Point getTo(){
		return to;
	}
}
