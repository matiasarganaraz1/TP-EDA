package gui;

import game.Blob;
import game.GameListener;
import game.BlobWars;
import game.Movement;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
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
	private JButton undoButton, passButton, newGameButton;
	private JLabel status, undoLabel, passLabel, newGameLabel;
	private Menu menu;
	private JPanel container;
	private final String playerTurn="               Your turn!",computerTurn = "The computer is thinking...";

	public Window(int level, boolean pruned, boolean timed){
		super("BlobWars");
		GameListener listener=new GameListener(){
				public void endOfGame(int playerCount, int computerCount) {
					int score = playerCount - computerCount;
					passButton.setEnabled(false);
//					try {
//						Thread.sleep(100);
//					} catch (InterruptedException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
					Window.this.repaint();

						if(score<0){
							JOptionPane.showMessageDialog(Window.this,"Perdiste");
							status.setText("Perdiste");
//	                		JOptionPane.showMessageDialog(Window.this, null, "Perdiste", 0, new ImageIcon("./resources/YOULOOSE.png"));
						}else if(score>0){
							JOptionPane.showMessageDialog(Window.this,"Ganaste");
							status.setText("Ganaste");
//							JOptionPane.showMessageDialog(Window.this, null, "Ganaste", 0, new ImageIcon("./resources/youWon.png"));
//							
//							panel.setPreferredSize(new Dimension(552,240));
						}else{
							JOptionPane.showMessageDialog(Window.this,"Empate");
							status.setText("Empate");
//							panel = new ImageFrame(ImageUtils.loadImage("./resources/drawgame.png"));
//							panel.setPreferredSize(new Dimension(425,352));
						}
						JOptionPane.showMessageDialog(Window.this,"Player:           Computer:\n    "+playerCount+"                      "+computerCount);
						status.setText(null);
//					Window.this.repaint();
				}
			
				public void enablePass() {
					passButton.setEnabled(true);
					passButton.repaint();
				}
				
				public void enableUndo() {
					undoButton.setEnabled(true);
					undoLabel.setForeground(Color.black);
					undoButton.repaint();
				}
				
				public void disableUndo() {
					undoButton.setEnabled(false);
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
		
		gPanel = new GamePanel(game, this);
		setPreferredSize(new Dimension(background.getWidth() + windowWidth, background.getHeight() + 20 + windowHeight));
		background.add(gPanel);
		background.setLayout(null);
		container.add(background);
		
		JPanel info = new ImagePanel(null);
		status=new JLabel(playerTurn);
		
		//undo
		undoLabel = new JLabel("UNDO");
		undoLabel.setForeground(Color.DARK_GRAY);
		undoLabel.setFont(new Font("Arial", Font.BOLD, 20));
		undoButton = new JButton(new ImageIcon("./resources/button.png"));
		undoButton.setEnabled(false);
		undoButton.setOpaque(false);
		undoButton.setBorderPainted(false);
		undoButton.setBackground(new Color(123,123,123,0));
		undoButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				passButton.setEnabled(false);
				Window.this.game.undo();
				status.setText(playerTurn);
				repaint();
			}
		});
		
		passButton = new JButton(new ImageIcon("./resources/button.png"));
		passButton.setEnabled(false);
		passButton.setOpaque(false);
		passButton.setBorderPainted(false);
		passButton.setBackground(new Color(123,123,123,0));
		passButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {				
				Window.this.game.endOfGame(Blob.PLAYER1);
				repaint();
			}
		});
		
		newGameButton = new JButton(new ImageIcon("./resources/button.png"));
		newGameButton.setOpaque(false);
		newGameButton.setBorderPainted(false);
		newGameButton.setBackground(new Color(123,123,123,0));
		newGameButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Window.this.game.newGame();
				status.setText(playerTurn);
				repaint();
			}
		});
		
		info.setLayout(null);
		info.add(undoLabel);
		info.add(undoButton);
		info.add(status);
		info.add(passButton);
		info.add(newGameButton);
		container.paintImmediately(container.getBounds());
		status.setBounds(5, 10, 150, 15);
		newGameButton.setBounds(160, 0, 115, 35);
		undoButton.setBounds(275,0,115,35);
		undoLabel.setBounds(303, 1, 115, 35);
		passButton.setBounds(390,0,115,35);
//		status.setOpaque(true);
		status.setForeground(Color.white);
//		status.setBackground(new Color(100,100,100,124));
		info.setBounds(31, 540, background.getWidth(), 30);
		
		background.add(info);
		background.setBounds(0, 25, background.getWidth(), background.getHeight());
		menu.setBounds(0,0,background.getWidth(),25);
		gPanel.setBounds(31, 26, 512, 512);
		
		add(container);
		
		pack();
		setVisible(true);
		
	}
	
	public void play(int row, int col){
		if( !game.hasEnded() && game.playerTurn(row, col) && !game.hasEnded()) {
			status.setText(computerTurn);
			
			Movement mov = game.computerSelectMovement();
			container.paintImmediately(container.getBounds());
			try {
				Thread.sleep(700);
			} catch (InterruptedException e) {
				System.out.println("ERROR");
			}
			if(mov == null)
				status.setText("Computer can't move and pass");
			else {
				game.computerMakeMovement(mov);
				status.setText(playerTurn);
			}
		}
		container.paintImmediately(container.getBounds());
	}
	
	public BlobWars getGame() {
		return game;
	}
}
