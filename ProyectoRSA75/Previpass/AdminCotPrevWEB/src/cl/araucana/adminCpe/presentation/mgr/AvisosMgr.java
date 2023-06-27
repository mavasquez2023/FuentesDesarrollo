package cl.araucana.adminCpe.presentation.mgr;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;

import cl.araucana.adminCpe.hibernate.dao.AvisosDAO;
import cl.araucana.cp.distribuidor.hibernate.beans.AvisosVO;
import cl.araucana.cp.distribuidor.hibernate.exceptions.DaoException;
/*
* @(#) AvisoMgr.java 1.4 10/05/2009
*
* Este c�digo fuente pertenece a la Caja de Compensaci�n de Asignaci�n Familiar
* La Araucana (C.C.A.F.). Su utilizaci�n y reproducci�n es confidencial y est�
* restringido a los sistemas de informaci�n propios de la instituci�n.
*/
/**
 * @author vagurto
 * @author cchamblas
 * 
 * @version 1.4
 */
public class AvisosMgr
{
	private AvisosDAO avisosDAO;
	
	static Logger log = Logger.getLogger(AvisosMgr.class);

	public AvisosMgr(Session session) 
	{
		this.avisosDAO = new AvisosDAO(session);
	}
	/**
	 * lista avisos
	 * @return
	 * @throws DaoException
	 */
	public List getAvisos() throws DaoException 
	{
		return this.avisosDAO.getAvisos();
	}
	
	/**
	 * aviso
	 * @param id
	 * @return
	 * @throws DaoException
	 */
	public AvisosVO getAviso(int id) throws DaoException 
	{
		return this.avisosDAO.getAviso(id);
	}	
	
	/**
	 * guardar aviso
	 * @param avisoVO
	 * @throws DaoException
	 */
	public void guardarAviso(AvisosVO avisoVO)throws DaoException
	{
		this.avisosDAO.guardarAviso(avisoVO);
	}
}
