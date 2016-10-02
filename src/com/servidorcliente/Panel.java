package com.servidorcliente;

import com.matriz.*;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Panel  extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private Dimension d;
	private ListaActores actores;
	
	public Panel(ListaActores act) {
		actores=act;
		d=new Dimension(700,700);
		setDoubleBuffered(true);
		
	}
	
	public void paintComponent(Graphics g){
		doDrawing(g);
		repaint();
		}
	
	
	private void doDrawing(Graphics g){
		Graphics2D g2d=(Graphics2D) g;
		g.setColor(getBackground());
		g.fillRect(0, 0, getWidth(), getHeight());
		Image fondo=new ImageIcon(getClass().getClassLoader().getResource("Imagenes/carretera704x704.gif")).getImage();
		g2d.drawImage(fondo,0,0,this);
		NodoActor tmp=actores.getHead();
		while(tmp!=null){
			Image imagen=new ImageIcon(getClass().getClassLoader().getResource("Imagenes/"+tmp.getNomImagen())).getImage();
			g2d.drawImage(imagen,tmp.getPosX(),tmp.getPosY(),this);
			tmp=tmp.next;
			 Toolkit.getDefaultToolkit().sync();
			 Toolkit.getDefaultToolkit().sync();
			 
		}
		Toolkit.getDefaultToolkit().sync();
	    g2d.dispose();
	}
	
	
	public void setLista(ListaActores act){
		actores=act;
	}
	

}
