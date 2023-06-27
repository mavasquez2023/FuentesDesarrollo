package cl.araucana.ea.struts.actions;

import java.util.*;

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
public class CtaCteN3Action extends Action {

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
		int codigoConcepto = 0;
		long periodoRemuneracion = 0L;

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
			rutEmpresa = ((Long) PropertyUtils.getSimpleProperty(form, "rutEmpresa")).longValue();
			codigoOficina = ((Integer)
					PropertyUtils.getSimpleProperty(form, "codigoOficina")).intValue();
			codigoSucursal = ((Integer)
					PropertyUtils.getSimpleProperty(form, "codigoSucursal")).intValue();
			codigoConcepto = 
					((Integer) PropertyUtils.getSimpleProperty(form, "codigoConcepto")).intValue();
			periodoRemuneracion = 
					((Long) PropertyUtils.getSimpleProperty(form, "periodoRemuneracion")).longValue();
						
						
			// get information using setting beans
			EmpresaTO dtoEmpresa = delegate.getEmpresa(new RutTO(rutEmpresa, ""));
			OficinaTO dtoOficina = delegate.getOficina(codigoOficina);  
 			SucursalTO dtoSucursal = delegate.getSucursal(new RutTO(rutEmpresa, ""), codigoOficina, codigoSucursal);
 			ConceptoTO dtoConcepto = delegate.getConcepto(codigoConcepto);
 			PeriodoTO dtoPeriodoRemuneracion = new PeriodoTO(periodoRemuneracion); 

			Collection c = delegate.getMovimientosCuentaCorriente(dtoEmpresa.getRut(), codigoOficina, codigoSucursal, codigoConcepto, dtoPeriodoRemuneracion);
			ResumenMovimientoCuentaCorrienteTO resumen = delegate.getResumenMovimientoCuentaCorriente(c, dtoConcepto.isTieneGravamenes(), dtoPeriodoRemuneracion);			


			// set values 
			request.setAttribute("fechaActual", new Date(System.currentTimeMillis()));		

			request.setAttribute("empresa", dtoEmpresa);
			request.setAttribute("oficina", dtoOficina);
			request.setAttribute("sucursal", dtoSucursal);
			request.setAttribute("concepto", dtoConcepto);
			request.setAttribute("periodo", dtoPeriodoRemuneracion);
			request.setAttribute("detalles", c); 
			request.setAttribute("resumen", resumen);
									

			Map paramsHome = new HashMap();
			paramsHome.put("idChanged", "SUCURSAL");
			paramsHome.put("rutEmpresa", new Long(rutEmpresa));
			paramsHome.put("codigoOficina", new Long(codigoOficina));
			paramsHome.put("codigoSucursal", new Long(codigoSucursal));
			
			request.setAttribute("paramsHome", paramsHome);


			// where to go						
			toPage = "success";						 
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
			forward = mapping.findForward("success");

		}

		// Finish with
		return (forward);

	}
}
