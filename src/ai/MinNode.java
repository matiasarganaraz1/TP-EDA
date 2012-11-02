package ai;

public class MinNode extends Node{

	public MinNode(AIBoard board, char blob, AIMovement movement){
		this.board = board;
		this.movement = movement;
		value = Integer.MAX_VALUE;
		myBlob = blob;
	}

	@Override
	public boolean chooseMove(int val) {
		return val < value;
	}

	@Override
	public boolean pruneBranch(Integer val) {
		if(val!=null){
			return value<=val;
		}
		return false;
	}
	

	@Override
	public void getValue(){
		super.getValue();
		value*=-1;
	}
	
	@Override
	public Node createChild(AIBoard board, char blob, AIMovement movement) {
		return new MaxNode(board, blob, movement);
	}
}