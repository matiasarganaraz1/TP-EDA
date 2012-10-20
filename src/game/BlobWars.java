package game;

import ai.Ai;


//import AI.MiniMaxTree;

public class BlobWars {
	private Board board,undo;
	private boolean pruned, timed;
	private int level;
	private int blobsCount;
	private GameListener listener;
	private boolean playerCanMove;
	private boolean playerTurn;
	private Point selectedBlob;
	
	public BlobWars(GameListener listener,int level, boolean pruned, boolean timed){
		this.listener=listener;
		this.timed=timed;
		this.pruned=pruned;
		this.level=level;
		newGame();
	}
	
	private void endOfGame() {
		int playerCount=board.countBlobs(Blob.PLAYER1);
		int computerCount=board.countBlobs(Blob.PLAYER2);
		listener.endOfGame(playerCount-computerCount);
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
	
	public void computerTurn() {
		Movement movement = Ai.getNextMove(board);
		if(movement == null)
			endOfGame(Blob.PLAYER2);
		movement.makeMovement(this);
		playerTurn=true;
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
		System.out.println("BlobCount: "+ blobsCount);
		if(playerTurn && playerCanMove){
			Point aux = board.selectBlob(row, col);
			if(selectedBlob == null || aux != null){
				selectedBlob = aux;
				return false;
			}
			undo = board.clone();
			double d = selectedBlob.distanceTo(new Point(row, col));
			System.out.println("distancia: " + d);
			if(d > 0  &&  d < 2.9){
				if(((d < 1.5) ? cloneBlob(row, col, Blob.PLAYER1) : board.moveBlob(selectedBlob, row, col, Blob.PLAYER1))){
					selectedBlob=null;
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean cloneBlob(int row, int col, Blob blob){
		boolean b = board.cloneBlob(row, col, blob);
		if(b)
			blobsCount++;
		return b;
	}
	
	public boolean moveBlob(Point from, int row, int col, Blob blob){
		boolean b = board.moveBlob(from, row, col, blob);
		if(b)
			blobsCount++;
		return b;
	}
	
	public Board getBoard() {
		return board;
	}
	
	public void newGame(){
		System.out.println("NEW GAME");
		board = new Board();
		undo = board.clone();
		blobsCount = 4;
		playerTurn=true;
		playerCanMove=true;
	}

	public Blob print(int row, int col) {
		return board.getBlob(row, col);
	}

	public Point getSelectedBlob() {
		return selectedBlob;
	}
	
	public void undo(){
		board = undo;
	}

	public boolean playerHasMoves() {
		double d = Math.random();
		return (d>0.5)?true : false;
	}
	
	
}
