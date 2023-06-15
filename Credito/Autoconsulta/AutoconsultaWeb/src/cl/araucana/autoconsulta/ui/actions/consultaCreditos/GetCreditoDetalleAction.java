package cl.araucana.autoconsulta.ui.actions.consultaCreditos;

import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorActionForm;

import cl.araucana.autoconsulta.bo.ICreditoBO;
import cl.araucana.autoconsulta.bo.impl.CreditoBO;
import cl.araucana.autoconsulta.serv.ServicesAutoconsultaDelegate;
import cl.araucana.autoconsulta.vo.UsuarioVO;
import cl.laaraucana.credito.to.CreditoTO;

/**
 * @version 	1.0
 * @author asepulveda
 */
public class GetCreditoDetalleAction extends Action {

	private static Logger logger = Logger.getLogger(GetCreditoDetalleAction.class);

	public static final String GLOBAL_FORWARD_consultaDetalleCreditos =
		"detalleCredito";
	public static final String GLOBAL_FORWARD_definirEmpleado =
		"detallePagos";

	public ActionForward execute(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response)
		throws Exception {
		
		HttpSession session = request.getSession(true);
		DynaValidatorActionForm daf = (DynaValidatorActionForm) form;
		ServicesAutoconsultaDelegate delegate = new ServicesAutoconsultaDelegate();
		
		Collection detalleCuotas = null;
		Collection detallePagos = null;
		Collection segurosCredito = null;
		
		
		String target = GLOBAL_FORWARD_consultaDetalleCreditos;
		String folio = "";
		String vistaDespliegue =  (String) request.getParameter("vistaDespliegue");
		folio = (String)daf.get("folio");
		
		
		
		if (folio == null || folio.equals("")) {
			session.setAttribute("credito.folio", "");
			return mapping.findForward(target);
		} else {
			session.setAttribute("credito.folio", folio);
		}
		
		if (vistaDespliegue == null || vistaDespliegue.equals("")) {
			vistaDespliegue = "Ver cuotas";
			session.setAttribute("vistaDespliegue", vistaDespliegue);
		} else {
			session.removeAttribute("vistaDespliegue");
			session.setAttribute("vistaDespliegue", vistaDespliegue);
			return mapping.findForward(target);
		}

		session.removeAttribute("validation.message");
		session.removeAttribute("servicio.devolverse");
		session.removeAttribute("cnsCredito.volver");
		
		
		long lFolio = Long.parseLong(folio);
		session.setAttribute("detalleCredito.folio", folio);
		Collection lista = (Collection)session.getAttribute( "listaCreditos");
		Iterator i = lista.iterator();
		while (i.hasNext()) {
			CreditoTO credito = (CreditoTO) i.next();
			if (credito.getFolio() == lFolio) {
				ICreditoBO creditoBO = new CreditoBO();
				creditoBO.setCredito(credito);
				detalleCuotas = creditoBO.obtenerCuotas();
				detallePagos = creditoBO.obtenerPagos();
				segurosCredito = creditoBO.obtenerSegurosCredito();
				
				session.removeAttribute("datosCredito");
				session.removeAttribute("detalleCuotas");
				session.removeAttribute("detallePagos");
				session.removeAttribute("segurosCredito");

				session.setAttribute("datosCredito", credito);
				session.setAttribute("detalleCuotas", detalleCuotas);
				session.setAttribute("detallePagos", detallePagos);
				session.setAttribute("segurosCredito", segurosCredito);
				
				if (credito.getRutAval1()!=0) {
					UsuarioVO aval1 = delegate.getDatosUsuario(credito.getRutAval1());
					session.setAttribute("aval1", aval1);
				}
				if (credito.getRutAval2()!=0) {
					UsuarioVO aval2 = delegate.getDatosUsuario(credito.getRutAval2());
					session.setAttribute("aval2", aval2);
				}
				break;
			}
		}
		
		target = GLOBAL_FORWARD_consultaDetalleCreditos;
		return mapping.findForward(target);
	}


	
}
