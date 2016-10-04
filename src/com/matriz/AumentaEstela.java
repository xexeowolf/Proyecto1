package com.matriz;

/**
 * Clase que representa a un item, que tendra efectos efectos especificos sobre un objeto de tipo ListaMoto.
 * Guarda un nombre representativo y un numero entero aleatorio que esta dentro de un rango definido.
 * @author Luis Alfredo Piedra Esquivel
 * @see Item
 * @version 02/10/2016
 *
 */
public class AumentaEstela extends Item {

	/**
	 * Constructor de la clase que hereda de la clase Item.
	 */
	public AumentaEstela() {
		super("aumestela",generarNumero(10));//Se genera un nuemero aleatorio de 1 a 10
	}
}
