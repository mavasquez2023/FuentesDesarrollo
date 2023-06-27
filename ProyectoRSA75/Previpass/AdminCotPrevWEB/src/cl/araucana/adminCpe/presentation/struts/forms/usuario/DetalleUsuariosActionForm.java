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
 * @(#) AsigPermAdminActionForm.java 1.9 10/05/2009
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */
/**
 * @author ccostagliola
 * @author cchamblas
 * 
 * @version 1.9
 */
public class DetalleUsuariosActionForm extends ActionForm
{
	private static final long serialVersionUID = -5011269036442468300L;

	private String idEncargadoFmt;
	private String apellidos;
	private String idUsuarioFmt;
	private String idUsuario;

	private String nombre;
	private String apPat;
	private String apMat;
	private String direccion;
	private String numero;
	private String dpto;
	private String email;
	private String fono;
	private String celular;
	private String fax;
	private String codigoFono;
	private String codigoFax;

	private int opcRegion;
	private int opcCiudad;
	private int opcComuna;
	private String opcHabilitado;
	private String nombreComuna;
	private String nombreCiudad;
	private String nombreRegion;
	private String nombreHabilitado;

	private List permisos;
	private List newPermisos;
	private List regiones;
	private List ciudades;
	private List comunas;

	// Cajas de filtro
	private String idGrupoConvenio;
	private String nombreGrupoConvenio;

	private String idEmpresa;
	private String nombreEmpresa;

	private String idEmpresaAdmin;
	private String nombreEmpresaAdmin;

	private boolean admin;

	private boolean adminSistemaAraucana;

	// vagurto, filtros jsp
	private String encargado;
	private String mostrarPermiso;
	private String mostrarDatos;

	private String idGrConvenio;
	private String nombreGrConvenio;
	private String password;
	private String confPassword;
	
//	TODO GMALLEA Para identificar si es empresa, independiente o los dos
	private boolean tipoAdminEmpresa;
	private boolean tipoAdminIndependiente;

	/**
	 * password
	 * 
	 * @return
	 */
	public String getPassword()
	{
		return this.password;
	}

	/**
	 * password
	 * 
	 * @param password
	 */
	public void setPassword(String password)
	{
		this.password = password;
	}

	/**
	 * id grupo convenio
	 * 
	 * @return
	 */
	public String getIdGrConvenio()
	{
		return this.idGrConvenio;
	}

	/**
	 * id grupo convenio
	 * 
	 * @param idGrConvenio
	 */
	public void setIdGrConvenio(String idGrConvenio)
	{
		this.idGrConvenio = idGrConvenio;
	}

	/**
	 * nombre grupo convenio
	 * 
	 * @return
	 */
	public String getNombreGrConvenio()
	{
		return this.nombreGrConvenio;
	}

	/**
	 * nombre grupo convenio
	 * 
	 * @param nombreGrConvenio
	 */
	public void setNombreGrConvenio(String nombreGrConvenio)
	{
		this.nombreGrConvenio = nombreGrConvenio;
	}

	/**
	 * mostrar datos
	 * 
	 * @return
	 */
	public String getMostrarDatos()
	{
		return this.mostrarDatos;
	}

	/**
	 * mostrar datos
	 * 
	 * @param mostrarDatos
	 */
	public void setMostrarDatos(String mostrarDatos)
	{
		this.mostrarDatos = mostrarDatos;
	}

	/**
	 * mostrar permisos
	 * 
	 * @return
	 */
	public String getMostrarPermiso()
	{
		return this.mostrarPermiso;
	}

	/**
	 * mostrar permisos
	 * 
	 * @param mostrarPermiso
	 */
	public void setMostrarPermiso(String mostrarPermiso)
	{
		this.mostrarPermiso = mostrarPermiso;
	}

	/**
	 * encargado
	 * 
	 * @return
	 */
	public String getEncargado()
	{
		return this.encargado;
	}

	/**
	 * encargado
	 * 
	 * @param encargado
	 */
	public void setEncargado(String encargado)
	{
		this.encargado = encargado;
	}

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

		this.idUsuarioFmt = null;
		this.idUsuario = null;

		this.nombre = null;
		this.apPat = null;
		this.apMat = null;
		this.direccion = null;
		this.numero = null;
		this.dpto = null;
		this.email = null;
		this.fono = null;
		this.celular = null;
		this.fax = null;

		this.opcHabilitado = null;
		this.nombreComuna = null;
		this.nombreCiudad = null;
		this.nombreRegion = null;
		this.nombreHabilitado = null;

		this.regiones = null;
		this.ciudades = null;
		this.comunas = null;
	}

	/**
	 * paellido materno
	 * 
	 * @return
	 */
	public String getApMat()
	{
		return this.apMat;
	}

	/**
	 * paellido materno
	 * 
	 * @param apMat
	 */
	public void setApMat(String apMat)
	{
		this.apMat = apMat;
	}

	/**
	 * apellido paterno
	 * 
	 * @return
	 */
	public String getApPat()
	{
		return this.apPat;
	}

	/**
	 * apellido paterno
	 * 
	 * @param apPat
	 */
	public void setApPat(String apPat)
	{
		this.apPat = apPat;
	}

	/**
	 * celular
	 * 
	 * @return
	 */
	public String getCelular()
	{
		return this.celular;
	}

	/**
	 * celular
	 * 
	 * @param celular
	 */
	public void setCelular(String celular)
	{
		this.celular = celular;
	}

	/**
	 * direccion
	 * 
	 * @return
	 */
	public String getDireccion()
	{
		return this.direccion;
	}

	/**
	 * direccion
	 * 
	 * @param direccion
	 */
	public void setDireccion(String direccion)
	{
		this.direccion = direccion;
	}

	/**
	 * departamento
	 * 
	 * @return
	 */
	public String getDpto()
	{
		return this.dpto;
	}

	/**
	 * departamento
	 * 
	 * @param dpto
	 */
	public void setDpto(String dpto)
	{
		this.dpto = dpto;
	}

	/**
	 * correo
	 * 
	 * @return
	 */
	public String getEmail()
	{
		return this.email;
	}

	/**
	 * correo
	 * 
	 * @param email
	 */
	public void setEmail(String email)
	{
		this.email = email;
	}

	/**
	 * fax
	 * 
	 * @return
	 */
	public String getFax()
	{
		return this.fax;
	}

	/**
	 * fax
	 * 
	 * @param fax
	 */
	public void setFax(String fax)
	{
		this.fax = fax;
	}

	/**
	 * telefono
	 * 
	 * @return
	 */
	public String getFono()
	{
		return this.fono;
	}

	/**
	 * telefono
	 * 
	 * @param fono
	 */
	public void setFono(String fono)
	{
		this.fono = fono;
	}

	/**
	 * id usuario
	 * 
	 * @return
	 */
	public String getIdUsuario()
	{
		return this.idUsuario;
	}

	/**
	 * id usuario
	 * 
	 * @param idUsuario
	 */
	public void setIdUsuario(String idUsuario)
	{
		this.idUsuario = idUsuario;
	}

	/**
	 * id usuario fmt
	 * 
	 * @return
	 */
	public String getIdUsuarioFmt()
	{
		return this.idUsuarioFmt;
	}

	/**
	 * id usuario fmt
	 * 
	 * @param idUsuarioFmt
	 */
	public void setIdUsuarioFmt(String idUsuarioFmt)
	{
		this.idUsuarioFmt = idUsuarioFmt;
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

	/**
	 * nombre ciudad
	 * 
	 * @return
	 */
	public String getNombreCiudad()
	{
		return this.nombreCiudad;
	}

	/**
	 * nombre ciudad
	 * 
	 * @param nombreCiudad
	 */
	public void setNombreCiudad(String nombreCiudad)
	{
		this.nombreCiudad = nombreCiudad;
	}

	/**
	 * nombre ciudad
	 * 
	 * @return
	 */
	public String getNombreComuna()
	{
		return this.nombreComuna;
	}

	/**
	 * nombre comuna
	 * 
	 * @param nombreComuna
	 */
	public void setNombreComuna(String nombreComuna)
	{
		this.nombreComuna = nombreComuna;
	}

	/**
	 * nombre region
	 * 
	 * @return
	 */
	public String getNombreRegion()
	{
		return this.nombreRegion;
	}

	/**
	 * nombre region
	 * 
	 * @param nombreRegion
	 */
	public void setNombreRegion(String nombreRegion)
	{
		this.nombreRegion = nombreRegion;
	}

	/**
	 * numero
	 * 
	 * @return
	 */
	public String getNumero()
	{
		return this.numero;
	}

	/**
	 * numero
	 * 
	 * @param numero
	 */
	public void setNumero(String numero)
	{
		this.numero = numero;
	}

	/**
	 * opc ciudad
	 * 
	 * @return
	 */
	public int getOpcCiudad()
	{
		return this.opcCiudad;
	}

	/**
	 * opc ciudad
	 * 
	 * @param opcCiudad
	 */
	public void setOpcCiudad(int opcCiudad)
	{
		this.opcCiudad = opcCiudad;
	}

	/**
	 * opc comuna
	 * 
	 * @return
	 */
	public int getOpcComuna()
	{
		return this.opcComuna;
	}

	/**
	 * opc comuna
	 * 
	 * @param opcComuna
	 */
	public void setOpcComuna(int opcComuna)
	{
		this.opcComuna = opcComuna;
	}

	/**
	 * opc region
	 * 
	 * @return
	 */
	public int getOpcRegion()
	{
		return this.opcRegion;
	}

	/**
	 * opc region
	 * 
	 * @param opcRegion
	 */
	public void setOpcRegion(int opcRegion)
	{
		this.opcRegion = opcRegion;
	}

	/**
	 * ciudades
	 * 
	 * @return
	 */
	public List getCiudades()
	{
		return this.ciudades;
	}

	/**
	 * ciudades
	 * 
	 * @param ciudades
	 */
	public void setCiudades(List ciudades)
	{
		this.ciudades = ciudades;
	}

	/**
	 * comunas
	 * 
	 * @return
	 */
	public List getComunas()
	{
		return this.comunas;
	}

	/**
	 * comunas
	 * 
	 * @param comunas
	 */
	public void setComunas(List comunas)
	{
		this.comunas = comunas;
	}

	/*
	 * regiones
	 */
	public List getRegiones()
	{
		return this.regiones;
	}

	/**
	 * regiones
	 * 
	 * @param regiones
	 */
	public void setRegiones(List regiones)
	{
		this.regiones = regiones;
	}

	/**
	 * nombre habilitado
	 * 
	 * @return
	 */
	public String getNombreHabilitado()
	{
		return this.nombreHabilitado;
	}

	/**
	 * nombre habilitado
	 * 
	 * @param nombreHabilitado
	 */
	public void setNombreHabilitado(String nombreHabilitado)
	{
		this.nombreHabilitado = nombreHabilitado;
	}

	/**
	 * opc habilitado
	 * 
	 * @return
	 */
	public String getOpcHabilitado()
	{
		return this.opcHabilitado;
	}

	/**
	 * opc habilitado
	 * 
	 * @param opcHabilitado
	 */
	public void setOpcHabilitado(String opcHabilitado)
	{
		this.opcHabilitado = opcHabilitado;
	}

	/**
	 * admin
	 * 
	 * @return
	 */
	public boolean isAdmin()
	{
		return this.admin;
	}

	/**
	 * admin
	 * 
	 * @param admin
	 */
	public void setAdmin(boolean admin)
	{
		this.admin = admin;
	}

	/**
	 * id grupo convenio
	 * 
	 * @return
	 */
	public String getIdGrupoConvenio()
	{
		return this.idGrupoConvenio;
	}

	/**
	 * id grupo convenio
	 * 
	 * @param idGrupoConvenio
	 */
	public void setIdGrupoConvenio(String idGrupoConvenio)
	{
		this.idGrupoConvenio = idGrupoConvenio;
	}

	/**
	 * nombre grupo convenio
	 * 
	 * @return
	 */
	public String getNombreGrupoConvenio()
	{
		return this.nombreGrupoConvenio;
	}

	/**
	 * nombre grupo convenio
	 * 
	 * @param nombreGrupoConvenio
	 */
	public void setNombreGrupoConvenio(String nombreGrupoConvenio)
	{
		this.nombreGrupoConvenio = nombreGrupoConvenio;
	}

	/**
	 * id empresa
	 * 
	 * @return
	 */
	public String getIdEmpresa()
	{
		return this.idEmpresa;
	}

	/**
	 * id empresa
	 * 
	 * @param idEmpresa
	 */
	public void setIdEmpresa(String idEmpresa)
	{
		this.idEmpresa = idEmpresa;
	}

	/**
	 * nombre empresa
	 * 
	 * @return
	 */
	public String getNombreEmpresa()
	{
		return this.nombreEmpresa;
	}

	/**
	 * nombre empresa
	 * 
	 * @param nombreEmpresa
	 */
	public void setNombreEmpresa(String nombreEmpresa)
	{
		this.nombreEmpresa = nombreEmpresa;
	}

	/**
	 * id empresa admin
	 * 
	 * @return
	 */
	public String getIdEmpresaAdmin()
	{
		return this.idEmpresaAdmin;
	}

	/**
	 * id empresa admin
	 * 
	 * @param idEmpresaAdmin
	 */
	public void setIdEmpresaAdmin(String idEmpresaAdmin)
	{
		this.idEmpresaAdmin = idEmpresaAdmin;
	}

	/**
	 * nombre empresa admin
	 * 
	 * @return
	 */
	public String getNombreEmpresaAdmin()
	{
		return this.nombreEmpresaAdmin;
	}

	/**
	 * nombre empresa admin
	 * 
	 * @param nombreEmpresaAdmin
	 */
	public void setNombreEmpresaAdmin(String nombreEmpresaAdmin)
	{
		this.nombreEmpresaAdmin = nombreEmpresaAdmin;
	}

	/**
	 * admin sistema araucana
	 * 
	 * @return
	 */
	public boolean isAdminSistemaAraucana()
	{
		return this.adminSistemaAraucana;
	}

	/**
	 * admin sistema araucana
	 * 
	 * @param adminSistemaAraucana
	 */
	public void setAdminSistemaAraucana(boolean adminSistemaAraucana)
	{
		this.adminSistemaAraucana = adminSistemaAraucana;
	}

	/**
	 * confirmacion password
	 * 
	 * @return
	 */
	public String getConfPassword()
	{
		return this.confPassword;
	}

	/**
	 * confirmacion password
	 * 
	 * @param confPassword
	 */
	public void setConfPassword(String confPassword)
	{
		this.confPassword = confPassword;
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
	 * codigo fax
	 * 
	 * @return
	 */
	public String getCodigoFax()
	{
		return this.codigoFax;
	}

	/**
	 * codigo fax
	 * 
	 * @param codigoFax
	 */
	public void setCodigoFax(String codigoFax)
	{
		this.codigoFax = codigoFax;
	}

	/**
	 * codigo telefono
	 * 
	 * @return
	 */
	public String getCodigoFono()
	{
		return this.codigoFono;
	}

	/**
	 * codigo telefono
	 * 
	 * @param codigoFono
	 */
	public void setCodigoFono(String codigoFono)
	{
		this.codigoFono = codigoFono;
	}

	/**
	 * nuevos permisis
	 * 
	 * @return
	 */
	public List getNewPermisos()
	{
		return this.newPermisos;
	}

	/**
	 * nuevos permisos
	 * 
	 * @param newPermisos
	 */
	public void setNewPermisos(List newPermisos)
	{
		this.newPermisos = newPermisos;
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
	 * isTipoAdminEmpresa
	 * 
	 * @param 
	 */
	public boolean isTipoAdminEmpresa() {
		return tipoAdminEmpresa;
	}

	/**
	 * setTipoAdminEmpresa
	 * 
	 * @param tipoAdminEmpresa
	 */
	public void setTipoAdminEmpresa(boolean tipoAdminEmpresa) {
		this.tipoAdminEmpresa = tipoAdminEmpresa;
	}
	/**
	 * isTipoAdminIndependiente
	 * 
	 * @param 
	 */
	public boolean isTipoAdminIndependiente() {
		return tipoAdminIndependiente;
	}
	/**
	 * setTipoAdminIndependiente
	 * 
	 * @param tipoAdminIndependiente
	 */
	public void setTipoAdminIndependiente(boolean tipoAdminIndependiente) {
		this.tipoAdminIndependiente = tipoAdminIndependiente;
	}
	
	
}
