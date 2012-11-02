package ai;

import game.*;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

public abstract class Node{

	protected AIBoard board;
	protected AIMovement movement;
	protected int value;
	protected char myBlob;

	
	public abstract boolean chooseMove(int val);
	public abstract boolean pruneBranch(Integer val);
	public abstract Node createChild(AIBoard board,  char Blob, AIMovement movement);


	
	public char getOppositeBlob(){
		return AIBoard.getOppositeBlob(myBlob);
	}
	
	public void getValue(){
		value=0;
		for(int i=0; i<Board.SIZE; i++){
			for(int j=0; j<Board.SIZE; j++){
				char blob = board.getBlob(i, j);
				value+= blob == myBlob?1:blob==getOppositeBlob()?-1:0;
			}
		}
	}



	public AIMovement nextMove(int maxLevel, long level, boolean prune, Integer parentVal, TimeWatcher timeWatcher){
		if(timeWatcher!=null){
			timeWatcher.checkTime();
			if(timeWatcher.timeFinished())
				return null;
		}
		
		
		if(maxLevel==level){ 
			getValue();
			return movement;
		}
		AIMovement ans = movement;
		for(Node child:childs()){
			if(prune && pruneBranch(parentVal))
				return null;
			
			child.nextMove(maxLevel, level+1, prune, value, timeWatcher);
			if(child.chooseMove(value)){
				ans=child.movement;
				value=child.value;
			}
		}
		return ans;
	}

	public List<Node> childs(){
		HashSet<AIMovement> movements = new HashSet<AIMovement>();
		List<Node> ans = new LinkedList<Node>();
		Displacement[][] displacements = MiniMax.getDisplacements();
		for(Displacement[] disps : displacements)
			for(int i=0; i < Board.SIZE; i++)
				for(int j=0; j<Board.SIZE;j++)
					for(Displacement d: disps){
						if(board.getBlob(i, j) == myBlob){
							Point from,to;
							from=new Point(i,j);
							to = new Point(i + d.getDx(), j + d.getDy());
							if(board.contains(to) && board.getBlob(to) == 'e'){
								AIMovement movement = d.getMovement(from);
								
								if(!movement.check() || !movements.contains(movement)){
									MiniMax.iterations++;
									movements.add(movement);
									ans.add(createChild(movement.makeMovement(board, myBlob), getOppositeBlob(), movement));
								}
							}
						}
					}
		
		return ans;
	}
}
