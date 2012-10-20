import javax.swing.*;
import java.awt.Graphics;
import javax.swing.border.Border;
class GoBoard extends JPanel{
    private int linien;

    public GoBoard(){
        this(9);    
    }

    public GoBoard(int pLinien){
        this.linien = pLinien;
        this.setBorder(BorderFactory.createEmptyBorder(0,10,10,10)); 
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        int d = 0;
        int h = 0;
        for(int i = 0; i < this.linien; i++){
            g.drawLine(0,h, getWidth(), h);
            g.drawLine(d,0,d,getHeight());
            h += getHeight()/this.linien;
            d +=getWidth()/this.linien;
        }
    }
}