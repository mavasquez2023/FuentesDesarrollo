package cl.araucana.cp.presentation.mgr;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;

import cl.araucana.cp.distribuidor.base.Constants;
import cl.araucana.cp.distribuidor.base.Utils;
import cl.araucana.cp.distribuidor.hibernate.beans.AdministradorVO;
import cl.araucana.cp.distribuidor.hibernate.beans.ConvenioVO;
import cl.araucana.cp.distribuidor.hibernate.beans.EmpresaVO;
import cl.araucana.cp.distribuidor.hibernate.beans.EncargadoConvenioVO;
import cl.araucana.cp.distribuidor.hibernate.beans.EncargadoVO;
import cl.araucana.cp.distribuidor.hibernate.beans.GrupoConvenioVO;
import cl.araucana.cp.distribuidor.hibernate.beans.LectorEmpresaConvenioVO;
import cl.araucana.cp.distribuidor.hibernate.beans.LectorEmpresaEmpresaVO;
import cl.araucana.cp.distribuidor.hibernate.beans.LectorEmpresaGrupoConvenioVO;
import cl.araucana.cp.distribuidor.hibernate.beans.LectorEmpresaSucursalVO;
import cl.araucana.cp.distribuidor.hibernate.beans.LectorEmpresaVO;
import cl.araucana.cp.distribuidor.hibernate.beans.NivelAccConvSucVO;
import cl.araucana.cp.distribuidor.hibernate.beans.PersonaVO;
import cl.araucana.cp.distribuidor.hibernate.exceptions.DaoException;
import cl.araucana.cp.distribuidor.presentation.beans.Empresa;
import cl.araucana.cp.distribuidor.presentation.beans.GrupoConvenio;
import cl.araucana.cp.distribuidor.presentation.beans.PermEncargadoLector;
import cl.araucana.cp.hibernate.dao.ConvenioDAO;
import cl.araucana.cp.hibernate.dao.LectorDAO;
import cl.araucana.cp.hibernate.dao.PersonaDAO;
import cl.araucana.cp.mail.EnviarClave;
import cl.araucana.cp.mail.data.MailClave;
import cl.araucana.cp.utils.ProxyLDAP;

/*
 * @(#) PersonaMgr.java 1.21 10/05/2009
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */
/**
 * @author ccostagliola
 * @author cchamblas
 * 
 * @version 1.21
 */
public class PersonaMgr
{
	private PersonaDAO personaDao;
	private LectorDAO lectorDao;
	private ConvenioDAO convenioDao;
	private static Logger log = Logger.getLogger(PersonaMgr.class);

	public PersonaMgr(Session session)
	{
		this.lectorDao = new LectorDAO(session);
		this.personaDao = new PersonaDAO(session);
		this.convenioDao = new ConvenioDAO(session);
	}

	/**
	 * administrador
	 * 
	 * @param idEncargado
	 * @return
	 * @throws DaoException
	 */
	public AdministradorVO getAdministrador(int idEncargado) throws DaoException
	{
		return this.personaDao.getAdministrador(idEncargado);
	}

	/**
	 * persona
	 * 
	 * @param idPersona
	 * @return
	 * @throws DaoException
	 */
	public PersonaVO getPersona(int idPersona) throws DaoException
	{
		return this.personaDao.getPersona(idPersona);
	}

	/**
	 * lista encargados
	 * 
	 * @param idEmpresa
	 * @param idConvenio
	 * @return
	 * @throws DaoException
	 */
	public List getListaEncargados(int idEmpresa, int idConvenio) throws DaoException
	{
		return this.personaDao.getListaEncargados(idEmpresa, idConvenio);
	}

	/**
	 * encargado
	 * 
	 * @param idEncargado
	 * @return
	 * @throws DaoException
	 */
	public EncargadoVO getEncargado(int idEncargado) throws DaoException
	{
		return this.personaDao.getEncargado(idEncargado);
	}

	/**
	 * guarda encargado
	 * 
	 * @param encargado
	 * @throws DaoException
	 */
	public void guardaEncargado(EncargadoVO encargado) throws DaoException
	{
		this.personaDao.guardaEncargado(encargado);
	}

	/**
	 * permiso encargado lector
	 * 
	 * @param idAdmin
	 * @param idUsuario
	 * @param tipoOperacion
	 * @return
	 * @throws DaoException
	 */
	public List getPermEncLectorAdministrados(int idAdmin, int idUsuario) throws DaoException
	{
		log.info("getPermEncLectorAdministrados: idAdmin:" + idAdmin + ":idUsuario:" + idUsuario + "::");
		List listaGruposAdmin = this.convenioDao.getGruposConveniosAdmin(idAdmin);// lista grupos administrados
		HashMap gruposLector = this.lectorDao.getGruposLector(idUsuario);// hash de grupos que tiene asigando permiso de lector
		HashMap emrpesasLector = this.lectorDao.getEmpresasLector(idUsuario);// hash de empresas que tiene asigando permiso de lector
		HashMap conveniosLector = this.lectorDao.getConveniosLector(idUsuario);// hash de convenios que tiene asigando permiso de lector
		HashMap sucursalesLector = this.lectorDao.getSucsLector(idUsuario);// hash de sucursales que tiene asigando permiso de lector
		HashMap encargadoConvenio = this.personaDao.getConveniosEncargado(idUsuario);// hash de convenios que tiene asigando permiso de encargado
		List listaEmpresasAdmin = this.personaDao.getListaEmpresasAdministradas(idAdmin);
		List niveles = getListaNivelesAcceso();
		HashMap result = new HashMap();
		HashMap hashNiveles = new HashMap();
		for (Iterator it = niveles.iterator(); it.hasNext();)
		{
			NivelAccConvSucVO nivel = (NivelAccConvSucVO) it.next();
			hashNiveles.put("" + nivel.getIdNivelAcceso(), nivel.getDescripcion().trim());
		}

		/*
		 * Obtiene grupos de convenios administrados, genera estructura jerarquica:
		 * 
		 * grupo empresa convenio sucursal
		 * 
		 * si el usuario buscado es nuevo (idUsuario == 0), a todos los convenios por defecto se les da el nivel de acceso "editor" si el usuario buscardo ya existe, se busca en la base de datos los
		 * permisos que ya tiene asignados. se ordenan las listas
		 * 
		 * tipoOperacion: CREAR = 0; EDITAR = 1; FICHA = 2;
		 */
		for (Iterator it = listaEmpresasAdmin.iterator(); it.hasNext();)
		{
			EmpresaVO empresaVO = (EmpresaVO) it.next();
			log.info("empresa:" + empresaVO.getIdEmpresa() + "::");
			List listaConvenios = this.convenioDao.getConveniosEmpresa(true, empresaVO.getIdEmpresa());
			for (Iterator itC = listaConvenios.iterator(); itC.hasNext();)
			{
				ConvenioVO convenio = (ConvenioVO) itC.next();
				GrupoConvenio grupo = null;
				if (result.containsKey("" + convenio.getIdGrupoConvenio()))
					grupo = (GrupoConvenio) result.get("" + convenio.getIdGrupoConvenio());
				else
				{
					String nombre = "";
					for (Iterator itG = listaGruposAdmin.iterator(); itG.hasNext();)
					{
						GrupoConvenioVO grupoVO = (GrupoConvenioVO) itG.next();
						if (grupoVO.getIdGrupoConvenio() == convenio.getIdGrupoConvenio())
						{
							nombre = grupoVO.getNombre();
							break;
						}
					}
					grupo = new GrupoConvenio(convenio.getIdGrupoConvenio(), gruposLector.containsKey("" + convenio.getIdGrupoConvenio()), nombre);
					result.put("" + convenio.getIdGrupoConvenio(), grupo);
				}
				Empresa empresa = grupo.getEmpresa(convenio.getIdEmpresa());
				if (empresa == null)
				{
					empresa = new Empresa(convenio.getIdEmpresa(), emrpesasLector.containsKey("" + convenio.getIdEmpresa()), Utils.formatRut(convenio.getIdEmpresa()), empresaVO.getRazonSocial());
					grupo.addEmpresa(empresa);
				}
				int nivel = (encargadoConvenio.containsKey("" + convenio.getIdEmpresa() + "#" + convenio.getIdConvenio()) ? ((Integer) encargadoConvenio.get("" + convenio.getIdEmpresa() + "#"
						+ convenio.getIdConvenio())).intValue() : 0);
				String sucs = (sucursalesLector.containsKey("" + convenio.getIdEmpresa() + "#" + convenio.getIdConvenio()) ? (String) sucursalesLector.get("" + convenio.getIdEmpresa() + "#"
						+ convenio.getIdConvenio()) : "");

				PermEncargadoLector perm = new PermEncargadoLector(convenio.getIdConvenio(), nivel, getDescNivel(nivel, hashNiveles), conveniosLector.containsKey("" + convenio.getIdEmpresa() + "#"
						+ convenio.getIdConvenio()), sucs);
				empresa.addPermEncargadoLector(perm);
			}
		}
		// ordenamiento resultados
		List tmp = new ArrayList();
		for (Iterator it = result.values().iterator(); it.hasNext();)
		{
			GrupoConvenio gc = (GrupoConvenio) it.next();
			for (Iterator it2 = gc.getEmpresas().iterator(); it2.hasNext();)
			{
				Empresa emp = (Empresa) it2.next();
				Collections.sort(emp.getConvenios());
			}
			Collections.sort(gc.getEmpresas());
			tmp.add(gc);
		}
		Collections.sort(tmp);

		return tmp;
	}

	/**
	 * desc nivel
	 * 
	 * @param id
	 * @param hashNiveles
	 * @return
	 */
	public String getDescNivel(int id, HashMap hashNiveles)
	{
		String key = "" + id;
		return (hashNiveles.containsKey(key) ? (String) hashNiveles.get(key) : "Sin Acceso");
	}

	/**
	 * lista niveles acceso
	 * 
	 * @return
	 * @throws DaoException
	 */
	public List getListaNivelesAcceso() throws DaoException
	{
		return this.personaDao.getListaNivelesAcceso();
	}

	/**
	 * lista encargados
	 * 
	 * @param colEmpAdmins
	 * @return
	 * @throws DaoException
	 */
	public List getListaEncargados(Collection colEmpAdmins) throws DaoException
	{
		return this.personaDao.getListaEncargados(colEmpAdmins);
	}

	/**
	 * Método que lista los Encargados filtrándolos según el criterio que corresponda 
	 * 
	 * @param colEmpAdmins
	 * @param filtros
	 * @return
	 * @throws DaoException
	 */
	public List getListaEncargados(Collection colEmpAdmins, HashMap filtros) throws DaoException
	{
		return this.personaDao.getListaEncargados(colEmpAdmins, filtros);
	}

	
	/**
	 * elimina permisos encargado
	 * 
	 * @param empresas
	 * @param idEncargado
	 * @throws DaoException
	 */
	public void borraPermisosEncargado(Collection empresas, int idEncargado) throws DaoException
	{
		this.personaDao.borraPermisosEncargado(empresas, idEncargado);
	}

	/**
	 * save persona
	 * 
	 * @param persona
	 * @throws DaoException
	 */
	public boolean savePersona(PersonaVO persona, String password) throws DaoException
	{
		this.personaDao.savePersona(persona);
		//TODO descomentar
		return ProxyLDAP.createUser(persona, password);
	}

	/**
	 * guarda acceso encargado lector
	 * 
	 * @param encargado
	 * @param lista
	 * @throws DaoException
	 */
	public void guardaAccesoEncargadoLector(EncargadoVO encargado, List lista,int idAdmin) throws DaoException
	{
		int idUsuario = encargado.getIdEncargado();
		this.personaDao.borraPermEncargado(idUsuario,idAdmin);
		this.lectorDao.borraPermiso(idUsuario, "idLectorEmpresa", LectorEmpresaSucursalVO.class);
		this.lectorDao.borraPermiso(idUsuario, "idLectorEmpresa", LectorEmpresaConvenioVO.class);
		this.lectorDao.borraPermiso(idUsuario, "idLectorEmpresa", LectorEmpresaEmpresaVO.class);
		this.lectorDao.borraPermiso(idUsuario, "idLectorEmpresa", LectorEmpresaGrupoConvenioVO.class);
		log.info("permisos anteriores del usuario:" + idUsuario + ":: borrados exitosamente");

		LectorEmpresaVO lector = new LectorEmpresaVO(idUsuario, encargado.getHabilitado());

		List permsEncargados = new ArrayList();
		guardaPermisos(encargado, lista, idUsuario, lector, permsEncargados);
		this.personaDao.guardaPermEncargado(permsEncargados);
	}

	/**
	 * guarda permisos
	 * 
	 * @param encargado
	 * @param lista
	 * @param idUsuario
	 * @param lector
	 * @param permsEncargados
	 * @throws DaoException
	 */
	private void guardaPermisos(EncargadoVO encargado, List lista, int idUsuario, LectorEmpresaVO lector, List permsEncargados) throws DaoException
	{
		boolean permisoGrupo = false;
		boolean flagEnc = false, flagLector = false;
		for (Iterator it = lista.iterator(); it.hasNext();)
		{
			GrupoConvenio gc = (GrupoConvenio) it.next();
			if (gc.isPermLector())
			{
				if (!flagLector)
				{
					flagLector = true;
					this.lectorDao.guardaLectorEmpresa(lector);
				}
				this.lectorDao.guardaGrupoConvenio(idUsuario, gc.getIdGrupo());
				permisoGrupo = true;
			}
			boolean permisoEmpresa = false;
			for (Iterator itp = gc.getEmpresas().iterator(); itp.hasNext();)
			{
				Empresa empresa = (Empresa) itp.next();
				if (empresa.isPermLector() && !permisoGrupo)
				{
					if (!flagLector)
					{
						flagLector = true;
						this.lectorDao.guardaLectorEmpresa(lector);
					}
					this.lectorDao.guardaEmpresa(idUsuario, empresa.getIdEmpresa());
					permisoEmpresa = true;
				}
				for (Iterator itC = empresa.getConvenios().iterator(); itC.hasNext();)
				{
					PermEncargadoLector perm = (PermEncargadoLector) itC.next();

					if (perm.isConvenioLector() && !permisoGrupo && !permisoEmpresa)
					{
						if (!flagLector)
						{
							flagLector = true;
							this.lectorDao.guardaLectorEmpresa(lector);
						}
						this.lectorDao.guardaConvenio(idUsuario, empresa.getIdEmpresa(), perm.getIdConvenio());
					} else if (perm.getSucursalLector() != null && perm.getSucursalLector().trim().length() > 0 && !permisoGrupo && !permisoEmpresa)
					{
						if (!flagLector)
						{
							flagLector = true;
							this.lectorDao.guardaLectorEmpresa(lector);
						}
						String sucs[] = perm.getSucursalLector().trim().split("#");
						for (int i = 0; i < sucs.length; i++)
							this.lectorDao.guardaSucursal(idUsuario, empresa.getIdEmpresa(), perm.getIdConvenio(), sucs[i].trim());
					}

					if (perm.getIdNivel() != Constants.NIVEL_ACC_CONV_SUC_NADA)
					{
						if (!flagEnc)
						{
							flagEnc = true;
							this.personaDao.guardaEncargado(encargado, true);
						}
						permsEncargados.add(new EncargadoConvenioVO(empresa.getIdEmpresa(), perm.getIdConvenio(), idUsuario, perm.getIdNivel()));
					}
				}
				permisoEmpresa = false;
			}
			permisoGrupo = false;
		}
		if (lista.size() == 0)
			this.personaDao.guardaEncargado(encargado, false);
	}

	/**
	 * Método que lista Empresas filtrándolas según el criterio que corresponda
	 * 
	 * @param col
	 * @param filtros
	 * @return
	 * @throws DaoException
	 */
	public List getListaEmpresas(Collection col, HashMap filtros) throws DaoException
	{
		return this.personaDao.getListaEmpresas(col, filtros);
	}

	/**
	 * lista empresas in
	 * 
	 * @param col
	 * @return
	 * @throws DaoException
	 */
	public List getListaEmpresasIn(Collection col) throws DaoException
	{
		return this.personaDao.getListaEmpresasIn(col);
	}

	/**
	 * admin empresa
	 * 
	 * @param login
	 * @return
	 * @throws DaoException
	 */
	public boolean isAdminEmpresa(String login) throws DaoException
	{
		return this.personaDao.isAdminEmpresa(login);
	}

	/**
	 * encargado empresa
	 * 
	 * @param login
	 * @return
	 * @throws DaoException
	 */
	public boolean isEscargadoEmpresa(String login) throws DaoException
	{
		return this.personaDao.isEscargadoEmpresa(login);
	}
	
	/**
	 * 
	 * @param mailTo
	 * @param fullNombre
	 * @param passwordDefault
	 * @param parametros
	 */
	public void enviarMailClaveInicial(String mailTo, String fullNombre, HashMap parametros, String password)
	{
		if (parametros.containsKey("" + Constants.PARAM_MAIL_HOST_LOCAL) && 
				parametros.containsKey("" + Constants.PARAM_MAIL_FROM) && parametros.containsKey("" + Constants.PARAM_MAIL_USER) && 
				parametros.containsKey("" + Constants.PARAM_MAIL_PASS) &&  parametros.containsKey("" + Constants.PARAM_MAIL_HOST_TO) &&
				parametros.containsKey("" + Constants.PARAM_MAIL_PORT))
		{
			try
			{
				log.info("\n\n\nparametros envio mail con clave:");
				log.info("mailTo:" + mailTo + ":mailHostTo:" + (String) parametros.get("" + Constants.PARAM_MAIL_HOST_TO) + ":mailFrom:" + (String) parametros.get("" + Constants.PARAM_MAIL_FROM) + ":userMail:"
						+ (String) parametros.get("" + Constants.PARAM_MAIL_USER) + ":passMail:" + (String) parametros.get("" + Constants.PARAM_MAIL_PASS) + ":mailHostLocal:" + (String) parametros.get("" + Constants.PARAM_MAIL_HOST_LOCAL) + "::");
				MailClave mail = new MailClave( Integer.parseInt((String)parametros.get("" + Constants.PARAM_MAIL_PORT)),
											mailTo,
											(String)parametros.get("" + Constants.PARAM_MAIL_HOST_TO),
											(String)parametros.get("" + Constants.PARAM_MAIL_FROM),
											(String)parametros.get("" + Constants.PARAM_MAIL_USER),
											(String)parametros.get("" + Constants.PARAM_MAIL_PASS),
											(String)parametros.get("" + Constants.PARAM_MAIL_HOST_LOCAL),
											fullNombre,
											password);


				EnviarClave.enviar(mail);
			} catch (Exception e)
			{
			}
		}
	}	
	
}
