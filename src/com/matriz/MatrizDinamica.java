package com.matriz;

import java.util.Random;
/**
 * Clase que representa una matriz, formada por nodos con cuatros referencias enlazados entre si.
 * @author Luis Alfredo Piedra Esquivel
 * @since 02/10/2016
 * @see NodoMatriz
 */

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
	
	
	
	/**
	 * Constructor de la clase.
	 * @param f numero filas que tendra la matriz
	 * @param c numero de columnas que tendra la matriz.
	 */
	public MatrizDinamica(int f,int c){
		if(f!=0 && c!=0){
		generarMatriz(f,c);
		}else{
			filas=columnas=0;
			colocador=null;
			distribuir=null;
		}
		factory=new Fabrica(); //Se inicializa la fabrica encargada de generar los Items aleatoriamente.
		actores=new ListaActores();//Se inicializa la lista de imagenes que representara los elementos de la matriz.
	}
	
	/**
	 * Metodo que retorna la lista de imagenes(ListaActores) relacionada con los nodos de la matriz
	 * @return lista de imagenes(ListaActores)
	 */
	public ListaActores getListaActores(){
		return actores;
	}
	
	/**
	 * Metodo que actualiza la lista de imagenes relacionada con los nodos de la matriz.
	 * @param act lista de imagenes(ListaActores) actualizada.
	 */
	public void setListaActores(ListaActores act){
		actores=act;
	}
	
	/**
	 * Metodo que agrega una imagen(Actor) con su respectiva posicion "x" y "y".
	 * @param nombre nombre de la imagen que se desea representar
	 * @param x	posicion en el eje x en la cual se va a dibujar la imagen
	 * @param y posicion en el eje x en la cual se va a dibujar la imagen
	 * @return la referencia al nodo que se acaba de agregar a la lista de imagenes.
	 */
	public NodoActor agregarActor(String nombre,int x,int y){
		return actores.add(nombre, x, y);
	}
	
	/**
	 * Metodo que elimina una moto(ListaMoto) de la matriz.
	 * @param nom nombre de la imagen que se desea eliminar.
	 */
	public void quitarMoto(String nom){
		actores.eliminarMoto(nom);
	}
	
	/**
	 * Metodo que elimina una imagen de la lista de imagenes de la matriz(ListaActores)
	 * @param tmp puntero del nodo que se desea eliminar.
	 */
	public void quitarActor(NodoActor tmp){
		actores.remove(tmp.getPosX(), tmp.getPosY(),tmp.getNombre());
	}
	
	/**
	 * Metodo que elimina una imagen de la lista de imagenes de la matriz(ListaActores)
	 * @param x posicion en el eje x de la imagen a eliminar.
	 * @param y posicion en el eje y de la imagen a eliminar.
	 * @param nombre sobrenombre asociado a una imagen.
	 */
	public void quitarActor(int x,int y,String nombre){
		actores.remove(x,y,nombre);
	}
	/**
	 * Metodo que elimina una imagen de la lista de imagenes de la matriz(ListaActores)
	 * @param nombreImagen nombre de la imagen que se desea eliminar.
	 * @param x posicion en el eje x de la imagen a eliminar.
	 * @param y posicion en el eje y de la imagen a eliminar.
	 */
	public void quitarActor(String nombreImagen,int x,int y){
		actores.remove(nombreImagen,x,y);
	}

	/**
	 * Metodo que retorna la direccion a una esquina de la matriz dependiendo del numero de jugador que desea ingresar.
	 * @param ID numero de jugador que desea enlazarse a la matriz.
	 * @return puntero a algun nodo ubicado en alguna esquina de la matriz.
	 */
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

	/**
	 * Metodo que posiciona un puntero encargado de colocar elementos en la matriz de manera aleatoria.
	 * @param direccion cadena de texto que representa la direccion a la cual se movera el puntero dentro de la matriz
	 * @param pasos numero de nodos que avanzar el puntero en cierta direccion
	 */
	public void moverColocador(String direccion,int pasos){
		switch(direccion){
		case "der": while(pasos!=0 && colocador.right!=null){colocador=colocador.right;pasos--;};break;
		case "izq": while(pasos!=0 && colocador.left!=null){colocador=colocador.left;pasos--;};break;
		case "abj": while(pasos!=0 && colocador.down!=null){colocador=colocador.down;pasos--;};break;
		case "arr": while(pasos!=0 && colocador.up!=null){colocador=colocador.up;pasos--;};break;
		}
	}
	/**
	 * Metodo que posiciona un puntero encargado de distribuir elementos en la matriz de manera aleatoria.
	 * @param direccion cadena de texto que representa la direccion a la cual se movera el puntero dentro de la matriz
	 * @param pasos numero de nodos que avanzar el puntero en cierta direccion
	 */
	public void moverDistribuir(String direccion,int pasos){
		switch(direccion){
		case "der": while(pasos!=0 && distribuir.right!=null){distribuir=distribuir.right;pasos--;};break;
		case "izq": while(pasos!=0 && distribuir.left!=null){distribuir=distribuir.left;pasos--;};break;
		case "abj": while(pasos!=0 && distribuir.down!=null){distribuir=distribuir.down;pasos--;};break;
		case "arr": while(pasos!=0 && distribuir.up!=null){distribuir=distribuir.up;pasos--;};break;
		}
	}
	
	/**
	 * Metodo que devuelve una cadena de texto que representa una direccion.
	 * @param x numero entero que define la direccion.
	 * @return texto que representa una direccion
	 */
	public String DireccionX(int x){
		if(x>0){
			return "der";
		}else if(x<0){
			return "izq";
		}else{
			return "cero";
		}
	}
	
	/**
	 * Metodo que devuelve una cadena de texto que representa una direccion.
	 * @param y numero entero que define la direccion.
	 * @return texto que representa una direccion
	 */
	public String DireccionY(int y){
		if(y<0){
			return "arr";
		}else if(y>0){
			return "abj";
		}else{
			return "cero";
		}
		
	}
	
	/**
	 * Metodo que coloca de manera aleatoria objetos de la clase Item en los nodos de la matriz
	 * @param x numero que representa el numero de pasos que se movera el puntero en el eje x.
	 * @param y numero que representa el numero de pasos que se movera el puntero en el eje y.
	 */
	public void colocarItems(int x,int y){
			moverColocador(DireccionX(x),Math.abs(x));//Numero aleatorio
			moverColocador(DireccionY(y),Math.abs(y));//Numero aleatorio
			if(colocador!=esqSD && colocador!=esqSI && colocador!=esqII && colocador!=esqID){//Se evitan las esquinas debido a que son zonas donde se colocan los jugadores
				Item elementos=factory.crearItem();//se genera un objeto de manera aleatoria
				if(colocador.getItem()==null){
					if(colocador.getEstado()==false){
						switch(elementos.getNombre()){//Se carga la imagen respectiva al objeto colocado
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
	
	/**
	 * Metodo que distribuye los items de una moto al destruirse en la matriz
	 * @param x numero que representa el numero de pasos que se movera el puntero en el eje x.
	 * @param y numero que representa el numero de pasos que se movera el puntero en el eje y.
	 * @param itm objeto de la clase Item que se colocara en algun nodo de la matriz
	 */
	public void distribuirItem(int x,int y,Item itm){
		moverDistribuir(DireccionX(x),Math.abs(x));//Numero aleatorio
		moverDistribuir(DireccionY(y),Math.abs(y));//Numero aleatorio
		if(distribuir!=esqSD && distribuir!=esqSI && distribuir!=esqII && distribuir!=esqID){//Se evitan las esquinas
			if(distribuir.getItem()==null){
				if(distribuir.getEstado()==false){
					switch(itm.getNombre()){//Se carga la imagen relacionada al objeto colocado
					case "combustible": actores.add("gas.gif", distribuir.getPosX(), distribuir.getPosY(),"item");break;
					case "aumestela": actores.add("agregarEstela.gif", distribuir.getPosX(), distribuir.getPosY(),"item");break;
					case "velocidad": actores.add("nitro.gif", distribuir.getPosX(), distribuir.getPosY(),"item");break;
					case "bomba":actores.add("bomba.gif", distribuir.getPosX(), distribuir.getPosY(),"item");break;
					case "escudo":actores.add("escudo.gif", distribuir.getPosX(), distribuir.getPosY(),"item");break;
					}
					distribuir.setItem(itm);
					distribuir.setEstado(true, "item");
				}
				else{//Llamada recursiva en caso de que no se logre colocar el item
					Random num=new Random();
					int [] un={-1,1};
					distribuirItem((num.nextInt(filas - 2) % 3 + 2) * un[num.nextInt(2)] ,(num.nextInt(columnas - 2) % 3 + 2)* un[num.nextInt(2)],itm);
				}
				
		}
	}
		
}
	
	/**
	 * Metodo que retorna el puntero colocador cuya posicion es aleatoria
	 * @return referencia al puntero colocador.
	 */
	public NodoMatriz getColocador(){
		return colocador;
	}
	
	/**
	 * Metodo que indica al puntero colocador a cual nodo de la matriz referenciar.
	 * @param temp nueva referencia del puntero colocador.
	 */
	public void setColocador(NodoMatriz temp){
		colocador=temp;
	}

	/**
	 * Metodo que genera una matriz de mxn d
	 * @param cantFilas numero de filas que tendra la matriz
	 * @param cantCol numero de columnas que tendra la matriz
	 */
	public void generarMatriz(int cantFilas, int cantCol){
		ListasDobles primero=new ListasDobles(0,0);//Se genera la primera fila con la cantidad de columnas(nodos) indicada
		primero.generarLista(cantCol);
		filas++;
		columnas=cantCol;
		ListasDobles current=primero;
		colocador=current.head;//se inicializa el puntero colocador
		distribuir=current.head;//se inicializa el puntero distribuidor
		esqSI=current.head;//se inicializa la esquina superior izquierda
		esqSD=current.tail;//se inicializa la esquina superior derecha
		while(filas<cantFilas){//Ciclo que genera una matriz con mas de una fila
			ListasDobles nuevo= new ListasDobles(0,filas*16);
			nuevo.generarLista(cantCol);
			ListasDobles temp=nuevo;
			NodoMatriz recA=current.head;
			NodoMatriz recB=temp.head;
			while(recA!=null && recB!=null){//Ciclo que enlaza las filas
				recA.down=recB;
				recB.up=recA;
				recA=recA.right;
				recB=recB.right;
			}
			filas++;
			current=temp;
		}
		esqII=current.head;//se inicializa la esquina inferior izquierda
		esqID=current.tail;//se inicializa la esquina inferior derecha
	}
	
	/**
	 * Metodo que recibe una cola y una pila para distribuir los Items que contienen por la matriz 
	 * @param items lista tipo cola que contiene objetos tipo Items
	 * @param poderes lista tipo pila que contiene objetos tipo Items
	 */
	public void distribMatriz(Cola items,Pila poderes){
		int [] un={-1,1};
		Random num=new Random();//numero aleatorio
		while(items.getTam()>0){
			Item obj=items.remove();
			distribuirItem((num.nextInt(filas - 2)  + 2) * un[num.nextInt(2)] ,(num.nextInt(columnas - 2)  + 2)* un[num.nextInt(2)],obj);
		}
		while(poderes.getTam()>0){
			Item pod=poderes.remove();
			distribuirItem((num.nextInt(filas - 2) + 2) * un[num.nextInt(2)] ,(num.nextInt(columnas - 2)  + 2)* un[num.nextInt(2)],pod);
		}
		
	}

	
	/**
	 *Metodo que coloca de manera aleatoria objetos de tipo Item en los nodos de la matriz
	 *@see HiloColocador 
	 */
	public void rellenarMatriz(){
		int [] un={-1,1};
		Random num=new Random();//numero aleatorio
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
}


