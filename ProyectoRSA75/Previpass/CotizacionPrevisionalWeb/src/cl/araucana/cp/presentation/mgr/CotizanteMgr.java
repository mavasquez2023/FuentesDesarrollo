package cl.araucana.cp.presentation.mgr;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.hibernate.Session;

import cl.araucana.cp.distribuidor.base.Constants;
import cl.araucana.cp.distribuidor.hibernate.beans.ApvVO;
import cl.araucana.cp.distribuidor.hibernate.beans.AsigFamVO;
import cl.araucana.cp.distribuidor.hibernate.beans.CausaVO;
import cl.araucana.cp.distribuidor.hibernate.beans.ComprobanteVO;
import cl.araucana.cp.distribuidor.hibernate.beans.CotizacionPendienteVO;
import cl.araucana.cp.distribuidor.hibernate.beans.CotizanteVO;
import cl.araucana.cp.distribuidor.hibernate.beans.EntidadApvVO;
import cl.araucana.cp.distribuidor.hibernate.beans.EntidadPensionVO;
import cl.araucana.cp.distribuidor.hibernate.beans.EntidadSaludVO;
import cl.araucana.cp.distribuidor.hibernate.beans.EntidadVO;
import cl.araucana.cp.distribuidor.hibernate.beans.GeneroVO;
import cl.araucana.cp.distribuidor.hibernate.beans.MapeoAPVVO;
import cl.araucana.cp.distribuidor.hibernate.beans.MapeoAsFamVO;
import cl.araucana.cp.distribuidor.hibernate.beans.MapeoGeneroVO;
import cl.araucana.cp.distribuidor.hibernate.beans.MapeoPensionVO;
import cl.araucana.cp.distribuidor.hibernate.beans.MapeoSaludVO;
import cl.araucana.cp.distribuidor.hibernate.beans.MapeoVO;
import cl.araucana.cp.distribuidor.hibernate.beans.TipoCausaVO;
import cl.araucana.cp.distribuidor.hibernate.beans.TipoNominaVO;
import cl.araucana.cp.distribuidor.hibernate.exceptions.DaoException;
import cl.araucana.cp.distribuidor.presentation.beans.Cotizante;
import cl.araucana.cp.hibernate.dao.ConvenioDAO;
import cl.araucana.cp.hibernate.dao.CotizanteDAO;
import cl.araucana.cp.hibernate.dao.EntidadesDAO;
import cl.araucana.cp.hibernate.dao.NominaDAO;

/*
 * @(#) CotizanteMgr.java 1.21 10/05/2009
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
public class CotizanteMgr
{
	private static Logger logger = Logger.getLogger(CotizanteMgr.class);
	private CotizanteDAO cotizanteDao;
	private ComprobanteMgr comprobanteMgr;
	private EntidadesDAO entidadesDao;
	private NominaDAO nominaDao;
	private ConvenioDAO convenioDao;
	private Session session;

	public CotizanteMgr(Session session)
	{
		this.cotizanteDao = new CotizanteDAO(session);
		this.comprobanteMgr = new ComprobanteMgr(session);
		this.nominaDao = new NominaDAO(session);
		this.entidadesDao = new EntidadesDAO(session);
		this.convenioDao = new ConvenioDAO(session);
		this.session = session;
	}
	
	/**
	 * si no existe cotizante/cotizacion, retorna null, trae cotizante y cotizacion
	 * 
	 * @param idEmpresa
	 * @param idConvenio
	 * @param tipoProceso
	 * @param rutTrabajador
	 * @param periodo
	 * @return
	 */
//	clillo 3-12-14 por Reliquidación
	//public CotizanteVO getCotizante(int idEmpresa, int idConvenio, char tipoProceso, int rutTrabajador)
	public CotizanteVO getCotizante(int idEmpresa, int idConvenio, char tipoProceso, int rutTrabajador, int periodo)
	{
		return this.cotizanteDao.getCotizante(idEmpresa, idConvenio, tipoProceso, rutTrabajador, periodo);
	}

	/**
	 * trae solo cotizante, no cotizacion
	 * 
	 * @param idEmpresa
	 * @param idConvenio
	 * @param rutTrabajador
	 * @param periodo
	 * @return
	 * @throws DaoException
	 */
	//clillo 12-5-14 por Reliquidación
	//public CotizanteVO getCotizante(String idEmpresa, String idConvenio, String rutTrabajador) throws DaoException
	public CotizanteVO getCotizante(String idEmpresa, String idConvenio, String rutTrabajador, int periodo) throws DaoException
	{
		return this.cotizanteDao.getCotizante(new Integer(idEmpresa).intValue(), new Integer(idConvenio).intValue(), new Integer(rutTrabajador).intValue(), periodo);
	}

	
	/**
	 * trae una lista de "CotizacionPendienteVO" pendientes (como la linea tiene errores, esta en tablas pendientes, y no en tablas cotizacion
	 * 
	 * @param idEmpresa
	 * @param idConvenio
	 * @param tipoProceso
	 * @param filtro
	 * @return
	 * @throws DaoException
	 */
	public List getListaCotizPend(int idEmpresa, int idConvenio, char tipoProceso, String filtro) throws DaoException
	{
		return this.cotizanteDao.getListaCotizPend(idEmpresa, idConvenio, tipoProceso, filtro);
	}

	/**
	 * Retorna un HashMap con tres listas adentro:
	 *  - ['avisos', descripcion de avisos asociados a la cotizacion pendiente]
	 *  - ['errores', descripcion de Errores asociados a la cotizacion pendiente]
	 *  - ['descripcionError', lista de descripciones de todos avisos y errores asociados a la cotizacion pendiente]
	 * 
	 * @param idEmpresa
	 * @param idConvenio
	 * @param tipoProceso
	 * @param causas
	 * @return
	 * @throws DaoException
	 */
	public HashMap getNivelErrorTipoCausa(List causas) throws DaoException
	{
		logger.info("\nobteniendo errores/avisos:" + causas.size() + "::");
		HashMap tiposCausa = this.cotizanteDao.getTiposCausa();
		HashMap result = new HashMap();
		HashMap descripcionError = new HashMap();
		List errores = new ArrayList();
		List avisos = new ArrayList();
		for (Iterator it = causas.iterator(); it.hasNext();)
		{
			Integer idCausa = (Integer) it.next();
			if (tiposCausa.containsKey(idCausa))
			{
				TipoCausaVO tc = (TipoCausaVO) tiposCausa.get(idCausa);
				if (tc.getError() == Constants.NIVEL_VAL_ERROR)
				{
					descripcionError.put(idCausa, "<span class=\"mensaje_error\">" + tc.getDescripcion().trim() + "</span>");
					errores.add(tc.getDescripcion().trim());
				} else if (tc.getError() == Constants.NIVEL_VAL_AVISO)
				{
					descripcionError.put(idCausa, "<span class=\"mensaje_aviso\">" + tc.getDescripcion().trim() + "</span>");
					avisos.add(tc.getDescripcion().trim());
				}
			}
		}
		result.put("errores", errores);
		result.put("avisos", avisos);
		result.put("descripcionError", descripcionError);
		return result;
	}

	/**
	 * trae pendiente, esta en tablas pendientes, y no en tablas cotizacion
	 * 
	 * @param idEmpresa
	 * @param idConvenio
	 * @param tipoProceso
	 * @param getCotizPend
	 * @return
	 * @throws DaoException
	 */
//	clillo 3-12-14 por Reliquidación
	//public CotizacionPendienteVO getCotizPend(int idEmpresa, int idConvenio, char tipoProceso, int getCotizPend) throws DaoException
	public CotizacionPendienteVO getCotizPend(int idEmpresa, int idConvenio, char tipoProceso, int getCotizPend, int periodo) throws DaoException
	{
		return this.cotizanteDao.getCotizPend(idEmpresa, idConvenio, tipoProceso, getCotizPend, periodo);
	}

	/**
	 * trae cotizantes con cotizaciones
	 * 
	 * @param idEmpresa
	 * @param idConvenio
	 * @param tipoProceso
	 * @return
	 * @throws DaoException
	 */
	public List getCotizantesNomina(int idEmpresa, int idConvenio, char tipoProceso) throws DaoException
	{
		return this.cotizanteDao.getCotizantesNomina(idEmpresa, idConvenio, tipoProceso);
	}

	/**
	 * causas
	 * 
	 * @param tipoProceso
	 * @param cotPend
	 * @return
	 * @throws DaoException
	 */
	public List getCausas(char tipoProceso, CotizacionPendienteVO cotPend) throws DaoException
	{
		return this.cotizanteDao.getCausas(tipoProceso, cotPend);
	}

	/**
	 * apvs
	 * 
	 * @param idEmpresa
	 * @param idConvenio
	 * @param idCotizante
	 * @return
	 * @throws DaoException
	 */
	public List getApvs(int idEmpresa, int idConvenio, int idCotizante) throws DaoException
	{
		return this.cotizanteDao.getApvs(idEmpresa, idConvenio, idCotizante);
	}

	/**
	 * movimientos personas
	 * 
	 * @param idEmpresa
	 * @param idConvenio
	 * @param idCotizante
	 * @return
	 * @throws DaoException
	 */
	public List getMovtosPers(int idEmpresa, int idConvenio, int idCotizante) throws DaoException
	{
		return this.cotizanteDao.getMovtosPers(idEmpresa, idConvenio, idCotizante);
	}

	/**
	 * guarda cotizante
	 * 
	 * @param tipoProceso
	 * @param idCotizPend
	 * @param idPersona
	 * @param oldRut
	 * @param cotizante
	 * @param periodo_old
	 * @return
	 * @throws Exception
	 */
//	clillo 3-12-14 por Reliquidación
	//public List guardaCotizante(char tipoProceso, int idCotizPend, String idPersona, String oldRut, CotizanteVO cotizante, HashMap causaAvisos) throws Exception
	public List guardaCotizante(char tipoProceso, int idCotizPend, String idPersona, String oldRut, CotizanteVO cotizante, HashMap causaAvisos, int periodo_old) throws Exception
	{
		List result = null;
		long folio = 0, total = 0;

		try {
			//CSanchez. Se respalda el folio y total del comprobante de pago. Previo a cualquier modificación de éste.
			ComprobanteMgr comprobanteMgr = new ComprobanteMgr(session);
			ComprobanteVO comprobanteVO = comprobanteMgr.getComprobante(tipoProceso, cotizante.getIdConvenio(), cotizante.getRutEmpresa());
			folio = comprobanteVO.getFolioTesoreria();
			total = comprobanteVO.getTotal();
		} catch (Exception e){}

		try
		{
			DistribuidorMgr distribuidorMgr = new DistribuidorMgr(this.session, idPersona);
//			clillo 3-12-14 por Reliquidación se guarda periodo_old
			result = distribuidorMgr.valida(idCotizPend, oldRut, cotizante, periodo_old);
			//result = distribuidorMgr.valida(idCotizPend, oldRut, cotizante);
		} catch (Exception e)
		{
			return null;
		}
		if (result.size() == 0 || isAviso(result, causaAvisos))// si no trae resultados => cotizacion se guardo
		{// anula folio
			try
			{
				//this.comprobanteMgr.anulaFolioByComprobante(tipoProceso, cotizante.getIdConvenio(), cotizante.getRutEmpresa());
				if (folio > 0 && total > 0)
					this.comprobanteMgr.anulaFolio(folio);
			} catch (Exception e)
			{
				throw e;
			}
		}
		return result;
	}

	public boolean isAviso(List errores, HashMap causaAvisos)
	{
		try
		{
			for (Iterator it = errores.iterator(); it.hasNext();)
			{
				Integer error = (Integer)it.next();
				if (!causaAvisos.containsKey("" + error.intValue()))
					return false;
			}
		} catch (Exception e)
		{
		}
		return true;
	}

	/**
	 * nombres reales
	 * 
	 * @param tipoProceso
	 * @param cot
	 * @return
	 * @throws DaoException
	 */
	public Cotizante setNombresReales(char tipoProceso, Cotizante cot) throws DaoException
	{
		EntidadVO entidad = null;
		if (tipoProceso != 'D')
		{
			entidad = this.getEntSalud(cot.getIdEntSaludReal());
			if (entidad != null)
				cot.setIdEntSalud(entidad.getNombre().trim());
			entidad = this.getEntFondoPension(cot.getIdEntPensionReal());
			if (entidad != null)
				cot.setIdEntPension(entidad.getNombre().trim());
			entidad = this.getEntFondoPension(cot.getIdEntAFCReal());
			if (entidad != null && entidad.getId() != Constants.ID_INP)
				cot.setIdEntAFC(entidad.getNombre().trim());
			else
				cot.setIdEntAFC("");
		} else
		{
			entidad = this.getEntApv(cot.getIdEntidadAPVCReal());
			if (entidad != null)
				cot.setIdEntidadAPVC(entidad.getNombre().trim());
		}
		return cot;
	}

	/**
	 * nombre reales
	 * 
	 * @param tipoProceso
	 * @param setGenero
	 * @param idMapaCod
	 * @param cot
	 * @return
	 * @throws DaoException
	 */
	public Cotizante setNombresReales(char tipoProceso, boolean setGenero, int idMapaCod, Cotizante cot) throws DaoException
	{
		EntidadVO entidad = null;
		if (tipoProceso != 'D')
		{
			MapeoVO mapeo = this.entidadesDao.getEntReal(idMapaCod, MapeoSaludVO.class, cot.getIdEntSalud());
			if (mapeo != null)
			{
				entidad = this.getEntSalud(mapeo.getId());
				cot.setIdEntSalud(entidad.getNombre().trim());
			}
			mapeo = this.entidadesDao.getEntReal(idMapaCod, MapeoPensionVO.class, cot.getIdEntPension());
			if (mapeo != null)
			{
				entidad = this.getEntFondoPension(mapeo.getId());
				cot.setIdEntPension(entidad.getNombre().trim());
			}
			mapeo = this.entidadesDao.getEntReal(idMapaCod, MapeoPensionVO.class, cot.getIdEntidadAFPV());
			if (mapeo != null)
			{
				entidad = this.getEntFondoPension(mapeo.getId());
				cot.setIdEntidadAFPV(entidad.getNombre().trim());
			}
			mapeo = this.entidadesDao.getEntReal(idMapaCod, MapeoPensionVO.class, cot.getIdEntAFC());
			if (mapeo != null)
			{
				entidad = this.getEntFondoPension(mapeo.getId());
				cot.setIdEntAFC(entidad.getNombre().trim());
			}
			mapeo = this.entidadesDao.getEntReal(idMapaCod, MapeoAsFamVO.class, cot.getIdTramo());
			if (mapeo != null)
			{
				entidad = this.getAsigFam(mapeo.getId());
				cot.setIdTramo(entidad.getNombre().trim());
			}
			if (setGenero)
			{
				mapeo = this.entidadesDao.getEntReal(idMapaCod, MapeoGeneroVO.class, cot.getIdGenero());
				if (mapeo != null)
				{
					entidad = this.getGenero(mapeo.getId());
					cot.setIdGenero(entidad.getNombre().trim());
				}
			}
		} else
		{
			MapeoVO mapeo = this.entidadesDao.getEntReal(idMapaCod, MapeoAPVVO.class, cot.getIdEntidadAPVC());
			if (mapeo != null)
			{
				entidad = this.getEntApv(mapeo.getId());
				cot.setIdEntidadAPVC(entidad.getNombre().trim());
			}

			if (setGenero)
			{
				mapeo = this.entidadesDao.getEntReal(idMapaCod, MapeoGeneroVO.class, cot.getIdGenero());
				if (mapeo != null)
				{
					entidad = this.getGenero(mapeo.getId());
					cot.setIdGenero(entidad.getNombre().trim());
				}
			}
		}
		return cot;
	}

	/**
	 * id reales
	 * 
	 * @param tipoProceso
	 * @param idMapaCod
	 * @param cot
	 * @return
	 * @throws DaoException
	 */
	public Cotizante setIdsReales(char tipoProceso, int idMapaCod, Cotizante cot) throws DaoException
	{
		if (tipoProceso != 'D')
		{
			MapeoVO mapeo = this.entidadesDao.getEntReal(idMapaCod, MapeoSaludVO.class, cot.getIdEntSalud());
			if (mapeo != null)
				cot.setIdEntSaludReal(mapeo.getId());
			mapeo = this.entidadesDao.getEntReal(idMapaCod, MapeoPensionVO.class, cot.getIdEntPension());
			if (mapeo != null)
				cot.setIdEntPensionReal(mapeo.getId());
			mapeo = this.entidadesDao.getEntReal(idMapaCod, MapeoPensionVO.class, cot.getIdEntidadAFPV());
			if (mapeo != null)
				cot.setIdEntidadAFPVReal(mapeo.getId());
			mapeo = this.entidadesDao.getEntReal(idMapaCod, MapeoPensionVO.class, cot.getIdEntAFC());
			if (mapeo != null)
				cot.setIdEntAFCReal(mapeo.getId());
			mapeo = this.entidadesDao.getEntReal(idMapaCod, MapeoAsFamVO.class, cot.getIdTramo());
			if (mapeo != null)
			{
				cot.setIdTramoReal(mapeo.getId());
				cot.setIdTramoRealINP(mapeo.getId());
			}
			mapeo = this.entidadesDao.getEntReal(idMapaCod, MapeoGeneroVO.class, cot.getIdGenero());
			if (mapeo != null)
				cot.setIdGeneroReal("" + mapeo.getId());
			mapeo = this.entidadesDao.getEntReal(idMapaCod, MapeoAPVVO.class, cot.getIdEntidadAPVC());
			if (mapeo != null)
				cot.setIdEntidadAPVCReal(mapeo.getId());
		} else
		{
			MapeoVO mapeo = this.entidadesDao.getEntReal(idMapaCod, MapeoAPVVO.class, cot.getIdEntidadAPVC());
			if (mapeo != null)
				cot.setIdEntidadAPVCReal(mapeo.getId());
			mapeo = this.entidadesDao.getEntReal(idMapaCod, MapeoGeneroVO.class, cot.getIdGenero());
			if (mapeo != null)
				cot.setIdGeneroReal("" + mapeo.getId());
			mapeo = this.entidadesDao.getEntReal(idMapaCod, MapeoAPVVO.class, cot.getIdEntDep());
			if (mapeo != null)
				cot.setIdEntDep(String.valueOf(mapeo.getId()));
		}
		return cot;
	}
	
	/**
	 * id reales
	 * 
	 * @param tipoProceso
	 * @param idMapaCod
	 * @param apvs
	 * @return
	 * @throws DaoException
	 */
	public List setIdsRealesAPV(char tipoProceso, int idMapaCod, List apvs) throws DaoException
	{
		MapeoVO mapeo = null;
		for(Iterator it = apvs.iterator(); it.hasNext();){
			ApvVO apvVO = (ApvVO)it.next();
			
			mapeo = this.entidadesDao.getEntReal(idMapaCod, MapeoPensionVO.class, ""+apvVO.getIdEntidadApvEmp());
			if(mapeo == null)
				mapeo = this.entidadesDao.getEntReal(idMapaCod, MapeoAPVVO.class, ""+apvVO.getIdEntidadApvEmp());
			
			apvVO.setIdEntidadApv(mapeo == null ? 0 : mapeo.getId());
		}
		return apvs;
	}

	/**
	 * elimina cotizante
	 * 
	 * @param pendiente
	 * @param rutEmpresa
	 * @param idConvenio
	 * @param tipoProceso
	 * @param idTrabajador
	 * @param idPersona
	 * @param periodo
	 * @return
	 * @throws Exception
	 */
	
//	clillo 3-12-14 por Reliquidación
	//public int eliminaCotizante(boolean pendiente, int rutEmpresa, int idConvenio, char tipoProceso, int idTrabajador, String idPersona) throws Exception
	public int eliminaCotizante(boolean pendiente, int rutEmpresa, int idConvenio, char tipoProceso, int idTrabajador, String idPersona, int periodo) throws Exception
	{
		int result = 0;
		long folio = 0, total = 0;

		try {
			//CSanchez. Se respalda el folio y total del comprobante de pago. Previo a cualquier modificación de éste.
			ComprobanteMgr comprobanteMgr = new ComprobanteMgr(session);
			ComprobanteVO comprobanteVO = comprobanteMgr.getComprobante(tipoProceso, idConvenio, rutEmpresa);
			folio = comprobanteVO.getFolioTesoreria();
			total = comprobanteVO.getTotal();
		} catch (Exception e){}
		
		try
		{
			logger.info("empieza eliminaCotizante!");
			DistribuidorMgr distribuidorMgr = new DistribuidorMgr(this.session, idPersona);
//			clillo 3-12-14 por Reliquidación
			//result = distribuidorMgr.eliminaTrabajador(pendiente, rutEmpresa, idConvenio, tipoProceso, idTrabajador);
			result = distribuidorMgr.eliminaTrabajador(pendiente, rutEmpresa, idConvenio, tipoProceso, idTrabajador, periodo);
			this.nominaDao.borraCRC(rutEmpresa, idConvenio, tipoProceso);
		} catch (Exception e)
		{
			return -1;
		}
		if (result == 1)// cotizacion se elimino
		{// anula folio
			try
			{
				//this.comprobanteMgr.anulaFolioByComprobante(tipoProceso, idConvenio, rutEmpresa);
				if (folio > 0 && total > 0)
					this.comprobanteMgr.anulaFolio(folio);

			} catch (Exception e)
			{
				throw e;
			}
		}
		logger.info("termina eliminaCotizante!:" + result + "::");
		return result;
	}

	/**
	 * apellidos compuestos
	 * 
	 * @return
	 * @throws DaoException
	 */
	public List getApellCompuestos() throws DaoException
	{
		return this.cotizanteDao.getApellCompuestos();
	}

	/**
	 * entidad fondo pension
	 * 
	 * @param id
	 * @return
	 * @throws DaoException
	 */
	public EntidadPensionVO getEntFondoPension(int id) throws DaoException
	{
		return this.entidadesDao.getEntFondoPension(id);
	}

	/**
	 * asignacion familiar
	 * 
	 * @param id
	 * @return
	 * @throws DaoException
	 */
	public AsigFamVO getAsigFam(int id) throws DaoException
	{
		return this.entidadesDao.getAsigFam(id);
	}

	/**
	 * genero
	 * 
	 * @param id
	 * @return
	 * @throws DaoException
	 */
	public GeneroVO getGenero(int id) throws DaoException
	{
		return this.entidadesDao.getGenero(id);
	}

	/**
	 * entidad apv
	 * 
	 * @param id
	 * @return
	 * @throws DaoException
	 */
	public EntidadApvVO getEntApv(int id) throws DaoException
	{
		return this.entidadesDao.getEntApv(id);
	}

	/**
	 * entidad salud
	 * 
	 * @param id
	 * @return
	 * @throws DaoException
	 */
	public EntidadSaludVO getEntSalud(int id) throws DaoException
	{
		return this.entidadesDao.getEntSalud(id);
	}

	/**
	 * tipos proceso
	 * 
	 * @return
	 * @throws DaoException
	 */
	public String[] getTiposProceso() throws DaoException
	{
		Collection lista = this.nominaDao.getTiposNominas();
		String[] result = new String[lista.size()];
		int pos = 0;
		for (Iterator it = lista.iterator(); it.hasNext();)
		{
			TipoNominaVO tn = (TipoNominaVO) it.next();
			result[pos++] = tn.getIdTipoNomina();
		}
		return result;
	}
	
//	clillo 3-12-14 por Reliquidación
	//public List getCausasAvisos(char tipoProceso, int idEmpresa, int idConvenio, int idCotizante) throws DaoException
	public List getCausasAvisos(char tipoProceso, int idEmpresa, int idConvenio, int idCotizante, int periodo) throws DaoException
	{
		return this.cotizanteDao.getCausasAvisos(tipoProceso, idEmpresa, idConvenio, idCotizante, periodo);
	}
//	clillo 3-12-14 por Reliquidación
	//public List getCausasAvisosCargas(char tipoProceso, int idEmpresa, int idConvenio, int idCotizante) throws DaoException
	public List getCausasAvisosCargas(char tipoProceso, int idEmpresa, int idConvenio, int idCotizante, int periodo) throws DaoException
	{
		return this.cotizanteDao.getCausasAvisosCargas(tipoProceso, idEmpresa, idConvenio, idCotizante, periodo);
	}
//	clillo 3-12-14 por Reliquidación
	//public List getCausasAvisosPendientes(char tipoProceso, int idEmpresa, int idConvenio, int idCotizante) throws DaoException
	public List getCausasAvisosPendientes(char tipoProceso, int idEmpresa, int idConvenio, int idCotizante, int periodo) throws DaoException
	{
		return this.cotizanteDao.getCausasAvisosPendientes(tipoProceso, idEmpresa, idConvenio, idCotizante, periodo);
	}
	
	
	public HashMap getTrabPaginados(int pagina, int primerReg, int rutEmpresa, int idConvenio, char tipoProceso, String filtro) throws DaoException
	{
		HashMap result = new HashMap();
		List pend = new ArrayList();
		List avisos = new ArrayList();
		List aprobados = new ArrayList();

		//valores con filtro aplicado
		int numAprobados = this.cotizanteDao.getNumAprobados(rutEmpresa, idConvenio, tipoProceso, filtro);
		int numAvisos = this.cotizanteDao.getNumAvisos(rutEmpresa, idConvenio, tipoProceso, filtro);
		int numPendientes = this.cotizanteDao.getNumPendientes(rutEmpresa, idConvenio, tipoProceso, filtro);
		int count = 0;
		numAprobados -= numAvisos;
		logger.info("paginando: pag solicitada:" + pagina + ":primerRegistro:" + primerReg + ":nPendientes:" + numPendientes + ":nAviso:" + numAvisos + ":nAprobados:" + numAprobados + "::");

		//trae todos los pendientes, despues se deben parsear, ordenar y paginar
		if (numPendientes > 0 && primerReg < numPendientes)
			pend = this.getListaCotizPend(rutEmpresa, idConvenio, tipoProceso, filtro);
		count = Math.min(numPendientes, primerReg + Constants.NUM_REG_PAG_CL) - primerReg; //suma aporte pendientes a result
		if (count < 0)	count = 0;
		logger.info("add n pend (todos):" + numPendientes + ":aporte pendientes a result:" + count + "::");
		//avisos
		if (numAvisos > 0 && count < Constants.NUM_REG_PAG_CL)
		{
			if (filtro == null)
			{
				int posIni = (primerReg <= numPendientes ? 0 : primerReg - numPendientes);
				avisos = this.cotizanteDao.getListaCotizantesAvisos(posIni, Constants.NUM_REG_PAG_CL - count, rutEmpresa, idConvenio, tipoProceso);
			} else
				avisos.add(this.cotizanteDao.getCotizante(rutEmpresa, idConvenio, tipoProceso, new Integer(filtro).intValue()));
		}
		logger.info("add n avisos:" + avisos.size() + "::");
		count += avisos.size();
		//aprobados
		if (numAprobados > 0 && count < Constants.NUM_REG_PAG_CL)
		{
			if (filtro == null)
			{
				int posIni = (primerReg <= numPendientes + numAvisos ? 0 : primerReg - (numPendientes + numAvisos));
				aprobados = this.cotizanteDao.getListaCotizantes(posIni, Constants.NUM_REG_PAG_CL - count, rutEmpresa, idConvenio, tipoProceso);
			} else
				aprobados.add(this.cotizanteDao.getCotizante(rutEmpresa, idConvenio, tipoProceso, new Integer(filtro).intValue()));
		}
		logger.info("add n aprobados:" + aprobados.size() + "::");

		result.put("pendientes", pend);
		result.put("avisos", avisos);
		result.put("aprobados", aprobados);
		int suma = numAprobados + numAvisos + numPendientes;
		result.put("nPaginas", new Integer(suma % Constants.NUM_REG_PAG_CL == 0 ? suma / Constants.NUM_REG_PAG_CL : (int) Math.round((suma / Constants.NUM_REG_PAG_CL) + 0.5)));
		return result;
	}

	public HashMap getAllTrabPaginados(int pagina, int primerReg, int idUser, HashMap filtros) throws DaoException
	{
		HashMap result = new HashMap();
		List avisos = new ArrayList();
		List aprobados = new ArrayList();
		HashMap pendientes = new HashMap();
		Set convConCotiz = new HashSet();
		Set listaConvPend = new HashSet();

		/*String filtroRut = (filtros.containsKey("idCotizante") ? ((Integer) filtros.get("idCotizante")).toString() : null);
		String filtroNombre = (filtros.containsKey("nombre") ? (String) filtros.get("nombre")                    : null);
		String filtroApellido = (filtros.containsKey("apellidos") ? (String) filtros.get("apellidos")                 : null);*/
		
		String filtroNomina = (filtros.containsKey("proceso") ? (String) filtros.get("proceso") : null);

		List tiposNomina;
		if (filtroNomina == null)
			tiposNomina = (List)this.nominaDao.getTiposNominas();
		else
			tiposNomina = (List)this.nominaDao.getTiposNominas(filtroNomina);

		logger.info("\n\ncomienza paginacion trabsAll");
		//valores con filtro aplicado
		int numAprobados = this.cotizanteDao.getNumAprobadosAll(idUser, filtros);
		logger.info("\nnumAprobados:" + numAprobados + "::");
		int numAvisos = this.cotizanteDao.getNumAvisosAll(idUser, filtros);
		logger.info("\nnumAvisos:" + numAvisos + "::");
		int numPendientes = this.cotizanteDao.getNumPendientesAll(idUser, filtros, tiposNomina);
		int count = 0;
		logger.info("\n\npaginando: pag solicitada:" + pagina + ":primerRegistro:" + primerReg + ":nPendientes:" + numPendientes + ":nAviso:" + numAvisos + ":nAprobados:" + numAprobados + "::");

		//trae todos los pendientes, despues se deben parsear, ordenar y paginar
		if (numPendientes > 0 && primerReg < numPendientes)
			pendientes = this.cotizanteDao.getPendientesAll(idUser, filtros, tiposNomina);
		count = Math.min(numPendientes, primerReg + Constants.NUM_REG_PAG_CL) - primerReg; //suma aporte pendientes a result
		if (count < 0)	count = 0;
		logger.info("add n pend (todos):" + numPendientes + ":aporte pendientes a result:" + count + "::");
		//avisos
		if (numAvisos > 0 && count < Constants.NUM_REG_PAG_CL)
		{
			int posIni = (primerReg <= numPendientes ? 0 : primerReg - numPendientes);
			avisos = this.cotizanteDao.getAllCotizantesAvisos(posIni, Constants.NUM_REG_PAG_CL - count, idUser, filtros);
		}
		logger.info("add n avisos:" + avisos.size() + "::");
		count += avisos.size();
		//aprobados
		if (numAprobados > 0 && count < Constants.NUM_REG_PAG_CL)
		{
			int posIni = (primerReg <= numPendientes + numAvisos ? 0 : primerReg - (numPendientes + numAvisos));
			aprobados = this.cotizanteDao.getAllCotizantes(posIni, Constants.NUM_REG_PAG_CL - count, idUser, filtros);
		}
		logger.info("add n aprobados:" + aprobados.size() + "::");

		result.put("pendientes", transformAllPend(tiposNomina, pendientes, listaConvPend));
		result.put("avisos", transformAllCotizantes(avisos, convConCotiz));
		result.put("aprobados", transformAllCotizantes(aprobados, convConCotiz));
		result.put("listaConvApro", transformAllConvenios(convConCotiz));
		result.put("listaConvPend", transformAllConvenios(listaConvPend));
		int suma = numAprobados + numAvisos + numPendientes;
		result.put("nPaginas", new Integer(suma % Constants.NUM_REG_PAG_CL == 0 ? suma / Constants.NUM_REG_PAG_CL : (int) Math.round((suma / Constants.NUM_REG_PAG_CL) + 0.5)));
		return result;
	}

	private List transformAllConvenios(Set convConCotiz) throws DaoException
	{
		List result = new ArrayList();
		for (Iterator it = convConCotiz.iterator(); it.hasNext();)
		{
			String key[] = ((String)it.next()).split("#");
			logger.info("convenio a utilizar:" + key[0] + "#" + key[1] + "::");
			result.add(this.convenioDao.getConvenio((new Integer(key[0])).intValue(), (new Integer(key[1])).intValue()));
		}
		return result;
	}

	public HashMap transformAllCotizantes(List trabs, Set convConCotiz)
	{
		HashMap resultFinal = new HashMap();
		try
		{
		String idCot = "";
		String rutEmpresa = ""; 
		for (Iterator it2 = trabs.iterator(); it2.hasNext();)
		{
			Object[] tupla = (Object[]) it2.next();
			CotizanteVO cotVista = new CotizanteVO(((Integer)tupla[1]).intValue(), ((Integer)tupla[2]).intValue(), ((Integer)tupla[0]).intValue());
			cotVista.setNombre((String)tupla[3]);
			cotVista.setApellidoPat((String)tupla[4]);
			cotVista.setApellidoMat((String)tupla[5]);
			cotVista.setTieneRemu(((Integer)tupla[6]).intValue());
			cotVista.setTieneGrat(((Integer)tupla[7]).intValue());
			cotVista.setTieneReli(((Integer)tupla[8]).intValue());
			cotVista.setTieneDepo(((Integer)tupla[9]).intValue());

			rutEmpresa = "" + cotVista.getRutEmpresa();
			String idConvenio = "" + cotVista.getIdConvenio();
			idCot = "" + cotVista.getIdCotizante();

			HashMap empresas = new HashMap();
			if (resultFinal.containsKey(idCot))
				empresas = (HashMap)resultFinal.get(idCot);
			else
			{
				empresas.put("cotizante", cotVista);
				resultFinal.put(idCot, empresas);
			}

			HashMap convenios = new HashMap();
			if (empresas.containsKey(rutEmpresa))
				convenios = (HashMap)empresas.get(rutEmpresa);
			else
				empresas.put(rutEmpresa, convenios);
			Set tps = new HashSet();
				convenios.put(idConvenio, tps);
			if (cotVista.getTieneRemu() > 0)
				tps.add("R");
			if (cotVista.getTieneGrat() > 0)
				tps.add("G");
			if (cotVista.getTieneReli() > 0)
				tps.add("A");
			if (cotVista.getTieneDepo() > 0)
				tps.add("D");

			convConCotiz.add(cotVista.getRutEmpresa() + "#" + cotVista.getIdConvenio());
		}
		} catch (Exception e)
		{
		}
		return resultFinal;
	}

	public HashMap transformAllPend(List tiposProceso, HashMap pendientes, Set listaConvPend)
	{
		HashMap result = new HashMap();
		for (Iterator it = tiposProceso.iterator(); it.hasNext();)
		{
			String tn = ((TipoNominaVO) it.next()).getIdTipoNomina();
			if (pendientes.get(tn) != null)
				for (Iterator it2 = ((List)pendientes.get(tn)).iterator(); it2.hasNext();)
				{
					CotizacionPendienteVO cotPend = (CotizacionPendienteVO)it2.next();
					List pendEmpConv = new ArrayList();
					String key = cotPend.getRutEmpresa() + "#" + cotPend.getIdConvenio() + "#" + tn;
					if (result.containsKey(key))
						pendEmpConv = (List)result.get(key);
					else
						result.put(key, pendEmpConv);
					pendEmpConv.add(cotPend);
					listaConvPend.add(cotPend.getRutEmpresa() + "#" + cotPend.getIdConvenio());
				}
		}
		return result;
	}

	public HashMap getApvsHash(int rutEmpresa, int idConvenio) throws DaoException
	{
		HashMap result = new HashMap();
		List lista = this.cotizanteDao.getApvs(rutEmpresa, idConvenio, 0);
		for (Iterator it = lista.iterator(); it.hasNext();)
		{
			ApvVO apv = (ApvVO)it.next();
			List listaTmp = new ArrayList();
			if (result.containsKey("" + apv.getIdCotizante()))
				listaTmp = (List)result.get("" + apv.getIdCotizante());
			listaTmp.add(apv);
			result.put("" + apv.getIdCotizante(), listaTmp);
		}
		return result;
	}
	
	public List getCausasVO(char tipoProceso, CotizacionPendienteVO cotPend) throws DaoException {
		return this.cotizanteDao.getCausasVO(tipoProceso, cotPend);
	}
	
	public HashMap getNivelErrorTipoCausasVO(List causas) throws DaoException {
		logger.info("\nobteniendo errores/avisos:" + causas.size() + "::");
		HashMap tiposCausa = this.cotizanteDao.getTiposCausa();
		HashMap result = new HashMap();
		HashMap descripcionError = new HashMap();
		List errores = new ArrayList();
		List avisos = new ArrayList();
		String valorInformado = "";
		for (Iterator it = causas.iterator(); it.hasNext();)
		{
			CausaVO causaVO = (CausaVO) it.next();
			Integer idCausa = new Integer (causaVO.getIdTipoCausa());
			if (tiposCausa.containsKey(idCausa))
			{
				TipoCausaVO tc = (TipoCausaVO) tiposCausa.get(idCausa);
				if (tc.getError() == Constants.NIVEL_VAL_ERROR)
				{
					valorInformado = causaVO.getValorInformado().trim();
					if (valorInformado.equals(""))
						valorInformado = "VACÍO";
					descripcionError.put(idCausa, "<span class=\"mensaje_error\">" + tc.getDescripcion().trim() + "</span>");
					errores.add(tc.getDescripcion().trim() + " (VALOR INFORMADO EN NÓMINA: " + valorInformado + ")");
				} else if (tc.getError() == Constants.NIVEL_VAL_AVISO)
				{
					descripcionError.put(idCausa, "<span class=\"mensaje_aviso\">" + tc.getDescripcion().trim() + "</span>");
					avisos.add(tc.getDescripcion().trim());
				}
			}
		}
		result.put("errores", errores);
		result.put("avisos", avisos);
		result.put("descripcionError", descripcionError);
		return result;
	}
	
	/**
	 * @author gmallea
	 * 
	 * obtiene el total de cotizantes
	 * 
	 * @param idEmpresa
	 * @param idConvenio
	 * @return int
	 * @throws DaoException
	 */
	public int totalCotizante(int idEmpresa, int idConvenio) throws DaoException
	{
		return this.cotizanteDao.totalCotizante(idEmpresa, idConvenio);
	}
}