package com.matriz;

/**
 * Clase que implementa una lista enlazada simple, que funciona como una cola.
 * @author Luis Alfredo Piedra Esquivel
 * @see NodoItem
 * @version 02/10/2016
 */
public class Cola {
	
	protected NodoItem head,tail; //punteros de tipo NodoItem
	private int tam;

	/**
	 * Constructor de la clase, inicializa los punteros en null y su tamano en 0.
	 */
	public Cola() {
		head=tail=null;
		tam=0;
	
	}
	/**
	 * Metodo que agrega un nodo a la lista.
	 * Cada nodo contiene un objeto de la clase item o alguna de sus subclases.
	 * @param itm objeto de la clase Item que se guarda en un nodo.
	 * @see Item
	 */
	public void add(Item itm){
		NodoItem nuevo= new NodoItem(itm);
			if(tam==0){
				head=tail=nuevo;
				tam++;
			}else{	
				tail.next=nuevo;	
				tail=nuevo;
				tam++;
			}
		}
	//}
	
	/**
	 * Metodo que devuelve el item asociado al primer nodo de la lista que posteriormente seria eliminado.
	 * @return el item asociado al primer nodo de la lista.
	 */
	public Item getItem(){
		return head.getItem();
	}
	
	/**
	 * Metodo que remueve el primer nodo de la lista.
	 * Si la lista esta vacio se retorna NULO.
	 * @return el item asociado al primer nodo de la lista o null.
	 */
	public Item remove(){
		if(tam!=0){
			Item itm=head.getItem();
			head=head.next;
			tam--;
			return itm;
			}
		else{
			return null;
		}
	}
	
	/**
	 * Metodo que retorna el tamano actual de la lista.
	 * @return un numero entero que representa la cantidad de nodos que tiene la lista
	 */
	public int getTam(){
		return tam;
	}
	
	
}
