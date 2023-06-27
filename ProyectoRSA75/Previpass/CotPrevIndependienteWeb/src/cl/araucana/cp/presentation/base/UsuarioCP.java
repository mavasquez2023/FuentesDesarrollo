package cl.araucana.cp.presentation.base;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Transformer;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import cl.araucana.core.util.Array;
import cl.araucana.cp.distribuidor.base.Constants;
import cl.araucana.cp.distribuidor.base.Utils;
import cl.araucana.cp.distribuidor.hibernate.beans.ConvenioVO;
import cl.araucana.cp.distribuidor.hibernate.beans.EmpresaVO;
import cl.araucana.cp.distribuidor.hibernate.beans.EncargadoConvenioVO;
import cl.araucana.cp.distribuidor.hibernate.beans.PersonaVO;
import cl.araucana.cp.distribuidor.hibernate.exceptions.DaoException;
import cl.araucana.cp.distribuidor.hibernate.exceptions.UsuarioNoEncontradoException;
import cl.araucana.cp.presentation.mgr.UsuarioMgr;
import cl.araucana.cp.seguridad.PermisoEmpresaConvenio;
/*
* @(#) Usuario.java 1.14 10/05/2009
*
* Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
* La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
* restringido a los sistemas de información propios de la institución.
*/
/**
 * @author ccostagliola
 * @author cchamblas
 * 
 * @version 1.14
 */
public class UsuarioCP extends Usuario
{
	private int numConLector = 0;
	private Map credenciales;
	private Set empresasAdmin; //idEmpresa
	private Set conveniosEditor; //PermisoConvEmpSuc
	private Set unionEmpLectura = new HashSet(); //idEmpresa
	private Set unionEmpEscritura = new HashSet(); //idEmpresa
	/**
	 * usuario cp
	 * @param l
	 */
	public UsuarioCP(String l) 
	{
		super(l);
	}
	/**
	 * usuario cp
	 * @param l
	 * @param session
	 * @throws UsuarioNoEncontradoException
	 */
	public UsuarioCP(String l, Session session)
	{
		super(l, session);
	}
	/**
	 * id persona
	 * @return
	 */
	public int getIdPersona() {
		return Integer.parseInt(this.login);
	}
	/**
	 * usuario cp
	 * @param credenciales
	 * @param session
	 * @throws UsuarioNoEncontradoException
	 * @throws DaoException 
	 */
	public UsuarioCP(Map credenciales, Session session) throws UsuarioNoEncontradoException, DaoException 
	{
		super((String) credenciales.get("login"), session);

		this.credenciales = credenciales;
		iniciarSeguridad(session);
	}
	/**
	 * iniciar seguridad
	 * @param sesion
	 * @throws UsuarioNoEncontradoException
	 * @throws DaoException 
	 */
	public void iniciarSeguridad(Session sesion) throws UsuarioNoEncontradoException, DaoException 
	{
		//Id persona
		PersonaVO persona = new UsuarioMgr(sesion).getPersona(this.login);
		Integer idPersona;
		if (persona != null)
			idPersona = persona.getIdPersona();
		else
			throw new UsuarioNoEncontradoException("No se encontro el usuario con login: " + this.login);

		//Empresas administradas
		List empresas = sesion.createCriteria(EmpresaVO.class)
							  .add(Restrictions.eq("idAdmin", new Long(idPersona.longValue())))
							  .add(Restrictions.eq("habilitada", new Integer(Constants.COD_HABILITACION_EMPRESA)))
							  .add(Restrictions.eq("tipo", Constants.TIPO_EMPRESA_INDEPENDIENTE))
							  .list();
		for (Iterator it = empresas.iterator(); it.hasNext();)
			this.unionEmpEscritura.add(new Integer(((EmpresaVO)it.next()).getIdEmpresa()));

		this.empresasAdmin = new HashSet(CollectionUtils.collect(sesion.createCriteria(EmpresaVO.class)
			.add(Restrictions.eq("idAdmin", new Long(idPersona.longValue())))
			.add(Restrictions.eq("tipo", Constants.TIPO_EMPRESA_INDEPENDIENTE))
			.list(), new Transformer() {
						public Object transform(Object input) {
							return new Integer(((EmpresaVO) input).getIdEmpresa());
						}
					}));

		//Convenios Edicion
		HashMap empsTmp = new HashMap();
		HashMap convsTmp = new HashMap();
		this.conveniosEditor = new HashSet();
		List convEditor = sesion.createCriteria(EncargadoConvenioVO.class)
								.add(Restrictions.eq("idEncargado", new Integer(idPersona.intValue())))
								.add(Restrictions.eq("idNivelAcceso", new Integer(Constants.NIVEL_ACC_CONV_SUC_EDITOR)))
								.list();
		
		agregaHabilitados(true, sesion, empsTmp, convsTmp, convEditor, this.unionEmpEscritura);

		//Convenios Lector
		this.unionEmpLectura.addAll(this.unionEmpEscritura);
		List convLector = sesion.createCriteria(EncargadoConvenioVO.class)
								.add(Restrictions.eq("idEncargado", new Integer(idPersona.intValue())))
								.add(Restrictions.eq("idNivelAcceso", new Integer(Constants.NIVEL_ACC_CONV_SUC_LECTOR)))
								.list();
		agregaHabilitados(false, sesion, empsTmp, convsTmp, convLector, this.unionEmpLectura);
	}

	public void agregaHabilitados(boolean flag, Session sesion, HashMap empsTmp, HashMap convsTmp, List convenios, Set listaAdd) 
	{
		for (Iterator it = convenios.iterator(); it.hasNext();)
		{
			EncargadoConvenioVO enc = (EncargadoConvenioVO)it.next();
			boolean empHabilitada = false;
			if (!empsTmp.containsKey("" + enc.getIdEmpresa()))
			{
				EmpresaVO emp = (EmpresaVO)sesion.createCriteria(EmpresaVO.class)
													.add(Restrictions.eq("idEmpresa", new Integer(enc.getIdEmpresa())))
													.add(Restrictions.eq("tipo", Constants.TIPO_EMPRESA_INDEPENDIENTE)).uniqueResult();
				if (emp != null)
				{
					empsTmp.put("" + enc.getIdEmpresa(), emp.getHabilitada());
					if (emp.getHabilitada().intValue() == Constants.COD_HABILITACION_EMPRESA)
						empHabilitada = true;
				}
			} else if (((Integer)empsTmp.get("" + enc.getIdEmpresa())).intValue() == Constants.COD_HABILITACION_EMPRESA)
				empHabilitada = true;
			if (empHabilitada)
			{
				listaAdd.add(new Integer(enc.getIdEmpresa()));
				if (!convsTmp.containsKey("" + enc.getIdEmpresa() + "#" + enc.getIdConvenio()))
				{
					ConvenioVO conv = (ConvenioVO)sesion.get(ConvenioVO.class, new ConvenioVO(enc.getIdEmpresa(), enc.getIdConvenio()));
					if (conv != null)
						convsTmp.put("" + enc.getIdEmpresa() + "#" + enc.getIdConvenio(), new Integer(conv.getHabilitado()));
					if (conv != null && conv.getHabilitado() == Constants.COD_HABILITACION_CONVENIO)
					{
						if (flag)
							this.conveniosEditor.add(new PermisoEmpresaConvenio(enc.getIdEmpresa(), enc.getIdConvenio()));
						else 
							this.numConLector++;
					}
				} else if (((Integer)convsTmp.get("" + enc.getIdEmpresa() + "#" + enc.getIdConvenio())).intValue() == Constants.COD_HABILITACION_CONVENIO)
				{
					if (flag)
						this.conveniosEditor.add(new PermisoEmpresaConvenio(enc.getIdEmpresa(), enc.getIdConvenio()));
					else 
						this.numConLector++;
				}
			}
		}
	}

	/**
	 * gredenciales
	 * @return
	 */
	public Map getCredenciales()
	{		
		return this.credenciales;
	}
	/**
	 * credenciales
	 * @param credenciales
	 */
	public void setCredenciales(Map credenciales)
	{
		
		this.credenciales = credenciales;
	}
	/**
	 * rol
	 * @return
	 */
	public String getRol() 
	{
		
		return (String) this.credenciales.get("rol");
	}
	/**
	 * usuario cp credenciales
	 */
	public String toString() 
	{
		String s = "UsuarioCP[credenciales: " + this.credenciales.toString() + ",\n";
		
		s += "empresasAdmin:\n\t";
		Integer idEmpresa;
		int i = 1;
		for (Iterator it = this.empresasAdmin.iterator(); it.hasNext();) 
		{
			idEmpresa = (Integer) it.next();
			s += Utils.formatRut(idEmpresa.intValue()) + ",";
			if (i++ % 6 == 0)
				s += "\n\t";
			else
				s += " ";
		}

		s += "\nconveniosLector:\n";
		PermisoEmpresaConvenio perm;

		s += "\nconveniosEditor:\n";
		for (Iterator it = this.conveniosEditor.iterator(); it.hasNext();)
		{
			perm = (PermisoEmpresaConvenio) it.next();
			s += "\t" + perm.toString() + "\n";
		}

		s += "\nunionEmpresasLectura:\n";
		for (Iterator it = this.unionEmpLectura.iterator(); it.hasNext();) 
			s += "\t" + ((Integer) it.next()).intValue() + "\n";

		s += "\nunionEmpresasEscritura:\n";
		for (Iterator it = this.unionEmpEscritura.iterator(); it.hasNext();) 
			s += "\t" + ((Integer) it.next()).intValue() + "\n";

		return s;
	}
	/**
	 * convenios editor
	 * @return
	 */
	public Set getConveniosEditor()
	{
		return this.conveniosEditor;
	}
	/**
	 * empresa admin
	 * @return
	 */
	public Set getEmpresasAdmin()
	{
		return this.empresasAdmin;
	}

	public boolean isEmpresaAdministrada(int rutBuscado)
	{
		for (Iterator it = this.empresasAdmin.iterator(); it.hasNext();) 
		{
			int idEmpresa = ((Integer) it.next()).intValue();
			if (idEmpresa == rutBuscado)
				return true;
		}
		return false;
	}
	/**
	 * union empresas lectura
	 * @return
	 */
	public Set getUnionEmpresasLectura() 
	{
		return this.unionEmpLectura;
	}
	/**
	 * union empresas lectura
	 * @return
	 */
	public Set getUnionEmpresasEscritura() 
	{
		return this.unionEmpEscritura;
	}
	/**
	 * empresas convenio editar
	 * @return
	 */
	public Set getEmpresasConveniosEditor() 
	{
		return new HashSet(CollectionUtils.collect(this.conveniosEditor,
			new Transformer() 
		{
				public Object transform(Object input) 
				{
					return new Integer(((PermisoEmpresaConvenio) input).getIdEmpresa());
				}
			}));
	}
	/**
	 * convenios editor ret vo
	 * @return
	 */
	public Set getConveniosEditorRetVO() 
	{
		return new HashSet(CollectionUtils.collect(this.conveniosEditor,
			new Transformer() 
		{
				public Object transform(Object input) 
				{
					PermisoEmpresaConvenio permiso = (PermisoEmpresaConvenio) input;
					return new ConvenioVO(permiso.getIdEmpresa(), permiso.getIdConvenio());
				}
			}));
	}

	public boolean isConvenioEditor(int rutEmpresa, int idConvenio)
	{
		for (Iterator it = this.conveniosEditor.iterator(); it.hasNext();)
		{
			PermisoEmpresaConvenio perm = (PermisoEmpresaConvenio) it.next();
			if (perm.getIdEmpresa() == rutEmpresa && perm.getIdConvenio() == idConvenio)
				return true;
		}
		return false;
	}
	
	/**
	 * union empresa lectura
	 * @return
	 */
	public Set getUnionEmpLectura() 
	{
		return this.unionEmpLectura;
	}
	/**
	 * union empresa lectura
	 * @param unionEmpLectura
	 */
	public void setUnionEmpLectura(Set unionEmpLectura) 
	{
		this.unionEmpLectura = unionEmpLectura;
	}
	/**
	 * union empresa escritura
	 * @return
	 */
	public Set getUnionEmpEscritura() 
	{
		return this.unionEmpEscritura;
	}
	/**
	 * union empresa escritura
	 * @param unionEmpEscritura
	 */
	public void setUnionEmpEscritura(Set unionEmpEscritura) 
	{
		this.unionEmpEscritura = unionEmpEscritura;
	}
	/**
	 * numero con lector
	 * @return
	 */
	public int getNumConLector() 
	{
		return this.numConLector;
	}
	/**
	 * numero con lector
	 * @param numConLector
	 */
	public void setNumConLector(int numConLector) 
	{
		this.numConLector = numConLector;
	}
	/**
	 * main
	 * @param args
	 */
	public static void main(String[] args)
	{
		/*Pattern pp = Pattern.compile("[\\!\\-]");
		String aa = "ss-d!d";
		logger.debug(pp.matcher(aa).replaceAll("A"));
		pp = Pattern.compile("[\\!]");
		logger.debug(pp.matcher(aa).replaceAll("A"));*/
	/*	int factor = 80;
		int lineas = 2024;
		float f = factor / lineas;
		*/
	}
}
