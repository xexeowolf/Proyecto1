package com.servidorcliente;

import com.matriz.ListaMoto;
/**
 * Clase de tipo de nodo, almacena una moto(ListaMoto),una direccion ip y puerto necesario para la conexion mediante sockets.
 * @author Luis Alfredo Piedra Esquivel
 * @since 02/10/2016
 * @see ListaMoto
 */
public class NodoJugador {

	private ListaMoto jugador;
	private int puertosalida;
	private String IP;
	public NodoJugador next;
	
	/**
	 * Constructor de la clase
	 * @param moto objeto de tipo ListaMoto, sobre el cual se realizaran cambios.
	 * @param ip texto que representa la direccion IP de un cliente.
	 * @param ps numero entero que representa el puerto por el cual escucha el cliente
	 */
	public NodoJugador(ListaMoto moto,String ip,int ps) {
		jugador=moto;
		IP=ip;
		puertosalida=ps;
	}
	
	/**
	 * Metodo que retorna la moto(ListaMoto) relacionada a un cliente
	 * @return un objeto de la clase ListaMoto
	 */
	public ListaMoto getPlayer(){
		return jugador;
	}
	/**
	 * Metodo que asigna a una moto(ListaMoto) a un cliente(NodoJugador).
	 * @param jug objeto de la clase ListaMoto el cual se va a asignar.
	 */
	public void setPlayer(ListaMoto jug){
		jugador=jug;
	}
	/**
	 * Metodo que retorna el puerto relacionado con un cliente.
	 * @return numero entero que representa el puerto de salida del servidor.
	 */
	public int getPuerto(){
		return puertosalida;
	}
	/**
	 * Metodo que cambia el puerto de salida relacionado con un cliente.
	 * @param p  numero entero que representa el nuevo puerto de salida.
	 */
	public void setPuerto(int p){
		puertosalida=p;
	}
	/**
	 * Metodo que retorna la direccion IP relacionada con un cliente
	 * @return cadena de texto que contiene la direccion IP
	 */
	public String getIP(){
		return IP;
	}
	/**
	 * Metodo que cambia la direccion IP relacionada con un cliente.
	 * @param ip cadena de texto que contiene la nueva direccion IP.
	 */
	public void setIP(String ip){
		IP=ip;
	}
}
