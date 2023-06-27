package cl.araucana.cp.presentation.mgr;

import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.hibernate.Session;

import cl.araucana.core.business.BusinessException;
import cl.araucana.cp.distribuidor.base.Constants;
import cl.araucana.cp.distribuidor.base.ParametrosHash;
import cl.araucana.cp.distribuidor.base.Utils;
import cl.araucana.cp.distribuidor.hibernate.beans.ComprobanteVO;
import cl.araucana.cp.distribuidor.hibernate.beans.ConvenioVO;
import cl.araucana.cp.distribuidor.hibernate.beans.CotizacionDCVO;
import cl.araucana.cp.distribuidor.hibernate.beans.CotizacionGRVO;
import cl.araucana.cp.distribuidor.hibernate.beans.CotizacionRAVO;
import cl.araucana.cp.distribuidor.hibernate.beans.CotizacionREVO;
import cl.araucana.cp.distribuidor.hibernate.beans.CotizanteVO;
import cl.araucana.cp.distribuidor.hibernate.beans.DetalleAporteCcafVO;
import cl.araucana.cp.distribuidor.hibernate.beans.DetalleCreditoCcafVO;
import cl.araucana.cp.distribuidor.hibernate.beans.DetalleLeasingCcafVO;
import cl.araucana.cp.distribuidor.hibernate.beans.DetalleSeccionVO;
import cl.araucana.cp.distribuidor.hibernate.beans.EmpresaVO;
import cl.araucana.cp.distribuidor.hibernate.beans.NominaDCVO;
import cl.araucana.cp.distribuidor.hibernate.beans.NominaGRVO;
import cl.araucana.cp.distribuidor.hibernate.beans.NominaRAVO;
import cl.araucana.cp.distribuidor.hibernate.beans.NominaREVO;
import cl.araucana.cp.distribuidor.hibernate.beans.NominaVO;
import cl.araucana.cp.distribuidor.hibernate.beans.PersonaVO;
import cl.araucana.cp.distribuidor.hibernate.beans.SeccionDNPVO;
import cl.araucana.cp.distribuidor.hibernate.beans.SeccionVO;
import cl.araucana.cp.distribuidor.hibernate.beans.TipoDetalleVO;
import cl.araucana.cp.distribuidor.hibernate.beans.TipoNominaVO;
import cl.araucana.cp.distribuidor.hibernate.beans.TipoSeccionVO;
import cl.araucana.cp.distribuidor.hibernate.exceptions.DaoException;
import cl.araucana.cp.distribuidor.presentation.beans.AporteDetalleInforme;
import cl.araucana.cp.distribuidor.presentation.beans.AporteInforme;
import cl.araucana.cp.distribuidor.presentation.beans.CreditoDetalleInforme;
import cl.araucana.cp.distribuidor.presentation.beans.CreditoInforme;
import cl.araucana.cp.distribuidor.presentation.beans.DetalleCajaInforme;
import cl.araucana.cp.distribuidor.presentation.beans.LeasingDetalleInforme;
import cl.araucana.cp.distribuidor.presentation.beans.LeasingInforme;
import cl.araucana.cp.hibernate.beans.MapeoTesoreriaVO;
import cl.araucana.cp.hibernate.beans.SPLPagoVO;
import cl.araucana.cp.hibernate.dao.ComprobanteDAO;
import cl.araucana.cp.hibernate.dao.ConvenioDAO;
import cl.araucana.cp.hibernate.dao.CotizanteDAO;
import cl.araucana.cp.hibernate.dao.DetalleAporteCcafDAO;
import cl.araucana.cp.hibernate.dao.DetalleCreditoCcafDAO;
import cl.araucana.cp.hibernate.dao.DetalleLeasingCcaDAO;
import cl.araucana.cp.hibernate.dao.DetalleReporteDAO;
import cl.araucana.cp.hibernate.dao.EmpresaDAO;
import cl.araucana.cp.hibernate.dao.MapeoTesoreriaDAO;
import cl.araucana.cp.hibernate.dao.NominaDAO;
import cl.araucana.cp.hibernate.dao.ParametrosDAO;
import cl.araucana.cp.hibernate.dao.TipoSeccionDAO;
import cl.araucana.cp.presentation.struts.javaBeans.Folio;
import cl.araucana.cp.presentation.struts.javaBeans.LineaComprobanteProvisorioVO;
import cl.araucana.cp.presentation.struts.javaBeans.LineaComprobantesAPagar;
import cl.araucana.cp.presentation.struts.javaBeans.LineaResumenComprobanteProvisorio;
import cl.araucana.cp.presentation.struts.javaBeans.PagoEnLinea;
import cl.araucana.cp.util.reporte.ComprobantePagadoPDF;
import cl.araucana.cp.util.reporte.ConstructorPDF;
import cl.araucana.cp.util.vo.DetalleReporteVO;
import cl.araucana.cp.utils.ProxyAS400;
import cl.araucana.cp.utils.PublicadorPDF;

/*
 * @(#) ComprobanteMgr.java 1.59 10/05/2009
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */
/**
 * @author pfrigolett
 * @author cchamblas
 * 
 * @version 1.59
 */
public class ComprobanteMgr
{
	private ComprobanteDAO comprobanteDao;
	private EmpresaDAO empresaDao;
	private MapeoTesoreriaDAO mapeoTesoreriaDao;
	private NominaDAO nominaDao;
	private DetalleReporteDAO detalleReporteDao;
	private TipoSeccionDAO tipoSeccionDao;
	private ParametrosDAO parametrosDao;
	private ConvenioDAO convenioDao;
	private DetalleAporteCcafDAO detalleAporteCcafDAO;
	private DetalleCreditoCcafDAO detalleCreditoCcafDAO;
	private DetalleLeasingCcaDAO detalleLeasingCcaDAO;
	private CotizanteDAO cotizanteDAO;
	
	private Logger log = Logger.getLogger(ComprobanteMgr.class);

	private static final int REPORTE_EDICION_COMPROBANTES = 1;

	/**
	 * comprobante
	 * 
	 * @param session
	 */
	public ComprobanteMgr(Session session)
	{
		this.comprobanteDao = new ComprobanteDAO(session);
		this.empresaDao = new EmpresaDAO(session);
		this.mapeoTesoreriaDao = new MapeoTesoreriaDAO(session);
		this.nominaDao = new NominaDAO(session);
		this.detalleReporteDao = new DetalleReporteDAO(session);
		this.tipoSeccionDao = new TipoSeccionDAO(session);
		this.parametrosDao = new ParametrosDAO(session);
		this.convenioDao = new ConvenioDAO(session);
		this.detalleAporteCcafDAO= new DetalleAporteCcafDAO(session);
		this.detalleCreditoCcafDAO= new DetalleCreditoCcafDAO(session);
		this.detalleLeasingCcaDAO= new DetalleLeasingCcaDAO(session);
		this.cotizanteDAO= new CotizanteDAO(session);
	}

	/**
	 * detalle reporte
	 * 
	 * @param idTipo
	 * @return
	 * @throws DaoException
	 */
	public List getDetalleReporte(int idTipo) throws DaoException
	{
		return this.detalleReporteDao.getLista(idTipo);
	}

	/**
	 * nomina
	 * 
	 * @param codigoBarra
	 * @return
	 * @throws DaoException
	 */
	public NominaVO getNomina(long codigoBarra) throws DaoException
	{
		return this.comprobanteDao.getNomina(codigoBarra);
	}

	/**
	 * guarda lista edicion comprobantes
	 * 
	 * @param codigoBarra
	 * @param totalPago
	 * @param listaSecciones
	 * @throws DaoException
	 * @throws Exception
	 */
	public long guardaListaEdicionComprobantes(long codigoBarra, List listaSecciones, String tipoNomina) throws DaoException, Exception
	{
		this.log.debug("ComprobanteMgr.guardaListaEdicionComprobantes(codigoBarra: " + codigoBarra + ", listaSecciones: " + listaSecciones + ")");

		long totalPago = 0;
		ComprobanteVO comprobante = this.getComprobante(new Long(codigoBarra));
		SeccionVO seccion;
		DetalleSeccionVO detalle;
		TipoSeccionVO tipoSeccion;
		LineaComprobanteProvisorioVO lineaSeccion, lineaDetalle;
		try
		{
			if (comprobante.getFolioTesoreria() > 0)
				anulaFolio(comprobante.getFolioTesoreria());
		} catch (Exception e)
		{
			this.log.error("error anulando folio", e);
			throw new Exception("error.anulaFolio");
		}
		comprobante.setFolioTesoreria(0);
		for (Iterator it = comprobante.getSecciones().iterator(); it.hasNext();)
		{
			seccion = (SeccionVO) it.next();
			lineaSeccion = buscaLineaSeccion(seccion.getIdTipoSeccion(), listaSecciones);// (LineaComprobanteProvisorioVO) lineaSeccion.getDetalle().get(iDet);
			if (lineaSeccion == null)
			{
				this.log.info("lineaSeccion no encontrado!!:" + lineaSeccion + "::");
				continue;
			}

			boolean flagSumaSecc = (lineaSeccion.getTipoPago() == Constants.EST_SECCION_INDEF ? false : true);
			tipoSeccion = this.tipoSeccionDao.getTipoSeccion(seccion.getIdTipoSeccion());
			this.log.debug("secc:" + seccion.getIdTipoSeccion() + ":flag:" + flagSumaSecc + ":tipoPago:" + lineaSeccion.getTipoPago() + ":num detalles:" + seccion.getDetallesSeccion().size()
					+ ":pagoInd:" + tipoSeccion.getPagoIndividual() + "::");
			this.log.debug("Encontro linea seccion: " + lineaSeccion);

			if (tipoSeccion.getPagoIndividual() != 0 && tipoSeccion.getId() != Constants.ID_TIPO_SECCION_CAJA && tipoSeccion.getId() != Constants.ID_TIPO_SECCION_INP)
			{
				for (Iterator itp = seccion.getDetallesSeccion().iterator(); itp.hasNext();)
				{
					detalle = (DetalleSeccionVO) itp.next();
					this.log.debug("\tdetalle encontrado:" + detalle.getIdDetalleSeccion() + "::");
					lineaDetalle = buscaLineaDetalle(detalle.getIdDetalleSeccion(), lineaSeccion.getDetalle());// (LineaComprobanteProvisorioVO) lineaSeccion.getDetalle().get(iDet);
					if (lineaDetalle == null)
					{
						this.log.info("lineaDetalle no encontrado!!:" + lineaDetalle + "::");
						continue;
					}
					detalle.setTipoPago(lineaDetalle.getTipoPago());
					if (!flagSumaSecc && lineaDetalle.getTipoPago() == Constants.EST_SECCION_PAGO)
					{
						totalPago += detalle.calculaTotal();
						this.log.debug("\t\tdetalleSuma:" + detalle.getIdDetalleSeccion() + "::" + lineaDetalle.getTipoPago() + "::");
					} else
						this.log.debug("\ttdetalle NO Suma:" + detalle.getIdDetalleSeccion() + "::" + lineaDetalle.getTipoPago() + "::");
					this.log.debug("\t\t\tdetalle:" + detalle.getIdDetalleSeccion() + ":total detalle:" + this.calcularTotalDetalleSeccion(detalle) + ":total acumulado:" + totalPago + "::");
				}
			}
			if (flagSumaSecc && lineaSeccion.getTipoPago() == Constants.EST_SECCION_PAGO)
			{
				totalPago += seccion.calculaTotal();
				this.log.debug("\tseccionSuma:" + seccion.getIdTipoSeccion() + "::" + lineaSeccion.getTipoPago() + "::");
			} else
				this.log.debug("\tseccion NO Suma:" + seccion.getIdTipoSeccion() + "::" + lineaSeccion.getTipoPago() + "::");

			seccion.setTipoPago(lineaSeccion.getTipoPago());
		}
		
		if (totalPago < 0)
			totalPago = 0;
		comprobante.setTotal(totalPago);
		
		
		long cb = this.comprobanteDao.guardaNuevoComprobante(comprobante);
		
		this.comprobanteDao.guardaSeccionDNP(cb,tipoNomina);
		
		//GMALLEA 04-06-2014 Obtenimos el total imponible de los cotizantes
		NominaVO nominaVO = this.nominaDao.getNominabyCodigoBarra(cb);
		
		List listCotizantes = this.cotizanteDAO.getCotizantesNomina(nominaVO.getEmpresa().getIdEmpresa(), nominaVO.getConvenio().getIdConvenio(), tipoNomina.charAt(0));
		
		long totalImponible = 0;
		for (Iterator it = listCotizantes.iterator(); it.hasNext();){		
			
			CotizanteVO cotizacion = (CotizanteVO) it.next();
			
			if(tipoNomina.charAt(0) == Constants.TIPO_NOM_REMUNERACION){
				
				CotizacionREVO cotizacionREVO = (CotizacionREVO)cotizacion.getCotizacion();
				 
				 if(cotizacionREVO.getRentaImp() > 0){
						totalImponible = totalImponible + cotizacionREVO.getRentaImp();
					}else{
						totalImponible = totalImponible + cotizacionREVO.getRentaImpInp();
					}
				 
			}else if(tipoNomina.charAt(0) == Constants.TIPO_NOM_RELIQUIDACION){
				
				CotizacionRAVO cotizacionRAVO = (CotizacionRAVO)cotizacion.getCotizacion();
				 
				 if(cotizacionRAVO.getRenta() > 0){
					 totalImponible = totalImponible + cotizacionRAVO.getRenta();
					}else{
						totalImponible = totalImponible + cotizacionRAVO.getRentaImpInp();
					}
				 				 
			}else if(tipoNomina.charAt(0) == Constants.TIPO_NOM_GRATIFICACION){
				
				CotizacionGRVO cotizacionGRVO = (CotizacionGRVO)cotizacion.getCotizacion();
				 
				 if(cotizacionGRVO.getRenta() > 0){
					 totalImponible = totalImponible + cotizacionGRVO.getRenta();
					}else{
						totalImponible = totalImponible + cotizacionGRVO.getRentaImpInp();
					}
				 				 
			}else if(tipoNomina.charAt(0) == Constants.TIPO_NOM_DEPOSITOCONVENIDO){
				 
				CotizacionDCVO cotizacionDCVO = (CotizacionDCVO)cotizacion.getCotizacion();
				 
				 if(cotizacionDCVO.getRentaImponible() > 0){
					 totalImponible = totalImponible + cotizacionDCVO.getRentaImponible();
					}else{
						totalImponible = totalImponible + cotizacionDCVO.getRentaImpInp();
					}
				}
		}
		System.out.println("***************** " + totalImponible);
		return cb;
	}

	/**
	 * busca una determinada seccion dentro de la lista de secciones recibida
	 * 
	 * @param idSeccion
	 * @param secciones
	 * @return
	 */
	private LineaComprobanteProvisorioVO buscaLineaSeccion(int idSeccion, List secciones)
	{
		for (Iterator xx = secciones.iterator(); xx.hasNext();)
		{
			LineaComprobanteProvisorioVO lineaSeccion = (LineaComprobanteProvisorioVO) xx.next();
			this.log.debug("\t\t\t\tlineaSeccion:" + lineaSeccion.getIdTipoDetalle() + "::");
			if (idSeccion == lineaSeccion.getIdTipoSeccion())
				return lineaSeccion;
		}
		return null;
	}

	/**
	 * busca un determinado detalle dentro de la lista de detalles recibida
	 * 
	 * @param idDetalleSeccion
	 * @param detalles
	 * @return
	 */
	private LineaComprobanteProvisorioVO buscaLineaDetalle(int idDetalleSeccion, List detalles)
	{
		for (Iterator xx = detalles.iterator(); xx.hasNext();)
		{
			LineaComprobanteProvisorioVO lineaDetalle = (LineaComprobanteProvisorioVO) xx.next();
			this.log.debug("\t\t\t\tlineaDetalle:" + lineaDetalle.getIdTipoDetalle() + "::");
			if (idDetalleSeccion == lineaDetalle.getIdTipoDetalle())
				return lineaDetalle;
		}
		return null;
	}

	/**
	 * anula folio
	 * 
	 * @param folio
	 * @throws DaoException
	 * @throws Exception
	 * @throws BusinessException
	 */
	//private void anulaFolio(long folio) throws Exception
	public void anulaFolio(long folio) throws Exception
	{
		// TODO descomentar anulacion folio tesoreria
		List listaParams = new ArrayList();
		listaParams.add(new Integer(Constants.PARAM_INT_TES_URL_SISTEMA));
		listaParams.add(new Integer(Constants.PARAM_INT_TES_USER_CONN));
		listaParams.add(new Integer(Constants.PARAM_INT_TES_PASS_CONN));
		listaParams.add(new Integer(Constants.PARAM_LIB_ANULA_FOLIO));
		ParametrosHash paramHash = this.parametrosDao.getParametrosHash(listaParams);
		ProxyAS400 proxyAS400 = new ProxyAS400();
		String result = proxyAS400.anularComprobante(folio, paramHash);
		if (!result.trim().equals(""))
			throw new Exception("problemas al anular folio");
	}

	/**
	 * anula folio
	 * 
	 * @param tipoProceso
	 * @param idConvenio
	 * @param rutEmpresa
	 * @throws DaoException
	 * @throws Exception
	 */
	public void anulaFolioByComprobante(char tipoProceso, int idConvenio, int rutEmpresa) throws DaoException, Exception
	{
		ComprobanteVO comprobante = this.comprobanteDao.getComprobante(idConvenio, "" + tipoProceso, "" + rutEmpresa);

		if (comprobante != null)
		{
			this.log.info("anulando folio:" + comprobante.getFolioTesoreria() + "::" + comprobante.getIdCodigoBarra() + "::");
			if (comprobante.getFolioTesoreria() > 0 && comprobante.getTotal() > 0)
			{
				try
				{
					anulaFolio(comprobante.getFolioTesoreria());
				} catch (Exception e)
				{
					this.log.error("error anulando folio", e);
					//throw new Exception("error.anulaFolio");
				}
				comprobante.setFolioTesoreria(0);
				this.comprobanteDao.guardaComprobante(comprobante);
			} else
				this.log.info("comprobante sin folio o total a pagar 0, no se anula");
		} else
			this.log.info("anulaFolio:no encontro comprobante:" + tipoProceso + "::" + idConvenio + "::" + rutEmpresa + "::");
	}

	/**
	 * construye lista comprobantes a pagar
	 * 
	 * @param codigos
	 * @return
	 * @throws DaoException
	 */
	public List construyeListaComprobantesAPagar(String[] codigos) throws DaoException
	{
		this.log.debug("Entra a: ComprobanteMgr.construyeListaComprobantesAPagar(codigos = " + codigos + ")");

		List consulta = new ArrayList();
		boolean soloCerosConPago = false;

		if ((codigos == null) || (codigos.length == 0))
			return null;

		ComprobanteVO comprobante;
		LineaComprobantesAPagar lineaConsulta;
		for (int i = 0; i < codigos.length; i++)
		{
			comprobante = this.getComprobante(Long.valueOf(codigos[i]));
			if (comprobante.getTotal() > 0)
			{
				lineaConsulta = new LineaComprobantesAPagar();
				lineaConsulta.setCodigoBarra(comprobante.getIdCodigoBarra());

				NominaVO nomina = this.getNomina(comprobante.getIdCodigoBarra());
				lineaConsulta.setRutFmt(Utils.formatRut(nomina.getRutEmpresa()));
				lineaConsulta.setRazonSocial(nomina.getEmpresa().getRazonSocial());
				lineaConsulta.setNombreConvenio(nomina.getConvenio().getDescripcion());
				lineaConsulta.setTotal(comprobante.getTotal());

				consulta.add(lineaConsulta);
			} else if (comprobante.getIdEstado() == Constants.EST_CMP_POR_PAGAR && comprobante.getTotal() == 0 && comprobante.tieneAlgoParaPago())
			{
				this.log.info("comprobante queda en estado pagado: total: $0, tiene algo para pagar::" + comprobante.getIdCodigoBarra() + "::");
				soloCerosConPago = true;
				NominaVO nomina = this.comprobanteDao.getNomina(comprobante.getIdCodigoBarra());
				comprobante.setPagado(new Timestamp(new Date().getTime()));
				if (comprobante.tienePagoParcial())
				{
					comprobante.setIdEstado(("" + Constants.EST_CMP_PAGO_PARCIAL).charAt(0));
					nomina.setIdEstado(Constants.EST_NOM_PAGADO_PARCIALMENTE);
				} else
				{
					comprobante.setIdEstado(("" + Constants.EST_CMP_PAGADO).charAt(0));
					nomina.setIdEstado(Constants.EST_NOM_PAGADO);
				}
			}
		}
		if (consulta.size() == 0 && soloCerosConPago)
			return null;
		Collections.sort(consulta);
		return consulta;
	}

	/**
	 * construye resumen comprobant provisorio
	 * 
	 * @param codigoBarra
	 * @return
	 * @throws DaoException
	 */
	public List construyeResumenComprobanteProvisorio(ComprobanteVO comprobante) throws DaoException
	{
		this.log.debug("Entra a: ComprobanteMgr.construyeResumenComprobantProvisorio(codigoBarra:" + (comprobante == null ? "null" : "" + comprobante.getIdCodigoBarra()) + ")");

		List detalleReporte = this.detalleReporteDao.getLista(REPORTE_EDICION_COMPROBANTES);

		if (comprobante == null)
			return null;
		if (comprobante.getSecciones() == null)
			return null;

		// Hace un mapa con las secciones que contiene el comprobante
		Map mapSecciones = new HashMap();
		SeccionVO sec;
		for (Iterator it = comprobante.getSecciones().iterator(); it.hasNext();)
		{
			sec = (SeccionVO) it.next();
			mapSecciones.put(new Integer(sec.getIdTipoSeccion()), sec);
		}

		List listaResumenComprobanteProvisorio = new ArrayList();
		TipoSeccionVO tipoSeccion;
		LineaResumenComprobanteProvisorio lineaRCProvisorio;
		DetalleReporteVO detReporte;
		boolean seccionConPagoIndividual = false;
		for (Iterator it = detalleReporte.iterator(); it.hasNext();)
		{
			detReporte = (DetalleReporteVO) it.next();

			// No hace nada si la seccion del reporte no esta en las llaves del conjunto de secciones del comprobante
			if (!mapSecciones.keySet().contains(new Integer(detReporte.getIdTipoSeccion())))
				continue;

			tipoSeccion = this.tipoSeccionDao.getTipoSeccion(detReporte.getIdTipoSeccion());
			seccionConPagoIndividual = tipoSeccion.getPagoIndividual() != 0;
			tipoSeccion.refreshListasM();

			// Busca la sección actual
			SeccionVO seccion = (SeccionVO) mapSecciones.get(new Integer(detReporte.getIdTipoSeccion()));

			if (!seccionConPagoIndividual && (seccion.getTipoPago() != Constants.EST_SECCION_PAGO))
				// Si no se paga, no lo agrega
				continue;

			DetalleSeccionVO detalleSeccion;
			long sumaDetalles = 0;
			boolean debeSumar;
			for (Iterator itp = seccion.getDetallesSeccion().iterator(); itp.hasNext();)
			{
				detalleSeccion = (DetalleSeccionVO) itp.next();
				debeSumar = false;

				if (seccionConPagoIndividual)
					debeSumar = detalleSeccion.getTipoPago() == Constants.EST_SECCION_PAGO;
				else
					debeSumar = seccion.getTipoPago() == Constants.EST_SECCION_PAGO;
				if (debeSumar)
					sumaDetalles += detalleSeccion.calculaTotal();
			}
			if (sumaDetalles != 0 || seccion.getIdTipoSeccion() == Constants.ID_TIPO_SECCION_CAJA || seccion.getIdTipoSeccion() == Constants.ID_TIPO_SECCION_INP)
			{
				lineaRCProvisorio = new LineaResumenComprobanteProvisorio();
				lineaRCProvisorio.setGlosa(tipoSeccion.getDescripcion());
				lineaRCProvisorio.setTotal(sumaDetalles);
				listaResumenComprobanteProvisorio.add(lineaRCProvisorio);
			}
		}

		return listaResumenComprobanteProvisorio;
	}

	public long calcularTotalDetalleSeccion(int idTipoSeccion, int idDetalleSeccion, List secciones)
	{
		SeccionVO seccion = null;
		for (Iterator it = secciones.iterator(); it.hasNext();)
		{
			SeccionVO sec = (SeccionVO) it.next();
			if (sec.getIdTipoSeccion() == idTipoSeccion)
				seccion = sec;
		}
		if (seccion != null)
		{
			for (Iterator itp = seccion.getDetallesSeccion().iterator(); itp.hasNext();)
			{
				DetalleSeccionVO detalleSeccion = (DetalleSeccionVO) itp.next();
				if (idDetalleSeccion == detalleSeccion.getIdDetalleSeccion())
					return detalleSeccion.calculaTotal();
			}
		}
		return 0;
	}

	public long calcularTotalDetalleSeccion(DetalleSeccionVO detalleSeccion)
	{
		detalleSeccion.refreshListaM();

		long suma = 0;
		String[] totalM = ((String)Constants.TOTAL_MX_SECCION.get("" + detalleSeccion.getIdTipoSeccion())).split("#");
		for (int i = 0; i < totalM.length; i++)
			suma += detalleSeccion.getM(new Integer(totalM[i]).intValue());
		return suma;
		//return (long) detalleSeccion.getM(this.comprobanteDao.getIndiceTotalTipoSeccion((detalleSeccion.getSeccion()).getIdTipoSeccion()));
	}

	/**
	 * construye lista edicion comprobantes
	 * 
	 * @param codigoBarra
	 * @return
	 * @throws DaoException
	 */
	public List construyeListaEdicionComprobantes(ComprobanteVO comprobante) throws DaoException
	{
		if (comprobante == null)
			return null;

		this.log.info("Entra a: ComprobanteMgr.construyeListaEdicionComprobantes(codigoBarra = " + comprobante.getIdCodigoBarra() + ")");
		List listaEdicionComprobantes;
		String tipoNomina;
		NominaVO nomina;

		List detalleReporte = this.detalleReporteDao.getLista(REPORTE_EDICION_COMPROBANTES);
		nomina = this.comprobanteDao.getNomina(comprobante.getIdCodigoBarra());
		tipoNomina = this.tipoProcesoNomina(nomina);
		if (comprobante.getSecciones() == null)
			return null;

		// Hace un mapa con las secciones que contiene el comprobante
		Map mapSecciones = new HashMap();
		SeccionVO sec;
		for (Iterator it = comprobante.getSecciones().iterator(); it.hasNext();)
		{
			sec = (SeccionVO) it.next();
			mapSecciones.put(new Integer(sec.getIdTipoSeccion()), sec);
		}

		// Construye la lista de secciones con sus correspondientes detalles
		DetalleReporteVO detReporte;
		LineaComprobanteProvisorioVO lineaCProvisorio;
		LineaComprobanteProvisorioVO lineaDetCProvisorio;
		LineaComprobanteProvisorioVO lineaDetDetCProvisorio;
		listaEdicionComprobantes = new ArrayList();
		Map mapTiposDetalleSeccion;
		TipoSeccionVO tipoSeccion;
		for (Iterator it = detalleReporte.iterator(); it.hasNext();)
		{
			detReporte = (DetalleReporteVO) it.next();

			// No hace nada si la seccion del reporte no esta en las llaves del conjunto de secciones del comprobante
			if (!mapSecciones.keySet().contains(new Integer(detReporte.getIdTipoSeccion())))
				continue;

			// Crea una nueva seccion linea de seccion
			lineaCProvisorio = new LineaComprobanteProvisorioVO();
			tipoSeccion = this.tipoSeccionDao.getTipoSeccion(detReporte.getIdTipoSeccion());
			lineaCProvisorio.setGlosa(tipoSeccion.getDescripcion());
			lineaCProvisorio.setPagableIndividual(Boolean.valueOf(tipoSeccion.getPagoIndividual() != 0));
			lineaCProvisorio.setDnp(Boolean.valueOf(tipoSeccion.getDnp() != 0));
			lineaCProvisorio.setDetalle(new ArrayList());
			tipoSeccion.refreshListasM();

			/*
			 * Crea la lista de detalles para la seccion actual
			 */
			// Busca la seccion actual
			SeccionVO seccion = (SeccionVO) mapSecciones.get(new Integer(detReporte.getIdTipoSeccion()));
			lineaCProvisorio.setIdCodigoBarra(seccion.getIdCodigoBarra());
			lineaCProvisorio.setIdTipoSeccion(seccion.getIdTipoSeccion());
			lineaCProvisorio.setIdTipoDetalle(0);

			// Setea el tipo de pago si corresponde
			if (!lineaCProvisorio.getPagableIndividual().booleanValue())
				lineaCProvisorio.setTipoPago(seccion.getTipoPago());

			// Llena un mapa de tipos de detalles de secciones para un acceso mas rapido
			mapTiposDetalleSeccion = new HashMap();
			TipoDetalleVO tipoDetalle;
			for (Iterator itp = tipoSeccion.getTipoDetalle().iterator(); itp.hasNext();)
			{
				tipoDetalle = (TipoDetalleVO) itp.next();
				if (tipoDetalle.getIdTipoNomina() != tipoNomina.charAt(0))
					continue;

				mapTiposDetalleSeccion.put(new Integer(tipoDetalle.getIdDetalleSeccion()), tipoDetalle);
			}

			// Construye la lista de detalles para la seccion actual
			DetalleSeccionVO detalleSeccion;
			lineaCProvisorio.setDetalle(new ArrayList());
			for (Iterator itp = seccion.getDetallesSeccion().iterator(); itp.hasNext();)
			{
				detalleSeccion = (DetalleSeccionVO) itp.next();
				detalleSeccion.refreshListaM();

				lineaDetCProvisorio = new LineaComprobanteProvisorioVO();
				// if (enIdsEntidadesFalsas(detalleSeccion.getIdDetalleSeccion(), seccion.getTipoSeccion().getClave()))
				if (!mapTiposDetalleSeccion.containsKey(new Integer(detalleSeccion.getIdDetalleSeccion())))
					continue;
				this.log.debug("buscando detalle seccion:" + detalleSeccion.getIdDetalleSeccion() + "::" + detalleSeccion.getIdTipoSeccion() + "::");
				lineaDetCProvisorio.setGlosa(((TipoDetalleVO) mapTiposDetalleSeccion.get(new Integer(detalleSeccion.getIdDetalleSeccion()))).getNombre());
				lineaDetCProvisorio.setTipoPago(detalleSeccion.getTipoPago());
				lineaDetCProvisorio.setIdCodigoBarra(detalleSeccion.getIdCodigoBarra());
				lineaDetCProvisorio.setIdTipoSeccion(seccion.getIdTipoSeccion());
				lineaDetCProvisorio.setIdTipoDetalle(detalleSeccion.getIdDetalleSeccion());
				
				if (seccion.getIdTipoSeccion() == 5){
					seccion.calculaTotal();
					detalleSeccion.calculaTotal();
					lineaCProvisorio.setSfe(detalleSeccion.getM3() < 0 ? Utils.formatMonto(Math.abs((long)detalleSeccion.getM3())) :"");
					lineaCProvisorio.setSfi(Utils.formatMonto(seccion.getTotal()));
				}
				if (seccion.getIdTipoSeccion() == 3){
					seccion.calculaTotal();
					detalleSeccion.calculaTotal();
					lineaCProvisorio.setSfe(seccion.getMm11() < 0 ? Utils.formatMonto(Math.abs((long)seccion.getMm11())) : "");
					lineaCProvisorio.setSfi(Utils.formatMonto(seccion.getTotal()));
				}
				
				//csanchez
				if (Constants.TIPOS_SECCION_CAAF != null)
					if (Constants.TIPOS_SECCION_CAAF.contains(new Integer(detalleSeccion.getIdTipoSeccion())))
						if (detalleSeccion.getIdDetalleSeccion() == Constants.ID_TIPO_DETALLE_ARAUCANA)
							lineaCProvisorio.setMostrar3(true);
				// Construye la lista con el detalle de cada detalle de seccion
				int idxMonto;
				lineaDetCProvisorio.setDetalle(new ArrayList());
				for (Iterator itpp = detReporte.getListaMontos().iterator(); itpp.hasNext();)
				{
					idxMonto = ((Integer) itpp.next()).intValue();
					lineaDetDetCProvisorio = new LineaComprobanteProvisorioVO();
					if (tipoSeccion.getDescripcion().toUpperCase().indexOf("MUTUALES") == -1)
					{
						// No es mutual, caso general
						lineaDetDetCProvisorio.setGlosa(tipoSeccion.getNombre(idxMonto));
					} else
					{
						// Es mutual, caso especial
						float tasaAdicionalMutual = nomina.getConvenio().getMutualTasaAdicional();
						float tasaFijaMutual = Constants.TASA_FIJA_MUTUAL;
						DecimalFormat decimalFmt = new DecimalFormat("#0.0#");
						lineaDetDetCProvisorio.setGlosa("Tasa fija " + decimalFmt.format(tasaFijaMutual) + " y tasa adicional " + decimalFmt.format(tasaAdicionalMutual));
					}
					lineaDetDetCProvisorio.setTotal(Math.round(detalleSeccion.getM(idxMonto)));

					lineaDetCProvisorio.getDetalle().add(lineaDetDetCProvisorio);
				}

				lineaCProvisorio.getDetalle().add(lineaDetCProvisorio);
			}

			listaEdicionComprobantes.add(lineaCProvisorio);
		}

		return listaEdicionComprobantes;
	}

	/**
	 * retorna un comprobante de acuerdo al código de barras recibido
	 * 
	 * @param idCodigoBarra
	 * @return
	 * @throws DaoException
	 */
	public ComprobanteVO getComprobante(Long idCodigoBarra) throws DaoException
	{
		return this.comprobanteDao.getComprobante(idCodigoBarra.longValue());
	}

	/**
	 * Retorna un comprobante de pago de acuerdo al tipo de nómina, convenio y RUT de la empresa
	 * 
	 * @param tipoProceso
	 * @param idConvenio
	 * @param rutEmpresa
	 * @return
	 * @throws DaoException
	 */
	public ComprobanteVO getComprobante(char tipoProceso, int idConvenio, int rutEmpresa) throws DaoException {
		return this.comprobanteDao.getComprobante(idConvenio, "" + tipoProceso, "" + rutEmpresa);
	}

	public ComprobanteVO armaComprobante(long idCodigoBarra) throws DaoException
	{
		//clillo 13-10-2017 AFP
		List listaParams = new ArrayList();
		listaParams.add(new Integer(Constants.PARAM_FIN_PAGO_CAJA));
		listaParams.add(new Integer(Constants.PARAM_FIN_PAGO_CAJA_IND));
		ParametrosHash paramHash = this.parametrosDao.getParametrosHash(listaParams);
			
		String diaFinPagoCaja = paramHash.get("" + Constants.PARAM_FIN_PAGO_CAJA).substring(0, 2);
		
		ComprobanteVO cmp = new ComprobanteVO(this.comprobanteDao.getComprobante(idCodigoBarra));
		cmp.setDiaFinPagoCaja(Integer.parseInt(diaFinPagoCaja));
		List secciones = this.comprobanteDao.getSecciones(idCodigoBarra);
		cmp.setSecciones(secciones);
		cmp.setIdCodigoBarra(idCodigoBarra);
		return cmp;
	}

	/**
	 * comprobante por pagar
	 * 
	 * @param idCodigoBarra
	 * @return
	 * @throws DaoException
	 */
	public ComprobanteVO getCmpPorPagar(Long idCodigoBarra) throws DaoException
	{
		return this.comprobanteDao.getCmpPorPagar(idCodigoBarra.longValue());
	}

	/**
	 * comprobante por pagar
	 * 
	 * @param idConvenio
	 * @param tipoProceso
	 * @param rutEmpresa
	 * @return
	 * @throws DaoException
	 */
	public ComprobanteVO getCmpPorPagar(int idConvenio, String tipoProceso, String rutEmpresa) throws DaoException
	{
		return this.comprobanteDao.getCmpPorPagar(idConvenio, tipoProceso, rutEmpresa);
	}

	/**
	 * tipos proceso
	 * 
	 * @return
	 * @throws DaoException
	 */
	public Collection getTiposProceso() throws DaoException
	{
		return this.nominaDao.getTiposNominas();
	}

	/**
	 * spl pago
	 * 
	 * @param idTrx
	 * @return
	 * @throws DaoException
	 */
	public SPLPagoVO getSPLPago(long idTrx) throws DaoException
	{
		return this.comprobanteDao.getSPLPago(idTrx);
	}

	/**
	 * cursa tesoreria
	 * 
	 * @param user
	 * @param listaFolios
	 * @throws DaoException
	 */
	public void cursaTesoreria(String user, Set listaFolios) throws DaoException
	{
		this.comprobanteDao.cursaTesoreria(user, listaFolios);
	}

	/**
	 * registra pago
	 * 
	 * @param pago
	 * @return
	 * @throws DaoException
	 */
	public List registraPago(SPLPagoVO pago) throws DaoException
	{
		return this.comprobanteDao.registraPago(pago);
	}

	public List verificaParaPago(List listaCodBarras) throws DaoException
	{
		List newLista = new ArrayList();
		for (Iterator it = listaCodBarras.iterator(); it.hasNext();)
		{
			Long idCB = (Long) it.next();
			ComprobanteVO comprobante = this.getCmpPorPagar(idCB);
			if (comprobante != null)
			{
				if (comprobante.getIdEstado() == Constants.EST_CMP_POR_PAGAR && comprobante.getTotal() == 0 && comprobante.tieneAlgoParaPago())
				{
					this.log.info("comprobante queda en estado pagado: total: $0, tiene algo para pagar::" + comprobante.getIdCodigoBarra() + "::");
					NominaVO nomina = this.comprobanteDao.getNomina(comprobante.getIdCodigoBarra());
					comprobante.setPagado(new Timestamp(new Date().getTime()));
					if (comprobante.tienePagoParcial())
					{
						comprobante.setIdEstado(("" + Constants.EST_CMP_PAGO_PARCIAL).charAt(0));
						nomina.setIdEstado(Constants.EST_NOM_PAGADO_PARCIALMENTE);
					} else
					{
						comprobante.setIdEstado(("" + Constants.EST_CMP_PAGADO).charAt(0));
						nomina.setIdEstado(Constants.EST_NOM_PAGADO);
					}
				} else if (comprobante.getTotal() > 0)
					newLista.add(idCB);
			}
		}
		return newLista;
	}

	/**
	 * Por cada comprobante recibido, primero busca en DB y hace los calculos necesarios para el seteo de valores, si el comprobante no tiene folio => se llama al proxy para que se comunique con la
	 * AS400, y solicite un nuevo folio. si ya tiene folio, solo setea los valores adecuados.
	 * 
	 * @param pago
	 * @param listaCodBarras
	 * @return
	 * @throws Exception
	 */
	public PagoEnLinea getFolio(PagoEnLinea pago, List listaCodBarras) throws Exception
	{
		//TODO descomentar getFolio
		/*pago.setPagador("99888777");
		pago.setCorrelativo("123456");
		Folio folio1 = new Folio();
		folio1.setMonto(12345678);
		folio1.setDetalle("Nomina para Convenio xxx");
		folio1.setNumero(12);
		pago.addFolio(folio1);
		pago.setResult("");
		return pago;*/

		try
		{
			List listaParams = new ArrayList();
			listaParams.add(new Integer(Constants.PARAM_INT_TES_URL_SISTEMA));
			listaParams.add(new Integer(Constants.PARAM_INT_TES_USER_CONN));
			listaParams.add(new Integer(Constants.PARAM_INT_TES_PASS_CONN));
			listaParams.add(new Integer(Constants.PARAM_INT_TES_LIB_CREA_FOLIO));
			listaParams.add(new Integer(Constants.PARAM_INT_TES_SUC_EMP_LCF));
			listaParams.add(new Integer(Constants.PARAM_INT_TES_COD_OF_LCF));
			listaParams.add(new Integer(Constants.PARAM_INT_TES_COD_AREA_NEG_LCF));
			listaParams.add(new Integer(Constants.PARAM_COD_SISTEMA_SPL));
			listaParams.add(new Integer(Constants.PARAM_USER_GENERA_FOLIO));
			ParametrosHash paramHash = this.parametrosDao.getParametrosHash(listaParams);

			List listaComprobantes = this.comprobanteDao.getComprobantes(listaCodBarras);
			ProxyAS400 proxyAS400 = new ProxyAS400(paramHash);
//TODO get folio
			for (Iterator it = listaComprobantes.iterator(); it.hasNext();)
			{
				ComprobanteVO comprobante = (ComprobanteVO) it.next();// por cada comprobante a pagar
				comprobante.refreshListaM();
				this.log.info("\ncomprobante:" + comprobante.getIdCodigoBarra() + "::");
				NominaVO nomina = this.comprobanteDao.getNomina(comprobante.getIdCodigoBarra());
				EmpresaVO empresa = this.empresaDao.getEmpresa(nomina.getRutEmpresa());
				//TODO 06-09-2012 GMALLEA Se comenta ya que se esta mandando como pagador el rut de la empresa
				//pago.setPagador("" + empresa.getIdEmpresa());

				HashMap totalCodigo = new HashMap();
				List mapeoTesoreria = this.mapeoTesoreriaDao.getSeccionConceptoTes(nomina.getTipoProceso());

				for (Iterator it2 = mapeoTesoreria.iterator(); it2.hasNext();)
				{
					MapeoTesoreriaVO mapeo = (MapeoTesoreriaVO) it2.next();
					
					//INICIO GMALLEA 
						if(mapeo.getAplicaPara().trim().equals(Constants.CONCEPTO_TESORERIA_APLICA_PARA_EMPRESA)){
							
							this.log.info("concepto Empresa:" + mapeo.getIdConceptoTesoreria() + "::");
							long monto = comprobante.getMonto(mapeo.getIdTipoSeccion(), mapeo.getIdDetalleSeccion(), mapeo.getIdMontoDetSeccion());
							this.log.info("obteniendo monto:" + monto + ":idMonto:" + mapeo.getIdMontoDetSeccion() + ":tipoSeccion:" + mapeo.getIdTipoSeccion() + ":idConcepto:"
									+ mapeo.getIdConceptoTesoreria() + "::");
							if (totalCodigo.containsKey("" + mapeo.getIdConceptoTesoreria()))
								monto += ((Long) totalCodigo.get("" + mapeo.getIdConceptoTesoreria())).longValue();
							totalCodigo.put("" + mapeo.getIdConceptoTesoreria(), new Long(monto));
							this.log.info("\ttotal seccion:" + mapeo.getIdTipoSeccion() + ":montoAcumulado por codigo tes:" + monto + ":tct.getIdMonto():" + mapeo.getIdMontoDetSeccion() + "::");
							
						}else if(mapeo.getAplicaPara().trim().equals(Constants.CONCEPTO_TESORERIA_APLICA_PARA_AMBOS)){
							
							this.log.info("concepto Ambos:" + mapeo.getIdConceptoTesoreria() + "::");
							long monto = comprobante.getMonto(mapeo.getIdTipoSeccion(), mapeo.getIdDetalleSeccion(), mapeo.getIdMontoDetSeccion());
							this.log.info("obteniendo monto:" + monto + ":idMonto:" + mapeo.getIdMontoDetSeccion() + ":tipoSeccion:" + mapeo.getIdTipoSeccion() + ":idConcepto:"
									+ mapeo.getIdConceptoTesoreria() + "::");
							if (totalCodigo.containsKey("" + mapeo.getIdConceptoTesoreria()))
								monto += ((Long) totalCodigo.get("" + mapeo.getIdConceptoTesoreria())).longValue();
							totalCodigo.put("" + mapeo.getIdConceptoTesoreria(), new Long(monto));
							this.log.info("\ttotal seccion:" + mapeo.getIdTipoSeccion() + ":montoAcumulado por codigo tes:" + monto + ":tct.getIdMonto():" + mapeo.getIdMontoDetSeccion() + "::");
						}
					}
				this.log.info("\n\nmontos por concepto:");
				for (Iterator it2 = totalCodigo.keySet().iterator(); it2.hasNext();)
				{
					String key = (String) it2.next();
					this.log.info("\tconcepto:" + key + ":monto:" + totalCodigo.get(key) + "::");
				}
				if (comprobante.getFolioTesoreria() == 0)
					proxyAS400.getFolio(empresa, comprobante, totalCodigo, paramHash, pago);
				else
				{
					//TODO Cambiar esto. debe ser distinto cada vez que se ejecute.
					pago.setCorrelativo(paramHash.get("" + Constants.PARAM_COD_SISTEMA_SPL) + comprobante.getFolioTesoreria());
					Folio folio = new Folio();
					folio.setMonto(comprobante.getTotal());
					folio.setDetalle("Nomina para Convenio " + comprobante.getIdCodigoBarra());
					folio.setNumero(comprobante.getFolioTesoreria());
					pago.addFolio(folio);
					pago.setResult("");
				}
				this.log.info("\nn sale comprobante:" + comprobante.getIdCodigoBarra() + "::");
				this.comprobanteDao.guardaComprobante(comprobante);
			}
		} catch (Exception error)
		{
			this.log.error("\n\nComprobanteMgr: ERROR en getFolio:", error);
			pago.setResult(Constants.AS400_GENERIC_ERROR);
		}
		return pago;
	}

	/**
	 * actualiza libro banco
	 * 
	 * @param pago
	 * @return
	 * @throws DaoException
	 */
	public int updateLibroBanco(SPLPagoVO pago) throws DaoException
	{
		List listaParams = new ArrayList();
		listaParams.add(new Integer(Constants.PARAM_INT_TES_URL_SISTEMA));
		listaParams.add(new Integer(Constants.PARAM_INT_TES_USER_CONN));
		listaParams.add(new Integer(Constants.PARAM_INT_TES_PASS_CONN));
		listaParams.add(new Integer(Constants.PARAM_INT_TES_LIB_UP_LIBRO_BCO));
		ParametrosHash paramHash = this.parametrosDao.getParametrosHash(listaParams);
		ProxyAS400 proxyAS400 = new ProxyAS400();
		String result = proxyAS400.updateLibroBanco(pago, paramHash);
		if (result == null || result.equals("1")) // ocurrio un error
			return -1;
		return 1; // proceso OK
	}

	/**
	 * generar archivos
	 * 
	 * @param listaIds
	 * @return
	 * @throws Exception
	 */
	public String generarArchivos(List listaIds) throws Exception
	{
		List listaComprobantes = this.comprobanteDao.getComprobantes(listaIds);
		List listaTipoSeccion = this.mapeoTesoreriaDao.getTipoSeccion();
		List listaDetReporte = getDetalleReporte(Constants.DET_REPORTE_PDF);
		HashMap listaLeyendas = this.detalleReporteDao.getPropertiesMapeo(Constants.PROP_MAPEO_MX_LEYENDA);
		Collection tmpList = getTiposProceso();
		HashMap tipoNominas = new HashMap();
		for (Iterator it = tmpList.iterator(); it.hasNext();)
		{
			TipoNominaVO tn = (TipoNominaVO) it.next();
			tipoNominas.put(tn.getIdTipoNomina(), tn.getDescripcion().trim());
		}

		List listaParams = new ArrayList();
		listaParams.add(new Integer(Constants.PARAM_TASA_FIJA));
		listaParams.add(new Integer(Constants.PARAM_PERIODO));
		listaParams.add(new Integer(Constants.PARAM_PUBLISHER_NAME));
		listaParams.add(new Integer(Constants.PARAM_DOC_TYPE_NAME_PDF));
		listaParams.add(new Integer(Constants.PARAM_ID_LOG_PDF));
		listaParams.add(new Integer(Constants.PARAM_PERIODO_INDEPENDIENTE));
		listaParams.add(new Integer(Constants.PARAM_FIN_PAGO_CAJA));
		listaParams.add(new Integer(Constants.PARAM_FIN_PAGO_CAJA_IND));
		
		ParametrosHash paramHash = this.parametrosDao.getParametrosHash(listaParams);

		ConstructorPDF constructor = new ConstructorPDF(paramHash, tipoNominas, listaTipoSeccion, listaDetReporte, listaLeyendas);
		HashMap listaEmpresas = new HashMap();
		HashMap listaRepresentantes = new HashMap();
		HashMap listaNominas = new HashMap();
		HashMap listaConvenios = new HashMap();
		String nombreArchivo = "";

		String fechaLimiteCaja = paramHash.get("" + Constants.PARAM_FIN_PAGO_CAJA);
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy kk:mm");
		Date fechaLimite = new Date(formato.parse(fechaLimiteCaja.trim()).getTime());
		boolean sinFolio=false;
		if (new Date().getTime() > fechaLimite.getTime()){
			sinFolio=true;
		}
		
		for (Iterator it = listaComprobantes.iterator(); it.hasNext();)
		{
			ComprobanteVO comprobante = (ComprobanteVO) it.next();
			if (comprobante.getIdEstado() == Constants.EST_CMP_POR_PAGAR && comprobante.getTotal() == 0 && comprobante.tieneAlgoParaPago())
			{
				this.log.info("comprobante queda en estado pagado: total: $0, tiene algo para pagar::" + comprobante.getIdCodigoBarra() + "::");
				NominaVO nomina = this.comprobanteDao.getNomina(comprobante.getIdCodigoBarra());
				comprobante.setPagado(new Timestamp(new Date().getTime()));
				if (comprobante.tienePagoParcial())
				{
					comprobante.setIdEstado(("" + Constants.EST_CMP_PAGO_PARCIAL).charAt(0));
					nomina.setIdEstado(Constants.EST_NOM_PAGADO_PARCIALMENTE);
				} else
				{
					comprobante.setIdEstado(("" + Constants.EST_CMP_PAGADO).charAt(0));
					nomina.setIdEstado(Constants.EST_NOM_PAGADO);
				}
			} else if (comprobante.getFolioTesoreria() == 0 && !sinFolio)
			{
				List listaTmp = new ArrayList();
				listaTmp.add(new Long(comprobante.getIdCodigoBarra()));
				this.log.info("generando comprobante para::" + comprobante.getIdCodigoBarra() + "::");
				try
				{
					PagoEnLinea pago = getFolio(new PagoEnLinea(), listaTmp);
					if (!pago.getResult().equals(""))
						throw new Exception("ERROR en obtencion de folio");
				} catch (Exception e)
				{
					throw new Exception("ERROR en obtencion de folio");
				}
			}
			this.comprobanteDao.loadConfigPDF(comprobante.getSecciones());
			NominaVO nomina = this.getNomina(comprobante.getIdCodigoBarra());
			int idConvenio = nomina.getIdConvenio();
			char tipoProceso = nomina.getTipoProceso();
			int rutEmpresa = nomina.getRutEmpresa();

			listaNominas.put("" + comprobante.getIdCodigoBarra(), nomina);
			EmpresaVO empresa = this.empresaDao.getEmpresaCasaMatriz(nomina.getRutEmpresa());

			ConvenioVO convenio = new ConvenioVO();
			convenio = this.convenioDao.getConvenio(empresa.getIdEmpresa(), idConvenio);
			empresa.setGrupoConvenio(convenio.getIdGrupoConvenio());

			listaEmpresas.put("" + rutEmpresa, empresa);
			listaConvenios.put(rutEmpresa + "#" + idConvenio, convenio);
			PersonaVO representante = this.empresaDao.getRepresentante(Long.parseLong(String.valueOf(empresa.getIdRepLegal())));
			listaRepresentantes.put(String.valueOf(empresa.getIdEmpresa()), representante);

			if (listaComprobantes.size() > 1 && "".equals(nombreArchivo))
				nombreArchivo = Constants.RUTA_CMPS + "Comprobante_" + tipoProceso + "_" + paramHash.get("" + Constants.PARAM_PERIODO) + ".pdf";
			else if ("".equals(nombreArchivo))
				nombreArchivo = Constants.RUTA_CMPS + "Comprobante" + Utils.formatRut(rutEmpresa) + "_" + tipoProceso + "_"
						+ (idConvenio < 10 ? "0" : "") + idConvenio + "_" + paramHash.get("" + Constants.PARAM_PERIODO) + ".pdf";
		}//TODO GMALLEA: Se manda " " para que el pdf obtenga los valores de una Empresa ya que se valida si es independiente o no.
		boolean result = constructor.createPDFtoPago(true, nombreArchivo, listaComprobantes, listaEmpresas, listaNominas, listaRepresentantes, listaConvenios , "",new HashMap());

		if (!result)
			throw new Exception("ERROR en la generacion de PDF para pago por caja::");
		return nombreArchivo;
	}
	
	/**
	 * generar archivos
	 * 
	 * @param listaIds
	 * @return
	 * @throws Exception
	 */
	public String generarArchivosDeclaraNoPago(long idCodigoBarra) throws Exception
	{
		//List listaComprobantes = this.comprobanteDao.getComprobantes(listaIds);
		//Se crea un comprobante para generar el PDF
		ComprobanteVO comprobanteVO = new ComprobanteVO();		
		java.sql.Timestamp timeStamp =new Timestamp(System.currentTimeMillis());
		List listSeccionesDNP = this.nominaDao.getSeccionDNP(idCodigoBarra);
		
		comprobanteVO.setEmitido(timeStamp);
		comprobanteVO.setIdCodigoBarra(idCodigoBarra);
		comprobanteVO.setIdDocumento(1898);
		comprobanteVO.setIdEstado('3');
		comprobanteVO.setNumTrabajadores(1);
		comprobanteVO.setPagado(timeStamp);
		comprobanteVO.setSecciones(listSeccionesDNP);
		
		List listaComprobantes = new ArrayList();
		listaComprobantes.add(comprobanteVO);
		
		List listaTipoSeccion = this.mapeoTesoreriaDao.getTipoSeccion();
		List listaDetReporte = getDetalleReporte(Constants.DET_REPORTE_PDF);
		HashMap listaLeyendas = this.detalleReporteDao.getPropertiesMapeo(Constants.PROP_MAPEO_MX_LEYENDA);
		Collection tmpList = getTiposProceso();
		HashMap tipoNominas = new HashMap();
		for (Iterator it = tmpList.iterator(); it.hasNext();)
		{
			TipoNominaVO tn = (TipoNominaVO) it.next();
			tipoNominas.put(tn.getIdTipoNomina(), tn.getDescripcion().trim());
		}

		List listaParams = new ArrayList();
		listaParams.add(new Integer(Constants.PARAM_TASA_FIJA));
		listaParams.add(new Integer(Constants.PARAM_PERIODO));
		listaParams.add(new Integer(Constants.PARAM_PUBLISHER_NAME));
		listaParams.add(new Integer(Constants.PARAM_DOC_TYPE_NAME_PDF));
		listaParams.add(new Integer(Constants.PARAM_ID_LOG_PDF));
		listaParams.add(new Integer(Constants.PARAM_PERIODO_INDEPENDIENTE));
		listaParams.add(new Integer(Constants.PARAM_FIN_PAGO_CAJA));
		
		ParametrosHash paramHash = this.parametrosDao.getParametrosHash(listaParams);

		ConstructorPDF constructor = new ConstructorPDF(paramHash, tipoNominas, listaTipoSeccion, listaDetReporte, listaLeyendas);
		HashMap listaEmpresas = new HashMap();
		HashMap listaRepresentantes = new HashMap();
		HashMap listaNominas = new HashMap();
		HashMap listaConvenios = new HashMap();
		String nombreArchivo = "";

		for (Iterator it = listaComprobantes.iterator(); it.hasNext();)
		{
			ComprobanteVO comprobante = (ComprobanteVO) it.next();
			if (comprobante.getIdEstado() == Constants.EST_CMP_POR_PAGAR && comprobante.getTotal() == 0 && comprobante.tieneAlgoParaPago())
			{
				this.log.info("comprobante queda en estado pagado: total: $0, tiene algo para pagar::" + comprobante.getIdCodigoBarra() + "::");
				NominaVO nomina = this.comprobanteDao.getNomina(comprobante.getIdCodigoBarra());
				comprobante.setPagado(new Timestamp(new Date().getTime()));
				if (comprobante.tienePagoParcial())
				{
					comprobante.setIdEstado(("" + Constants.EST_CMP_PAGO_PARCIAL).charAt(0));
					nomina.setIdEstado(Constants.EST_NOM_PAGADO_PARCIALMENTE);
				} else
				{
					comprobante.setIdEstado(("" + Constants.EST_CMP_PAGADO).charAt(0));
					nomina.setIdEstado(Constants.EST_NOM_PAGADO);
				}
			} else if (comprobante.getFolioTesoreria() == 0)
			{
				List listaTmp = new ArrayList();
				listaTmp.add(new Long(comprobante.getIdCodigoBarra()));
				this.log.info("generando comprobante para::" + comprobante.getIdCodigoBarra() + "::");
				/*try
				{
					PagoEnLinea pago = getFolio(new PagoEnLinea(), listaTmp);
					if (!pago.getResult().equals(""))
						throw new Exception("ERROR en obtencion de folio");
				} catch (Exception e)
				{
					throw new Exception("ERROR en obtencion de folio");
				}*/
			}
			//this.comprobanteDao.loadConfigPDF(comprobante.getSecciones());
			List listSeccionDNP = nominaDao.getSeccionDNP(comprobante.getIdCodigoBarra());
			SeccionDNPVO seccionDNPVO  = (SeccionDNPVO)listSeccionDNP.get(0);
			
			//NominaVO nomina =  this.getNomina(comprobante.getIdCodigoBarra());
			int idConvenio = seccionDNPVO.getIdConvenio();
			char tipoProceso = seccionDNPVO.getTipoProceso().charAt(0);
			int rutEmpresa = seccionDNPVO.getIdEmpresa();

			listaNominas.put("" + comprobante.getIdCodigoBarra(), seccionDNPVO);
			EmpresaVO empresa = this.empresaDao.getEmpresaCasaMatriz(seccionDNPVO.getIdEmpresa());

			ConvenioVO convenio = new ConvenioVO();
			convenio = this.convenioDao.getConvenio(empresa.getIdEmpresa(), idConvenio);
			empresa.setGrupoConvenio(convenio.getIdGrupoConvenio());

			listaEmpresas.put("" + rutEmpresa, empresa);
			listaConvenios.put(rutEmpresa + "#" + idConvenio, convenio);
			PersonaVO representante = this.empresaDao.getRepresentante(Long.parseLong(String.valueOf(empresa.getIdRepLegal())));
			listaRepresentantes.put(String.valueOf(empresa.getIdEmpresa()), representante);

			if (listaComprobantes.size() > 1 && "".equals(nombreArchivo))
				nombreArchivo = Constants.RUTA_CMPS + "Comprobante_" + tipoProceso + "_" + paramHash.get("" + Constants.PARAM_PERIODO) + ".pdf";
			else if ("".equals(nombreArchivo))
				nombreArchivo = Constants.RUTA_CMPS + "Comprobante" + Utils.formatRut(rutEmpresa) + "_" + tipoProceso + "_"
						+ (idConvenio < 10 ? "0" : "") + idConvenio + "_" + paramHash.get("" + Constants.PARAM_PERIODO) + ".pdf";
		}//TODO GMALLEA: Se manda " " para que el pdf obtenga los valores de una Empresa ya que se valida si es independiente o no.
		boolean result = constructor.createPDFtoPagoDeclaraNoPago(true, nombreArchivo, listaComprobantes, listaEmpresas, listaNominas, listaRepresentantes, listaConvenios , "",new HashMap());

		if (!result)
			throw new Exception("ERROR en la generacion de PDF para pago por caja::");
		return nombreArchivo;
	}

	/**
	 * carga on demand
	 * 
	 * @param listaComprobantes
	 * @return
	 */
	public boolean cargaOnDemand(List listaComprobantes)
	{
		try
		{
			List indices = new ArrayList();
			List pathNames = new ArrayList();
			Collection tmpList = getTiposProceso();
			HashMap tipoNominas = new HashMap();
			List listaDetReporte = getDetalleReporte(Constants.DET_REPORTE_PDF);
			List listaTipoSeccion = this.mapeoTesoreriaDao.getTipoSeccion();
			HashMap listaLeyendas = this.detalleReporteDao.getPropertiesMapeo(Constants.PROP_MAPEO_MX_LEYENDA);
			for (Iterator it = tmpList.iterator(); it.hasNext();)
			{
				TipoNominaVO tn = (TipoNominaVO) it.next();
				tipoNominas.put(tn.getIdTipoNomina(), tn.getDescripcion().trim());
			}

			List listaParams = new ArrayList();
			listaParams.add(new Integer(Constants.PARAM_TASA_FIJA));
			listaParams.add(new Integer(Constants.PARAM_PERIODO));
			listaParams.add(new Integer(Constants.PARAM_PUBLISHER_NAME));
			listaParams.add(new Integer(Constants.PARAM_DOC_TYPE_NAME_PDF));
			listaParams.add(new Integer(Constants.PARAM_ID_LOG_PDF));
			listaParams.add(new Integer(Constants.PARAM_PERIODO_INDEPENDIENTE));
			ParametrosHash paramHash = this.parametrosDao.getParametrosHash(listaParams);

			ComprobantePagadoPDF constructor = new ComprobantePagadoPDF(paramHash, tipoNominas, listaTipoSeccion, listaDetReporte, listaLeyendas);
			for (Iterator it = listaComprobantes.iterator(); it.hasNext();)
			{
				ComprobanteVO comprobante = (ComprobanteVO) it.next();
				if (comprobante.getFolioTesoreria() == 0)
				{
					List listaTmp = new ArrayList();
					listaTmp.add(new Long(comprobante.getIdCodigoBarra()));
					getFolio(new PagoEnLinea(), listaTmp);
				}
				NominaVO nomina = getNomina(comprobante.getIdCodigoBarra());
				int idConvenio = nomina.getIdConvenio();
				char tipoProceso = nomina.getTipoProceso();
				int rutEmpresa = nomina.getRutEmpresa();
				EmpresaVO empresa = this.empresaDao.getEmpresaCasaMatriz(rutEmpresa);

				ConvenioVO convenio = this.convenioDao.getConvenio(rutEmpresa, idConvenio);
				empresa.setGrupoConvenio(convenio.getIdGrupoConvenio());
				
				this.comprobanteDao.loadConfigPDF(comprobante.getSecciones());
				
				String nameFile = "Comprobante" + rutEmpresa + "-" + Utils.generaDV(rutEmpresa) + "_" + tipoProceso + "_"
						+ (idConvenio < 10 ? "0" : "") + idConvenio + "_" + paramHash.get("" + Constants.PARAM_PERIODO) + ".pdf";
				PersonaVO representante = this.empresaDao.getRepresentante(empresa.getIdRepLegal().longValue());
				
//				Todo GMALLEA Se agrega el detalle de CCAF
				DetalleCajaInforme detalleCajaInforme  = this.detalleCcafByDB2(comprobante);
				
				boolean result = constructor.createPDFtoPago(nameFile, comprobante, empresa, idConvenio, tipoProceso, representante, convenio,empresa.getTipo(),detalleCajaInforme);
				if (!result)
					throw new Exception("ERROR en la generacion de PDF para pago por caja: codigo de barras:" + comprobante.getIdCodigoBarra() + "::");
				// 200610/863/78602340/TRANSPORTES VENTURELLI LTDA/1/R/81000000188735
				indices.add(paramHash.get("" + Constants.PARAM_PERIODO) + "/" + convenio.getIdGrupoConvenio() + "/" + empresa.getIdEmpresa() + "/" + (empresa.getRazonSocial().trim().length() < 25 ? empresa.getRazonSocial().trim() :  empresa.getRazonSocial().trim().substring(0, 25))+ "/" + idConvenio + "/"
						+ tipoProceso + "/" + comprobante.getIdCodigoBarra());
				pathNames.add(nameFile);
			}
			PublicadorPDF.publica(paramHash, indices, pathNames);
		} catch (Exception e)
		{
			this.log.error("error en generacion/publicacion docs PDF:", e);
			return false;
		}
		return true;
	}

	/**
	 * comprobantes
	 * 
	 * @param ids
	 * @return
	 * @throws DaoException
	 */
	public List getComprobantes(List ids) throws DaoException
	{
		return this.comprobanteDao.getComprobantes(ids);
	}

	/**
	 * tipos proceso nomina
	 * 
	 * @param nomina
	 * @return
	 */
	public String tipoProcesoNomina(NominaVO nomina)
	{

		if (nomina.getClass().equals(NominaREVO.class))
			return "R";
		else if (nomina.getClass().equals(NominaRAVO.class))
			return "A";
		else if (nomina.getClass().equals(NominaGRVO.class))
			return "G";
		else if (nomina.getClass().equals(NominaDCVO.class))
			return "D";
		else
			return "";
	}

	public long calcularTotalSeccion(int idTipoSeccion, List secciones)
	{
		for (Iterator it = secciones.iterator(); it.hasNext();)
		{
			SeccionVO sec = (SeccionVO) it.next();
			if (idTipoSeccion == sec.getIdTipoSeccion())
				return sec.calculaTotal();
		}
		return 0;
	}
public DetalleCajaInforme detalleCcafByDB2(ComprobanteVO comprobante) throws DaoException, ParseException{
		
		DetalleCajaInforme detalleCajaInforme = new DetalleCajaInforme();
		
		List listAporte = this.detalleAporteCcafDAO.getDetalleAporte(comprobante.getIdCodigoBarra());
		
		AporteInforme aporteInforme = new AporteInforme();
		AporteDetalleInforme aporteDetalleInforme = null;
		
		DetalleAporteCcafVO detalleAporteCcafVO=null;
		AporteDetalleInforme[] aporteDetalleInformeArray = new AporteDetalleInforme[listAporte.size()];
		
		int montoAporte=0;
		int contadorAporte = 0;
		for (Iterator itAporte = listAporte.iterator(); itAporte.hasNext();)
		{
			aporteDetalleInforme =  new AporteDetalleInforme();
			detalleAporteCcafVO = (DetalleAporteCcafVO) itAporte.next();
			
			aporteDetalleInforme.setFechaVencimiento(aporteDetalleInforme.parseFechaVencimiento(detalleAporteCcafVO.getFechaVencimiento()));
			aporteDetalleInforme.setInteresesReajuste((int) detalleAporteCcafVO.getInteresReajuste());
			aporteDetalleInforme.setMonto((int)detalleAporteCcafVO.getMonto());
			aporteDetalleInforme.setRentaPromedio((int)detalleAporteCcafVO.getRentaPromedio());
			montoAporte = (int)detalleAporteCcafVO.getMontoTotal();
			
			aporteDetalleInformeArray[contadorAporte] = aporteDetalleInforme;
			contadorAporte=contadorAporte+1;
		}
		
		if(montoAporte > 0){
			aporteInforme.setMonto(montoAporte);
			aporteInforme.setAporteDetalleInformes(aporteDetalleInformeArray);
			
			detalleCajaInforme.setAporteInforme(aporteInforme);
		}
		
		List listCredito = this.detalleCreditoCcafDAO.getDetalleCredito(comprobante.getIdCodigoBarra());
		
		CreditoInforme creditoInforme = new CreditoInforme();
		CreditoDetalleInforme creditoDetalleInforme = null;
		
		DetalleCreditoCcafVO detalleCreditoCcafVO = null;
		CreditoDetalleInforme[] creditoDetalleInformeArray = new CreditoDetalleInforme[listCredito.size()];
		
		int montoCredito=0;
		int contadorCredito =0;
		for (Iterator itCredito = listCredito.iterator(); itCredito.hasNext();){
			
			creditoDetalleInforme = new CreditoDetalleInforme();
			detalleCreditoCcafVO = (DetalleCreditoCcafVO) itCredito.next();
								
			creditoDetalleInforme.setCapital((int)detalleCreditoCcafVO.getCapital());
			creditoDetalleInforme.setCodigoOficina(detalleCreditoCcafVO.getCodigoOficina());
			creditoDetalleInforme.setEstadoCouta(detalleCreditoCcafVO.getEstadoActual());
			creditoDetalleInforme.setFechaVencimiento(creditoDetalleInforme.parseFechaVencimiento(detalleCreditoCcafVO.getFechaVencimiente()));
			creditoDetalleInforme.setFolioCredito(detalleCreditoCcafVO.getFolioCredito());
			creditoDetalleInforme.setGravamenes((int)detalleCreditoCcafVO.getGravamenes());
			creditoDetalleInforme.setIntereses((int)detalleCreditoCcafVO.getInteres());
			creditoDetalleInforme.setLineaCredito((int)detalleCreditoCcafVO.getLineaCredito());
			creditoDetalleInforme.setMontoAbonado((int)detalleCreditoCcafVO.getMontoAbonado());
			creditoDetalleInforme.setMontoDescuento((int)detalleCreditoCcafVO.getMontoDescuenro());
			creditoDetalleInforme.setMultas((int)detalleCreditoCcafVO.getMulta());
			creditoDetalleInforme.setNumCuota(detalleCreditoCcafVO.getNumCuata());
			creditoDetalleInforme.setSeguros((int)detalleCreditoCcafVO.getSeguros());
			creditoDetalleInforme.setTotalCoutas((int)detalleCreditoCcafVO.getTotalCouta());
			creditoDetalleInforme.setValorCouta((int)detalleCreditoCcafVO.getValorCuota());
			montoCredito= (int)detalleCreditoCcafVO.getMontoTotal();
			
			creditoDetalleInformeArray[contadorCredito] = creditoDetalleInforme;
			contadorCredito=contadorCredito+1;
		}
		if(montoCredito > 0){
			creditoInforme.setMonto(montoCredito);
			creditoInforme.setCreditoDetalle(creditoDetalleInformeArray);
			
			detalleCajaInforme.setCreditoInforme(creditoInforme);
		}
		
		List listLeasing = this.detalleLeasingCcaDAO.getDetalleLeasing(comprobante.getIdCodigoBarra());
		
			LeasingInforme leasingInforme = new LeasingInforme();
			LeasingDetalleInforme leasingDetalleInforme = null;
		
			DetalleLeasingCcafVO detalleLeasingCcafVO  = null;
			LeasingDetalleInforme[] leasingDetalleInformeArray = new LeasingDetalleInforme[listLeasing.size()];
			
			int montoLeasing = 0;
			int contadorLeasing =0;
			for (Iterator itLeasing = listLeasing.iterator(); itLeasing.hasNext();){
				
				leasingDetalleInforme = new LeasingDetalleInforme();
				
				detalleLeasingCcafVO = (DetalleLeasingCcafVO) itLeasing.next();
				
				leasingDetalleInforme.setCodigoOficina(detalleLeasingCcafVO.getCodigoOficina());
				leasingDetalleInforme.setFechaVencimiento(leasingDetalleInforme.parseFechaVencimiento(detalleLeasingCcafVO.getFechaVencimiento()));
				leasingDetalleInforme.setMonto((int)detalleLeasingCcafVO.getMonto());
				leasingDetalleInforme.setMontoUF(detalleLeasingCcafVO.getMontoUF());
				leasingDetalleInforme.setNumCuenta(detalleLeasingCcafVO.getNumCuenta());
				montoLeasing=montoLeasing+leasingDetalleInforme.getMonto();
				
				leasingDetalleInformeArray[contadorLeasing] = leasingDetalleInforme;
				contadorLeasing=contadorLeasing+1;
				
			}
			if(montoLeasing > 0){
				leasingInforme.setMonto(montoLeasing);
				leasingInforme.setLeasingDetalleInformes(leasingDetalleInformeArray);
			
				detalleCajaInforme.setLeasingInforme(leasingInforme);
			}
			
			return detalleCajaInforme;
	}

	public void validarDeclaracionesNoPagos(List consulta) throws DaoException{
		LineaComprobanteProvisorioVO comprobanteProvisorioVO = null;
		LineaComprobanteProvisorioVO comprobanteProvisorioDetalleVO = null;
		TipoSeccionVO tipoSeccion;
		
		for( Iterator it = consulta.iterator();it.hasNext();){
			
			comprobanteProvisorioVO = (LineaComprobanteProvisorioVO)it.next();
			
			tipoSeccion = this.tipoSeccionDao.getTipoSeccion(comprobanteProvisorioVO.getIdTipoSeccion());
			
				if(tipoSeccion.getClave().trim().equals("APV") || tipoSeccion.getClave().trim().equals("APVC") || 
						tipoSeccion.getClave().trim().equals("CAJA") || tipoSeccion.getClave().trim().equals("MUTUAL")){
					
					comprobanteProvisorioVO.setTipoPago(3);
				
				}else if(tipoSeccion.getClave().trim().equals("INP")){
					comprobanteProvisorioVO.setTipoPago(2);
					
				}else if(tipoSeccion.getClave().trim().equals("AFP") || tipoSeccion.getClave().trim().equals("ISAPRE")){
					
				}    
			for( Iterator itD = comprobanteProvisorioVO.getDetalle().iterator();itD.hasNext();){
					comprobanteProvisorioDetalleVO = (LineaComprobanteProvisorioVO)itD.next();
					
					if(tipoSeccion.getClave().trim().equals("APV") || tipoSeccion.getClave().trim().equals("APVC") || 
							tipoSeccion.getClave().trim().equals("CAJA") || tipoSeccion.getClave().trim().equals("MUTUAL")){
						
					}else if(tipoSeccion.getClave().trim().equals("INP")){
						comprobanteProvisorioVO.setTipoPago(2);
						comprobanteProvisorioDetalleVO.setTipoPago(2);
						
					}else if(tipoSeccion.getClave().trim().equals("AFP") || tipoSeccion.getClave().trim().equals("ISAPRE")){
						
						if(tipoSeccion.getDnp() == 1 && comprobanteProvisorioDetalleVO.getTipoPago() == 1 ){
							comprobanteProvisorioDetalleVO.setTipoPago(2);
						}
						if(tipoSeccion.getDnp() == 0 && comprobanteProvisorioDetalleVO.getTipoPago() == 1 ){
							comprobanteProvisorioDetalleVO.setTipoPago(3);
						}
					}	
				}
			}
		}
}
