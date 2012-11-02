package ai;

import game.Board;
import game.Blob;
import game.Movement;

public class MiniMax {

	public static int PLAYERTURN=1, COMPUTERTURN=2;
	private Node root;
	private int limit;
	private boolean prune, timed;
	private char blob;
	private AIBoard board;
	public static int iterations;
	public static Displacement[][] displacements = {D1.values(), D2.values()};

	public MiniMax(int limit, boolean prune, boolean timed, int player, Board board) {
		this.limit=limit;
		this.prune = prune;
		this.timed=timed;
		blob = AIBoard.convertBlob(player == COMPUTERTURN ? Blob.PLAYER2 : Blob.PLAYER1);
		this.board = new AIBoard(board);
	}
	
	public static Displacement[][] getDisplacements(){
		return displacements;
	}
	
	public Movement getNextMove(){
		Movement aux= timed?getNextMoveByTime() : getNextMoveByLevel(limit, null);
		System.out.println("Iteraciones: "+iterations);
		iterations=0;
		return aux;
	}
	
	private Movement getNextMoveByLevel(int limit, TimeWatcher timeWatcher){
		root = new MaxNode(board, blob, null);
		iterations = 0;
		AIMovement aux = root.nextMove(limit, 0, prune, null, timeWatcher);
		return aux!=null?aux.getMovement():null;
		
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
		int level=2;
		while(!timeWatcher.timeFinished()){
//			if(tryingMov!=null)
				currentAns=tryingMov;
			tryingMov=getNextMoveByLevel(level, timeWatcher);
			System.out.println("Current "+currentAns);
			System.out.println("Level: "+level);
			level++;				
		}
		return currentAns;
	}
	
	public int getIterations(){
		return iterations;
	}
	
}
