package cl.araucana.autoconsulta.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

/**
 * @author asepulveda
 *
 */
public class OficinasSucursalesVO implements Serializable {

	private long codigoOficina=0;
	private String oficina=null;
	private long codigoSucursal=0;
	private String sucursal=null;
	private	boolean aCargo=false;	


	/**
	 * @return
	 */
	public long getCodigoOficina() {
		return codigoOficina;
	}

	/**
	 * @return
	 */
	public long getCodigoSucursal() {
		return codigoSucursal;
	}

	/**
	 * @return
	 */
	public String getOficina() {
		return oficina;
	}

	/**
	 * @return
	 */
	public String getSucursal() {
		return sucursal;
	}

	/**
	 * @param l
	 */
	public void setCodigoOficina(long l) {
		codigoOficina = l;
	}

	/**
	 * @param l
	 */
	public void setCodigoSucursal(long l) {
		codigoSucursal = l;
	}

	/**
	 * @param string
	 */
	public void setOficina(String string) {
		oficina = string;
	}

	/**
	 * @param string
	 */
	public void setSucursal(String string) {
		sucursal = string;
	}

	/**
	 * @return
	 */
	public boolean isACargo() {
		return aCargo;
	}

	/**
	 * @param b
	 */
	public void setACargo(boolean b) {
		aCargo = b;
	}

}
