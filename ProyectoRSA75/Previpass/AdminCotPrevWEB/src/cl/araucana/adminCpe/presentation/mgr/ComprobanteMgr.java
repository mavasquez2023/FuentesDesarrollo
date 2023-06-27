package cl.araucana.adminCpe.presentation.mgr;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;

import cl.araucana.adminCpe.hibernate.dao.ComprobanteDAO;
import cl.araucana.adminCpe.hibernate.dao.DetalleAporteCcafDAO;
import cl.araucana.adminCpe.hibernate.dao.DetalleCreditoCcafDAO;
import cl.araucana.adminCpe.hibernate.dao.DetalleLeasingCcaDAO;
import cl.araucana.adminCpe.hibernate.dao.DetalleReporteDAO;
import cl.araucana.adminCpe.hibernate.dao.EmpresaDAO;
import cl.araucana.adminCpe.hibernate.dao.MapeoTesoreriaDAO;
import cl.araucana.adminCpe.hibernate.dao.NominaDAO;
import cl.araucana.adminCpe.hibernate.dao.ParametroDAO;
import cl.araucana.adminCpe.utils.ProxyAS400;
import cl.araucana.core.business.BusinessException;
import cl.araucana.cp.distribuidor.base.Constants;
import cl.araucana.cp.distribuidor.base.ParametrosHash;
import cl.araucana.cp.distribuidor.base.Utils;
import cl.araucana.cp.distribuidor.hibernate.beans.ComprobanteVO;
import cl.araucana.cp.distribuidor.hibernate.beans.DetalleAporteCcafVO;
import cl.araucana.cp.distribuidor.hibernate.beans.DetalleCreditoCcafVO;
import cl.araucana.cp.distribuidor.hibernate.beans.DetalleLeasingCcafVO;
import cl.araucana.cp.distribuidor.hibernate.beans.EmpresaVO;
import cl.araucana.cp.distribuidor.hibernate.beans.NominaVO;
import cl.araucana.cp.distribuidor.hibernate.beans.PersonaVO;
import cl.araucana.cp.distribuidor.hibernate.beans.TipoNominaVO;
import cl.araucana.cp.distribuidor.hibernate.exceptions.DaoException;
import cl.araucana.cp.distribuidor.presentation.beans.AporteDetalleInforme;
import cl.araucana.cp.distribuidor.presentation.beans.AporteInforme;
import cl.araucana.cp.distribuidor.presentation.beans.CreditoDetalleInforme;
import cl.araucana.cp.distribuidor.presentation.beans.CreditoInforme;
import cl.araucana.cp.distribuidor.presentation.beans.DetalleCajaInforme;
import cl.araucana.cp.distribuidor.presentation.beans.LeasingDetalleInforme;
import cl.araucana.cp.distribuidor.presentation.beans.LeasingInforme;
import cl.araucana.cp.util.reporte.ConstructorPDF;

/*
 * @(#) ComprobanteMgr.java 1.11 10/05/2009
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */
/**
 * @author jdelgado
 * @author cchamblas
 * 
 * @version 1.11
 */
public class ComprobanteMgr
{
	private ComprobanteDAO comprobanteDAO;
	private ParametroDAO parametroDao;
	private NominaDAO nominaDao;
	private DetalleReporteDAO detalleReporteDAO;
	private EmpresaDAO empresaDao;
	private MapeoTesoreriaDAO mapeoTesoreriaDao;
	private DetalleAporteCcafDAO detalleAporteCcafDAO;
	private DetalleCreditoCcafDAO detalleCreditoCcafDAO;
	private DetalleLeasingCcaDAO detalleLeasingCcaDAO;
	
	static Logger log = Logger.getLogger(ComprobanteMgr.class);

	public ComprobanteMgr(Session session)
	{
		this.comprobanteDAO = new ComprobanteDAO(session);
		this.parametroDao = new ParametroDAO(session);
		this.nominaDao = new NominaDAO(session);
		this.detalleReporteDAO = new DetalleReporteDAO(session);
		this.empresaDao = new EmpresaDAO(session);
		this.mapeoTesoreriaDao = new MapeoTesoreriaDAO(session);
		this.detalleAporteCcafDAO=new DetalleAporteCcafDAO(session);
		this.detalleCreditoCcafDAO=new DetalleCreditoCcafDAO(session);
		this.detalleLeasingCcaDAO=new DetalleLeasingCcaDAO(session);
	}

	/**
	 * comprobante pago
	 * 
	 * @param idCodigoBarra
	 * @return
	 * @throws DaoException
	 */

	public ComprobanteVO getComprobantePago(long idCodigoBarra) throws DaoException
	{
		return this.comprobanteDAO.getComprobantePago(idCodigoBarra);
	}

	public HashMap getEstadosCmps() throws DaoException
	{
		return this.comprobanteDAO.getEstadosCmps();
	}

	/**
	 * lista comprobante pago
	 * 
	 * @return
	 * @throws DaoException
	 */
	public List getListaComprobantePago() throws DaoException
	{
		return this.comprobanteDAO.getListaComprobantePago();
	}

	/**
	 * lista comprobante pago estado
	 * 
	 * @param estados
	 * @return
	 * @throws DaoException
	 */

	public List getListaComprobantePagoByEstado(List estados) throws DaoException
	{
		return this.comprobanteDAO.getListaComprobantePagoByEstado(estados);
	}

	/**
	 * lista comprobante pago SPL
	 * 
	 * @return
	 * @throws DaoException
	 */
	public List getListaComprobanteSpl() throws DaoException
	{
		return this.comprobanteDAO.getListaComprobanteSpl();
	}

	/**
	 * @param folio
	 * @throws DaoException
	 * @throws Exception
	 * @throws BusinessException
	 */
	private void anulaFolio(long folio, ParametrosHash paramHash) throws DaoException, Exception
	{
		String result = ProxyAS400.anularComprobante(folio, paramHash);
		if (!result.trim().equals(""))
			throw new Exception("problemas al anular folio:" + result.trim());
	}

	/**
	 * anula folio No pagados
	 * 
	 * @throws DaoException
	 * @throws Exception
	 */
	public void anulaFoliosNoPagados() throws DaoException, Exception
	{
		List cmpsNoPagados = this.comprobanteDAO.getCmpsNoPagados();
		List listaParams = new ArrayList();
		listaParams.add(new Integer(Constants.PARAM_INT_TES_URL_SISTEMA));
		listaParams.add(new Integer(Constants.PARAM_INT_TES_USER_CONN));
		listaParams.add(new Integer(Constants.PARAM_INT_TES_PASS_CONN));
		listaParams.add(new Integer(Constants.PARAM_LIB_ANULA_FOLIO));
		ParametrosHash paramHash = this.parametroDao.getParametrosHash(listaParams);
		//GMALLEA 26-04-2012 Se actualizan todos los comprobantes de la tabla ya que queda solo los independientes
		
		this.detalleAporteCcafDAO.eliminAporteCcafTodo();
		this.detalleCreditoCcafDAO.eliminaCreditoCcafTodo();
		this.detalleLeasingCcaDAO.eliminLeasingCcafTodo();
		
		for (Iterator it = cmpsNoPagados.iterator(); it.hasNext();)
		{
			ComprobanteVO comprobante = (ComprobanteVO) it.next();
			log.info("Proceso de Cierre Comprobante:" + comprobante.getFolioTesoreria() + "::" + comprobante.getIdCodigoBarra() + "::");
			
				try
				{
						//TODO GMALLEA CIERRE PERIODO FOLIO
						NominaVO nominaVO = this.comprobanteDAO.getNomina(comprobante.getIdCodigoBarra());
						//TODO GMALLEA Si la nomina es null significa que es empresa porque ya se borraron todas las nominas de tipo empresa
						if(nominaVO == null){
							anulaFolio(comprobante.getFolioTesoreria(), paramHash);
							comprobante.setFolioTesoreria(0);
							this.comprobanteDAO.guardaComprobante(comprobante);
						}else{
							
							if(comprobante.getFolioTesoreria() > 0  && 
								!String.valueOf(comprobante.getIdEstado()).equals(String.valueOf(Constants.EST_CMP_PAGO_PARCIAL)) && 
								!String.valueOf(comprobante.getIdEstado()).equals(String.valueOf(Constants.EST_CMP_PAGADO))){
								
								log.info("Se anula el : Folio = " +comprobante.getFolioTesoreria() +" Id_Estado = "+comprobante.getIdEstado());
								
									anulaFolio(comprobante.getFolioTesoreria(), paramHash);
							}else{
								log.info("No se pudo anular el folio ya el comprobante no cumple las condiciones: Folio = " +comprobante.getFolioTesoreria() +
										" Id_Estado = "+comprobante.getIdEstado());
							}
							List listSecciones = this.comprobanteDAO.getSecciones(comprobante.getIdCodigoBarra());
							List listConfigPDFVO = this.comprobanteDAO.getConfigPDFVO(comprobante.getIdCodigoBarra());
							
							//this.comprobanteDAO.borraSecciones(comprobante.getIdCodigoBarra());
							//jlandero - asepulveda cambios total independiente											 
							 ComprobanteVO comprobanteActualizado =  this.comprobanteDAO.guardaComprobante(comprobante.getIdDocumento(), listSecciones, listConfigPDFVO, comprobante.getTotal());
							
							 this.nominaDao.actualizaNominaRe(comprobanteActualizado.getIdCodigoBarra(), nominaVO.getRutEmpresa(), nominaVO.getIdConvenio());
							 
							 this.comprobanteDAO.eliminarComprobante(comprobante, listConfigPDFVO);
						}
					
				} catch (Exception e)
				{	
					log.error("\n\nProblemas al anular folio en tesoreria:" + comprobante.getFolioTesoreria() + "::" + e.getMessage() + "::");
				}
			}
			
		log.info("fin anulacion folios:::");
	}

	// TODO codigo copiado desde cliente
	/**
	 * lista detalle reporte
	 * 
	 * @param idTipo
	 * @return
	 * @throws DaoException
	 */
	public List getDetalleReporte(int idTipo) throws DaoException
	{
		return this.detalleReporteDAO.getLista(idTipo);
	}

	/**
	 * collection tipos proceso
	 * 
	 * @return
	 * @throws DaoException
	 */
	public List getTiposProceso() throws DaoException
	{
		return (List) this.nominaDao.getTiposNominas();
	}

	/**
	 * generar archivos
	 * 
	 * @param listaIds
	 * @return
	 * @throws Exception
	 */
	public String generarArchivos(List listaIds, String tipoEmpresa) throws Exception
	{
		List listaComprobantes = this.comprobanteDAO.getComprobantes(listaIds);
		List listaTipoSeccion = this.mapeoTesoreriaDao.getTipoSeccion();
		List listaDetReporte = getDetalleReporte(Constants.DET_REPORTE_PDF);
		HashMap listaLeyendas = this.detalleReporteDAO.getPropertiesMapeo(Constants.PROP_MAPEO_MX_LEYENDA);
		HashMap tipoNominas = new HashMap();
		for (Iterator it = getTiposProceso().iterator(); it.hasNext();)
		{
			TipoNominaVO tn = (TipoNominaVO) it.next();
			tipoNominas.put(tn.getIdTipoNomina(), tn.getDescripcion().trim());
		}

		List listaParams = new ArrayList();
		listaParams.add(new Integer(Constants.PARAM_TASA_FIJA));
		listaParams.add(new Integer(Constants.PARAM_PERIODO));
		listaParams.add(new Integer(Constants.PARAM_PERIODO_INDEPENDIENTE));
		listaParams.add(new Integer(Constants.PARAM_FIN_PAGO_CAJA_IND));
		listaParams.add(new Integer(Constants.PARAM_FIN_PAGO_CAJA));
		//TODO revisar generacion cmp app admin!
		/*listaParams.add(new Integer(Constants.PARAM_PUBLISHER_NAME));
		listaParams.add("docTypeName");
		listaParams.add("idLogPDF");*/
		cl.araucana.cp.distribuidor.base.ParametrosHash paramHash = this.parametroDao.getParametrosHash(listaParams);
		ConstructorPDF constructor = new ConstructorPDF(paramHash, tipoNominas, listaTipoSeccion, listaDetReporte, listaLeyendas);
		HashMap listaEmpresas = new HashMap();
		HashMap listaRepresentantes = new HashMap();
		HashMap listaConvenios = new HashMap();
		HashMap listaNominas = new HashMap();
		HashMap listadetalleCajaInforme = new HashMap();
		String nombreArchivo = "";
		for (Iterator it = listaComprobantes.iterator(); it.hasNext();)
		{
			ComprobanteVO comprobante = (ComprobanteVO) it.next();
			this.comprobanteDAO.loadConfigPDF(comprobante.getSecciones());
			log.info("ComprobanteMgr: comprobante_idDocumento:" + comprobante.getIdDocumento() + "::");
			NominaVO nomina = getNomina(comprobante.getIdCodigoBarra());
			int idConvenio = nomina.getIdConvenio();
			char tipoProceso = nomina.getTipoProceso();
			int rutEmpresa = nomina.getRutEmpresa();
			listaNominas.put("" + comprobante.getIdCodigoBarra(), nomina);
			EmpresaVO empresa = this.empresaDao.getEmpresaCasaMatriz(rutEmpresa);
			listaEmpresas.put("" + empresa.getIdEmpresa(), empresa);
			PersonaVO representante = this.empresaDao.getRepresentante(Long.parseLong(String.valueOf(empresa.getIdRepLegal())));
			listaRepresentantes.put(String.valueOf(empresa.getIdEmpresa()), representante);
			listaConvenios.put(rutEmpresa + "#" + idConvenio, this.comprobanteDAO.getConvenio(rutEmpresa, idConvenio));

			if (listaComprobantes.size() > 1 && "".equals(nombreArchivo))
				nombreArchivo = Constants.RUTA_CMPS + "Comprobante_" + tipoProceso + "_" + paramHash.get("" + Constants.PARAM_PERIODO) + ".pdf";
			else if ("".equals(nombreArchivo))
				nombreArchivo = Constants.RUTA_CMPS + "Comprobante" + Utils.formatRut(rutEmpresa) + "_" + tipoProceso + "_"
						+ (idConvenio < 10 ? "0" : "") + idConvenio + "_" + paramHash.get("" + Constants.PARAM_PERIODO) + ".pdf";
			
//			Se rescatan los valores de la base de datos ya que el comprobante se encuentra pagado	
			DetalleCajaInforme detalleCajaInforme  = this.detalleCcafByDB2(comprobante);
		
			listadetalleCajaInforme.put(""+empresa.getIdEmpresa(), detalleCajaInforme);

		}
		boolean result = constructor.createPDFtoPago(true, nombreArchivo, listaComprobantes, listaEmpresas, listaNominas, listaRepresentantes, listaConvenios,tipoEmpresa,listadetalleCajaInforme);
		if (!result)
			throw new Exception("ERROR en la generacion de PDF para pago por caja::");
		return nombreArchivo;
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
		return this.comprobanteDAO.getNomina(codigoBarra);
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
	/**
	 * Actualiza los comprobantes entregados en una lista de codigos de barra y anula el folio para las empresas
	 * 
	 * @author gmallea
	 * 
	 * @param List listCodigoBarra
	 * @return
	 * @throws DaoException 
	 * @throws Exception
	 */
	public void actualizaComprobanteEmpresa(List listCodigoBarra) throws DaoException
	{
		List cmpsNoPagados = this.comprobanteDAO.getCmpsNoPagadosEmpresaEIndependiente(listCodigoBarra);
		List listaParams = new ArrayList();
		listaParams.add(new Integer(Constants.PARAM_INT_TES_URL_SISTEMA));
		listaParams.add(new Integer(Constants.PARAM_INT_TES_USER_CONN));
		listaParams.add(new Integer(Constants.PARAM_INT_TES_PASS_CONN));
		listaParams.add(new Integer(Constants.PARAM_LIB_ANULA_FOLIO));
		ParametrosHash paramHash = this.parametroDao.getParametrosHash(listaParams);
		
		try{
			for (Iterator it = cmpsNoPagados.iterator(); it.hasNext();)
			{
				ComprobanteVO comprobante = (ComprobanteVO) it.next();
				log.info("Proceso de Cierre Comprobante:" + comprobante.getFolioTesoreria() + "::" + comprobante.getIdCodigoBarra() + "::");
								try{
									anulaFolio(comprobante.getFolioTesoreria(), paramHash);
								}catch (Exception e) {
									log.error("Error en ComprobanteMgr.actualizaComprobanteEmpresa anulafolio ", e);
								}
								comprobante.setFolioTesoreria(0);
								this.comprobanteDAO.guardaComprobante(comprobante);
				}
		} catch (Exception ex)
		{
			log.error("Error en ComprobanteMgr.actualizaComprobanteEmpresa", ex);
			log.info("No se pudo anular el folio :" + ex);
		}
	}
	
	/**
	 * Actualiza los comprobantes entregados en una lista de codigos de barra y anula el folio para los independientes
	 * 
	 * @author gmallea
	 * 
	 * @param List listCodigoBarra
	 * @return
	 * @throws DaoException 
	 * @throws Exception
	 */
	public void actualizaComprobanteIndependiente(List listCodigoBarra) throws DaoException
	{
		
		List cmpsNoPagados = this.comprobanteDAO.getCmpsNoPagadosEmpresaEIndependiente(listCodigoBarra);
		List listaParams = new ArrayList();
		listaParams.add(new Integer(Constants.PARAM_INT_TES_URL_SISTEMA));
		listaParams.add(new Integer(Constants.PARAM_INT_TES_USER_CONN));
		listaParams.add(new Integer(Constants.PARAM_INT_TES_PASS_CONN));
		listaParams.add(new Integer(Constants.PARAM_LIB_ANULA_FOLIO));
		ParametrosHash paramHash = this.parametroDao.getParametrosHash(listaParams);

		this.detalleAporteCcafDAO.eliminAporteCcafTodo();
		this.detalleCreditoCcafDAO.eliminaCreditoCcafTodo();
		this.detalleLeasingCcaDAO.eliminLeasingCcafTodo();
		
		for (Iterator it = cmpsNoPagados.iterator(); it.hasNext();)
		{
			ComprobanteVO comprobante = (ComprobanteVO) it.next();
			log.info("Proceso de Cierre Comprobante:" + comprobante.getFolioTesoreria() + "::" + comprobante.getIdCodigoBarra() + "::");
			
				try
				{
						NominaVO nominaVO = this.comprobanteDAO.getNomina(comprobante.getIdCodigoBarra());
					
						if(nominaVO != null){
							
							if(comprobante.getFolioTesoreria() > 0  && 
								!String.valueOf(comprobante.getIdEstado()).equals(String.valueOf(Constants.EST_CMP_PAGO_PARCIAL)) && 
								!String.valueOf(comprobante.getIdEstado()).equals(String.valueOf(Constants.EST_CMP_PAGADO))){
								
								log.info("Se anula el : Folio = " +comprobante.getFolioTesoreria() +" Id_Estado = "+comprobante.getIdEstado());
									try{//gmallea try se se caee agregar ultima version
										anulaFolio(comprobante.getFolioTesoreria(), paramHash);
									}catch (Exception e) {
										log.error("Error en ComprobanteMgr.actualizaComprobanteIndependiente anulafolio ", e);
									}
							}else{
								log.info("No se pudo anular el folio ya el comprobante no cumple las condiciones: Folio = " +comprobante.getFolioTesoreria() +
										" Id_Estado = "+comprobante.getIdEstado());
							}
							List listSecciones = this.comprobanteDAO.getSecciones(comprobante.getIdCodigoBarra());
							List listConfigPDFVO = this.comprobanteDAO.getConfigPDFVO(comprobante.getIdCodigoBarra());
							
							 ComprobanteVO comprobanteActualizado =  this.comprobanteDAO.guardaComprobante(comprobante.getIdDocumento(), listSecciones, listConfigPDFVO, comprobante.getTotal());
							
							 this.nominaDao.actualizaNominaRe(comprobanteActualizado.getIdCodigoBarra(), nominaVO.getRutEmpresa(), nominaVO.getIdConvenio());
							 
							 this.comprobanteDAO.eliminarComprobante(comprobante, listConfigPDFVO);
						}
					
				} catch (Exception ex)
				{	
					log.error("Error en ComprobanteMgr.actualizaComprobanteIndependiente", ex);
					throw new DaoException("Error en ComprobanteMgr.actualizaComprobanteIndependiente", ex);
				}
			}
		}
}
