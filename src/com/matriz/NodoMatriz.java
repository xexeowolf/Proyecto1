package com.matriz;

/**
 * Clase que representa a un nodo con cuatro referencias, guarda ademas valores y objetos necesarios para la logica del juego.
 * @author Luis Alfredo Piedra Esquivel
 * @see Item
 * @version 02/10/2016
 */
public class NodoMatriz {
	protected NodoMatriz up;
	protected NodoMatriz down;
	protected NodoMatriz left;
	protected NodoMatriz right;
	private Item item;	
	private boolean ocupado;
	private String ocupante;
	private int posX;
	private int posY;
	
	/**
	 * Constructor de la clase.
	 * Inicializa las cuatro referencia en nulo.
	 */
	public NodoMatriz() {
		down=left=right=up=null;
		posX=posY=0;
		ocupado=false;
		
	}
	
	/**
	 * Metodo que asigna al nodo como ocupado o no, ademas de quien se encuentra en el.
	 * @param v valor boolean que define si el nodo esta ocupado(true) o no(false).
	 * @param nom nombre del ocupante.Ejm: moto,item,estela.....
	 */
	public void setEstado(boolean v,String nom){
		ocupado=v;
		ocupante=nom;
	}
	/**
	 * Metodo que retorna un nombre del objeto que este referenciando al nodo.
	 * @return el nombre guardado en el nodo como ocupante.
	 */
	public String getOcupante(){
		return ocupante;
	}
	/**
	 * Metodo que indica si un nodo esta siendo referenciado por algun objeto diferente de la clase NodoMatriz.
	 * @return un valor boolean que indica si esta ocupado(true) o no(false).
	 */
	public boolean getEstado(){
		return ocupado;
	}
	
	/**
	 * Metodo que asigna un objeto de la clase Item al nodo.
	 * @param ele objeto de la clase Item que se desea guardar.
	 */
	public void setItem(Item ele){
		item=ele;
	}
	/**
	 * Metodo que asigna un objeto de la subclase HiperVelocidad al nodo.
	 * @param ele objeto de la subclase HiperVelocidad que se desea guardar.
	 */
	public void setItem(HiperVelocidad ele){
		item=ele;
	}
	/**
	 * Metodo que asigna un objeto de la subclase Bomba al nodo.
	 * @param ele objeto de la subclase Bomba que se desea guardar.
	 */
	public void setItem(Bomba ele){
		item=ele;
	}
	/**
	 * Metodo que asigna un objeto de la subclase Gas al nodo.
	 * @param ele objeto de la subclase Gas que se desea guardar.
	 */
	public void setItem(Gas ele){
		item=ele;
	}
	/**
	 * Metodo que asigna un objeto de la subclase AumentaEstela al nodo.
	 * @param ele objeto de la subclase AumentaEstela que se desea guardar.
	 */
	public void setItem(AumentaEstela ele){
		item=ele;
	}
	/**
	 * Metodo que asigna un objeto de la subclase Escudo al nodo.
	 * @param ele objeto de la subclase Escudo que se desea guardar.
	 */
	public void setItem(Escudo ele){
		item=ele;
	}

	/**
	 * Metodo que retorna el objeto de la clase Item o alguna de sus subclases asociado al nodo.
	 * @return objeto de la clase Item o alguna de sus subclases.
	 */
	public Item getItem(){
		return item;
	}
	/**
	 * Metodo que retorna la posicion en eje x que representa el nodo.
	 * @return la posicion en el eje x.
	 */
	public int getPosX(){
		return posX;
	}
	/**
	 * Metodo que retorna la posicion en eje y que representa el nodo.
	 * @return la posicion en el eje y.
	 */
	public int getPosY(){
		return posY;
	}
	/**
	 * Metodo que modifica la posicion en el eje x que representa el nodo.
	 * @param x  numero entero que representa la posicion en el eje x.
	 */
	public void setPosX(int x){
		posX=x;
	}
	/**
	 * Metodo que modifica la posicion en el eje y que representa el nodo.
	 * @param y  numero entero que representa la posicion en el eje y.
	 */
	public void setPosY(int y){
		posY=y;
	}
	

}
