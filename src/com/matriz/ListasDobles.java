package com.matriz;

public class ListasDobles {
	protected NodoMatriz head;
	protected NodoMatriz tail;

	public ListasDobles() {
		head=tail=null;
	}
	public void generarLista(int cantElem){
		NodoMatriz primer= new NodoMatriz();
		head=tail=primer;
		if (cantElem>=2){
			NodoMatriz segundo=new NodoMatriz();
			segundo.left=head;
			head.right=segundo;
			tail=segundo;
			int i;
			for(i=2;i<cantElem;i++){
				NodoMatriz nuevo=new NodoMatriz();
				nuevo.left=tail;
				tail.right=nuevo;
				tail=nuevo;
			}
		}
	}
	
}
