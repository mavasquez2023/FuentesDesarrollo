package cl.laaraucana.satelites.Utils;

import cl.laaraucana.satelites.certificados.utils.CertificadoConst;

/*
 * @author J-Factory
 *
 */
public class PerfilarSingleton {
	
	private static PerfilarSingleton instance = null;
	String perfilamientoActivo= "true";
	
	
	protected PerfilarSingleton() {
		this.perfilamientoActivo= CertificadoConst.PERFILAMIENTO_MENU;
	}
	
	
	public static PerfilarSingleton getInstance() {
	      if(instance == null) {
	         instance = new PerfilarSingleton();
	      }
	      return instance;
	   }


	/**
	 * @return the perfilamientoActivo
	 */
	public String getPerfilamientoActivo() {
		return perfilamientoActivo;
	}


	/**
	 * @param perfilamientoActivo the perfilamientoActivo to set
	 */
	public void setPerfilamientoActivo(String perfilamientoActivo) {
		this.perfilamientoActivo = perfilamientoActivo;
	}


	

}