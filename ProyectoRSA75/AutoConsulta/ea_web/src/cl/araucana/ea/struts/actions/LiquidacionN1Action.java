package cl.araucana.ea.struts.actions;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessages;

import cl.araucana.common.Profile;
import cl.araucana.ea.ctacte.delegate.BusinessDelegate;
import cl.araucana.ea.ctacte.delegate.BusinessDelegateFactory;
import cl.araucana.ea.ctacte.dto.FechaTO;
import cl.araucana.ea.ctacte.dto.OficinaTO;
import cl.araucana.ea.ctacte.dto.PeriodoTO;
import cl.araucana.ea.ctacte.dto.RutTO;
import cl.araucana.ea.ctacte.dto.SucursalTO;
import cl.araucana.ea.struts.exceptions.ServiceUnavailableException;

/**
 * @version 	1.0
 * @author
 */
public class LiquidacionN1Action extends Action {

	static final String DELEGATE_IMPL = "cl.araucana.ea.ctacte.delegate.BusinessDelegateImpl"; 
	
	public ActionForward execute(
			ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response)
		throws Exception {

		HttpSession session = request.getSession();
		ActionErrors errors = new ActionErrors();
		ActionForward forward = new ActionForward();
		ActionMessages messages = new ActionMessages();
		String toPage = null;


		/*
		 * 	get an instance of business delegate
		 */
		BusinessDelegate delegate = null;
		try {
			delegate = (BusinessDelegate) 
									BusinessDelegateFactory
									.singlton()
									.newInstance(DELEGATE_IMPL);
		} catch (Exception e) {
			StringBuffer msg = new StringBuffer();
			msg.append("\r\n\tAtrapado en: ").append(this.getClass().getName()).append("#execute");
			msg.append("\r\n\tCausa: error propagado.");
			throw new ServiceUnavailableException(msg.toString(), e);
		}

		/*
		 * 	get session attributes
		 */
		Profile profile = (Profile) session.getAttribute("ea_user_profile");
		long rutEmpresa = ((Long) profile.getAttribute("empresa")).longValue();
				
		try {
			String idChanged = null;
			int codigoOficina = 0;
			int codigoSucursal = 0;
			long periodoLiquidacion = 0L;
			long fechaLiquidacion = 0L;
			int count; 

			// setear empresa -- puede sufrir cambio en proxima version
			//PropertyUtils.setSimpleProperty(form, "rutEmpresa", key);
			//rutEmpresa = ((Long) PropertyUtils.getSimpleProperty(form, "rutEmpresa")).longValue();			
			request.setAttribute("empresa", delegate.getEmpresa(new RutTO(rutEmpresa, "")));
			

			idChanged = (String) PropertyUtils.getSimpleProperty(form, "idChanged");			
								
			if("EMPRESA".equals(idChanged)) {
				Collection oficinas = delegate.getOficinas(new RutTO(rutEmpresa, ""));
				
				if(oficinas.size() == 1) {
					OficinaTO oficina = null;
					Iterator it = oficinas.iterator();					
					while(it.hasNext()) {
						oficina = (OficinaTO) it.next();
					}
					idChanged = "OFICINA";
					codigoOficina = oficina.getCodigo();
					request.setAttribute("oficinas", 
							delegate.getOpciones(oficinas, 
												 BusinessDelegate.OPCION_OFICINA,
												 false));

				} else {
					PropertyUtils.setSimpleProperty(form, "codigoOficina", new Integer(0));							 						
					request.setAttribute("oficinas", 
						delegate.getOpciones(oficinas, BusinessDelegate.OPCION_OFICINA, true));
				}				
				
				// se genera lista de oficinas de la empresa
				/*PropertyUtils.setSimpleProperty(form, "codigoOficina", new Integer(0));
				Collection oficinas = delegate.getOficinasEmpresa(rutEmpresa);
				request.setAttribute("oficinas", 
								delegate.getOpciones(oficinas, BusinessServiceDelegate.OPCION_OFICINA));
				*/
			} 
			
			if("OFICINA".equals(idChanged)) {
				if(codigoOficina == 0) {
					codigoOficina = ((Integer) PropertyUtils.getSimpleProperty(form, "codigoOficina")).intValue();
					Collection oficinas = delegate.getOficinas(new RutTO(rutEmpresa, ""));	
					request.setAttribute("oficinas", 
							delegate.getOpciones(oficinas, 
												 BusinessDelegate.OPCION_OFICINA,
												 false));
				}

				Collection sucursales = delegate.getSucursales(new RutTO(rutEmpresa, ""), codigoOficina);
				if(sucursales.size() == 1) {
					SucursalTO sucursal = null;
					Iterator it = sucursales.iterator();					
					while(it.hasNext()) {
						sucursal = (SucursalTO) it.next();
					}
					idChanged = "SUCURSAL";
					codigoSucursal= sucursal.getCodigo();
					request.setAttribute("sucursales", 
							delegate.getOpciones(sucursales, 
												 BusinessDelegate.OPCION_SUCURSAL,
												 false));

				} else {					
					PropertyUtils.setSimpleProperty(form, "codigoSucursal", new Integer(0));						
					request.setAttribute("sucursales", 
							delegate.getOpciones(sucursales, BusinessDelegate.OPCION_SUCURSAL, true));
				}				
			} 
			
			if ("SUCURSAL".equals(idChanged)) {
				if(codigoOficina == 0) {
					codigoOficina = ((Integer) PropertyUtils.getSimpleProperty(form, "codigoOficina")).intValue();
					Collection oficinas = delegate.getOficinas(new RutTO(rutEmpresa, ""));	
					request.setAttribute("oficinas", 
							delegate.getOpciones(oficinas, 
												 BusinessDelegate.OPCION_OFICINA,
												 false));
				}

				if(codigoSucursal== 0) {
					codigoSucursal = ((Integer) PropertyUtils.getSimpleProperty(form, "codigoSucursal")).intValue();
					Collection sucursales = delegate.getSucursales(new RutTO(rutEmpresa, ""), codigoOficina);	
					request.setAttribute("sucursales", 
							delegate.getOpciones(sucursales, 
												 BusinessDelegate.OPCION_SUCURSAL,
												 false));
				}
				
				Collection periodosLiquidacion = delegate.getPeriodosLiquidacion(new RutTO(rutEmpresa, ""), codigoOficina, codigoSucursal);
				if(periodosLiquidacion.size() == 1) {
					PeriodoTO dtoPeriodoLiquidacion = null;
					Iterator it = periodosLiquidacion.iterator();					
					while(it.hasNext()) {
						dtoPeriodoLiquidacion = (PeriodoTO) it.next();
					}
					idChanged = "PERIODO";
					periodoLiquidacion = dtoPeriodoLiquidacion.getPeriodoLong();
					request.setAttribute("periodosLiquidacion", 
							delegate.getOpciones(periodosLiquidacion, 
												 BusinessDelegate.OPCION_PERIODO,
												 false));

				} else {					
					PropertyUtils.setSimpleProperty(form, "periodoLiquidacion", new Long(0));						
					request.setAttribute("periodosLiquidacion", 
							delegate.getOpciones(periodosLiquidacion, BusinessDelegate.OPCION_PERIODO, true));
				}
			
			} 

			if ("PERIODO".equals(idChanged)) {
				if(codigoOficina == 0) {
					codigoOficina = ((Integer) PropertyUtils.getSimpleProperty(form, "codigoOficina")).intValue();
					Collection oficinas = delegate.getOficinas(new RutTO(rutEmpresa, ""));	
					request.setAttribute("oficinas", 
							delegate.getOpciones(oficinas, 
												 BusinessDelegate.OPCION_OFICINA,
												 false));
				}

				if(codigoSucursal== 0) {
					codigoSucursal = ((Integer) PropertyUtils.getSimpleProperty(form, "codigoSucursal")).intValue();
					Collection sucursales = delegate.getSucursales(new RutTO(rutEmpresa, ""), codigoOficina);	
					request.setAttribute("sucursales", 
							delegate.getOpciones(sucursales, 
												 BusinessDelegate.OPCION_SUCURSAL,
												 false));
				}
								
				if(periodoLiquidacion == 0) {
					periodoLiquidacion = ((Long) PropertyUtils.getSimpleProperty(form, "periodoLiquidacion")).longValue();
					Collection periodosLiquidacion = delegate.getPeriodosLiquidacion(
														new RutTO(rutEmpresa, ""), 
														codigoOficina,
														codigoSucursal);	
					request.setAttribute("periodosLiquidacion", 
							delegate.getOpciones(periodosLiquidacion, 
												 BusinessDelegate.OPCION_PERIODO,
												 false));
				}
				
				Collection fechasLiquidacion = delegate.getFechasLiquidacion(
														new RutTO(rutEmpresa, ""),
														codigoOficina, 
														codigoSucursal,
														new PeriodoTO(periodoLiquidacion));
				if(fechasLiquidacion.size() == 1) {
					FechaTO dtoFechaLiquidacion = null;
					Iterator it = fechasLiquidacion.iterator();					
					while(it.hasNext()) {
						dtoFechaLiquidacion = (FechaTO) it.next();
					}
					idChanged = "FECHA";
					fechaLiquidacion = dtoFechaLiquidacion.getFechaLong();
					request.setAttribute("fechasLiquidacion", 
							delegate.getOpciones(fechasLiquidacion, 
												 BusinessDelegate.OPCION_FECHA,
												 false));

				} else {					
					PropertyUtils.setSimpleProperty(form, "fechaLiquidacion", new Long(0));						
					request.setAttribute("fechasLiquidacion", 
							delegate.getOpciones(fechasLiquidacion , BusinessDelegate.OPCION_FECHA, true));
				}
	
			} 
			
			if ("FECHA".equals(idChanged)) {
				if(codigoOficina == 0) {
					codigoOficina = ((Integer) PropertyUtils.getSimpleProperty(form, "codigoOficina")).intValue();
					Collection oficinas = delegate.getOficinas(new RutTO(rutEmpresa, ""));	
					request.setAttribute("oficinas", 
							delegate.getOpciones(oficinas, 
												 BusinessDelegate.OPCION_OFICINA,
												 false));
				}

				if(codigoSucursal== 0) {
					codigoSucursal = ((Integer) PropertyUtils.getSimpleProperty(form, "codigoSucursal")).intValue();
					Collection sucursales = delegate.getSucursales(new RutTO(rutEmpresa, ""), codigoOficina);	
					request.setAttribute("sucursales", 
							delegate.getOpciones(sucursales, 
												 BusinessDelegate.OPCION_SUCURSAL,
												 false));
				}
								
				if(periodoLiquidacion == 0) {
					periodoLiquidacion = ((Long) PropertyUtils.getSimpleProperty(form, "periodoLiquidacion")).longValue();
					Collection periodos = delegate.getPeriodosLiquidacion(
														new RutTO(rutEmpresa, ""), 
														codigoOficina,
														codigoSucursal);	
					request.setAttribute("periodosLiquidacion", 
							delegate.getOpciones(periodos, 
												 BusinessDelegate.OPCION_PERIODO,
												 false));
				}
				
				if (fechaLiquidacion == 0)  {
					fechaLiquidacion = ((Long) PropertyUtils.getSimpleProperty(form, "fechaLiquidacion")).longValue();
					Collection fechas = delegate.getFechasLiquidacion(
														new RutTO(rutEmpresa, ""),
														codigoOficina,
														codigoSucursal,
														new PeriodoTO(periodoLiquidacion));	
					request.setAttribute("fechasLiquidacion", 
							delegate.getOpciones(fechas, 
												 BusinessDelegate.OPCION_FECHA,
												 false));
				}

					
				Collection detalles = delegate.getLiquidacionCuentaCorriente(
											new RutTO(rutEmpresa, ""),
											codigoOficina,
											codigoSucursal,
											new PeriodoTO(periodoLiquidacion),
											new FechaTO(fechaLiquidacion));

				request.setAttribute("resumen",
						delegate.getResumenLiquidacionCuentaCorriente(detalles));						
				
				request.setAttribute("detalles", detalles); 
					
				Map paramsDocumentoPago = new HashMap();
				paramsDocumentoPago.put("rutEmpresa", new Long(rutEmpresa));
				paramsDocumentoPago.put("codigoOficina", new Integer(codigoOficina));
				paramsDocumentoPago.put("codigoSucursal", new Integer(codigoSucursal));
				paramsDocumentoPago.put("periodoLiquidacion", new Long(periodoLiquidacion));
				paramsDocumentoPago.put("fechaLiquidacion", new FechaTO(fechaLiquidacion).toString8());

				request.setAttribute("paramsGravamenes", paramsDocumentoPago);				
				request.setAttribute("paramsDocumentoPago", paramsDocumentoPago);	
				
			}
		} catch (Exception e) {
			// Report the error using the appropriate name and ID.
			errors.add("name", new ActionError("id"));
			throw e;
		}

		// If a message is required, save the specified key(s)
		// into the request for use by the <struts:errors> tag.

		if (!errors.isEmpty()) {
			saveErrors(request, errors);
			
			// Forward control to the appropriate 'failure' URI (change name as desired)
			//	forward = mapping.findForward("failure");
		}
		
		// Write logic determining how the user should be forwarded.
		forward = mapping.findForward("success");

		// Finish with
		return (forward);
	}
}
