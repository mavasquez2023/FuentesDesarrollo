package cl.araucana.ea.struts.actions;

import java.util.Collection;
import java.util.Date;
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
import org.apache.struts.action.ActionMessages;
import org.apache.struts.action.DynaActionForm;

import cl.araucana.common.Profile;
import cl.araucana.ea.credito.delegate.BusinessDelegate;
import cl.araucana.ea.credito.delegate.BusinessDelegateFactory;
import cl.araucana.ea.credito.dto.AfiliadoTO;
import cl.araucana.ea.credito.dto.EmpresaTO;
import cl.araucana.ea.credito.dto.FechaTO;
import cl.araucana.ea.credito.dto.SaldoDeudaCapitalParaFiniquitoTO;
import cl.araucana.ea.struts.exceptions.InvalidParameterException;
import cl.araucana.ea.struts.exceptions.ServiceUnavailableException;


/**
 * @version 	1.0
 * @author
 */
public class CertificadoAction extends Action {

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

		/*
		 * 	get user input data
		 */
		long rutAfiliado = 0L;
		long fechaFiniquito = 0L;
		try {
			rutAfiliado = 
					((Long) PropertyUtils.getSimpleProperty(form, "rutAfiliado")).longValue();
			fechaFiniquito = 
					((Long) PropertyUtils.getSimpleProperty(form, "fechaFiniquito")).longValue();
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
			EmpresaTO dtoEmpresa = delegate.getEmpresa(rutEmpresa);
			AfiliadoTO dtoAfiliado = delegate.getAfiliado(rutAfiliado);
			FechaTO dtoFechaFiniquito = new FechaTO(fechaFiniquito);
			
			Collection creditos = delegate.getSaldoDeudaCapitalParaFiniquito(dtoEmpresa.getRut(), dtoAfiliado.getRut(), dtoFechaFiniquito);
			int folioCertificado = 0;
			long total = 0L;
			if((creditos != null) || (creditos.size() != 0)) {
				//folioCertificado = delegate.getFolioCertificadoSaldoDeudaCapital();
				Iterator it = creditos.iterator();
				while(it.hasNext()) {
					total += ((SaldoDeudaCapitalParaFiniquitoTO) it.next()).getSaldoCapital();
				}
				folioCertificado = 10;
			}						


			request.setAttribute("empresa", dtoEmpresa);
			request.setAttribute("oficina", dtoAfiliado);
			request.setAttribute("sucursal", dtoFechaFiniquito);
			request.setAttribute("folioCertificado", new Integer(folioCertificado));			
			request.setAttribute("creditos", creditos);
			request.setAttribute("total", new Long(total));		
		
			toPage = "certificado";
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
