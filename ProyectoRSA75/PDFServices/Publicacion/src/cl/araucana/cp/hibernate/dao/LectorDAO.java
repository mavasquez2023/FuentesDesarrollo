package cl.araucana.cp.hibernate.dao;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import cl.araucana.cp.distribuidor.hibernate.beans.LectorEmpresaConvenioVO;
import cl.araucana.cp.distribuidor.hibernate.beans.LectorEmpresaEmpresaVO;
import cl.araucana.cp.distribuidor.hibernate.beans.LectorEmpresaGrupoConvenioVO;
import cl.araucana.cp.distribuidor.hibernate.beans.LectorEmpresaSucursalVO;
import cl.araucana.cp.distribuidor.hibernate.beans.LectorEmpresaVO;
import cl.araucana.cp.distribuidor.hibernate.beans.LectorEntidadVO;
import cl.araucana.cp.distribuidor.hibernate.exceptions.DaoException;
/*
* @(#) LectorDao.java 1.2 10/05/2009
*
* Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
* La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
* restringido a los sistemas de información propios de la institución.
*/
/**
 * @author cchamblas
 * 
 * @version 1.2
 */
public class LectorDAO
{
	private static Logger log = Logger.getLogger(LectorDAO.class);
	private Session session;

	public LectorDAO(Session session)
	{
		this.session = session;
	}
	
	/**
	 * grupos lector
	 * @param idLectorEmpresa
	 * @return
	 * @throws DaoException
	 */
	public HashMap getGruposLector(int idLectorEmpresa) throws DaoException 
	{
		try
		{
			log.info("LectorDAO:getGruposLector:" + idLectorEmpresa + "::");
			List lista = this.session.createCriteria(LectorEmpresaGrupoConvenioVO.class)
				.add(Restrictions.eq("idLectorEmpresa", new Integer(idLectorEmpresa))).list();
			if (lista.size() == 0)
				return new HashMap();
			HashMap result = new HashMap();
			for (Iterator it = lista.iterator(); it.hasNext();)
			{
				LectorEmpresaGrupoConvenioVO lg = (LectorEmpresaGrupoConvenioVO)it.next();
				result.put("" + lg.getIdGrupoConvenio(), "" + lg.getIdGrupoConvenio());
			}
			return result;
		} catch(Exception ex) 
		{
			log.error("LectorDAO:getGruposLector error:", ex);
			throw new DaoException("Problemas en LectorDAO:getGruposLector:", ex);
		}
	}
	
	/**
	 * empresas lector
	 * @param idLectorEmpresa
	 * @return
	 * @throws DaoException
	 */
	public HashMap getEmpresasLector(int idLectorEmpresa) throws DaoException 
	{
		try
		{
			log.info("LectorDAO:getEmpresasLector:" + idLectorEmpresa + "::");
			List lista = this.session.createCriteria(LectorEmpresaEmpresaVO.class)
				.add(Restrictions.eq("idLectorEmpresa", new Integer(idLectorEmpresa))).list();
			if (lista.size() == 0)
				return new HashMap();
			HashMap result = new HashMap();
			for (Iterator it = lista.iterator(); it.hasNext();)
			{
				LectorEmpresaEmpresaVO le = (LectorEmpresaEmpresaVO)it.next();
				result.put("" + le.getIdEmpresa(), "" + le.getIdEmpresa());
			}
			return result;
		} catch(Exception ex) 
		{
			log.error("LectorDAO:getEmpresasLector error:", ex);
			throw new DaoException("Problemas en LectorDAO:getEmpresasLector:", ex);
		}
	}
	/**
	 * convenios lector
	 * @param idLectorEmpresa
	 * @return
	 * @throws DaoException
	 */
	public HashMap getConveniosLector(int idLectorEmpresa) throws DaoException 
	{
		try
		{
			log.info("LectorDAO:getConveniosLector:" + idLectorEmpresa + "::");
			List lista = this.session.createCriteria(LectorEmpresaConvenioVO.class)
				.add(Restrictions.eq("idLectorEmpresa", new Integer(idLectorEmpresa))).list();
			if (lista.size() == 0)
				return new HashMap();
			HashMap result = new HashMap();
			for (Iterator it = lista.iterator(); it.hasNext();)
			{
				LectorEmpresaConvenioVO lc = (LectorEmpresaConvenioVO)it.next();
				result.put("" + lc.getIdEmpresa() + "#" + lc.getIdConvenio(), "" + lc.getIdConvenio());
			}
			return result;
		} catch(Exception ex) 
		{
			log.error("LectorDAO:getGruposLector error:", ex);
			throw new DaoException("Problemas en LectorDAO:getConveniosLector:", ex);
		}
	}
	/**
	 * sucursal lector
	 * @param idLectorEmpresa
	 * @return
	 * @throws DaoException
	 */
	public HashMap getSucsLector(int idLectorEmpresa) throws DaoException 
	{
		try
		{
			log.info("LectorDAO:getSucsLector:" + idLectorEmpresa + "::");
			List lista = this.session.createCriteria(LectorEmpresaSucursalVO.class)
				.add(Restrictions.eq("idLectorEmpresa", new Integer(idLectorEmpresa))).addOrder(Order.asc("idSucursal")).list();
			if (lista.size() == 0)
				return new HashMap();
			HashMap result = new HashMap();
			for (Iterator it = lista.iterator(); it.hasNext();)
			{
				LectorEmpresaSucursalVO ls = (LectorEmpresaSucursalVO)it.next();
				String str = ls.getIdSucursal().trim();
				if (result.containsKey("" + ls.getIdEmpresa() + "#" + ls.getIdConvenio()))
					str = (String)result.get("" + ls.getIdEmpresa() + "#" + ls.getIdConvenio()) + "#" + str;
				result.put("" + ls.getIdEmpresa() + "#" + ls.getIdConvenio(), str);
				log.debug("\tsucs:" + ls.getIdEmpresa() + "#" + ls.getIdConvenio() + "::" + str + "::");
			}
			return result;
		} catch(Exception ex) 
		{
			log.error("LectorDAO:getSucsLector error:", ex);
			throw new DaoException("Problemas en LectorDAO:getSucsLector:", ex);
		}
	}
	/**
	 * borra permiso
	 * @param idUsuario
	 * @param nombre
	 * @param tipo
	 * @throws DaoException
	 */
	public void borraPermiso(int idUsuario, String nombre, Class tipo)  throws DaoException 
	{
		try 
		{
			List lista = this.session.createCriteria(tipo).add(Restrictions.eq(nombre, new Integer(idUsuario))).list();

			if(lista != null && lista.size() > 0){
				for(Iterator it = lista.iterator(); it.hasNext();)
					this.session.delete(it.next());
				this.session.flush();
			}
		} catch (Exception ex) 
		{
			throw new DaoException("Error al borrar permisos:usuario:" + idUsuario + ":tipo:" + tipo.getName() + "::", ex);
		}
	}
	/**
	 * guarda lector empresa
	 * @param lector
	 * @throws DaoException
	 */
	public void guardaLectorEmpresa(LectorEmpresaVO lector) throws DaoException 
	{
		try 
		{
			LectorEmpresaVO l = (LectorEmpresaVO)this.session.get(LectorEmpresaVO.class, new Integer(lector.getIdLectorEmpresa()));
			if(l != null)
				this.session.merge(lector);
			else
				this.session.save(lector);

			log.info("LectorDAO:lector empresa guardado:" + lector.getIdLectorEmpresa() + "::");
		} catch (Exception ex) 
		{
			throw new DaoException("Error al guardar el lector: ", ex);
		}
	}
	
	/**
	 * ccappelletti para discriminar a nivel alto si el lector empresa está habilitado
	 * @return
	 */
	public LectorEmpresaVO getLectorEmpresa (int idLectorEmpresa) {
		return  (LectorEmpresaVO)this.session.get(LectorEmpresaVO.class, new Integer(idLectorEmpresa));
	}

	/**
	 * ccappelletti para discriminar a nivel alto si está habilitado como lector entidad
	 * @return
	 */
	public LectorEntidadVO getLectorEntidad (int idLectorEntidad) {
		return  (LectorEntidadVO)this.session.get(LectorEntidadVO.class, new Integer(idLectorEntidad));
	}	
	
	/**
	 * guarda grupo convenio
	 * @param idLectorEmpresa
	 * @param idGrupoConvenio
	 * @throws DaoException
	 */
	public void guardaGrupoConvenio(int idLectorEmpresa, int idGrupoConvenio) throws DaoException 
	{
		try 
		{
			LectorEmpresaGrupoConvenioVO lgc = (LectorEmpresaGrupoConvenioVO)this.session.get(LectorEmpresaGrupoConvenioVO.class, new LectorEmpresaGrupoConvenioVO(idLectorEmpresa, idGrupoConvenio));
			if(lgc == null)
				this.session.save(new LectorEmpresaGrupoConvenioVO(idLectorEmpresa, idGrupoConvenio));
		} catch (Exception ex) 
		{
			throw new DaoException("Error al guardar el lector:guardaGrupoConvenio ", ex);
		}
	}
	/**
	 * guarda empresa
	 * @param idLectorEmpresa
	 * @param rutEmpresa
	 * @throws DaoException
	 */
	public void guardaEmpresa(int idLectorEmpresa, int rutEmpresa) throws DaoException 
	{
		try 
		{
			LectorEmpresaEmpresaVO le = (LectorEmpresaEmpresaVO)this.session.get(LectorEmpresaEmpresaVO.class, new LectorEmpresaEmpresaVO(idLectorEmpresa, rutEmpresa));
			if (le == null)
				this.session.save(new LectorEmpresaEmpresaVO(idLectorEmpresa, rutEmpresa));
		} catch (Exception ex) 
		{
			throw new DaoException("Error al guardar el lector:guardaEmpresa ", ex);
		}
	}
	/**
	 * guarda convenio
	 * @param idLectorEmpresa
	 * @param rutEmpresa
	 * @param idConvenio
	 * @throws DaoException
	 */
	public void guardaConvenio(int idLectorEmpresa, int rutEmpresa, int idConvenio)  throws DaoException 
	{
		try 
		{
			LectorEmpresaConvenioVO lec = (LectorEmpresaConvenioVO)this.session.get(LectorEmpresaConvenioVO.class, new LectorEmpresaConvenioVO(idLectorEmpresa, idConvenio, rutEmpresa));
			if (lec == null)
				this.session.save(new LectorEmpresaConvenioVO(idLectorEmpresa, idConvenio, rutEmpresa));
		} catch (Exception ex) 
		{
			throw new DaoException("Error al guardar el lector:guardaConvenio ", ex);
		}
	}
	/**
	 * guarda sucursal
	 * @param idLectorEmpresa
	 * @param rutEmpresa
	 * @param idConvenio
	 * @param idSucursal
	 * @throws DaoException
	 */
	public void guardaSucursal(int idLectorEmpresa, int rutEmpresa, int idConvenio, String idSucursal) throws DaoException 
	{
		try 
		{
			LectorEmpresaSucursalVO les = new LectorEmpresaSucursalVO();
			les.setIdLectorEmpresa(idLectorEmpresa);
			les.setIdEmpresa(rutEmpresa);
			les.setIdConvenio(idConvenio);
			les.setIdSucursal(idSucursal);
			List lista = this.session.createCriteria(LectorEmpresaSucursalVO.class)
				.add(Restrictions.eq("idLectorEmpresa", new Integer(idLectorEmpresa)))
				.add(Restrictions.eq("idEmpresa", new Integer(rutEmpresa)))
				.add(Restrictions.eq("idConvenio", new Integer(idConvenio)))
				.add(Restrictions.eq("idSucursal", new String(idSucursal)))
				.list();
			if(lista.size() == 0)
				this.session.save(les);
		} catch (Exception ex) 
		{
			throw new DaoException("Error al guardar el lector:guardaSucursal ", ex);
		}
	}
	/**
	 * borra sucursal
	 * @param idLectorEmpresa
	 * @param rutEmpresa
	 * @param idConvenio
	 * @param idSucursal
	 * @throws DaoException
	 */
	public void borraSucursal(int idLectorEmpresa, int rutEmpresa, int idConvenio, String idSucursal) throws DaoException 
	{
		try 
		{			
			List lista = this.session.createCriteria(LectorEmpresaSucursalVO.class)
				.add(Restrictions.eq("idLectorEmpresa", new Integer(idLectorEmpresa)))
				.add(Restrictions.eq("idEmpresa", new Integer(rutEmpresa)))
				.add(Restrictions.eq("idConvenio", new Integer(idConvenio)))
				.add(Restrictions.eq("idSucursal", new String(idSucursal)))
				.list();
			for(int a = 0; a < lista.size(); a++)
			{
				LectorEmpresaSucursalVO legc = (LectorEmpresaSucursalVO)lista.get(a);
				this.session.delete(legc);
			}
			this.session.flush();				
		} catch (Exception ex) {
			throw new DaoException("Error al guardar el lector:guardaSucursal ", ex);
		}
	}
	/**
	 * existe convenio
	 * @param idLectorEmpresa
	 * @param idEmpresa
	 * @param idConvenio
	 * @return
	 * @throws DaoException
	 */
	public boolean existeConvenio(int idLectorEmpresa, int idEmpresa, int idConvenio) throws DaoException 
	{
		try 
		{
			List lista = this.session.createCriteria(LectorEmpresaConvenioVO.class)
				.add(Restrictions.eq("idLectorEmpresa", new Integer(idLectorEmpresa)))
				.add(Restrictions.eq("idEmpresa", new Integer(idEmpresa)))
				.add(Restrictions.eq("idConvenio", new Integer(idConvenio)))
				.list();
			if(lista.size() > 0)
				return true;
			return false;
		} catch (Exception ex) 
		{
			throw new DaoException("Error al guardar el lector:existeEmpresa ", ex);
		}
	}
	/**
	 * existe empresa
	 * @param idLectorEmpresa
	 * @param idEmpresa
	 * @return
	 * @throws DaoException
	 */
	public boolean existeEmpresa(int idLectorEmpresa, int idEmpresa) throws DaoException 
	{
		try 
		{
			List lista = this.session.createCriteria(LectorEmpresaEmpresaVO.class)
				.add(Restrictions.eq("idLectorEmpresa", new Integer(idLectorEmpresa)))
				.add(Restrictions.eq("idEmpresa", new Integer(idEmpresa)))
				.list();
			if(lista.size()>0)
				return true;
			return false;
						
		} catch (Exception ex) 
		{
			throw new DaoException("Error al guardar el lector:existeEmpresa ", ex);
		}
	}
	/**
	 * existe grupo
	 * @param idLectorEmpresa
	 * @param idGrupoConvenio
	 * @return
	 * @throws DaoException
	 */
	public boolean existeGrupo(int idLectorEmpresa, int idGrupoConvenio) throws DaoException 
	{
		try 
		{
			List lista = this.session.createCriteria(LectorEmpresaGrupoConvenioVO.class)
				.add(Restrictions.eq("idLectorEmpresa", new Integer(idLectorEmpresa)))
				.add(Restrictions.eq("idGrupoConvenio", new Integer(idGrupoConvenio)))
				.list();
			if(lista.size()>0)
				return true;
			return false;

		} catch (Exception ex) 
		{
			throw new DaoException("Error al guardar el lector:existeGrupo ", ex);
		}
	}
	/**
	 * existe empresa
	 * @param idLectorEmpresa
	 * @param idEmpresa
	 * @param idConvenio
	 * @param idSucursal
	 * @return
	 * @throws DaoException
	 */
	public boolean existeSucursal(int idLectorEmpresa, int idEmpresa, int idConvenio, String idSucursal) throws DaoException 
	{
		try 
		{
			List lista = this.session.createCriteria(LectorEmpresaSucursalVO.class)
				.add(Restrictions.eq("idLectorEmpresa", new Integer(idLectorEmpresa)))
				.add(Restrictions.eq("idEmpresa", new Integer(idEmpresa)))
				.add(Restrictions.eq("idConvenio", new Integer(idConvenio)))
				.add(Restrictions.eq("idSucursal", new String(idSucursal)))
				.list();
			if(lista.size()>0)
				return true;
			return false;

		} catch (Exception ex) 
		{
			throw new DaoException("Error al guardar el lector:existeEmpresa ", ex);
		}
	}
	
	/**
	 * grupos lector como lista
	 * @param idLectorEmpresa
	 * @return
	 * @throws DaoException
	 */
	public List getGruposLectorList(int idLectorEmpresa) throws DaoException 
	{
		List lista = null;
		try {
			log.info("LectorDAO:getGruposLectorList:" + idLectorEmpresa + "::");
			lista = this.session.createCriteria(LectorEmpresaGrupoConvenioVO.class)
					.add(Restrictions.eq("idLectorEmpresa", new Integer(idLectorEmpresa))).list();
		} catch(Exception ex) {
			log.error("LectorDAO:getGruposLector error:", ex);
			throw new DaoException("Problemas en LectorDAO:getGruposLectorLista:", ex);
		}
		return lista;
	}
	
	/**
	 * empresas lector
	 * @param idLectorEmpresa
	 * @return
	 * @throws DaoException
	 */
	public List getEmpresasLectorList(int idLectorEmpresa) throws DaoException 
	{
		List lista = null;
		try {
			log.info("LectorDAO:getEmpresasLectorList:" + idLectorEmpresa + "::");
			lista = this.session.createCriteria(LectorEmpresaEmpresaVO.class)
				.add(Restrictions.eq("idLectorEmpresa", new Integer(idLectorEmpresa))).list();
		} catch(Exception ex) {
			log.error("LectorDAO:getEmpresasLectorList error:", ex);
			throw new DaoException("Problemas en LectorDAO:getEmpresasLectorList:", ex);
		}
		return lista;
	}

	/**
	 * convenios lector
	 * @param idLectorEmpresa
	 * @return
	 * @throws DaoException
	 */
	public List getConveniosLectorList(int idLectorEmpresa) throws DaoException 
	{
		List lista = null;		
		try {
			log.info("LectorDAO:getConveniosLectorList:" + idLectorEmpresa + "::");
			lista = this.session.createCriteria(LectorEmpresaConvenioVO.class)
				.add(Restrictions.eq("idLectorEmpresa", new Integer(idLectorEmpresa))).list();
		} catch(Exception ex) {
			log.error("LectorDAO:getGruposLectorList error:", ex);
			throw new DaoException("Problemas en LectorDAO:getConveniosLectorList:", ex);
		}
		return lista;
	}
	
	
	/**
	 * sucursal lector
	 * @param idLectorEmpresa
	 * @return
	 * @throws DaoException
	 */
	public List getSucsLectorList(int idLectorEmpresa) throws DaoException 
	{
		List lista = null;
		try
		{
			log.info("LectorDAO:getSucsLectorList:" + idLectorEmpresa + "::");
			lista = this.session.createCriteria(LectorEmpresaSucursalVO.class)
				.add(Restrictions.eq("idLectorEmpresa", new Integer(idLectorEmpresa))).addOrder(Order.asc("idSucursal")).list();

		} catch(Exception ex) 
		{
			log.error("LectorDAO:getSucsLectorList error:", ex);
			throw new DaoException("Problemas en LectorDAO:getSucsLectorList:", ex);
		}
		return lista;
	}
	
	
}
