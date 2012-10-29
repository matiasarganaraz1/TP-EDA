package game;

import java.util.Deque;
import java.util.LinkedList;
import ai.MiniMaxTree;


//import AI.MiniMaxTree;

public class BlobWars {
	private Board board;
	private Deque<BoardState> undo;
	private boolean pruned, timed;
	private int level;
	private int blobsCount;
	private GameListener listener;
	private boolean playerCanMove;
	private boolean playerTurn;
	private boolean hasEnded = false;
	private Point selectedBlob;

	public BlobWars(GameListener listener,int level, boolean pruned, boolean timed){
		this.listener=listener;
		this.timed=timed;
		this.pruned=pruned;
		this.level=level;
		newGame();
	}

	public void endOfGame() {
		hasEnded=true;
		int playerCount=board.countBlobs(Blob.PLAYER1);
		int computerCount=board.countBlobs(Blob.PLAYER2);
		listener.endOfGame(playerCount,computerCount);
	}

	public void endOfGame(Blob blob){
		for(int i=0; i<Board.SIZE; i++){
			for(int j=0; j<Board.SIZE; j++){
				if(board.getBlob(i, j) == Blob.EMPTY)
					board.putBlob(i, j, blob.getOpposite());
			}
		}
		endOfGame();
	}

	public int countBlobs(Blob blob){
		return board.countBlobs(blob);
	}

	public Movement computerSelectMovement() {
		MiniMaxTree tree = new MiniMaxTree(level, pruned, timed, 2, board);
		Movement movement = tree.getNextMove();
		if(movement == null){
			endOfGame(Blob.PLAYER2);
			return null;
		}
		selectedBlob = movement.from;
		return movement;
	}

	public void computerMakeMovement(Movement movement){
		movement.makeMovement(this);
		selectedBlob = null;
		playerTurn=true;
		if(!playerHasMoves())
			if(countBlobs(Blob.PLAYER1)==0 || blobsCount() == Board.SIZE*Board.SIZE)
				endOfGame();
			else
				listener.enablePass();
	}
	
	

	//		MiniMaxTree tree = new MiniMaxTree(level, board, pruned, timed, false, MiniMaxTree.CPUTURN);
	//		Position pos = tree.getNextMove();
	//		boolean cpuCanMove = pos != null;
	//		if (cpuCanMove) {
	//			board=board.putTile(pos.getRow(), pos.getCol(), Tile.PLAYER2);
	//			tileCount++;
	//		}
	//		
	//		if(!cpuCanMove&&!playerCanMove || tileCount>=Board.SIZE*Board.SIZE){
	//			endOfGame();
	//			return;
	//		}
	//		
	//		playerTurn=true;
	//		playerCanMove=board.playerHasMoves();
	//		if(!playerCanMove){
	//			if(cpuCanMove){
	//				listener.enablePass();
	//			}
	//			else{
	//				endOfGame();
	//			}
	//		}
	//	}

	public boolean playerTurn(int row, int col){
		if(playerTurn && playerCanMove){
			Point aux = board.selectBlob(row, col);
			if(selectedBlob == null || aux != null){
				selectedBlob = aux;
				return false;
			}
			double d = selectedBlob.distanceTo(new Point(row, col));
			if(d > 0  &&  d < 2.9){
				BoardState bs = new BoardState(board.clone(), blobsCount);
				if(((d < 1.5) ? board.cloneBlob(row, col, Blob.PLAYER1) : board.moveBlob(selectedBlob, row, col, Blob.PLAYER1))){
					undo.push(bs);
					listener.enableUndo();
					selectedBlob=null;
					playerTurn = false;
					if(countBlobs(Blob.PLAYER2) == 0) {
						hasEnded = true;
						endOfGame();
					}
					return true;
				}
			}
		}
		return false;
	}

	public Board getBoard() {
		return board;
	}

	public void newGame(){
		System.out.println("NEW GAME");
		board = new Board();
		if(undo!=null && !undo.isEmpty()){
			listener.disableUndo();
		}
		undo = new LinkedList<BoardState>();
		blobsCount = 4;
		playerTurn=true;
		playerCanMove=true;
		selectedBlob = null;
		hasEnded = false;
	}

	public Blob print(int row, int col) {
		return board.getBlob(row, col);
	}

	public Point getSelectedBlob() {
		return selectedBlob;
	}

	public void undo(){
		BoardState prevBoard = undo.pop();
		board = prevBoard.getBoard();
		blobsCount = prevBoard.getBlobCount();
		hasEnded = false;
		playerTurn = true;
		selectedBlob = null;
		if(undo.isEmpty())
			listener.disableUndo();
	}

	public boolean playerHasMoves() {
		return board.playerHasMoves();
	}

	public boolean hasEnded() {
		return hasEnded;
	}

	public int blobsCount(){
		return blobsCount;
	}

	public void increaseBlobCount() {
		blobsCount++;
	}

	public boolean cloneBlob(int row, int col, Blob blob) {
		boolean b = board.cloneBlob(row, col, blob);
		if(b)
			blobsCount++;
		return b;
	}

	public boolean moveBlob(Point from, int row, int col, Blob blob) {
		return board.moveBlob(from, row, col, blob);
	}

}
