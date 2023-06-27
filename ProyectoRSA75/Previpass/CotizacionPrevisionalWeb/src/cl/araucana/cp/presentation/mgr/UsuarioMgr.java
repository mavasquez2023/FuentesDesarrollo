package cl.araucana.cp.presentation.mgr;

import org.apache.log4j.Logger;
import org.hibernate.Session;

import cl.araucana.cp.distribuidor.hibernate.beans.PersonaVO;
import cl.araucana.cp.distribuidor.hibernate.exceptions.DaoException;
import cl.araucana.cp.distribuidor.hibernate.exceptions.UsuarioNoEncontradoException;
import cl.araucana.cp.hibernate.dao.ConvenioDAO;
import cl.araucana.cp.hibernate.dao.PersonaDAO;

/*
 * @(#) UsuarioMgr.java 1.20 10/05/2009
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */
/**
 * @author pfrigolett
 * @author cchamblas
 * 
 * @version 1.20
 */
public class UsuarioMgr
{
	static Logger logger = Logger.getLogger(UsuarioMgr.class);
	private PersonaDAO personaDao;
	private ConvenioDAO convenioDao;

	public UsuarioMgr(Session session)
	{
		this.personaDao = new PersonaDAO(session);
		this.convenioDao = new ConvenioDAO(session);
	}

	/**
	 * persona login
	 * 
	 * @param login
	 * @return
	 * @throws DaoException
	 * @throws UsuarioNoEncontradoException
	 */
	public PersonaVO getPersona(String login) throws DaoException
	{
		logger.info("UsuarioMgr::getPersona:" + login + "::");
		return this.personaDao.getPersona(login);
	}

	/**
	 * persona
	 * 
	 * @param id
	 * @return
	 * @throws DaoException
	 */
	public PersonaVO getPersona(int id) throws DaoException
	{
		return this.personaDao.getPersona(id);
	}

	public int getNivelPermiso(int idPersona, int idConvenio, int rutEmpresa)
	{
		return this.convenioDao.getNivelPermiso(idPersona, idConvenio, rutEmpresa);
	}
}
