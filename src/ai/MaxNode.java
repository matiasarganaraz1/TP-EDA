package ai;

public class MaxNode extends Node {

	public MaxNode(AIBoard board, char blob, AIMovement movement) {
		this.board = board;
		this.movement = movement;
		value = Integer.MIN_VALUE;
		myBlob = blob;
	}

	@Override
	public boolean chooseMove(int val) {
		return value < val;
	}

	@Override
	public boolean pruneBranch(Integer val) {
		if (val != null) {
			return value >= val;
		}
		return false;
	}

	@Override
	public Node createChild(AIBoard board, char blob, AIMovement movement) {
		return new MinNode(board, blob, movement);
	}
}
