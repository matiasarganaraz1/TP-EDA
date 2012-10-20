package ai;

import game.BlobWars;
import game.Board;
import game.Movement;
import game.Blob;
import game.Direction;
import game.Point;

public class Ai {

	public static Movement getNextMove(Board board) {
		System.out.println("getNextMove");
		Point from,to;
		from=to=null;
		for(int i=0; i < Board.SIZE; i++){
			for(int j=0; j<Board.SIZE;j++){
				if(board.getBlob(i, j) == Blob.PLAYER2){
					for(int k=1;k<=2;k++){
						from=new Point(i,j);
						for (Direction dir : Direction.values()) {
							to = new Point(i + dir.getRow()*k, j + dir.getCol()*k);
							if(board.contains(to) && board.getBlob(to) == Blob.EMPTY){
								switch (k) {
									case 1:
										return new Movement(from, to){

												@Override
												public boolean makeMovement(
														BlobWars game) {
													
													return game.cloneBlob(this.to.getX(), this.to.getY(), Blob.PLAYER2);
												}
												
											};
//											break;
									case 2:
										return new Movement(from, to){

											@Override
											public boolean makeMovement(
													BlobWars game) {
												
												return game.moveBlob(from, this.to.getX(), this.to.getY(), Blob.PLAYER2);
											}
											
										};
//											break;
									}
							}
						}
					}
				}
			}
		}
		return null;
	}

}
