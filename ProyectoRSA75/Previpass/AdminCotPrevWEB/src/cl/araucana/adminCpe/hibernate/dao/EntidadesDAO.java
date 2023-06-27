package cl.araucana.adminCpe.hibernate.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import cl.araucana.cp.distribuidor.base.Constants;
import cl.araucana.cp.distribuidor.hibernate.beans.ApvVO;
import cl.araucana.cp.distribuidor.hibernate.beans.AsigFamVO;
import cl.araucana.cp.distribuidor.hibernate.beans.CiudadVO;
import cl.araucana.cp.distribuidor.hibernate.beans.ComunaVO;
import cl.araucana.cp.distribuidor.hibernate.beans.ConvenioVO;
import cl.araucana.cp.distribuidor.hibernate.beans.CotizanteVO;
import cl.araucana.cp.distribuidor.hibernate.beans.DetalleSeccionVO;
import cl.araucana.cp.distribuidor.hibernate.beans.EntidadAFCVO;
import cl.araucana.cp.distribuidor.hibernate.beans.EntidadApvVO;
import cl.araucana.cp.distribuidor.hibernate.beans.EntidadCCAFVO;
import cl.araucana.cp.distribuidor.hibernate.beans.EntidadExCajaVO;
import cl.araucana.cp.distribuidor.hibernate.beans.EntidadMutualVO;
import cl.araucana.cp.distribuidor.hibernate.beans.EntidadPagadoraVO;
import cl.araucana.cp.distribuidor.hibernate.beans.EntidadPensionVO;
import cl.araucana.cp.distribuidor.hibernate.beans.EntidadPrevisionalVO;
import cl.araucana.cp.distribuidor.hibernate.beans.EntidadSaludVO;
import cl.araucana.cp.distribuidor.hibernate.beans.EntidadSilVO;
import cl.araucana.cp.distribuidor.hibernate.beans.EntidadVO;
import cl.araucana.cp.distribuidor.hibernate.beans.FoliacionVO;
import cl.araucana.cp.distribuidor.hibernate.beans.GeneroVO;
import cl.araucana.cp.distribuidor.hibernate.beans.MapaCodigoVO;
import cl.araucana.cp.distribuidor.hibernate.beans.MapeoAPVVO;
import cl.araucana.cp.distribuidor.hibernate.beans.MapeoPensionVO;
import cl.araucana.cp.distribuidor.hibernate.beans.MapeoSaludVO;
import cl.araucana.cp.distribuidor.hibernate.beans.MapeoTesoreriaVO;
import cl.araucana.cp.distribuidor.hibernate.beans.MapeoVO;
import cl.araucana.cp.distribuidor.hibernate.beans.ParametroVO;
import cl.araucana.cp.distribuidor.hibernate.beans.RegImpositivoVO;
import cl.araucana.cp.distribuidor.hibernate.beans.RegionVO;
import cl.araucana.cp.distribuidor.hibernate.beans.TipoDetalleVO;
import cl.araucana.cp.distribuidor.hibernate.beans.TipoMovimientoPersonalVO;
import cl.araucana.cp.distribuidor.hibernate.beans.TipoMvtoPersoAFVO;
import cl.araucana.cp.distribuidor.hibernate.beans.TipoNominaVO;
import cl.araucana.cp.distribuidor.hibernate.exceptions.DaoException;

/**
 * @author ccostagliola
 * @author cchamblas
 * 
 * @version 1.22
 */
public class EntidadesDAO
{
	private static Logger log = Logger.getLogger(EntidadesDAO.class);
	private Session session;
	private DetalleReporteDAO detalleReporteDao;
	private NominaDAO nominaDao;

	public EntidadesDAO(Session session)
	{
		this.session = session;
		this.detalleReporteDao = new DetalleReporteDAO(session);
		this.nominaDao = new NominaDAO(session);
	}

	/**
	 * elimina regimen impositivo
	 * 
	 * @param idEntidadExCaja
	 * @param idEntidad
	 */
	public void borraEntRegimenImpositivo(int idEntidadExCaja, int idEntidad)
	{
		try
		{
			log.info("EntidadVO:borraEntRegimenImpositivo");
			List lista = this.session.createCriteria(RegImpositivoVO.class).add(Restrictions.eq("idEntExCaja", new Integer(idEntidadExCaja))).add(
					Restrictions.eq("idRegImpositivo", new Integer(idEntidad))).list();
			RegImpositivoVO reg;
			for (Iterator it = lista.iterator(); it.hasNext();)
			{
				reg = (RegImpositivoVO) it.next();
				this.session.delete(reg);
			}
		} catch (Exception ex)
		{
			log.error("Error en EntidadesDAO:borraEntRegimenImpositivo: " + ex);
		}
	}

	/**
	 * 
	 * @param id
	 * @return caja
	 * @throws DaoException
	 */
	public EntidadCCAFVO getCaja(int id) throws DaoException
	{
		try
		{
			log.info("EntidadesDAO:getCaja: " + id);
			return (EntidadCCAFVO) this.session.load(EntidadCCAFVO.class, new Integer(id));
		} catch (Exception ex)
		{
			log.error("Error en EntidadesDAO:getCaja: " + ex);
			throw new DaoException("EntidadesDAO:getCaja:", ex);
		}
	}

	/**
	 * 
	 * @param idCiudad
	 * @return ciudad
	 * @throws DaoException
	 */
	public CiudadVO getCiudad(int idCiudad) throws DaoException
	{
		try
		{
			log.info("EntidadesDAO:getCiudad: " + idCiudad);
			return (CiudadVO) this.session.load(CiudadVO.class, new Integer(idCiudad));
		} catch (Exception ex)
		{
			log.error("Error en EntidadesDAO:getCiudad: " + ex);
			throw new DaoException("EntidadesDAO:getCiudad:", ex);
		}
	}

	/**
	 * 
	 * @param idRegion
	 * @return lista ciudades
	 * @throws DaoException
	 */
	public List getCiudades(int idRegion) throws DaoException
	{
		try
		{
			log.info("EntidadesDAO:getCiudades:");
			return this.session.createCriteria(CiudadVO.class).add(Restrictions.eq("region", new RegionVO(idRegion))).add(Restrictions.ne("idCiudad", new Integer(Constants.ID_CIUDAD_DEFAULT)))
					.addOrder(Order.asc("nombre")).list();
		} catch (Exception ex)
		{
			log.error("Error en EntidadesDAO:getCiudades:" + ex);
			throw new DaoException("EntidadesDAO:getCiudades:", ex);
		}
	}

	/**
	 * 
	 * @param idExCaja
	 * @return lista codigo reg imp
	 * @throws DaoException
	 */
	public List getCodRegImp(int idExCaja) throws DaoException
	{
		try
		{
			return this.session.createCriteria(RegImpositivoVO.class).add(Restrictions.eq("idEntExCaja", new Integer(idExCaja))).list();
		} catch (Exception ex)
		{
			log.error("Error en EntidadesDAO:getCodRegImp");
			throw new DaoException("EntidadesDAO:getCodRegImp:", ex);
		}
	}

	/**
	 * 
	 * @param idComuna
	 * @return comuna
	 * @throws DaoException
	 */
	public ComunaVO getComuna(int idComuna) throws DaoException
	{
		try
		{
			log.info("EntidadesDAO:getComuna: " + idComuna);
			return (ComunaVO) this.session.load(ComunaVO.class, new Integer(idComuna));
		} catch (Exception ex)
		{
			log.error("Error en EntidadesDAO:getComuna: " + ex);
			throw new DaoException("EntidadesDAO:getComuna:", ex);
		}
	}

	/**
	 * 
	 * @param idCiudad
	 * @return lista comunas
	 * @throws DaoException
	 */
	public List getComunas(int idCiudad) throws DaoException
	{
		try
		{
			log.info("EntidadesDAO:getComunas:");
			return this.session.createCriteria(ComunaVO.class).add(Restrictions.eq("ciudad", new CiudadVO(idCiudad))).add(Restrictions.ne("idComuna", new Integer(Constants.ID_COMUNA_DEFAULT)))
					.addOrder(Order.asc("nombre")).list();
		} catch (Exception ex)
		{
			log.error("Error en EntidadesDAO:getComunas");
			throw new DaoException("EntidadesDAO:getComunas:", ex);
		}
	}

	/**
	 * 
	 * @param idEntPagadora
	 * @return entidad pagadora
	 * @throws DaoException
	 */
	public EntidadPagadoraVO getEntidadPagadora(int idEntPagadora) throws DaoException
	{
		try
		{
			log.info("EntidadesDAO:getEntidadPagadora:");
			return (EntidadPagadoraVO) this.session.load(EntidadPagadoraVO.class, new Integer(idEntPagadora));
		} catch (Exception ex)
		{
			log.error("Error en EntidadesDAO:getEntidadPagadora");
			throw new DaoException("EntidadesDAO:getEntidadPagadora:", ex);
		}
	}

	/**
	 * 
	 * @param idMapaCod
	 * @param tipo
	 * @param codigo
	 * @return mapeo
	 * @throws DaoException
	 */
	public MapeoVO getEntReal(int idMapaCod, Class tipo, String codigo) throws DaoException
	{
		try
		{
			Criteria crit = this.session.createCriteria(tipo).add(Restrictions.eq("idMapaCod", new Integer(idMapaCod)));
			List result = crit.add(Restrictions.eq("codigo", codigo)).list();
			if ((result != null) && (result.size() > 0))
			{
				return (MapeoVO) result.get(0);
			}
			return null;
		} catch (Exception ex)
		{
			log.error("Error en EntidadesDAO:getEntReal");
			throw new DaoException("EntidadesDAO:getEntReal:", ex);
		}
	}

	/**
	 * 
	 * @return lista entidad afc
	 * @throws DaoException
	 */
	public List getEntsAFC() throws DaoException
	{
		try
		{
			return this.session.createCriteria(EntidadAFCVO.class).list();
		} catch (Exception ex)
		{
			log.error("Error en EntidadesDAO:getEntsAFC");
			throw new DaoException("EntidadesDAO:getEntsAFC:", ex);
		}
	}

	/**
	 * 
	 * @param idEntPagadora
	 * @return entidad afc
	 * @throws DaoException
	 */
	public EntidadAFCVO getEntsAFC(int idEntPagadora) throws DaoException
	{
		try
		{
			return (EntidadAFCVO) this.session.createCriteria(EntidadAFCVO.class).add(Restrictions.eq("idEntPagadora", new Integer(idEntPagadora))).uniqueResult();
		} catch (Exception ex)
		{
			log.error("Error en EntidadesDAO:getEntsAFC");
			throw new DaoException("EntidadesDAO:getEntsAFC:", ex);
		}
	}

	/**
	 * 
	 * @return entidad apv
	 * @throws DaoException
	 */
	public List getEntsApvs() throws DaoException
	{
		try
		{
			return this.session.createCriteria(EntidadApvVO.class).list();
		} catch (Exception ex)
		{
			log.error("Error en EntidadesDAO:getEntsApvs");
			throw new DaoException("EntidadesDAO:getEntsApvs:", ex);
		}
	}

	/**
	 * 
	 * @return lista entidad ccaf
	 * @throws DaoException
	 */
	public List getEntsCCAF() throws DaoException
	{
		try
		{
			return this.session.createCriteria(EntidadCCAFVO.class).add(Restrictions.ne("id", new Integer(0))).list();
		} catch (Exception ex)
		{
			log.error("Error en EntidadesDAO:getEntsCCAF");
			throw new DaoException("EntidadesDAO:getEntsCCAF:", ex);
		}
	}

	/**
	 * 
	 * @param id
	 * @param idEntPagadora
	 * @return entidad ccaf
	 * @throws DaoException
	 */
	public EntidadCCAFVO getEntsCCAF(int id, int idEntPagadora) throws DaoException
	{
		try
		{
			return (EntidadCCAFVO) this.session.createCriteria(EntidadCCAFVO.class).add(Restrictions.eq("id", new Integer(id))).add(Restrictions.eq("idEntPagadora", new Integer(idEntPagadora)))
					.uniqueResult();
		} catch (Exception ex)
		{
			log.error("Error en EntidadesDAO:getEntsCCAF");
			throw new DaoException("EntidadesDAO:getEntsCCAF:", ex);
		}
	}

	/**
	 * 
	 * @param id
	 * @param idEntPagadora
	 * @return entidad ccaf
	 * @throws DaoException
	 */
	public EntidadCCAFVO getEntsCCAF(int id) throws DaoException
	{
		try
		{
			return (EntidadCCAFVO) this.session.createCriteria(EntidadCCAFVO.class)
											   .add(Restrictions.eq("id", new Integer(id)))
											   .uniqueResult();
		} catch (Exception ex)
		{
			log.error("Error en EntidadesDAO:getEntsCCAF");
			throw new DaoException("EntidadesDAO:getEntsCCAF:", ex);
		}
	}	
	
	
	/**
	 * 
	 * @return lista entidad ex caja
	 * @throws DaoException
	 */
	public List getEntsExCaja() throws DaoException
	{
		try
		{
			return this.session.createCriteria(EntidadExCajaVO.class).list();
		} catch (Exception ex)
		{
			log.error("Error en EntidadesDAO:getEntsExCaja", ex);
			throw new DaoException("EntidadesDAO:getEntsExCaja:", ex);
		}
	}

	/**
	 * 
	 * @param idEntPagadora
	 * @return liista entidad ex caja
	 * @throws DaoException
	 */
	public List getEntsExCaja(int idEntPagadora) throws DaoException
	{
		try
		{
			return this.session.createCriteria(EntidadExCajaVO.class).add(Restrictions.eq("idEntPagadora", new Integer(idEntPagadora))).list();
		} catch (Exception ex)
		{
			log.error("Error en EntidadesDAO:getEntsExCaja", ex);
			throw new DaoException("EntidadesDAO:getEntsExCaja:", ex);
		}
	}

	/**
	 * 
	 * @param idEntPagadora
	 * @param codigo
	 * @return lista entidad ex caja
	 * @throws DaoException
	 */
	public List getEntsExCaja(int idEntPagadora, int codigo) throws DaoException
	{
		try
		{
			return this.session.createCriteria(EntidadExCajaVO.class).add(Restrictions.eq("idEntPagadora", new Integer(idEntPagadora))).add(Restrictions.eq("id", new Integer(codigo))).list();
		} catch (Exception ex)
		{
			log.error("Error en EntidadesDAO:getEntsExCaja", ex);
			throw new DaoException("EntidadesDAO:getEntsExCaja:", ex);
		}
	}

	/**
	 * 
	 * @return lista entidad mutual
	 * @throws DaoException
	 */
	public List getEntsMutual() throws DaoException
	{
		try
		{
			return this.session.createCriteria(EntidadMutualVO.class).add(Restrictions.ne("id", new Integer(Constants.SIN_MUTUAL))).list();
		} catch (Exception ex)
		{
			log.error("Error en EntidadesDAO:getEntsMutual");
			throw new DaoException("EntidadesDAO:getEntsMutual:", ex);
		}
	}

	/**
	 * 
	 * @param flag
	 * @return lista entidad pension
	 * @throws DaoException
	 */
	public List getEntsPension(boolean flag) throws DaoException
	{
		try
		{
			if (flag)
				return this.session.createCriteria(EntidadPensionVO.class).add(Restrictions.ne("id", new Integer(Constants.ID_INP))).list();
			return this.session.createCriteria(EntidadPensionVO.class).list();
		} catch (Exception ex)
		{
			log.error("Error en EntidadesDAO:getEntsPension");
			throw new DaoException("EntidadesDAO:getEntsPension:", ex);
		}
	}

	/**
	 * 
	 * @param id
	 * @param idEntPagadora
	 * @param flag
	 * @return entidad pension
	 * @throws DaoException
	 */
	public EntidadPensionVO getEntsPension(int id, int idEntPagadora, boolean flag) throws DaoException
	{
		try
		{
			if (flag)
			{
				return (EntidadPensionVO) this.session.createCriteria(EntidadPensionVO.class).add(Restrictions.eq("id", new Integer(id))).add(
						Restrictions.eq("idEntPagadora", new Integer(idEntPagadora))).add(Restrictions.ne("id", new Integer(Constants.ID_INP))).uniqueResult();
			}
			return (EntidadPensionVO) this.session.createCriteria(EntidadPensionVO.class).add(Restrictions.eq("id", new Integer(id))).add(Restrictions.eq("idEntPagadora", new Integer(idEntPagadora)))
					.uniqueResult();
		} catch (Exception ex)
		{
			log.error("Error en EntidadesDAO:getEntsPension");
			throw new DaoException("EntidadesDAO:getEntsPension:", ex);
		}
	}

	/**
	 * 
	 * @param flag
	 * @return lista entidad salud
	 * @throws DaoException
	 */
	public List getEntsSalud(boolean flag) throws DaoException
	{
		try
		{
			if (flag)
				return this.session.createCriteria(EntidadSaludVO.class).add(Restrictions.ne("id", new Integer(Constants.ID_FONASA))).list();
			return this.session.createCriteria(EntidadSaludVO.class).list();
		} catch (Exception ex)
		{
			log.error("Error en EntidadesDAO:getEntsSalud");
			throw new DaoException("EntidadesDAO:getEntsSalud:", ex);
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
			log.error("Error en EntidadesDAO:getEntidades");
			throw new DaoException("EntidadesDAO:getEntidades:", ex);
		}
	}

	/**
	 * 
	 * @param id
	 * @param idEntPagadora
	 * @param flag
	 * @return entidad salud
	 * @throws DaoException
	 */
	public EntidadSaludVO getEntsSalud(int idEntPagadora, boolean flag) throws DaoException
	{
		try
		{
			List lista = new ArrayList();
			if (flag)
			{
				lista = this.session.createCriteria(EntidadSaludVO.class).add(Restrictions.eq("idEntPagadora", new Integer(idEntPagadora)))
						.add(Restrictions.ne("id", new Integer(Constants.ID_FONASA))).list();
			} else
			{
				lista = this.session.createCriteria(EntidadSaludVO.class).add(Restrictions.eq("idEntPagadora", new Integer(idEntPagadora))).list();
			}
			EntidadSaludVO _e = new EntidadSaludVO();
			if (lista.size() > 0)
				_e = (EntidadSaludVO) lista.get(0);
			return _e;
		} catch (Exception ex)
		{
			log.error("Error en EntidadesDAO:getEntsSalud");
			throw new DaoException("EntidadesDAO:getEntsSalud:", ex);
		}
	}

	/**
	 * 
	 * @return lista entidad sil
	 * @throws DaoException
	 */
	public List getEntsSIL() throws DaoException
	{
		try
		{
			return this.session.createCriteria(EntidadSilVO.class).list();
		} catch (Exception ex)
		{
			log.error("Error en EntidadesDAO:getEntsSIL");
			throw new DaoException("EntidadesDAO:getEntsSIL:", ex);
		}
	}

	/**
	 * 
	 * @param obj
	 * @param id
	 * @param col
	 * @param id2
	 * @param col2
	 * @return existe
	 */
	public boolean getExiste(Class obj, int id, String col, int id2, String col2)
	{
		try
		{
			log.info("EntidadesDAO:getExiste: " + id);
			List lista = this.session.createCriteria(obj).add(Restrictions.eq(col, new Integer(id))).add(Restrictions.eq(col2, new Integer(id2))).list();
			if (lista.size() > 0)
				return true;
			return false;
		} catch (Exception ex)
		{
			log.error("Error en EntidadesDAO:getExiste: " + ex);
		}
		return false;
	}

	/**
	 * 
	 * @return generos
	 * @throws DaoException
	 */
	public List getGeneros() throws DaoException
	{
		try
		{
			return this.session.createCriteria(GeneroVO.class).list();
		} catch (Exception ex)
		{
			log.error("Error en EntidadesDAO:getGeneros");
			throw new DaoException("EntidadesDAO:getGeneros:", ex);
		}
	}

	/**
	 * 
	 * @param idMutual
	 * @return entidad mutal
	 * @throws DaoException
	 */
	public EntidadMutualVO getMutual(int idMutual) throws DaoException
	{
		try
		{
			log.info("EntidadesDAO:getMutual: " + idMutual);
			return (EntidadMutualVO) this.session.load(EntidadMutualVO.class, new Integer(idMutual));
		} catch (Exception ex)
		{
			log.error("Error en EntidadesDAO:getMutual: " + ex);
			throw new DaoException("EntidadesDAO:getMutual:", ex);
		}
	}

	/**
	 * 
	 * @param idRegion
	 * @return region
	 * @throws DaoException
	 */
	public RegionVO getRegion(int idRegion) throws DaoException
	{
		try
		{
			// log.info("EntidadesDAO:getRegion: " + idRegion);
			return (RegionVO) this.session.load(RegionVO.class, new Integer(idRegion));
		} catch (Exception ex)
		{
			log.error("Error en EntidadesDAO:getRegion: " + ex);
			throw new DaoException("EntidadesDAO:getRegion:", ex);
		}
	}

	/**
	 * 
	 * @return lista regiones
	 * @throws DaoException
	 */
	public List getRegiones() throws DaoException
	{
		try
		{
			// log.info("EntidadesDAO:getRegiones:");
			return this.session.createCriteria(RegionVO.class).addOrder(Order.asc("idRegion")).add(Restrictions.ne("idRegion", new Integer(Constants.ID_REGION_DEFAULT))).list();
		} catch (Exception ex)
		{
			log.error("Error en EntidadesDAO:getRegiones");
			throw new DaoException("EntidadesDAO:getRegiones:", ex);
		}
	}

	/**
	 * 
	 * @return lista tipos movimiento personal
	 * @throws DaoException
	 */
	public List getTiposMovPersonal() throws DaoException
	{
		try
		{
			return this.session.createCriteria(TipoMovimientoPersonalVO.class).list();
		} catch (Exception ex)
		{
			log.error("Error en EntidadesDAO:getTiposMovPersonal");
			throw new DaoException("EntidadesDAO:getTiposMovPersonal:", ex);
		}
	}

	/**
	 * 
	 * @return lista tipos movimiento personal af
	 * @throws DaoException
	 */
	public List getTiposMovPersonalAF() throws DaoException
	{
		try
		{
			return this.session.createCriteria(TipoMvtoPersoAFVO.class).list();
		} catch (Exception ex)
		{
			log.error("Error en EntidadesDAO:getTiposMovPersonalAF");
			throw new DaoException("EntidadesDAO:getTiposMovPersonalAF:", ex);
		}
	}

	/**
	 * 
	 * @return lista tramos asignacion familiar
	 * @throws DaoException
	 */
	public List getTramosAsigFam() throws DaoException
	{
		try
		{
			List result = this.session.createCriteria(AsigFamVO.class).list();
			// showList2(result);
			return result;
		} catch (Exception ex)
		{
			log.error("Error en EntidadesDAO:getTramosAsigFam");
			throw new DaoException("EntidadesDAO:getTramosAsigFam:", ex);
		}
	}

	/**
	 * guarda entidad regimen impositivo
	 * 
	 * @param regimenVO
	 * @throws DaoException
	 */
	public void guardaEntsRegimenImpositivo(RegImpositivoVO regimenVO) throws DaoException
	{
		try
		{
			log.info("EntidadesDAO:guardaEntsRegimenImpositivo");
			this.session.merge(regimenVO);

		} catch (Exception ex)
		{
			log.error("EntidadesDAO:guardaEntsRegimenImpositivo error: " + ex.getCause().toString());
			throw new DaoException("Problemas en EntidadesDAO:guardaEntsRegimenImpositivo", ex);
		}
	}

	/**
	 * nuevaEntidad nueva entidad regimen impositivo
	 * 
	 * @param regimen
	 * @throws DaoException
	 */
	public void nuevaEntsRegimenImpositivo(RegImpositivoVO regimen) throws DaoException
	{
		try
		{
			log.info("EntidadesDAO:nuevaEntsRegimenImpositivo");

			this.session.save(regimen);

		} catch (Exception ex)
		{
			log.error("EntidadesDAO:nuevaEntsCcaf error: " + ex.getCause().toString());
			throw new DaoException("Problemas en EntidadesDAO:nuevaEntsCcaf", ex);
		}
	}

	/**
	 * reinicio folios
	 * 
	 * @throws DaoException
	 */
	public void reinicioFolios() throws DaoException
	{
		try
		{
			log.info("EntidadesDAO:reinicioFolios");

			ParametroVO param = (ParametroVO) this.session.get(ParametroVO.class, new Integer(Constants.PARAM_REINICIO_FOLIOS));
			if (param != null)
			{
				String data[] = param.getValor().trim().split(",");
				for (int i = 0; i < data.length; i++)// por cada entidad
				{
					List folios = this.session.createCriteria(FoliacionVO.class).add(Restrictions.eq("idEntPagadora", new Integer(data[i]))).list();
					for (Iterator it = folios.iterator(); it.hasNext();) // por cada folio
					{
						FoliacionVO folio = (FoliacionVO) it.next();
						folio.setFolioInicial(1);
						folio.setFolioActual(1);
						folio.setFolioFinal(2000000);
						this.session.merge(folio);
					}
				}

				log.info("EntidadesDAO: fin reinicioFolios");
			} else
				throw new DaoException("Problemas en EntidadesDAO:reinicioFolios: no se encontro parametro necesario:reinicioFolios");
		} catch (Exception ex)
		{
			log.error("EntidadesDAO:nuevaEntidad error: " + ex.getCause().toString());
			throw new DaoException("Problemas en EntidadesDAO:reinicioFolios", ex);
		}
	}

	/**
	 * 
	 * @param flag
	 * @param sacarNinguna
	 * @return lista entidad pension
	 * @throws DaoException
	 */
	public List getEntsPension(boolean flag, boolean sacarNinguna) throws DaoException
	{
		try
		{
			Criteria crit = this.session.createCriteria(EntidadPensionVO.class);
			if (flag) // Sacar INP
				crit = crit.add(Restrictions.ne("id", new Integer(Constants.ID_INP)));
			if (sacarNinguna)
				crit = crit.add(Restrictions.ne("id", new Integer(Constants.ID_AFP_NINGUNA)));
			return crit.list();
		} catch (Exception ex)
		{
			log.error("Error en EntidadesDAO:getEntsPension");
			throw new DaoException("EntidadesDAO:getEntsPension:", ex);
		}
	}

	/**
	 * 
	 * @return lista regimen impositivo
	 * @throws DaoException
	 */
	public List getRegImp() throws DaoException
	{
		try
		{
			return this.session.createCriteria(RegImpositivoVO.class).add(Restrictions.ne("idRegImpositivo", new Integer(Constants.CODREGIMP_FALSO))).list();
		} catch (Exception ex)
		{
			log.error("Error en EntidadesDAO:getRegImp");
			throw new DaoException("EntidadesDAO:getRegImp:", ex);
		}
	}

	/**
	 * 
	 * @param idExCaja
	 * @return lista regimen impositivo
	 * @throws DaoException
	 */
	public List getRegImp(int idExCaja) throws DaoException
	{
		try
		{
			List lista = this.session.createCriteria(RegImpositivoVO.class).add(Restrictions.eq("idEntExCaja", new Integer(idExCaja))).list();
			return lista;
		} catch (Exception ex)
		{
			log.error("Error en EntidadesDAO:getRegImp");
			throw new DaoException("EntidadesDAO:getRegImp:", ex);
		}
	}

	/**
	 * 
	 * @param idExCaja
	 * @param idEntidad
	 * @return reimen impositivo
	 * @throws DaoException
	 */
	public RegImpositivoVO getRegImp(int idExCaja, int idEntidad) throws DaoException
	{
		try
		{
			return (RegImpositivoVO) this.session.createCriteria(RegImpositivoVO.class).add(Restrictions.eq("idEntExCaja", new Integer(idExCaja))).add(
					Restrictions.eq("idRegImpositivo", new Integer(idEntidad))).uniqueResult();

		} catch (Exception ex)
		{
			log.error("Error en EntidadesDAO:getRegImp");
			throw new DaoException("EntidadesDAO:getRegImp:", ex);
		}
	}

	/**
	 * 
	 * @param idEntSalud
	 * @return entidadsalud
	 * @throws DaoException
	 */
	public EntidadSaludVO getEntSalud(int idEntSalud) throws DaoException
	{
		try
		{
			Criteria crit = this.session.createCriteria(EntidadSaludVO.class).add(Restrictions.eq("id", new Integer(idEntSalud)));
			List result = crit.list();
			if (result != null && result.size() > 0)
				return (EntidadSaludVO) result.get(0);
			return null;
		} catch (Exception ex)
		{
			log.error("Error en EntidadesDAO:getEntSalud");
			throw new DaoException("EntidadesDAO:getEntSalud:", ex);
		}
	}

	/**
	 * 
	 * @param idFondoPension
	 * @return entidad pension
	 * @throws DaoException
	 */
	public EntidadPensionVO getEntFondoPension(int idFondoPension) throws DaoException
	{
		try
		{
			return (EntidadPensionVO) this.session.get(EntidadPensionVO.class, new Integer(idFondoPension));
		} catch (Exception ex)
		{
			log.error("Error en EntidadesDAO:getEntFondoPension");
			throw new DaoException("EntidadesDAO:getEntFondoPension:", ex);
		}
	}

	/**
	 * 
	 * @param idEntAPV
	 * @return entidad apv
	 * @throws DaoException
	 */
	public EntidadApvVO getEntApv(int idEntAPV) throws DaoException
	{
		try
		{
			return (EntidadApvVO) this.session.get(EntidadApvVO.class, new Integer(idEntAPV));
		} catch (Exception ex)
		{
			log.error("Error en EntidadesDAO:getEntApv:" + idEntAPV + "::");
			throw new DaoException("EntidadesDAO:getEntApv:", ex);
		}
	}

	/**
	 * 
	 * @param idAsigFam
	 * @return asignacion familiar
	 * @throws DaoException
	 */
	public AsigFamVO getAsigFam(int idAsigFam) throws DaoException
	{
		try
		{
			return (AsigFamVO) this.session.get(AsigFamVO.class, new Integer(idAsigFam));
		} catch (Exception ex)
		{
			log.error("Error en EntidadesDAO:getAsigFam:" + idAsigFam + "::");
			throw new DaoException("EntidadesDAO:getAsigFam:", ex);
		}
	}

	/**
	 * 
	 * @param idGenero
	 * @return genero
	 * @throws DaoException
	 */
	public GeneroVO getGenero(int idGenero) throws DaoException
	{
		try
		{
			return (GeneroVO) this.session.get(GeneroVO.class, new Integer(idGenero));
		} catch (Exception ex)
		{
			log.error("Error en EntidadesDAO:getGenero:" + idGenero + "::");
			throw new DaoException("EntidadesDAO:getGenero:", ex);
		}
	}

	/**
	 * crea mapeos
	 * 
	 * @param idEntPagadora
	 * @param nuevoCodigo
	 * @param tipo
	 * @throws DaoException
	 */
	public void creaMapeos(EntidadVO entidad) throws DaoException
	{
		try
		{
			log.info("EntidadesDAO:creaMapeos");
			if (entidad.getMapeoAsociado() != null)
			{
				List lista = this.session.createCriteria(MapaCodigoVO.class).list();
				for (Iterator it = lista.iterator(); it.hasNext();)
				{
					MapaCodigoVO mapa = (MapaCodigoVO) it.next();
					Class claseMapeo = Class.forName(entidad.getMapeoAsociado().getName());
					Class[] argTypes = { Integer.class, Integer.class, String.class };
					Object[] argValues = { new Integer(mapa.getIdMapaCodigo()), new Integer(entidad.getId()), Constants.VALOR_MAPEO_DEFECTO };
					MapeoVO mapeo = (MapeoVO) claseMapeo.getConstructor(argTypes).newInstance(argValues);
					this.session.save(mapeo);
				}
			}
		} catch (Exception ex)
		{
			log.error("EntidadesDAO:actualizaMapeos error: " + ex.getCause().toString());
			throw new DaoException("Problemas en EntidadesDAO:actualizaMapeos", ex);
		}
	}

	/**
	 * 
	 * @param id
	 * @return lista banco asociado entidad
	 * @throws DaoException
	 */
	public int consultaBancoAociadoEntidad(int id) throws DaoException
	{
		try
		{
			log.info("EntidadesDAO:consultaBancoAociadoEntidad");
			List lista = this.session.createCriteria(EntidadPagadoraVO.class).add(Restrictions.eq("idCtoBanco", new Integer(id))).list();

			return lista.size();
		} catch (Exception ex)
		{
			log.error("EntidadesDAO:actualizaMapeoTesoreria error: " + ex.getCause().toString());
			throw new DaoException("Problemas en EntidadesDAO:consultaBancoAociadoEntidad", ex);
		}

	}

	/**
	 * 
	 * @param idEntPagadora
	 * @param idEntidad
	 * @return lista entidad ex caja
	 * @throws DaoException
	 */
	public EntidadExCajaVO getEntExCaja(int idEntPagadora, int idEntidad) throws DaoException
	{
		try
		{
			log.info("EntidadesDAO:consultaBancoAociadoEntidad");
			List lista = this.session.createCriteria(EntidadExCajaVO.class).add(Restrictions.eq("id", new Integer(idEntidad))).add(Restrictions.eq("idEntPagadora", new Integer(idEntPagadora))).list();
			if (lista.size() > 0)
				return (EntidadExCajaVO) lista.get(0);
			return null;
		} catch (Exception ex)
		{
			log.error("EntidadesDAO:getEntExCaja error: " + ex.getCause().toString());
			throw new DaoException("Problemas en EntidadesDAO:getEntExCaja", ex);
		}
	}

	/**
	 * 
	 * @param entidadExCajaVO
	 * @return actualiza entidad ex caja
	 * @throws DaoException
	 */
	public boolean actualizaEntidadExCaja(EntidadExCajaVO entidadExCajaVO) throws DaoException
	{
		try
		{
			this.session.update(entidadExCajaVO);
			return true;
		} catch (Exception e)
		{
			log.error("EntidadesDAO:actualizaEntidadExCaja error: " + e.getCause().toString());
			throw new DaoException("Problemas en EntidadesDAO:actualizaEntidadExCaja", e);

		}
	}

	/**
	 * eliminar regimen
	 * 
	 * @param id
	 */
	public int eliminaRegimen(int id)
	{
		try
		{
			log.info(" eliminaRegimen");
			List lista = this.session.createCriteria(RegImpositivoVO.class).add(Restrictions.eq("idEntExCaja", new Integer(id))).list();
			Iterator it = lista.iterator();
			while (it.hasNext())
			{
				RegImpositivoVO reg = (RegImpositivoVO) it.next();
				int num = isReferenced(reg);
				if (num == 0)
					this.session.delete(reg);
				else
					return num;
			}
			return 0;
		} catch (Exception ex)
		{
			log.error("Error en EntidadesDAO:eliminaRegimen: " + ex);
			return 20;
		}
	}

	/**
	 * guardar regimen
	 * 
	 * @param listaRegimenVO
	 */
	public int guardaRegimen(List listaRegimenVO)
	{
		try
		{
			log.info(" guardaRegimen");
			Iterator it = listaRegimenVO.iterator();
			while (it.hasNext())
			{
				RegImpositivoVO reg = (RegImpositivoVO) it.next();
				this.session.save(reg);

			}
			return 0;
		} catch (Exception ex)
		{
			log.error("Error en EntidadesDAO:actualizaRegimen: " + ex);
			return 21;
		}
	}

	/**
	 * actualiza regimen
	 * 
	 * @param listaRegimenVO
	 */
	public int actualizaRegimen(List listaRegimenVO)
	{
		try
		{
			log.info(" guardaRegimen");
			HashMap listaBorrar = new HashMap();
			Iterator it = listaRegimenVO.iterator();
			while (it.hasNext())
			{
				RegImpositivoVO reg = (RegImpositivoVO) it.next();
				listaBorrar.put(String.valueOf(reg.getIdRegImpositivo()), reg);
			}
			it = listaRegimenVO.iterator();
			while (it.hasNext())
			{
				RegImpositivoVO reg = (RegImpositivoVO) it.next();
				listaBorrar.remove(String.valueOf(reg.getIdRegImpositivo()));
				if (this.session.createCriteria(RegImpositivoVO.class).add(Restrictions.eq("idEntExCaja", new Integer(reg.getIdEntExCaja()))).add(
						Restrictions.eq("idRegImpositivo", new Integer(reg.getIdRegImpositivo()))).list().size() > 0)
					this.session.merge(reg);
				else
					this.session.save(reg);
			}
			int error = 0;
			for (Iterator itH = listaBorrar.keySet().iterator(); itH.hasNext();)
			{
				if (error == 0)
				{
					RegImpositivoVO reg = (RegImpositivoVO) itH.next();
					error = eliminaRegimen(reg.getIdRegImpositivo());
				}

			}
			return error;
		} catch (Exception ex)
		{
			log.error("Error en EntidadesDAO:actualizaRegimen: " + ex);
			return 21;
		}
	}

	/**
	 * guardar ex caja
	 * 
	 * @param entidadExCajaVO
	 * @throws DaoException
	 */
	public int guardarEntExCaja(EntidadExCajaVO entidadExCajaVO)
	{
		try
		{
			this.session.save(entidadExCajaVO);
			return 0;
		} catch (Exception e)
		{
			log.error("EntidadesDAO:guardarEntExCaja error: " + e.getCause().toString());
			return 23;
		}
	}

	/**
	 * eliminar ex caja
	 * 
	 * @param i
	 */
	public int eliminaEntidadExCaja(int i)
	{
		try
		{
			log.info(" eliminaEntidadExCaja " + i);
			List lista = this.session.createCriteria(EntidadExCajaVO.class).add(Restrictions.eq("id", new Integer(i))).list();
			EntidadExCajaVO exCaja = (EntidadExCajaVO) lista.get(0);
			int error = isReferenced(exCaja);
			if (error == 0)
				this.session.delete(exCaja);
			return error;
		} catch (Exception ex)
		{
			log.error("Error en EntidadesDAO:eliminaRegimen: " + ex);
			return 22;
		}
	}

	/**
	 * @param entidadPagadoraVO
	 * @return
	 */
	public int updateEntidadPagadora(boolean force, EntidadPagadoraVO entidadPagadoraVO)
	{
		try
		{
			log.info("EntidadesDAO: updateEntidadPagadora");
			if (!force)
			{
				EntidadPagadoraVO ent = (EntidadPagadoraVO) this.session.get(EntidadPagadoraVO.class, new Integer(entidadPagadoraVO.getIdEntPagadora()));
				entidadPagadoraVO.setIdCtaCte(ent.getIdCtaCte());
				entidadPagadoraVO.setIdCtoBanco(ent.getIdCtoBanco());
			}

			//GMALLEA 18-03-2014 Se ocupa HQL ya que de la otra forma persiste todo con mayusculas. 
			Query query = session.createQuery("update "+EntidadPagadoraVO.class.getName()+" set "+
						  " idCtoBanco = "+entidadPagadoraVO.getIdCtoBanco()+","+
						  " idCtaCte ='"+entidadPagadoraVO.getIdCtaCte()+"',"+
						  " nombre = '"+entidadPagadoraVO.getNombre()+"',"+
						  " tieneConvenio = "+entidadPagadoraVO.getTieneConvenio()+","+
						  " imprime = "+entidadPagadoraVO.getImprime()+","+
						  " idBancoSpl = "+entidadPagadoraVO.getIdBancoSpl()+","+
						  " idCtaCteSpl = '"+entidadPagadoraVO.getIdCtaCteSpl()+"',"+
						  " generaCheque = "+entidadPagadoraVO.isGeneraCheque()+","+
						  " reiniciaFolio = "+entidadPagadoraVO.isReiniciaFolio() +","+
						  " correoContacto = '"+entidadPagadoraVO.getCorreoContacto()+"',"+
						  " nombreContacto = '"+entidadPagadoraVO.getNombreContacto()+"',"+
						  " entidadFTP = '"+entidadPagadoraVO.getEntidadFTP()+"',"+
						  " carpetaFTP = '"+entidadPagadoraVO.getCarpetaFTP()+"',"+
						  " usuarioFTP = '"+entidadPagadoraVO.getUsuarioFTP()+"',"+
						  " claveFTP = '"+entidadPagadoraVO.getClaveFTP()+"',"+
						  " tipoArchMovimiento = "+entidadPagadoraVO.getTipoArchMovimiento().intValue()+
						  " where idEntPagadora= " +entidadPagadoraVO.getIdEntPagadora());
			
			query.executeUpdate();


			//this.session.merge(entidadPagadoraVO);
		} catch (Exception ex)
		{
			log.info("EntidadesDAO: ERR: updateEntidadPagadora: no se pudo actualizar entidadPagadoraVO");
			return 1;
		}
		return 0;
	}

	/**
	 * @param tipo
	 * @param idCodAntiguo
	 * @param entidad
	 * @return
	 */
	public int updateEntidad(boolean force, Class tipo, int idCodAntiguo, EntidadVO entidad)
	{
		try
		{
			int error = 0;
			log.info("EntidadesDAO: updateEntidad:" + tipo + ":codOrigen:" + idCodAntiguo + ":codNew:" + entidad.getId() + ":");

			List listaEnt = this.session.createCriteria(tipo).add(Restrictions.eq("id", new Integer(entidad.getId()))).list();
			if (listaEnt.size() == 0)// cambio id
			{
				log.info("EntidadesDAO:updateEntidad:cambio ID");
				List listaEntAnt = this.session.createCriteria(tipo).add(Restrictions.eq("id", new Integer(idCodAntiguo))).list();
				if (entidad.getClave().equals("AFP"))
				{// mantiene datos de cta para AFC
					EntidadPensionVO entOld = (EntidadPensionVO) listaEntAnt.get(0);
					EntidadPensionVO afp = (EntidadPensionVO) entidad;
					afp.setIdBanco(entOld.getIdBanco());
					afp.setIdCtaCte(entOld.getIdCtaCte());
				}
				this.session.save(entidad);
				if (force && entidad.getClave().equals("AFP"))
				{
					EntidadPensionVO afp = (EntidadPensionVO) entidad;
					if (afp.getIdAfc() != Constants.AFC_FALSO)
					{
						int contador = ((Integer) this.session.createCriteria(EntidadPensionVO.class).add(Restrictions.ne("id", new Integer(idCodAntiguo))).add(
								Restrictions.eq("idAfc", new Integer(afp.getIdAfc()))).setProjection(Projections.rowCount()).list().get(0)).intValue();
						if (contador > 1)
							return 25;
						EntidadAFCVO afc = (EntidadAFCVO) this.session.get(EntidadAFCVO.class, new Integer(afp.getIdAfc()));
						if (afc != null)
						{
							this.session.save(new EntidadAFCVO(afp.getId(), afp.getIdEntPagadora()));
							listaEntAnt.add(afc);
							afp.setIdAfc(afp.getId());
							this.session.merge(afp);
						}
					}
				}
				error = this.updateReferencias(idCodAntiguo, entidad);
				if (error == 0)
					for (Iterator it = listaEntAnt.iterator(); it.hasNext();)
						this.session.delete(it.next());
				else
					return error;
			} else
			{
				log.info("EntidadesDAO:updateEntidad:no cambio ID");
				if (idCodAntiguo != entidad.getId())
					return 3;
				if (force && entidad.getClave().equals("AFP"))
				{// mantiene datos de cta para AFC
					EntidadPensionVO entOld = (EntidadPensionVO) listaEnt.get(0);
					EntidadPensionVO afp = (EntidadPensionVO) entidad;
					afp.setIdBanco(entOld.getIdBanco());
					afp.setIdCtaCte(entOld.getIdCtaCte());
				}
				this.session.merge(entidad);
			}

		} catch (Exception ex)
		{
			log.error("EntidadesDAO: ERR: updateEntidad: " + tipo + " no se pudo actualizar entidad", ex);
			return 2;
		}
		return 0;
	}

	/**
	 * 
	 * @param entidad
	 * @throws DaoException
	 */
	public void creaTipoDetalle(EntidadVO entidad) throws DaoException
	{
		try
		{
			log.info("EntidadesDAO: updateTipoDetalle");
			HashMap propsMapeos = this.detalleReporteDao.getPropertiesMapeo(Constants.PROP_MAPEO_SECC_TP);
			List tiposNominas = this.nominaDao.getTiposNominas();
			String claveEspecial = "RAPVC";

			for (Iterator it = tiposNominas.iterator(); it.hasNext();)
			{
				TipoNominaVO tn = (TipoNominaVO) it.next();

				if (propsMapeos.containsKey(tn.getIdTipoNomina() + entidad.getClave()))
				{
					TipoDetalleVO tipoDetalleVO = new TipoDetalleVO();
					tipoDetalleVO.setIdTipoNomina(tn.getIdTipoNomina().charAt(0));
					tipoDetalleVO.setIdTipoSeccion(Integer.parseInt((String) propsMapeos.get(tn.getIdTipoNomina() + entidad.getClave())));
					tipoDetalleVO.setIdDetalleSeccion(entidad.getId());
					tipoDetalleVO.setIdEntPagadora(entidad.getIdEntPagadora());
					if (this.session.get(TipoDetalleVO.class, tipoDetalleVO) != null)
						this.session.merge(tipoDetalleVO);
					else
						this.session.save(tipoDetalleVO);
				}
				if (claveEspecial.indexOf(entidad.getClave()) != -1)
				{
					TipoDetalleVO tipoDetalleVO = new TipoDetalleVO();
					tipoDetalleVO.setIdTipoNomina(tn.getIdTipoNomina().charAt(0));
					tipoDetalleVO.setIdTipoSeccion(Integer.parseInt((String) propsMapeos.get(claveEspecial)));
					tipoDetalleVO.setIdDetalleSeccion(entidad.getId());
					tipoDetalleVO.setIdEntPagadora(entidad.getIdEntPagadora());
					if (this.session.get(TipoDetalleVO.class, tipoDetalleVO) != null)
						this.session.merge(tipoDetalleVO);
					else
						this.session.save(tipoDetalleVO);
				}
			}
		} catch (Exception ex)
		{
			log.error("EntidadesDAO: updateTipoDetalle error: " + ex.getCause().toString());
			throw new DaoException("Problemas en EntidadesDAO: updateTipoDetalle", ex);
		}
	}

	/**
	 * @param tipo
	 * @param entidad
	 * @return
	 */
	public int delEntidad(Class tipo, EntidadVO entidad)
	{
		int error = 0;
		try
		{
			log.info("EntidadesDAO: delEntidad");
			error = this.isReferenced(entidad);
			if (error != 0)
				return error;

			List lista = this.session.createCriteria(tipo).add(Restrictions.eq("idEntPagadora", new Integer(entidad.getIdEntPagadora()))).add(Restrictions.eq("id", new Integer(entidad.getId())))
					.list();
			this.delTipoDetalle(entidad.getIdEntPagadora(), entidad.getId());
			this.delMapeos(entidad);
			for (Iterator it = lista.iterator(); it.hasNext();)
				this.session.delete(it.next());
			this.session.flush();
		} catch (Exception ex)
		{
			log.info("EntidadesDAO: ERR: delEntidad: " + entidad.getClave() + " no se pudo eliminar la entidad");
			return 12;
		}
		return 0;
	}

	private void delMapeos(EntidadVO entidad) throws DaoException
	{
		try
		{
			if (entidad.getMapeoAsociado() != null)
			{
				List list = this.session.createCriteria(entidad.getMapeoAsociado()).add(Restrictions.eq("id", new Integer(entidad.getId()))).list();
				for (Iterator it = list.iterator(); it.hasNext();)
					this.session.delete(it.next());
			}
			this.session.flush();
		} catch (Exception ex)
		{
			log.error("Error en EntidadesDAO:delMapeos");
			throw new DaoException("EntidadesDAO:delMapeos:", ex);
		}
	}

	/**
	 * @param entidadPagadoraVO
	 * @return
	 */
	public int delEntidadPagadora(EntidadPagadoraVO entidadPagadoraVO)
	{
		try
		{
			log.info("EntidadesVO:borraEntidadPagadora");
			if (0 != ((Integer) this.session.createCriteria(EntidadSaludVO.class).add(Restrictions.eq("idEntPagadora", new Integer(entidadPagadoraVO.getIdEntPagadora()))).setProjection(
					Projections.rowCount()).list().get(0)).intValue()
					+ ((Integer) this.session.createCriteria(EntidadSilVO.class).add(Restrictions.eq("idEntPagadora", new Integer(entidadPagadoraVO.getIdEntPagadora()))).setProjection(
							Projections.rowCount()).list().get(0)).intValue()
					+ ((Integer) this.session.createCriteria(EntidadCCAFVO.class).add(Restrictions.eq("idEntPagadora", new Integer(entidadPagadoraVO.getIdEntPagadora()))).setProjection(
							Projections.rowCount()).list().get(0)).intValue()
					+ ((Integer) this.session.createCriteria(EntidadPensionVO.class).add(Restrictions.eq("idEntPagadora", new Integer(entidadPagadoraVO.getIdEntPagadora()))).setProjection(
							Projections.rowCount()).list().get(0)).intValue()
					+ ((Integer) this.session.createCriteria(EntidadMutualVO.class).add(Restrictions.eq("idEntPagadora", new Integer(entidadPagadoraVO.getIdEntPagadora()))).setProjection(
							Projections.rowCount()).list().get(0)).intValue()
					+ ((Integer) this.session.createCriteria(EntidadAFCVO.class).add(Restrictions.eq("idEntPagadora", new Integer(entidadPagadoraVO.getIdEntPagadora()))).setProjection(
							Projections.rowCount()).list().get(0)).intValue()
					+ ((Integer) this.session.createCriteria(EntidadApvVO.class).add(Restrictions.eq("idEntPagadora", new Integer(entidadPagadoraVO.getIdEntPagadora()))).setProjection(
							Projections.rowCount()).list().get(0)).intValue())
				return 0;
			List lista = this.session.createCriteria(EntidadPagadoraVO.class).add(Restrictions.eq("idEntPagadora", new Integer(entidadPagadoraVO.getIdEntPagadora()))).list();
			EntidadPagadoraVO _entidadPagadora;
			for (Iterator it = lista.iterator(); it.hasNext();)
			{
				_entidadPagadora = (EntidadPagadoraVO) it.next();
				EntidadPrevisionalVO entiPrev = new EntidadPrevisionalVO(new Integer(entidadPagadoraVO.getIdEntPagadora()));
				this.session.delete(entiPrev);
				this.session.delete(_entidadPagadora);
			}
		} catch (Exception ex)
		{
			log.info("EntidadesDAO: ERR: delEntidadPagadora: no se pudo eliminar entidad pagadora");
			return 19;
		}
		return 0;
	}

	/**
	 * @param idEntPagadora
	 * @param id
	 * @param tipo
	 * @throws DaoException
	 */
	public void delTipoDetalle(int idEntPagadora, int id)
	{
		try
		{
			log.info("EntidadesDAO:delTipoDetalle");
			List lista = this.session.createCriteria(MapeoTesoreriaVO.class).add(Restrictions.eq("idDetalleSeccion", new Integer(id))).list();
			for (Iterator it = lista.iterator(); it.hasNext();)
				this.session.delete(it.next());

			lista = this.session.createCriteria(TipoDetalleVO.class).add(Restrictions.eq("idEntPagadora", new Integer(idEntPagadora))).add(Restrictions.eq("idDetalleSeccion", new Integer(id))).list();
			for (Iterator it = lista.iterator(); it.hasNext();)
				this.session.delete(it.next());
		} catch (Exception ex)
		{
			log.error("Error en EntidadesDAO:delTipoDetalle:", ex);
		}
	}

	/**
	 * @param entidadPagadoraVO
	 * @return
	 */
	public boolean saveEntidadPagadora(EntidadPagadoraVO entidadPagadoraVO, boolean force)
	{
		try
		{
			log.info("EntidadesDAO: saveEntidadPagadora");
			List lista = this.session.createCriteria(EntidadPagadoraVO.class).add(Restrictions.eq("idEntPagadora", new Integer(entidadPagadoraVO.getIdEntPagadora()))).list();
			if (lista.size() == 0)
			{
				EntidadPagadoraVO entidadPagadoraVO2 = new EntidadPagadoraVO();
				entidadPagadoraVO2.setIdEntPagadora(entidadPagadoraVO.getIdEntPagadora());
				entidadPagadoraVO2.setIdCtoBanco(entidadPagadoraVO.getIdCtoBanco());
				entidadPagadoraVO2.setIdCtaCte(entidadPagadoraVO.getIdCtaCte());
				entidadPagadoraVO2.setNombre(entidadPagadoraVO.getNombre());
				entidadPagadoraVO2.setTieneConvenio(entidadPagadoraVO.getTieneConvenio());
				entidadPagadoraVO2.setImprime(entidadPagadoraVO.getImprime());
				entidadPagadoraVO2.setIdBancoSpl(entidadPagadoraVO.getIdBancoSpl());
				entidadPagadoraVO2.setIdCtaCteSpl(entidadPagadoraVO.getIdCtaCteSpl());
				entidadPagadoraVO2.setGeneraCheque(entidadPagadoraVO.isGeneraCheque());
				entidadPagadoraVO2.setReiniciaFolio(entidadPagadoraVO.isReiniciaFolio());				
				entidadPagadoraVO2.setCorreoContacto(entidadPagadoraVO.getCorreoContacto());
				entidadPagadoraVO2.setNombreContacto(entidadPagadoraVO.getNombreContacto());
				entidadPagadoraVO2.setEntidadFTP(entidadPagadoraVO.getEntidadFTP());
				entidadPagadoraVO2.setCarpetaFTP(entidadPagadoraVO.getCarpetaFTP());	
				entidadPagadoraVO2.setUsuarioFTP(entidadPagadoraVO.getUsuarioFTP());
				entidadPagadoraVO2.setClaveFTP(entidadPagadoraVO.getClaveFTP());
				entidadPagadoraVO2.setTipoArchMovimiento(entidadPagadoraVO.getTipoArchMovimiento());
				
				this.session.save(entidadPagadoraVO);
				//GMALLEA Se actualiza para dejar los datos ftp en misuscula si lo corresponde
				this.updateEntidadPagadora(force, entidadPagadoraVO2);

				EntidadPrevisionalVO entPrevisional = new EntidadPrevisionalVO(entidadPagadoraVO.getIdEntPagadora());
				this.session.save(entPrevisional);
			} else if (force)
			{
				//GMALLEA 18-03-2014 Se ocupa HQL ya que de la otra forma persiste todo con mayusculas. 
				this.updateEntidadPagadora(force, entidadPagadoraVO);
				//this.session.merge(entidadPagadoraVO);
			}
		} catch (Exception ex)
		{
			log.info("EntidadesDAO: ERR: saveEntidadPagadora: no se pudo guardar entidadPagadoraVO");
			return false;
		}
		return true;
	}

	/**
	 * @param tipo
	 * @param entidad
	 * @return
	 */
	public boolean saveEntidad(Class tipo, EntidadVO entidad)
	{
		try
		{
			log.info("EntidadesDAO:saveEntidad");
			List lista = this.session.createCriteria(tipo).add(Restrictions.eq("id", new Integer(entidad.getId()))).list();
			List listaEP = this.session.createCriteria(tipo).add(Restrictions.eq("idEntPagadora", new Integer(entidad.getIdEntPagadora()))).list();
			if (listaEP.size() > 0)
				return false;
			if (lista.size() == 0)
			{
				this.session.save(entidad);
				if (!entidad.getClave().equals(""))
					this.creaTipoDetalle(entidad);
				this.creaMapeos(entidad);
			} else
			{
				log.info("EntidadesDAO: OBS: saveEntidad: ya existe la entidadVO");
				return false;
			}
		} catch (Exception ex)
		{
			log.info("EntidadesDAO: ERR: saveEntidad: no se pudo guardar entidadVO");
			return false;
		}
		return true;
	}

	/**
	 * @param idEntPagadora
	 * @return
	 * @throws DaoException
	 */
	public EntidadSilVO getEntsSil(int idEntPagadora) throws DaoException
	{
		try
		{
			List lista = this.session.createCriteria(EntidadSilVO.class).add(Restrictions.eq("idEntPagadora", new Integer(idEntPagadora))).list();
			EntidadSilVO _e = null;
			if (lista.size() > 0)
				_e = (EntidadSilVO) lista.get(0);
			return _e;
		} catch (Exception ex)
		{
			log.error("Error en EntidadesDAO:getEntsSil");
			throw new DaoException("EntidadesDAO:getEntsSil:", ex);
		}
	}

	/**
	 * @param idEntPagadora
	 * @return
	 * @throws DaoException
	 */
	public EntidadMutualVO getEntsMutual(int idEntPagadora) throws DaoException
	{
		try
		{
			List lista = this.session.createCriteria(EntidadMutualVO.class).add(Restrictions.eq("idEntPagadora", new Integer(idEntPagadora))).list();
			EntidadMutualVO _e = null;
			if (lista.size() > 0)
				_e = (EntidadMutualVO) lista.get(0);
			return _e;
		} catch (Exception ex)
		{
			log.error("Error en EntidadesDAO:getEntsMutual");
			throw new DaoException("EntidadesDAO:getEntsMutual:", ex);
		}
	}

	/**
	 * @param idEntPagadora
	 * @return
	 * @throws DaoException
	 */
	public EntidadApvVO getEntsApv(int idEntPagadora) throws DaoException
	{
		try
		{
			List lista = this.session.createCriteria(EntidadApvVO.class).add(Restrictions.eq("idEntPagadora", new Integer(idEntPagadora))).list();
			EntidadApvVO _e = null;
			if (lista.size() > 0)
				_e = (EntidadApvVO) lista.get(0);
			return _e;
		} catch (Exception ex)
		{
			log.error("Error en EntidadesDAO:getEntsApv");
			throw new DaoException("EntidadesDAO:getEntsApv:", ex);
		}
	}

	/**
	 * @param idEntPagadora
	 * @return
	 * @throws DaoException
	 */
	public EntidadPensionVO getEntsPension(int idEntPagadora) throws DaoException
	{
		try
		{
			List lista = this.session.createCriteria(EntidadPensionVO.class).add(Restrictions.eq("idEntPagadora", new Integer(idEntPagadora))).list();
			EntidadPensionVO _e = null;
			if (lista.size() > 0)
				_e = (EntidadPensionVO) lista.get(0);
			return _e;
		} catch (Exception ex)
		{
			log.error("Error en EntidadesDAO:getEntsPension");
			throw new DaoException("EntidadesDAO:getEntsPension:", ex);
		}
	}

	/**
	 * @param idEntPagadora
	 * @return
	 * @throws DaoException
	 */
	public EntidadCCAFVO getEntsCcaf(int idEntPagadora) throws DaoException
	{
		try
		{
			List lista = this.session.createCriteria(EntidadCCAFVO.class).add(Restrictions.eq("idEntPagadora", new Integer(idEntPagadora))).list();
			EntidadCCAFVO _e = null;
			if (lista.size() > 0)
				_e = (EntidadCCAFVO) lista.get(0);
			return _e;
		} catch (Exception ex)
		{
			log.error("Error en EntidadesDAO:getEntsCcaf");
			throw new DaoException("EntidadesDAO:getEntsCcaf:", ex);
		}
	}

	/**
	 * @param tipo
	 * @param idCodAntiguo
	 * @param entidad
	 * @return
	 */
	public int updateReferencias(int idCodAntiguo, EntidadVO entidadVO)
	{
		try
		{
			int idEntidad = entidadVO.getId();
			Integer idEntAntiguo = new Integer(idCodAntiguo);
			String clave = entidadVO.getClave();

			try
			{
				if (entidadVO.isCotizanteRefered())
				{
					Criteria crit = this.session.createCriteria(CotizanteVO.class);
					if (clave.equals("ISAPRE"))
						crit.add(Restrictions.eq("idEntSaludReal", idEntAntiguo));
					if (clave.equals("EXCAJA"))
						crit.add(Restrictions.eq("idEntExCaja", idEntAntiguo));
					if (clave.equals("AFP"))
						crit.add(Restrictions.eq("idEntPensionReal", idEntAntiguo));
					if (clave.equals("SIL"))
						crit.add(Restrictions.eq("idEntSil", idEntAntiguo));
					if (clave.equals("AFC"))
						crit.add(Restrictions.eq("idEntAfcReal", idEntAntiguo));
					List listaCotizantes = crit.list();
					for (Iterator it = listaCotizantes.iterator(); it.hasNext();)
					{
						CotizanteVO cotizante = (CotizanteVO) it.next();
						if (clave.equals(""))
							cotizante.setIdEntSaludReal(idEntidad);
						if (clave.equals("EXCAJA"))
							cotizante.setIdEntExCaja(idEntidad);
						if (clave.equals("AFP"))
							cotizante.setIdEntPensionReal(idEntidad);
						if (clave.equals("SIL"))
							cotizante.setIdEntSil(idEntidad);
						if (clave.equals("AFC"))
							cotizante.setIdEntAfcReal(idEntidad);
						this.session.merge(cotizante);
					}
					if (clave.equals("AFP"))
					{
						listaCotizantes = this.session.createCriteria(CotizanteVO.class).add(Restrictions.eq("idEntAfcReal", idEntAntiguo)).list();

						for (Iterator it = listaCotizantes.iterator(); it.hasNext();)
						{
							CotizanteVO cotizante = (CotizanteVO) it.next();
							cotizante.setIdEntAfcReal(idEntidad);
							this.session.merge(cotizante);
						}
					}
				}
			} catch (Exception ex1)
			{
				log.info("EntidadesDAO: ERR: updateReferencias: " + clave + " no se pudo actualizar el cotizante asociado a la entidad.");
				return 5;
			}
			try
			{
				if (entidadVO.isMapeoRefered())
				{
					List listToDel = new ArrayList();
					if (clave.equals("ISAPRE"))
					{
						List listaMapeo = this.session.createCriteria(MapeoSaludVO.class).add(Restrictions.eq("id", idEntAntiguo)).list();
						for (Iterator it = listaMapeo.iterator(); it.hasNext();)
						{
							MapeoSaludVO map = (MapeoSaludVO) it.next();
							MapeoSaludVO _map = new MapeoSaludVO(new Integer(map.getIdMapaCod()), new Integer(idEntidad), map.getCodigo());
							this.session.save(_map);
							listToDel.add(map);
						}
					} else if (clave.equals("APV"))
					{
						Criteria crit = this.session.createCriteria(MapeoAPVVO.class);
						crit.add(Restrictions.eq("id", idEntAntiguo));
						List listaMapeo = crit.list();
						for (Iterator it = listaMapeo.iterator(); it.hasNext();)
						{
							MapeoAPVVO map = (MapeoAPVVO) it.next();
							MapeoAPVVO _map = new MapeoAPVVO(new Integer(map.getIdMapaCod()), new Integer(idEntidad), map.getCodigo());
							this.session.save(_map);
							this.session.delete(map);
						}
					} else if (clave.equals("AFP"))
					{
						Criteria crit = this.session.createCriteria(MapeoPensionVO.class);
						crit.add(Restrictions.eq("id", idEntAntiguo));
						List listaMapeo = crit.list();
						for (Iterator it = listaMapeo.iterator(); it.hasNext();)
						{
							MapeoPensionVO map = (MapeoPensionVO) it.next();
							MapeoPensionVO _map = new MapeoPensionVO(new Integer(map.getIdMapaCod()), new Integer(idEntidad), map.getCodigo());
							this.session.save(_map);
							this.session.delete(map);
						}
					}
					for (Iterator it = listToDel.iterator(); it.hasNext();)
						this.session.delete(it.next());
				}
			} catch (Exception ex1)
			{
				log.info("EntidadesDAO: ERR: updateReferencias: " + clave + " no se pudo actualizar el mapeo asociado a la entidad.");
				return 6;
			}
			this.session.flush();
			try
			{
				if (entidadVO.isTipoDetRefered())
				{
					List lista = this.session.createCriteria(TipoDetalleVO.class).add(Restrictions.eq("idDetalleSeccion", idEntAntiguo)).add(
							Restrictions.eq("idEntPagadora", new Integer(entidadVO.getIdEntPagadora()))).list();
					List listToDel = new ArrayList();
					Set tiposSecc = new HashSet();
					for (Iterator it = lista.iterator(); it.hasNext();)
					{
						TipoDetalleVO tip = (TipoDetalleVO) it.next();
						TipoDetalleVO _tip = new TipoDetalleVO(tip);
						_tip.setIdDetalleSeccion(idEntidad);
						this.session.save(_tip);
						tiposSecc.add(new Integer(tip.getIdTipoSeccion()));
						listToDel.add(tip);
					}
					this.session.flush();
					if (tiposSecc.size() > 0)
					{
						Query query = this.session.createQuery("UPDATE " + MapeoTesoreriaVO.class.getName() + " map SET map.idDetalleSeccion = ? "
								+ "WHERE map.idDetalleSeccion = ? AND map.idTipoSeccion in (:lista)");
						query.setInteger(0, idEntidad);
						query.setInteger(1, idEntAntiguo.intValue());
						query.setParameterList("lista", tiposSecc);
						query.executeUpdate();
						for (Iterator it = listToDel.iterator(); it.hasNext();)
							this.session.delete(it.next());

						// actualiza referencia de detalles de comprobantes
						query = this.session.createQuery("UPDATE " + DetalleSeccionVO.class.getName() + " det SET det.idDetalleSeccion = ? "
								+ "WHERE det.idDetalleSeccion = ? AND det.idTipoSeccion in (:lista)");
						query.setInteger(0, idEntidad);
						query.setInteger(1, idEntAntiguo.intValue());
						query.setParameterList("lista", tiposSecc);
						query.executeUpdate();
					}
				}
			} catch (Exception ex1)
			{
				log.error("EntidadesDAO: ERR: updateReferencias: " + clave + " no se pudo actualizar el tipo detalle asociado a la entidad.", ex1);
				return 7;
			}
			this.session.flush();
			try
			{
				if (entidadVO.isConvenioRefered())
				{
					Criteria crit = this.session.createCriteria(ConvenioVO.class);
					if (clave.equals("MUTUAL"))
						crit.add(Restrictions.eq("idMutual", idEntAntiguo));
					if (clave.equals("CCAF"))
						crit.add(Restrictions.eq("idCcaf", idEntAntiguo));
					List listaConvenio = crit.list();
					for (Iterator it = listaConvenio.iterator(); it.hasNext();)
					{
						ConvenioVO convenio = (ConvenioVO) it.next();
						if (clave.equals("MUTUAL"))
							convenio.setIdMutual(idEntidad);
						if (clave.equals("CCAF"))
							convenio.setIdCcaf(idEntidad);
						this.session.merge(convenio);
					}
				}
			} catch (Exception ex1)
			{
				log.info("EntidadesDAO: ERR: updateReferencias: " + clave + " no se pudo actualizar el convenio asociado a la entidad.");
				return 8;
			}
			this.session.flush();
			try
			{
				if (entidadVO.isApvRefered())
				{

					Criteria crit = this.session.createCriteria(ApvVO.class);
					crit.add(Restrictions.eq("idEntidadApv", idEntAntiguo));
					List listaApv = crit.list();
					for (Iterator it = listaApv.iterator(); it.hasNext();)
					{
						ApvVO apv = (ApvVO) it.next();
						apv.setIdEntidadApv(idEntidad);
						this.session.merge(apv);
					}
				}
			} catch (Exception ex1)
			{
				log.info("EntidadesDAO: ERR: updateReferencias: " + clave + " no se pudo actualizar el APV asociado a la entidad.");
				return 9;
			}
			this.session.flush();
		} catch (Exception ex)
		{
			log.info("EntidadesDAO: ERR: updateEntidad: no se pudo actualizar entidad", ex);
			return 4;
		}
		return 0;
	}

	/**
	 * @param tipo
	 * @param idCodAntiguo
	 * @param entidad
	 * @return
	 */
	public int isReferenced(EntidadVO entidadVO)
	{
		try
		{
			Integer idEntidad = new Integer(entidadVO.getId());
			String clave = entidadVO.getClave();

			try
			{
				if (entidadVO.isCotizanteRefered())
				{
					Criteria crit = this.session.createCriteria(CotizanteVO.class);
					if (clave.equals("ISAPRE"))
						crit.add(Restrictions.eq("idEntSaludReal", idEntidad));
					if (clave.equals("EXCAJA"))
						crit.add(Restrictions.eq("idEntExCaja", idEntidad));
					if (clave.equals("AFP"))
						crit.add(Restrictions.eq("idEntPensionReal", idEntidad));
					if (clave.equals("SIL"))
						crit.add(Restrictions.eq("idEntSil", idEntidad));
					if (clave.equals("AFC"))
						crit.add(Restrictions.eq("idEntAfcReal", idEntidad));
					if (((Integer) crit.setProjection(Projections.rowCount()).list().get(0)).intValue() > 0)
						return 13;

					if (clave.equals("AFP"))
					{
						crit = this.session.createCriteria(CotizanteVO.class);
						crit.add(Restrictions.eq("idEntAfcReal", idEntidad));
						if (((Integer) crit.setProjection(Projections.rowCount()).list().get(0)).intValue() > 0)
							return 13;
					}
				}
			} catch (Exception ex1)
			{
				log.info("EntidadesDAO: ERR: isDelete: " + clave + " no se pudo eliminar la entidad por estar asociada a un cotizante.");
				return 13;
			}
			try
			{
				if (entidadVO.isConvenioRefered())
				{
					Criteria crit = this.session.createCriteria(ConvenioVO.class);
					if (clave.equals("MUTUAL"))
						crit.add(Restrictions.eq("idMutual", idEntidad));
					if (clave.equals("CAJA"))
						crit.add(Restrictions.eq("idCcaf", idEntidad));
					if (((Integer) crit.setProjection(Projections.rowCount()).list().get(0)).intValue() > 0)
					{
						if (clave.equals("CAJA"))
							return 16;// msg convenio
						return 24;// msg empresa
					}
				}
			} catch (Exception ex1)
			{
				log.info("EntidadesDAO: ERR: isDelete: " + clave + " no se pudo eliminar la entidad. hay convenios asociados");
				if (clave.equals("CAJA"))
					return 16;// msg convenio
				return 24;// msg empresa
			}
			try
			{
				if (entidadVO.isApvRefered())
				{
					Criteria crit = this.session.createCriteria(ApvVO.class).add(Restrictions.eq("idEntidadApv", idEntidad));
					if (((Integer) crit.setProjection(Projections.rowCount()).list().get(0)).intValue() > 0)
						return 17;
				}
			} catch (Exception ex1)
			{
				log.info("EntidadesDAO: ERR: isDelete: " + clave + " no se pudo eliminar la entidad. hay apv asociado");
				return 17;
			}
			try
			{
				if (entidadVO.isAfcRefered()
						&& ((Integer) this.session.createCriteria(EntidadPensionVO.class).add(Restrictions.eq("idAfc", idEntidad)).setProjection(Projections.rowCount()).list().get(0)).intValue() > 0)
					return 18;
			} catch (Exception ex1)
			{
				log.info("EntidadesDAO: ERR: isDelete: " + clave + " no se pudo eliminar la entidad. Esta asociada a un AFP");
				return 18;
			}
		} catch (Exception ex)
		{
			log.error("EntidadesDAO: ERR: isDelete: no se pudo eliminar entidad", ex);
			return 4;
		}
		return 0;
	}

	public int isReferenced(RegImpositivoVO reg)
	{
		try
		{
			Criteria crit = this.session.createCriteria(CotizanteVO.class);
			crit.add(Restrictions.eq("idRegimenImp", new Integer(reg.getIdRegImpositivo())));
			crit.add(Restrictions.eq("idEntExCaja", new Integer(reg.getIdEntExCaja())));
			if (((Integer) crit.setProjection(Projections.rowCount()).list().get(0)).intValue() > 0)
				return 13;
		} catch (Exception ex1)
		{
			log.info("EntidadesDAO: ERR: isDelete:  no se pudo eliminar RegImpositivoVO por estar asociada a un cotizante.");
			return 13;
		}
		return 0;
	}
}
