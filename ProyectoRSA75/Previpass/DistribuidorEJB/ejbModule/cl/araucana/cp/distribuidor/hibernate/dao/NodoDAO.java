package cl.araucana.cp.distribuidor.hibernate.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import cl.araucana.cp.distribuidor.base.Constants;
import cl.araucana.cp.distribuidor.hibernate.beans.BalanceoCargaVO;
import cl.araucana.cp.distribuidor.hibernate.beans.DescriptorNominaVO;
import cl.araucana.cp.distribuidor.hibernate.beans.NodoVO;
import cl.araucana.cp.distribuidor.hibernate.exceptions.DaoException;
import cl.araucana.cp.distribuidor.hibernate.beans.ParametroVO;

public class NodoDAO 
{
	private static Logger logger = Logger.getLogger(NodoDAO.class);
    Session session;

    public NodoDAO(Session session) 
    {
        this.session = session;
    }

    public List getNodos() throws DaoException 
    {
    	logger.info("NodoDAO:getNodos");
    	try 
        {
        	Criteria crit = this.session.createCriteria(NodoVO.class);
        	return crit.add(Restrictions.eq("habilitado", new Integer(Constants.COD_HABILITACION_NODO))).list();
        } catch (Exception ex) 
        {
        	logger.error("ERROR:" + ex);
            throw new DaoException("Problemas obteniendo getNodos",ex);
        }
    }

    public NodoVO getNodoMinConexiones() throws DaoException 
    {
    	logger.info("NodoDAO:getNodoMinConecciones");
    	try 
        {
        	Criteria crit = this.session.createCriteria(NodoVO.class);
        	crit.add(Restrictions.eq("habilitado", new Integer(Constants.COD_HABILITACION_NODO)));
        	crit.add(Restrictions.ne("idNodo", new Integer(Constants.NODO_FALSO)));
        	List result = crit.addOrder(Order.desc("numConnDisponibles")).list();

        	if (result != null && result.size() > 0)
        	{
        		NodoVO nodo = (NodoVO)result.get(0);
        		return nodo;
        	}
        } catch (Exception ex) 
        {
        	logger.error("ERROR:" + ex);
            throw new DaoException("Problemas getNodoMinConecciones:",ex);
        }
        return null;
    }

    public float getMaxCargaPromedio(int idEnvio) throws DaoException 
    {
    	logger.info("NodoDAO:getMaxCargaPromedio");
    	try 
        {
        	Criteria crit = this.session.createCriteria(BalanceoCargaVO.class);
        	crit.setProjection(Projections.max("cargaPromedio"));
        	crit.createCriteria("descriptores").add(Restrictions.eq("idEnvio", new Integer(idEnvio)));
        	List result = crit.list();
        	logger.info("result:" + result + ":::");
        	if (result != null && result.size() > 0 && result.get(0) != null && !result.get(0).equals(""))
        	{
        		Float cargaMax = (Float)result.get(0);
        		logger.info("CargaPromedio:" + cargaMax.floatValue() + ":::");
        		return cargaMax.floatValue();
        	}
        	logger.debug("no se encontro registo, primer envio del convenio? idEnvio:" + idEnvio + "::");
        	return 0;
        } catch (Exception ex) 
        {
        	logger.error("ERROR:" + ex);
            throw new DaoException("Problemas obteniendo getMaxCargaPromedio",ex);
        }
    }

    public List getNodosCapCarga(float c2Max, List lista) throws DaoException 
    {
    	logger.info("NodoDAO:getNodosCapCarga: priorizando por capacidad de carga");
    	try 
        {
        	Criteria crit = this.session.createCriteria(NodoVO.class);
        	crit.add(Restrictions.eq("habilitado", new Integer(Constants.COD_HABILITACION_NODO)));
        	crit.add(Restrictions.ne("idNodo", new Integer(Constants.NODO_FALSO)));
        	if (lista.size() > 0)
        	{
        		List listIds = new ArrayList();
        		for (Iterator it = lista.iterator(); it.hasNext();)
        			listIds.add(new Integer(((NodoVO)it.next()).getIdNodo()));
        		if (!listIds.isEmpty())
        			crit.add(Restrictions.in("idNodo", listIds));
        		
        	}
        	logger.info("NodoDAO:getNodosNumConn: criteria:" + crit.toString() + "::");
        	List nodosDisponibles = crit.list();
        	List result = new ArrayList();
        	List mejoresNodosNoCandidatos = new ArrayList();
        	float mejorCarga = -1;
        	logger.debug("resultado criterio por capacidad de carga (en orden):");
        	if (nodosDisponibles != null && nodosDisponibles.size() > 0)
        	{
        		for (Iterator it = nodosDisponibles.iterator(); it.hasNext();)
        		{
        			NodoVO nodo = (NodoVO)it.next();
        			float anchoUsoSistema = Math.abs(nodo.getUsoSistMaximo() - nodo.getUsoSistMinimo());
        			float distanciaRelativaPuntoMedio = 0;
    				// el siguiente calculo podria sobrepasar o dar division por cero
        			try 
        			{
        				distanciaRelativaPuntoMedio = Math.abs(2*c2Max - nodo.getUsoSistMinimo() - nodo.getUsoSistMaximo()) / anchoUsoSistema;
        			} catch (Throwable e)
        			{
        			}
					String catalogacion = null;
        			if (Math.abs(distanciaRelativaPuntoMedio)<= 1)
        			{
        				catalogacion = "candidato directo";
        				result.add(nodo);
        			} else if (mejorCarga<0 || mejorCarga>=distanciaRelativaPuntoMedio)
        			{
        				if (mejorCarga == distanciaRelativaPuntoMedio)
        					catalogacion = "agregado a las mejores alternativas";
        				else
        				{
        					mejoresNodosNoCandidatos.clear();
        					mejorCarga = distanciaRelativaPuntoMedio;
        					catalogacion = "nueva mejor alternativa";
        				}
        				mejoresNodosNoCandidatos.add(nodo);
        			} else
        				catalogacion = "descartado";
					logger.debug("\tnodo " + nodo.getIdNodo() + '[' + nodo.getUrl().trim() + "]\t distancia relativa:" + distanciaRelativaPuntoMedio + "\t catalogacion: " + catalogacion);
        		}
        	}
        	if (result.isEmpty())
        	{
        		logger.debug("\n\tno se encontraron nodos en el rango para este criterio.");
        		return mejoresNodosNoCandidatos;
        	}
        	return result;
        } catch (Exception ex) 
        {
        	logger.error("ERROR:" + ex);
            throw new DaoException("Problemas obteniendo getNodosCapCarga",ex);
        }    	
    }

    public List getNodosNumConn(List lista) throws DaoException 
    {
    	logger.info("NodoDAO:getNodosNumConn: priorizando por numero de conexiones:");
    	try 
        {
        	Criteria crit = this.session.createCriteria(NodoVO.class);
        	//crit.add(Restrictions.gt("numConnDisponibles", new Integer(0)));
        	crit.add(Restrictions.eq("habilitado", new Integer(Constants.COD_HABILITACION_NODO)));
        	crit.add(Restrictions.ne("idNodo", new Integer(Constants.NODO_FALSO)));
        	if (lista.size() > 0)
        	{
        		List listIds = new ArrayList();
        		for (Iterator it = lista.iterator(); it.hasNext();)
        			listIds.add(new Integer(((NodoVO)it.next()).getIdNodo()));
        		if (!listIds.isEmpty())
        			crit.add(Restrictions.in("idNodo", listIds));
        	}
        	crit = crit.addOrder(Order.desc("numConnDisponibles"));
        	logger.info("NodoDAO:getNodosNumConn: criteria:" + crit.toString() + "::");
        	List nodosOrdenados = crit.list();
        	List result = new ArrayList();
    		logger.debug("resultado criterio por numero de conexiones (en orden):");
       		for (Iterator it = nodosOrdenados.iterator(); it.hasNext();)
       		{
       			NodoVO nodo = (NodoVO)it.next();
       			if (result.isEmpty() || ((NodoVO)result.get(0)).getNumConnDisponibles() == nodo.getNumConnDisponibles())
       			{
       				result.add(nodo);
       				logger.debug("\tnodo encontrado por numero de conexiones:id:" + nodo.getIdNodo() + ":url:" + nodo.getUrl() + "::");
       			}
       		}
        	if (result.isEmpty())
        		logger.debug("\n\tno se encontraron nodos para este criterio.");
    		return result;
        } catch (Exception ex) 
        {
        	logger.error("ERROR:", ex);
            throw new DaoException("Problemas obteniendo getNodosNumConn",ex);
        }    	
    }

    public NodoVO getNodoDistribuidor() throws DaoException 
    {
    	logger.info("NodoDAO:getNodoDistribuidor");
    	NodoVO nodo = null;
        try 
        {
        	Criteria crit = this.session.createCriteria(NodoVO.class);
        	List lista = crit.add(Restrictions.eq("distribuidor", new Integer(Constants.COD_HABILITACION_DISTRIBIDOR)))
        		.add(Restrictions.eq("habilitado", new Integer(Constants.COD_HABILITACION_NODO))).list();
        	if (lista.size() > 0)
        	{
        		nodo = (NodoVO)lista.get(0);
        		logger.debug("nodo distribuidor obtenido:" + nodo.getIdNodo() + ":");
        	}
        } catch (HibernateException ex) 
        {
            throw new DaoException("Problemas obteniendo el nodo distribuidor",ex);
        }
        return nodo;
    }
    
    public boolean actualizaBalanceoCarga(List factoresCarga) throws DaoException 
    {
    	logger.info("NodoDAO:actualizaBalanceoCarga");    
        try 
        {
        	Criteria crit = this.session.createCriteria(BalanceoCargaVO.class);
        	crit.setFetchMode("descriptores", FetchMode.SELECT);
        	List lista = crit.list();
        	if (lista != null && lista.size() > 0)
        	{
        		HashMap factCarga = new HashMap();
        		for (Iterator it = factoresCarga.iterator(); it.hasNext();)
        		{
        			ParametroVO p = (ParametroVO)it.next();
        			String nombre = p.getNombre().trim();
        			String tp = p.getNombre().substring(nombre.length() - 1, nombre.length());
        			logger.debug("PUT:" + tp + ":" + p.getValor().trim() + "::");
        			factCarga.put(tp, new Float(p.getValor().trim()));
        		}
        		
        		for (Iterator it = lista.iterator(); it.hasNext();)
        		{
        			BalanceoCargaVO bc = (BalanceoCargaVO)it.next();
        			bc.setCargaPromedio((float)((bc.getCargaPromedio() * bc.getNumPeriodos()) + (bc.getNumLineas() * ((Float)factCarga.get("" + bc.getTipoProceso())).floatValue())) / (bc.getNumPeriodos() + 1));
        			bc.sumNumPeriodos();
        			bc.setNumLineas(0);
        			logger.debug("a guargar: carga:" + bc.getCargaPromedio() + ":sumNumPeriodos:" + bc.getNumPeriodos() + ":tNumLineas:" + bc.getNumLineas() + "::");
        			this.session.save(bc);
        		}
        	}
        } catch (HibernateException ex) 
        {
            throw new DaoException("Problemas actualizaBalanceoCarga",ex);
        }
        return true;
    }

    public List getDescriptoresAsig(int idEnvio) throws DaoException 
    {
    	try
    	{
    		return this.session.createCriteria(DescriptorNominaVO.class).add(Restrictions.eq("idEnvio", new Integer(idEnvio))).list();

        } catch (HibernateException ex) 
        {
            throw new DaoException("Problemas al obtener lista de decriptores por envio:" + idEnvio + "::",ex);
        }
    }

	public void ocupaNodo(int idNodo) throws DaoException
	{
		try
		{
			NodoVO nodo = (NodoVO) this.session.load(NodoVO.class, new Integer(idNodo));
			nodo.asigna();
			this.session.update(nodo);
		} catch (Exception e)
		{
			logger.error("\n\nERROR ocupaNodo:", e);
			throw new DaoException("ERROR ValidacionDAO:ocupaNodo:idNodo:" + idNodo);
		}
	}
}
