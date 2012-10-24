package game;

public class BoardState {
	private Board board;
	private int blobCount;
	
	public BoardState(Board board, int blobCount){
		this.board = board;
		this.blobCount = blobCount;
	}
	
	public Board getBoard(){
		return board;
	}
	
	public int getBlobCount(){
		return blobCount;
	}

}
