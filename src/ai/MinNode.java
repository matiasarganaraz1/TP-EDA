package ai;

import game.Board;
import game.Movement;
import game.Blob;


public class MinNode extends Node{

	public MinNode(Board board, Blob blob, Movement mov){
		this.board = board;
		value = Integer.MAX_VALUE;
		this.movement = mov;
		this.myBlob = blob;
	}

	@Override
	public boolean chooseMove(int val) {
		return value>val;
	}

	@Override
	public boolean pruneBranch(Integer val) {
		if(val!=null){
			return value<=val;
		}
		return false;
	}
	
	@Override
	public Blob getOpositeBlob(){
		return myBlob.getOpposite();
	}
	
	@Override
	public void addNewChild(Board board, Blob blob, Movement mov) {
		childs.add(new MaxNode(board, blob, mov));
	}
}