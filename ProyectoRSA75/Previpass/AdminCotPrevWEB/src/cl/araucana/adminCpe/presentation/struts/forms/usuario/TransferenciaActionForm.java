package cl.araucana.adminCpe.presentation.struts.forms.usuario;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.Factory;
import org.apache.commons.collections.ListUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import cl.araucana.adminCpe.presentation.struts.javaBeans.Usuario;
import cl.araucana.cp.distribuidor.hibernate.beans.EmpresaVO;
import cl.araucana.cp.distribuidor.presentation.beans.Empresa;
import cl.araucana.cp.distribuidor.presentation.beans.GrupoConvenio;
import cl.araucana.cp.distribuidor.presentation.beans.PermEncargadoLector;

/*
 * @(#) TransferenciaActionForm.java 1.4 10/05/2009
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */
/**
 * @author cchamblas
 * 
 * @version 1.4
 */
public class TransferenciaActionForm extends ActionForm implements Serializable
{
	private static final long serialVersionUID = -6011757842846230003L;
	private int rutInicioTrans;
	private int rutDestinoTrans;
	private String rutInicio;
	private String rutDestino;
	private String volver;
	private String operacion;
	private Usuario usuarioInicio;
	private Usuario usuarioDestino;

	private List permisosEncLector;
	private List permisosAdmin;

	/**
	 * reset
	 */
	public void reset(ActionMapping arg0, HttpServletRequest arg1)
	{
		super.reset(arg0, arg1);

		this.permisosEncLector = ListUtils.lazyList(new ArrayList(), new Factory()
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

		this.permisosAdmin = ListUtils.lazyList(new ArrayList(), new Factory()
		{
			public Object create()
			{
				return new EmpresaVO();
			}
		});
	}

	/**
	 * permisos admin
	 * 
	 * @return
	 */
	public List getPermisosAdmin()
	{
		return this.permisosAdmin;
	}

	/**
	 * permisos admin
	 * 
	 * @param permisosAdmin
	 */
	public void setPermisosAdmin(List permisosAdmin)
	{
		this.permisosAdmin = permisosAdmin;
	}

	/**
	 * permisos lector
	 * 
	 * @return
	 */
	public List getPermisosEncLector()
	{
		return this.permisosEncLector;
	}

	/**
	 * permisos lector
	 * 
	 * @param permisosEncLector
	 */
	public void setPermisosEncLector(List permisosEncLector)
	{
		this.permisosEncLector = permisosEncLector;
	}

	/**
	 * rut destino
	 * 
	 * @return
	 */
	public String getRutDestino()
	{
		return this.rutDestino;
	}

	/**
	 * rut destino
	 * 
	 * @param rutDestino
	 */
	public void setRutDestino(String rutDestino)
	{
		this.rutDestino = rutDestino;
	}

	/**
	 * rut inicio
	 * 
	 * @return
	 */
	public String getRutInicio()
	{
		return this.rutInicio;
	}

	/**
	 * rut inicio
	 * 
	 * @param rutInicio
	 */
	public void setRutInicio(String rutInicio)
	{
		this.rutInicio = rutInicio;
	}

	/**
	 * usuario destino
	 * 
	 * @return
	 */
	public Usuario getUsuarioDestino()
	{
		return this.usuarioDestino;
	}

	/**
	 * usuario destino
	 * 
	 * @param usuarioDestino
	 */
	public void setUsuarioDestino(Usuario usuarioDestino)
	{
		this.usuarioDestino = usuarioDestino;
	}

	/**
	 * usuario inicio
	 * 
	 * @return
	 */
	public Usuario getUsuarioInicio()
	{
		return this.usuarioInicio;
	}

	/**
	 * uauario inicio
	 * 
	 * @param usuarioInicio
	 */
	public void setUsuarioInicio(Usuario usuarioInicio)
	{
		this.usuarioInicio = usuarioInicio;
	}

	/**
	 * operacion
	 * 
	 * @return
	 */
	public String getOperacion()
	{
		return this.operacion;
	}

	/**
	 * operacion
	 * 
	 * @param operacion
	 */
	public void setOperacion(String operacion)
	{
		this.operacion = operacion;
	}

	/**
	 * rut destino trans
	 * 
	 * @return
	 */
	public int getRutDestinoTrans()
	{
		return this.rutDestinoTrans;
	}

	/**
	 * rut destino trans
	 * 
	 * @param rutDestinoTrans
	 */
	public void setRutDestinoTrans(int rutDestinoTrans)
	{
		this.rutDestinoTrans = rutDestinoTrans;
	}

	/**
	 * rut inicio trans
	 * 
	 * @return
	 */
	public int getRutInicioTrans()
	{
		return this.rutInicioTrans;
	}

	/**
	 * rut inicio trans
	 * 
	 * @param rutInicioTrans
	 */
	public void setRutInicioTrans(int rutInicioTrans)
	{
		this.rutInicioTrans = rutInicioTrans;
	}

	/**
	 * volver
	 * 
	 * @return
	 */
	public String getVolver()
	{
		return this.volver;
	}

	/**
	 * volver
	 * 
	 * @param volver
	 */
	public void setVolver(String volver)
	{
		this.volver = volver;
	}
}
