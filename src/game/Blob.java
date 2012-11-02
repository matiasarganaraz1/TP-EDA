package game;


public enum Blob {
	PLAYER1,PLAYER2, EMPTY, ROCK;
	
	public Blob getOpposite(){
		if(this==PLAYER1){
			return Blob.PLAYER2;
		}else if(this==PLAYER2){
			return Blob.PLAYER1;
		}
		return Blob.EMPTY;
	}
	
	@Override
	public String toString() {
		switch (this){
		case PLAYER1:
			return"Player1";
		case PLAYER2:
			return "Player2";
		case ROCK:
			return "Rock";
		default:
			return "empty  ";
		}
	}
	
	
}
