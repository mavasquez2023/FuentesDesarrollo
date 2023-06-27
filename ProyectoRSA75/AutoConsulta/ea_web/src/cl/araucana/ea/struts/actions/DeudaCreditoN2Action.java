package cl.araucana.ea.struts.actions;

import java.util.Collection;
import java.util.HashMap;
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
import org.apache.struts.action.DynaActionForm;

import cl.araucana.common.Profile;
import cl.araucana.ea.credito.delegate.BusinessDelegate;
import cl.araucana.ea.credito.delegate.BusinessDelegateFactory;
import cl.araucana.ea.credito.dto.AfiliadoTO;
import cl.araucana.ea.credito.dto.EmpresaTO;
import cl.araucana.ea.credito.dto.RutTO;
import cl.araucana.ea.credito.dto.ResumenCuotasTO;
import cl.araucana.ea.credito.dto.PageBar;
import cl.araucana.ea.struts.exceptions.InvalidParameterException;
import cl.araucana.ea.struts.exceptions.ServiceUnavailableException;


/**
 * @version 	1.0
 * @author
 */
public class DeudaCreditoN2Action extends Action {

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
		int codigoOficinaProceso;
		long folio;
		int codigoOficina;
		int codigoSucursal;
		try {
			rutAfiliado = ((Long) PropertyUtils.getSimpleProperty(form, "rutAfiliado")).longValue();
			folio = ((Long) PropertyUtils.getSimpleProperty(form, "folio")).longValue();
			codigoOficinaProceso = ((Integer) PropertyUtils.getSimpleProperty(form, "codigoOficinaProceso")).intValue();
			codigoOficina= ((Integer) PropertyUtils.getSimpleProperty(form, "codigoOficina")).intValue();
			codigoSucursal= ((Integer) PropertyUtils.getSimpleProperty(form, "codigoSucursal")).intValue();
		} catch (Exception e) {
			StringBuffer msg = new StringBuffer();
			msg.append("\r\n\tAtrapado en: ").append(this.getClass().getName()).append("#execute");
			msg.append("\r\n\tCausa: error propagado.");
			throw new InvalidParameterException(msg.toString(), e);
		}

	
		/*
		 * addtional validation if necessary
		 */


		
		/*
		 * perform business logic
		 */
		try {
			EmpresaTO dtoEmpresa = delegate.getEmpresa(rutEmpresa);
			AfiliadoTO dtoAfiliado = delegate.getAfiliado(rutAfiliado);
			Collection cuotas = delegate.getCuotasValidas(codigoOficinaProceso, folio);
			ResumenCuotasTO resumen = delegate.getResumenCuotas(cuotas);

			/*
			 * determinar pagina previa, e.i viene de cuenta corriente o credito
			 */ 			
			Map paramsHome = new HashMap();
			if (codigoOficina == 0) {
				paramsHome.put("idChanged", "AFILIADO");
				paramsHome.put("rutEmpresa", new Long(rutEmpresa));
				paramsHome.put("rutAfiliado", new Long(rutAfiliado));
				request.setAttribute("paramsHomeCredito", paramsHome);				
			} else {
				paramsHome.put("idChanged", "EMPRESA");
				paramsHome.put("rutEmpresa", new Long(rutEmpresa));
				paramsHome.put("codigoOficina", new Integer(codigoOficina));				
				paramsHome.put("codigoSucursal", new Integer(codigoSucursal));
				request.setAttribute("paramsHomeCtaCte", paramsHome);
			}

			/*
			 * Paging
			 */
			String pageBar = null;		
			Collection detalles = null;
			Collection subset = null;
			int page = ((Integer) PropertyUtils.getSimpleProperty(form, "page")).intValue();
			int count = ((Integer) PropertyUtils.getSimpleProperty(form, "count")).intValue();
			String baseLink = request.getContextPath() + "/deudaCreditoN2.do" + 
							   "?rutEmpresa=" + rutEmpresa + 
							   "&rutAfiliado=" + rutAfiliado +
							   "&folio=" + folio + 
							   "&codigoOficinaProceso=" + codigoOficinaProceso;
			if(codigoOficina != 0) {
				baseLink += "&codigoOficina=" + codigoOficina;
			}
			
			// default value		
			PageBar pb = new PageBar(page, count, cuotas.size(), baseLink, true);

			request.setAttribute("detalles", pb.getSubset(cuotas, page, count));
			request.setAttribute("pageBar", pb.getHtml());
			request.setAttribute("empresa", dtoEmpresa);			
			request.setAttribute("afiliado", dtoAfiliado);			
			request.setAttribute("resumen" , resumen);
		
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
