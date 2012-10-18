package gui;

import game.Board;
import game.BlobWars;
import game.Chip;
import game.Point;

import java.awt.Color;
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
	private static final int CHIP_SIZE = 30;
	private Image blackTile, whiteTile, boardIm;
	private Window window;

	public GamePanel(BlobWars game, Window window) {
		try{
			this.window=window;
			this.game= game;
			blackTile= ImageUtils.loadImage("./resources/blacktile.png");
			whiteTile=ImageUtils.loadImage("./resources/whitetile.png");
			boardIm=ImageUtils.loadImage("./resources/board.png");
		}catch(IOException e){
			JOptionPane.showMessageDialog(window, "An error has occurred while uploading the images");
			System.exit(0);
		}
		
		setPreferredSize(new Dimension(Board.SIZE * CHIP_SIZE, Board.SIZE*CHIP_SIZE));
		setBackground(new Color(255,201,125));
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent m) {
				int row=m.getY()/CHIP_SIZE;
				int col=m.getX()/CHIP_SIZE;
				System.out.println(new Point(row, col));
				GamePanel.this.window.play(row,col);
			}
		});
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		drawGrid(g);
		drawTiles(g);
	}

	private void drawGrid(Graphics g) {
		g.setColor(Color.GRAY);
		g.drawImage(boardIm,0,0,CHIP_SIZE*Board.SIZE,CHIP_SIZE*Board.SIZE, null);
	}

	private void drawTiles(Graphics g) {
		Board board= game.getBoard();
		for (int row = 0; row < Board.SIZE; row++) {
			for (int col = 0; col < Board.SIZE; col++) {
				Chip tile = board.getChip(row, col);
				if (tile == Chip.PLAYER1) {
					g.drawImage(whiteTile,col*CHIP_SIZE,row*CHIP_SIZE,CHIP_SIZE,CHIP_SIZE,null);
				} else if (tile == Chip.PLAYER2) {
					g.drawImage(blackTile,col*CHIP_SIZE,row*CHIP_SIZE,CHIP_SIZE,CHIP_SIZE,null);
				}
			}
		}
	}
}
