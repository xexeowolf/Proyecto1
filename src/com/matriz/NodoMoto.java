package com.matriz;

/**
 * Clase tipo nodo con doble referencia, ademas de una puntero tipo NodoMatriz que lo enlaza a la matriz.
 * @author Luis Alfredo Piedra Esquivel
 * @since 02/10/2016
 * @see Pila,Cola
 */
public class NodoMoto {
	
	private int estela;
	private double combustible;
	private int velocidad;
	private String direccion;
	private Pila poderes;
	private Cola items;
	public NodoMatriz abajo;
	public NodoMoto sig;
	public NodoMoto ant;
	private int pasos;
	
	
	/**
	 * Constructor de la clase.
	 */
	public NodoMoto(){
		pasos=0;
		estela=3;
		velocidad=300;
		combustible=100.0;
		poderes=new Pila();
		items=new Cola();
		abajo=null;
		sig=null;
		ant=null;
		direccion="abj";
		
	}
	
	/**
	 * Constructor de la clase.
	 * @param g indica si lo que se desea crea es un nodo sin informacion adicional(estelas).
	 */
	public NodoMoto(boolean g){
		abajo=null;
		sig=null;
		ant=null;
	}

	/**
	 * Metodo que agrega un Item a la lista de poderes(Pila)
	 * @param itm objeto de la clase Item que se guardara en la pila.
	 * @see Pila
	 */
	public void agregarPoder(Item itm){
		poderes.add(itm);
	}
	/**
	 * Metodo que agrega un Item a la lista de items(Cola)
	 * @param itm objeto de la clase Item que se guardara en la cola.
	 * @see Cola
	 */
	public void agregarItem(Item itm){
		items.add(itm);
	}
	/**
	 * Metodo que retorna la lista de items(cola).
	 * @return cola que contiene los items relacionados con la ListaMoto.
	 * @see ListaMoto
	 */
	public Cola getItems(){
		return items;
	}
	/**
	 * Metodo que retorna la lista de poderes(pila).
	 * @return pila que contiene los poderes relacionados con la ListaMoto.
	 * @see ListaMoto
	 */
	public Pila getPoderes(){
		return poderes;
	}
	
	/**
	 * Metodo que modifica el parametro estela que indica el numero de estelas(nodo) una moto(ListaMoto).
	 * @param i numero entero que se le suma a la cantidad actual de estelas.
	 */
	public void setEstela(int i){
		estela=estela+i;
	}
	
	/**
	 * Metodo que reduce el atributo combustible de una moto(ListaMoto)
	 * @see ListaMoto
	 */
	public void reduceGas(){
		if(pasos==100){//cancela el efecto de hipervelocidad
			velocidad=300;
			pasos=0;
		}
		else{
			pasos++;
		}
		combustible=combustible-0.2;
	}
	
	/**
	 * Metodo que retorna el numero entero que representa la cantidad de combustible de la moto(ListaMoto)
	 * @return numero entero que representa el combustible.
	 */
	public double getGas(){
		return combustible;
	}
	
	/**
	 * Metodo que retorna el numero de estelas que contiene una moto(ListaMoto).
	 * @return numero entero representa el numero de estelas.
	 */
	public int getEstela(){
		return estela;
	}
	
	/**
	 * Metodo que modifica la velocidad del movimiento de la moto.
	 * @param v numero entro que representa la nueva velocidad de la moto.
	 */
	public void setVel(int v){
		velocidad=v;
	}
	/**
	 * Metodo que retorna el valor actual de velocidad de movimiento de la moto(ListaMoto).
	 * @return numero entero que representa la velocidad actual de la moto.
	 */
	public int getVel(){
		return velocidad;
	}
	/**
	 * Metodo que cambia la direccion de movimiento de la moto.
	 * @param nom texto que representa la nueva orientacion de la moto(ListaMoto) en la matriz e interfaz grafica.
	 */
	public void setDireccion(String nom){
		direccion=nom;
	}
	/**
	 * Metodo que devuelve la direccion actual de la moto.
	 * @return texto que representa la direccion de la moto.
	 */
	public String getDireccion(){
		return direccion;
	}
	/**
	 * Metodo que aumenta el atributo combustible de una moto(ListaMoto)
	 * @param g numero entero que se le suma al valor de combustible actual.
	 */
	public void aumGas(double g){
		combustible=combustible+g;
		if(combustible>100.0){
			combustible=100.0;
		}
	}
	
}
