package cl.araucana.adminCpe.presentation.mgr;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;

import cl.araucana.adminCpe.hibernate.dao.SeccionDAO;
import cl.araucana.cp.distribuidor.hibernate.beans.DetalleSeccionVO;
import cl.araucana.cp.distribuidor.hibernate.exceptions.DaoException;
/*
* @(#) SeccionMgr.java 1.2 10/05/2009
*
* Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
* La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
* restringido a los sistemas de información propios de la institución.
*/
/**
 * @author jdelgado
 * 
 * @version 1.2
 */
public class SeccionMgr
{
	private SeccionDAO seccionDAO;
	
	static Logger log = Logger.getLogger(SeccionMgr.class);

	public SeccionMgr(Session session)
	{
		this.seccionDAO = new SeccionDAO(session);
	}

	/**
	 * lista secciones
	 * @param idCodigoBarra
	 * @return
	 * @throws DaoException
	 */
	public List getSecciones(long idCodigoBarra) throws DaoException
	{
		return this.seccionDAO.getSecciones(idCodigoBarra);
	}
	/**
	 * lista detalle secciones
	 * @param tipoProceso
	 * @return
	 * @throws DaoException
	 */
	public List getDetalleSecciones(int tipoProceso) throws DaoException
	{
		return this.seccionDAO.getDetalleSecciones(tipoProceso);
	}
	/**
	 * lista detalle tipo
	 * @param idCodigoBarra
	 * @param tipo
	 * @return
	 * @throws DaoException
	 */
	public List getDetalleTipo(long idCodigoBarra, char tipo) throws DaoException
	{
		return this.seccionDAO.getDetalleTipo(idCodigoBarra, tipo);
	}
	/**
	 * detalle seccion
	 * @param idCodigoBarra
	 * @param tipo
	 * @param idDetalleSeccion
	 * @return
	 * @throws DaoException
	 */
	public DetalleSeccionVO getDetalle(long idCodigoBarra, int tipo, int idDetalleSeccion ) throws DaoException
	{
		return this.seccionDAO.getDetalle(idCodigoBarra, tipo, idDetalleSeccion);
	}	
}
