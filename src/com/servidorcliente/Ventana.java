package com.servidorcliente;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;

import com.matriz.MatrizDinamica;
/**
 * Interfaz grafica encargada de representar una lista de imagenes(ListaActores) relacionada con una matriz y los elementos que esta contiene.
 * @author Luis Alfredo Piedra Esquivel
 * @since 02/10/2016
 * @see JFrame
 */
public class Ventana extends JFrame{

	private static final long serialVersionUID = 1L;
	
	public Panel panel;
	public MatrizDinamica matriz;
	public JLabel tiempo,atrb,jug1,jug2,jug3,jug4;
	public JTextArea textjug1,textjug2,textjug3,textjug4,textiempo;
	
	
	/**
	 * Constructor de la clase
	 * @param filas numero de filas que tendra la matriz
	 * @param columnas numero de columnas que tendra la matriz
	 */
	public Ventana(int filas,int columnas) {
		setLayout(null);
		matriz=new MatrizDinamica(filas,columnas);//Matriz sobre la cual se realizan las acciones representadas graficamente
		panel=new Panel(matriz.getListaActores());//panel encargado de dibujar las imagenes
		panel.setBounds(0,0,700,700);
		atrb=new JLabel("Atributos");
		jug1=new JLabel("Jugador 1");
		jug2=new JLabel("Jugador 2");
		jug3=new JLabel("Jugador 3");
		jug4=new JLabel("Jugador 4");
		tiempo=new JLabel("Tiempo Partida");
		textjug1=new JTextArea();
		textjug2=new JTextArea();
		textjug3=new JTextArea();
		textjug4=new JTextArea();
		textiempo=new JTextArea();
		textjug1.setEditable(false);//Se evita que el usuario edite los cuadros de texto que muestran informacion relevante
		textjug2.setEditable(false);
		textjug3.setEditable(false);
		textjug4.setEditable(false);
		atrb.setBounds(760,10,100,20);
		jug1.setBounds(710,50,100,20);
		textjug1.setBounds(710,80,150,80);
		jug2.setBounds(710,200,100,20);
		textjug2.setBounds(710,220,150,80);
		jug3.setBounds(710,340,100,20);
		textjug3.setBounds(710,360,150,80);
		jug4.setBounds(710,480,100,20);
		textjug4.setBounds(710,500,150,80);
		tiempo.setBounds(710,610,200,20);
		textiempo.setBounds(710,630,140,20);
		add(panel);//Se agregan los componentes a la interfaz.
		add(atrb);
		add(jug1);
		add(jug2);
		add(jug3);
		add(jug4);
		add(tiempo);
		add(textjug1);
		add(textjug2);
		add(textjug3);
		add(textjug4);
		add(textiempo);
		setTitle("Servidor");
		setSize(900,720);
		setDefaultCloseOperation(EXIT_ON_CLOSE);//Se asegura que todo proceso termine al cerrar la ventana
		setLocationRelativeTo(null);
		setVisible(true);
		setResizable(false);//Se evita el usuario modifique el tamano de la ventana
		addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent ep){
				System.exit(0);
			}
		});
		}	
	
}
