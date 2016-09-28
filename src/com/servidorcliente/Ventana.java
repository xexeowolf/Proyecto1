package com.servidorcliente;

import java.awt.EventQueue;
import javax.swing.JFrame;
import com.matriz.MatrizDinamica;

public class Ventana extends JFrame{

	private static final long serialVersionUID = 1L;
	
	public Panel panel;
	public MatrizDinamica matriz;
	
	public Ventana() {
		matriz=new MatrizDinamica(20,20);
		panel=new Panel(matriz.getListaActores());
		add(panel);
		setTitle("Servidor");
		setSize(700,700);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
		setResizable(false);
		}	
	
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {

			@Override
			public void run() {
			Ventana gam = new Ventana();
			
			}
		});
	}

	@Override
	public void keyPressed(KeyEvent x) {
		switch(x.getKeyCode()){
		case KeyEvent.VK_DOWN:if(moto.getHead().getDireccion()!="arr"){moto.getHead().setDireccion("abj");}break;
		case KeyEvent.VK_UP:if(moto.getHead().getDireccion()!="abj"){moto.getHead().setDireccion("arr");}break;
		case KeyEvent.VK_LEFT:if(moto.getHead().getDireccion()!="der"){moto.getHead().setDireccion("izq");}break;
		case KeyEvent.VK_RIGHT:if(moto.getHead().getDireccion()!="izq"){moto.getHead().setDireccion("der");}break;}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}*/
}
