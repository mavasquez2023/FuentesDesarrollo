package cl.laaraucana.claves.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import cl.laaraucana.claves.business.UtilBitacora;
import cl.laaraucana.claves.business.UtilLDAP;
import cl.laaraucana.claves.clientesws.ClienteSinacofi;
import cl.laaraucana.claves.clientesws.model.ConstantesRespuestasWS;
import cl.laaraucana.claves.clientesws.vo.SalidaSinacofiVO;
import cl.laaraucana.claves.dao.vo.NotificaVO;
import cl.laaraucana.claves.dao.vo.RegistroVO;
import cl.laaraucana.servicios.usuariosLDAP.UsuariosLDAP;


/**
 * @version 1.0
 * @author
 */
public class ActualizaContactoAction extends Action {
	protected Logger logger = Logger.getLogger(this.getClass());
		
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		ActionForward forward = new ActionForward(); // return value
		String rut=null;
		try {
			HttpSession sesion= request.getSession();
			RegistroVO registro= (RegistroVO)sesion.getAttribute("registro");
			if(registro==null){
				forward = mapping.findForward("init");
				return forward;
			}
			rut = registro.getRut_DV();
			String serie = request.getParameter("serie");
			String celular = request.getParameter("celular");
			String email = request.getParameter("email");
			registro.setEmail(email);
			registro.setCelular(celular.replaceAll("\\+", ""));
			
			//Se valida número de serie 
			if(serie!=null){
				serie= serie.trim();
			}
			String mensaje="OK";
			//Consulta Sinacofi
			ClienteSinacofi cli = new ClienteSinacofi();
			SalidaSinacofiVO respSina = (SalidaSinacofiVO) cli.call(rut, serie);
			
			if (respSina == null || respSina.getCodigoError() != ConstantesRespuestasWS.COD_RESPUESTA_SUCCESS || respSina.getCodigoRetorno().equals("00000")) {
				if(respSina!=null){
					logger.info("Respuesta Sinacofi, codigo retorno= " + respSina.getCodigoRetorno() + ", Cedula Vigente=" + respSina.getCedulaVigente() + ", mensaje: " + respSina.getMensaje());
				}
				mensaje="error";
			}else if (!respSina.getCodigoRetorno().equals("10000") || respSina.getCedulaVigente().trim().equals("NO")) {
					logger.info("mensaje: " + respSina.getMensaje());
					mensaje="cedula_error";
			}
			
			//Exito en validación
			if(mensaje.equals("OK")){

				//Actualiza clave en LDAP
				NotificaVO notifica= new NotificaVO();
				notifica.setMail("X");
				UsuariosLDAP resultado= UtilLDAP.changePassword(registro, notifica);
				if(resultado.getCODIGO_RESPUESTA()==1 && resultado.getESTADO()==1){
					//Se actualiza registro en BD
					String tipoenvio="EMAIL";
					registro.setTipoenvio(tipoenvio);
					UtilBitacora.modificaRegistro(registro);
					forward = mapping.findForward("exito");
					mensaje="";
				}else{
					forward = mapping.findForward("error");
					request.setAttribute("errorMsg", "error clave LDAP");
				}
			}else if(mensaje.equals("cedula_error")){
				forward = mapping.findForward("nosinacofi");
			}else{
				forward = mapping.findForward("error");
			}
			request.setAttribute("errorMsg", mensaje);

					
			
			
		} catch (Exception e) {
			// Rut no valido
			logger.error("Error en Actualiza Contacto, RUT: " + rut + e.getMessage());
			request.setAttribute("title", "Sistema Error");
			request.setAttribute("errorMsg", "Error mensaje:" + e.getMessage());
			request.setAttribute("error", "1");
			forward = mapping.findForward("error");
		}
		
		return (forward);

	}
	
	    
}
