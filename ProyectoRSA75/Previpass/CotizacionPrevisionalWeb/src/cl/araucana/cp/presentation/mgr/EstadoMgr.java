package cl.araucana.cp.presentation.mgr;

import java.util.Collection;

import org.apache.log4j.Logger;
import org.hibernate.Session;

import cl.araucana.cp.distribuidor.hibernate.exceptions.DaoException;
import cl.araucana.cp.hibernate.dao.EstadosDAO;
import cl.araucana.cp.presentation.struts.actions.nomina.ListarAction;

/*
 * @(#) EstadoMgr.java 1.7 10/05/2009
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */
/**
 * @author ccostagliola
 * @author cchamblas
 * 
 * @version 1.7
 */
public class EstadoMgr
{
	private EstadosDAO estadoDao;
	static Logger log = Logger.getLogger(ListarAction.class);

	public EstadoMgr(Session session)
	{
		this.estadoDao = new EstadosDAO(session);
	}

	/**
	 * estados
	 * 
	 * @return
	 * @throws DaoException
	 */
	public Collection getEstados() throws DaoException
	{
		return this.estadoDao.getEstados();
	}
}
