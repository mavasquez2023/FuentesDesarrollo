package cl.laaraucana.botonpago.web.satelitesIntegracion;

/*import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.servlet.RequestDispatcher;*/
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import cl.laaraucana.botonpago.web.utils.Configuraciones;
import cl.laaraucana.botonpago.web.utils.Constantes;

public class RedireccionarSatelitesAction extends Action {
	Logger logger = Logger.getLogger(this.getClass());
	
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		logger.info("RedireccionarSatelitesAction executandose:");
		String urlDestino = "";
		HttpSession session = request.getSession();

		String rol = (String) session.getAttribute("rol");
		System.out.println("Boton pago, Rol:" +  rol + ".");
		String rutDeudor = ((String) session.getAttribute("rutDeudor"));
		if (Constantes.getInstancia().LDAP_ROL_EJECUTIVO.equals(rol)) {
			urlDestino = Configuraciones.getConfig("url.vigentes.destino") + rutDeudor;
			request.setAttribute("esEjecutivo", true);
		} else {
			String newCred = (String) session.getAttribute("uc");
			if(newCred!=null){
				urlDestino = Configuraciones.getConfig("url.vigentes.destino2") + "?uc=" + newCred;
			}else{
				urlDestino = Configuraciones.getConfig("url.vigentes.destino") + rutDeudor;
			}
			
			request.setAttribute("esEjecutivo", false);
		}
		logger.info("Se redirecciona a Satelites");
		logger.info("urlDestino: "+urlDestino);
		request.setAttribute("urlDestino", urlDestino);
		return mapping.findForward("success");
	}

}