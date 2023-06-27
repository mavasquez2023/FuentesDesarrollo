package cl.araucana.autoconsulta.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

/**
 * @author asepulveda
 *
 */
public class CuentasAhorroRutVO implements Serializable {
	
	private int cantidadCuentas=0;
	private int cantidadCuentasActivas=0;
	private int cantidadCuentasInactivas=0;
	private Collection cuentas=new ArrayList();  //CuentaAhorroVO


	/**
	 * @return
	 */
	public int getCantidadCuentas() {
		return cantidadCuentas;
	}

	/**
	 * @return
	 */
	public int getCantidadCuentasActivas() {
		return cantidadCuentasActivas;
	}

	/**
	 * @return
	 */
	public int getCantidadCuentasInactivas() {
		return cantidadCuentasInactivas;
	}

	/**
	 * @return
	 */
	public Collection getCuentas() {
		return cuentas;
	}

	/**
	 * @param i
	 */
	public void setCantidadCuentas(int i) {
		cantidadCuentas = i;
	}

	/**
	 * @param i
	 */
	public void setCantidadCuentasActivas(int i) {
		cantidadCuentasActivas = i;
	}

	/**
	 * @param i
	 */
	public void setCantidadCuentasInactivas(int i) {
		cantidadCuentasInactivas = i;
	}

	/**
	 * @param collection
	 */
	public void setCuentas(Collection collection) {
		cuentas = collection;
	}

}
