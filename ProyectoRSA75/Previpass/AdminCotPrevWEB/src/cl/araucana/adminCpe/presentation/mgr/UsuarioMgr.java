package cl.araucana.adminCpe.presentation.mgr;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;

import cl.araucana.adminCpe.hibernate.dao.EmpresaDAO;
import cl.araucana.adminCpe.hibernate.dao.PersonaDAO;
import cl.araucana.cp.distribuidor.base.Constants;
import cl.araucana.cp.distribuidor.hibernate.beans.ConvenioVO;
import cl.araucana.cp.distribuidor.hibernate.beans.EmpresaVO;
import cl.araucana.cp.distribuidor.hibernate.beans.PersonaVO;
import cl.araucana.cp.distribuidor.hibernate.exceptions.DaoException;
import cl.araucana.cp.distribuidor.hibernate.exceptions.UsuarioNoEncontradoException;

import com.bh.talon.User;
/*
* @(#) UsuarioMgr.java 1.5 10/05/2009
*
* Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
* La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
* restringido a los sistemas de información propios de la institución.
*/
/**
 * @author ccostagliola
 * @author cchamblas
 * 
 * @version 1.5
 */
public class UsuarioMgr
{
	private EmpresaDAO empresaDao;
	private PersonaDAO personaDao;
	static final Integer int1 = new Integer(1);
	static Logger log = Logger.getLogger(UsuarioMgr.class);

	public UsuarioMgr()
	{}

	public UsuarioMgr(Session session)
	{
		this.empresaDao = new EmpresaDAO(session);
		this.personaDao = new PersonaDAO(session);
	}

	/**
	 * persona login
	 * @param login
	 * @return
	 * @throws DaoException
	 * @throws UsuarioNoEncontradoException
	 */
	public PersonaVO getPersonaLogin(Object login) throws DaoException 
	{
		return this.personaDao.getPersona(login);
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
	 * @param rutEmpresa
	 * @return
	 * @throws DaoException
	 */
	public Collection getConvenios(User usuarioID, int rutEmpresa) throws DaoException
	{
		log.debug("Entro a buscar login: " + usuarioID.getLogin());
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
	 * @param usuarioID
	 * @return
	 * @throws DaoException
	 */
	public Collection getEmpresas(User usuarioID) throws DaoException
	{
		return this.getEmpresas(usuarioID.getLogin());
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
}
