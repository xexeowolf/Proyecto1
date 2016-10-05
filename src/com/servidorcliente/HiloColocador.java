package com.servidorcliente;

import com.matriz.MatrizDinamica;

/**
 * Clase que coloca objetos de tipo Item aleatoriamente en los nodos de la matriz.
 * @author Luis Alfredo Piedra Esquivel
 * @since 02/10/2016
 * @see MatrizDinamica
 */
public class HiloColocador extends Thread {

	public MatrizDinamica matriz;
	
	/**
	 * Constructor de la clase.
	 * @param mat matriz sobre la cual el hilo actuara.
	 */
	public HiloColocador(MatrizDinamica mat){
		matriz=mat;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Thread#run()
	 */
	public void run(){
		matriz.rellenarMatriz();//metodo que coloca items sobre la matriz
	
	}

}
