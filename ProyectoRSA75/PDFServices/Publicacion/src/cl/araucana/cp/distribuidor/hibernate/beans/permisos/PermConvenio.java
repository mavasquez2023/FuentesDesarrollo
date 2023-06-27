/*
 * Created on Nov 5, 2010
 *
 * Window - Preferences - Java - Code Style - Code Templates
 */
package cl.araucana.cp.distribuidor.hibernate.beans.permisos;

import java.io.Serializable;
import java.util.HashMap;

import cl.araucana.cp.distribuidor.hibernate.beans.ConvenioVO;
import cl.araucana.cp.distribuidor.hibernate.beans.SucursalVO;

/**
 * @author ccappelletti
 *
 */
public class PermConvenio implements Serializable {
	
	private ConvenioVO convenio;
	private boolean permiso = false;
	
	/**
	 * sucursales con permiso
	 */
	private HashMap sucursales = new HashMap();

	/**
	 * @param convenio
	 * @param permiso
	 */
	public PermConvenio(ConvenioVO convenio, boolean permiso) {
		super();
		this.convenio = convenio;
		this.permiso = permiso;
	}
	/**
	 * @return Returns the convenio.
	 */
	public ConvenioVO getConvenio() {
		return convenio;
	}
	/**
	 * @param convenio The convenio to set.
	 */
	public void setConvenio(ConvenioVO convenio) {
		this.convenio = convenio;
	}
	/**
	 * @return Returns the permiso.
	 */
	public boolean isPermiso() {
		return permiso;
	}
	/**
	 * @param permiso The permiso to set.
	 */
	public void setPermiso(boolean permiso) {
		this.permiso = permiso;
	}
	/**
	 * @return Returns the sucursales.
	 */
	public HashMap getSucursales() {
		return sucursales;
	}
	/**
	 * @param sucursales The sucursales to set.
	 */
	public void setSucursales(HashMap sucursales) {
		this.sucursales = sucursales;
	}
	
	/**
	 * Agrega una sucursal al permiso de convenio (agregarla aqui significa que tiene permiso)
	 * @param sucursal
	 */
	public void addSucursal (SucursalVO sucursal) {
		sucursales.put(sucursal.getIdSucursal(), sucursal);
	}
}
