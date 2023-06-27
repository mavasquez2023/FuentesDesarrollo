package cl.araucana.ea.struts.actions;

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

import cl.araucana.common.Profile;
import cl.araucana.common.UserProfile;
import cl.araucana.ea.ctacte.delegate.*;
import cl.araucana.ea.ctacte.dto.*;

import java.security.Principal;
import java.util.*;


/**
 * @version 	1.0
 * @author
 */
public class LiquidacionDefaultN3Action extends Action {

	static BusinessDelegateFactory factory;
	
	public ActionForward execute(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response)
		throws Exception {

		ActionErrors errors = new ActionErrors();
		ActionForward forward = new ActionForward();
		// return value
		HttpSession session = null;
		Profile profile = null;
		boolean isAccessGranted = false;
		
		BusinessDelegate delegate = null;
				
		long rutEmpresa = 0L;
		int codigoOficina = 0;
		int codigoSucursal = 0;
		long periodo = 0L;
		long fecha = 0L;
		int codigoConcepto = 0;
		
		String toPage = null;		

		try {
			session = request.getSession();
			profile = (UserProfile) session.getAttribute("ea_user_profile");
			
			Long key = (Long) profile.getAttribute("empresa");
			PropertyUtils.setSimpleProperty(form, "rutEmpresa", key);
			
			// getting business service delegator
			factory = BusinessDelegateFactory.singlton();
			delegate = factory.newInstance("cl.araucana.ea.ctacte.delegate.BusinessDelegateImpl");

			// retriving parameters
			rutEmpresa = 
					((Long) PropertyUtils.getSimpleProperty(form, "rutEmpresa")).longValue();
			codigoOficina = 
					((Integer) PropertyUtils.getSimpleProperty(form, "codigoOficina")).intValue();
			codigoSucursal = 
					((Integer) PropertyUtils.getSimpleProperty(form, "codigoSucursal")).intValue();
			periodo = 
					((Long) PropertyUtils.getSimpleProperty(form, "periodo")).longValue();
			fecha = 
					((Long) PropertyUtils.getSimpleProperty(form, "fecha")).longValue();
			codigoConcepto = 
					((Integer) PropertyUtils.getSimpleProperty(form, "codigoConcepto")).intValue();
			
			ConceptoTO concepto = delegate.getConcepto(codigoConcepto);					

			/*
			// get information using setting beans
			request.setAttribute("empresa", 
						delegate.getEmpresa(rutEmpresa));
			request.setAttribute("oficina", 
						delegate.getOficina(codigoOficina));
			request.setAttribute("sucursal", 
						delegate.getSucursal(rutEmpresa, codigoOficina, codigoSucursal));
			request.setAttribute("periodo",
						new DTOPeriodo(periodo));
			request.setAttribute("fecha",
						new DTOFecha(fecha));
			request.setAttribute("concepto", concepto);
			
			Map paramsHome = new HashMap();
			paramsHome.put("idChanged", "EMPRESA");
			paramsHome.put("rutEmpresa", new Long(rutEmpresa));
			paramsHome.put("codigoOficina", new Integer(codigoOficina));
			paramsHome.put("codigoSucursal", new Integer(codigoSucursal));
			paramsHome.put("periodo", new Long(periodo));
			paramsHome.put("fecha", new Long(fecha));
			request.setAttribute("paramsHome", paramsHome);
			

			request.setAttribute("mensaje",	 delegate.getNoServicioMensajeConcepto(codigoConcepto));;
			*/
			
			
			toPage = "underConstruction";
		} catch (Exception e) {

			// Report the error using the appropriate name and ID.
			errors.add("name", new ActionError("id"));

		}

		// If a message is required, save the specified key(s)
		// into the request for use by the <struts:errors> tag.

		if (!errors.isEmpty()) {
			saveErrors(request, errors);

			// Forward control to the appropriate 'failure' URI (change name as desired)
			//	forward = mapping.findForward("failure");

		} else {

			// Forward control to the appropriate 'success' URI (change name as desired)
			forward = mapping.findForward(toPage);

		}

		// Finish with
		return (forward);

	}
}
