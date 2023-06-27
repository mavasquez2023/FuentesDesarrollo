package cl.araucana.ea.struts.actions;

import java.util.Collection;
import java.util.Iterator;

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
import org.apache.struts.action.DynaActionForm;

import cl.araucana.common.Profile;
import cl.araucana.ea.credito.delegate.BusinessDelegate;
import cl.araucana.ea.credito.delegate.BusinessDelegateFactory;
import cl.araucana.ea.credito.dto.CreditoTO;
import cl.araucana.ea.credito.dto.EmpresaTO;
import cl.araucana.ea.credito.dto.RutTO;
import cl.araucana.ea.struts.exceptions.InvalidParameterException;
import cl.araucana.ea.struts.exceptions.ServiceUnavailableException;


/**
 * @version 	1.0
 * @author
 */
public class DeudaCreditoN1Action extends Action {

	static final String DELEGATE_IMPL = "cl.araucana.ea.credito.delegate.BusinessDelegateImpl"; 
	
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
		String idChanged = null;
		try {
			idChanged = (String) PropertyUtils.getSimpleProperty(form, "idChanged");
			if("".equals(idChanged)) { 
				idChanged = "EMPRESA";			
			}
		} catch (Exception e) {
			StringBuffer msg = new StringBuffer();
			msg.append("\r\n\tAtrapado en: ").append(this.getClass().getName()).append("#execute");
			msg.append("\r\n\tCausa: error propagado.");
			throw new InvalidParameterException(msg.toString(), e);
		}
		
		/*
		 * 	perform business logic
		 */
		try {
			if ("EMPRESA".equals(idChanged)) {
				/*
				 * obtener lista de deudores
				 */ 
				Collection afiliados = delegate.getAfiliadosConCreditosValidos(rutEmpresa);
				if(afiliados == null) {
					messages.add("", new ActionMessage("", ""));
				} else {
					PropertyUtils.setSimpleProperty(form, "rutAfiliado", new Long(0));											
					Collection opciones = delegate.getOpciones(afiliados, BusinessDelegate.OPCION_AFILIADO, true);				 	
					session.setAttribute("afiliados", opciones);
				}
			} else {
				/*
				 * un deudor fue seleccionado
				 */ 
				long rutAfiliado = ((Long) PropertyUtils.getSimpleProperty(form, "rutAfiliado")).longValue();				
				//Collection afiliados = delegate.getAfiliadosConCreditosValidos(new RutTO(rutEmpresa, ""));
				//Collection opciones = delegate.getOpciones(afiliados, BusinessDelegate.OPCION_AFILIADO, false); 							
				Collection creditos = delegate.getCreditosValidos(rutEmpresa, rutAfiliado);
				
				double montoTotalCredito = 0.0D;
				Iterator it = creditos.iterator();
				while(it.hasNext()) {
					CreditoTO aCredito = (CreditoTO) it.next();
					montoTotalCredito += aCredito.getSaldoVigente() + aCredito.getSaldoMoroso();
				}
				
				//request.setAttribute("afiliados", opciones);
				request.setAttribute("detalles", creditos);
				request.setAttribute("total", new Double(montoTotalCredito));
			}
			request.setAttribute("empresa", delegate.getEmpresa(rutEmpresa));											
			toPage = "success";
		} catch (Exception e) {
			StringBuffer msg = new StringBuffer();
			msg.append("\r\n\tAtrapado en: ").append(this.getClass().getName()).append("#execute");
			msg.append("\r\n\tCausa: error propagado.");
			throw new ServiceUnavailableException(msg.toString(), e);							
		}

		if (!errors.isEmpty()) {
			saveErrors(request, errors);
			toPage = "error";
		}
		
		forward = mapping.findForward(toPage);
		return (forward);
	}
}
