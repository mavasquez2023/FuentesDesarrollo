package cl.araucana.infcottra.main.actions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import cl.araucana.infcottra.business.CotizacionesTrabajador;
import cl.araucana.infcottra.dao.VO.CotizacionVO;
import cl.araucana.infcottra.dao.VO.ParamVO;
import cl.araucana.infcottra.dao.VO.SalidaVO;
import cl.araucana.infcottra.main.dao.ConsultaCotizacionDAO;
import cl.araucana.infcottra.main.forms.ConsultaCotizacionForm;
import cl.araucana.infcottra.utils.CertificadoConst;
import cl.araucana.infcottra.utils.CertificadoUtils;
import cl.laaraucana.satelites.Utils.CompletaUtil;
import cl.laaraucana.satelites.Utils.FormUtils;
import cl.laaraucana.satelites.Utils.RutUtil;
import cl.laaraucana.satelites.Utils.Utils;

/**
 * @version 1.0
 * @author
 */
public class ConsultaEmpresaAction extends Action {
	protected Logger logger = Logger.getLogger(this.getClass());
	@SuppressWarnings("unchecked")
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		ActionForward forward = new ActionForward(); // return value
		String rutemp=null;
		String ruttra=null;
		try {
			ConsultaCotizacionForm formCRM = (ConsultaCotizacionForm) form;
			if(formCRM!=null){
				rutemp = formCRM.getRutEmpresa();
				ruttra = formCRM.getRutTrabajador();
			}

			HttpSession sesion = request.getSession();
			sesion = request.getSession();

			String rutEmpresa = request.getParameter("rutEmpresa");
			rutEmpresa= rutEmpresa.replaceAll("\\.", "");
			String rutTrabajador = request.getParameter("rutTrabajador");
			rutTrabajador= rutTrabajador.replaceAll("\\.", "");

			rutEmpresa= rutEmpresa==null || rutEmpresa.equals("")?rutemp:rutEmpresa;
			rutTrabajador= rutTrabajador==null || rutTrabajador.equals("")?ruttra:rutTrabajador;

			RutUtil rutentrada= new RutUtil();
			if(!rutentrada.IsRutValido(rutEmpresa) || !rutentrada.IsRutValido(rutTrabajador)){
				request.setAttribute("error", "2");
				forward = mapping.findForward("success");
				return (forward);
			}
			
			//Buscando COtizaciones Trabajador
			logger.info("Ingreso a Informe Cotizaciones Trabajador, RutEmpresa:" + rutEmpresa + ", rutTrabajador:" + rutTrabajador);
			CotizacionesTrabajador cotizacionesTra= new CotizacionesTrabajador();
			SalidaVO salida= cotizacionesTra.cotizacionesTrabajador(rutEmpresa, rutTrabajador);

			if(salida.getCotizaciones().size()>0){
				request.setAttribute("error", "0");
			}else{
				request.setAttribute("error", "-1");
			}

			sesion.setAttribute("salida", salida);
			sesion.setAttribute("fechaEmision", Utils.getFechaCompleta());
			request.setAttribute("rowspan", salida.getCotizaciones().size()-1);
			request.setAttribute("rutTrabajador", rutTrabajador);
			request.setAttribute("rutEmpresa", rutEmpresa);

		} catch (Exception e) {
			// Rut no valido
			logger.error("Error en consulta Certificado Cotizaciones Trabajador Rut: " + ruttra);
			request.setAttribute("title", "Sistema Error");
			request.setAttribute("errorMsg", "Error al consultar datos:" + e.getMessage());
			request.setAttribute("error", "1");			
		}

		forward = mapping.findForward("success");
		return (forward);

	}
	
}
