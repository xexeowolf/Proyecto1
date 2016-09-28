package com.servidorcliente;

import com.matriz.ListaMoto;

public class NodoJugador {

	private ListaMoto jugador;
	private int puertosalida;
	private String IP;
	public NodoJugador next;
	
	public NodoJugador(ListaMoto moto,String ip,int ps) {
		jugador=moto;
		IP=ip;
		puertosalida=ps;
	}
	
	public ListaMoto getPlayer(){
		return jugador;
	}
	public void setPlayer(ListaMoto jug){
		jugador=jug;
	}
	public int getPuerto(){
		return puertosalida;
	}
	public void setPuerto(int p){
		puertosalida=p;
	}
	public String getIP(){
		return IP;
	}
	public void setIP(String ip){
		IP=ip;
	}
}
