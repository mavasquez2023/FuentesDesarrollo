package cl.araucana.cp.distribuidor.hibernate.dao;

import java.util.HashMap;
import java.util.Iterator;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;

import cl.araucana.cp.distribuidor.hibernate.beans.EntidadCCAFVO;
import cl.araucana.cp.distribuidor.hibernate.exceptions.DaoException;

public class EntidadesDAO
{
	private static Logger log = Logger.getLogger(EntidadesDAO.class);
    Session session;

    public EntidadesDAO(Session session) 
    {
        this.session = session;
    }

    public HashMap cargaCCAF()
    {
    	try
    	{
    		log.info("EntidadesDAO:cargaCCAF:");
    		Criteria c = this.session.createCriteria(EntidadCCAFVO.class);
    		Iterator result = c.list().iterator();
        	if (result != null && result.hasNext())
        	{
        		HashMap resultHM = new HashMap();
    			while (result.hasNext())
        		{
    				EntidadCCAFVO caja = (EntidadCCAFVO)result.next();
        			resultHM.put("" + caja.getId(), caja);
        		}
        		return resultHM;
        	}
    		throw new DaoException("ERROR EntidadesDAO:cargaCCAF: no se encontraron registros de cajas de compensacion.");
    	} catch (Exception e)
    	{
    		log.error("EntidadesDAO:cargaCCAF error:", e);
    		return new HashMap();
    	}
    }
}