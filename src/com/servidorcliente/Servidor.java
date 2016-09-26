package com.servidorcliente;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;




public class Servidor {


	private Interfaz interfaz;
	private Actor jugador1;
	private Actor jugador2;
	private Actor jugador3;
	private Actor jugador4;
	
	private HiloMovimiento hiloM1;
	private HiloMovimiento hiloM2;
	private HiloMovimiento hiloM3;
	private HiloMovimiento hiloM4;
	
	private int cantJugadores;
	
	public JFrame ventana;
	public JTextField txt;
	public JLabel prg;
	public JButton btn;
	
	public Servidor() {
		interfaz=new Interfaz("Servidor",700,700);
		cantJugadores=0;
	}
	
	public void setCliente(String ip,int pe,int ps,Servidor serv){
		cantJugadores++;
		switch(cantJugadores){
		case 1:jugador1=new Actor(interfaz.getWidth(),interfaz.getHeight(),"bicho.gif");interfaz.agregarActor("jugador"+cantJugadores, jugador1);/*if(hiloM1.isAlive()){hiloM1.stop();};*/hiloM1=new HiloMovimiento(jugador1,interfaz);hiloM1.start();break;
		case 2:jugador2=new Actor(interfaz.getWidth(),interfaz.getHeight(),"bicho.gif");interfaz.agregarActor("jugador"+cantJugadores, jugador2);/*if(hiloM2.isAlive()){hiloM2.stop();};*/hiloM2=new HiloMovimiento(jugador2,interfaz);hiloM2.start();break;
		case 3:jugador3=new Actor(interfaz.getWidth(),interfaz.getHeight(),"bicho.gif");interfaz.agregarActor("jugador"+cantJugadores, jugador3);/*if(hiloM3.isAlive()){hiloM3.stop();};*/hiloM3=new HiloMovimiento(jugador3,interfaz);hiloM3.start();break;
		case 4:jugador4=new Actor(interfaz.getWidth(),interfaz.getHeight(),"bicho.gif");interfaz.agregarActor("jugador"+cantJugadores, jugador4);/*if(hiloM4.isAlive()){hiloM4.stop();};*/hiloM4=new HiloMovimiento(jugador4,interfaz);hiloM4.start();break;
		}
		HiloReceptor threadR=new HiloReceptor(pe,serv,cantJugadores);
		HiloEnvia threadE=new HiloEnvia(ip,ps,serv,cantJugadores);
		threadE.start();
		threadR.start();
		
		
	}
	
	
	public void moverJugador(int jug,int x,int y){
		switch(jug){
		case 1: hiloM1.setDireccion(x, y);break;
		case 2: hiloM2.setDireccion(x, y);break;
		case 3: hiloM3.setDireccion(x, y);break;
		case 4: hiloM4.setDireccion(x, y);break;
		}
	}
	
	public int getPosX(int num){
		if(num==1){
			return jugador1.getPosX();
		}
		else if(num==2){
			return jugador2.getPosX();
		}
		else if(num==3){
			return jugador3.getPosX();
		}
		else{
			return jugador4.getPosX();
		}
	}
	
	public int getPosY(int num){
		if(num==1){
			return jugador1.getPosY();
		}
		else if(num==2){
			return jugador2.getPosY();
		}
		else if(num==3){
			return jugador3.getPosY();
		}
		else{
			return jugador4.getPosY();
		}
	}

	public static void main(String[] args){
		
	}

}