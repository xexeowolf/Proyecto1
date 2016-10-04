package com.matriz;

/**
 * Clase que representa a un item, tiene efectos especificos sobre un objeto de la clase ListaMoto.
 * @author Luis Alfredo Piedra Esquivel
 * @see Item
 * @version 02/10/2016
 */
public class HiperVelocidad extends Item{
	/**
	 * Constructor de la clase que hereda de la clase Item
	 * @see Item
	 */
	public HiperVelocidad() {
		super("velocidad",100);//Guarda un valor entero que representa milisegundos.
	}
}
