package com.servidorcliente;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JPanel;
import com.matriz.*;


public class Interfaz extends Canvas {
	
	private static final long serialVersionUID = 1L;
	
	public int ancho;
	public int alto;
	public Cola actores;
	BufferedImage buffer;

	public Interfaz(String titulo,int a,int h) {
		alto=h;
		ancho=a;
		actores=new Cola();
		buffer=new BufferedImage(WIDTH,HEIGHT, BufferedImage.TYPE_INT_RGB);
		JFrame ventana=new JFrame(titulo);
		JPanel panel =(JPanel)ventana.getContentPane();
		setBounds(0,0,ancho,alto);
		panel.setPreferredSize(new Dimension(ancho,alto));
		panel.setLayout(null);
		panel.add(this);
		ventana.setBounds(0, 0, ancho, alto);
		ventana.setVisible(true);
		ventana.setResizable(false);
		ventana.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				System.exit(0);
			}
		});
	}
	
	public void agregarActor(String nombre,Actor A){
		actores.add(nombre, 9);
		
	}
	
	
	public void pintar(Actor A){
		
		Graphics g=A.getBuffer().getGraphics();
		g.setColor(getBackground());
		g.fillRect(0, 0, getWidth(), getHeight());
		g.drawImage(A.getFigura(),A.getPosX(),A.getPosY(),this);
	}
	
	public void pintarBuffer(Graphics g,Actor A){
		g.drawImage(A.getBuffer(),0,0,this);
	}
	public void pintarB(Graphics g){
		g.drawImage(buffer,0,0,this);
		
	}
	/*public void aplicarCambios(int cantJugadores){
			int cont=cantJugadores;
		    while(cont!=0){
		    	String nom=actores.getNombre();
				Actor temp= actores.removeA();
				pintar(temp);
				actores.add(nom, temp);
				cont--;
			}   
		}
	public void aplicarBuffer(int c){
		while(c!=0){
    		String nom=actores.getNombre();
			Actor temp= actores.removeA();
			pintarBuffer(getGraphics(),temp);
			actores.add(nom, temp);
			c--;
    	}
	}
	
	public static void main(String[] args){
		int k=8;
		String j="jugador"+k;
		System.out.print(j);
	}*/
	
}