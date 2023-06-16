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

import cl.araucana.core.registry.UserRegistryConnection;
import cl.araucana.core.util.UserPrincipal;
import cl.araucana.core.util.UserPrincipal_Original;
import cl.laaraucana.botonpago.web.utils.Configuraciones;
import cl.laaraucana.botonpago.web.utils.Constantes;

public class RedireccionarSatelitesAction extends Action {
	Logger logger = Logger.getLogger(this.getClass());
	
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

		String urlDestino = "";
		String urlDestino2 = "";
		HttpSession session = request.getSession();
		String rol = (String) session.getAttribute("rol");
		System.out.println("Boton pago, Rol:" +  rol + ".");
		if (Constantes.getInstancia().LDAP_ROL_EJECUTIVO.equals(rol)) {
			String rutDeudor = ((String) session.getAttribute("rutDeudor"));
			UserPrincipal userPrincipal = new UserPrincipal(rutDeudor,"1234");
			String uc = userPrincipal.encode();
			urlDestino = Configuraciones.getConfig("url.vigentes.destino") + rutDeudor;
			//urlDestino2 = Configuraciones.getConfig("url.vigentes.destino") + rutDeudor;
			request.setAttribute("esEjecutivo", true);
		} else {
			//if(Configuraciones.getMainConfig("ambiente").equals("prd")){
				String newCred = (String) session.getAttribute("uc");
				//UserPrincipal up = UserPrincipal.decodeUserCredentials(newCred);
				//UserPrincipal_Original  upOrig = new UserPrincipal_Original(up.getName(), up.getPassword());
				//String hexCred = upOrig.encode(); 
				urlDestino = Configuraciones.getConfig("url.vigentes.destino2") + "?uc=" + newCred;
				
				
			/*}else{
				urlDestino = request.getContextPath() + "/router.do?service=CV";
			}*/
			request.setAttribute("esEjecutivo", false);
		}
		logger.info("Se redirecciona a Satelites");
		logger.info("urlDestino"+urlDestino);
		logger.info("urlDestino2"+urlDestino2);
		request.setAttribute("urlDestino", urlDestino);
		//request.setAttribute("urlDestino2", urlDestino2);
		return mapping.findForward("success");
	}

	/*	private void sendGet(String url) throws Exception {
			URL obj = new URL(url);
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();
	 
			// optional default is GET
			con.setRequestMethod("GET");
	 
			//add request header
			con.setRequestProperty("User-Agent", "Mozilla/5.0");
	 
			int responseCode = con.getResponseCode();
	 
			BufferedReader in = new BufferedReader(
			        new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();
	 
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
	 
			//print result
	 
		}*/

}