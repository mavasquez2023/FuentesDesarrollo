package cl.araucana.adminCpe.presentation.mgr;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;

import cl.araucana.adminCpe.hibernate.dao.EmpresaDAO;
import cl.araucana.adminCpe.hibernate.dao.LectorDAO;
import cl.araucana.adminCpe.hibernate.dao.PersonaDAO;
import cl.araucana.cp.distribuidor.base.Constants;
import cl.araucana.cp.distribuidor.hibernate.beans.ConvenioVO;
import cl.araucana.cp.distribuidor.hibernate.beans.EmpresaVO;
import cl.araucana.cp.distribuidor.hibernate.beans.LectorEmpresaVO;
import cl.araucana.cp.distribuidor.hibernate.beans.PersonaVO;
import cl.araucana.cp.distribuidor.hibernate.exceptions.DaoException;

import com.bh.talon.User;
/*
* @(#) LectorMgr.java 1.5 10/05/2009
*
* Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
* La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
* restringido a los sistemas de información propios de la institución.
*/
/**
 * @author jdelgado
 * @author cchamblas
 * 
 * @version 1.5
 */
public class LectorMgr
{
	static final Integer int1 = new Integer(1);
	private EmpresaDAO empresaDao;
	private PersonaDAO personaDao;
	private LectorDAO lectorDao;
	static Logger log = Logger.getLogger(LectorMgr.class);

	public LectorMgr()
	{
		super();
	}

	public LectorMgr(Session session)
	{
		this.empresaDao = new EmpresaDAO(session);
		this.personaDao = new PersonaDAO(session);
		this.lectorDao = new LectorDAO(session);
	}

	/**
	 * eliminar sucursal
	 * @param idLectorEmpresa
	 * @param rutEmpresa
	 * @param idConvenio
	 * @param idSucursal
	 * @throws DaoException
	 */
	public void borraSucursal(int idLectorEmpresa, int rutEmpresa, int idConvenio, String idSucursal) throws DaoException 
	{
		this.lectorDao.borraSucursal(idLectorEmpresa, rutEmpresa, idConvenio, idSucursal);
	}

	/**
	 * existe convenio
	 * @param idLectorEmpresa
	 * @param idEmpresa
	 * @param idConvenio
	 * @return
	 * @throws DaoException
	 */
	public boolean existeConvenio(int idLectorEmpresa, int idEmpresa, int idConvenio) throws DaoException
	{
		return this.lectorDao.existeConvenio(idLectorEmpresa, idEmpresa, idConvenio);
	}

	/**
	 * existe empresa
	 * @param idLectorEmpresa
	 * @param idEmpresa
	 * @return
	 * @throws DaoException
	 */
	public boolean existeEmpresa(int idLectorEmpresa, int idEmpresa) throws DaoException {
		return this.lectorDao.existeEmpresa(idLectorEmpresa, idEmpresa);
	}
	/**
	 * existe grupo
	 * @param idLectorEmpresa
	 * @param idGrupoConvenio
	 * @return
	 * @throws DaoException
	 */
	public boolean existeGrupo(int idLectorEmpresa, int idGrupoConvenio) throws DaoException {
		return this.lectorDao.existeGrupo(idLectorEmpresa, idGrupoConvenio); 
	}
	/**
	 * existe sucursal
	 * @param idLectorEmpresa
	 * @param idEmpresa
	 * @param idConvenio
	 * @param idSucursal
	 * @return
	 * @throws DaoException
	 */
	public boolean existeSucursal(int idLectorEmpresa, int idEmpresa, int idConvenio, String idSucursal) throws DaoException{
		return this.lectorDao.existeSucursal(idLectorEmpresa, idEmpresa,idConvenio, idSucursal);
	}
	/**
	 * collection convenios
	 * @param login
	 * @return
	 * @throws DaoException
	 */
	public Collection getConvenios(String login) throws DaoException
	{
		List retList = new ArrayList();
		ConvenioVO convenio;
		for (Iterator it = this.getPersona(login).getConvenios().iterator(); it.hasNext();)
		{
			convenio = (ConvenioVO) it.next();
			if ((convenio.getHabilitado() == Constants.COD_HABILITACION_CONVENIO) && (this.empresaDao.getEmpresa(convenio.getIdEmpresa()).getHabilitada().intValue() == Constants.COD_HABILITACION_EMPRESA))
				retList.add(convenio);
		}
		return retList;
	}
	/**
	 * collection convenios
	 * @param usuarioID
	 * @return
	 * @throws DaoException
	 */
	public Collection getConvenios(User usuarioID) throws DaoException
	{
		return this.getConvenios(usuarioID.getLogin());
	}
	/**
	 * collection convenios
	 * @param usuarioID
	 * @param rutEmpresa
	 * @return
	 * @throws DaoException
	 */
	public Collection getConvenios(User usuarioID, int rutEmpresa) throws DaoException
	{
		log.info("Entro a buscar login: " + usuarioID.getLogin());
		List retList = new ArrayList();
		ConvenioVO convenio;
		for (Iterator it = this.getPersona(usuarioID.getLogin()).getConvenios().iterator(); it.hasNext();)
		{
			convenio = (ConvenioVO) it.next();
			if ((convenio.getIdEmpresa() == rutEmpresa) && (convenio.getHabilitado() == Constants.COD_HABILITACION_CONVENIO)
					&& (this.empresaDao.getEmpresa(convenio.getIdEmpresa()).getHabilitada().intValue() == Constants.COD_HABILITACION_EMPRESA))
				retList.add(convenio);
		}
		return retList;
	}
	/**
	 * collection empresas
	 * @param login
	 * @return
	 * @throws DaoException
	 */
	public Collection getEmpresas(String login) throws DaoException
	{
		List retList = new ArrayList();
		EmpresaVO empresa;
		for (Iterator it = this.getPersona(login).getEmpresas().iterator(); it.hasNext();)
		{
			empresa = (EmpresaVO) it.next();
			if (empresa.getHabilitada().intValue() == Constants.COD_HABILITACION_EMPRESA)
				retList.add(empresa);
		}
		return retList;
	}
	/**
	 * collection empresas
	 * @param usuarioID
	 * @return
	 * @throws DaoException
	 */
	public Collection getEmpresas(User usuarioID) throws DaoException
	{
		return this.getEmpresas(usuarioID.getLogin());
	}

	/**
	 * lector empresa
	 * @param idLectorEmpresa
	 * @return
	 * @throws Exception
	 */
	public LectorEmpresaVO getLectorEmpresa(int idLectorEmpresa) throws Exception 
	{
		return this.lectorDao.getLectorEmpresa(idLectorEmpresa);
	}

	/**
	 * nivel acceso
	 * @param idEmpresa
	 * @param idConvenio
	 * @param idEncargado
	 * @return
	 * @throws Exception
	 */
	public int getNivelAcceso(int idEmpresa, int idConvenio, int idEncargado) throws Exception 
	{
		return this.lectorDao.getNivelAcceso(idEmpresa, idConvenio, idEncargado);
	}

	/**
	 * persona
	 * @param login
	 * @return
	 * @throws DaoException
	 */
	public PersonaVO getPersona(Object login) throws DaoException
	{
		log.debug("getPersona Mgr " + login);
		return this.personaDao.getPersona(login);
	}

	/**
	 * guarda convenio
	 * @param idLectorEmpresa
	 * @param rutEmpresa
	 * @param idConvenio
	 * @throws Exception
	 */
	public void guardaConvenio(int idLectorEmpresa, int rutEmpresa, int idConvenio) throws Exception 
	{
		this.lectorDao.guardaConvenio(idLectorEmpresa, rutEmpresa, idConvenio);
	}
	/**
	 * guarda empresa
	 * @param idLectorEmpresa
	 * @param rutEmpresa
	 * @throws Exception
	 */
	public void guardaEmpresa(int idLectorEmpresa, int rutEmpresa) throws Exception 
	{
		this.lectorDao.guardaEmpresa(idLectorEmpresa, rutEmpresa);
	}
	/**
	 * guarda grupo convenio
	 * @param idLectorEmpresa
	 * @param idGrupoConvenio
	 * @throws Exception
	 */
	public void guardaGrupoConvenio(int idLectorEmpresa, int idGrupoConvenio) throws Exception 
	{
		this.lectorDao.guardaGrupoConvenio(idLectorEmpresa, idGrupoConvenio);
	}
	/**
	 * guarda lector empresa
	 * @param lectorEmpresaVO
	 * @throws DaoException
	 */
	public void guardaLectorEmpresa(LectorEmpresaVO lectorEmpresaVO) throws DaoException 
	{
		this.lectorDao.guardaLectorEmpresa(lectorEmpresaVO);
	}
	/**
	 * guarda nivel acceso
	 * @param idLectorEmpresa
	 * @param rutEmpresa
	 * @param idConvenio
	 * @param idNivelAcceso
	 * @throws Exception
	 */
	public void guardaNivelAcceso(int idLectorEmpresa, int rutEmpresa, int idConvenio, int idNivelAcceso) throws Exception 
	{
		this.lectorDao.guardaNivelAcceso(idLectorEmpresa, rutEmpresa, idConvenio, idNivelAcceso);
	}
	/**
	 * guarda sucursal
	 * @param idLectorEmpresa
	 * @param rutEmpresa
	 * @param idConvenio
	 * @param idSucursal
	 * @throws Exception
	 */
	public void guardaSucursal(int idLectorEmpresa, int rutEmpresa, int idConvenio, String idSucursal) throws Exception 
	{
		this.lectorDao.guardaSucursal(idLectorEmpresa, rutEmpresa, idConvenio, idSucursal);
	}
}
