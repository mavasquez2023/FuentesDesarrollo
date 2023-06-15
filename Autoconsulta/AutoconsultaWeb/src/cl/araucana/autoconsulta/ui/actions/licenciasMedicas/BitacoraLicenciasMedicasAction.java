package cl.araucana.autoconsulta.ui.actions.licenciasMedicas;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import cl.araucana.autoconsulta.common.Funciones;
import cl.araucana.autoconsulta.serv.ServicesAutoconsultaDelegate;
import cl.araucana.autoconsulta.vo.LicenciaMedicaVO;

/**
 * @version 	1.0
 * @author IBM Software Factory
 */
public class BitacoraLicenciasMedicasAction extends Action {

	private static Logger logger = Logger.getLogger(BitacoraLicenciasMedicasAction.class);

	public ActionForward execute(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response)
		throws Exception {
		
		String  forward = "bitacora";

		HttpSession session = request.getSession(true);
		
		try {
			String rut = null;
			//UsuarioVO usuario = (UsuarioVO)session.getAttribute("datosUsuario");
			
/*			Map parametros = request.getParameterMap();
			
			for (Iterator it = parametros.entrySet().iterator(); it.hasNext();) {
				Map.Entry pairs = (Map.Entry) it.next();
				System.out.println(pairs.getKey() + " = " + pairs.getValue());
			}*/
			String fechaPago = request.getParameter("fechaPago");
			try {
				fechaPago = Funciones.agregaSlash(fechaPago);
			} catch (Exception e) {
				fechaPago = "00/00/0000";
			}
			
			String nroLicenciaParam = request.getParameter("nroLicencia");
			if(session.getAttribute("rut")==null){
				logger.debug("El nro. de licencia consultado no es válido");
				request.setAttribute("error", "Ingrese rut a consulta");
			}else if(nroLicenciaParam == null ){
				logger.debug("El nro. de licencia consultado no es válido");
				request.setAttribute("error", "El nro. de licencia consultado no es válido");
			}else{
				rut = (String) session.getAttribute("rut");
				rut = rut.split("-")[0];
				rut = rut.trim();
				long rutL = Long.parseLong(rut);
				
				String nroLicenciaStr=nroLicenciaParam.split("_")[0];
				String fechaHasta=nroLicenciaParam.split("_")[1];
				
				long nroLicencia =Long.parseLong(nroLicenciaStr);
				ServicesAutoconsultaDelegate delegate = new ServicesAutoconsultaDelegate();
				LicenciaMedicaVO licencia = delegate.getBitacoraLicenciaMedica(rutL, nroLicencia, fechaHasta);
				if(licencia!=null){
					licencia.setFechaDePago(fechaPago);
					request.setAttribute("licencia", licencia);
				}else{
					logger.debug("No se encontró el nro de licencia consultado");
					request.setAttribute("error", "No se encontró el nro de licencia consultado, por favor intente nuevamente");
				}
			}
		} catch (Exception e) {
			logger.error("Se produjo un error al consultar por la licencia", e);
			request.setAttribute("error", "Se produjo un error al consultar por la licencia");
		}

		return mapping.findForward(forward);
	}

}
