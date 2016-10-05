package com.servidorcliente;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;

import com.matriz.ListaActores;

/**
 * Interfaz grafica encargada de representar una lista de imagenes(ListaActores).
 * @author Luis Alfredo Piedra Esquivel
 * @since 02/10/2016
 * @see JFrame
 */
public class SubVentana extends JFrame{


	private static final long serialVersionUID = 1L;
	public Panel panel;
	public ListaActores actores;
	public JButton btn;
	public JComboBox listapoderes;	
	public JLabel mjs,label1,label2,label3;
	public JTextArea t1,t2,t3;
	
	
	/**
	 * Constructor de la clase
	 */
	public SubVentana() {
		setLayout(null);
		actores=new ListaActores();//Se inicializa la lista de actores(imagenes)
		panel=new Panel(actores);//panel encargado de dibujar las imagenes en la interfaz
		btn=new JButton("Aplicar");
		listapoderes=new JComboBox();
		listapoderes.addItem("Escudo");
		listapoderes.addItem("+velocidad");
		mjs=new JLabel("Poderes");
		label1=new JLabel("Combustible");
		label2=new JLabel("Escudo");
		label3=new JLabel("+Velocidad");
		t1=new JTextArea();
		t2=new JTextArea();
		t3=new JTextArea();
		t1.setEditable(false);//Se evita que los cuadros de texto sea editados debido a que solo se desea mostrar la informacion 
		t2.setEditable(false);
		t3.setEditable(false);
		panel.setBounds(0, 0, 700, 700);
		btn.setBounds(700,44,100,20);
		mjs.setBounds(720,0,100,20);
		label1.setBounds(705,100,100,20);
		t1.setBounds(705,125,90,20);
		t2.setBounds(705,175,90,20);
		t3.setBounds(705,225,90,20);
		label2.setBounds(710,150,100,20);
		label3.setBounds(705,200,100,20);
		listapoderes.setBounds(700,22,100,20);
		add(btn);//Se agregan los componentes de la interfaz
		add(mjs);
		add(label1);
		add(label2);
		add(label3);
		add(t1);
		add(t2);
		add(t3);
		add(listapoderes);
		add(panel);
		setTitle("Usuario");
		setSize(800,700);
		setDefaultCloseOperation(EXIT_ON_CLOSE); //Se asegura que todo proceso se finalice al cerrar la ventana
		setLocationRelativeTo(null);
		setVisible(false);
		setResizable(true);
		
	}
	
	/**
	 * Metodo que actualiza la lista de imagenes que sera representada en la interfaz.
	 * @param lista
	 */
	public void setImagenes(ListaActores lista){
		panel.setLista(lista);
	}




	
	
}
