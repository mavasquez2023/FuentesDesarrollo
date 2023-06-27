package cl.araucana.cp.distribuidor.business.mgr;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import cl.araucana.cp.distribuidor.hibernate.beans.DescriptorNominaVO;
import cl.araucana.cp.distribuidor.hibernate.beans.NodoVO;
import cl.araucana.cp.distribuidor.hibernate.beans.ParametroVO;
import cl.araucana.cp.distribuidor.hibernate.dao.NodoDAO;
import cl.araucana.cp.distribuidor.hibernate.dao.ParametrosDAO;

public class AsignadorMgr
{
	private static Logger log = Logger.getLogger(AsignadorMgr.class);
	Session session;
	NodoDAO nodoDao;
	ParametrosDAO parametrosDao;

	public AsignadorMgr(Session session)
	{
		init(session);
	}

	private void init(Session sess)
	{
		this.session = sess;
		this.nodoDao = new NodoDAO(sess);
		this.parametrosDao = new ParametrosDAO(sess);
	}

	public boolean actualizaBalanceo() throws Exception
	{
		try
		{
			log.info("inicio actualizacion balanceo:");
			this.nodoDao.actualizaBalanceoCarga(this.parametrosDao.getFactoresCarga());

			log.info("fin actualizacion balanceo:");
		} catch (Exception e)
		{
			log.error("ERROR AsignadorMgr:actualizaBalanceo:Message:", e);
			throw new Exception();
		}
		return true;
	}

	public HashMap asigna(int idEnvio) throws Exception
	{
		Transaction tx = null;
		try
		{
			HashMap result = new HashMap();
			List descriptores = this.nodoDao.getDescriptoresAsig(idEnvio);
			log.info("DistribuidorSessionBean:asigna idEnvio:" + idEnvio + "::");
			List criterios = this.parametrosDao.getCritDistribucion();
			log.info("numero de criterios a aplicar:" + criterios.size() + "::");
			tx = this.session.beginTransaction();

			for (Iterator itDesc = descriptores.iterator(); itDesc.hasNext();)
			{
				List nodosCalificados = new ArrayList();
				DescriptorNominaVO descriptor = (DescriptorNominaVO)itDesc.next();
				String id = descriptor.getRutEmpresa() + "#" + descriptor.getIdConvenio() + "#" + descriptor.getTipoProceso();
				for (Iterator it = criterios.iterator(); it.hasNext();)
				{
					ParametroVO p = (ParametroVO) it.next();
					log.info("aplicando criterio (desde tabla parametro):" + p.getNombre() + ":" + p.getValor() + "::");
					if (p.getValor() != null)
					{
						String nombreCriterio = p.getValor().trim();
						if ("C1".equals(nombreCriterio))
							nodosCalificados = this.nodoDao.getNodosNumConn(nodosCalificados);
						else if ("C2".equals(nombreCriterio))
							nodosCalificados = this.nodoDao.getNodosCapCarga(this.nodoDao.getMaxCargaPromedio(idEnvio), nodosCalificados);
					}
					if (nodosCalificados.size() == 1)
						break;
				}
				if (nodosCalificados.size() > 0)
				{
					NodoVO nodo = (NodoVO) nodosCalificados.get(0);
					log.info("despues de aplicar criterios solicitados, id nodo encontrado:" + nodo.getIdNodo() + "::");
					this.nodoDao.ocupaNodo(nodo.getIdNodo());
					result.put(id, nodo);
					continue;
				}
				NodoVO nodo = this.nodoDao.getNodoMinConexiones();
				log.info("resultado de filtros no obtuvo nodo, asignando nodo por defecto (mayor numero conexiones disponibles)");
				log.info("\tNODO por defecto:" + nodo.getIdNodo() + "::");
				this.nodoDao.ocupaNodo(nodo.getIdNodo());
				result.put(id, nodo);
			}
			tx.commit();
			return result;
		} catch (Exception e)
		{
			log.error("ERROR AsignadorMgr:asigna:idEnvio:" + idEnvio, e);
			if (tx != null)
				tx.rollback();
			throw new Exception();
		}
	}

	public NodoVO asigna() throws Exception
	{
		try
		{
			log.info("AsignadorMgr:asigna 1 trabajador:");
			List result = new ArrayList();

			result = this.nodoDao.getNodosNumConn(result);
			if (result.size() > 0)
			{
				NodoVO n = (NodoVO) result.get(0);
				log.info("despues de aplicar criterios solicitados, id nodo encontrado:" + n.getIdNodo() + ": 1 trabajador:");
				return n;
			}
			NodoVO nodo = this.nodoDao.getNodoMinConexiones();
			log.info("resultado de filtros no obtuvo nodo, asignando nodo por defecto (mayor numero conexiones disponibles)");
			log.info("\tNODO por defecto:" + nodo.getIdNodo() + "::");
			return nodo;
		} catch (Exception e)
		{
			log.error("ERROR AsignadorMgr:asigna 1 trabajador:", e);
			throw new Exception();
		}
	}
}
