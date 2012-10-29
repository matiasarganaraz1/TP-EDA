package game;

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
	public int getCol() {
		return dy;
	}
	public int getRow() {
		return dx;
	}
	@Override
	public Movement getMovement(Point from, Blob blob) {
		return new Movement(from, new Point(dx + from.getX(), dy + from.getY()), blob){

			@Override
			public boolean makeMovement(BlobWars game) {

				return game.moveBlob(from, to.getX(), to.getY(), Blob.PLAYER2);
			}

			@Override
			public boolean canBeMade(Board board) {
				return board.getBlob(to) == Blob.EMPTY;
			}

			@Override
			public Board tryMovement(Board board) {
				board.moveBlob(from, to.getX(), to.getY(), myBlob);
				return board;
			}


		};
	}
}
