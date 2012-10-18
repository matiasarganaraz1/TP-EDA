package game;

import java.awt.Point;

public enum Chip {
	PLAYER1,PLAYER2, EMPTY;
	
	public Chip getOpposite(){
		if(this==PLAYER1){
			return Chip.PLAYER2;
		}else if(this==PLAYER2){
			return Chip.PLAYER1;
		}
		return Chip.EMPTY;
	}
	
	@Override
	public String toString() {
		switch (this){
		case PLAYER1:
			return"player1";
		case PLAYER2:
			return "player2";
		default:
			return "empty  ";
		}
	}
	
	
}
