package com.servidorcliente;

import java.awt.EventQueue;
import javax.swing.JFrame;
import com.matriz.MatrizDinamica;

public class Ventana extends JFrame{

	private static final long serialVersionUID = 1L;
	
	public Panel panel;
	public MatrizDinamica matriz;
	
	public Ventana(int filas,int columnas) {
		matriz=new MatrizDinamica(filas,columnas);
		panel=new Panel(matriz.getListaActores());
		add(panel);
		setTitle("Servidor");
		setSize(700,720);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
		setResizable(false);
		}	
}
