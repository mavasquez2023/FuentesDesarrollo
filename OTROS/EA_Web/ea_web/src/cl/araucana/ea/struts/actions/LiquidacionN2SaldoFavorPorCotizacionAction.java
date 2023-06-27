package cl.araucana.ea.struts.actions;

import java.util.Collection;

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
import cl.araucana.ea.ctacte.dto.ConceptoTO;
import cl.araucana.ea.ctacte.dto.EmpresaTO;
import cl.araucana.ea.ctacte.dto.FechaTO;
import cl.araucana.ea.ctacte.dto.OficinaTO;
import cl.araucana.ea.ctacte.dto.PeriodoTO;
import cl.araucana.ea.ctacte.dto.RutTO;
import cl.araucana.ea.ctacte.dto.SucursalTO;
import cl.araucana.ea.ctacte.dto.TotalesSaldosFavorPorCotizacionTO;
import cl.araucana.ea.struts.exceptions.InvalidParameterException;
import cl.araucana.ea.struts.exceptions.ServiceUnavailableException;

/**
 * @version 	1.0
 * @author
 */
public class LiquidacionN2SaldoFavorPorCotizacionAction extends Action {

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

		/*
		 * 	get user input data
		 */
		int codigoOficina = 0;
		int codigoSucursal = 0;
		long periodoLiquidacion = 0L;
		long fechaLiquidacion = 0L;
		int codigoConcepto = 0;
		try {
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
			PeriodoTO dtoPeriodoLiquidacion = new PeriodoTO(periodoLiquidacion); 
			FechaTO dtoFechaLiquidacion = new FechaTO(fechaLiquidacion);

			Collection detalles = delegate.getSaldosFavorPorCotizacion(
														dtoEmpresa.getRut(), 
														codigoOficina, 
														codigoSucursal, 
														dtoPeriodoLiquidacion, 
														dtoFechaLiquidacion, 
														codigoConcepto);
																	
			TotalesSaldosFavorPorCotizacionTO totales = delegate.getTotalesSaldosFavorPorCotizacion(detalles);			

			// set values
			request.setAttribute("empresa", dtoEmpresa);
			request.setAttribute("oficina", dtoOficina);
			request.setAttribute("sucursal", dtoSucursal);
			request.setAttribute("periodoLiquidacion", dtoPeriodoLiquidacion);
			request.setAttribute("fechaLiquidacion", dtoFechaLiquidacion);
			request.setAttribute("concepto", dtoConcepto);

			request.setAttribute("detalles", detalles);
			request.setAttribute("resumen", totales);
			
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
