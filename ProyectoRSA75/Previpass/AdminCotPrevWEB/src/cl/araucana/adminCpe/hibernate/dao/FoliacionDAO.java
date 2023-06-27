package cl.araucana.adminCpe.hibernate.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import cl.araucana.cp.distribuidor.hibernate.beans.EntidadPagadoraVO;
import cl.araucana.cp.distribuidor.hibernate.beans.FoliacionVO;
import cl.araucana.cp.distribuidor.hibernate.exceptions.DaoException;
/*
* @(#) FoliacionDao.java 1.8 10/06/2009
*
* Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
* La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
* restringido a los sistemas de información propios de la institución.
*/
/**
 * @author jdelgado
 * @author malvarez
 * 
 * @version 1.8
 */
public class FoliacionDAO
{
	private static Logger log = Logger.getLogger(FoliacionDAO.class);
	private Session session;

	public FoliacionDAO(Session session)
	{
		this.session = session;
	}

	/**
	 * elimina folio entidad
	 * @param idEntidad
	 * @throws Exception
	 */
	public void borraFoliosEntidad(int idEntidad) throws Exception 
	{
		log.info("FoliacionDAO: borraFoliosEntidad: entPagadora:"+idEntidad);
		try
		{
			List lista = this.session.createCriteria(FoliacionVO.class).add(Restrictions.eq("idEntPagadora", new Integer(idEntidad))).list();
			if(lista.size() > 0)
			{
				for (Iterator it = lista.iterator(); it.hasNext();) 
				{
					this.session.delete(it.next());
				}
				this.session.flush();
			}
		} catch(Exception ex) 
		{
			log.error("FoliacionVO:borraFolios error: " + ex.getCause().toString());
			throw new DaoException("Problemas en FoliacionVO.borraFolios", ex);
		}
	}

	/**
	 * 
	 * @param id
	 * @return lista folios entidad pagadora
	 * @throws DaoException
	 */
	public List getFoliosEntidadPagadora(int id) throws DaoException
	{
		try
		{
			return this.session.createCriteria(FoliacionVO.class).add(Restrictions.eq("idEntPagadora", new Integer(id))).addOrder(Order.asc("idFoliacion")).list();
		} catch (Exception ex)
		{
			log.error("Error en EntidadesDAO.getFolios");
			throw new DaoException("EntidadesDAO:getFolios:", ex);
		}
	}
	/**
	 * 
	 * @param id
	 * @return lista folio 
	 * @throws DaoException
	 */
	public List getFoliosId(int id) throws DaoException
	{
		try
		{
			return this.session.createCriteria(FoliacionVO.class).add(Restrictions.eq("idFoliacion", new Integer(id))).addOrder(Order.asc("idFoliacion")).list();
		} catch (Exception ex)
		{
			log.error("Error en EntidadesDAO.getFolios");
			throw new DaoException("EntidadesDAO:getFolios:", ex);
		}
	}

	/**
	 * guarda folios
	 * @param listaGuardar
	 * @throws DaoException
	 */
	public List guardaFolios(List listaGuardar) throws DaoException 
	{
		try 
		{
			log.info("FoliacionDAO:guardaFolios n folios:" + (listaGuardar.size()-1) + "::");
			List _listaGuardar =  new ArrayList();
			List _listaActualizar =  new ArrayList();
			List _listaBorrar =  new ArrayList();
			int idEntidadPagadora = 0;
			for (Iterator it = listaGuardar.iterator(); it.hasNext();) 
			{
				FoliacionVO folio = (FoliacionVO) it.next();
				idEntidadPagadora = folio.getIdEntPagadora();
				if(folio.getIdFoliacion() == 0)
				{
					if(folio.getFolioFinal() != 0)
						_listaGuardar.add(folio);
				} else
					_listaActualizar.add(folio);
			}
			if(idEntidadPagadora != 0)
			{
				List listaFolios = this.session.createCriteria(FoliacionVO.class).add(Restrictions.eq("idEntPagadora", new Integer(idEntidadPagadora))).list();
				for (Iterator it = listaFolios.iterator(); it.hasNext();) 
				{
					boolean borra = true;
					FoliacionVO folio = (FoliacionVO) it.next();
					for (Iterator itG = _listaGuardar.iterator(); itG.hasNext();) 
					{
						FoliacionVO folioG = (FoliacionVO) itG.next();
						if(folioG.getIdFoliacion() == folio.getIdFoliacion())
							borra = false;
					}	
					for (Iterator itG = _listaActualizar.iterator(); itG.hasNext();) 
					{
						FoliacionVO folioG = (FoliacionVO) itG.next();
						if(folioG.getIdFoliacion() == folio.getIdFoliacion())
							borra = false;
					}	
					if(borra)
						_listaBorrar.add(folio);
				}
			}
			boolean fls=false;
			for (Iterator it = _listaBorrar.iterator(); it.hasNext();) 
			{
				FoliacionVO folio = (FoliacionVO) it.next();
				this.session.delete(folio);
				fls=true;
			}
			for (Iterator it = _listaActualizar.iterator(); it.hasNext();) 
			{
				FoliacionVO folio = (FoliacionVO) it.next();
				this.session.merge(folio);	
				fls=true;
			}
			int id = getFolioId(idEntidadPagadora);
			for (Iterator it = _listaGuardar.iterator(); it.hasNext();) 
			{
				FoliacionVO folio = (FoliacionVO) it.next();
				folio.setIdFoliacion(id++);
				this.session.save(folio);
				fls=true;
			}
			if(fls)
				this.session.flush();
			return new ArrayList();
		} catch(Exception ex) 
		{
			log.error("FoliacionDAO:guardaFolios error: ", ex);
			throw new DaoException("Problemas en FoliacionVO.guardaFolios", ex);
		}
	}
	
	/**
	 * Devuelve el siguiente correlativo
	 * @return
	 * @throws DaoException
	 */
	public int getFolioId(int idEntPagadora) throws DaoException 
	{
		int idTabla = 0;
		try 
		{
			List lista = this.session.createCriteria(FoliacionVO.class).add(Restrictions.eq("idEntPagadora", new Integer(idEntPagadora))).setProjection(Projections.max("idFoliacion")).list();

			if (lista.get(0) != null)
				idTabla = ((Integer)lista.get(0)).intValue();
	    	idTabla += 1;
		    	
		} catch(Exception ex) 
		{
			log.error("FoliacionVO:getFolioId error:", ex);
			throw new DaoException("Problemas en FoliacionVO.getFolioId", ex);
		}
		return idTabla;
	}

	/**
	 * Reinicia el folio actual para aquellas entidades pagadoras que tengan el campo REINICIA_FOLIO en 1
	 *
	 */
	public void reiniciaFolioActual() {
		Query query = this.session.createQuery("UPDATE " + FoliacionVO.class.getName() +
												 " SET folioActual    = ? " +
												"WHERE idEntPagadora IN (SELECT idEntPagadora FROM " + EntidadPagadoraVO.class.getName() + " WHERE reiniciaFolio = 1)");
		query.setInteger(0, 1);
		query.executeUpdate();
	}
}
