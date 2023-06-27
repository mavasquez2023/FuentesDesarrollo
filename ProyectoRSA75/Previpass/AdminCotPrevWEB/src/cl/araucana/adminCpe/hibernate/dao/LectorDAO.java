package cl.araucana.adminCpe.hibernate.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import cl.araucana.cp.distribuidor.hibernate.beans.ConvenioVO;
import cl.araucana.cp.distribuidor.hibernate.beans.EmpresaVO;
import cl.araucana.cp.distribuidor.hibernate.beans.EncargadoConvenioVO;
import cl.araucana.cp.distribuidor.hibernate.beans.GrupoConvenioVO;
import cl.araucana.cp.distribuidor.hibernate.beans.LectorEmpresaConvenioVO;
import cl.araucana.cp.distribuidor.hibernate.beans.LectorEmpresaEmpresaVO;
import cl.araucana.cp.distribuidor.hibernate.beans.LectorEmpresaGrupoConvenioVO;
import cl.araucana.cp.distribuidor.hibernate.beans.LectorEmpresaSucursalVO;
import cl.araucana.cp.distribuidor.hibernate.beans.LectorEmpresaVO;
import cl.araucana.cp.distribuidor.hibernate.beans.PersonaVO;
import cl.araucana.cp.distribuidor.hibernate.beans.SucursalVO;
import cl.araucana.cp.distribuidor.hibernate.exceptions.DaoException;
/*
* @(#) EstadisticasDao.java 1.8 10/06/2009
*
* Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
* La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
* restringido a los sistemas de información propios de la institución.
*/

/**
 * @author jdelgado
 * @author cchamblas
 * 
 * @version 1.8
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
	 * elimina permiso
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

			for(Iterator it = lista.iterator(); it.hasNext();)
				this.session.delete(it.next());
			this.session.flush();
		} catch (Exception ex) 
		{
			throw new DaoException("Error al borrar permisos:usuario:" + idUsuario + ":tipo:" + tipo.getName() + "::", ex);
		}
	}

	/**
	 * elimina sucursal
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
						
		} catch (Exception ex) {
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
	 * existe sucursal
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
	 * existe sucursal
	 * @param idLectorEmpresa
	 * @param idEmpresa
	 * @param idConvenio
	 * @return
	 * @throws DaoException
	 */
	public boolean existeSucursal(int idLectorEmpresa, int idEmpresa, int idConvenio) throws DaoException 
	{
		try 
		{	
			List lista = this.session.createCriteria(LectorEmpresaSucursalVO.class)
				.add(Restrictions.eq("idLectorEmpresa", new Integer(idLectorEmpresa)))
				.add(Restrictions.eq("idEmpresa", new Integer(idEmpresa)))
				.add(Restrictions.eq("idConvenio", new Integer(idConvenio)))
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
	 * lista convenios
	 * @param idLectorEmpresa
	 * @param idEmpresa
	 * @return
	 * @throws Exception
	 */
	public List getConvenios(int idLectorEmpresa, int idEmpresa) throws Exception 
	{
		try
		{
			log.info("LectorDAO:getConvenios");
			List lista = this.session.createCriteria(LectorEmpresaConvenioVO.class)
				.add(Restrictions.eq("idLectorEmpresa", new Integer(idLectorEmpresa)))
				.add(Restrictions.eq("idEmpresa", new Integer(idEmpresa)))
				.list();
			return lista;
		} catch(Exception ex) 
		{
			log.error("LectorDAO:del error: " + ex.getCause().toString());
			throw new DaoException("Problemas en LectorDAO:getConvenios", ex);
		}
	}

	/**
	 * lista convenios
	 * @param idLectorEmpresa
	 * @return
	 * @throws Exception
	 */
	public List getConvenios(int idLectorEmpresa) throws Exception 
	{
		try
		{
			log.info("LectorDAO:getConvenios");
			List lista = this.session.createCriteria(LectorEmpresaConvenioVO.class)
				.add(Restrictions.eq("idLectorEmpresa", new Integer(idLectorEmpresa)))
				.list();
			return lista;
		} catch(Exception ex) {
			log.error("LectorDAO:del error: " + ex.getCause().toString());
			throw new DaoException("Problemas en LectorDAO:getConvenios", ex);
		}
	}

	/**
	 * lista empresas
	 * @param idLectorEmpresa
	 * @return
	 * @throws Exception
	 */
	public List getEmpresas(int idLectorEmpresa) throws Exception {
		try{
			log.info("LectorDAO:getEmpresas");
			List lista = this.session.createCriteria(LectorEmpresaEmpresaVO.class)
				.add(Restrictions.eq("idLectorEmpresa", new Integer(idLectorEmpresa)))
				.list();
			return lista;
		} catch(Exception ex) 
		{
			log.error("LectorDAO:del error: " + ex.getCause().toString());
			throw new DaoException("Problemas en LectorDAO:getEmpresas", ex);
		}
	}

	/**
	 * lista grupos convenio
	 * @param idLectorEmpresa
	 * @return
	 * @throws DaoException
	 */
	public List getGruposConvenio(int idLectorEmpresa) throws DaoException 
	{
		try
		{
			log.info("LectorDAO:getGruposConvenio");
			List lista = this.session.createCriteria(LectorEmpresaGrupoConvenioVO.class)
				.add(Restrictions.eq("idLectorEmpresa", new Integer(idLectorEmpresa)))
				.list();	
			return lista;
		} catch(Exception ex) 
		{
			log.error("LectorDAO:del error: " + ex.getCause().toString());
			throw new DaoException("Problemas en LectorDAO:getGruposConvenio", ex);
		}
	}

	/**
	 * lector empresa
	 * @param idLectorEmpresa
	 * @return
	 * @throws DaoException
	 */
	public LectorEmpresaVO getLectorEmpresa(int idLectorEmpresa) throws DaoException 
	{
		try{
			log.info("LectorDAO:del");
			List lista = this.session.createCriteria(LectorEmpresaVO.class)
				.add(Restrictions.eq("idLectorEmpresa", new Integer(idLectorEmpresa)))
				.list();
			for (Iterator it = lista.iterator(); it.hasNext();) {
				return (LectorEmpresaVO)it.next();
			}
			return null;
		} catch(Exception ex) {
			log.error("BancoDAO:del error: " + ex.getCause().toString());
			throw new DaoException("Problemas en BancoDAO:del", ex);
		}
	}

	/**
	 * nivel acceso
	 * @param idEmpresa
	 * @param idConvenio
	 * @param idEncargado
	 * @return
	 * @throws DaoException
	 */
	public int getNivelAcceso(int idEmpresa, int idConvenio, int idEncargado) throws DaoException 
	{
		try{
			log.info("LectorDAO:getEmpresas");
			List lista = this.session.createCriteria(EncargadoConvenioVO.class)
				.add(Restrictions.eq("idEmpresa", new Integer(idEmpresa)))
				.add(Restrictions.eq("idConvenio", new Integer(idConvenio)))
				.add(Restrictions.eq("idEncargado", new Integer(idEncargado)))
				.list();
			int resultado = 0;
			for(int a=0;a<lista.size();a++){
				EncargadoConvenioVO enc = (EncargadoConvenioVO)lista.get(a);
				resultado=enc.getIdNivelAcceso();
			}
			return resultado;
		} catch(Exception ex) {
			log.error("LectorDAO:del error: " + ex.getCause().toString());
			throw new DaoException("Problemas en LectorDAO:getEmpresas", ex);
		}
	}
	
	/**
	 * sucursal 
	 * @param idEmpresa
	 * @param idSucursal
	 * @return
	 * @throws DaoException
	 */
	public SucursalVO getSucursal(int idEmpresa, String idSucursal) throws DaoException 
	{
		try{
			log.info("LectorDAO:getSucursal");
			return (SucursalVO)this.session.createCriteria(SucursalVO.class)
				.add(Restrictions.eq("idEmpresa", new Integer(idEmpresa)))
				.add(Restrictions.eq("idSucursal", new String(idSucursal)))
				.uniqueResult();
		} catch(Exception ex) {
			log.error("LectorDAO:del error: " + ex.getCause().toString());
			throw new DaoException("Problemas en LectorDAO:getSucursal", ex);
		}
	}
	/**
	 * lista sucursales
	 * @param idLectorEmpresa
	 * @param idEmpresa
	 * @param idConvenio
	 * @return
	 * @throws DaoException
	 */
	public List getSucursales(int idLectorEmpresa, int idEmpresa, int idConvenio) throws DaoException
	{
		try
		{
			log.info("LectorDAO:getConvenios");
			List lista = this.session.createCriteria(LectorEmpresaSucursalVO.class)
				.add(Restrictions.eq("idLectorEmpresa", new Integer(idLectorEmpresa)))
				.add(Restrictions.eq("idEmpresa", new Integer(idEmpresa)))
				.add(Restrictions.eq("idConvenio", new Integer(idConvenio)))
				.list();
			return lista;
		} catch(Exception ex) 
		{
			log.error("LectorDAO:del error: " + ex.getCause().toString());
			throw new DaoException("Problemas en LectorDAO:getConvenios", ex);
		}
	}

	/**
	 * lista sucursales
	 * @param idLectorEmpresa
	 * @return
	 * @throws Exception
	 */
	public List getSucursales(int idLectorEmpresa) throws Exception 
	{
		try
		{
			log.info("LectorDAO:getSucursales");
			this.session.flush();
			List lista = this.session.createCriteria(LectorEmpresaSucursalVO.class)
				.add(Restrictions.eq("idLectorEmpresa", new Integer(idLectorEmpresa)))
				.list();
			return lista;
		} catch(Exception ex) 
		{
			log.error("LectorDAO:del error: " + ex.getCause().toString());
			throw new DaoException("Problemas en LectorDAO:getSucursales", ex);
		}
	}

	/**
	 * guarda sucursal
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
	 * guarda nivel acceso
	 * @param idLectorEmpresa
	 * @param rutEmpresa
	 * @param idConvenio
	 * @param idNivelAcceso
	 * @throws Exception
	 */
	public void guardaNivelAcceso(int idLectorEmpresa, int rutEmpresa, int idConvenio, int idNivelAcceso)  throws Exception 
	{
		try
		{
			log.info("LectorDAO:guardaNivelAcceso");
			List lista = this.session.createCriteria(EncargadoConvenioVO.class)
				.add(Restrictions.eq("idEmpresa", new Integer(rutEmpresa)))
				.add(Restrictions.eq("idConvenio", new Integer(idConvenio)))
				.add(Restrictions.eq("idEncargado", new Integer(idLectorEmpresa)))
				.add(Restrictions.eq("idNivelAcceso", new Integer(idNivelAcceso)))
				.list();
			if(lista.size()==0){
				EncargadoConvenioVO enc = new EncargadoConvenioVO();
				enc.setIdEmpresa(rutEmpresa);
				enc.setIdConvenio(idConvenio);
				enc.setIdEncargado(idLectorEmpresa);
				enc.setIdNivelAcceso(idNivelAcceso);
				this.session.save(enc);
			}
			
		} catch(Exception ex) {
			log.error("LectorDAO:del error: " + ex.getCause().toString());
			throw new DaoException("Problemas en LectorDAO:guardaNivelAcceso", ex);
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
	 * lista grupos convenio
	 * @param idLectorEmpresa
	 * @return
	 * @throws DaoException
	 */
	public List getGruposConvenioVO(int idLectorEmpresa) throws DaoException 
	{
		try
		{
			log.info("LectorDAO:getGruposConvenioVO");
			List lista = this.session.createCriteria(LectorEmpresaGrupoConvenioVO.class)
				.add(Restrictions.eq("idLectorEmpresa", new Integer(idLectorEmpresa))).list();
			if (lista.size() == 0)
				return new ArrayList();

			List ids = new ArrayList();
			for (Iterator it = lista.iterator(); it.hasNext();)
				ids.add(new Integer(((LectorEmpresaGrupoConvenioVO)it.next()).getIdGrupoConvenio()));
			if (!ids.isEmpty())
				return this.session.createCriteria(GrupoConvenioVO.class)
					.add(Restrictions.in("idGrupoConvenio", ids))
					.list();
			return new ArrayList();
		} catch(Exception ex) 
		{
			log.error("LectorDAO:getGruposConvenioVO error:", ex);
			throw new DaoException("Problemas en LectorDAO:getGruposConvenioVO", ex);
		}
	}

	/**
	 * lista empresas
	 * @param idLectorEmpresa
	 * @return
	 * @throws DaoException
	 */
	public List getEmpresasVO(int idLectorEmpresa) throws DaoException 
	{
		try
		{
			log.info("LectorDAO:getEmpresasVO");
			List lista = this.session.createCriteria(LectorEmpresaEmpresaVO.class)
				.add(Restrictions.eq("idLectorEmpresa", new Integer(idLectorEmpresa))).list();
			if (lista.size() == 0)
				return new ArrayList();

			List ids = new ArrayList();
			for (Iterator it = lista.iterator(); it.hasNext();)
				ids.add(new Integer(((LectorEmpresaEmpresaVO)it.next()).getIdEmpresa()));
			if (!ids.isEmpty())
				return this.session.createCriteria(EmpresaVO.class)
					.add(Restrictions.in("idEmpresa", ids))
					.list();
			return new ArrayList();
		} catch(Exception ex) 
		{
			log.error("LectorDAO:getEmpresasVO error:", ex);
			throw new DaoException("Problemas en LectorDAO:getEmpresasVO", ex);
		}
	}

	/**
	 * lista convenios
	 * @param idLectorEmpresa
	 * @return
	 * @throws DaoException
	 */
	public List getConveniosVO(int idLectorEmpresa) throws DaoException 
	{
		try
		{
			log.info("LectorDAO:getConveniosVO");
			List lista = this.session.createCriteria(LectorEmpresaConvenioVO.class)
				.add(Restrictions.eq("idLectorEmpresa", new Integer(idLectorEmpresa)))
				.addOrder(Order.asc("idEmpresa")).list();
			if (lista.size() == 0)
				return new ArrayList();

			List result = new ArrayList();
			for (Iterator it = lista.iterator(); it.hasNext();)
			{
				LectorEmpresaConvenioVO lec = (LectorEmpresaConvenioVO)it.next();
				result.addAll(this.session.createCriteria(ConvenioVO.class)
						.add(Restrictions.eq("idEmpresa", new Integer(lec.getIdEmpresa())))
						.add(Restrictions.eq("idConvenio", new Integer(lec.getIdConvenio())))
						.list());
			}
			return result;
		} catch(Exception ex) 
		{
			log.error("LectorDAO:getConveniosVO error:", ex);
			throw new DaoException("Problemas en LectorDAO:getConveniosVO", ex);
		}
	}

	/**
	 * lista sucursales
	 * @param idLectorEmpresa
	 * @param idEmpresa
	 * @param idConvenio
	 * @return
	 * @throws DaoException
	 */
	public List getSucursalesVO(int idLectorEmpresa, int idEmpresa, int idConvenio) throws DaoException 
	{
		try
		{
			List lista = this.session.createCriteria(LectorEmpresaSucursalVO.class)
				.add(Restrictions.eq("idLectorEmpresa", new Integer(idLectorEmpresa)))
				.add(Restrictions.eq("idEmpresa", new Integer(idEmpresa)))
				.add(Restrictions.eq("idConvenio", new Integer(idConvenio))).list();
			if (lista.size() == 0)
				return new ArrayList();

			List ids = new ArrayList();
			for (Iterator it = lista.iterator(); it.hasNext();)
				ids.add(((LectorEmpresaSucursalVO)it.next()).getIdSucursal().trim());
			if (!ids.isEmpty())
				return this.session.createCriteria(SucursalVO.class)
					.add(Restrictions.eq("idEmpresa", new Integer(idEmpresa)))
					.add(Restrictions.in("idSucursal", ids))
					.list();
			return new ArrayList();
		} catch(Exception ex) 
		{
			log.error("LectorDAO:getSucursalesVO error:", ex);
			throw new DaoException("Problemas en LectorDAO:getSucursalesVO", ex);
		}
	}

	/**
	 * lista sucursales
	 * @param idLectorEmpresa
	 * @return
	 * @throws DaoException
	 */
	public List getSucursalesVO(int idLectorEmpresa) throws DaoException 
	{
		try
		{
			List lista = this.session.createCriteria(LectorEmpresaSucursalVO.class)
				.add(Restrictions.eq("idLectorEmpresa", new Integer(idLectorEmpresa))).list();
			if (lista.size() == 0)
				return new ArrayList();

			List ids = new ArrayList();
			for (Iterator it = lista.iterator(); it.hasNext();)
				ids.add(((LectorEmpresaSucursalVO)it.next()).getIdSucursal().trim());
			if (!ids.isEmpty())
				return this.session.createCriteria(SucursalVO.class)
					.add(Restrictions.in("idSucursal", ids))
					.list();
			return new ArrayList();
		} catch(Exception ex) 
		{
			log.error("LectorDAO:getSucursalesVO error:", ex);
			throw new DaoException("Problemas en LectorDAO:getSucursalesVO", ex);
		}
	}

	/**
	 * lista lector grupo
	 * @param idGrupo
	 * @return
	 * @throws DaoException
	 */
	public List getLectorXGrupo(int idGrupo) throws DaoException 
	{
		try
		{
			log.info("LectorDAO:getLectorXGrupo");
			List lista = this.session.createCriteria(LectorEmpresaGrupoConvenioVO.class)
				.add(Restrictions.eq("idGrupoConvenio", new Integer(idGrupo))).list();
			if (lista.size() == 0)
				return new ArrayList();

			List ids = new ArrayList();
			for (Iterator it = lista.iterator(); it.hasNext();)
				ids.add(new Integer(((LectorEmpresaGrupoConvenioVO)it.next()).getIdLectorEmpresa()));
			if (!ids.isEmpty())
				return this.session.createCriteria(PersonaVO.class)
					.add(Restrictions.in("idPersona", ids))
					.list();
			return new ArrayList();
		} catch(Exception ex) 
		{
			log.error("LectorDAO:getLectorXGrupo error:", ex);
			throw new DaoException("Problemas en LectorDAO:getLectorXGrupo", ex);
		}
	}

	/**
	 * lista lector empresa
	 * @param idEmpresa
	 * @return
	 * @throws DaoException
	 */
	public List getLectorXEmpresa(int idEmpresa) throws DaoException 
	{
		try
		{
			log.info("LectorDAO:getLectorXEmpresa");
			List lista = this.session.createCriteria(LectorEmpresaEmpresaVO.class)
				.add(Restrictions.eq("idEmpresa", new Integer(idEmpresa))).list();
			if (lista.size() == 0)
				return new ArrayList();

			List ids = new ArrayList();
			for (Iterator it = lista.iterator(); it.hasNext();)
				ids.add(new Integer(((LectorEmpresaEmpresaVO)it.next()).getIdLectorEmpresa()));
			if (!ids.isEmpty())
				return this.session.createCriteria(PersonaVO.class)
					.add(Restrictions.in("idPersona", ids))
					.list();
			return new ArrayList();
		} catch(Exception ex) 
		{
			log.error("LectorDAO:getLectorXEmpresa error:", ex);
			throw new DaoException("Problemas en LectorDAO:getLectorXEmpresa", ex);
		}
	}

	/**
	 * lista lector convenio
	 * @param idEmpresa
	 * @param idConvenio
	 * @return
	 * @throws DaoException
	 */
	public List getLectorXConvenio(int idEmpresa, int idConvenio) throws DaoException 
	{
		try
		{
			log.info("LectorDAO:getLectorXConvenio");
			List lista = this.session.createCriteria(LectorEmpresaConvenioVO.class)
				.add(Restrictions.eq("idEmpresa", new Integer(idEmpresa))).add(Restrictions.eq("idConvenio", new Integer(idConvenio))).list();
			if (lista.size() == 0)
				return new ArrayList();

			List ids = new ArrayList();
			for (Iterator it = lista.iterator(); it.hasNext();)
				ids.add(new Integer(((LectorEmpresaConvenioVO)it.next()).getIdLectorEmpresa()));
			if (!ids.isEmpty())
				return this.session.createCriteria(PersonaVO.class)
					.add(Restrictions.in("idPersona", ids))
					.list();
			return new ArrayList();
		} catch(Exception ex) 
		{
			log.error("LectorDAO:getLectorXConvenio error:", ex);
			throw new DaoException("Problemas en LectorDAO:getLectorXConvenio", ex);
		}
	}

	/**
	 * lista lector sucursal
	 * @param idEmpresa
	 * @param idConvenio
	 * @return
	 * @throws DaoException
	 */
	public List getLectoresSucursal(int idEmpresa, int idConvenio) throws DaoException 
	{
		try
		{
			log.info("LectorDAO:getLectoresSucursal");
			return this.session.createCriteria(LectorEmpresaSucursalVO.class)
				.add(Restrictions.eq("idEmpresa", new Integer(idEmpresa))).add(Restrictions.eq("idConvenio", new Integer(idConvenio))).list();
		} catch(Exception ex) 
		{
			log.error("LectorDAO:getLectoresSucursal error:", ex);
			throw new DaoException("Problemas en LectorDAO:getLectoresSucursal", ex);
		}
	}
}
