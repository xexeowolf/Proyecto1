package com.matriz;

public class MatrizDinamica {
	protected int filas;
	protected int columnas;
	protected ListasDobles current;
	
	public MatrizDinamica(){
		filas=columnas=0;
		current=null;
	}
	public void generarMatriz(int cantFilas, int cantCol){
		ListasDobles primero=new ListasDobles();
		primero.generarLista(cantCol);
		filas++;
		columnas=cantCol;
		current=primero;
		ListasDobles ref=current;
		while(filas<cantFilas){
			ListasDobles nuevo= new ListasDobles();
			nuevo.generarLista(cantCol);
			ListasDobles temp=nuevo;
			NodoMatriz recA=current.head;
			NodoMatriz recB=temp.head;
			while(recA!=null && recB!=null){
				recA.down=recB;
				recB.up=recA;
				recA=recA.right;
				recB=recB.right;
			}
			filas++;
			current=temp;
		}
		current=ref;
	}
}

