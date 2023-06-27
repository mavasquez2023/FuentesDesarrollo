package cl.araucana.adminCpe.presentation.struts.forms.usuario;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.Factory;
import org.apache.commons.collections.ListUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import cl.araucana.cp.distribuidor.presentation.beans.Empresa;
import cl.araucana.cp.distribuidor.presentation.beans.GrupoConvenio;
import cl.araucana.cp.distribuidor.presentation.beans.PermEncargadoLector;

/*
 * @(#) AsigPermEncargadoActionForm.java 1.3 10/05/2009
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */
/**
 * @author cchamblas
 * 
 * @version 1.3
 */
public class AsigPermEncargadoActionForm extends ActionForm
{
	private static final long serialVersionUID = 780718564613748641L;
	private int idEncargado;
	private String idEncargadoFmt;
	private String nombre;
	private String apellidos;
	private String rutEmpresa;
	private String razonSocial;
	private String idGrupo;
	private String nombreGrupo;

	private List permisos;
	private List newPermisos;

	/**
	 * reset
	 */
	public void reset(ActionMapping mapping, HttpServletRequest request)
	{
		this.permisos = ListUtils.lazyList(new ArrayList(), new Factory()
		{
			public Object create()
			{
				GrupoConvenio grupo = new GrupoConvenio();
				grupo.setEmpresas(ListUtils.lazyList(new ArrayList(), new Factory()
				{
					public Object create()
					{
						Empresa emp = new Empresa();
						emp.setConvenios(ListUtils.lazyList(new ArrayList(), new Factory()
						{
							public Object create()
							{
								return new PermEncargadoLector();
							}
						}));
						return emp;
					}
				}));
				return grupo;
			}
		});

		this.newPermisos = ListUtils.lazyList(new ArrayList(), new Factory()
		{
			public Object create()
			{
				GrupoConvenio grupo = new GrupoConvenio();
				grupo.setEmpresas(ListUtils.lazyList(new ArrayList(), new Factory()
				{
					public Object create()
					{
						Empresa emp = new Empresa();
						emp.setConvenios(ListUtils.lazyList(new ArrayList(), new Factory()
						{
							public Object create()
							{
								return new PermEncargadoLector();
							}
						}));
						return emp;
					}
				}));
				return grupo;
			}
		});
	}

	/**
	 * id encargado
	 * 
	 * @return
	 */
	public int getIdEncargado()
	{
		return this.idEncargado;
	}

	/**
	 * id encargado
	 * 
	 * @param idEncargado
	 */
	public void setIdEncargado(int idEncargado)
	{
		this.idEncargado = idEncargado;
	}

	/**
	 * id grupo
	 * 
	 * @return
	 */
	public String getIdGrupo()
	{
		return this.idGrupo;
	}

	/**
	 * id grupo
	 * 
	 * @param idGrupo
	 */
	public void setIdGrupo(String idGrupo)
	{
		this.idGrupo = idGrupo;
	}

	/**
	 * nombre grupo
	 * 
	 * @return
	 */
	public String getNombreGrupo()
	{
		return this.nombreGrupo;
	}

	/**
	 * nombre grupo
	 * 
	 * @param nombreGrupo
	 */
	public void setNombreGrupo(String nombreGrupo)
	{
		this.nombreGrupo = nombreGrupo;
	}

	/**
	 * permisos
	 * 
	 * @return
	 */
	public List getPermisos()
	{
		return this.permisos;
	}

	/**
	 * permisos
	 * 
	 * @param permisos
	 */
	public void setPermisos(List permisos)
	{
		this.permisos = permisos;
	}

	/**
	 * nuevos permisos
	 * 
	 * @return
	 */
	public List getNewPermisos()
	{
		return this.newPermisos;
	}

	/**
	 * nuevos permisos por asignar
	 * 
	 * @param permisosPorAsignar
	 */
	public void setNewPermisos(List permisosPorAsignar)
	{
		this.newPermisos = permisosPorAsignar;
	}

	/**
	 * razon social
	 * 
	 * @return
	 */
	public String getRazonSocial()
	{
		return this.razonSocial;
	}

	/**
	 * razon sacial
	 * 
	 * @param razonSocial
	 */
	public void setRazonSocial(String razonSocial)
	{
		this.razonSocial = razonSocial;
	}

	/**
	 * rut empresa
	 * 
	 * @return
	 */
	public String getRutEmpresa()
	{
		return this.rutEmpresa;
	}

	/**
	 * rut empresa
	 * 
	 * @param rutEmpresa
	 */
	public void setRutEmpresa(String rutEmpresa)
	{
		this.rutEmpresa = rutEmpresa;
	}

	/**
	 * apellidos
	 * 
	 * @return
	 */
	public String getApellidos()
	{
		return this.apellidos;
	}

	/**
	 * apellidos
	 * 
	 * @param apellidos
	 */
	public void setApellidos(String apellidos)
	{
		this.apellidos = apellidos;
	}

	/**
	 * id encargado fmt
	 * 
	 * @return
	 */
	public String getIdEncargadoFmt()
	{
		return this.idEncargadoFmt;
	}

	/**
	 * id encargado fmt
	 * 
	 * @param idEncargadoFmt
	 */
	public void setIdEncargadoFmt(String idEncargadoFmt)
	{
		this.idEncargadoFmt = idEncargadoFmt;
	}

	/**
	 * nombre
	 * 
	 * @return
	 */
	public String getNombre()
	{
		return this.nombre;
	}

	/**
	 * nombre
	 * 
	 * @param nombre
	 */
	public void setNombre(String nombre)
	{
		this.nombre = nombre;
	}
}
