package com.matriz;
/**
 * Clase que genera de manera aleatoria un objeto de ciertas clases, estas son 5 subclases de la clase Item.
 * @author Luis Alfredo Piedra Esquivel
 * @see Item
 * @version 02/10/2016
 */
public class Fabrica {
	
	

	/**
	 * Constructor de la clase generico.
	 */
	public Fabrica() {
	}
	
	public Item crearItem(){
		int x= (int)(Math.random()*5+1);//Se genera un numero aleatorio para definir que tipo de objeto se va a crear.
		if(x==1){
			return new Bomba();
		}
		else if(x==2){
			return new AumentaEstela();
		}
		else if(x==3){
			return new Gas();
		}
		else if(x==4){
			return new Escudo();
		}else{
			return new HiperVelocidad();
		}
	}

}

