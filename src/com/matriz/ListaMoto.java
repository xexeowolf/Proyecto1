package com.matriz;

/**
 * Clase que implementa una estructura tipo lista doblemente enlazado guarda los atributos de las motos necesarios para la logica del juego. 
 * Ademas cada nodo de la lista posee una tercer referencia que lo enlaza a una matriz.
 * @author Luis Alfredo Piedra Esquivel
 * @since 02/10/2016
 */
public class ListaMoto {
	private NodoMoto head;
	private NodoMoto tail;
	private NodoActor refEscudo; //puntero que  mantiene la imagen de un escudo sobre la imagen de la moto.
	private NodoActor imagen;	//puntero que alinea la imagen de la moto en la posicion donde este el primer nodo de la lista.	
	private NodoActor contraimagen;//puntero que mantiene la referencia sobre la imagen del ultimo nodo de la lista. 
	private int tam;
	public int ID; //Identificacion del jugador. Ejm: 1,2,3,4
	public boolean vivo;// Indica si el jugador sigue jugando(true) o su moto fue destruida(false).
	public String escudo; //Indica si el jugador tiene activado el escudo	
	private MatrizDinamica matriz;// Matriz sobre la cual la lista se desplazara e interactuara.
	public HiloMovMoto hilo;//Hilo que mantiene en movimiento a la moto y su respectiva imagen.
	
	
	/**
	 * Constructor de la clase.
	 * @param id numero entero con un valor de 1 a 5 que representa el numero de jugador, relevante para el servidor.
	 * @param nombre nombre de la imagen que representara a la lista en la interfaz grafica.
	 * @param m	matriz dinamica en la cual se va a enlazar la lista y sobre la cual realizara cambios.
	 */
	public ListaMoto(int id,String nombre,MatrizDinamica m) {
		NodoMoto nuevo=new NodoMoto();
		ID=id;
		nuevo.abajo=m.buscarLugar(id);
		matriz=m;
		while(nuevo.abajo==null){
			nuevo.abajo=m.buscarLugar(id);
		}
		imagen=matriz.agregarActor(nombre, -50, -50); //imagen que representa la lista en la interfaz grafica.
		contraimagen=imagen;
		head=tail=nuevo;
		tam=0;
		int k=3;
		while(k!=0){ //Ciclo que genera tres estelas iniciales.
			add();
			k--;
		}
		vivo=true;
	}
	
	/**
	 * Metodo que retorna la referencia al primer nodo de la lista,
	 * @return un puntero con la referencia al primer nodo de la lista.
	 */
	public NodoMoto getHead(){
		return head;
	}
	/**
	 * Metodo que modifica la referencia al primer nodo de la lista.
	 * @param tmp referencia la cual le dara su valor al puntero head y definira el cual es el primer nodo de la lista.
	 */
	public void setHead(NodoMoto tmp){
		head=tmp;
	}
	
	
	/**
	 * Metodo que agrega un nodo a la lista y lo relaciona con una imagen de estela para su representacion grafica.
	 * Siempre lo agrega al final de la lista debido a que el primer nodo se considera la moto en la interfaz grafica.
	 * @see NodoMoto
	 */
	public void add(){
		NodoMoto est=new NodoMoto(true);
		NodoActor tmp=matriz.agregarActor("estela"+Integer.toString(ID)+".gif",-50,-50);
		contraimagen.extra=tmp;
		contraimagen=tmp;
		tail.sig=est;
		tail.sig.ant=tail;
		tail=est;
		tam++;
	}
	
	/**
	 * Metodo que aumenta mantiene el tamano de la lista igual a el numero de estelas(atributo de la clase NodoMoto).
	 * @see NodoMoto (estela). 
	 */
	public void aumentarLista(){
		if(head.getEstela()>tam){
			add();
		}
	}
		
	/**
	 * Metodo encargado de verificar mediante la referencia a la matriz la informacion contenida en los nodos de dicha matriz.
	 * Ademas de obtener la informacion, la procesa segun la logica del juego y aplica los cambios respectivos al estado de la lista.
	 * @see MatrizDinamica,NodoMatriz
	 */
	public void verificar() {
		
		if(head.abajo.getEstado()==false){
			head.abajo.setEstado(true, "moto"); //indica que un nodo de la matriz esta ocupado por una moto
		}
		else{
			switch(head.abajo.getOcupante()){//obtiene un nombre que indica que elemento ocupa actualmente un nodo
			case "item":{
			Item k=head.abajo.getItem();//obtiene el item que se encuentra en el nodo. 
			if(k!=null){
				switch(k.getNombre()){
				case "combustible": head.agregarItem(k);matriz.quitarActor(head.abajo.getPosX(),head.abajo.getPosY(),"item");break;
				case "velocidad":head.agregarPoder(k);matriz.quitarActor(head.abajo.getPosX(),head.abajo.getPosY(),"item");break; 
				case "aumestela":head.agregarItem(k);matriz.quitarActor(head.abajo.getPosX(),head.abajo.getPosY(),"item");break;
				case "escudo":head.agregarPoder(k);matriz.quitarActor(head.abajo.getPosX(),head.abajo.getPosY(),"item");break;   
				case "bomba":matriz.quitarActor(head.abajo.getPosX(),head.abajo.getPosY(),"item");destruirMoto();break;
				}
			}
			if(head!=null && head.abajo!=null){
			head.abajo.setEstado(true, "moto");}//despues de retirar el item, la moto se coloca en el nodo.
			break;}
			case "moto":{destruirMoto();break;}
			case "estela":{if(escudo!="activo"){destruirMoto();}else{escudo="desac";matriz.quitarActor("escudoMoto.gif",refEscudo.getPosX(),refEscudo.getPosY());}refEscudo=null;break;}
			}
		}
		try {
			if(head!=null){
				Thread.sleep(head.getVel());
			}
			
		} catch (InterruptedException e) {
			System.out.print("Error en el tiempo de espera de la moto\n");
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Metodo que reasigna los punteros de cada nodo de la lista hacia la matriz.
	 * Ademas cambia de posicion las imagenes en la interfaz grafica segun la direccion del movimiento.
	 * @see NodoMoto,NodoMatriz,MatrizDinamica,NodoActor,ListaActores
	 */
	public void avanzar(){
		switch(head.getDireccion()){//direccion de la moto
		case "abj":{
			if(head.abajo.down!=null && head.abajo.down!=head.sig.abajo){//verifica que la lista no este en el limite de la matriz o trate de ir en direccion contraria.
				NodoMoto pto = tail;
				if(pto.abajo!=null){
					pto.abajo.setEstado(false, "");
				}
				while(pto!=head){//Ciclo que reasigna los punteros de las nodos representados como estelas
					pto.abajo=pto.ant.abajo;
					if(pto.abajo!=null){
					pto.abajo.setEstado(true, "estela");
					}
					pto=pto.ant;
				}
				head.abajo=head.abajo.down;
				imagen.setNombreImagen("moto"+Integer.toString(ID)+"abj.gif");//Cambio de imagen segun orientacion
				NodoMoto ptemp=head;
				NodoActor pAct=imagen;
				if(refEscudo!=null){ //cambia de posicion la imagen escudo segun  la posicion de la imagen de la moto.
					refEscudo.setPosX(head.abajo.getPosX());
					refEscudo.setPosY(head.abajo.getPosY());
				}
				while(ptemp!=null && ptemp.abajo!=null && pAct!=null){//cambia de posicion todas las imagenes que representa las motos y sus respectivas estelas
					pAct.setPosX(ptemp.abajo.getPosX());
					pAct.setPosY(ptemp.abajo.getPosY());
					pAct=pAct.extra;
					ptemp=ptemp.sig;
				}
				verificar();
				if(head!=null){
					head.reduceGas();	
				}
			}
			break;}
		case "arr":{
			if(head.abajo.up!=null && head.abajo.up!=head.sig.abajo){//verifica que la lista no este en el limite de la matriz o trate de ir en direccion contraria.
				NodoMoto pto = tail;
				if(pto.abajo!=null){
					pto.abajo.setEstado(false, "");
				}
				while(pto!=head){//Ciclo que reasigna los punteros de las nodos representados como estelas
					pto.abajo=pto.ant.abajo;
					if(pto.abajo!=null){
					pto.abajo.setEstado(true, "estela");
					}
					pto=pto.ant;
				}
				head.abajo=head.abajo.up;
				imagen.setNombreImagen("moto"+Integer.toString(ID)+"arr.gif");//Cambio de imagen segun orientacion
				NodoMoto ptemp=head;
				NodoActor pAct=imagen;
				if(refEscudo!=null){//cambia de posicion la imagen escudo segun  la posicion de la imagen de la moto.
					refEscudo.setPosX(head.abajo.getPosX());
					refEscudo.setPosY(head.abajo.getPosY());
				}
				while(ptemp!=null && ptemp.abajo!=null && pAct!=null){//cambia de posicion todas las imagenes que representa las motos y sus respectivas estelas
					pAct.setPosX(ptemp.abajo.getPosX());
					pAct.setPosY(ptemp.abajo.getPosY());
					pAct=pAct.extra;
					ptemp=ptemp.sig;
				}
				verificar();
				if(head!=null){
					head.reduceGas();	
				}
			}
			break;}
		case "der":{
			if(head.abajo.right!=null && head.abajo.right!=head.sig.abajo){//verifica que la lista no este en el limite de la matriz o trate de ir en direccion contraria.
				NodoMoto pto = tail;
				if(pto.abajo!=null){
					pto.abajo.setEstado(false, "");
				}
				while(pto!=head){//Ciclo que reasigna los punteros de las nodos representados como estelas
					pto.abajo=pto.ant.abajo;
					if(pto.abajo!=null){
					pto.abajo.setEstado(true, "estela");
					}
					pto=pto.ant;
				}
				head.abajo=head.abajo.right;
				imagen.setNombreImagen("moto"+Integer.toString(ID)+"der.gif");//Cambio de imagen segun orientacion
				NodoMoto ptemp=head;
				NodoActor pAct=imagen;
				if(refEscudo!=null){//cambia de posicion la imagen escudo segun  la posicion de la imagen de la moto.
					refEscudo.setPosX(head.abajo.getPosX());
					refEscudo.setPosY(head.abajo.getPosY());
				}
				while(ptemp!=null && ptemp.abajo!=null && pAct!=null){//cambia de posicion todas las imagenes que representa las motos y sus respectivas estelas
					pAct.setPosX(ptemp.abajo.getPosX());
					pAct.setPosY(ptemp.abajo.getPosY());
					pAct=pAct.extra;
					ptemp=ptemp.sig;
				}
				verificar();
				if(head!=null){
					head.reduceGas();	
				}
			}
			break;}
		case "izq":{
			if(head.abajo.left!=null && head.abajo.left!=head.sig.abajo){//verifica que la lista no este en el limite de la matriz o trate de ir en direccion contraria.
				NodoMoto pto = tail;
				if(pto.abajo!=null){
					pto.abajo.setEstado(false, "");
				}
				while(pto!=head){//Ciclo que reasigna los punteros de las nodos representados como estelas
					pto.abajo=pto.ant.abajo;
					if(pto.abajo!=null){
					pto.abajo.setEstado(true, "estela");
					}
					pto=pto.ant;
				}
				head.abajo=head.abajo.left;
				imagen.setNombreImagen("moto"+Integer.toString(ID)+"izq.gif");//Cambio de imagen segun orientacion
				NodoMoto ptemp=head;
				NodoActor pAct=imagen;
				if(refEscudo!=null){//cambia de posicion la imagen escudo segun  la posicion de la imagen de la moto.
					refEscudo.setPosX(head.abajo.getPosX());
					refEscudo.setPosY(head.abajo.getPosY());
				}
				while(ptemp!=null && ptemp.abajo!=null && pAct!=null){//cambia de posicion todas las imagenes que representa las motos y sus respectivas estelas
					pAct.setPosX(ptemp.abajo.getPosX());
					pAct.setPosY(ptemp.abajo.getPosY());
					pAct=pAct.extra;
					ptemp=ptemp.sig;
				}
				verificar();
				if(head!=null){
					head.reduceGas();	
				}
				
			}
			break;}
	}
	if(head != null && head.getGas()<=0.0){//si la moto se queda sin combustible es destruida 
		destruirMoto();
	}
}
	
	/**
	 * Metodo que se encarga de eliminar los punteros que relacionan la lista con la matriz 
	 * Ademas de  sus respectivas imagenes de la interfaz.
	 * @see MatrizDinamica,ListaActores
	 */
	public void destruirMoto()
	{	if(head!=null){
			matriz.distribMatriz(head.getItems(), head.getPoderes());}
		NodoMoto temporal=null;
		while(head!=null && head.abajo!=null){
			head.abajo.setEstado(false,"");
			head.abajo=null;
			head=head.sig;
		}
		head=temporal;
		while(imagen!=null){
			imagen.setNombreImagen("vacio.gif");
			imagen=imagen.extra;
		}
		if(refEscudo!=null){
			refEscudo.setNombreImagen("vacio.gif");
		}
		matriz.getListaActores().eliminarMoto("vacio.gif");
		vivo=false;
		
	}
	
	/**
	 * Metodo que retorna una valor booleano que representa si la moto sigue en juego o fue destruida.
	 * @return true si la lista sigue en juego o false si fue destruida.
	 */
	public boolean getVivo(){
		return vivo;
	}
	
	/**
	 * Metodo que aplica los efectos de los items guardados en la cola sobre los atributos del primer nodo de la lista.
	 * @see Item
	 */
	public void aplicarItem(){
			if( head!=null && head.getItems().getTam()>0){
				switch(head.getItems().getItem().getNombre()){
				case "combustible":head.aumGas(head.getItems().remove().getValor());break;
				case "aumestela":head.setEstela(head.getItems().remove().getValor());aumentarLista();break;
				}
				
			}
		
	}
	
	/**
	 * Metodo que aplica los efectos de los poderes guardados en la pila, sobre los atributos del primer nodo de la lista.
	 *@see Item
	 */
	
	public void aplicarPoder(){
		if(head!=null && head.getPoderes().getTam()!=0){
			Item pod=head.getPoderes().remove();//item del ultimo nodo agregado en la pila
			switch(pod.getNombre()){
			case "escudo": if(escudo!="activo"){refEscudo=matriz.agregarActor("escudoMoto.gif", imagen.getPosX(), imagen.getPosY());}escudo="activo";break;
			case "velocidad":head.setVel(pod.getValor());break;
			}	
		}
		
	}
	
	/**
	 * Metodo aplica el movimiento de la lista sobre la matriz si esta no ha sido eliminada.
	 * @see HiloMovMoto
	 */
	public void moverse(){
		if(vivo==true){
			avanzar();
		}
		
	}

	

}
