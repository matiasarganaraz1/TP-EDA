package gui;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.JPanel;


public class ImagePanel extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Image image;
	
	public ImagePanel(Image image) {
		if (image != null) {
			this.image = image;
		}

	}
	
	@Override
	public void paintComponent(Graphics g) {
		if(image!=null){
			g.drawImage(image,0,0,image.getWidth(null),image.getHeight(null), null);
			
		}
		setOpaque(false);
		super.paintComponent(g);
	}
	public int getWidth(){
		return (image!=null)?image.getWidth(null):super.getWidth();
	}
	
	public int getHeight(){
		return (image!=null)?image.getHeight(null):super.getHeight();
	}
}
