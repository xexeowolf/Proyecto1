package com.matriz;

public class ListaMoto {
	private NodoMoto head;
	private NodoMoto tail;
	private NodoActor imagen;
	private int tam;
	private int ID;
	private boolean vivo;
	private String escudo;
	private MatrizDinamica matriz;
	public HiloMovMoto hilo;
	

	
	public ListaMoto(int id,String nombre,MatrizDinamica m) {
		NodoMoto nuevo=new NodoMoto();
		ID=id;
		nuevo.abajo=m.buscarLugar();
		matriz=m;
		while(nuevo.abajo==null){
			nuevo.abajo=m.buscarLugar();
		}
		imagen=m.agregarActor(nombre, 0, 0,imagen);
		head=tail=nuevo;
		tam=0;
		int k=3;
		while(k!=0){
			add();
			k--;
		}
		vivo=true;
	}
	
	public NodoMoto getHead(){
		return head;
	}
	
	public void add(){
		NodoMoto est=new NodoMoto(true);
		NodoActor tmp=null;
		tmp=matriz.agregarActor("estela"+Integer.toString(ID)+".gif",0,0,tmp);
		tail.sig=est;
		tail.sig.ant=tail;
		tail=est;
		tam++;
	}
	
	public void aumentarLista(){
		if(head.getEstela()>tam){
			add();
		}
	}
	
	@SuppressWarnings("deprecation")
	public void precaucion(){
		hilo.stop();
		if(head.abajo!=null){
			hilo.resume();
			enlazar();
		}
		hilo.resume();
	}
	
	public void enlazar(){
		NodoMoto tmp=tail;
		if(tmp.abajo!=null){
		tmp.abajo.setEstado(false, "");}
		tmp.abajo=tmp.ant.abajo;
		tmp=tmp.ant;
		if(tmp.abajo!=null){
		tmp.abajo.setEstado(true, "estela");}
		while(tmp!=head){
			tmp.abajo=tmp.ant.abajo;
			tmp=tmp.ant;
			if(tmp.abajo!=null){
			tmp.abajo.setEstado(true, "estela");
			}
		}	
		
		switch(head.getDireccion()){
		case "arr": 	
			if(head.abajo.up!=null)
		{	
			head.abajo=head.abajo.up;
			imagen.setPosX(head.abajo.getPosX());
			imagen.setPosY(head.abajo.getPosY());
			NodoActor punt=imagen.next;
			NodoMoto punt2=head.sig;
			while(punt!=null && punt2!=null)
			{ if(punt2.abajo!=null){	
				punt.setPosX(punt2.abajo.getPosX());
				punt.setPosY(punt2.abajo.getPosY());}
				punt=punt.next;
				punt2=punt2.sig;
			};
			verificar();}break;
		case "abj": 
			if(head.abajo.down!=null)
		{	head.abajo=head.abajo.down;
		imagen.setPosX(head.abajo.getPosX());
		imagen.setPosY(head.abajo.getPosY());
		NodoActor punt=imagen.next;
		NodoMoto punt2=head.sig;
		while(punt!=null && punt2!=null)
		{ if(punt2.abajo!=null){	
			punt.setPosX(punt2.abajo.getPosX());
			punt.setPosY(punt2.abajo.getPosY());}
			punt=punt.next;
			punt2=punt2.sig;
		};
		verificar();}break;
		case "der": 
			if(head.abajo.right!=null)
		{	head.abajo=head.abajo.right;
			imagen.setPosX(head.abajo.getPosX());
			imagen.setPosY(head.abajo.getPosY());
			NodoActor punt=imagen.next;
			NodoMoto punt2=head.sig;
			while(punt!=null && punt2!=null)
			{if(punt2.abajo!=null){ 	
				punt.setPosX(punt2.abajo.getPosX());
				punt.setPosY(punt2.abajo.getPosY());}
				punt=punt.next;
				punt2=punt2.sig;
			};
			verificar();}break;
		case "izq": 
			if(head.abajo.left!=null)
		{	head.abajo=head.abajo.left;
			imagen.setPosX(head.abajo.getPosX());
			imagen.setPosY(head.abajo.getPosY());
			NodoActor punt=imagen.next;
			NodoMoto punt2=head.sig;
			while(punt!=null && punt2!=null)
			{if(punt2.abajo!=null){
				punt.setPosX(punt2.abajo.getPosX());
				punt.setPosY(punt2.abajo.getPosY());}
				punt=punt.next;
				punt2=punt2.sig;
			};
			verificar();}break;
		}
		head.reduceGas();
		if(head.getGas()==0.0){
			destruirMoto();
		}
	}
		
	public void verificar() {
		
		if(head.abajo.getEstado()==false){
			head.abajo.setEstado(true, "moto");
		}
		else{
			switch(head.abajo.getOcupante()){
			case "item":{
			Item k=head.abajo.getItem();
			if(k!=null){
				switch(k.getNombre()){
				case "combustible": head.agregarItem(k);matriz.quitarActor(head.abajo.getPosX(),head.abajo.getPosY(),"item");break;
				case "velocidad":head.agregarPoder(k);matriz.quitarActor(head.abajo.getPosX(),head.abajo.getPosY(),"item");break; 
				case "aumestela":head.agregarItem(k);matriz.quitarActor(head.abajo.getPosX(),head.abajo.getPosY(),"item");break;
				case "escudo":head.agregarPoder(k);matriz.quitarActor(head.abajo.getPosX(),head.abajo.getPosY(),"item");break;   
				case "bomba":matriz.quitarActor(head.abajo.getPosX(),head.abajo.getPosY(),"item");destruirMoto();break;
				}
			}break;}
			case "moto":{destruirMoto();break;}
			case "estela":{if(escudo!="activo"){destruirMoto();}else{escudo="desac";};break;}
			}
		}
		try {
			Thread.sleep(head.getVel());
		} catch (InterruptedException e) {
			System.out.print("Error al mover la moto\n");
			e.printStackTrace();
		}
		
	}
	
	@SuppressWarnings("deprecation")
	public void destruirMoto()
	{
		NodoActor puntero=imagen;
		while(puntero!=null){
			matriz.quitarActor(puntero);
			puntero=puntero.next;
		}
		matriz.quitarActor(imagen);
		matriz.distribMatriz(head.getItems(), head.getPoderes());
		NodoMoto tm=head;
		while(tm!=null){
			tm.abajo.setEstado(false, "");
			tm.abajo=null;
			tm=tm.sig;
		}
		head=tail=null;
		vivo=false;
	}
	
	public boolean getVivo(){
		return vivo;
	}
	
	public void aplicarItem(){
		while(true){
			if(head.getItems().getTam()>0){
				switch(head.getItems().getItem().getNombre()){
				case "combustible":head.aumGas(head.getItems().remove().getValor());break;
				case "aumestela":head.setEstela(head.getItems().remove().getValor());aumentarLista();break;
				}
			}
		}
	}
	
	public void aplicarPoder(){
		Item pod=head.getPoderes().remove();
		switch(pod.getNombre()){
		case "escudo": escudo="activo";break;
		case "velocidad":head.setVel(pod.getValor());break;
		}
	}
	
	public void moverse(){
			precaucion();
	}

	
	
	public static void main(String[] args) {

	}

}
