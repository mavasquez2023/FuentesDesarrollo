package cl.araucana.autoconsulta.vo;

import java.io.Serializable;

/**
 * 
 * @author	sebastian.helguera
 * @version	1.0.0	28/09/2009
 */
public class DeudaIntercajaVO implements Serializable {
	
	private long monto = 0;
	private String caja = null;
	
	
	/**
	 * @return
	 */
	public String getCaja() {
		return caja;
	}

	/**
	 * @return
	 */
	public long getMonto() {
		return monto;
	}

	/**
	 * @param string
	 */
	public void setCaja(String string) {
		caja = string;
	}

	/**
	 * @param i
	 */
	public void setMonto(long i) {
		monto = i;
	}

}
