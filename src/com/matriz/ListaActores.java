package com.matriz;

import java.io.Serializable;
/**
 * Clase que representa un lista enlazada doble que guarda la informacion de las imagenes que se representan en la interfaz grafica.
 * @author Luis Alfredo Piedra Esquivel
 * @see Serializable
 * @version 02/10/2016
 */
public class ListaActores implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private NodoActor head,tail; //punteros de tipo NodoActor
	private int tam;
	
	/**
	 *Constructor de la clase.
	 *Inicializa los punteros de tipo NodoActor en null.
	 * @see NodoActor  
	 */
	public ListaActores() {
		head=tail=null;
		tam=0;
	}
	
	
	
	/**
	 * Metodo que eliminar todos los nodos que contengan un nombre de imagen especifico.
	 * @param nom nombre de la imagen que se desea eliminar.
	 */
	public void eliminarMoto(String nom){
	if(tam>0){
		while(head!=null){
			if(head.getNomImagen()==nom){
				head=head.next;
				head.prev=null;
				tam--;
			}
			else{
				break;
			}
		}
		if(tam>1){
			NodoActor tmp=head.next;
			while(tmp!=null){
				if(tmp.getNomImagen()==nom){
					tmp=tmp.prev;
					tmp.next=tmp.next.next;
					if(tmp.next!=null){
						tmp.next.prev=tmp;	
					}
					tam--;
					eliminarMoto(nom); //Llamada recursiva encargada de repetir el proceso hasta que sea necesario.
				}
				else{
					tmp=tmp.next;
				}
			}
		}
	}
		}
	
	/**
	 * Metodo que agrega un nodo a la lista.
	 * @param direccion nombre de la imagen que estara asociada y sera representada graficamente.
	 * @param x	posicion en el eje x que tendra la imagen.
	 * @param y	posicion en el eje y que tendra la imagen.
	 * @param nombre nombre caracteristico de ciertos objetos para identificarlos en la matriz.Ejm: moto,item,estela...
	 */
	public void add(String direccion,int x,int y,String nombre){
		NodoActor nuevo= new NodoActor(direccion,x,y);
		nuevo.setNombre(nombre);
		if(tam==0){
			head=tail=nuevo;
			tam++;
		}
		else{
			tail.next=nuevo;
			nuevo.prev=tail;
			tail=nuevo;
			tam++;
		}
	}
	
	/**
	 * Metodo que agrega un nodo al principio de la lista.
	 * @param direccion nombre de la imagen que estara asociada y sera representada graficamente.
	 * @param x	posicion en el eje x que tendra la imagen.
	 * @param y	posicion en el eje y que tendra la imagen.
	 * @return la referencia al primer nodo de la lista.
	 */
	public NodoActor add(String direccion,int x,int y){
		NodoActor nuevo= new NodoActor(direccion,x,y);
		nuevo.setNombre("moto"); //Las motos siempren van al principio de la lista.
		nuevo.next=head;
		if(tam==0){
		tail=head=nuevo;}
		else{
			head.prev=nuevo;
			head=nuevo;}
		tam++;
		return head;
	}
	
	/**
	 * Metodo que elimina un nodo de la lista.
	 * @param x posicion en el eje x, unica para cada nodo.
	 * @param y posicion en el eje y, unica para cada nodo.
	 * @param nombre caracteristico que representa el tipo de imagen que contiene el nodo,necesario para asegurar eliminar solo un nodo.
	 */
	public void remove(int x,int y,String nombre){
	if(tam!=0){
		if(head.getPosX()==x && head.getPosY()==y && head.getNombre()==nombre){
			head=head.next;
			head.prev=null;
			tam--;
		}
		else{
			if(tam>1){
			NodoActor tmp=head;
			while(tmp.next!=null){
				if(tmp.next.getPosX()==x && tmp.next.getPosY()==y && tmp.next.getNombre()==nombre){
					if(tmp.next==tail){
						tail=tmp;
					}
					tmp.next=tmp.next.next;
					if(tmp.next!=null){
					tmp.next.prev=tmp;}
					tam--;
					break;
				}
				tmp=tmp.next;
			}
		}
	}
}
}
	
	/**
	 * Metodo que elmina uno o varios nodos asociados a una imagen en especifico.
	 * @param nombreImagen nombre que representa la imagen que se desea eliminar.
	 * @param x	posicion en el eje x, unica para cada nodo.
	 * @param y posicion en el eje y, unica para cada nodo.
	 */
	public void remove(String nombreImagen,int x,int y){
		if(tam!=0){
			if(head.getPosX()==x && head.getPosY()==y && head.getNomImagen()==nombreImagen){
				head=head.next;
				head.prev=null;
				tam--;
			}
			else{
				if(tam>1){
				NodoActor tmp=head;
				while(tmp.next!=null){
					if(tmp.next.getPosX()==x && tmp.next.getPosY()==y && tmp.next.getNomImagen()==nombreImagen){
						if(tmp.next==tail){
							tail=tmp;
						}
						tmp.next=tmp.next.next;
						if(tmp.next!=null){
						tmp.next.prev=tmp;}
						tam--;
						break;
					}
					tmp=tmp.next;
				}
			}
		}
	}
	}
	
	/**
	 * Metodo que retorna el numero de nodos que tiene la lista.
	 * @return el tamano de la lista.
	 */
	public int getTam(){
		return tam;
	}
	 /**
	 * Metodo que retorna la referencia al primero nodo de la lista.
	 * @return el puntero con la referencia al primer nodo de la lista.
	 */
	public NodoActor getHead(){
		 return head;
	 }
	 /**
	 * Metodo que modifica la referencia del primer nodo de la lista 
	 * @param pto puntero que reasigna a head.
	 */
	public void setHead(NodoActor pto){
		 head=pto;
	 }
}
