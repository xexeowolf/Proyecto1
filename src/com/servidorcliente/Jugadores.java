package com.servidorcliente;

import com.matriz.ListaMoto;

/**
 * Clase que implementa una lista enlazada simple que almacena la informacion relacionada con cada jugador conectado al servidor.
 * Con la informacion almacenada se envia las actualizaciones realizadas por el servidor a los clientes.
 * Ademas se logra aplicar los cambios enviados por un cliente,al servidor, a su moto(ListaMoto) relacionado. 
 * @author Luis Alfredo Piedra Esquivel
 * @since 02/10/2016
 * @see NodoJugador,ListaMoto
 */
public class Jugadores {
	
	public NodoJugador head,tail;
	private int tam;
	
	/**
	 *Constructor de la clase. 
	 */
	public Jugadores() {
		head=tail=null;
		tam=0;
	}
	
	/**
	 * Metodo encargado de agregar un nodo a la lista.
	 * @param jugador moto(ListaMoto) relacionada a un cliente especifico
	 * @param ip cadena de texto que representa la direccion IP del usuario
	 * @param ps numero entero que representa el puerto por el cual recibe informacion el usuario
	 * @see NodoJugador
	 */
	public void agregar(ListaMoto jugador,String ip,int ps){
		NodoJugador nuevo=new NodoJugador(jugador,ip,ps);
		if(tam==0){
			head=tail=nuevo;
			tam++;
		}
		else{
			tail.next=nuevo;
			tail=nuevo;
			tam++;
		}
		
	}
	/**
	 * Metodo encargado de eliminar un nodo de la lista.
	 * @param ps puerto de salida relacionado con el nodo que se desea eliminar.
	 * @return numero entero que representa el ID del cliente que fue eliminado.
	 */
	public int eliminar(int ps){
		int num=0;
		if(head.getPuerto()==ps){
			num=head.getPlayer().ID;
			head=head.next;
		}
		else{
			NodoJugador tmp=head;
			while(tmp.next!=null){
				if(tmp.next.getPuerto()==ps){
					num=tmp.next.getPlayer().ID;
					if(tmp.next==tail){
						tail=tmp;
					}
					tmp.next=tmp.next.next;
					tam--;
				}	
			}
		}
		return num;
	}
	
	/**
	 * Metodo que localiza un nodo de la lista y retorna un referencia a dicho nodo.
	 * @param pto numero entero que representa el puerto de salida relacionado al cliente.
	 * @return la referencia a un nodo especifico de la lista.
	 */
	public NodoJugador buscar(int pto){
		NodoJugador tmp=head;
		do{
			if(tmp.getPuerto()==pto){
				return tmp;
			}
			tmp=tmp.next;
		}while(tmp!=null);
		return null;
	}
	
	/**
	 * Metodo que elimina el primer nodo de la lista
	 */
	public void eliminarAlInicio(){
		head=head.next;
	}
	
	/**
	 * Metodo que modifica el tamano de la lista.
	 * @param t numero entero que representa el nuevo tamano de la lista.
	 */
	public void setTam(int t){
		tam=t;
	}
	
	/**
	 * Metodo que devuelve el tamano actual de la lista
	 * @return numero entero que representa el numero de nodos que tiene la lista.
	 */
	public int getTam(){
		return tam;
	}

}
