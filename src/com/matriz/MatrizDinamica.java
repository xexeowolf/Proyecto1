package com.matriz;

import java.util.Random;

public class MatrizDinamica {
	private int filas;
	private int columnas;
	private NodoMatriz colocador; 
	private NodoMatriz distribuir;
	private Fabrica factory;
	private NodoMatriz esqSD;
	public NodoMatriz esqSI;
	private NodoMatriz esqII;
	private NodoMatriz esqID;
	private ListaActores actores;
	
	
	
	public MatrizDinamica(int f,int c){
		if(f!=0 && c!=0){
		generarMatriz(f,c);
		}else{
			filas=columnas=0;
			colocador=null;
			distribuir=null;
		}
		factory=new Fabrica();
		actores=new ListaActores();
	}
	
	
//Metodos sobre la lista de imagenes que muestra la interfaz.--------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	public ListaActores getListaActores(){
		return actores;
	}
	public void setListaActores(ListaActores act){
		actores=act;
	}
	public NodoActor agregarActor(String nombre,int x,int y){
		return actores.add(nombre, x, y);
	}
	public void quitarMoto(String nom){
		actores.eliminarMoto(nom);
	}
	public void quitarActor(NodoActor tmp){
		actores.remove(tmp.getPosX(), tmp.getPosY(),tmp.getNombre());
	}
	public void quitarActor(int x,int y,String nombre){
		actores.remove(x,y,nombre);
	}
	public void quitarActor(String nombreImagen,int x,int y){
		actores.remove(nombreImagen,x,y);
	}
//------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
//Metodo para buscar posiciones libre donde colocar la moto.
	public NodoMatriz buscarLugar(int ID){
		if(ID==1){
			return esqSI;
		}
		else if(ID==2){
			return esqSD;
		}
		else if(ID==3){
			return esqII;
		}
		else{
			return esqID;
		}
	}
//------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
//Metodos para mover los punteros que colocan items en los nodos de la matriz
	public void moverColocador(String direccion,int pasos){
		switch(direccion){
		case "der": while(pasos!=0 && colocador.right!=null){colocador=colocador.right;pasos--;};break;
		case "izq": while(pasos!=0 && colocador.left!=null){colocador=colocador.left;pasos--;};break;
		case "abj": while(pasos!=0 && colocador.down!=null){colocador=colocador.down;pasos--;};break;
		case "arr": while(pasos!=0 && colocador.up!=null){colocador=colocador.up;pasos--;};break;
		}
	}
	
	public void moverDistribuir(String direccion,int pasos){
		switch(direccion){
		case "der": while(pasos!=0 && distribuir.right!=null){distribuir=distribuir.right;pasos--;};break;
		case "izq": while(pasos!=0 && distribuir.left!=null){distribuir=distribuir.left;pasos--;};break;
		case "abj": while(pasos!=0 && distribuir.down!=null){distribuir=distribuir.down;pasos--;};break;
		case "arr": while(pasos!=0 && distribuir.up!=null){distribuir=distribuir.up;pasos--;};break;
		}
	}
	//------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	//Metodos para definir la direccion en que se van a mover los punteros que colocan items.
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
		if(y<0){
			return "arr";
		}else if(y>0){
			return "abj";
		}else{
			return "cero";
		}
		
	}
//------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
//Metodos que colocan items en una posicion 'x' y 'y' definidas de manera aleatoria.
	public void colocarItems(int x,int y){
			moverColocador(DireccionX(x),Math.abs(x));
			moverColocador(DireccionY(y),Math.abs(y));
			if(colocador!=esqSD && colocador!=esqSI && colocador!=esqII && colocador!=esqID){
				Item elementos=factory.crearItem();
				if(colocador.getItem()==null){
					if(colocador.getEstado()==false){
						switch(elementos.getNombre()){
						case "combustible": actores.add("gas.gif", colocador.getPosX(), colocador.getPosY(),"item");break;
						case "aumestela": actores.add("agregarEstela.gif", colocador.getPosX(), colocador.getPosY(),"item");break;
						case "velocidad": actores.add("nitro.gif", colocador.getPosX(), colocador.getPosY(),"item");break;
						case "bomba":actores.add("bomba.gif", colocador.getPosX(), colocador.getPosY(),"item");break;
						case "escudo":actores.add("escudo.gif", colocador.getPosX(), colocador.getPosY(),"item");break;
						}
						colocador.setItem(elementos);
						colocador.setEstado(true, "item");
					}
			}
		}
			
	}
	
	public void distribuirItem(int x,int y,Item itm){
		moverDistribuir(DireccionX(x),Math.abs(x));
		moverDistribuir(DireccionY(y),Math.abs(y));
		if(distribuir!=esqSD && distribuir!=esqSI && distribuir!=esqII && distribuir!=esqID){
			if(distribuir.getItem()==null){
				if(distribuir.getEstado()==false){
					switch(itm.getNombre()){
					case "combustible": actores.add("gas.gif", distribuir.getPosX(), distribuir.getPosY(),"item");break;
					case "aumestela": actores.add("agregarEstela.gif", distribuir.getPosX(), distribuir.getPosY(),"item");break;
					case "velocidad": actores.add("nitro.gif", distribuir.getPosX(), distribuir.getPosY(),"item");break;
					case "bomba":actores.add("bomba.gif", distribuir.getPosX(), distribuir.getPosY(),"item");break;
					case "escudo":actores.add("escudo.gif", distribuir.getPosX(), distribuir.getPosY(),"item");break;
					}
					distribuir.setItem(itm);
					distribuir.setEstado(true, "item");
				}
				else{
					Random num=new Random();
					int [] un={-1,1};
					distribuirItem((num.nextInt(filas - 2) % 3 + 2) * un[num.nextInt(2)] ,(num.nextInt(columnas - 2) % 3 + 2)* un[num.nextInt(2)],itm);
				}
				
		}
	}
		
}
//------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
//Metodo para obtener y posicionar el puntero que coloca items de manera aleatoria en la matriz		
	public NodoMatriz getColocador(){
		return colocador;
	}
	public void setColocador(NodoMatriz temp){
		colocador=temp;
	}
//------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
//Metodo que genera una matriz de una cantidad definida de filas y colas, inicializa todos los punteros necesarios.
	public void generarMatriz(int cantFilas, int cantCol){
		ListasDobles primero=new ListasDobles(0,0);
		primero.generarLista(cantCol);
		filas++;
		columnas=cantCol;
		ListasDobles current=primero;
		colocador=current.head;
		distribuir=current.head;
		esqSI=current.head;
		esqSD=current.tail;
		while(filas<cantFilas){
			ListasDobles nuevo= new ListasDobles(0,filas*16);
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
//------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
//Metodo que distribuye los items de una moto en el mapa en caso de ser destruida.	
	public void distribMatriz(Cola items,Pila poderes){
		int [] un={-1,1};
		Random num=new Random();
		while(items.getTam()>0){
			Item obj=items.remove();
			distribuirItem((num.nextInt(filas - 2)  + 2) * un[num.nextInt(2)] ,(num.nextInt(columnas - 2)  + 2)* un[num.nextInt(2)],obj);
		}
		while(poderes.getTam()>0){
			Item pod=poderes.remove();
			distribuirItem((num.nextInt(filas - 2) + 2) * un[num.nextInt(2)] ,(num.nextInt(columnas - 2)  + 2)* un[num.nextInt(2)],pod);
		}
		
	}
//------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------	
//Metodo infinito que coloca items en la matriz.
	public void rellenarMatriz(){
		int [] un={-1,1};
		Random num=new Random();
		while(true){
			colocarItems((num.nextInt(filas - 2) + 2) * un[num.nextInt(2)] ,(num.nextInt(columnas - 2) + 2)* un[num.nextInt(2)]);
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				System.out.print("Error en el colocador automatico\n");
				e.printStackTrace();
			}
		}
	}
//------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	
	
	public static void main(String[] args){
		/*MatrizDinamica m=new MatrizDinamica(20,20);
		NodoMatriz k=m.buscarLugar();
		if(k==null){
			System.out.print("Mierda");
		}else{
			System.out.print("Exito");
		}
		//HiloMovimiento j=new HiloMovimiento(m);
		//HiloA h= new HiloA(m);
		//h.start();
		//j.start();
		//Ventana v=new Ventana();*/
	
		
	}
}


