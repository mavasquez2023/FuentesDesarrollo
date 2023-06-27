/*
 * Almacena la raiz de los permisos o sea el grupo convenio
 * 
 * Created on Nov 17, 2010
 * 
 * Este c�digo fuente pertenece a la Caja de Compensaci�n de Asignaci�n Familiar
 * La Araucana (C.C.A.F.). Su utilizaci�n y reproducci�n es confidencial y est�
 * restringido a los sistemas de informaci�n propios de la instituci�n. 
 *
*/
package cl.araucana.cp.distribuidor.hibernate.beans.permisos;

import java.util.HashMap;

/**
 * @author ccappelletti
 *
 */
public class PermGrupoConvenio {

	private int idGrupoConvenio;
	private HashMap permEmpresas = new HashMap ();
	private boolean permiso = false;	

	
	
	/**
	 * @param idGrupoConvenio
	 * @param permiso
	 */
	public PermGrupoConvenio(int idGrupoConvenio, boolean permiso) {
		super();
		this.idGrupoConvenio = idGrupoConvenio;
		this.permiso = permiso;
	}
	/**
	 * @return Returns the idGrupoConvenio.
	 */
	public int getIdGrupoConvenio() {
		return idGrupoConvenio;
	}
	/**
	 * @param idGrupoConvenio The idGrupoConvenio to set.
	 */
	public void setIdGrupoConvenio(int idGrupoConvenio) {
		this.idGrupoConvenio = idGrupoConvenio;
	}
	/**
	 * @return Returns the permEmpresas.
	 */
	public HashMap getPermEmpresas() {
		return permEmpresas;
	}
	/**
	 * @param permEmpresas The permEmpresas to set.
	 */
	public void setPermEmpresas(HashMap permEmpresas) {
		this.permEmpresas = permEmpresas;
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
	
	public void addPermEmpresa(PermEmpresa perm) {
		permEmpresas.put(new Integer (perm.getEmpresa().getIdEmpresa()), perm);
	}	
}
