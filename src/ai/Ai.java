package ai;

import game.Board;
import game.D2;
import game.Displacement;
import game.Movement;
import game.Blob;
import game.D1;
import game.Point;

public class Ai {

	public static Movement getNextMove(Board board) {
		Displacement[][] displacements = {D1.values(), D2.values()};
		Point from,to;
		from=to=null;
		for(Displacement[] disps : displacements){
			for(int i=0; i < Board.SIZE; i++){
				for(int j=0; j<Board.SIZE;j++){
					for(Displacement d: disps){
						Point p = new Point(i,j);
						System.out.println(""+p+d);
						if(board.getBlob(i, j) == Blob.PLAYER2){
							from=new Point(i,j);
							to = new Point(i + d.getRow(), j + d.getCol());
							if(board.contains(to) && board.getBlob(to) == Blob.EMPTY){
								return d.getMovement(from);
							}
						}
					}
				}
			}
		}
		return null;
	}
}
