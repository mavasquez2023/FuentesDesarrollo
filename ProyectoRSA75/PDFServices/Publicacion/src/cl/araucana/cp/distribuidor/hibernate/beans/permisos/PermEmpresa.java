/*
 * Created on Nov 5, 2010
 *
 * Este c�digo fuente pertenece a la Caja de Compensaci�n de Asignaci�n Familiar
 * La Araucana (C.C.A.F.). Su utilizaci�n y reproducci�n es confidencial y est�
 * restringido a los sistemas de informaci�n propios de la instituci�n.
 */
package cl.araucana.cp.distribuidor.hibernate.beans.permisos;

import java.io.Serializable;
import java.util.HashMap;

import cl.araucana.cp.distribuidor.hibernate.beans.EmpresaVO;

/**
 * @author ccappelletti
 *
 */
public class PermEmpresa implements Serializable {

	private EmpresaVO empresa;
	private boolean permiso = false;
	private HashMap permConvenios = new HashMap();
	
	/**
	 * @param empresa
	 * @param permiso
	 */
	public PermEmpresa(EmpresaVO empresa, boolean permiso) {
		super();
		this.empresa = empresa;
		this.permiso = permiso;
	}
	/**
	 * @return Returns the empresa.
	 */
	public EmpresaVO getEmpresa() {
		return empresa;
	}
	/**
	 * @param empresa The empresa to set.
	 */
	public void setEmpresa(EmpresaVO empresa) {
		this.empresa = empresa;
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
	
	public void addPermConvenio(PermConvenio perm) {
		permConvenios.put(new Integer (perm.getConvenio().getIdConvenio()), perm);
	}

	/**
	 * @return Returns the permConvenios.
	 */
	public HashMap getPermConvenios() {
		return permConvenios;
	}
	/**
	 * @param permConvenios The permConvenios to set.
	 */
	public void setPermConvenios(HashMap permConvenios) {
		this.permConvenios = permConvenios;
	}
}
