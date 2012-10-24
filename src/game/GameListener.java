package game;

public interface GameListener {
	public void endOfGame(int playerCount, int computerCount);
	public void enablePass();
	public void enableUndo();
	public void disableUndo();
}
