import javax.swing.*;
import java.awt.Graphics;
import java.io.*;
import java.awt.*;

import javax.swing.border.Border;
import javax.swing.border.LineBorder;

class GoTest{
    private static void initGui(){
        JFrame frame = new JFrame("GoBoard");
        GoBoard jboard = new GoBoard();
        jboard.setLayout(new BorderLayout(10,10));
        jboard.setBorder(BorderFactory.createEmptyBorder(0,10,10,10)); 
        frame.add(jboard);



        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args){
        javax.swing.SwingUtilities.invokeLater(new Runnable(){
            public void run(){
                initGui();
            }
        });
    }
}