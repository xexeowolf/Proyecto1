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
	ListaActores actores;
	
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
		NodoActor tmp=actores.getHead();
		while(tmp!=null){
			g2d.drawImage(tmp.getImagen(),tmp.getPosX(),tmp.getPosY(),this);
			tmp=tmp.next;
		}
		Toolkit.getDefaultToolkit().sync();
	    g2d.dispose();
	}
	
	
	public void setLista(ListaActores act){
		actores=act;
	}
	

}
