package cl.araucana.autoconsulta.ui.actions.satelitesIntegracion;

import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.schema.util.FileSettings;

import cl.araucana.autoconsulta.vo.UsuarioVO;
import cl.araucana.common.env.AppConfig;

/**
 * @author Alberto Canifrú
 *
 */

public class RedireccionarSatelitesAction extends Action {
	
	private ResourceBundle resources;
	
	public ActionForward execute(ActionMapping mapping,	ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		ActionForward forward = null;
		HttpSession session = request.getSession();
	
		//Cargar el archivo de propiedades de satelites
		
		String accion = request.getParameter("navurl");
		//Extrae la ip del servidor de satélites.
		String servicesFile = FileSettings.getValue(AppConfig.getInstance().settingsFileName,
		"/application-settings/common/services-file");
		String server = FileSettings.getValue(servicesFile,
				"/services-settings/satelites/server-ip/");
		//server= "http://localhost:9083/";
		//Si estan seteadas las credenciales en la sesion
		if (session.getAttribute("uc") != null) {				
			if(accion != null && server != null){ 
				//Setear los parametros a enviar al jsp
				try{
					resources = ResourceBundle.getBundle("etc/services");
					String url = "";
					String ruta = resources.getString(accion);
										
					UsuarioVO user = (UsuarioVO) session.getAttribute("datosUsuario");
					
					//La url del action de satelites
					url += server + ruta;
					//Envia las credenciales de usuaio
					url += "?uc=" + session.getAttribute("uc");
					
					//Si el usuario inicio como, encargado y luego selecciono empresa.
					if(session.getAttribute("seleccionaEmpresaACargo").equals("true")){
						url += "&empresa="+user.getRut();
					}
					
					request.setAttribute("urlDestino", url);				
					forward = mapping.findForward("redireccionar");
				}catch(Exception e){
					//Cuando no estan definidos los parametros de enrutamiento
					request.setAttribute("error.code", "404");
					request.setAttribute("error.message", "Página no encontrada");
					request.setAttribute("error.info", "La url a la que está intentando acceder no está disponible");
					forward = mapping.findForward("error");
				}				
			}else{
				//Redireccionar a pagina de error
				request.setAttribute("error.code", "404");
				request.setAttribute("error.message", "Página no encontrada");
				request.setAttribute("error.info", "La url a la que está intentando acceder no está disponible");
				forward = mapping.findForward("error");
			}
		}else{
			session.invalidate();
			forward = mapping.findForward("loginpage");
		}
		return forward;
	}
	
}