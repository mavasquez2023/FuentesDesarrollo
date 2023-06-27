package cl.araucana.cp.presentation.mgr;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;

import cl.araucana.cp.distribuidor.base.Constants;
import cl.araucana.cp.distribuidor.hibernate.beans.ConvenioVO;
import cl.araucana.cp.distribuidor.hibernate.beans.PersonaVO;
import cl.araucana.cp.distribuidor.hibernate.exceptions.DaoException;
import cl.araucana.cp.hibernate.dao.EmpresaDAO;
import cl.araucana.cp.hibernate.dao.LectorDAO;
import cl.araucana.cp.hibernate.dao.PersonaDAO;

/*
 * @(#) LectorMgr.java 1.1 10/05/2009
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
public class LectorMgr
{
	private static Log logger = LogFactory.getLog(LectorMgr.class);
	static final Integer int1 = new Integer(1);
	private EmpresaDAO empresaDao;
	private PersonaDAO personaDao;
	private LectorDAO lectorDao;

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
	 * 
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
	 * 
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
	 * 
	 * @param idLectorEmpresa
	 * @param idEmpresa
	 * @return
	 * @throws DaoException
	 */
	public boolean existeEmpresa(int idLectorEmpresa, int idEmpresa) throws DaoException
	{
		return this.lectorDao.existeEmpresa(idLectorEmpresa, idEmpresa);
	}

	/**
	 * existe grupo
	 * 
	 * @param idLectorEmpresa
	 * @param idGrupoConvenio
	 * @return
	 * @throws DaoException
	 */
	public boolean existeGrupo(int idLectorEmpresa, int idGrupoConvenio) throws DaoException
	{
		return this.lectorDao.existeGrupo(idLectorEmpresa, idGrupoConvenio);
	}

	/**
	 * existe sucursal
	 * 
	 * @param idLectorEmpresa
	 * @param idEmpresa
	 * @param idConvenio
	 * @param idSucursal
	 * @return
	 * @throws DaoException
	 */
	public boolean existeSucursal(int idLectorEmpresa, int idEmpresa, int idConvenio, String idSucursal) throws DaoException
	{
		return this.lectorDao.existeSucursal(idLectorEmpresa, idEmpresa, idConvenio, idSucursal);
	}

	/**
	 * convenios
	 * 
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
			if ((convenio.getHabilitado() == Constants.COD_HABILITACION_CONVENIO)
					&& (this.empresaDao.getEmpresa(convenio.getIdEmpresa()).getHabilitada().intValue() == Constants.COD_HABILITACION_EMPRESA))
				retList.add(convenio);
		}
		return retList;
	}

	/**
	 * persona
	 * 
	 * @param login
	 * @return
	 * @throws DaoException
	 */
	public PersonaVO getPersona(String login) throws DaoException
	{
		logger.info("getPersona Mgr " + login);
		return this.personaDao.getPersona(login);
	}

	/**
	 * guarda sucursal
	 * 
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
