package gui;



import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

public class Menu extends JMenuBar{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JMenu file;
	private JMenu help;
	private Window window;
	
	public Menu(Window window){
		this.window=window;
		file=new JMenu("File");
		help=new JMenu("Help");
		JMenuItem newGame = new JMenuItem("New Game");
		newGame.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				Menu.this.window.getGame().newGame();
				Menu.this.window.repaint();
			}

			
			
		});
		JMenuItem howToPlay = new JMenuItem("How to play");
		howToPlay.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(Menu.this.window.getContentPane(), "/////////HELP");
				
			}
		});
		JMenuItem about = new JMenuItem("About BlobWars");
		about.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(Menu.this.window.getContentPane(), "BlobWars V1.0\nArgañaraz Matias\nNoriega Jose");
				
			}
		});
		JMenuItem exit = new JMenuItem("Exit");
		exit.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		
		file.add(newGame);
		file.add(exit);
		help.add(howToPlay);
		help.add(about);
		add(file);
		add(help);
	}
}
