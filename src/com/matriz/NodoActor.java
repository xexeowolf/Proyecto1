package com.matriz;

import java.io.Serializable;

/**
 * Clase de tipo de nodo que almacena la informacion necesaria para representar imagenes en una interfaz grafica.
 * @author Luis Alfredo Piedra Esquivel
 * @see Serializable
 * @version 02/10/2016
 */

public class NodoActor implements Serializable{

	
	private static final long serialVersionUID = 1L;
	private int posX;
	private int posY;
	public NodoActor next,prev,extra;
	private String nombre;
	private String nomImagen;
	
	
	
	/**
	 * Constructor de la clase.
	 * Inicializa todos los punteros en null.
	 * @param direccion nombre de la imagen que se desea cargar en la interfaz.
	 * @param x	posicion en el eje x en el cual se colocara la imagen representada.
	 * @param y posicion en el eje y en el cual se colocara la imagen representada.
	 */
	public NodoActor(String direccion,int x,int y) {
		posX=x;
		posY=y;
		next=prev=extra=null;
		nomImagen=direccion;
	}
	
	/**
	 * Metodo que retorna el nombre de la imagen que se almaceno.
	 * @return nombre de la imagen asociado.
	 */
	public String getNomImagen(){
		return nomImagen;
	}
	
	/**
	 * Metodo que cambia la imagen que se desea representar.
	 * @param k nombre de la nueva imagen que se representara.
	 */
	public void setNombreImagen(String k){
		nomImagen=k;
	}
	
	/**
	 * Metodo que retorna la posicion  en el eje x de la imagen.
	 * @return la posicion en el eje x.
	 */
	public int getPosX(){
		return posX;
	}
	/**
	 * Metodo que retorna la posicion en el eje y de la imagen.
	 * @return la posicion en el eje y.
	 */
	public int getPosY(){
		return posY;
	}
	/**
	 * Metodo que cambia la posicion en el eje x de imagen.
	 * @param x nueva posicion en el eje x.
	 */
	public void setPosX(int x){
		posX=x;
	}
	/**
	 * Metodo que cambia la posicion en el eje y de imagen.
	 * @param y nueva posicion en el eje y.
	 */
	public void setPosY(int y){
		posY=y;
	}
	
	/**
	 * Metodo que retorna el nombre clave asociado a una imagen.
	 * @return nombre caracteristico de un tipo de imagen.
	 */
	public String getNombre(){
		return nombre;
	}
	
	/**
	 * Metodo que cambia el nombre clave de una imagen.
	 * @param k nombre clave que tendra asociada la imagen.
	 */
	public void setNombre(String k){
		nombre=k;
	}
	

}
