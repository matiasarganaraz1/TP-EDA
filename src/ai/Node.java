package ai;

import game.*;

import java.util.LinkedList;
import java.util.List;

public abstract class Node{

	protected List<Node> childs=new LinkedList<Node>();
	protected Board board;
	protected Movement movement;
	protected int value;
	protected Blob myBlob;

	public abstract boolean chooseMove(int val);
	public abstract boolean pruneBranch(Integer val);
	public abstract Blob getOpositeBlob();
	public abstract void addNewChild(Board board,  Blob Blob, Movement mov);


	//Falta este tambien.
	public int getHeuristicalValue(){
		return 2;
	
	}



	public Movement nextMove(int maxLevel, long level, boolean prune, Integer parentVal, TimeWatcher timeWatcher){
		if(timeWatcher!=null){
			timeWatcher.checkTime();
		}
		if(maxLevel==level){ 
			getHeuristicalValue();
			return movement;
		}
		Movement ans = movement;
		level++;
		
		//revisar. yo lo hice asi, pero si vamos a usar lo que vos hiciste 
		//habria que adaptarlo. es un toque igual
		setChilds();
		for(Node child:childs){
			if(prune && pruneBranch(parentVal)){
				return null;
			}
			if(child.chooseMove(value)){
				child.nextMove(maxLevel, level, prune, value, timeWatcher);
				ans=child.movement;
				value=child.value;
			}
		}
		return ans;
	}

	public void setChilds(){	
		/**  aca iria lo tuyo.para probar
		 *** las dos alternativas, pero hacer que sea menos pesada esta,
		 *** puse a los displacements en la clase tree. asi estan cargados una sola vez en memoria
		*/ 
		
		Displacement[][] displacements = MiniMaxTree.getDisplacements();
		for(Displacement[] disps : displacements){
			for(int i=0; i < Board.SIZE; i++){
				for(int j=0; j<Board.SIZE;j++){
					for(Displacement d: disps){
						if(board.getBlob(i, j) == myBlob){
							Point from,to;
							from=new Point(i,j);
							to = new Point(i + d.getRow(), j + d.getCol());
							if(board.contains(to) && board.getBlob(to) == Blob.EMPTY){
								Movement movement = d.getMovement(from, myBlob);
								Board cBoard = board.clone();
								addNewChild(movement.tryMovement(cBoard), myBlob.getOpposite(), movement);
							}
						}
					}
				}
			}
		}
	}
}
