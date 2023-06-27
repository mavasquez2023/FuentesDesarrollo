package cl.araucana.adminCpe.hibernate.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import cl.araucana.adminCpe.hibernate.beans.SPLEstadoVO;
import cl.araucana.adminCpe.hibernate.beans.SPLMedioPagoVO;
import cl.araucana.adminCpe.hibernate.beans.SPLPagoVO;
import cl.araucana.cp.distribuidor.hibernate.exceptions.DaoException;
/*
* @(#) SplDao.java 1.4 10/06/2009
*
* Este c�digo fuente pertenece a la Caja de Compensaci�n de Asignaci�n Familiar
* La Araucana (C.C.A.F.). Su utilizaci�n y reproducci�n es confidencial y est�
* restringido a los sistemas de informaci�n propios de la instituci�n.
*/

/**
 * @author malvarez
 * @author jdelgado
 * 
 * @version 1.4
 */
public class SplDAO {
	private static Logger log = Logger.getLogger(SplDAO.class);
	private Session session;

	public SplDAO(Session session) {
		this.session = session;
	}

	/**
	 * 
	 * @return lista medios pago Acitvos
	 * @throws DaoException
	 */
	public List getMediosPagoActivos() throws DaoException 
	{
		try 
		{
			List lista = this.session.createCriteria(SPLMedioPagoVO.class)
						.add(Restrictions.eq("activo", new Integer(1)))
						.list();
			return lista;
		} catch (Exception ex) 
		{
			log.error("Error en SplDAO:getMediosPagoActivos");
			throw new DaoException("Error en SplDAO.getMediosPagoActivos", ex);
		}
	}
	/**
	 * 
	 * @return lista montos convenios
	 * @throws DaoException
	 */
	public List getMontosSplConvenio() throws DaoException 
	{
		try 
		{
			List lista = this.session.createCriteria(SPLPagoVO.class)
						.addOrder(Order.asc("idConvenio"))
						.list();
			return lista;
		} catch (Exception ex) 
		{
			log.error("Error en SplDAO:getMontosSplConvenio");
			throw new DaoException("Error en SplDAO.getMontosSplConvenio", ex);
		}
	}
	/**
	 * 
	 * @param idConvenio
	 * @return lista Montos SPL  por convenio
	 * @throws DaoException
	 */
	public List getMontosSplConvenio(int idConvenio) throws DaoException 
	{
		try 
		{
			List lista = this.session.createCriteria(SPLPagoVO.class)
						.add(Restrictions.eq("idConvenio", new Integer(idConvenio)))
						.list();
			return lista;
		} catch (Exception ex) 
		{
			log.error("Error en SplDAO.getMontosSplConvenio");
			throw new DaoException("Error en SplDAO.getMontosSplConvenio", ex);
		}
	}
	/**
	 * 
	 * @param idEstado
	 * @return lista estados de pago
	 * @throws DaoException
	 */
	public String getEstadoPago(int idEstado) throws DaoException 
	{
		try {
			
			SPLEstadoVO splEstadoVO =  (SPLEstadoVO)this.session.createCriteria(SPLEstadoVO.class)
						.add(Restrictions.eq("idEstado", new Integer(idEstado)))
						.uniqueResult();
			if(splEstadoVO != null)
				return splEstadoVO.getDescripcion();
			return "";
		} catch (Exception ex) 
		{
			log.error("Error en SplDAO:getEstadoPago");
			throw new DaoException("Error en SplDAO.getEstadoPago", ex);
		}
	}
}
