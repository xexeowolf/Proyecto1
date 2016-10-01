package com.servidorcliente;

import java.awt.EventQueue;

import javax.swing.JFrame;

import com.matriz.ListaActores;

public class SubVentana extends JFrame{


	private static final long serialVersionUID = 1L;
	public Panel panel;
	public ListaActores actores;
	
	public SubVentana() {
		actores=new ListaActores();
		panel=new Panel(actores);
		add(panel);
		setTitle("Cliente");
		setSize(700,700);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(false);
		setResizable(false);
	}
	
	public void setImagenes(ListaActores lista){
		panel.actores=lista;
	}
	
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {

			@Override
			public void run() {
				SubVentana l=new SubVentana();
				
			
			}
		});
	}*/

	
	
}
