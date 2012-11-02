package ai;

import game.BlobWars;
import game.Movement;
import game.Point;
import game.Blob;



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
	
	@Override
	public AIMovement getMovement(Point from) {
		return new AIMovement(from, new Point(dx + from.getX(), dy + from.getY())) {

			@Override
			public AIBoard makeMovement(AIBoard board, char blob) {
				AIBoard aux = board.clone();
				return aux.cloneBlob(to.getX(), to.getY(), blob);
			}

			@Override
			public Movement getMovement() {
				return new Movement(from, new Point(dx + from.getX(), dy + from.getY())) {

					@Override
					public boolean makeMovement(BlobWars game, Blob blob) {
						return game.cloneBlob(this.to.getX(), this.to.getY(), blob);
					}
					
					@Override
					public String toString(){
						return "Clone "+super.toString();
					}
				};
			}
			
			
			@Override
			public boolean check(){
				return true;
			}
			
			@Override
			public int hashCode(){
				return 10*(to.getX()+1)+to.getY();
			}
			
			@Override
			public boolean equals(Object obj){
				AIMovement aux = (AIMovement)obj;
				return aux.to.getX() == to.getX() && aux.to.getY() == to.getY();
			}
		};
	}

}
