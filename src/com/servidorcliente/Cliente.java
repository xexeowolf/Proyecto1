package com.servidorcliente;

import org.json.*;

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

public class Cliente implements KeyListener,Runnable,ActionListener{
	JFrame ventana;
	JLabel label1,label2;
	JTextField ip,nick;
	JButton btn;
	
	String direccion;
	Interfaz interfaz;
	String IP;
	Actor jugador;
	String figura="bicho.gif";
	
	HiloMovimiento hiloM;
	
	public Cliente(){
		ventana=new JFrame("Bienvenido");
		ventana.setBounds(0,0,600,150);
		label1=new JLabel("Ingrese un nombre: ");
		label1.setBounds(10,30,200,20);
		label2=new JLabel("Ingrese la direccion IP: ");
		label2.setBounds(10,60,200,20);
		ip=new JTextField();
		ip.setBounds(230,60,200,20);
		nick=new JTextField();
		nick.setBounds(230,30,200,20);
		btn=new JButton("Enviar");
		btn.setBounds(250,90,80,20);
		btn.addActionListener(this);
		ventana.setVisible(true);
		ventana.setLayout(null);
		
		ventana.add(label1);
		ventana.add(label2);
		ventana.add(ip);
		ventana.add(nick);
		ventana.add(btn);
		ventana.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent ep){
				System.exit(0);
			}
		});
		}


	@Override
	public void actionPerformed(ActionEvent u) {
		if(u.getSource()==btn){
		interfaz=new Interfaz("Usuario-"+nick.getText(),700,700);
		interfaz.addKeyListener(this);
		jugador=new Actor(interfaz.getWidth(),interfaz.getHeight(),figura);
		interfaz.agregarActor("jugador1", jugador);
		ventana.setVisible(false);
		setConexion();
		hiloM=new HiloMovimiento(jugador,interfaz);
		hiloM.start();
		Thread hilo=new Thread(this);
		hilo.start();
		}
	}

	
	public void setConexion() {
		try{
			Socket cli= new Socket(ip.getText(),9095);
			JSONObject medio= new JSONObject();
			medio.put("conexion","1");
			medio.put("IP", "192.168.1.62");
			medio.put("PE", "9087");
			medio.put("PS", "9088");
			ObjectOutputStream flujo= new ObjectOutputStream(cli.getOutputStream());
			flujo.writeObject(medio);
			cli.close();
		} catch(Exception ex){System.out.println("Error al enviar en la conexion del cliente "+ex.getMessage());}
		
	}
	
	public void enviar() {
			try{
				Socket cli= new Socket(ip.getText(),9087);
				JSONObject medio= new JSONObject();
				medio.put("mensaje",direccion);
				ObjectOutputStream flujo= new ObjectOutputStream(cli.getOutputStream());
				flujo.writeObject(medio);
				cli.close();
			} catch(Exception ex){ex.printStackTrace();}//System.out.println("Error al enviar en el cliente "+ex.getMessage());}
		}

	
	public void keyPressed(KeyEvent x) {
		switch(x.getKeyCode()){
		case KeyEvent.VK_DOWN:direccion="abj";enviar();break;
		case KeyEvent.VK_UP:direccion="arr";enviar();break;
		case KeyEvent.VK_LEFT:direccion="izq";enviar();break;
		case KeyEvent.VK_RIGHT:direccion="der";enviar();break;}
		}
	
	public void run() {
		try{
			ServerSocket serv= new ServerSocket(9088);
			Socket c;
			JSONObject recibido=new JSONObject();
			while(true){
				c=serv.accept();
				ObjectInputStream flujo2= new ObjectInputStream(c.getInputStream());
				recibido=(JSONObject)flujo2.readObject();
				jugador.setPosX((int)recibido.get("X"));
				jugador.setPosY((int)recibido.get("Y"));
				c.close();}
		}catch(Exception e){System.out.println("Error al recibir en el cliente"+e.getMessage());}
	}
	
	public static void main(String[] args){
		new Cliente();
		
		
	}

	
	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
}
