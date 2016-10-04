package com.matriz;

/**
 * Clase que representa a un item, tiene efectos especificos sobre un objeto de la clase ListaMoto.
 * @author Luis Alfredo Piedra Esquivel
 * @see Item
 * @version 02/10/2016
 */
public class Gas extends Item {

	/**
	 * Constructor de la clase que hereda de la clase Item
	 * @see Item
	 */
	public Gas() {
		super("combustible",generarNumero(100));//Guarda un numero aleatorio entre 1 y 100.
	}
}
