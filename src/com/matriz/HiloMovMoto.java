package com.matriz;

/**
 * Clase que ejecuta un metodo de la clase ListaMoto de manera paralela a otros procesos.
 * @author Luis Alfredo Piedra Esquivel
 * @see Thread
 * @version 02/10/2016
 */
public class HiloMovMoto extends Thread {


	public ListaMoto moto;
	
	/**
	 * Constructor de la clase.
	 * @param m objeto de la clase ListaMoto sobre el cual trabajara el hilo.
	 */
	public HiloMovMoto(ListaMoto m){
		moto=m;
		
	}
	
	public void run(){
		while(true){
			moto.moverse();//Mueve una lista a travez de una matriz.
		}
	}
	

	
}
