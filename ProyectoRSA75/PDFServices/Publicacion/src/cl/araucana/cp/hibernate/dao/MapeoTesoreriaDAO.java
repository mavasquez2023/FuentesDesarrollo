package cl.araucana.cp.hibernate.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import cl.araucana.cp.distribuidor.hibernate.beans.TipoSeccionVO;
import cl.araucana.cp.distribuidor.hibernate.exceptions.DaoException;
import cl.araucana.cp.hibernate.beans.MapeoTesoreriaVO;
/*
* @(#) MapeoTesoreriaDao.java 1.10 10/05/2009
*
* Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
* La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
* restringido a los sistemas de información propios de la institución.
*/
/**
 * @author cchamblas
 * 
 * @version 1.10
 */
public class MapeoTesoreriaDAO
{
	private static Logger log = Logger.getLogger(MapeoTesoreriaDAO.class);
    private Session session;

    public MapeoTesoreriaDAO(Session session) 
    {
        this.session = session;
    }
    /**
     * mapeo tesoreria
     * @param idTipoSeccion
     * @param idTipoDetalleSeccion
     * @param tipoNomina
     * @return
     * @throws DaoException
     */
    public MapeoTesoreriaVO getMapeoTesoreria(int idTipoSeccion, int idTipoDetalleSeccion, char tipoNomina) throws DaoException 
    {
    	try
    	{
    		log.info("MapeoTesoreriaDAO:getMapeoTesoreria");
    		Criteria crit = this.session.createCriteria(MapeoTesoreriaVO.class);
    		crit.add(Restrictions.eq("idTipoNomina", "" + tipoNomina));
    		crit.add(Restrictions.eq("idTipoSeccion", new Integer(idTipoSeccion)));
    		List lista = crit.add(Restrictions.eq("idDetalleSeccion", new Integer(idTipoDetalleSeccion))).list();
    		return (MapeoTesoreriaVO)lista.get(0); 
    	} catch (Exception ex) 
    	{
    		log.error("ERROR MapeoTesoreriaDAO:getMapeoTesoreria:" + ex);
    		throw new DaoException("Problemas obteniendo getMapeoTesoreria idTipoSeccion:" + idTipoSeccion + ":idTipoDetalleSeccion:" + idTipoDetalleSeccion + ":tipoNomina:" + tipoNomina + "::", ex);
    	}
    }

    /**
     * seccion concepto tes 
     * 
	 * SELECT distinct ID_TIPO_SECCION, ID_CONCEPTO FROM COTPREV2.MAPEO_TESORERIA where ID_TIPO_NOMINA = 'R'GO
     * @param tipoProceso
     * @return
     * @throws DaoException
     */
    public List getSeccionConceptoTes(char tipoProceso) throws DaoException 
    {
    	try
    	{
    		log.info("MapeoTesoreriaDAO:getSeccionConceptoTes");
    		/*List lista = this.session.createSQLQuery("SELECT distinct ID_TIPO_SECCION, ID_CONCEPTO FROM MAPEO_TESORERIA where ID_TIPO_NOMINA = ?").setString(0, "" + tipoProceso).list();
    		List result = new ArrayList();
    		for (Iterator it = lista.iterator(); it.hasNext();)
    		{
    			Object[] tuple = (Object[]) it.next();
    			MapeoTesoreriaVO mt = new MapeoTesoreriaVO(((Short)tuple[0]).intValue(), ((Integer)tuple[1]).intValue());
    			result.add(mt);
    		}
    		return result;*/
    		return this.session.createCriteria(MapeoTesoreriaVO.class).add(Restrictions.eq("idTipoNomina", "" + tipoProceso)).list();
    	} catch (Exception ex) 
    	{
    		log.error("ERROR MapeoTesoreriaDAO:getSeccionConceptoTes:" + ex);
    		throw new DaoException("Problemas obteniendo getSeccionConceptoTes:", ex);
    	}
    }
    /**
     * tipo seccion
     * @return
     * @throws DaoException
     */
    public List getTipoSeccion() throws DaoException 
    {
    	try
    	{
    		log.info("MapeoTesoreriaDAO:getTipoSeccion");
    		return this.session.createCriteria(TipoSeccionVO.class).list(); 
    	} catch (Exception ex) 
    	{
    		log.error("ERROR MapeoTesoreriaDAO:getTipoSeccion:" + ex);
    		throw new DaoException("Problemas obteniendo getTipoSeccion:", ex);
    	}
    }
}
