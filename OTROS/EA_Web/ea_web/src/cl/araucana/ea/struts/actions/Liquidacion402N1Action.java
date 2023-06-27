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
import cl.araucana.ea.ctacte.delegate.*;
import cl.araucana.ea.ctacte.dto.*;

import java.security.Principal;
import java.util.*;


/**
 * @version 	1.0
 * @author
 */
public class Liquidacion402N1Action extends Action {

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
		long periodoLiquidacion = 0L;
		long fechaLiquidacion = 0L;
		int codigoConcepto = 0;
		
		String toPage = null;

		try {
			/*
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
			periodoLiquidacion = 
					((Long) PropertyUtils.getSimpleProperty(form, "periodoLiquidacion")).longValue();
			fechaLiquidacion = 
					((Long) PropertyUtils.getSimpleProperty(form, "fechaLiquidacion")).longValue();
			codigoConcepto = 
					((Integer) PropertyUtils.getSimpleProperty(form, "codigoConcepto")).intValue();

			
			// get information using setting beans
			DTOEmpresa dtoEmpresa = delegate.getEmpresa(new DTORUT(rutEmpresa, ""));
			DTOOficina dtoOficina = delegate.getOficina(codigoOficina);  
			DTOSucursal dtoSucursal = delegate.getSucursal(new DTORUT(rutEmpresa, ""), codigoOficina, codigoSucursal);
			DTOConcepto dtoConcepto = delegate.getConcepto(codigoConcepto);
			DTOPeriodo dtoPeriodoLiquidacion = new DTOPeriodo(periodoLiquidacion); 
			DTOFecha dtoFechaLiquidacion = new DTOFecha(fechaLiquidacion);

			Collection detalles = delegate.getAbonosASaldoAdeudado(dtoEmpresa.getRut(), codigoOficina, codigoSucursal, dtoPeriodoLiquidacion, dtoFechaLiquidacion, codigoConcepto);			
			DTOResumenAbonosASaldoAdeudado resumen = delegate.getResumenAbonosASaldoAdeudado(detalles);			
			

			// setear variables
			request.setAttribute("empresa", dtoEmpresa);
			request.setAttribute("oficina", dtoOficina);
			request.setAttribute("sucursal", dtoSucursal);
			request.setAttribute("periodoLiquidacion", dtoPeriodoLiquidacion);
			request.setAttribute("fechaLiquidacion", dtoFechaLiquidacion);
			request.setAttribute("concepto", dtoConcepto);

			request.setAttribute("detalles", detalles);
			request.setAttribute("resumen", resumen);

			*/
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
