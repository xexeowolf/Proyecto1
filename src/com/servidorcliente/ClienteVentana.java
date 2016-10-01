package com.servidorcliente;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import org.json.JSONObject;

import com.matriz.ListaActores;
import com.matriz.NodoActor;

public class ClienteVentana extends Thread implements ActionListener,KeyListener {

	private SubVentana pantalla;
	private JFrame ventana;
	private JLabel label1,label2,label3;
	private JTextField ip,nick,puerto;
	private JButton btn;
	private int PuertoV;
	int x;
	
	public ClienteVentana() {
		x=0;
		pantalla=new SubVentana();
		pantalla.addKeyListener(this);
		ventana=new JFrame("Bienvenido");
		ventana.setBounds(0,0,600,175);
		label1=new JLabel("Ingrese un nombre: ");
		label1.setBounds(10,30,200,20);
		label2=new JLabel("Ingrese la direccion IP: ");
		label2.setBounds(10,60,200,20);
		label3=new JLabel("Ingrese un puerto para iniciar la conexion: ");
		label3.setBounds(10,90,400,20);
		ip=new JTextField();
		ip.setBounds(350,60,200,20);
		nick=new JTextField();
		nick.setBounds(350,30,200,20);
		puerto=new JTextField();
		puerto.setBounds(350,90,200,20);
		btn=new JButton("Enviar");
		btn.setBounds(250,120,80,20);
		btn.addActionListener(this);
		ventana.setVisible(true);
		ventana.setLayout(null);
		ventana.setResizable(false);
		ventana.add(label1);
		ventana.add(label2);
		ventana.add(label3);
		ventana.add(ip);
		ventana.add(nick);
		ventana.add(puerto);
		ventana.add(btn);
		pantalla.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent ep){
				try{
					Socket cli= new Socket("192.168.1.62",9095);
					JSONObject medio= new JSONObject();
					medio.put("val",2);
					medio.put("puerto",PuertoV);
					ObjectOutputStream flujo= new ObjectOutputStream(cli.getOutputStream());
					flujo.writeObject(medio);
					cli.close();
				} catch(Exception ex){System.out.print("Error al enviar actualizacion en el cliente\n");ex.printStackTrace();}
				System.exit(0);
			}
		});
		}

	public void setConexion(){
		try{
			Socket cli= new Socket("192.168.1.62",9095);
			JSONObject medio= new JSONObject();
			PuertoV=Integer.parseInt(puerto.getText());
			medio.put("puerto", PuertoV);
			medio.put("val",1);
			medio.put("IP",ip.getText());
			ObjectOutputStream flujo= new ObjectOutputStream(cli.getOutputStream());
			flujo.writeObject(medio);
			cli.close();
		} catch(Exception ex){System.out.print("Error al enviar actualizacion en el cliente\n");ex.printStackTrace();}
	}
	
	public void enviar(String dir){
		try{
			Socket cli= new Socket("192.168.1.62",9095);
			JSONObject medio= new JSONObject();
			medio.put("val",5);
			medio.put("direccion", dir);
			medio.put("puerto",PuertoV);
			ObjectOutputStream flujo= new ObjectOutputStream(cli.getOutputStream());
			flujo.writeObject(medio);
			cli.close();
		} catch(Exception ex){System.out.print("Error al enviar actualizacion en el cliente\n");ex.printStackTrace();}
	}


	@Override
	public void actionPerformed(ActionEvent arg0) {
		setConexion();
		ventana.setVisible(false);
		pantalla.setVisible(true);
		

	}

	@Override
	public void keyPressed(KeyEvent x) {
		switch(x.getKeyCode()){
		case KeyEvent.VK_DOWN:enviar("abj")/*System.out.print("abajo")*/;break;
		case KeyEvent.VK_UP:enviar("arr")/*System.out.print("arriba")*/;break;
		case KeyEvent.VK_LEFT:enviar("izq")/*System.out.print("izq")*/;break;
		case KeyEvent.VK_RIGHT:enviar("der")/*System.out.print("der")*/;break;}
		}

	
	public void run(){
		try{
			ServerSocket serv=new ServerSocket(9096);//PuertoV
			Socket cli;
			while(true){
				cli=serv.accept();
				ObjectInputStream flujo = new ObjectInputStream(cli.getInputStream());
				JSONObject recibido=new JSONObject();
				recibido=(JSONObject)flujo.readObject();
				int cont=recibido.getInt("Cantidad");
				ListaActores nueva=new ListaActores();
				while(cont!=0){
					nueva.add(recibido.getString("Imagen"+Integer.toString(cont)),recibido.getInt("PosX"+Integer.toString(cont)),recibido.getInt("PosY"+Integer.toString(cont)) ,"");
					cont--;
				}
				pantalla.setImagenes(nueva);
				cli.close();
			}
		} catch(Exception g){System.out.print("Error al recibir en el hilo principal");g.printStackTrace();}
	}
	
	
	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	public static void main(String[] args) {
		ClienteVentana lol=new ClienteVentana();
		lol.start();
	}

}
