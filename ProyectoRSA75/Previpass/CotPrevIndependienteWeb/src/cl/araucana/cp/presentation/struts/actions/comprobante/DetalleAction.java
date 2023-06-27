package cl.araucana.cp.presentation.struts.actions.comprobante;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.axis.client.Stub;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.action.ActionRedirect;
import org.apache.struts.util.LabelValueBean;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import cl.araucana.cp.distribuidor.base.Constants;
import cl.araucana.cp.distribuidor.base.Utils;
import cl.araucana.cp.distribuidor.hibernate.beans.ComprobanteVO;
import cl.araucana.cp.distribuidor.hibernate.beans.ConvenioVO;
import cl.araucana.cp.distribuidor.hibernate.beans.CotizacionREVO;
import cl.araucana.cp.distribuidor.hibernate.beans.DetalleSeccionVO;
import cl.araucana.cp.distribuidor.hibernate.beans.EmpresaVO;
import cl.araucana.cp.distribuidor.hibernate.beans.NominaREVO;
import cl.araucana.cp.distribuidor.hibernate.beans.NominaVO;
import cl.araucana.cp.distribuidor.hibernate.beans.ParametroVO;
import cl.araucana.cp.distribuidor.hibernate.beans.PersonaVO;
import cl.araucana.cp.distribuidor.hibernate.beans.SeccionVO;
import cl.araucana.cp.distribuidor.hibernate.beans.TipoDetalleVO;
import cl.araucana.cp.distribuidor.hibernate.beans.TipoSeccionVO;
import cl.araucana.cp.distribuidor.hibernate.exceptions.DaoException;
import cl.araucana.cp.hibernate.dao.DetalleAporteCcafDAO;
import cl.araucana.cp.hibernate.dao.DetalleCreditoCcafDAO;
import cl.araucana.cp.hibernate.dao.DetalleLeasingCcaDAO;
import cl.araucana.cp.hibernate.dao.ParametrosDAO;
import cl.araucana.cp.hibernate.utils.HibernateUtil;
import cl.araucana.cp.presentation.base.AppAction;
import cl.araucana.cp.presentation.mgr.ComprobanteMgr;
import cl.araucana.cp.presentation.mgr.ConvenioMgr;
import cl.araucana.cp.presentation.mgr.EmpresaMgr;
import cl.araucana.cp.presentation.mgr.ParametroMgr;
import cl.araucana.cp.presentation.mgr.TipoSeccionMgr;
import cl.araucana.cp.presentation.mgr.UsuarioMgr;
import cl.araucana.cp.presentation.struts.forms.AporteDetalleFormVO;
import cl.araucana.cp.presentation.struts.forms.CreditoDetalleFormVO;
import cl.araucana.cp.presentation.struts.forms.LeasingDetalleFormVO;
import cl.araucana.cp.presentation.struts.forms.comprobante.DetalleActionForm;
import cl.araucana.cp.presentation.struts.javaBeans.DetalleSeccion;
import cl.araucana.cp.presentation.struts.javaBeans.Seccion;
import cl.araucana.cp.util.vo.DetalleReporteVO;
import cl.araucana.cp.webServices.aporte.orqInput.service.OrqInputServiceImpl;
import cl.araucana.cp.webServices.aporte.orqInput.service.OrqInputServiceImplServiceLocator;
import cl.araucana.cp.webServices.aporte.orqInput.service.vo.OrqInputResultVO;

import com.bh.talon.Message;
import com.bh.talon.MessageList;
import com.bh.talon.User;
/*
* @(#) DetalleAction.java 1.24 10/05/2009
*
* Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
* La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
* restringido a los sistemas de información propios de la institución.
*/
/**
 * @author cchamblas
 *
 * @version 1.24
 */
public class DetalleAction extends AppAction
{
	public static final String FORWARD = "comprobanteFicha";
	public static final String PAGO = "pago";
	static Logger logger = Logger.getLogger(DetalleAction.class);

	public DetalleAction()
	{
		super();
		this.btns.add("filtro");
		this.btns.add("imprimir");
		this.btns.add("imprimirResumen");
		this.btns.add("pagoCaja");
		this.btns.add("pagoLinea");
	}

	/**
	 * Procesa el request para generar la respuesta html que se le entregara
	 * al cliente.
	 * <p>
	 * Los parametros de <code>request</code> que se deben agregar al llamar a este Action son
	 * los siguientes:
	 * <dl>
	 * <dt>accion</dt>
	 * <dd>inicio</dd>
	 * <dt>subAccion</dt>
	 * <dd>procesos</dd>
	 * <dt>subSubAccion</dt>
	 * <dd>comprobanteFicha</dd>
	 * <dt>idCodigoBarras</dt>
	 * <dd>El codigo de barras del comprobante que se quiere mostrar.</dd>
	 * <dt>codigoBarra</dt>
	 * <dd>El codigo de barras del comprobante que se quiere mostrar. Puede ir este o el anterior.</dd>
	 * <dt>operacion</dt>
	 * <dd>Si es "Pago en Línea", se redirecciona al pago en linea del comprobante.
	 * Si es "Pagar en Caja", se redirecciona al pago por caja del comprobante.
	 * Si es "Búsqueda", se actualiza la consulta con el valor de los combos.</dd>
	 * </dl>
	 *
	 * @param	usuario		el usuario que esta loggeado en la sesion en cuyo contexto se llama a este metodo
	 * @param	mapping		el objeto con los mapeos de accion para este <code>Action</code>
	 * @param	form		el objeto <code>ActionForm</code> correspondiente
	 * @param	request		el objeto <code>request</code> con los parametros para procesar
	 * @param	response	el objeto <code>response</code> con la respuesta al cliente
	 * @return	el mapeo de accion correspondiente
	 */
	protected ActionForward doTask(User user, ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		try
		{
			String idCodigoBarras = "";
			String operacion = "";
			if (request.getParameter("operacion") != null)
				operacion = request.getParameter("operacion");
			logger.info("DetalleAction:operacion:" + operacion + "::");
			Session session = HibernateUtil.getSession();

			DetalleActionForm formulario = (DetalleActionForm)form;
			if (request.getParameter("idCodigoBarras") != null && !request.getParameter("idCodigoBarras").equals(""))
				idCodigoBarras = request.getParameter("idCodigoBarras");
			else if (request.getParameter("codigoBarra") != null && !request.getParameter("codigoBarra").equals(""))
				idCodigoBarras = request.getParameter("codigoBarra");
			//PAGO!!
			if (Utils.codificaAcentos(operacion).equals(Constants.TXT_BTNS.getProperty("pagoLinea")) || operacion.equals(Constants.TXT_BTNS.getProperty("pagoLinea")))//redireccion a pago SPL
			{
				ActionRedirect redirect = new ActionRedirect(mapping.findForward(PAGO));
			    redirect.addParameter("operacion", "SPL");
			    redirect.addParameter("accion", this.accion);
			    redirect.addParameter("subAccion", this.subAccion);
			    redirect.addParameter("codigos", idCodigoBarras);
			    redirect.addParameter("rut", "" + formulario.getRutEmpresa());
			    request.setAttribute("cambioParam", "accion=inicio&subAccion=procesos&subSubAccion=comprobanteFicha&idCodigoBarras=" + idCodigoBarras);

		        return redirect;
			} else if (Utils.codificaAcentos(operacion).equals(Constants.TXT_BTNS.getProperty("pagoCaja")))//redireccion a pago caja (generacion PDF)
			{logger.info("Entro a Pagar Por Caja DetalleAction.java");
				ActionRedirect redirect = new ActionRedirect(mapping.findForward(PAGO));
			    redirect.addParameter("operacion", "Pagar en Caja");
			    redirect.addParameter("accion", this.accion);
			    redirect.addParameter("subAccion", this.subAccion);
			    redirect.addParameter("codigos", idCodigoBarras);
			    redirect.addParameter("rut", "" + formulario.getRutEmpresa());
			    request.setAttribute("cambioParam", "accion=inicio&subAccion=procesos&subSubAccion=comprobanteFicha&idCodigoBarras=" + idCodigoBarras);
		        return redirect;
			}
			int rutEmpresa = formulario.getRutEmpresa();
			int idConvenio = formulario.getConvenio();
			char tipoProceso = formulario.getTipoProceso();
			int nivelPermiso = 0;
			int idPersona = ((PersonaVO)user.getUserReference()).getIdPersona().intValue();
			ComprobanteVO comprobante = null;

			//busca informacion para fichaComprobante
			UsuarioMgr usuarioMgr = new UsuarioMgr(session);
			ComprobanteMgr comprobanteMgr = new ComprobanteMgr(session);
			EmpresaMgr empresaMgr = new EmpresaMgr(session);
			TipoSeccionMgr tipoSeccionMgr = new TipoSeccionMgr(session);
			ParametroMgr parametroMgr = new ParametroMgr(session);

			if (Utils.codificaAcentos(operacion).equals(Constants.TXT_BTNS.getProperty("filtro")) || operacion.equals(Constants.TXT_BTNS.getProperty("filtro")))//FILTRO!!
			{
				logger.info("buscando cmp por nomina:" + tipoProceso + "::" + idConvenio + "::" + rutEmpresa + "::");
				nivelPermiso = usuarioMgr.getNivelPermiso(idPersona, idConvenio, rutEmpresa);
				if (nivelPermiso == Constants.NIVEL_ACC_CONV_SUC_EDITOR || nivelPermiso == Constants.NIVEL_ACC_CONV_SUC_LECTOR)
					comprobante = comprobanteMgr.getCmpPorPagar(idConvenio, "" + tipoProceso, "" + rutEmpresa);
			} else
			{
				logger.info("buscando cmp por cod barras:" + idCodigoBarras + "::");
				comprobante = comprobanteMgr.getCmpPorPagar(new Long(idCodigoBarras));
				if (comprobante != null)
				{
					logger.info("encontro cmp");
					NominaVO nomina = comprobanteMgr.getNomina(comprobante.getIdCodigoBarra());
					if (nomina != null)
					{
						logger.info("con nomina asociada");
						rutEmpresa = nomina.getRutEmpresa();
						idConvenio = nomina.getIdConvenio();
						nivelPermiso = usuarioMgr.getNivelPermiso(idPersona, idConvenio, rutEmpresa);
						if (nivelPermiso != Constants.NIVEL_ACC_CONV_SUC_EDITOR && nivelPermiso != Constants.NIVEL_ACC_CONV_SUC_LECTOR)
							comprobante = null;
						tipoProceso = nomina.getTipoProceso();
						formulario.setRutEmpresa(rutEmpresa);
						formulario.setConvenio(idConvenio);
						formulario.setTipoProceso(tipoProceso);
						logger.info("encontrada nomina:" + tipoProceso + "::" + idConvenio + "::" + rutEmpresa + "::");
					}
				}
			}
			formulario.setTiposProcesos(comprobanteMgr.getTiposProceso());
			if (nivelPermiso == Constants.NIVEL_ACC_CONV_SUC_NADA || comprobante == null)//si no encontro comprobante => envia mensaje
				return retornaMsgError(user, mapping, request, idCodigoBarras, formulario, rutEmpresa, idConvenio, nivelPermiso, empresaMgr);

			logger.info("\nnivel permiso:" + nivelPermiso + "::");
			if (nivelPermiso == Constants.NIVEL_ACC_CONV_SUC_EDITOR)
				formulario.setPuedePagar(1);
			else
				formulario.setPuedePagar(0);

			HashMap combos = empresaMgr.getCombosPermisos(false, ((PersonaVO)user.getUserReference()).getIdPersona().intValue(), rutEmpresa, idConvenio);
			if (combos == null)
			{
				ActionMessages am = new ActionMessages();
				am.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("permisos.generic"));
				this.saveMessages(request, am);
				return mapping.findForward("error");
			}
			formulario.setEmpresas((List)combos.get("empresas"));
			formulario.setObjEmpresas((List)combos.get("objEmpresas"));
			EmpresaVO empresa = (EmpresaVO)combos.get("empresa");
			formulario.setRazonSocial(empresa.getRazonSocial());
			formulario.setRutEmpresa(rutEmpresa);
			formulario.setRutEmpresaFormat(Utils.formatRut(empresa.getIdEmpresa()));

			if (comprobante.getTotal() == 0 && !comprobante.tieneAlgoParaPago())
			{
				logger.info("comprobante sin monto para pago (total = 0)");
				ActionMessages am = new ActionMessages();
				am.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("error.cmpSinMontoAPagar"));
				this.saveMessages(request, am);
				return mapping.findForward(FORWARD);
			}

			HashMap listaTipoSeccion = tipoSeccionMgr.getTiposSecciones();
			List listaDetReporte = comprobanteMgr.getDetalleReporte(Constants.DET_REPORTE_HTML);
			formulario.setSecciones(generaSecciones(tipoProceso, comprobante.getSecciones(), listaTipoSeccion, listaDetReporte, parametroMgr.getParam(Constants.PARAM_TASA_FIJA)));

			formulario.setTotalCmp(Utils.formatMonto(comprobante.getTotal()));
			formulario.setTotalLong(comprobante.getTotal());
			formulario.setIdCodigoBarras("" + comprobante.getIdCodigoBarra());
			if(formulario.getEstadoImpresion()!= null)
			{
				if(formulario.getEstadoImpresion().equals("OK"))
				{
					logger.info("\nenvia a imprimir");
					return mapping.findForward("imprimir");
				}
			}
			
			ActionRedirect actionRedirect=this.validaCamposCCAF(session, formulario, mapping, request);
			if(actionRedirect != null)
				return actionRedirect;
			
			
			return mapping.findForward(FORWARD);
		} catch (Exception e)
		{
			logger.error("DetalleAction::", e);
			MessageList l = new MessageList();
			l.add(	new Message( "Ha ocurrido un Error", e.getMessage()));
			request.setAttribute("messageList", l);
			return mapping.findForward(PARAM_ERROR);
		}
	}

	private ActionForward retornaMsgError(User user, ActionMapping mapping, HttpServletRequest request, String idCodigoBarras, DetalleActionForm formulario, int rutEmpresa, int idConvenio, int nivelPermiso, EmpresaMgr empresaMgr) throws DaoException
	{
		ActionMessages am = new ActionMessages();
		HashMap combos = empresaMgr.getCombosPermisos(true, ((PersonaVO)user.getUserReference()).getIdPersona().intValue(), rutEmpresa, idConvenio);
		if (combos == null)
		{
			am.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("permisos.generic"));
			this.saveMessages(request, am);
			return mapping.findForward("error");
		}
		formulario.setEmpresas((List)combos.get("empresas"));
		formulario.setObjEmpresas((List)combos.get("objEmpresas"));
		EmpresaVO empresa = (EmpresaVO)combos.get("empresa");
		formulario.setRazonSocial(empresa.getRazonSocial());
		formulario.setRutEmpresa(rutEmpresa);
		formulario.setRutEmpresaFormat(Utils.formatRut(empresa.getIdEmpresa()));

		if (rutEmpresa > 0 && nivelPermiso == Constants.NIVEL_ACC_CONV_SUC_NADA)
		{
			am.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("permisos.cmp", idCodigoBarras +" "));
			this.saveMessages(request, am);
			return mapping.findForward("error");
		}

		logger.info("comprobante nulo");
		am.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("error.sinComprobantePorPagar"));
		formulario.setSecciones(null);
		this.saveMessages(request, am);
		return mapping.findForward(FORWARD);
	}

	public List generaSecciones(char tipoProceso, List seccionesVOS, HashMap listaTipoSeccion, List listaDetReporte, String tasaFija) throws Exception
	{
		List seccionesBeans = new ArrayList();

		for (Iterator it = seccionesVOS.iterator(); it.hasNext();)
		{
			long totalSeccion = 0;
			SeccionVO seccionVO = (SeccionVO)it.next();

			if (seccionVO.getTipoPago() == Constants.EST_SECCION_NO_PAGO || seccionVO.getTipoPago() == Constants.EST_SECCION_DNP)//seccion no se incluye!
				continue;

			TipoSeccionVO ts = (TipoSeccionVO)listaTipoSeccion.get("" + seccionVO.getIdTipoSeccion());
			logger.debug("seccion:" + seccionVO.getIdTipoSeccion() + ":" + ts.getClave() + "::");
			DetalleReporteVO dr = getDetalleReporte(seccionVO.getIdTipoSeccion(), listaDetReporte);
			ts.refreshListasM();
			int montosM = 0;
			for (int i = 11; i > 0; i--)
			{
				//Asepulveda 06-07-2010
				//if (!ts.getM(i).trim().equals(""))				
				if (!ts.getM(i).trim().equals("") && !ts.getM(i).trim().equals("SIS")) //SIS es el nombre de M12 en la tabla tipo_seccion para el tipo_seccion 1
				{
					montosM = i;
					break;
				}
			}
			dr.generaArray();
			ts.refreshListasM();

			//Se cambió ts.getClave() por ts.getDescripcion()
			Seccion secc = new Seccion("" + seccionVO.getIdTipoSeccion(), ts.getDescripcion().trim());
			List listaDetalle = new ArrayList();

			for (Iterator it2 = seccionVO.getDetallesSeccion().iterator(); it2.hasNext();)
			{
				long totalDetalle = 0;
				DetalleSeccionVO detSeccVO = (DetalleSeccionVO)it2.next();
				if (ts.getPagoIndividual() == 1 &&
						(detSeccVO.getTipoPago() == Constants.EST_SECCION_NO_PAGO || detSeccVO.getTipoPago() == Constants.EST_SECCION_DNP))
					continue;
				detSeccVO.refreshListaM();
				logger.debug("\n\nbuscando seccion:" + seccionVO.getIdTipoSeccion() + ":");
				TipoDetalleVO tipoDetalle = getTipoDetalle(detSeccVO.getIdDetalleSeccion(), ts.getTipoDetalle());
				if (tipoDetalle == null)
					continue;
				DetalleSeccion detalle = new DetalleSeccion("" + detSeccVO.getIdDetalleSeccion(), tipoDetalle.getNombre());
				int[] collsToShow = dr.generaArray();

				if (secc.getNombre().equals("MUTUAL"))
				{
					totalDetalle = setMutual(detalle, detSeccVO, collsToShow[0], tasaFija);
					totalSeccion += totalDetalle;
				} else
				{
					int i = 0;
					List detalles = new ArrayList();
					while (collsToShow[i] >= 0)
					{
						int pos = collsToShow[i++];
						detalles.add(new LabelValueBean(ts.getNombre(pos), Utils.formatMonto((long)detSeccVO.getM(pos))));
					}
					detalle.setListaDetalles(detalles);
					 //caso especial depósito, sumar depósito e indemnizaciones
					if (tipoProceso == Constants.TIPO_NOM_DEPOSITOCONVENIDO || (detSeccVO.getIdTipoSeccion() == Constants.SECCION_DEPOSITOCONVENIDO && tipoProceso == Constants.TIPO_NOM_REMUNERACION))
						totalDetalle = (long)detSeccVO.getM(montosM) + (long)detSeccVO.getM(montosM - 1);
					else
						totalDetalle = (long)detSeccVO.getM(montosM);
					detalle.setTotal(Utils.formatMonto(totalDetalle));
					totalSeccion += totalDetalle;
				}

				listaDetalle.add(detalle);
			}
			secc.setDetalles(listaDetalle);
			secc.setTotal(Utils.formatMonto(totalSeccion));
			if (totalSeccion != 0 || seccionVO.getIdTipoSeccion() == Constants.ID_TIPO_SECCION_CAJA || (seccionVO.getIdTipoSeccion() == Constants.ID_TIPO_SECCION_INP && seccionVO.getNumTrabajadores()>0))
				seccionesBeans.add(secc);
		}
		return seccionesBeans;//this.paramSPL.get("tasaFija")
	}

	/**
	 * mutual
	 * @param detalle
	 * @param detSeccVO
	 * @param pos
	 * @param tasaFija
	 * @return
	 */
	public long setMutual(DetalleSeccion detalle, DetalleSeccionVO detSeccVO, int pos, String tasaFija)
	{
		List detalles = new ArrayList();
		double total = detSeccVO.getM(pos);
		double tasaTotal= (detSeccVO.getM(0) / 100);
		double tasaObligatoria= Double.parseDouble(tasaFija);
		float tasaAdicional= (float)(tasaTotal-tasaObligatoria);
		detalles.add(new LabelValueBean("Tasa Fija " + tasaObligatoria + " Tasa Adicional " + tasaAdicional, Utils.formatMonto((long)total)));
		detalle.setListaDetalles(detalles);
		detalle.setTotal(Utils.formatMonto((long)total));
		return (long)total;
	}
	/**
	 * tipo detalle
	 * @param idTipoDetalle
	 * @param lista
	 * @return
	 */
	public TipoDetalleVO getTipoDetalle(int idTipoDetalle, List lista)
	{
		for (Iterator it3 = lista.iterator(); it3.hasNext();)
		{
			TipoDetalleVO td = (TipoDetalleVO)it3.next();
			if (idTipoDetalle == td.getIdDetalleSeccion())
				return td;
		}
		logger.error("no se encontro TipoDetalle:" + idTipoDetalle + "::");
		return null;
	}
	/**
	 * detalle reporte
	 * @param idTipoSeccion
	 * @param lista
	 * @return
	 */
	public DetalleReporteVO getDetalleReporte(int idTipoSeccion, List lista)
	{
		for (Iterator it4 = lista.iterator(); it4.hasNext();)
		{
			DetalleReporteVO dr = (DetalleReporteVO)it4.next();
			if (idTipoSeccion == dr.getIdTipoSeccion())
				return dr;
		}
		return null;
	}
	
	public ActionRedirect validaCamposCCAF(Session session, DetalleActionForm formulario,ActionMapping mapping,HttpServletRequest request) throws DaoException{
		
		ConvenioMgr convenioMgr = new ConvenioMgr(session);
		ConvenioVO convenio = convenioMgr.getConvenio(formulario.getRutEmpresa(), new Integer(formulario.getConvenio()).intValue());
		if(convenio.getIdCcaf() == 3){
			
			try{
					OrqInputServiceImplServiceLocator implServiceLocator = new OrqInputServiceImplServiceLocator();
					
					OrqInputServiceImpl inputServiceImpl = implServiceLocator.getOrqInputServiceImpl();
					
					ParametrosDAO parametrosDAO = new ParametrosDAO(session);
					ParametroVO parametroVO = parametrosDAO.getParametro(new Integer(Constants.PARAM_TIME_OUT_OBT_PAGO).intValue());
					
					org.apache.axis.client.Stub s = (Stub) inputServiceImpl;
					s.setTimeout(Integer.parseInt(parametroVO.getValor().trim()));
					
					logger.info("***** Datos a enviar WS obtenerInfoPago Rut Empresa: "+ formulario.getRutEmpresa());
					
					OrqInputResultVO inputResultVO = inputServiceImpl.obtenerInfoPago(formulario.getRutEmpresa());
					
					CotizacionREVO cotizacion =(CotizacionREVO)session.createCriteria(CotizacionREVO.class)
												.add(Restrictions.eq("rutEmpresa", new Integer(formulario.getRutEmpresa())))
												.add(Restrictions.eq("idCotizante", new Integer(formulario.getRutEmpresa())))
												.add(Restrictions.eq("idConvenio", new Integer(convenio.getIdConvenio())))
												.uniqueResult();
					
						logger.info("***** Respuesta WS obtenerInfoPago Codigo: "+ inputResultVO.getErrorVO().getCodError());
						logger.info("***** Respuesta WS obtenerInfoPago Descripcion: "+ inputResultVO.getErrorVO().getGlsError());
						
						
						boolean isRedicRemuneracion = false;
						
						
						if(inputResultVO.getAporteVO().getMonto() != Utils.desFormatMonto(""+cotizacion.getCcafAporte())){
				 			isRedicRemuneracion=true;
						}
						if(inputResultVO.getCreditoVO().getMonto() != Utils.desFormatMonto(""+cotizacion.getCcafCredito())){
				 			isRedicRemuneracion=true;
						}
						if(inputResultVO.getLeasingBO().getMonto()  != Utils.desFormatMonto(""+cotizacion.getCcafLeasing())){
				 			isRedicRemuneracion=true;
						}

						if(inputResultVO != null){
							session.clear();
							NominaVO nominaWS = (NominaVO)session.createCriteria(NominaREVO.class).add(Restrictions.eq("rutEmpresa",new Integer(formulario.getRutEmpresa())))
																	.add(Restrictions.eq("idConvenio",new Integer(formulario.getConvenio()))).uniqueResult();
							if(nominaWS.getIdCodigoBarras() > 0){
								DetalleCreditoCcafDAO detalleCreditoCcafDAO =new DetalleCreditoCcafDAO(session);
								DetalleLeasingCcaDAO detalleLeasingCcaDAO = new DetalleLeasingCcaDAO(session);
								DetalleAporteCcafDAO detalleAporteCcafDAO = new DetalleAporteCcafDAO(session);
								
								detalleCreditoCcafDAO.guardarCreditoCCAFParseado(inputResultVO, nominaWS.getIdCodigoBarras());
								detalleLeasingCcaDAO.guardaLeasingCCAFParseado(inputResultVO, nominaWS.getIdCodigoBarras());
								detalleAporteCcafDAO.guardaAporteCCAFParseado(inputResultVO, nominaWS.getIdCodigoBarras());
								
							}
							
						if(isRedicRemuneracion){
							ActionRedirect redirect;
							redirect = new ActionRedirect(mapping.findForward("editarCotizanteActualizado"));
							redirect.addParameter("idConvenio", (""+formulario.getConvenio()).trim());
							redirect.addParameter("rutEmpresa", (""+formulario.getRutEmpresa()).trim());
							redirect.addParameter("idCotizante", (""+formulario.getRutEmpresa()).trim());
							redirect.addParameter("tipoNomina", (""+formulario.getTipoProceso()).trim());
							redirect.addParameter("operacion", "mod");
							redirect.addParameter("accion", "inicio");
							redirect.addParameter("subAccion", "trabajadores");
							redirect.addParameter("subSubAccion", "trabajadorEditar");
							redirect.addParameter("nombre", formulario.getRazonSocial().trim());
							
							return redirect;
						}
						if(inputResultVO.getAporteVO().getMonto() > 0){
							AporteDetalleFormVO aporteDetalleFormVO = new AporteDetalleFormVO();
							formulario.setAporteDetalleFormVO(aporteDetalleFormVO.listAporteDetalleFormVO(inputResultVO.getAporteVO().getAporteDetalle()));
						}
						if(inputResultVO.getCreditoVO().getMonto() > 0){
							CreditoDetalleFormVO creditoDetalleFormVO = new CreditoDetalleFormVO();
							formulario.setCreditoDetalleFormVO(creditoDetalleFormVO.listCreditoDetalleFormVO(inputResultVO.getCreditoVO().getCreditoDetalle()));
						}
						if(inputResultVO.getLeasingBO().getMonto() > 0){
							LeasingDetalleFormVO leasingDetalleFormVO = new LeasingDetalleFormVO();
							formulario.setLeasingDetalleFormVO(leasingDetalleFormVO.listLeasingDetalleFormVO(inputResultVO.getLeasingBO().getLeasingDetalle()));
						}
					}
										
				}catch (Exception e) {
					
					logger.error("DetalleAction::", e);
					MessageList l = new MessageList();
					l.add(	new Message( "Ha ocurrido un Error al obtener el pago CCAF ", e.getMessage()));
					request.setAttribute("messageList", l);
					
				}
			}
		return null;
		//FIN GMALLEA
	}

}
