package com.matriz;


/**
 * Clase de tipo de nodo que guarda objetos de tipo Item para su posterior procesamiento.
 * @author Luis Alfredo Piedra Esquivel
 * @see Item
 * @version 02/10/2016
 */
public class NodoItem{
	private Item item;
	protected NodoItem next;
	
	/**
	 * Constructor de la clase.
	 * Guarda una instancia de tipo Item.
	 * Inicializa el puntero al siguiente nodo en null.
	 * @param itm objeto que se desea guardar en el nodo.
	 */
	public NodoItem(Item itm){
		next=null;
		item=itm;
	}
	
	/**
	 * Metodo que retorna el objeto Item asociado al nodo.
	 * @return un objeto de la clase Item o sus subclases.
	 */
	public Item getItem(){
		return item;
	}

}
