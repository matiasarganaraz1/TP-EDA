package gui;

import game.Board;
import game.BlobWars;
import game.Blob;
import game.D1;
import game.D2;
import game.Displacement;
import game.Point;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class GamePanel extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BlobWars game;
	private static final int BLOB_SIZE = 64;
	private Image blackBlob, whiteBlob, selectedWhite, selectedBlack, boardIm, cell_d1, cell_d2;
	private Window window;

	public GamePanel(BlobWars game, Window window) {
		try{
			this.window=window;
			this.game = game;
			blackBlob = ImageUtils.loadImage("./resources/apple.png");
			whiteBlob = ImageUtils.loadImage("./resources/android.png");
			boardIm = ImageUtils.loadImage("./resources/board.png");
			selectedWhite = ImageUtils.loadImage("./resources/lighted-android.png");
			selectedBlack = ImageUtils.loadImage("./resources/lighted-apple.png");
			cell_d1 = ImageUtils.loadImage("./resources/cell-d1.png");
			cell_d2 = ImageUtils.loadImage("./resources/cell-d2.png");
		}catch(IOException e){
			JOptionPane.showMessageDialog(window, "An error has occurred while uploading the images");
			System.exit(0);
		}
		setPreferredSize(new Dimension(Board.SIZE * BLOB_SIZE, Board.SIZE * BLOB_SIZE));
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent m) {
				int row=m.getY()/BLOB_SIZE;
				int col=m.getX()/BLOB_SIZE;
				GamePanel.this.window.play(row,col);
			}
		});
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		drawGrid(g);
		drawBlobs(g);
	}

	private void drawGrid(Graphics g) {
		g.drawImage(boardIm,0,0,BLOB_SIZE*Board.SIZE,BLOB_SIZE*Board.SIZE, null);
		drawPosiblesMoves(g);
	}

	private void drawPosiblesMoves(Graphics g) {
		Point p=game.getSelectedBlob();
		if(p != null){
			drawPosiblesMoves(g, p, D1.values(), cell_d1);
			drawPosiblesMoves(g, p, D2.values(), cell_d2);
		}
		D1.values();
	}
			
			
	private void drawPosiblesMoves(Graphics g, Point p, Displacement[] dis, Image img){
		int x=p.getX();
		int y=p.getY();
		Board board = game.getBoard();
		for (Displacement d : dis) {
			int r = x + d.getRow();
			int c = y + d.getCol();;
			if(board.contains(r,c) && board.getBlob(r,c) == Blob.EMPTY){
				g.drawImage(img, c*BLOB_SIZE, r*BLOB_SIZE, BLOB_SIZE, BLOB_SIZE, null);
			}
		}
	}
//			imin=Math.max(0, x-2);
//			imax=Math.min(x+2, Board.SIZE-1);
//			jmin=Math.max(0, y-2);
//			jmax=Math.min(y+2, Board.SIZE-1);
//			for(int i=imin; i<=imax; i++) {
//				for(int j=jmin; j<=jmax; j++) {
//					if(board.getBlob(i,j) == Blob.EMPTY) {
//						if(p.distanceTo(new Point(i,j))<2)
//							g.drawImage(cell_d1, j*BLOB_SIZE, i*BLOB_SIZE, BLOB_SIZE, BLOB_SIZE, null);
//						else
//							g.drawImage(cell_d2, j*BLOB_SIZE, i*BLOB_SIZE, BLOB_SIZE, BLOB_SIZE, null);
//						}
//				}
//			}
//		}
//	}

	public int getWidth(){
		return boardIm.getWidth(null);
	}
	
	public int getHeight(){
		return boardIm.getHeight(null);
	}
	
	
	private void drawBlobs(Graphics g) {
		Board board= game.getBoard();
		for (int row = 0; row < Board.SIZE; row++) {
			for (int col = 0; col < Board.SIZE; col++) {
				Blob blob = board.getBlob(row, col);
				if (blob == Blob.PLAYER1) {
					g.drawImage(whiteBlob,col*BLOB_SIZE,row*BLOB_SIZE,BLOB_SIZE,BLOB_SIZE,null);
				} else if (blob == Blob.PLAYER2) {
					g.drawImage(blackBlob,col*BLOB_SIZE,row*BLOB_SIZE,BLOB_SIZE,BLOB_SIZE,null);
				}
			}
		}
		Point p = game.getSelectedBlob();
		if(p!=null){
			g.drawImage(((board.getBlob(p) == Blob.PLAYER1)?selectedWhite:selectedBlack), p.getY()*BLOB_SIZE, p.getX()*BLOB_SIZE, BLOB_SIZE, BLOB_SIZE, null);
		}
		
	}
}
