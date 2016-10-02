package com.servidorcliente;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;

import com.matriz.ListaActores;

public class SubVentana extends JFrame{


	private static final long serialVersionUID = 1L;
	public Panel panel;
	public ListaActores actores;
	public JButton btn;
	public JComboBox listapoderes;
	public JLabel mjs,label1,label2,label3;
	public JTextArea t1,t2,t3;
	
	
	public SubVentana() {
		setLayout(null);
		actores=new ListaActores();
		panel=new Panel(actores);
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
		t1.setEditable(false);
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
		add(btn);
		add(mjs);
		add(label1);
		add(label2);
		add(label3);
		add(t1);
		add(t2);
		add(t3);
		add(listapoderes);
		add(panel);
		setTitle("Cliente");
		setSize(800,700);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(false);
		setResizable(true);
		
	}
	
	public void setImagenes(ListaActores lista){
		panel.setLista(lista);
	}
	
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {

			@Override
			public void run() {
				SubVentana l=new SubVentana();
				
			
			}
		});
	}*/
	public static void main(String[] args){
		SubVentana y=new SubVentana();
	}



	
	
}
