package gui;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JOptionPane;

public class ImageOptionPane extends JOptionPane{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Image image;
	
	public ImageOptionPane(Image img) {
		if (img != null)
			this.image = img;
	}
	
	@Override
	public void paintComponent(Graphics g) {
		if(image!=null){
			g.drawImage(image,0,0,image.getWidth(null),image.getHeight(null), null);
			
		}
		setOpaque(false);
		super.paintComponent(g);
	}

}
