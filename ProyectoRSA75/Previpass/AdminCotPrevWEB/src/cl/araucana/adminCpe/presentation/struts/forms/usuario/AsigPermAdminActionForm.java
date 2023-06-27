package cl.araucana.adminCpe.presentation.struts.forms.usuario;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.Factory;
import org.apache.commons.collections.ListUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import cl.araucana.cp.distribuidor.hibernate.beans.EmpresaVO;
/*
* @(#) AsigPermAdminActionForm.java 1.1 10/05/2009
*
* Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
* La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
* restringido a los sistemas de información propios de la institución.
*/
/**
 * @author cchamblas
 * 
 * @version 1.1
 */
public class AsigPermAdminActionForm extends ActionForm 
{
	private static final long serialVersionUID = 780718564613748641L;
	private int idAdmin;
	private String idAdminFmt;
	private String nombre;
	private String apellidos;
	private String rutEmpresa;
	private String razonSocial;
	private String idGrupo;
	private String nombreGrupo;

	private List permisos;
	private List permisosPorAsignar;

	/**
	 * reset
	 */
	public void reset(ActionMapping mapping, HttpServletRequest request)
	{
		this.permisos =  ListUtils.lazyList(new ArrayList(),
				new Factory() {
					public Object create() {
						return new EmpresaVO();
					}
				}
			);

			this.permisosPorAsignar =  ListUtils.lazyList(new ArrayList(),
					new Factory() {
						public Object create() {
							return new EmpresaVO();
						}
					}
				);
	}

	/**
	 * apellidos
	 * @return
	 */
	public String getApellidos() {
		return this.apellidos;
	}

	/**
	 * apellidos
	 * @param apellidos
	 */
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	/**
	 * id admin
	 * @return
	 */
	public int getIdAdmin() {
		return this.idAdmin;
	}

	/**
	 * id admin
	 * @param idAdmin
	 */
	public void setIdAdmin(int idAdmin) {
		this.idAdmin = idAdmin;
	}

	/**
	 * id admin fmt
	 * @return
	 */
	public String getIdAdminFmt() {
		return this.idAdminFmt;
	}

	/**
	 * id admin fmt
	 * @param idAdminFmt
	 */
	public void setIdAdminFmt(String idAdminFmt) {
		this.idAdminFmt = idAdminFmt;
	}

	/**
	 * id grupo
	 * @return
	 */
	public String getIdGrupo() {
		return this.idGrupo;
	}

	/**
	 * id grupo
	 * @param idGrupo
	 */
	public void setIdGrupo(String idGrupo) {
		this.idGrupo = idGrupo;
	}

	/**
	 * nombre
	 * @return
	 */
	public String getNombre() {
		return this.nombre;
	}

	/**
	 * nombre
	 * @param nombre
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * nombre grupo
	 * @return
	 */
	public String getNombreGrupo() {
		return this.nombreGrupo;
	}

	/**
	 * nombre grupo
	 * @param nombreGrupo
	 */
	public void setNombreGrupo(String nombreGrupo) {
		this.nombreGrupo = nombreGrupo;
	}

	/*
	 * permisos
	 */
	public List getPermisos() {
		return this.permisos;
	}

	/**
	 * permisos
	 * @param permisos
	 */
	public void setPermisos(List permisos) {
		this.permisos = permisos;
	}

	/**
	 * permisis por asignar
	 * @return
	 */
	public List getPermisosPorAsignar() {
		return this.permisosPorAsignar;
	}

	/**
	 * permisos por asignar
	 * @param permisosPorAsignar
	 */
	public void setPermisosPorAsignar(List permisosPorAsignar) {
		this.permisosPorAsignar = permisosPorAsignar;
	}

	/**
	 * razon social
	 * @return
	 */
	public String getRazonSocial() {
		return this.razonSocial;
	}

	/**
	 * razon sacial
	 * @param razonSocial
	 */
	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}

	/**
	 * rut empresa
	 * @return
	 */
	public String getRutEmpresa() {
		return this.rutEmpresa;
	}

	/**
	 * rut empresa
	 * @param rutEmpresa
	 */
	public void setRutEmpresa(String rutEmpresa) {
		this.rutEmpresa = rutEmpresa;
	}
}
