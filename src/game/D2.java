package game;

public enum D2 implements Displacement{
	DNORTH(-2, 0), DNORTHEAST(-2,2), DEAST(0,2), DSOUTHEAST(2,2), 
	DSOUTH(2,0), DSOUTHWEST(2,-2), DWEST(0,-2), DNORTHWEST(-2,-2),
	L_LU(-2,-1), L_RU(-2,1), L_UR(-1,2), L_DR(1,2), 
	L_RD(2,1), L_LD(2,-1), L_DL(1,-2),L_UL(-1,-2);

	private int row, col;
	
	private D2(int row, int col){
		this.row=row;
		this.col=col;
	}
	public int getCol() {
		return col;
	}
	public int getRow() {
		return row;
	}
	@Override
	public Movement getMovement(Point from) {
		return new Movement(from, new Point(row + from.getX(), col + from.getY())){

			@Override
			public boolean makeMovement(BlobWars game) {

				return game.moveBlob(from, this.to.getX(), this.to.getY(), Blob.PLAYER2);
			}

		};
	}
}
