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
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import cl.araucana.common.Profile;
import cl.araucana.common.UserProfile;
import cl.araucana.ea.ctacte.delegate.*;
import cl.araucana.ea.ctacte.dto.*;
import cl.araucana.ea.struts.exceptions.InvalidParameterException;
import cl.araucana.ea.struts.exceptions.ServiceUnavailableException;

import java.security.Principal;
import java.util.*;

/**
 * @version 	1.0
 * @author
 */
public class CtaCteN2Action extends Action {

	static final String DELEGATE_IMPL = "cl.araucana.ea.ctacte.delegate.BusinessDelegateImpl"; 
	
	public ActionForward execute(
			ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response)
		throws Exception {

		HttpSession session = request.getSession();
		String toPage = null;
		ActionErrors errors = new ActionErrors();
		ActionForward forward = new ActionForward();
		ActionMessages messages = new ActionMessages();

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


		/*
		 * 	get user input data
		 */
		long rutEmpresa = 0L;
		int codigoOficina = 0;
		int codigoSucursal = 0;
		int codigoConcepto = 0;		
		try {
			rutEmpresa = ((Long) profile.getAttribute("empresa")).longValue();
			codigoOficina = ((Integer) PropertyUtils.getSimpleProperty(form, "codigoOficina")).intValue();
			codigoSucursal = ((Integer)	PropertyUtils.getSimpleProperty(form, "codigoSucursal")).intValue();
			codigoConcepto = ((Integer) PropertyUtils.getSimpleProperty(form, "codigoConcepto")).intValue();
		} catch (Exception e) {
			StringBuffer msg = new StringBuffer();
			msg.append("\r\n\tAtrapado en: ").append(this.getClass().getName()).append("#execute");
			msg.append("\r\n\tCausa: error en la recuperacion de parametros.");
			throw new InvalidParameterException(msg.toString());
		}
		
		/*
		 * 	perform business logic
		 */
		try {
			EmpresaTO dtoEmpresa = delegate.getEmpresa(new RutTO(rutEmpresa, ""));
			OficinaTO dtoOficina = delegate.getOficina(codigoOficina);  
			SucursalTO dtoSucursal = delegate.getSucursal(new RutTO(rutEmpresa, ""), codigoOficina, codigoSucursal);
			ConceptoTO dtoConcepto = delegate.getConcepto(codigoConcepto);

			Collection detalles = delegate.getCuentaCorriente(
												new RutTO(rutEmpresa), 
												codigoOficina, 
												codigoSucursal, 
												codigoConcepto); 								 			
			if(detalles == null) {
				messages.add("noinformacion", new ActionMessage(""));
			}
			ResumenCuentaCorrienteDeConceptoTO resumen = delegate.getResumenCuentaCorrienteDeConcepto(detalles);
						
			// setear valores a los beans
			request.setAttribute("empresa", dtoEmpresa);
			request.setAttribute("oficina", dtoOficina);
			request.setAttribute("sucursal", dtoSucursal);
			request.setAttribute("concepto", dtoConcepto);
			request.setAttribute("detalles", detalles);
			request.setAttribute("resumen", resumen);									
			request.setAttribute("fechaActual", new Date(System.currentTimeMillis()));		

			Map paramsHome = new HashMap();
			paramsHome.put("idChanged", "EMPRESA");
			paramsHome.put("rutEmpresa", new Long(rutEmpresa));
			paramsHome.put("codigoOficina", new Long(codigoOficina));
			paramsHome.put("codigoSucursal", new Long(codigoSucursal));			
			request.setAttribute("paramsHome", paramsHome);

			// destino
			toPage = "success";							 
		} catch (Exception e) {
			StringBuffer msg = new StringBuffer();
			msg.append("\r\n\tAtrapado en: ").append(this.getClass().getName()).append("#execute");
			msg.append("\r\n\tCausa: error propagado.");
			throw new ServiceUnavailableException(msg.toString(), e);
		}

		if (!errors.isEmpty()) {
			saveErrors(request, errors);
			forward = mapping.findForward("failure");
		}
		
		forward = mapping.findForward(toPage);
		return (forward);

	}
}
