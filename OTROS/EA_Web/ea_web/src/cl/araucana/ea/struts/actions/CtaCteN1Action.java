package cl.araucana.ea.struts.actions;

import javax.naming.InitialContext;
import javax.naming.NamingException;
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
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.taglib.tiles.InitDefinitionsTag;

import cl.araucana.common.Profile;
import cl.araucana.common.UserProfile;
import cl.araucana.ea.ctacte.delegate.*;
import cl.araucana.ea.ctacte.dto.*;
import cl.araucana.ea.struts.exceptions.*;

import java.security.Principal;
import java.util.*;


/**
 * @version 	1.0
 * @author
 */
public class CtaCteN1Action extends Action {

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
		String toPage = null;
		String idChanged = null;

		//setLocale(request, new java.util.Locale("es"));
		//
		// get an instance of business delegate
		//
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
				
		Profile profile = null;
		boolean isAccessGranted = false;
						
		long rutEmpresa = 0L;
		int codigoOficina = 0;
		int codigoSucursal = 0;
	
		/*
		 * 	get an instance of business delegate
		 */
		try {
			session = request.getSession();
			profile = (UserProfile) session.getAttribute("ea_user_profile");
			
			if(profile == null) {
				throw new InvalidProfileException();
			}
			
			Long key = (Long) profile.getAttribute("empresa");
			
			PropertyUtils.setSimpleProperty(form, "rutEmpresa", key);

			// retriving parameters
			idChanged = (String) PropertyUtils.getSimpleProperty(form, "idChanged");			
			rutEmpresa = ((Long) PropertyUtils.getSimpleProperty(form, "rutEmpresa")).longValue();
			
			// get information using setting beans
			request.setAttribute(
					"empresa", delegate.getEmpresa(new RutTO(rutEmpresa, "")));
						
			request.setAttribute("fechaActual", new Date());		
						
			if ("EMPRESA".equals(idChanged)) {
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
				

			} 
			
			if ("OFICINA".equals(idChanged)) {				
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

				// obtener detalles y resumen
				Collection c =	delegate.getCuentaCorriente(
									new RutTO(rutEmpresa, ""), 
									codigoOficina, 
									codigoSucursal);				
				ResumenCuentaCorrienteTO resumen = delegate.getResumenCuentaCorriente(c);

				if ((resumen.getSaldoCobrar() == 0) && (resumen.getTotalPagar() == 0)) {
					ActionMessages messages = new ActionMessages();
					messages.add("messageSinMovimiento", new ActionMessage("global.message.SinMovimiento"));
					saveMessages(request, messages);
				} else {
					request.setAttribute("detalles", c);
					request.setAttribute("resumen", resumen);				
				}
				
				// agregar parametros usado en enlace a home de ctacte.
				Map paramsHome = new HashMap();
				paramsHome.put("idChanged", "SUCURSAL");
				paramsHome.put("rutEmpresa", new Long(rutEmpresa));
				paramsHome.put("codigoOficina", new Long(codigoOficina));
				paramsHome.put("codigoSucursal", new Long(codigoSucursal));			
				session.setAttribute("paramsHomeCtaCte", paramsHome);
				
			} 			
 			
			toPage = "success";
		} catch (Exception e) {
			e.printStackTrace();
			
			StringBuffer msg = new StringBuffer();
			msg.append("\r\n\tAtrapado en: ").append(this.getClass().getName()).append("#CtaCte1Action");
			msg.append("\r\n\tCausa: error propagado.");
			throw new ServiceUnavailableException(msg.toString(), e);			
		}

		// If a message is required, save the specified key(s)
		// into the request for use by the <struts:errors> tag.

		if (!errors.isEmpty()) {
			saveErrors(request, errors); 
		}
		// Write logic determining how the user should be forwarded.
		forward = mapping.findForward(toPage);

		// Finish with
		return (forward);
	}
}
