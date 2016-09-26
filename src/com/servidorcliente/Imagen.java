package com.servidorcliente;

import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.HashMap;
import javax.imageio.ImageIO;


public class Imagen {
	private HashMap imagenes;
	      
	public Imagen() {
	        imagenes = new HashMap();
	}
	      
	private BufferedImage cargarImagen(String nombre) {
	    URL url=null;
	    try {
	          url = getClass().getClassLoader().getResource(nombre);
	          return ImageIO.read(url);
	    }
	    catch (Exception e) {
	          System.out.println("No se pudo cargar la imagen " + nombre +" de "+url);
	          System.out.println("El error fue : "+e.getClass().getName()+" "+e.getMessage());
	          System.exit(0);
	          return null;
	    }
	  }
	     
	public BufferedImage obtenerImagen(String nombre) {
		BufferedImage img = (BufferedImage)imagenes.get(nombre);
	    if (img == null) {
	          img = cargarImagen("Imagenes/"+nombre);
	          imagenes.put(nombre,img);
	        }
	     return img;
	      }
}