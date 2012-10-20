import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class as{
	public static void main (String [] arg){
		JFrame ventana = new JFrame("Calculadora");
		ventana.setLayout(null);
		ventana.setSize(400,600);
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ventana.setVisible(true);
		
		JButton boton0 = new JButton(" 0 ");
         	boton0.setBackground( SystemColor.control );
        	boton0.setBounds(new Rectangle(50,50,100,75));
		ventana.add(boton0);
	}

}