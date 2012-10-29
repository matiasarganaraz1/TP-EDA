package ai;

import game.Board;
import game.Movement;
import game.Blob;


public class MaxNode extends Node{

	public MaxNode(Board board, Blob blob, Movement mov){
		this.board=board;
		this.movement = mov;
		value = Integer.MIN_VALUE;
		this.myBlob=blob;
	}

	@Override
	public boolean chooseMove(int val) {
		return value<val;
	}

	@Override
	public boolean pruneBranch(Integer val) {
		if(val!=null){
			return value>=val;
		}
		return false;
	}

	@Override
	public Blob getOpositeBlob(){
		return myBlob.getOpposite();
	}
	
	@Override
	public void addNewChild(Board board, Blob blob, Movement mov) {
		childs.add(new MinNode(board, blob, mov));
	}
}
