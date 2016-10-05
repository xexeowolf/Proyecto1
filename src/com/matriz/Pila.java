package com.matriz;

/**
 * Clase que implementa un estructura de datos tipo Pila.
 * @author Luis Alfredo Piedra Esquivel
 * @since 02/10/2016
 * @see NodoItem
 */
public class Pila {
	protected NodoItem top;
	private int tam;
	
	/**
	 * Constructor de la clase.
	 */
	public Pila() {
		top=null;
		tam=0;
	}
	/**
	 * Metodo que agrega un nuevo nodo a la pila.
	 * @param itm objeto de tipo Item que sera guardado dentro del nodo.
	 */
	public void add(Item itm){
		NodoItem nuevo= new NodoItem(itm);
		if(top==null){
			top=nuevo;
		}else{
			nuevo.next=top;
			top=nuevo;
		}
		tam++;
	}
	/**
	 * Metodo que elimina el ultimo nodo agregado a la pila.
	 * @return el objeto de tipo Item que estaba almacenado en el nodo que fue eliminado.
	 */
	public Item remove(){
		if(top!=null){
			Item itm=top.getItem();
			top=top.next;
			tam--;
			return itm;
		}else{
			return null;
		}
	}
	
	/**
	 * Metodo ubica un nodo con informacion especifica al tope de la pila.
	 * @param nom texto que se relaciona con el Item que se busca y que esta guardado en los nodos.
	 */
	public void ubicar(String nom){
		if(tam>0){
			if(top.getItem().getNombre()!=nom){
				NodoItem tempant=top;
				NodoItem temp=top.next;
				while(temp!=null){
					if(temp.getItem().getNombre()==nom){
						tempant.next=temp.next;
						temp.next=top;
						top=temp;
						break;
					}
					temp=temp.next;
					tempant=tempant.next;
				}
			}
		}
	}
	
	/**
	 * Metodo que encuentro cuantos elementos en la lista tienen las mismas caracteristas.
	 * @param nom texto que representa la caracteristica en comun que se desea identificar.
	 * @return numero entero que representa la cantidad de elementos encontrados.
	 */
	public int inventario(String nom){
		int i=0;
		NodoItem tmp=top;
		while(tmp!=null){
			if(tmp.getItem().getNombre()==nom){
				i++;
			}
			tmp=tmp.next;
		}
		return i;
	}

	/**
	 * Metodo que devuelve el tamano actual de la lista.
	 * @return numero entero que representa la cantidad de nodos de la pila.
	 */
	public int getTam(){
		return tam;
	}
}
