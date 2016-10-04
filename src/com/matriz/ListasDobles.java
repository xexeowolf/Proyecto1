package com.matriz;

/**
 * Clase que implementa listas doblemente enlazadas y sus operaciones basicas.
 * @author Luis Alfredo Piedra Esquivel
 * @version 02/10/2016
 * @see NodoMatriz
 */
public class ListasDobles {
	
	protected NodoMatriz head;//puntero de tipo NodoMatriz
	protected NodoMatriz tail;//puntero de tipo NodoMatriz
	private int x,y;
	
	/**
	 * Constructor de la clase.
	 * Inicializa los punteros en nulo y el tamano en 0.
	 * @param X posicion en el eje x que tendra un nodo, necesario para representarlo en la interfaz grafica.
	 * @param Y posicion en el eje y que tendra un nodo, necesario para representarlo en la interfaz grafica.
	 */
	public ListasDobles(int X,int Y) {
		head=tail=null;
		x=X;
		y=Y;		
	}
	
	/**
	 * Metodo que genera una lista de cierta cantidad de nodos.
	 * @param cantElem numero entero que representa la cantidad de nodos que tendra la lista.
	 */
	public void generarLista(int cantElem){
		NodoMatriz primer= new NodoMatriz();//Se genera el primer nodo de la lista
		primer.setPosX(x);
		primer.setPosY(y);
		x=x+16;								//Se le suman 16 debido al tamano de las imagenes utilizadas que son de 16x16 pixeles
		head=tail=primer;
		if (cantElem>=2){	//Si se desea una lista con mas de un elementos
			NodoMatriz segundo=new NodoMatriz();//se inicializa el segundo elemento.
			segundo.setPosX(x);
			segundo.setPosY(y);
			x=x+16;
			segundo.left=head;
			head.right=segundo;
			tail=segundo;
			int i;
			for(i=2;i<cantElem;i++){//Se genera el resto de los nodos
				NodoMatriz nuevo=new NodoMatriz();
				nuevo.setPosX(x);
				nuevo.setPosY(y);
				nuevo.left=tail;
				tail.right=nuevo;
				tail=nuevo;
				x=x+16;
			}
		}
	}
}
