package com.matriz;

public class ListaMoto {
	private NodoMoto head;
	private NodoMoto tail;
	private NodoActor refEscudo;
	private NodoActor imagen;
	private NodoActor contraimagen;
	private int tam;
	public int ID;
	private boolean vivo;
	public String escudo;
	private MatrizDinamica matriz;
	public HiloMovMoto hilo;
	
	
	public ListaMoto(int id,String nombre,MatrizDinamica m) {
		NodoMoto nuevo=new NodoMoto();
		ID=id;
		nuevo.abajo=m.buscarLugar(id);
		matriz=m;
		while(nuevo.abajo==null){
			nuevo.abajo=m.buscarLugar(id);
		}
		imagen=matriz.agregarActor(nombre, -50, -50);
		contraimagen=imagen;
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
		NodoActor tmp=matriz.agregarActor("estela"+Integer.toString(ID)+".gif",-50,-50);
		contraimagen.extra=tmp;
		contraimagen=tmp;
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
			}
			if(head!=null && head.abajo!=null){
			head.abajo.setEstado(true, "moto");}
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
	
	public void avanzar(){
		switch(head.getDireccion()){
		case "abj":{
			if(head.abajo.down!=null && head.abajo.down!=head.sig.abajo){
				NodoMoto pto = tail;
				if(pto.abajo!=null){
					pto.abajo.setEstado(false, "");
				}
				while(pto!=head){
					pto.abajo=pto.ant.abajo;
					if(pto.abajo!=null){
					pto.abajo.setEstado(true, "estela");
					}
					pto=pto.ant;
				}
				head.abajo=head.abajo.down;
				imagen.setNombreImagen("moto"+Integer.toString(ID)+"abj.gif");
				NodoMoto ptemp=head;
				NodoActor pAct=imagen;
				if(refEscudo!=null){
					refEscudo.setPosX(head.abajo.getPosX());
					refEscudo.setPosY(head.abajo.getPosY());
				}
				while(ptemp!=null && ptemp.abajo!=null && pAct!=null){
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
			if(head.abajo.up!=null && head.abajo.up!=head.sig.abajo){
				NodoMoto pto = tail;
				if(pto.abajo!=null){
					pto.abajo.setEstado(false, "");
				}
				while(pto!=head){
					pto.abajo=pto.ant.abajo;
					if(pto.abajo!=null){
					pto.abajo.setEstado(true, "estela");
					}
					pto=pto.ant;
				}
				head.abajo=head.abajo.up;
				imagen.setNombreImagen("moto"+Integer.toString(ID)+"arr.gif");
				NodoMoto ptemp=head;
				NodoActor pAct=imagen;
				if(refEscudo!=null){
					refEscudo.setPosX(head.abajo.getPosX());
					refEscudo.setPosY(head.abajo.getPosY());
				}
				while(ptemp!=null && ptemp.abajo!=null && pAct!=null){
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
			if(head.abajo.right!=null && head.abajo.right!=head.sig.abajo){
				NodoMoto pto = tail;
				if(pto.abajo!=null){
					pto.abajo.setEstado(false, "");
				}
				while(pto!=head){
					pto.abajo=pto.ant.abajo;
					if(pto.abajo!=null){
					pto.abajo.setEstado(true, "estela");
					}
					pto=pto.ant;
				}
				head.abajo=head.abajo.right;
				imagen.setNombreImagen("moto"+Integer.toString(ID)+"der.gif");
				NodoMoto ptemp=head;
				NodoActor pAct=imagen;
				if(refEscudo!=null){
					refEscudo.setPosX(head.abajo.getPosX());
					refEscudo.setPosY(head.abajo.getPosY());
				}
				while(ptemp!=null && ptemp.abajo!=null && pAct!=null){
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
			if(head.abajo.left!=null && head.abajo.left!=head.sig.abajo){
				NodoMoto pto = tail;
				if(pto.abajo!=null){
					pto.abajo.setEstado(false, "");
				}
				while(pto!=head){
					pto.abajo=pto.ant.abajo;
					if(pto.abajo!=null){
					pto.abajo.setEstado(true, "estela");
					}
					pto=pto.ant;
				}
				head.abajo=head.abajo.left;
				imagen.setNombreImagen("moto"+Integer.toString(ID)+"izq.gif");
				NodoMoto ptemp=head;
				NodoActor pAct=imagen;
				if(refEscudo!=null){
					refEscudo.setPosX(head.abajo.getPosX());
					refEscudo.setPosY(head.abajo.getPosY());
				}
				while(ptemp!=null && ptemp.abajo!=null && pAct!=null){
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
	if(head != null && head.getGas()<=0.0){
		destruirMoto();
	}
}
	
	public void destruirMoto()
	{	if(head!=null){
			matriz.distribMatriz(head.getItems(), head.getPoderes());}
		while(head!=null && head.abajo!=null){
			head.abajo.setEstado(false,"");
			head=head.sig;
		}
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
	
	public boolean getVivo(){
		return vivo;
	}
	
	public void aplicarItem(){
			if( head!=null && head.getItems().getTam()>0){
				switch(head.getItems().getItem().getNombre()){
				case "combustible":head.aumGas(head.getItems().remove().getValor());break;
				case "aumestela":head.setEstela(head.getItems().remove().getValor());aumentarLista();break;
				}
				
			}
		
	}
	
	public void aplicarPoder(){
		if(head!=null && head.getPoderes().getTam()!=0){
			Item pod=head.getPoderes().remove();
			switch(pod.getNombre()){
			case "escudo": if(escudo!="activo"){refEscudo=matriz.agregarActor("escudoMoto.gif", imagen.getPosX(), imagen.getPosY());}escudo="activo";break;
			case "velocidad":head.setVel(pod.getValor());break;
			}	
		}
		
	}
	
	public void moverse(){
		if(vivo==true){
			avanzar();
		}
		
	}

	

}
