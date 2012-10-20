package gui;

import game.Blob;
import game.Board;
import game.GameListener;
import game.BlobWars;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Window extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final int windowWidth = 6;
	private final int windowHeight = 33;
	private GamePanel gPanel;
	private BlobWars game;
	private JButton undoButton, passButton;
	private Menu menu;
	private JLabel status;
	private JPanel container;

	public Window(int level, boolean pruned, boolean timed){
		super("BlobWars");
		GameListener listener=new GameListener(){
				public void endOfGame(int score) {
					JFrame frame = new JFrame("Game Over");
					frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					frame.setLayout(new BorderLayout());
					ImageFrame panel;
					try {
						if(score<0){
							panel = new ImageFrame(ImageUtils.loadImage("./resources/YOULOOSE.png"));
						}else if(score>0){
							panel = new ImageFrame(ImageUtils.loadImage("./resources/youWon.png"));
							panel.setPreferredSize(new Dimension(552,240));
						}else{
							panel = new ImageFrame(ImageUtils.loadImage("./resources/drawgame.png"));
							panel.setPreferredSize(new Dimension(425,352));
						}
						frame.getContentPane().add(panel, BorderLayout.CENTER);	
						frame.setVisible(true);
						frame.pack();
					} catch (IOException e) {
						JOptionPane.showMessageDialog(Window.this, "There's has been an error while loading the images");
					}
//					Window.this.passButton.setEnabled(false);
					Window.this.repaint();
				}
			
				public void enablePass() {
					undoButton.setEnabled(true);
					undoButton.repaint();
				}
			};
		game=new BlobWars(listener, level, pruned, timed);
		initializeContent();
		
		
	}
	
	private void initializeContent(){
		//Swing
		setResizable(false);
		setLocation(360, 50);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		container=new JPanel();
		container.setLayout(null);
		menu = new Menu(this);
		container.add(menu);
		
		ImagePanel background = null;
		try {
			background = new ImagePanel(ImageUtils.loadImage("./resources/background.png"));
		} catch (IOException e) {
			JOptionPane.showMessageDialog(Window.this, "There's has been an error while loading the images");
		}
		background.setPreferredSize(new Dimension(572,607));
		gPanel = new GamePanel(game, this);
		setPreferredSize(new Dimension(background.getWidth() + windowWidth, background.getHeight() + 20 + windowHeight));
		background.add(gPanel);
		background.setLayout(null);
		container.add(background);
		
		JPanel info = new ImagePanel(null);
		status=new JLabel("Your turn!");
		
		undoButton=new JButton("Undo");
		undoButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
//				Window.this.passButton.setEnabled(false);
//				Window.this.game.computerTurn();
				Window.this.game.undo();
				repaint();
			}
		});
		
		passButton=new JButton("Pass");
		passButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Window.this.game.endOfGame(Blob.PLAYER1);
				repaint();
			}
		});
		passButton.setEnabled(false);
		
		
		info.setLayout(null);
		info.add(undoButton);
//		info.add(status);
		info.add(passButton);
		
		undoButton.setBounds(90,10,80,20);
		status.setBounds(5, 5, 80, 15);
		status.setBackground(Color.blue);
		passButton.setBounds(180,10,80,20);
		info.setBounds(31, 540, background.getWidth(), 30);
		info.setBackground(Color.RED);
		background.add(info);
		
		background.setBounds(0, 25, background.getWidth(), background.getHeight());
		menu.setBounds(0,0,background.getWidth(),25);
		gPanel.setBounds(31, 26, 512, 512);
		
		add(container);
		
		pack();
		setVisible(true);
		
	}
	
	public void play(int row, int col){
		if(!game.playerHasMoves())
			passButton.setEnabled(true);
		boolean b = game.playerTurn(row, col);
		System.out.println("b: "+ b);
		container.paintImmediately(container.getBounds());
		if(b){
			status.setText("The computer is thinking...");
			
			game.computerTurn();
			status.setText("Your turn");
			container.paintImmediately(container.getBounds());
		}
		container.paintImmediately(container.getBounds());
	}
	
	public BlobWars getGame() {
		return game;
	}
}
