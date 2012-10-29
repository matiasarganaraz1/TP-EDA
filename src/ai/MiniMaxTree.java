package ai;

import game.Board;
import game.D1;
import game.D2;
import game.Displacement;
import game.Movement;
import game.Blob;

public class MiniMaxTree {

	public static int PLAYERTURN=1, COMPUTERTURN=2;
	private Node root;
	private int limit;
	private boolean prune, timed;
	private Blob blob;
	private Board board;
	public static Displacement[][] displacements = {D1.values(), D2.values()};

	public MiniMaxTree(int limit, boolean prune, boolean timed, int player, Board board) {
		this.limit=limit;
		this.prune = prune;
		this.timed=timed;
		blob = player == COMPUTERTURN ? Blob.PLAYER2 : Blob.PLAYER1;
		root = new MaxNode(board, blob, null);
		this.board=board;
	}
	
	public static Displacement[][] getDisplacements(){
		return displacements;
	}
	
	public Movement getNextMove(){
		return timed?getNextMoveByTime() : getNextMoveByLevel(limit, null);
	}
	
	private Movement getNextMoveByLevel(int limit, TimeWatcher timeWatcher){
		root = new MaxNode(board, blob, null);
		Movement ans = root.nextMove(limit, 0, prune, null, timeWatcher);
		return ans;
	}
	
	
	//por ahora lo deje casi igual que como estaba en el otro tp. 
	//cambie lo de que tiraba la excepcion.
	//hay que ver si se puede mejorar porque es re choto lo que
	//hace este algoritmo.
	//primero entra con maxlevel 1. si le queda tiempo, entra con 2. con 3...
	//y asi sucesivamente. con lo cual se va a la mierda el orden que tiene el algoritmo por
	//nivel, que de por si es enorme. 
	private Movement getNextMoveByTime(){
		Movement tryingMov=null, currentAns=null;
		TimeWatcher timeWatcher=new TimeWatcher(limit);
		int level=1;
		while(!timeWatcher.timeFinished()){
			currentAns=tryingMov;
			tryingMov=getNextMoveByLevel(level, timeWatcher);
			level++;				
		}
		return currentAns;
	}
	
}
