package cl.araucana.cp.hibernate.dao;

import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import cl.araucana.cp.distribuidor.base.Constants;
import cl.araucana.cp.distribuidor.hibernate.beans.ActividadEconomicaVO;
import cl.araucana.cp.distribuidor.hibernate.beans.AsigFamVO;
import cl.araucana.cp.distribuidor.hibernate.beans.CiudadVO;
import cl.araucana.cp.distribuidor.hibernate.beans.ComunaVO;
import cl.araucana.cp.distribuidor.hibernate.beans.EntidadAFCVO;
import cl.araucana.cp.distribuidor.hibernate.beans.EntidadApvVO;
import cl.araucana.cp.distribuidor.hibernate.beans.EntidadCCAFVO;
import cl.araucana.cp.distribuidor.hibernate.beans.EntidadExCajaVO;
import cl.araucana.cp.distribuidor.hibernate.beans.EntidadMutualVO;
import cl.araucana.cp.distribuidor.hibernate.beans.EntidadPensionVO;
import cl.araucana.cp.distribuidor.hibernate.beans.EntidadSaludVO;
import cl.araucana.cp.distribuidor.hibernate.beans.EntidadSilVO;
import cl.araucana.cp.distribuidor.hibernate.beans.EntidadVO;
import cl.araucana.cp.distribuidor.hibernate.beans.FoliacionVO;
import cl.araucana.cp.distribuidor.hibernate.beans.GeneroVO;
import cl.araucana.cp.distribuidor.hibernate.beans.MapeoVO;
import cl.araucana.cp.distribuidor.hibernate.beans.RegImpositivoVO;
import cl.araucana.cp.distribuidor.hibernate.beans.RegionVO;
import cl.araucana.cp.distribuidor.hibernate.beans.TipoMovimientoPersonalVO;
import cl.araucana.cp.distribuidor.hibernate.beans.TipoMvtoPersoAFVO;
import cl.araucana.cp.distribuidor.hibernate.exceptions.DaoException;
/*
* @(#) EntidadesDAO:java 1.21 10/05/2009
*
* Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
* La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
* restringido a los sistemas de información propios de la institución.
*/
/**
 * @author cchamblas
 * 
 * @version 1.21
 */
public class EntidadesDAO
{
	private static Logger logger = Logger.getLogger(EntidadesDAO.class);
	private Session session;
	private boolean loggear = true;

	public EntidadesDAO(Session session)
	{
		this.session = session;
	}
	/**
	 * muestra lista
	 * @param list
	 */
	public void showList(List list)
	{
		for (Iterator it = list.iterator(); it.hasNext();)
		{
			Object o = it.next();
			EntidadVO e = (EntidadVO)o;//it.next();
			logger.debug("id:" + e.getId() + ":entpag:" + e.getIdEntPagadora() + ":nombre:" + e.getNombre() + "::");
		}
	}
	/**
	 * muestra lista 2
	 * @param list
	 */
	public void showList2(List list)
	{
		for (Iterator it = list.iterator(); it.hasNext();)
		{
			Object o = it.next();
			AsigFamVO e = (AsigFamVO)o;//it.next();
			logger.debug("id:" + e.getId() + ":entpag:" + e.getNombre() + ":nombre:" + e.getNombre() + "::");
		}
	}
	/**
	 * generos
	 * @return
	 * @throws DaoException
	 */
	public List getGeneros() throws DaoException
	{
		try
		{
			if (this.loggear) logger.info("EntidadesDAO:getGeneros:");
			return this.session.createCriteria(GeneroVO.class).add(Restrictions.ne("id", new Integer(Constants.GENERO_FALSO))).list();
		} catch (Exception ex)
		{
			logger.error("Error en EntidadesDAO:getGeneros");
			throw new DaoException("EntidadesDAO:getGeneros:", ex);
		}
	}
	/**
	 * entidad salud
	 * @param flag
	 * @return
	 * @throws DaoException
	 */
	public List getEntsSalud() throws DaoException 
	{
		try
		{
			if (this.loggear) logger.info("EntidadesDAO:getEntsSalud:");
			return this.session.createCriteria(EntidadSaludVO.class).add(Restrictions.ne("id", new Integer(Constants.ID_FONASA))).list();
		} catch (Exception ex)
		{
			logger.error("Error en EntidadesDAO:getEntsSalud");
			throw new DaoException("EntidadesDAO:getEntsSalud:", ex);
		}
	}
	/**
	 * entidad pension
	 * @param flag
	 * @param sacarNinguna
	 * @return
	 * @throws DaoException
	 */
	public List getEntsPension(boolean flag, boolean sacarNinguna) throws DaoException 
	{
		try
		{
			if (this.loggear) logger.info("EntidadesDAO:getEntsPension:");
			Criteria crit = this.session.createCriteria(EntidadPensionVO.class);
			if (flag) //Sacar INP
				crit = crit.add(Restrictions.ne("id", new Integer(Constants.ID_INP)));
			if (sacarNinguna)
				crit = crit.add(Restrictions.ne("id", new Integer(Constants.ID_AFP_NINGUNA)));
			return crit.list();
		} catch (Exception ex)
		{
			logger.error("Error en EntidadesDAO:getEntsPension");
			throw new DaoException("EntidadesDAO:getEntsPension:", ex);
		}
	}
	/**
	 * entidad afc
	 * @return
	 * @throws DaoException
	 */
	public List getEntsAFC() throws DaoException 
	{
		try
		{
			if (this.loggear) logger.info("EntidadesDAO:getEntsAFC:");
			return this.session.createCriteria(EntidadAFCVO.class).add(Restrictions.ne("id", new Integer(Constants.AFC_FALSO))).list();
		} catch (Exception ex)
		{
			logger.error("Error en EntidadesDAO:getEntsAFC");
			throw new DaoException("EntidadesDAO:getEntsAFC:", ex);
		}
	}
	/**
	 * entidad ex caja
	 * @return
	 * @throws DaoException
	 */
	public List getEntsExCaja() throws DaoException 
	{
		try
		{
			if (this.loggear) logger.info("EntidadesDAO:getEntsExCaja:");
			return this.session.createCriteria(EntidadExCajaVO.class).add(Restrictions.ne("id", new Integer(Constants.EXCAJA_FALSO))).list();
		} catch (Exception ex)
		{
			logger.error("Error en EntidadesDAO:getEntsExCaja:", ex);
			throw new DaoException("EntidadesDAO:getEntsExCaja:", ex);
		}
	}
	/**
	 * entidad apvs
	 * @return
	 * @throws DaoException
	 */
	public List getEntsApvs() throws DaoException 
	{
		try
		{
			if (this.loggear) logger.info("EntidadesDAO:getEntsApvs:");
			return this.session.createCriteria(EntidadApvVO.class).add(Restrictions.ne("id", new Integer(Constants.SIN_APV))).list();
		} catch (Exception ex)
		{
			logger.error("Error en EntidadesDAO:getEntsApvs");
			throw new DaoException("EntidadesDAO:getEntsApvs:", ex);
		}
	}
	/**
	 * entidad sil
	 * @return
	 * @throws DaoException
	 */
	public List getEntsSIL() throws DaoException 
	{
		try
		{
			if (this.loggear) logger.info("EntidadesDAO:getEntsSIL:");
			return this.session.createCriteria(EntidadSilVO.class).add(Restrictions.ne("id", new Integer(Constants.ENTSIL_FALSO))).list();
		} catch (Exception ex)
		{
			logger.error("Error en EntidadesDAO:getEntsSIL");
			throw new DaoException("EntidadesDAO:getEntsSIL:", ex);
		}
	}
	/**
	 * tramos asignacion familiar
	 * @return
	 * @throws DaoException
	 */
	public List getTramosAsigFam() throws DaoException 
	{
		try
		{
			if (this.loggear) logger.info("EntidadesDAO:getTramosAsigFam:");
			List result = this.session.createCriteria(AsigFamVO.class)
							.add(Restrictions.ne("id", new Integer(Constants.TRAMO_ASIGFAM_FALSO)))
							.addOrder(Order.asc("nombre"))
							.list();
			//showList2(result);
			return result;
		} catch (Exception ex)
		{
			logger.error("Error en EntidadesDAO:getTramosAsigFam");
			throw new DaoException("EntidadesDAO:getTramosAsigFam:", ex);
		}
	}
	/**
	 * entidad mutual
	 * @return
	 * @throws DaoException
	 */
	public List getEntsMutual() throws DaoException 
	{
		try
		{
			if (this.loggear) logger.info("EntidadesDAO:getEntsMutual:");
			return this.session.createCriteria(EntidadMutualVO.class)
				.add(Restrictions.ne("id", new Integer(Constants.SIN_MUTUAL)))
				.list();
		} catch (Exception ex)
		{
			logger.error("Error en EntidadesDAO:getEntsMutual");
			throw new DaoException("EntidadesDAO:getEntsMutual:", ex);
		}
	}
	/**
	 * codigo reimen impositivo
	 * @param idExCaja
	 * @return
	 * @throws DaoException
	 */
	public List getCodRegImp(int idExCaja) throws DaoException 
	{
		try
		{
			if (this.loggear) logger.info("EntidadesDAO:getCodRegImp:");
			return this.session.createCriteria(RegImpositivoVO.class).add(Restrictions.eq("idEntExCaja", new Integer(idExCaja))).add(Restrictions.ne("idRegImpositivo", new Integer(Constants.CODREGIMP_FALSO))).list();			
		} catch (Exception ex)
		{
			logger.error("Error en EntidadesDAO:getCodRegImp");
			throw new DaoException("EntidadesDAO:getCodRegImp:", ex);
		}
	}
	/**
	 * regimen impositivo
	 * @return
	 * @throws DaoException
	 */
	public List getRegImp() throws DaoException 
	{
		try
		{
			if (this.loggear) logger.info("EntidadesDAO:getRegImp:");
			return this.session.createCriteria(RegImpositivoVO.class).add(Restrictions.ne("idRegImpositivo", new Integer(Constants.CODREGIMP_FALSO))).list();			
		} catch (Exception ex)
		{
			logger.error("Error en EntidadesDAO:getRegImp");
			throw new DaoException("EntidadesDAO:getRegImp:", ex);
		}
	}
	/**
	 * tipos movimiento personal
	 * @return
	 * @throws DaoException
	 */
	public List getTiposMovPersonal() throws DaoException 
	{
		try
		{
			if (this.loggear) logger.info("EntidadesDAO:getTiposMovPersonal:");
			return this.session.createCriteria(TipoMovimientoPersonalVO.class).list();			
		} catch (Exception ex)
		{
			logger.error("Error en EntidadesDAO:getTiposMovPersonal");
			throw new DaoException("EntidadesDAO:getTiposMovPersonal:", ex);
		}
	}
	/**
	 * tipos movimiento personal
	 * @return
	 * @throws DaoException
	 */
	public List getTiposMovPersonalAF() throws DaoException 
	{
		try
		{
			if (this.loggear) logger.info("EntidadesDAO:getTiposMovPersonalAF:");
			return this.session.createCriteria(TipoMvtoPersoAFVO.class).list();			
		} catch (Exception ex)
		{
			logger.error("Error en EntidadesDAO:getTiposMovPersonalAF");
			throw new DaoException("EntidadesDAO:getTiposMovPersonalAF:", ex);
		}
	}
	/**
	 * entidad ccaf
	 * @return
	 * @throws DaoException
	 */
	public List getEntsCCAF() throws DaoException 
	{
		try
		{
			if (this.loggear) logger.info("EntidadesDAO:getEntsCCAF:");
			return this.session.createCriteria(EntidadCCAFVO.class)
				.add(Restrictions.ne("id", new Integer(Constants.SIN_CCAF))).list();
		} catch (Exception ex)
		{
			logger.error("Error en EntidadesDAO:getEntsCCAF");
			throw new DaoException("EntidadesDAO:getEntsCCAF:", ex);
		}
	}
	/**
	 * lista regiones
	 * @return
	 * @throws DaoException
	 */
	public List getRegiones() throws DaoException {
		try
		{
			logger.info("EntidadesDAO:getRegiones:");
			return this.session.createCriteria(RegionVO.class)
				.add(Restrictions.ne("idRegion", new Integer(Constants.ID_REGION_DEFAULT)))
				.addOrder(Order.asc("idRegion"))
				.list();
		} catch (Exception ex)
		{
			logger.error("Error en EntidadesDAO:getRegiones");
			throw new DaoException("EntidadesDAO:getRegiones:", ex);
		}
	}
	/**
	 * lista ciudades
	 * @param idRegion
	 * @return
	 * @throws DaoException
	 */
	public List getCiudades(int idRegion) throws DaoException {
		try
		{
			logger.info("EntidadesDAO:getCiudades:");
			return this.session.createCriteria(CiudadVO.class)
				.add(Restrictions.eq("region", new RegionVO(idRegion)))
				.add(Restrictions.ne("idCiudad", new Integer(Constants.ID_CIUDAD_DEFAULT)))
				.addOrder(Order.asc("nombre"))
				.list();
		} catch (Exception ex)
		{
			logger.error("Error en EntidadesDAO:getCiudades:" + ex);
			throw new DaoException("EntidadesDAO:getCiudades:", ex);
		}
	}
	/**
	 * lista comunas
	 * @param idCiudad
	 * @return
	 * @throws DaoException
	 */
	public List getComunas(int idCiudad) throws DaoException {
		try
		{
			logger.info("EntidadesDAO:getComunas:");
			return this.session.createCriteria(ComunaVO.class)
				.add(Restrictions.eq("ciudad", new CiudadVO(idCiudad)))
				.add(Restrictions.ne("idComuna", new Integer(Constants.ID_COMUNA_DEFAULT)))
				.addOrder(Order.asc("nombre"))
				.list();
		} catch (Exception ex)
		{
			logger.error("Error en EntidadesDAO:getComunas");
			throw new DaoException("EntidadesDAO:getComunas:", ex);
		}
	}
	/**
	 * entidad mutual
	 * @param idMutual
	 * @return
	 * @throws DaoException
	 */
	public EntidadMutualVO getMutual(int idMutual) throws DaoException {
		try
		{
			logger.info("EntidadesDAO:getMutual: " + idMutual);
			return (EntidadMutualVO) this.session.load(EntidadMutualVO.class, new Integer(idMutual));
		} catch (Exception ex)
		{
			logger.error("Error en EntidadesDAO:getMutual: " + ex);
			throw new DaoException("EntidadesDAO:getMutual:", ex);
		}
	}
	/**
	 * comuna
	 * @param idComuna
	 * @return
	 * @throws DaoException
	 */
	public ComunaVO getComuna(int idComuna) throws DaoException {
		try
		{
			logger.info("EntidadesDAO:getComuna: " + idComuna);
			return (ComunaVO) this.session.load(ComunaVO.class, new Integer(idComuna));
		} catch (Exception ex)
		{
			logger.error("Error en EntidadesDAO:getComuna: " + ex);
			throw new DaoException("EntidadesDAO:getComuna:", ex);
		}
	}
	/**
	 * region
	 * @param idRegion
	 * @return
	 * @throws DaoException
	 */
	public RegionVO getRegion(int idRegion) throws DaoException {
		try	{
			logger.info("EntidadesDAO:getRegion: " + idRegion);
			return (RegionVO) this.session.load(RegionVO.class, new Integer(idRegion));
		} catch (Exception ex) {
			logger.error("Error en EntidadesDAO:getRegion: " + ex);
			throw new DaoException("EntidadesDAO:getRegion:", ex);
		}
	}
	/**
	 * ciudad
	 * @param idCiudad
	 * @return
	 * @throws DaoException
	 */
	public CiudadVO getCiudad(int idCiudad) throws DaoException {
		try	{
			logger.info("EntidadesDAO:getCiudad: " + idCiudad);
			return (CiudadVO) this.session.load(CiudadVO.class, new Integer(idCiudad));
		} catch (Exception ex) {
			logger.error("Error en EntidadesDAO:getCiudad: " + ex);
			throw new DaoException("EntidadesDAO:getCiudad:", ex);
		}
	}
	/**
	 * entidad ccaf vo
	 * @param id
	 * @return
	 * @throws DaoException
	 */
	public EntidadCCAFVO getCaja(int id) throws DaoException {
		try	{
			logger.info("EntidadesDAO:getCaja: " + id);
			return (EntidadCCAFVO) this.session.load(EntidadCCAFVO.class, new Integer(id));
		} catch (Exception ex) {
			logger.error("Error en EntidadesDAO:getCaja: " + ex);
			throw new DaoException("EntidadesDAO:getCaja:", ex);
		}
	}
	/**
	 * entidad real
	 * @param idMapaCod
	 * @param tipo
	 * @param codigo
	 * @return
	 * @throws DaoException
	 */
	public MapeoVO getEntReal(int idMapaCod, Class tipo, String codigo) throws DaoException 
	{
		try
		{
			if(codigo.trim().equals(""))
				return null;
			if (this.loggear) logger.info("EntidadesDAO:getEntReal:" + codigo + "::" + idMapaCod + "::" + tipo.getName() + "::");
			Criteria crit = this.session.createCriteria(tipo).add(Restrictions.eq("idMapaCod", new Integer(idMapaCod)));
			List result = crit.add(Restrictions.eq("codigo", codigo)).list();
			if (result != null && result.size() > 0)
				return (MapeoVO)result.get(0);
			return null;
		} catch (Exception ex)
		{
			logger.error("Error en EntidadesDAO:getEntReal");
			throw new DaoException("EntidadesDAO:getEntReal:", ex);
		}
	}
	/**
	 * entidad fonde pension
	 * @param idFondoPension
	 * @return
	 * @throws DaoException
	 */
	public EntidadPensionVO getEntFondoPension(int idFondoPension) throws DaoException 
	{
		try
		{
			if (this.loggear) logger.info("EntidadesDAO:getEntFondoPension:");
			return (EntidadPensionVO) this.session.get(EntidadPensionVO.class, new Integer(idFondoPension));
		} catch (Exception ex)
		{
			logger.error("Error en EntidadesDAO:getEntFondoPension");
			throw new DaoException("EntidadesDAO:getEntFondoPension:", ex);
		}
	}
	/**
	 * asignacion familiar
	 * @param idAsigFam
	 * @return
	 * @throws DaoException
	 */
	public AsigFamVO getAsigFam(int idAsigFam) throws DaoException 
	{
		try
		{
			if (this.loggear) logger.info("EntidadesDAO:getAsigFam:");
			return (AsigFamVO)this.session.get(AsigFamVO.class, new Integer(idAsigFam));
		} catch (Exception ex)
		{
			logger.error("Error en EntidadesDAO:getAsigFam:" + idAsigFam + "::");
			throw new DaoException("EntidadesDAO:getAsigFam:", ex);
		}
	}
	/**
	 * genero
	 * @param idGenero
	 * @return
	 * @throws DaoException
	 */
	public GeneroVO getGenero(int idGenero) throws DaoException 
	{
		try
		{
			if (this.loggear) logger.info("EntidadesDAO:getGenero:");
			return (GeneroVO)this.session.get(GeneroVO.class, new Integer(idGenero));
		} catch (Exception ex)
		{
			logger.error("Error en EntidadesDAO:getGenero:" + idGenero + "::");
			throw new DaoException("EntidadesDAO:getGenero:", ex);
		}
	}
	/**
	 * entidad apv
	 * @param idEntAPV
	 * @return
	 * @throws DaoException
	 */
	public EntidadApvVO getEntApv(int idEntAPV) throws DaoException 
	{
		try
		{
			if (this.loggear) logger.info("EntidadesDAO:getEntApv:");
			return (EntidadApvVO)this.session.get(EntidadApvVO.class, new Integer(idEntAPV));
		} catch (Exception ex)
		{
			logger.error("Error en EntidadesDAO:getEntApv:" + idEntAPV + "::");
			throw new DaoException("EntidadesDAO:getEntApv:", ex);
		}
	}
	/**
	 * entidad salud
	 * @param idEntSalud
	 * @return
	 * @throws DaoException
	 */
	public EntidadSaludVO getEntSalud(int idEntSalud) throws DaoException 
	{
		try
		{
			if (this.loggear) logger.info("EntidadesDAO:getEntSalud:");
			Criteria crit = this.session.createCriteria(EntidadSaludVO.class).add(Restrictions.eq("id", new Integer(idEntSalud)));
			List result = crit.list();
			if (result != null && result.size() > 0)
				return (EntidadSaludVO)result.get(0);
			return null;
		} catch (Exception ex)
		{
			logger.error("Error en EntidadesDAO:getEntSalud");
			throw new DaoException("EntidadesDAO:getEntSalud:", ex);
		}
	}
		/**
		 * entidad mutual
		 * @param idEntMutual
		 * @return
		 * @throws DaoException
		 */
	public EntidadMutualVO getEntMutual(int idEntMutual) throws DaoException 
	{
		try
		{
			if (this.loggear) logger.info("EntidadesDAO:getEntMutual:"+idEntMutual);
			Criteria crit = this.session.createCriteria(EntidadMutualVO.class).add(Restrictions.eq("id", new Integer(idEntMutual)));
			List result = crit.list();
			if (result != null && result.size() > 0)
				return (EntidadMutualVO)result.get(0);
			return null;
		} catch (Exception ex)
		{
			logger.error("Error en EntidadesDAO:getEntMutual");
			throw new DaoException("EntidadesDAO:getEntMutual:", ex);
		}
	}
	public float getPorcentajeFONASA() throws DaoException 
	{
		try
		{
			EntidadSaludVO ent = (EntidadSaludVO)this.session.get(EntidadSaludVO.class, new Integer(Constants.ID_FONASA));
			return ent.getTasaSalud();
		} catch (Exception ex)
		{
			logger.error("Error en EntidadesDAO:getPorcentajeFONASA");
			throw new DaoException("EntidadesDAO:getPorcentajeFONASA:", ex);
		}
	}

	public List getEntidades(boolean flag, int valor, Class tipo) throws DaoException
	{
		try
		{
			if (flag)
				return this.session.createCriteria(tipo).add(Restrictions.ne("id", new Integer(valor))).list();
			return this.session.createCriteria(tipo).list();
		} catch (Exception ex)
		{
			logger.error("Error en EntidadesDAO:getEntidades");
			throw new DaoException("EntidadesDAO:getEntidades:", ex);
		}
	}
	/**
	 * entidad ccaf
	 * @return
	 * @throws DaoException
	 */
	public EntidadCCAFVO getEntsCCAFByID(int idCaja) throws DaoException 
	{
		try
		{
			if (this.loggear) logger.info("EntidadesDAO:getEntsCCAF:");
			return (EntidadCCAFVO)this.session.createCriteria(EntidadCCAFVO.class)
				.add(Restrictions.eq("id",new Integer(idCaja)))
				.list().get(0);
		} catch (Exception ex)
		{
			logger.error("Error en EntidadesDAO:getEntsCCAF");
			throw new DaoException("EntidadesDAO:getEntsCCAF:", ex);
		}
	}
	
	public EntidadMutualVO getEntsMutual(int idMutual) throws DaoException 
	{
		try
		{
			if (this.loggear) logger.info("EntidadesDAO:getEntsMutual:");
			return  (EntidadMutualVO)this.session.createCriteria(EntidadMutualVO.class)
				.add(Restrictions.eq("id", new Integer(idMutual)))
				.list().get(0);
		} catch (Exception ex)
		{
			logger.error("Error en EntidadesDAO:getEntsMutual");
			throw new DaoException("EntidadesDAO:getEntsMutual:", ex);
		}
	}

	public EntidadExCajaVO getEntsExCaja(int idExCaja) throws DaoException 
		{
			try
			{
				if (this.loggear) logger.info("EntidadesDAO:getEntsExCaja:");
				return (EntidadExCajaVO)this.session.createCriteria(EntidadExCajaVO.class).add(Restrictions.eq("id", new Integer(idExCaja))).list().get(0);
			} catch (Exception ex)
			{
				logger.error("Error en EntidadesDAO:getEntsExCaja:", ex);
				throw new DaoException("EntidadesDAO:getEntsExCaja:", ex);
			}
		}
	}
