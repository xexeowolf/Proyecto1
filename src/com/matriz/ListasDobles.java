package com.matriz;

public class ListasDobles {
	protected NodoMatriz head;
	protected NodoMatriz tail;
	private int x,y;
	
	public ListasDobles(int X,int Y) {
		head=tail=null;
		x=X;
		y=Y;		
	}
	public void generarLista(int cantElem){
		NodoMatriz primer= new NodoMatriz();
		primer.setPosX(x);
		primer.setPosY(y);
		x=x+32;
		head=tail=primer;
		if (cantElem>=2){
			NodoMatriz segundo=new NodoMatriz();
			segundo.setPosX(x);
			segundo.setPosY(y);
			x=x+32;
			segundo.left=head;
			head.right=segundo;
			tail=segundo;
			int i;
			for(i=2;i<cantElem;i++){
				NodoMatriz nuevo=new NodoMatriz();
				nuevo.setPosX(x);
				nuevo.setPosY(y);
				nuevo.left=tail;
				tail.right=nuevo;
				tail=nuevo;
				x=x+32;
			}
		}
	}
}
