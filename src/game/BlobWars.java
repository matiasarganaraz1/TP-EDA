package game;


//import AI.MiniMaxTree;

public class BlobWars {
	private Board board;
	private boolean pruned, timed;
	private int level;
	private GameListener listener;
	private boolean playerCanMove;
	private boolean playerTurn;
	private Point selectedChip;
	private int chipCount;
	
	public BlobWars(GameListener listener,int level, boolean pruned, boolean timed){
		this.listener=listener;
		this.timed=timed;
		this.pruned=pruned;
		this.level=level;
		newGame();
	}
	
	private void endOfGame() {
		int playerCount=board.countChips(Chip.PLAYER1);
		int computerCount=board.countChips(Chip.PLAYER2);
		listener.endOfGame(playerCount-computerCount);
	}
	
//	public void computerTurn() {
//		MiniMaxTree tree = new MiniMaxTree(level, board, pruned, timed, false, MiniMaxTree.CPUTURN);
//		Position pos = tree.getNextMove();
//		boolean cpuCanMove=false;
//		if (pos != null) {
//			board=board.putTile(pos.getRow(), pos.getCol(), Tile.PLAYER2);
//			tileCount++;
//			cpuCanMove=true;
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
			Point aux = board.selectChip(row, col);
			if(selectedChip == null || aux != null){
				selectedChip = aux;
				return false;
			}
				double d = selectedChip.distanceTo(new Point(row, col));
				System.out.println("distancia: " + d);
				if(d > 0  &&  d < 2.9){
					if(((d < 1.5) ? board.cloneChip(row, col, Chip.PLAYER1) : board.moveChip(selectedChip, row, col, Chip.PLAYER1))){
						board.attack(new Point(row, col), Chip.PLAYER1);
						chipCount++;
						selectedChip=null;
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
		board=new Board(Board.DEFAULT_FIELD);
		chipCount=4;
		playerTurn=true;
		playerCanMove=true;
	}

	public Chip print(int row, int col) {
		return board.getChip(row, col);
	}
	
}
