package cl.araucana.autoconsulta.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

/**
 * @author aituarte
 *
 */
public class RutVO implements Serializable {
	
	private long rut=0;
	private String dv="?";
	

	/**
	 * @return
	 */
	public long getRut() {
		return rut;
	}

	/**
	 * @param l
	 */
	public void setRut(long l) {
		rut = l;
	}
	/**
	 * @return
	 */
	public String getDv() {
		return dv;
	}

	/**
	 * @param l
	 */
	public void setDv(String s) {
		dv = s;
	}
	
	/**
	 * @return
	 */
	public String getFullRut() {
		return rut+"-"+dv;
	}	
}
