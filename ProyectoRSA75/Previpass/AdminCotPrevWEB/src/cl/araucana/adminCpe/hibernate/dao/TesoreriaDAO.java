package cl.araucana.adminCpe.hibernate.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import cl.araucana.adminCpe.presentation.struts.javaBeans.LineaMapeoTesoreria;
import cl.araucana.cp.distribuidor.hibernate.beans.ConceptoTesoreriaVO;
import cl.araucana.cp.distribuidor.hibernate.beans.EntidadApvVO;
import cl.araucana.cp.distribuidor.hibernate.beans.EntidadCCAFVO;
import cl.araucana.cp.distribuidor.hibernate.beans.EntidadExCajaVO;
import cl.araucana.cp.distribuidor.hibernate.beans.EntidadMutualVO;
import cl.araucana.cp.distribuidor.hibernate.beans.EntidadPensionVO;
import cl.araucana.cp.distribuidor.hibernate.beans.EntidadSaludVO;
import cl.araucana.cp.distribuidor.hibernate.beans.MapeoTesoreriaVO;
import cl.araucana.cp.distribuidor.hibernate.beans.TipoDetalleVO;
import cl.araucana.cp.distribuidor.hibernate.beans.TipoNominaVO;
import cl.araucana.cp.distribuidor.hibernate.beans.TipoSeccionVO;
import cl.araucana.cp.distribuidor.hibernate.exceptions.DaoException;

/*
* @(#) TesoreriaDao.java 1.4 10/06/2009
*
* Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
* La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
* restringido a los sistemas de información propios de la institución.
*/

/**
 * @author jdeñgado
 * @author cchamblas
 * 
 * @version 1.4
 */
public class TesoreriaDAO
{
	private static Logger log = Logger.getLogger(TesoreriaDAO.class);
	private Session session;

	public TesoreriaDAO(Session session)
	{
		this.session = session;
	}

	/**
	 * Borra concepto tesoreria
	 * @param conceptoTesoreriaVO
	 * @throws DaoException
	 */
	public void delete(ConceptoTesoreriaVO conceptoTesoreriaVO) throws DaoException
	{
		try{
			this.session.delete(conceptoTesoreriaVO);
			this.session.flush();
		} catch (Exception ex)
		{
			log.error("Error en TesoreriaDAO.delete");
			throw new DaoException("TesoreriaDAO:delete:", ex);
		}
	}
	/**
	 * Borra mapeo Tesoreria
	 * @param mapeoTesoreriaVO
	 * @throws DaoException
	 */
	public void delete(MapeoTesoreriaVO mapeoTesoreriaVO) throws DaoException
	{
		try{
			this.session.flush();
			this.session.delete(mapeoTesoreriaVO);
		} catch (Exception ex)
		{
			log.error("Error en TesoreriaDAO.delete");
			throw new DaoException("TesoreriaDAO:delete:", ex);
		}
	}
	/**
	 * 
	 * @param conceptoTesoreriaVO
	 * @return
	 * @throws DaoException
	 */
	public boolean existeAsociacionConceptoTesoreria(int conceptoTesoreria) throws DaoException
	{
		try{
			List lista = this.session.createCriteria(MapeoTesoreriaVO.class)
						.add(Restrictions.eq("idConceptoTesoreria", new Integer(conceptoTesoreria)))
						.list();
			if(lista.size()>0)
					return true;
			return false;
		} catch (Exception ex)
		{
			log.error("Error en TesoreriaDAO.existeAsociacionConceptoTesoreria");
			throw new DaoException("TesoreriaDAO:existeAsociacionConceptoTesoreria:", ex);
		}
	}
	/**
	 * 
	 * @param conceptoTesoreriaVO
	 * @return
	 * @throws DaoException
	 */
	public boolean existeConceptoTesoreria(int conceptoTesoreriaVO) throws DaoException
	{
		try{
			List lista = this.session.createCriteria(ConceptoTesoreriaVO.class)
						.add(Restrictions.eq("id", new Integer(conceptoTesoreriaVO)))
						.list();
			if(lista.size()>0)
				return true;
			return false;
		} catch (Exception ex)
		{
			log.error("Error en TesoreriaDAO.existeConceptoTesoreria");
			throw new DaoException("TesoreriaDAO:existeConceptoTesoreria:", ex);
		}
	}
	/**
	 * 
	 * @param mapeoTesoreriaVO
	 * @return
	 * @throws DaoException
	 */
	public boolean existeMapeoTesoreria(MapeoTesoreriaVO mapeoTesoreriaVO) throws DaoException
	{
		try{
			List lista = this.session.createCriteria(MapeoTesoreriaVO.class)
						.add(Restrictions.eq("idTipoNomina", new Character(mapeoTesoreriaVO.getIdTipoNomina())))
						.add(Restrictions.eq("idTipoSeccion", new Integer(mapeoTesoreriaVO.getIdTipoSeccion())))
						.add(Restrictions.eq("idDetalleSeccion", new Integer(mapeoTesoreriaVO.getIdDetalleSeccion())))
						.add(Restrictions.eq("idConceptoTesoreria", new Integer(mapeoTesoreriaVO.getIdConceptoTesoreria())))
						.add(Restrictions.eq("idMontoDetSeccion", new Integer(mapeoTesoreriaVO.getIdMontoDetSeccion())))
						.list();
			if(lista.size()>0)
					return true;
			return false;
		} catch (Exception ex)
		{
			log.error("Error en TesoreriaDAO.existeMapeoTesoreria");
			throw new DaoException("TesoreriaDAO:existeMapeoTesoreria:", ex);
		}
	}
	/**
	 * 
	 * @return
	 * @throws DaoException
	 */
	public List getConceptoTesoreria() throws DaoException
	{
		try{
			return this.session.createCriteria(ConceptoTesoreriaVO.class).list();
		} catch (Exception ex)
		{
			log.error("Error en TesoreriaDAO.getConceptoTesoreria");
			throw new DaoException("TesoreriaDAO:getConceptoTesoreria:", ex);
		}
	}
	/**
	 * 
	 * @return retorna lista detalle tesoreria
	 * @param idTipoNomina, idTipoSeccion
	 * @throws DaoException
	 */
	public List getDetalleTesoreria(long idTipoSeccion, String idTipoNomina) throws DaoException
	{	
		List listaRetorno = new ArrayList();
		try
		{
			
			String select = "select mapeo from MapeoTesoreriaVO mapeo ";
			
			if (!idTipoNomina.equals("0") && idTipoSeccion != 0){
				select += " where mapeo.idTipoNomina = '"+idTipoNomina+"' and mapeo.idTipoSeccion = "+idTipoSeccion;
			}
			else if (idTipoNomina.equals("0") && idTipoSeccion != 0){
				select += " where mapeo.idTipoSeccion ="+idTipoSeccion;
			}
			else if (!idTipoNomina.equals("0") && idTipoSeccion == 0){
				select += " where mapeo.idTipoNomina = '"+idTipoNomina+"'";
			}			
			select += " order by mapeo.idTipoNomina, "+
			"mapeo.idTipoSeccion, "+
			"mapeo.idDetalleSeccion, "+
			"mapeo.idConceptoTesoreria, "+
			"mapeo.idMontoDetSeccion ";
			
			StringBuffer hqlQuery = new StringBuffer(select);			
			Query query = this.session.createQuery(hqlQuery.toString());
			List lista = query.list();
			
			/*List lista = (this.session.createCriteria(MapeoTesoreriaVO.class)
							.addOrder(Order.asc("idTipoNomina"))
							.addOrder(Order.asc("idTipoSeccion"))
							.addOrder(Order.asc("idDetalleSeccion"))
							.addOrder(Order.asc("idConceptoTesoreria"))
							.addOrder(Order.asc("idMontoDetSeccion")))
							.list();*/
			LineaMapeoTesoreria linea;
			for(Iterator it = lista.iterator(); it.hasNext();)
			{
				MapeoTesoreriaVO mapeo = (MapeoTesoreriaVO)it.next();
									
				TipoDetalleVO tipoDetalleVO = this.getTipoDetalle(mapeo.getIdTipoNomina(), mapeo.getIdTipoSeccion(), mapeo.getIdDetalleSeccion());
				
				linea = new LineaMapeoTesoreria();	
				
				linea.setIdNomina(mapeo.getIdTipoNomina());
				linea.setIdTipoSeccion(mapeo.getIdTipoSeccion());
				linea.setIdTipoDetalle(mapeo.getIdDetalleSeccion());
				linea.setIdConcepto(mapeo.getIdConceptoTesoreria());
				linea.setIdMonto(mapeo.getIdMontoDetSeccion());
				
				linea.setNomina(mapeo.getNombreNomina());
				linea.setTipoSeccion(mapeo.getNombreSeccion());
				linea.setConcepto(mapeo.getNombreConcepto().trim());
				
				if(tipoDetalleVO != null)
					linea.setTipoDetalle(getNombreTipoDetalle(tipoDetalleVO.getIdEntPagadora()));

				linea.setMonto(mapeo.getIdMontoDetSeccion());
				linea.setValorMonto(mapeo.getIdMontoDetSeccion());
				listaRetorno.add(linea);
			}
			return listaRetorno;
		} catch (Exception ex)
		{
			log.error("Error en TesoreriaDAO.getDetalleTesoreria");
			throw new DaoException("TesoreriaDAO:getDetalleTesoreria:", ex);
		}
	}
	/**
	 * 
	 * @return retorna Montos tesoreria
	 * @throws DaoException
	 */
	public List getListaMontos() throws DaoException
	{
		try{
			List listaRetorno = new ArrayList();
			LineaMapeoTesoreria linea;
			for(int a=1;a<13;a++){
				linea = new LineaMapeoTesoreria();
				linea.setIdMonto(a);
				linea.setMonto(a);
				listaRetorno.add(linea);
			}
	return listaRetorno;
	} catch (Exception ex)
		{
			log.error("Error en TesoreriaDAO.getDetalleSeccion");
			throw new DaoException("TesoreriaDAO:getDetalleSeccion:", ex);
		}
	}
	/**
	 * 
	 * @param idTipoDetalle
	 * @return Retorna Nombre tipo detalle tesoreria
	 * @throws DaoException
	 */
	public String getNombreTipoDetalle(int idTipoDetalle) throws DaoException
	{
		String retorno = "";
		try{
			List lista = this.session.createCriteria(EntidadPensionVO.class)
						.add(Restrictions.eq("idEntPagadora", new Integer(idTipoDetalle)))
						.list();
			if(lista.size()>0){
				retorno=((EntidadPensionVO)lista.get(0)).getNombre();
			} else {
				lista = this.session.createCriteria(EntidadSaludVO.class)
						.add(Restrictions.eq("idEntPagadora", new Integer(idTipoDetalle)))
						.list();
				if(lista.size()>0){
					retorno=((EntidadSaludVO)lista.get(0)).getNombre();
				} else {
					lista = this.session.createCriteria(EntidadApvVO.class)
							.add(Restrictions.eq("idEntPagadora", new Integer(idTipoDetalle)))
							.list();
					if(lista.size()>0){
						retorno=((EntidadApvVO)lista.get(0)).getNombre();
					} else {
						lista = this.session.createCriteria(EntidadCCAFVO.class)
								.add(Restrictions.eq("idEntPagadora", new Integer(idTipoDetalle)))
								.list();
						if(lista.size()>0){
							retorno=((EntidadCCAFVO)lista.get(0)).getNombre();
						} else {
							lista = this.session.createCriteria(EntidadExCajaVO.class)
									.add(Restrictions.eq("idEntPagadora", new Integer(idTipoDetalle)))
									.list();
							if(lista.size()>0){
								retorno=((EntidadExCajaVO)lista.get(0)).getNombre();
							} else {
								lista = this.session.createCriteria(EntidadMutualVO.class)
										.add(Restrictions.eq("idEntPagadora", new Integer(idTipoDetalle)))
										.list();
								if(lista.size()>0)
									retorno=((EntidadMutualVO)lista.get(0)).getNombre();
							}
						}
					}
				}
			}
			return retorno;
		} catch (Exception ex)
		{
			log.error("Error en TesoreriaDAO.getTipoSeccion");
			throw new DaoException("TesoreriaDAO:getTipoSeccion:", ex);
		}
	}
	/**
	 * 
	 * @param idNomina
	 * @param idSeccion
	 * @return lista tipo detalle Nomina
	 * @throws DaoException
	 */
	public List getTipoDetalle(char idNomina, int idSeccion) throws DaoException
	{
		try{
			List lista= this.session.createCriteria(TipoDetalleVO.class)
						.add(Restrictions.eq("idTipoNomina", new Character(idNomina)))
						.add(Restrictions.eq("idTipoSeccion", new Integer(idSeccion)))
						.list();
			return lista;
		} catch (Exception ex)
		{
			log.error("Error en TesoreriaDAO.getTipoSeccion");
			throw new DaoException("TesoreriaDAO:getTipoSeccion:", ex);
		}
	}

	/**
	 * 
	 * @param nomina
	 * @param seccion
	 * @param detalle
	 * @return lista tipo detalle Nomina
	 * @throws DaoException
	 */
	public TipoDetalleVO getTipoDetalle(char nomina, int seccion, int detalle) throws DaoException
	{
		try
		{
			List lista = this.session.createCriteria(TipoDetalleVO.class)
					.add(Restrictions.eq("idTipoNomina", new Character(nomina)))
					.add(Restrictions.eq("idTipoSeccion", new Integer(seccion)))
					.add(Restrictions.eq("idDetalleSeccion", new Integer(detalle)))
					.list();
			if(lista.size()>0)
				return (TipoDetalleVO)lista.get(0);
			return null;
		} catch (Exception ex)
		{
			log.error("Error en TesoreriaDAO.getTipoSeccion");
			throw new DaoException("TesoreriaDAO:getTipoSeccion:", ex);
		}
	}
	/**
	 * 
	 * @return lista tipo Nomina
	 * @throws DaoException
	 */
	public List getTipoNomina() throws DaoException
	{
		try{
			return this.session.createCriteria(TipoNominaVO.class).list();
		} catch (Exception ex)
		{
			log.error("Error en TesoreriaDAO.getTipoNomina");
			throw new DaoException("TesoreriaDAO:getTipoNomina:", ex);
		}
	}
	/**
	 * 
	 * @return lista de tipo seccion
	 * @throws DaoException
	 */
	public List getTipoSeccion() throws DaoException
	{
		try{
			return this.session.createCriteria(TipoSeccionVO.class).list();
		} catch (Exception ex)
		{
			log.error("Error en TesoreriaDAO.getTipoSeccion");
			throw new DaoException("TesoreriaDAO:getTipoSeccion:", ex);
		}
	}
	/**
	 * 
	 * @param conceptoTesoreriaVO
	 * @throws DaoException
	 */
	public void save(ConceptoTesoreriaVO conceptoTesoreriaVO) throws DaoException
	{
		try{
			this.session.save(conceptoTesoreriaVO);
			this.session.flush();
		} catch (Exception ex)
		{
			log.error("Error en TesoreriaDAO.save");
			throw new DaoException("TesoreriaDAO:save:", ex);
		}
	}
	/**
	 * Guarda Mapero Tesoreria
	 * @param mapeoTesoreriaVO
	 * @throws DaoException
	 */
	public void save(MapeoTesoreriaVO mapeoTesoreriaVO) throws DaoException
	{
		try{
			this.session.save(mapeoTesoreriaVO);
			this.session.flush();
		} catch (Exception ex)
		{
			log.error("Error en TesoreriaDAO.save");
			throw new DaoException("TesoreriaDAO:save:", ex);
		}
	}
	/**
	 * 
	 * @param conceptoTesoreriaVO
	 * @param _conceptoTesoreriaVO
	 * @throws DaoException 
	 * @throws DaoException
	 */
	public void update(int idNuevo,int idAntiguo) throws DaoException 
	{
		try{
			this.session.flush();
			List lista = this.session.createCriteria(MapeoTesoreriaVO.class).add(Restrictions.eq("idConceptoTesoreria", new Integer(idAntiguo))).list();
			List listaBorrar = new ArrayList();
			for(Iterator it =  lista.iterator(); it.hasNext();)
			{
				MapeoTesoreriaVO map = (MapeoTesoreriaVO)it.next();
				MapeoTesoreriaVO newMap = new  MapeoTesoreriaVO(
							map.getIdTipoNomina(), 
							map.getIdTipoSeccion(), 
							map.getIdDetalleSeccion(), 
							idNuevo, 
							map.getIdMontoDetSeccion());
				this.session.save(newMap);
				listaBorrar.add(map);
				this.session.flush();
			}
			for(Iterator it =  listaBorrar.iterator(); it.hasNext();)
			{
				this.session.delete(it.next());
				this.session.flush();
			}
		} catch (Exception ex)
		{
			log.error("Error en TesoreriaDAO.delete");
			throw new DaoException("TesoreriaDAO:delete:", ex);
		}
	}
	/**
	 * 
	 * @param conceptoTesoreriaVO
	 * @throws DaoException
	 */
	public void update(ConceptoTesoreriaVO conceptoTesoreriaVO) throws DaoException
	{
		try{
			{
				this.session.update(conceptoTesoreriaVO);
				this.session.flush();
			}
		} catch (Exception ex)
		{
			log.error("Error en TesoreriaDAO.update");
			throw new DaoException("TesoreriaDAO:update:", ex);
		}
	}
	/**
	 * Actualiza mapeo
	 * @param mapeoTesoreriaVO
	 * @throws DaoException
	 */
	public void update(MapeoTesoreriaVO mapeoTesoreriaVO) throws DaoException
	{
		try{
			this.session.flush();
			this.session.update(mapeoTesoreriaVO);
		} catch (Exception ex)
		{
			log.error("Error en TesoreriaDAO.update");
			throw new DaoException("TesoreriaDAO:update:", ex);
		}
	}

	/**
	 * 
	 * @return retorna cantidad registros tesoreria
	 * @param idTipoNomina, idTipoSeccion
	 * @throws DaoException
	 */
	public int getCantidadRegistros(long idTipoSeccion, String idTipoNomina) throws DaoException
	{	
		try
		{
			String select = "select mapeo from MapeoTesoreriaVO mapeo ";
			
			if (!idTipoNomina.equals("0") && idTipoSeccion != 0){
				select += " where mapeo.idTipoNomina = '"+idTipoNomina+"' and mapeo.idTipoSeccion = "+idTipoSeccion;
			}
			else if (idTipoNomina.equals("0") && idTipoSeccion != 0){
				select += " where mapeo.idTipoSeccion ="+idTipoSeccion;
			}
			else if (!idTipoNomina.equals("0") && idTipoSeccion == 0){
				select += " where mapeo.idTipoNomina ='"+idTipoNomina+"'";
			}			
			StringBuffer hqlQuery = new StringBuffer(select);			
			Query query = this.session.createQuery(hqlQuery.toString());
			List lista = query.list();
			if(lista!=null)
				return lista.size();
			return 0;
		} catch (Exception ex)
		{
			log.error("Error en TesoreriaDAO.getCantidadRegistros");
			throw new DaoException("TesoreriaDAO:getCantidadRegistros:", ex);
		}
	}
}
