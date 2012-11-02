package ai;

import game.Blob;
import game.BlobWars;
import game.Movement;
import game.Point;



public enum D2 implements Displacement{
	DNORTH(-2, 0), DNORTHEAST(-2,2), DEAST(0,2), DSOUTHEAST(2,2), 
	DSOUTH(2,0), DSOUTHWEST(2,-2), DWEST(0,-2), DNORTHWEST(-2,-2),
	L_LU(-2,-1), L_RU(-2,1), L_UR(-1,2), L_DR(1,2), 
	L_RD(2,1), L_LD(2,-1), L_DL(1,-2),L_UL(-1,-2);

	private int dx, dy;
	
	private D2(int row, int col){
		this.dx=row;
		this.dy=col;
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
				return aux.moveBlob(from, to.getX(), to.getY(), blob);
			}
			
			@Override
			public String toString(){
				return "Move "+super.toString();
			}
			
			@Override
			public Movement getMovement() {
				return new Movement(from, new Point(dx + from.getX(), dy + from.getY())) {

					@Override
					public boolean makeMovement(BlobWars game, Blob blob) {
						return game.moveBlob(from, this.to.getX(), this.to.getY(), blob);
					}
				};
			}
		};
	}
}
