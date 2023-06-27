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
import org.apache.struts.action.DynaActionForm;

import cl.araucana.common.Profile;
import cl.araucana.ea.credito.delegate.BusinessDelegate;
import cl.araucana.ea.credito.delegate.BusinessDelegateFactory;
import cl.araucana.ea.credito.dto.AfiliadoTO;
import cl.araucana.ea.credito.dto.EmpresaTO;
import cl.araucana.ea.credito.dto.RutTO;
import cl.araucana.ea.credito.dto.ResumenTablaDesarrolloTO;
import cl.araucana.ea.credito.dto.PageBar;
import cl.araucana.ea.struts.exceptions.InvalidParameterException;
import cl.araucana.ea.struts.exceptions.ServiceUnavailableException;


/**
 * @version 	1.0
 * @author
 */
public class TablaDesarrolloCreditoAction extends Action {

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
		String pageFlag = null;
		try {
			rutAfiliado = ((Long) PropertyUtils.getSimpleProperty(form, "rutAfiliado")).longValue();
			codigoOficinaProceso = ((Integer) PropertyUtils.getSimpleProperty(form, "codigoOficinaProceso")).intValue();
			folio = ((Long) PropertyUtils.getSimpleProperty(form, "folio")).longValue();
			pageFlag = (String) PropertyUtils.getSimpleProperty(form, "pf");
		} catch (Exception e) {
			StringBuffer msg = new StringBuffer();
			msg.append("\r\n\tAtrapado en: ").append(this.getClass().getName()).append("#execute");
			msg.append("\r\n\tCausa: error propagado.");
			throw new InvalidParameterException(msg.toString(), e);
		}


		/*
		 * perform business logic
		 */
		Collection detalles = null;
		Collection subset = null;
		Iterator it = null;
		
		try {
			// get information using setting beans
			EmpresaTO dtoEmpresa = delegate.getEmpresa(rutEmpresa);
			AfiliadoTO dtoAfiliado = delegate.getAfiliado(rutAfiliado);
			Collection cuotas = delegate.getTablaDesarrollo(codigoOficinaProceso, folio);						
			ResumenTablaDesarrolloTO resumen = delegate.getResumenTablaDesarrollo(codigoOficinaProceso, folio); 

			request.setAttribute("empresa", dtoEmpresa);
			request.setAttribute("afiliado", dtoAfiliado);	
			request.setAttribute("resumen", resumen);

			// parameters for certificado page
			Map params = new HashMap();
			params.put("rutEmpresa", new Long(rutEmpresa));
			params.put("rutAfiliado", new Long(rutAfiliado));
			params.put("codigoOficinaProceso", new Integer(codigoOficinaProceso));
			params.put("folio", new Long(folio));
			params.put("pf", "Y");

			Map paramsHome = new HashMap();
			paramsHome.put("idChanged", "AFILIADO");
			paramsHome.put("rutEmpresa", new Long(rutEmpresa));
			paramsHome.put("rutAfiliado", new Long(rutAfiliado));
						
			request.setAttribute("params", params);
			request.setAttribute("paramsHome", paramsHome);
			request.setAttribute("args", 
								 "rutEmpresa=" + rutEmpresa + 
								 "&rutAfiliado=" + rutAfiliado + 
								 "&codigoOficinaProceso=" + codigoOficinaProceso + 
								 "&folio=" + folio +
								 "&pf=Y");
			//request.setAttribute("detalles", cuotas);
			
			// Pagging
			int page = ((Integer) PropertyUtils.getSimpleProperty(form, "page")).intValue();
			int count = ((Integer) PropertyUtils.getSimpleProperty(form, "count")).intValue();
			String baseLink = request.getContextPath() + 
								"/tablaDesarrolloCredito.do" + 
								"?rutEmpresa=" + rutEmpresa + 
								"&rutAfiliado=" + rutAfiliado +
								"&folio=" + folio + 
								"&codigoOficinaProceso=" + codigoOficinaProceso;

			// default value		
			PageBar pb = new PageBar(page, count, cuotas.size(), baseLink, true);		
			if ("Y".equals(pageFlag))
				request.setAttribute("detalles", cuotas);
			else
				request.setAttribute("detalles", pb.getSubset(cuotas, page, count));
				
			request.setAttribute("pageBar", pb.getHtml());
			
			// determin what page to be shown
			if ("Y".equals(pageFlag))
				toPage = "certificado";
			else
				toPage = "success";
				
		} catch (Exception e) {
			e.printStackTrace();
			// Report the error using the appropriate name and ID.
			errors.add("name", new ActionError("id"));

		}

		// If a message is required, save the specified key(s)
		// into the request for use by the <struts:errors> tag.

		if (!errors.isEmpty()) {
			saveErrors(request, errors);
			toPage = "error";
		}
		
		forward = mapping.findForward(toPage);
		return (forward);
	}
}
