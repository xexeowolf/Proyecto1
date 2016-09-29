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
		actores.add("moto.gif", 30, 30, "");
		actores.add("moto.gif", 130, 30, "");
		actores.add("moto.gif", 230, 30, "");
		actores.add("moto.gif", 30, 330, "");
		actores.add("moto.gif", 30, 230, "");
		panel=new Panel(actores);
		add(panel);
		setTitle("Cliente");
		setSize(700,700);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
		setResizable(false);
	}
	
	public void setImagenes(ListaActores lista){
		actores=lista;
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {

			@Override
			public void run() {
				new SubVentana();
				
			
			}
		});
	}

	
	
}
