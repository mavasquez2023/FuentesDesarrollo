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
import cl.laaraucana.claves.business.UtilInfoAfiliado;
import cl.laaraucana.claves.business.UtilLDAP;
import cl.laaraucana.claves.clientesws.model.ConstantesRespuestasWS;
import cl.laaraucana.claves.clientesws.vo.SalidainfoAfiliadoVO;
import cl.laaraucana.claves.dao.ConsultaServicesDAO;
import cl.laaraucana.claves.dao.vo.NotificaVO;
import cl.laaraucana.claves.dao.vo.RegistroVO;
import cl.laaraucana.servicios.usuariosLDAP.UsuariosLDAP;


/**
 * @version 1.0
 * @author
 */
public class RecuperarClaveAction extends Action {
	protected Logger logger = Logger.getLogger(this.getClass());
	
	public ActionForward recuperar(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		ActionForward forward = new ActionForward(); // return value
		String rut=null;
		try {
			
			HttpSession sesion= request.getSession();
			String clave = request.getParameter("clave");
			RegistroVO registro= (RegistroVO)sesion.getAttribute("registro");
			if(registro==null){
				forward = mapping.findForward("init");
				return forward;
			}
			rut = registro.getRut_DV();
			NotificaVO notifica= new NotificaVO();
			if(clave.equals("E")){
				logger.info("Notifica clave correo: " + registro.getEmail());
				notifica.setMail("X");
				registro.setTipoenvio("EMAIL");
				forward = mapping.findForward("exito");
				request.setAttribute("email", registro.getEmailX());
			}else{
				logger.info("Notifica clave celular: " + registro.getCelular());
				notifica.setSms("X");
				registro.setTipoenvio("SMS");
				forward = mapping.findForward("celular");
				request.setAttribute("celular", registro.getCelularX());
			}
			logger.info("Cambiar password en LDAP, invoca WS ");
			UsuariosLDAP resultado= UtilLDAP.changePassword(registro, notifica);
			if(resultado.getCODIGO_RESPUESTA()==1 && resultado.getESTADO()==1){
				logger.info("Actualiza Bitacora ");
				UtilBitacora.modificaRegistro(registro);
			}else{
				logger.warn("Error al actualizar clave en LDAP, CODIGO_RESPUESTA= " + resultado.getCODIGO_RESPUESTA()+ ", estado= " + resultado.getESTADO());
				forward = mapping.findForward("error");
			}
		} catch (Exception e) {
			// Rut no valido
			logger.error("Error en Recuperar Clave, RUT: "+ rut + ", mensaje=" + e.getMessage());
			request.setAttribute("title", "Sistema Error");
			request.setAttribute("errorMsg", "Error mensaje:" + e.getMessage());
			request.setAttribute("error", "1");
			forward = mapping.findForward("error");
		}
		
		return (forward);

	}
	
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		ActionForward forward = new ActionForward(); // return value
		try {
			HttpSession sesion= request.getSession();
			String rut = request.getParameter("rut");
			request.getSession().setAttribute("rut", rut);
			String clave = request.getParameter("clave");
			if(clave!=null){
				forward= recuperar(mapping, form, request, response);
			}else{
				request.setAttribute("rut", rut);
				rut= rut.replaceAll("\\.", "");
				logger.info("Recuperar Clave RUT: " + rut);
				//Se consulta info Afiliado en CRM
				SalidainfoAfiliadoVO salida= UtilInfoAfiliado.consultaAfiliado(rut);
				if(salida!=null && salida.getCodigoError()==ConstantesRespuestasWS.COD_RESPUESTA_SUCCESS){
					if(salida.getNombreCompleto()==null){
						logger.info("No es afiliado");
						forward = mapping.findForward("noafiliado");
						request.setAttribute("errorMsg", "rut_error");
						return (forward);
					}

					//Consulto datos de afiliado en Bitacora
					ConsultaServicesDAO consultaDAO= new ConsultaServicesDAO();
					int rutint= Integer.parseInt(rut.split("-")[0]);
					RegistroVO registro= consultaDAO.consultaRegistro(rutint);
					if(registro==null){
						logger.info("RUT no registrado");
						forward = mapping.findForward("noregistrado");
					}else{
						forward = mapping.findForward("success");
						if(registro.getCelular().length()==11){
							registro.setCelular(registro.getCelular().substring(2));
						}
						sesion.setAttribute("registro", registro);
					}
				}else{
					forward = mapping.findForward("error");
				}
			}
		} catch (Exception e) {
			// Rut no valido
			logger.error("Error en Valida RUT: " + e.getMessage());
			request.setAttribute("title", "Sistema Error");
			request.setAttribute("errorMsg", "Error mensaje:" + e.getMessage());
			request.setAttribute("error", "1");
			forward = mapping.findForward("error");
		}
		
		return (forward);

	}
	
	    
}
