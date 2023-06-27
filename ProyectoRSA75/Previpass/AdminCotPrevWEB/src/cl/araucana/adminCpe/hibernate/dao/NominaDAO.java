package cl.araucana.adminCpe.hibernate.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Ehcache;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import cl.araucana.cp.distribuidor.base.Constants;
import cl.araucana.cp.distribuidor.hibernate.beans.CausaDCVO;
import cl.araucana.cp.distribuidor.hibernate.beans.CausaGRVO;
import cl.araucana.cp.distribuidor.hibernate.beans.CausaRAVO;
import cl.araucana.cp.distribuidor.hibernate.beans.CausaREVO;
import cl.araucana.cp.distribuidor.hibernate.beans.ComprobanteVO;
import cl.araucana.cp.distribuidor.hibernate.beans.ConfigPDFVO;
import cl.araucana.cp.distribuidor.hibernate.beans.CotizacionDCVO;
import cl.araucana.cp.distribuidor.hibernate.beans.CotizacionGRVO;
import cl.araucana.cp.distribuidor.hibernate.beans.CotizacionPendienteDCVO;
import cl.araucana.cp.distribuidor.hibernate.beans.CotizacionPendienteGRVO;
import cl.araucana.cp.distribuidor.hibernate.beans.CotizacionPendienteRAVO;
import cl.araucana.cp.distribuidor.hibernate.beans.CotizacionPendienteREVO;
import cl.araucana.cp.distribuidor.hibernate.beans.CotizacionRAVO;
import cl.araucana.cp.distribuidor.hibernate.beans.CotizacionREVO;
import cl.araucana.cp.distribuidor.hibernate.beans.CotizanteVO;
import cl.araucana.cp.distribuidor.hibernate.beans.EmpresaVO;
import cl.araucana.cp.distribuidor.hibernate.beans.NominaDCVO;
import cl.araucana.cp.distribuidor.hibernate.beans.NominaGRVO;
import cl.araucana.cp.distribuidor.hibernate.beans.NominaRAVO;
import cl.araucana.cp.distribuidor.hibernate.beans.NominaREVO;
import cl.araucana.cp.distribuidor.hibernate.beans.NominaVO;
import cl.araucana.cp.distribuidor.hibernate.beans.TipoNominaVO;
import cl.araucana.cp.distribuidor.hibernate.exceptions.DaoException;
import cl.araucana.cp.util.vo.DocumentoVO;

/**
 * @author ccostagliola
 * @author acuña
 * 
 * @version 1.13
 */
public class NominaDAO
{
	private static Logger log = Logger.getLogger(NominaDAO.class);
	private Session session;

	public NominaDAO(Session session)
	{
		this.session = session;
	}

	/**
	 * collection tipo de nominas
	 * 
	 * @return
	 * @throws DaoException
	 */
	public List getTiposNominas() throws DaoException
	{
		try
		{
			return this.session.createCriteria(TipoNominaVO.class).addOrder(Order.asc("orden")).list();
		} catch (Exception ex)
		{
			log.error("Error en NominaDAO.getNominas", ex);
			throw new DaoException("Error en NominaDAO.getNominas", ex);
		}
	}

	/**
	 * eliminar nominas
	 * 
	 * @return
	 * @throws DaoException
	 */
	public String borraNominas(Set nominas) throws DaoException
	{
		String respuesta = "borraNominas";
		log.info("NominaDAO:borraNominas inicio proceso");
		try 
		{
			borraDataConvenio(NominaREVO.class, new Class[] { CotizacionREVO.class, CausaREVO.class, CotizacionPendienteREVO.class });
			borraDataConvenio(NominaGRVO.class, new Class[] { CotizacionGRVO.class, CausaGRVO.class, CotizacionPendienteGRVO.class });
			borraDataConvenio(NominaRAVO.class, new Class[] { CotizacionRAVO.class, CausaRAVO.class, CotizacionPendienteRAVO.class });
			borraDataConvenio(NominaDCVO.class, new Class[] { CotizacionDCVO.class, CausaDCVO.class, CotizacionPendienteDCVO.class });
			borrar("nomina remu", NominaREVO.class.getName(), nominas);
			borrar("nomina grati", NominaGRVO.class.getName(), nominas);
			borrar("nomina reli", NominaRAVO.class.getName(), nominas);
			borrar("nomina depo", NominaDCVO.class.getName(), nominas);
			borraCotizantes();
			//GMALLEA 26-04-2012 Se actualiza el idEstado a no pagado ya que solo quedan nominas de independientes
			this.actualizaEstadoNominaRe();
			respuesta = "";
		} catch (Exception ex)
		{
			log.error("Error en NominaDAO.seteaNominas", ex);
			throw new DaoException("Error en NominaDAO.seteaNominas", ex);
		}
		log.info("NominaDAO:limpiezaNominas fin proceso");
		return respuesta;
	}

	private void borraCotizantes()
	{
		List convenios = this.session.createQuery("select distinct c.rutEmpresa, c.idConvenio from " + CotizanteVO.class.getName() +" c ,"+ EmpresaVO.class.getName() +" e "+
												 "	where c.rutEmpresa = e.idEmpresa and e.tipo='"+Constants.TIPO_EMPRESA+"' ").list();
		if (convenios == null)
			return;
		for (Iterator iter = convenios.iterator(); iter.hasNext();)
		{
			Object[] convenio = (Object[]) iter.next();
			borraDataConvenio(((Number) convenio[0]).intValue(), ((Number) convenio[1]).intValue(), CotizanteVO.class);
		}
	}

	private void borraDataConvenio(Class tipoNomina, Class[] clasesDependientes)
	{
		for (Iterator iter = this.session.createCriteria(tipoNomina).list().iterator(); iter.hasNext();)
		{//TODO GMALLEA CIERRE PERIODO NOMINAS
			NominaVO nomina = (NominaVO) iter.next();
			if(!nomina.getEmpresa().getTipo().equals(Constants.TIPO_EMPRESA_INDEPENDIENTE)){
				for (int i = 0; i < clasesDependientes.length; i++){
						borraDataConvenio(nomina.getRutEmpresa(), nomina.getIdConvenio(), clasesDependientes[i]);
				}
			}
		}
	}

	/**
	 * Se limpia la tabla canvenio
	 * 
	 * @param int rutEmpresa
	 * @param int idConvenio
	 * @param Class clase
	 * @return
	 */
	private void borraDataConvenio(int rutEmpresa, int idConvenio, Class clase)
	{
		log.info("rutEmpresa/Convenio[" + rutEmpresa + '/' + idConvenio + "] Borrando " + clase.getName());
		Query query = this.session.createQuery("delete from " + clase.getName() + " where rutEmpresa = ? and idConvenio = ?");
		query.setInteger(0, rutEmpresa);
		query.setInteger(1, idConvenio);
		log.info(new StringBuffer(clase.getName()).append(" eliminadas:").append(query.executeUpdate()));
	}

	private void borrar(String msg, String clase, Set nominas)
	{
		Date ini = new Date();
		if(nominas.size() > 0){
			String where = "where rutEmpresa in (";
			for(Iterator it = nominas.iterator() ; it.hasNext();){
				where = where + (Integer) it.next() +",";
			}
			String whereIN =  where.substring(0,where.length()-1);
			whereIN = whereIN + ")";
			Query query = this.session.createQuery("DELETE FROM " + clase +" "+ whereIN);
			
			int count = query.executeUpdate();
			Date fin = new Date();
			log.info(count + ":" + msg + " eliminados:\t\tdiff:" + (fin.getTime() - ini.getTime()) + "::");
	
		}
	}

	/**
	 * eliminar comprobantes
	 * 
	 * @return
	 * @throws DaoException
	 */
	public String borraComprobantes() throws DaoException
	{
		List listaBorrar;
		String respuesta = "borraComprobantes";
		log.info("NominaDAO:borraComprobantes inicio proceso");
		try
		{
			listaBorrar = this.session.createCriteria(ConfigPDFVO.class).list();
			
			for (Iterator it = listaBorrar.iterator(); it.hasNext();){
				
				ConfigPDFVO configPDFVO =  (ConfigPDFVO) it.next();
				
				//TODO GMALLEA CIERRE PERIODO FOLIO
				ComprobanteDAO comprobanteDAO = new ComprobanteDAO(session);
				NominaVO nominaVO = comprobanteDAO.getNomina(configPDFVO.getIdCodigoBarra());
				
					//TODO GMALLEA Si la nomina es null significa que es espresa porque ya se borraron todas las nominas de tipo empresa 
					if(nominaVO == null ){
							this.session.delete(configPDFVO);
							log.info(" * configPDFVO Eliminada * ");
					}
			}
			this.session.flush();
			listaBorrar = this.session.createCriteria(ComprobanteVO.class).list();
			for (Iterator it = listaBorrar.iterator(); it.hasNext();){
				
				
				ComprobanteVO comprobanteVO = (ComprobanteVO)it.next();
			
				//TODO GMALLEA CIERRE PERIODO FOLIO
				ComprobanteDAO comprobanteDAO = new ComprobanteDAO(session);
				NominaVO nominaVO = comprobanteDAO.getNomina(comprobanteVO.getIdCodigoBarra());
				
					//TODO GMALLEA Si la nomina es null significa que es espresa porque ya se borraron todas las nominas de tipo empresa
					if(nominaVO == null ){
						this.session.delete(comprobanteVO);
						log.info(" * comprobanteVO Eliminada * ");
						
					}
			}

			this.session.flush();
			respuesta = "";
		} catch (Exception ex)
		{
			log.error("Error en NominaDAO.seteaComprobante", ex);
			throw new DaoException("Error en NominaDAO.seteaComprobante", ex);
		}
		log.info("NominaDAO:limpiezaComprobante fin proceso");
		return respuesta;
	}

	/**
	 * contador
	 * 
	 * @param tipo
	 * @return
	 * @throws DaoException
	 */
	public Integer count(Class tipo) throws DaoException
	{
		try
		{
			List r = this.session.createCriteria(tipo).setProjection(Projections.rowCount()).list();
			return (Integer) r.get(0);
		} catch (Exception ex)
		{
			log.error("Error en NominaDAO:count:tipo:" + tipo.getName() + "::", ex);
			throw new DaoException("Error en NominaDAO:count", ex);
		}
	}

	/**
	 * limpia cache
	 * 
	 */
	public void limpiaCache()
	{
		try
		{
			log.info("limpiaCacheWeb");
			CacheManager.create();
			CacheManager cm = CacheManager.getInstance();
			String[] nombres = cm.getCacheNames();

			if (nombres != null)
			{
				for (int i = 0; i < nombres.length; i++)
				{
					Ehcache ehCache = cm.getEhcache(nombres[i]);
					ehCache.removeAll();
					ehCache.clearStatistics();
				}
			}
		} catch (Exception e)
		{
			log.error("ERROR en limpiaCache WEB:", e);
		}
	}

	/**
	 * Consulta la Nominas de Gratificacion.
	 * 
	 * @param rutEmpresa
	 * @param idGrupoConvenio
	 * @param idEstado
	 * @return
	 */
	public List getNominaPorGrupo(int rutEmpresa, int idGrupoConvenio, int idEstado, String tipoNomina) throws DaoException
	{
		try
		{
			log.info("NominaDAO:getNominaPorGrupo:rutEmpresa:" + rutEmpresa + ":idGrupoConvenio:" + idGrupoConvenio + ":idEstado:" + idEstado + ":tipoNomina:" + tipoNomina + "::");

			List params = new ArrayList();
			String nombre = "";
			if (tipoNomina.equals("R"))
				nombre = NominaREVO.class.getName();
			else if (tipoNomina.equals("G"))
				nombre = NominaGRVO.class.getName();
			else if (tipoNomina.equals("A"))
				nombre = NominaRAVO.class.getName();
			else
				nombre = NominaDCVO.class.getName();
			StringBuffer hqlQuery = new StringBuffer("from " + nombre + " nomina where nomina.rutEmpresa !=" + new Integer(Constants.RUT_EMPRESA_FALSA));

			if (rutEmpresa > 0)
			{
				hqlQuery.append(" and cast(cast(nomina.rutEmpresa as integer) as string) like ?");
				params.add(rutEmpresa + "%");
			}
			if (idGrupoConvenio > 0)
			{
				hqlQuery.append(" and nomina.idConvenio = ?");
				params.add(new Integer(idGrupoConvenio));
			}

			if (idEstado != 0)
			{
				hqlQuery.append(" and nomina.idEstado = ?");
				params.add(new Integer(idEstado));
			}

			hqlQuery.append(" order by nomina.rutEmpresa");
			Query query = this.session.createQuery(hqlQuery.toString());
			int par = 0;
			for (Iterator iter = params.iterator(); iter.hasNext();)
				query.setParameter(par++, iter.next());
			log.info("query busqueda:" + hqlQuery.toString() + "::");
			return query.list();

		} catch (Exception ex)
		{
			log.error("ERROR NominaDAO:getNominaPorGrupo()", ex);
			throw new DaoException("Problemas en getNominaPorGrupo", ex);
		}
	}

	/**
	 * de acuerto al tipo de nomina recibida ('R', 'G', 'A', 'D') retorna el nombre almacenado en DB2
	 * 
	 * @param tipoNomina
	 * @return
	 * @throws DaoException
	 */
	public String getNombreTipoNomina(String tipoNomina) throws DaoException
	{
		try
		{
			TipoNominaVO tn = (TipoNominaVO) this.session.get(TipoNominaVO.class, tipoNomina);
			if (tn != null)
				return tn.getDescripcion().trim();
			return "";
		} catch (Exception ex)
		{
			throw new DaoException("Error en NominaDAO:getNombreTipoNomina:", ex);
		}
	}

	/**
	 * busca en DB2 la nomina asociada al tipo de nomina, empresa y convenio indicado
	 * 
	 * @param tipoNomina
	 * @param idEmpresa
	 * @param idConvenio
	 * @return retorna nomina encontrada, o null si no existe
	 * @throws DaoException
	 */
	public NominaVO getNomina(String tipoNomina, long idEmpresa, int idConvenio) throws DaoException
	{
		NominaVO n = null;
		if (tipoNomina.equals("R"))
			n = new NominaREVO(idConvenio, (int) idEmpresa);
		else if (tipoNomina.equals("A"))
			n = new NominaRAVO(idConvenio, (int) idEmpresa);
		else if (tipoNomina.equals("G"))
			n = new NominaGRVO(idConvenio, (int) idEmpresa);
		else
			n = new NominaDCVO(idConvenio, (int) idEmpresa);

		try
		{
			Object o = this.session.get(n.getClass(), n);
			if (o != null)
				return (NominaVO) o;
			return null;
		} catch (Exception ex)
		{
			log.error("Ha ocurrido una excepcion en NominaDAO:getNomina:", ex);
			throw new DaoException("No se encontro la nomina: " + tipoNomina + ", rut: " + idEmpresa + ", convenio: " + idConvenio);
		}
	}

	/**
	 * nomina
	 * 
	 * @param codigoBarra
	 * @return
	 * @throws DaoException
	 */
	public NominaVO getNomina(String tipoNomina, long codigoBarra) throws DaoException
	{
		try
		{
			log.info("NominaDAO:getNomina(codigoBarra: " + codigoBarra + ")");

			if (tipoNomina.equals("R"))
				return (NominaVO) this.session.createCriteria(NominaREVO.class).add(Restrictions.eq("idCodigoBarras", new Long(codigoBarra))).uniqueResult();
			else if (tipoNomina.equals("G"))
				return (NominaVO) this.session.createCriteria(NominaGRVO.class).add(Restrictions.eq("idCodigoBarras", new Long(codigoBarra))).uniqueResult();
			else if (tipoNomina.equals("A"))
				return (NominaVO) this.session.createCriteria(NominaRAVO.class).add(Restrictions.eq("idCodigoBarras", new Long(codigoBarra))).uniqueResult();
			else if (tipoNomina.equals("D"))
				return (NominaVO) this.session.createCriteria(NominaDCVO.class).add(Restrictions.eq("idCodigoBarras", new Long(codigoBarra))).uniqueResult();

			return null;
		} catch (Exception ex)
		{
			log.error("ERROR NominaDAO:getNomina codigoBarra:" + codigoBarra + ": ", ex);
			throw new DaoException("Problemas obteniendo la nomina a partir del comprobante:" + codigoBarra + "::", ex);
		}
	}

	public DocumentoVO getLastDocumento(int idEmpresa, String tipo, int idConvenio) throws DaoException
	{
		try
		{
			List l = this.session.createCriteria(DocumentoVO.class).add(Restrictions.eq("rutEmpresa", new Integer(idEmpresa))).add(Restrictions.eq("idConvenio", new Integer(idConvenio))).add(
					Restrictions.eq("tipoProceso", tipo)).addOrder(Order.desc("idEnvio")).list();
			if (l.size() > 0)
				return (DocumentoVO) l.get(0);
			return null;
		} catch (Exception ex)
		{
			log.error("ERROR NominaDAO:ggetLastDocumento:" + idEmpresa + "::" + idConvenio + ": ", ex);
			throw new DaoException("Problemas obteniendo ultimo documento de una nomina: " + idEmpresa + "::" + idConvenio + "::", ex);
		}
	}
	
	/**
	 * Borra CRC de todas las nóminas asociadas a un Grupo de Convenio.
	 *
	 * @param idEmpresa
	 * @throws DaoException
	 */
	public void borraCRCporGrupoConvenio(int idGrupoConvenio) throws DaoException {
		try {
			String select;
			String[] nominas = { "NominaREVO"
							   , "NominaRAVO"
							   , "NominaGRVO"
							   , "NominaDCVO" };
			char[] tipoNomina = { 'R'
								, 'A'
								, 'G'
								, 'D' };

			for (int i = 0; i < nominas.length; i++) {
				select = new String();
				select = "SELECT n"									+
						 "  FROM " + nominas[i] + " AS n"			+
						      ", ConvenioVO AS c"					+
						 " WHERE c.idEmpresa       = n.rutEmpresa"	+
			 			 "   AND c.idConvenio      = n.idConvenio"	+
			 			 "   AND c.idGrupoConvenio = " + idGrupoConvenio;

				List result = this.session.createQuery(select).list();

				if (result.size() > 0) {
					for (Iterator it = result.iterator(); it.hasNext(); ) {
						NominaVO nomina = (NominaVO) it.next();
						this.borraCRC(nomina.getRutEmpresa(), nomina.getIdConvenio(), tipoNomina[i]);
					}
				}
			}
		} catch (Exception ex) {
			log.error("problemas al borraCRCporGrupoConvenio:", ex);
			throw new DaoException("ERROR NominaDAO:borraCRCporGrupoConvenio:", ex);
		}
	}

	/**
	 * Borra CRC de una nómina específica.
	 *
	 * @param rutEmpresa
	 * @param idConvenio
	 * @param tipoProceso
	 * @throws DaoException
	 */
	public void borraCRC(int rutEmpresa, int idConvenio, char tipoProceso) throws DaoException {
		try {
			NominaVO nomina = this.getNomina("" + tipoProceso, rutEmpresa, idConvenio);
			if (nomina != null) {
				nomina.setCrc(0);
				this.session.merge(nomina);
			}
		} catch (Exception ex) {
			log.error("problemas al borraCRC:", ex);
			throw new DaoException("ERROR NominaDAO:borraCRC:", ex);
		}
	}
		public List getTipoNomina(String tipoEmpresa)throws DaoException{
		
		try
		{
			List lis = new ArrayList();
			Query query = this.session.createQuery("select e.idEmpresa from " +NominaREVO.class.getName() +" n, "+ EmpresaVO.class.getName() +" e "+
												   " where n.rutEmpresa = e.idEmpresa "+
												   " and e.tipo = '"+tipoEmpresa+"')");
			
			lis.addAll(query.list());
			
			query = this.session.createQuery("select e.idEmpresa from " +NominaDCVO.class.getName() +" n, "+ EmpresaVO.class.getName() +" e "+
					   " where n.rutEmpresa = e.idEmpresa "+
					   " and e.tipo = '"+tipoEmpresa+"')");

			lis.addAll(query.list());

			query = this.session.createQuery("select e.idEmpresa from " +NominaGRVO.class.getName() +" n, "+ EmpresaVO.class.getName() +" e "+
					   " where n.rutEmpresa = e.idEmpresa "+
					   " and e.tipo = '"+tipoEmpresa+"')");

			lis.addAll(query.list());
			
			query = this.session.createQuery("select e.idEmpresa from " +NominaRAVO.class.getName() +" n, "+ EmpresaVO.class.getName() +" e "+
					   " where n.rutEmpresa = e.idEmpresa "+
					   " and e.tipo = '"+tipoEmpresa+"')");

			lis.addAll(query.list());
			
			
			//Se eliminan los repetidos del arreglo
			HashSet hs = new HashSet();
			hs.addAll(lis);
			 
			lis.clear();
			lis.addAll(hs);
			
			return lis;
		
		} catch (Exception ex)
		{
			log.error("ERROR EmpresaDAO.getTipoNomina(" + tipoEmpresa + ")");
			throw new DaoException("Problemas en getTipoNomina", ex);
		}
	}
		
		/**
		 * Actualiza el codigo de barra de la nomina segun el rut empresa, lo utiliza solo los independientes
		 *
		 * @author gmallea
		 *
		 * @param long idCodigoBarra
		 * @param long rutEmpresa
		 * @param int idConvenio
		 * @throws DaoException
		 */
		public void actualizaNominaRe(long idCodigoBarra , long rutEmpresa, int idConvenio) throws DaoException
		{
			try
			{			Date ini = new Date();
						
									
						Query query = this.session.createQuery(" UPDATE NominaREVO "+  
															   " SET idCodigoBarras = "+idCodigoBarra+
															   " WHERE rutEmpresa="+ rutEmpresa +" AND idConvenio="+ idConvenio);
								
						int count = query.executeUpdate();
						
						Date fin = new Date();
						log.info("ConfigPDFVO : "+ count + " "+ idCodigoBarra + " eliminados:\t\tdiff:" + (fin.getTime() - ini.getTime()) + "::");
				
			} catch (Exception ex)
			{
				log.error("Error en NominaDAO.actualizaNominaRe");
				throw new DaoException("Error en NominaDAO.actualizaNominaRe", ex);
			}
		}
		
		
		/**
		 * Actualiza el codigo de barra de la nomina, lo utiliza solo los independientes procesod de cierre entiguo
		 *
		 * @author gmallea
		 *
		 * @throws DaoException
		 */
		public void actualizaEstadoNominaRe() throws DaoException
		{
			try
			{			Date ini = new Date();
						
									
						Query query = this.session.createQuery(" UPDATE NominaREVO "+
															   " SET idEstado = "+Constants.EST_NOM_POR_PAGAR+ 
															   " WHERE idEstado <> " + Constants.EST_NOM_CREADA_EN_LINEA);
															   
								
						int count = query.executeUpdate();
						
						Date fin = new Date();
						log.info("actualizaEstadoNominaRe : Total de Nominas actualizadas a estado por pagadr " + count + " actualizadas:\t\tdiff:" + (fin.getTime() - ini.getTime()) + "::"); 
				
			} catch (Exception ex)
			{
				log.error("Error en NominaDAO.actualizaEstadoNominaRe");
				throw new DaoException("Error en NominaDAO.actualizaEstadoNominaRe", ex);
			}
		}
		
		/**
		 * Se obtiene las nominas por el codigo de barra
		 * 
		 * @author gmallea
		 * 
		 * @param long codigoBarra
		 * @return NominaVO
		 * @throws DaoException
		 */
		public NominaVO getNominabyCodigoBarra(long codigoBarra) throws DaoException
		{
			NominaVO nomina = null;
			try
			{
				log.info("NominaDAO:getNomina(codigoBarra: " + codigoBarra + ")");
					if(nomina == null){
						nomina = (NominaVO) this.session.createCriteria(NominaREVO.class).add(Restrictions.eq("idCodigoBarras", new Long(codigoBarra))).uniqueResult();
					}
					if (nomina == null){
						nomina = (NominaVO) this.session.createCriteria(NominaGRVO.class).add(Restrictions.eq("idCodigoBarras", new Long(codigoBarra))).uniqueResult();
					}
					if (nomina == null){
						nomina = (NominaVO) this.session.createCriteria(NominaRAVO.class).add(Restrictions.eq("idCodigoBarras", new Long(codigoBarra))).uniqueResult();
					}
					if (nomina == null){
						nomina = (NominaVO) this.session.createCriteria(NominaDCVO.class).add(Restrictions.eq("idCodigoBarras", new Long(codigoBarra))).uniqueResult();
					}

				return nomina;
			} catch (Exception ex)
			{
				log.error("ERROR NominaDAO:getNominabyCodigoBarra codigoBarra:" + codigoBarra + ": ", ex);
				throw new DaoException("Problemas obteniendo la nomina a partir del comprobante:" + codigoBarra + "::", ex);
			}
		}
		
		/**
		 * Prepara los objetos para posterior eliminarlos en las tablas (CotizacionXX, CausaXX, CotizacionPendienteXX ), solo empresa
		 * 
		 * @author gmallea
		 * 
		 * @param Class tipoNomina
		 * @param Class[] clasesDependientes
		 * @param List listRutEmpresaParam
		 * @return
		 * @throws DaoException
		 */
		private void borraDataConvenioEmpresa(Class tipoNomina, Class[] clasesDependientes, List listRutEmpresaParam)
		{
			for (Iterator iter = this.session.createCriteria(tipoNomina).add(Restrictions.in("rutEmpresa", listRutEmpresaParam)).list().iterator(); iter.hasNext();)
			{
				NominaVO nomina = (NominaVO) iter.next();
					for (int i = 0; i < clasesDependientes.length; i++){
							borraDataConvenio(nomina.getRutEmpresa(), nomina.getIdConvenio(), clasesDependientes[i]);
				}
			}
		}

		/**
		 * Limpia las tablas de la nomina y cotizante segun un listado de rutEmpresa, solo empresa
		 * 
		 * @author gmallea
		 * 
		 * @param List rutEmpresas
		 * @return
		 * @throws DaoException
		 */
		public String borraNominasEmpresa(List rutEmpresas) throws DaoException
		{
			String respuesta = "borraNominas";
			log.info("NominaDAO:borraNominasEmpresa inicio proceso");
			
			try 
			{
				List listRutEmpresaParam = new ArrayList();
				
				for(Iterator iter = rutEmpresas.iterator() ; iter.hasNext();){
					listRutEmpresaParam.add(new Integer(iter.next().toString()) );
				}
				
				borraDataConvenioEmpresa(NominaREVO.class, new Class[] { CotizacionREVO.class, CausaREVO.class, CotizacionPendienteREVO.class },listRutEmpresaParam);
				borraDataConvenioEmpresa(NominaGRVO.class, new Class[] { CotizacionGRVO.class, CausaGRVO.class, CotizacionPendienteGRVO.class },listRutEmpresaParam);
				borraDataConvenioEmpresa(NominaRAVO.class, new Class[] { CotizacionRAVO.class, CausaRAVO.class, CotizacionPendienteRAVO.class },listRutEmpresaParam);
				borraDataConvenioEmpresa(NominaDCVO.class, new Class[] { CotizacionDCVO.class, CausaDCVO.class, CotizacionPendienteDCVO.class },listRutEmpresaParam);
				borrarEmpresa("nomina remu", NominaREVO.class.getName(), rutEmpresas);
				borrarEmpresa("nomina grati", NominaGRVO.class.getName(), rutEmpresas);
				borrarEmpresa("nomina reli", NominaRAVO.class.getName(), rutEmpresas);
				borrarEmpresa("nomina depo", NominaDCVO.class.getName(), rutEmpresas);
				borraCotizantesEmpresa();
				
				respuesta = "";
			} catch (Exception ex)
			{
				log.error("Error en NominaDAO.borraNominasEmpresa", ex);
				throw new DaoException("Error en NominaDAO.borraNominasEmpresa", ex);
			}
			log.info("NominaDAO:borraNominasEmpresa fin proceso");
			return respuesta;
		}
		
		/**
		 * Limpia las tablas Nominax  (remu, grati, reli, depo) solo empresa
		 * 
		 * @author gmallea
		 * 
		 * @param String msg
		 * @param String clase
		 * @param List nominas
		 * @return
		 */
		private void borrarEmpresa(String msg, String clase, List rutEmpresas)
		{
			if(rutEmpresas.size() > 0){
				String where = "where rutEmpresa in (";
				for(Iterator it = rutEmpresas.iterator() ; it.hasNext();){
					where = where + (Integer) it.next() +",";
				}
				String whereIN =  where.substring(0,where.length()-1);
				whereIN = whereIN + ")";
				Query query = this.session.createQuery("DELETE FROM " + clase +" "+ whereIN);
				
				int count = query.executeUpdate();
				log.info(msg +" Se eliminan "+count+" registros con el rut empresa "+ whereIN );
		
			}
		}
		
		/**
		 * Limpia las tablas Cotizante, solo para empresa 
		 * 
		 * @author gmallea
		 * 
		 * @param List nominas
		 * @return
		 */
		private void borraCotizantesEmpresa()
		{
			List convenios = this.session.createQuery("select distinct c.rutEmpresa, c.idConvenio from " + CotizanteVO.class.getName() +" c ,"+ EmpresaVO.class.getName() +" e "+
													 "	where c.rutEmpresa = e.idEmpresa and e.tipo='"+Constants.TIPO_EMPRESA+"' ").list();
			if (convenios == null)
				return;
			for (Iterator iter = convenios.iterator(); iter.hasNext();)
			{
				Object[] convenio = (Object[]) iter.next();
				borraDataConvenio(((Number) convenio[0]).intValue(), ((Number) convenio[1]).intValue(), CotizanteVO.class);
			}
		}
		
		/**
		 * Actualiza el estado de las nominas de 9 a 3, solo para los independentes
		 * 
		 * @author gmallea
		 * 
		 * @param List nominas
		 * @return
		 * @throws DaoException
		 */
		public void actualizaEstadoNominaReIndependiente(List nominas) throws DaoException
		{
			try
			{			Date ini = new Date();
					if(nominas.size() > 0){
							String where = "rutEmpresa in (";
								for(Iterator it = nominas.iterator() ; it.hasNext();){
										where = where + (Integer) it.next() +",";
									}
								String whereIN =  where.substring(0,where.length()-1);
								whereIN = whereIN + ")";
						Query query = this.session.createQuery(" UPDATE NominaREVO "+
															   " SET idEstado = "+Constants.EST_NOM_POR_PAGAR+ 
															   " WHERE idEstado <> " + Constants.EST_NOM_CREADA_EN_LINEA+
															   " AND "+whereIN);
						int count = query.executeUpdate();
						
						Date fin = new Date();
						log.info("actualizaEstadoNominaReIndependiente : Total de Nominas actualizadas a estado por pagadr " + count + " actualizadas:\t\tdiff:" + (fin.getTime() - ini.getTime()) + "::"); 
					}
			} catch (Exception ex)
			{
				log.error("Error en NominaDAO.actualizaEstadoNominaReIndependiente");
				throw new DaoException("Error en NominaDAO.actualizaEstadoNominaReIndependiente", ex);
			}
		}
		
		/**
		 * Obtienen los codigos de barra segun el tipo de empresa (EMPRESA o INDEPENDINTE)
		 * 
		 * @author gmallea
		 * 
		 * @param String tipoEmpresa
		 * @return
		 * @throws DaoException
		 */
		public List getCodigoBarraByTipoEmpresa(String tipoEmpresa) throws DaoException
		{
			try
			{
				List lis= new ArrayList();
				
				Query query = this.session.createQuery("select n.idCodigoBarras from " +NominaREVO.class.getName() +" n, "+ EmpresaVO.class.getName() +" e "+
						   " where n.rutEmpresa = e.idEmpresa "+
						   " and e.tipo = '"+tipoEmpresa+"')");

				lis.addAll(query.list());

				query = this.session.createQuery("select n.idCodigoBarras from " +NominaGRVO.class.getName() +" n, "+ EmpresaVO.class.getName() +" e "+
							   " where n.rutEmpresa = e.idEmpresa "+
							   " and e.tipo = '"+tipoEmpresa+"')");
				
				lis.addAll(query.list());

				query = this.session.createQuery("select n.idCodigoBarras from " +NominaRAVO.class.getName() +" n, "+ EmpresaVO.class.getName() +" e "+
							   " where n.rutEmpresa = e.idEmpresa "+
							   " and e.tipo = '"+tipoEmpresa+"')");
				
				lis.addAll(query.list());
					
				query = this.session.createQuery("select n.idCodigoBarras from " +NominaDCVO.class.getName() +" n, "+ EmpresaVO.class.getName() +" e "+
							   " where n.rutEmpresa = e.idEmpresa "+
							   " and e.tipo = '"+tipoEmpresa+"')");
				
				lis.addAll(query.list());
					
				//Se eliminan los repetidos del arreglo
				HashSet hs = new HashSet();
				hs.addAll(lis);
				 
				lis.clear();
				lis.addAll(hs);
			
				return lis;
			} catch (Exception ex)
			{
				log.error("Problemas obteniendo la nomina a partir del comprobante:::", ex);
				throw new DaoException("Problemas obteniendo la nomina a partir del comprobante:::", ex);
			}
		}
		
		/**
		 * Limpia las tablas los comprobantes (configPDF, detalleSeccion, Seccion y comprobantePago) segun en listado de codigos de barra, solo empresa
		 * 
		 * @author gmallea
		 * 
		 * @param List listCodBarra
		 * @return
		 * @throws DaoException
		 */
		public String borraComprobantesEmpresa(List listCodBarra) throws DaoException
		{
			List listaBorrar;
			String respuesta = "borraComprobantes";
			
			try
			{
				List listCodBarraParam = new ArrayList();
				
				for(Iterator iter = listCodBarra.iterator() ; iter.hasNext();){
					listCodBarraParam.add(new Long(iter.next().toString()) );
				}
				listaBorrar = this.session.createCriteria(ConfigPDFVO.class)
														.add(Restrictions.in("idCodigoBarra", listCodBarra))
														.list();
				
				for (Iterator it = listaBorrar.iterator(); it.hasNext();){
					
					ConfigPDFVO configPDFVO =  (ConfigPDFVO) it.next();
					
								this.session.delete(configPDFVO);
								
				}
				log.info(" * Se elimino exitosamente los registros de configPDFVO * ");
				this.session.flush();
				
				listaBorrar = this.session.createCriteria(ComprobanteVO.class)
														.add(Restrictions.in("idCodigoBarra", listCodBarra))
														.list();
				
				for (Iterator it = listaBorrar.iterator(); it.hasNext();){
					
					ComprobanteVO comprobanteVO = (ComprobanteVO)it.next();
				
							this.session.delete(comprobanteVO);
			
				}
				log.info(" * Se elimino exitosamente los registros del comprobanteVO Eliminada * ");
				this.session.flush();
				respuesta = "";
			} catch (Exception ex)
			{
				log.error("Error en NominaDAO.borraComprobantesEmpresa", ex);
				throw new DaoException("Error en NominaDAO.borraComprobantesEmpresa", ex);
			}
			log.info("NominaDAO:borraComprobantesEmpresa fin proceso");
			return respuesta;
		}
		/**
		 * contador
		 * 
		 * @param tipo
		 * @return
		 * @throws DaoException
		 */
		public List countTipo(Class tipo, String tipoEmpresa) throws DaoException
		{
			try
			{
				List lis= new ArrayList();
				
				Query query = this.session.createQuery("SELECT n.idCodigoBarras FROM "+tipo.getName()+" n , "+EmpresaVO.class.getName()+" e "+
														" where n.rutEmpresa = e.idEmpresa "+
														" and e.tipo = '"+tipoEmpresa+"')");

				lis =query.list();
				
				return lis;
			} catch (Exception ex)
			{
				log.error("Error en NominaDAO:countTipo:tipo:" + tipo.getName() + "::", ex);
				throw new DaoException("Error en NominaDAO:countTipo", ex);
			}
		}
		
		public List countComprobantes(List nominas) throws DaoException
		{
			try
			{
				List lis= new ArrayList();
				String whereIN="";
				if(nominas.size() > 0){
					String where = "where n.idCodigoBarra in (";
					for(Iterator it = nominas.iterator() ; it.hasNext();){
						where = where + (Long) it.next() +",";
					}
					whereIN =  where.substring(0,where.length()-1);
					whereIN = whereIN + ")";
				
					
					
					Query query = this.session.createQuery("SELECT n.idCodigoBarra FROM "+ComprobanteVO.class.getName() +" n " + whereIN);
					
					lis=query.list();
				}
				return lis;
			} catch (Exception ex)
			{
				throw new DaoException("Error en NominaDAO:countComprobantes", ex);
			}
		}
		
		public int countCotizantes(Class tipo, String tipoEmpresa) throws DaoException
		{
			try
			{
				int count=0;
				
				Query query = this.session.createQuery("SELECT count(c.rutEmpresa) FROM "+tipo.getName()+" c, "+
														EmpresaVO.class.getName()+" e "+
														" WHERE c.rutEmpresa = e.idEmpresa and e.tipo ='"+tipoEmpresa+"'");
				
				count=((Integer)query.uniqueResult()).intValue();
				
				return count;
			} catch (Exception ex)
			{
				throw new DaoException("Error en NominaDAO:countCotizantes", ex);
			}
		}
}
