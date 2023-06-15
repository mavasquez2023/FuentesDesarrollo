package cl.araucana.autoconsulta.ui.actions.consultaChequesEmpresa;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorActionForm;

import cl.araucana.autoconsulta.serv.ServicesAutoconsultaDelegate;
import cl.araucana.autoconsulta.vo.UsuarioVO;

/**
 * @version 	1.0
 * @author asepulveda
 */
public class GetConsultaChequesEmpresaAction extends Action {
	
	private static Logger logger = Logger.getLogger(GetConsultaChequesEmpresaAction.class);
	
	public static final String GLOBAL_FORWARD_consultaChequesEmpresa = "consultaChequesEmpresa";

	public static final String MESSAGE_PAGE = "message";


	public ActionForward execute(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response)
		throws Exception {
			HttpSession session = request.getSession(true);
			ServicesAutoconsultaDelegate delegate = new ServicesAutoconsultaDelegate();
			long rutEmpresa=0;
			String nombreCertificado=null;
			String rutCertificado=null;
			String target=null;
			//DynaValidatorActionForm daf = (DynaValidatorActionForm)form;
			
			//String dispositivo = request.getRemoteAddr();
			//logger.debug("IP: "+dispositivo);
			
			String textRutEmpresa = null;
			
			UsuarioVO usuario = (UsuarioVO) session.getAttribute("datosUsuario");
			usuario.setIpConexion(request.getRemoteAddr());
			if(usuario.isEsEmpresa() || usuario.isEsEmpresaPublica()) {
				// El usuario es empresa
				textRutEmpresa = String.valueOf(usuario.getRut());
				//usuario.setRutusuarioAutenticado(usuario.getRut());
				usuario.setRutEmpresa(usuario.getRut());
			}
			
			rutEmpresa = Long.parseLong(textRutEmpresa);	
			logger.debug("rutEmpresa: "+rutEmpresa);
			usuario.setRutAfiliado(usuario.getRut());
			Collection consultaCheques = delegate.getChequesEmpresa(usuario);
			

			session.setAttribute("cheques",consultaCheques);			
			session.setAttribute("nombre", usuario.getNombre());
			session.setAttribute("rut", usuario.getFullRut());
			session.setAttribute("fechaHoy", new java.util.Date());
			logger.debug("A desplegar Consulta Cheques");
			target=GLOBAL_FORWARD_consultaChequesEmpresa;

 		    return mapping.findForward(target);
	}
}
