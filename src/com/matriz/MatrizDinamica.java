package com.matriz;

import java.util.Random;


public class MatrizDinamica {
	private int filas;
	private int columnas;
	private NodoMatriz colocador; 
	private Fabrica factory;
	private NodoMatriz esqSD;
	private NodoMatriz esqSI;
	private NodoMatriz esqII;
	private NodoMatriz esqID;
	
	public MatrizDinamica(int f,int c){
		if(f!=0 && c!=0){
		generarMatriz(f,c);
		}else{
			filas=columnas=0;
			colocador=null;
		}
		factory=new Fabrica();
	}
	
	
	
	public NodoMatriz buscarLugar(){
		if(esqSD.getEstado()){
			return esqSD;
		}
		else if(esqSI.getEstado()){
			return esqSI;
		}
		else if(esqID.getEstado()){
			return esqID;
		}
		else if(esqII.getEstado()){
			return esqII;
		}
		else{
			return null;
		}
	}
	
	public void moverColocador(String direccion,int pasos){
		switch(direccion){
		case "der": while(pasos!=0 && colocador.right!=null){colocador=colocador.right;pasos--;};break;
		case "izq": while(pasos!=0 && colocador.left!=null){colocador=colocador.left;pasos--;};break;
		case "abj": while(pasos!=0 && colocador.down!=null){colocador=colocador.down;pasos--;};break;
		case "arr": while(pasos!=0 && colocador.up!=null){colocador=colocador.up;pasos--;};break;
		}
	}
	
	public String DireccionX(int x){
		if(x>0){
			return "der";
		}else if(x<0){
			return "izq";
		}else{
			return "cero";
		}
	}
	
	public String DireccionY(int y){
		if(y>0){
			return "arr";
		}else if(y<0){
			return "abj";
		}else{
			return "cero";
		}
		
	}
	
	public void colocarItems(int x,int y){
			moverColocador(DireccionX(x),Math.abs(x));
			moverColocador(DireccionY(y),Math.abs(y));
			if(colocador!=esqSD && colocador!=esqSI && colocador!=esqII && colocador!=esqID){
				Item elementos=factory.crearItem();
				if(colocador.getItem()==null){
					if(colocador.getEstado()==false){
						colocador.setItem(elementos);
						colocador.setEstado(true, "item");
					}
					
			}
		}
			
	}
	public NodoMatriz getColocador(){
		return colocador;
	}
	public void setColocador(NodoMatriz temp){
		colocador=temp;
	}
	
	public void generarMatriz(int cantFilas, int cantCol){
		ListasDobles primero=new ListasDobles();
		primero.generarLista(cantCol);
		filas++;
		columnas=cantCol;
		ListasDobles current=primero;
		colocador=current.head;
		esqSI=current.head;
		esqSD=current.tail;
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
		esqII=current.head;
		esqID=current.tail;
	}
	
	public void rellenarMatriz(){
		Random num=new Random();
		while(true){
			colocarItems(num.nextInt(2*filas-1)-(filas-1),num.nextInt(2*columnas-1)-(columnas-1));
		}
	}
	
	
	/*public void recorrer(){
		while(colocador.right!=null){
			if(colocador==esqSD){
				System.out.print("esqSD");
			}
			if(colocador==esqSI){
				System.out.print("esqSI");
			}
			System.out.print(colocador.getItem()+" derecha"+"\n");
			colocador=colocador.right;
		}
		while(colocador.down!=null){
			if(colocador==esqSD){
				System.out.print("esqSD");
			}
			if(colocador==esqID){
				System.out.print("esqID");
			}
			System.out.print(colocador.getItem()+" abajo"+"\n");
			colocador=colocador.down;
		}
		while(colocador.left!=null){
			if(colocador==esqID){
				System.out.print("esqID");
			}
			if(colocador==esqII){
				System.out.print("esqII");
			}
			System.out.print(colocador.getItem()+" izquierda"+"\n");
			colocador=colocador.left;
		}
		while(colocador.up!=null){
			if(colocador==esqII){
				System.out.print("esqII");
			}
			if(colocador==esqSI){
				System.out.print("esqSI");
			}
			System.out.print(colocador.getItem()+" arriba"+"\n");
			colocador=colocador.up;
		}
		
	}
	
	public static void main(String[] args){
		MatrizDinamica n= new MatrizDinamica(10,10);
		NodoMatriz x=n.getColocador();
		Random aleat=new Random();
		int i=20;
		while(i!=0){
			n.colocarItems(aleat.nextInt(19)-9,aleat.nextInt(19)-9);
			i--;
		}
		n.setColocador(x);
		n.recorrer();
		if(n.getColocador()==null){
			System.out.print("nulo");
		}else{
		System.out.print("no nulo");
		}
	}*/

}

