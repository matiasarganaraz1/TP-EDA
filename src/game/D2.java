package game;



public enum D2 implements Displacement{
	DNORTH(-2, 0), DNORTHEAST(-2,2), DEAST(0,2), DSOUTHEAST(2,2), 
	DSOUTH(2,0), DSOUTHWEST(2,-2), DWEST(0,-2), DNORTHWEST(-2,-2),
	L_LU(-2,-1), L_RU(-2,1), L_UR(-1,2), L_DR(1,2), 
	L_RD(2,1), L_LD(2,-1), L_DL(1,-2),L_UL(-1,-2);

	private int dx, dy;
	
	private D2(int dx, int dy){
		this.dx=dx;
		this.dy=dy;
	}
	
	public int getDx() {
		return dx;
	}
	
	public int getDy() {
		return dy;
	}
}
