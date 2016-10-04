package com.matriz;

import java.lang.Math;
/**
 * Clase que guarda un nombre y un valor entero utilizados para definir atributos de otras clases.
 * Clase padre utilizada para desarrollar distintas clases con caracteristicas especificas.
 * @author Luis Alfredo Piedra Esquivel
 * @version 02/10/2016
 */
public class Item {
	
	private String nombre;
	private int valor;
	
	/**
	 * Constructor de la clase.
	 * @param Nombre variable de tipo String que guarda un nombre significativo para ciertos metodos de otras clases.
	 * @param numero entero utilizado para cambiar los atributos de objetos provenientes de clases como ListaMoto. 	
	 */
	public Item(String Nombre,int numero) {
		nombre=Nombre;
		valor=numero;
	}
	
	/**
	 * Constructor de la clase, utilizado cuando no es necesario utilizar el atributo valor.
	 * @param Nombre variable de tipo String que guarda un nombre significativo para ciertos metodos.
	 */
	public Item(String Nombre){
		nombre=Nombre;
		valor=-1;
	}
	
	/**
	 * Metodo que genera un numero aleatorio dentro de un rango definido.
	 * @param n numero entero que representa el maximo de un rango que va desde 1 hasta n.
	 * @return un numero dentro del rango que va desde 1 hasta n.
	 */
	public static int generarNumero(int n){
		int l =(int)(Math.random()*n)+1;
		return l;
	}
	
	/**
	 * Metodo que retorna el valor asociado al atributo nombre.
	 * @return el valor asociado a nombre.
	 */
	public String getNombre(){
		return nombre;
	}
	
	/**
	 * Metodo que retorna el valor asociado al atributo valor.
	 * @return el valor asociado a valor.
	 */
	public int getValor(){
		return valor;
	}

}
