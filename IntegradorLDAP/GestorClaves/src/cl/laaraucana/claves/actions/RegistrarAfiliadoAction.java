package cl.laaraucana.claves.actions;


import java.util.Map;
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
import cl.laaraucana.claves.dao.vo.NotificaVO;
import cl.laaraucana.claves.dao.vo.RegistroVO;
import cl.laaraucana.servicios.usuariosLDAP.UsuariosLDAP;



/**
 * @version 1.0
 * @author
 */
public class RegistrarAfiliadoAction extends Action {
	protected Logger logger = Logger.getLogger(this.getClass());

	
	@SuppressWarnings("unchecked")
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		ActionForward forward = new ActionForward(); // return value
		Map<String, String> listamap=null;
		try {	
			HttpSession sesion = request.getSession();

			String rut = (String)sesion.getAttribute("rut");
			if(rut==null){
				forward = mapping.findForward("init");
				return forward;
			}
			rut= rut.toUpperCase();
			rut= rut.replaceAll("\\.", "");
			rut= Integer.parseInt(rut.split("-")[0]) + "-" + rut.split("-")[1];
			request.setAttribute("rut", rut);
			
			logger.info("Registrando RUT :" + rut);
			String nombre = (String)sesion.getAttribute("nombre");
			String celular = request.getParameter("celular");
			String telefono = request.getParameter("telefono");
			String email = request.getParameter("email");
			String terminos = request.getParameter("terminos");
			RegistroVO registro= new RegistroVO();
			String[] rutdv=rut.split("-");
			registro.setRut(Integer.parseInt(rutdv[0]));
			registro.setDv(rutdv[1]);
			registro.setNombre(nombre);
			String celular_sms= celular.substring(3);
			registro.setCelular(celular_sms);
			if(telefono!=null && !telefono.equals("")){
				registro.setTelefono(telefono.substring(3));
			}else{
				registro.setTelefono("");
			}
			
			registro.setEmail(email);
			
			NotificaVO notifica= new NotificaVO();
			notifica.setMail("X");
			//Se agrega usuario en LDAP
			logger.info("Agergando usuario en LDAP, invocando WS");
			UsuariosLDAP resultado= UtilLDAP.addUsuario(registro, notifica);
			
			if(resultado.getCODIGO_RESPUESTA()==1 && resultado.getESTADO()==1){
				logger.info("Agregando registro en Bitacora");
				//La bitácora se inserta por ws de IntegradorLDAP
				//int numinsert= UtilBitacora.addRegistro(rut, nombre, celular, telefono, email);
				
				forward = mapping.findForward("success");
				request.setAttribute("email", registro.getEmail());
			}else{
				logger.warn("Error al agregar registro en LDAP");
				forward = mapping.findForward("error");
			}
			
		} catch (Exception e) {
			forward = mapping.findForward("error");
			logger.error("Error en Registrar Afiliado: " + e.getMessage());
			request.setAttribute("title", "Sistema Error");
			request.setAttribute("errorMsg", "Error mensaje:" + e.getMessage());
			request.setAttribute("error", "1");

		}

		return (forward);

	}
	
	    
}
