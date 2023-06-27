package cl.araucana.autoconsulta.ui.actions.admin;

import java.util.ArrayList;
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

import cl.araucana.autoconsulta.bo.IUsuarioBO;
import cl.araucana.autoconsulta.bo.impl.UsuarioBO;
import cl.araucana.autoconsulta.vo.EmpresaACargoVO;
import cl.araucana.autoconsulta.vo.AccesoServicioVO;


/**
 * @version 	1.0
 * @author ayr
 */
public class AdminUsuarioAction extends Action {

	private static Logger logger = Logger.getLogger(AdminUsuarioAction.class);
	
	public static final String GLOBAL_FORWARD_asignaAplicacion = 
		"asignaAplicacion";

	public ActionForward execute(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response)
		throws Exception {
		
		HttpSession session = request.getSession(true);
		DynaValidatorActionForm daf = (DynaValidatorActionForm) form;
		String target = GLOBAL_FORWARD_asignaAplicacion;
		String accion = null;
		IUsuarioBO usBO;
		Collection lstServicios;
		
		session.removeAttribute( "validation.message");

		accion = (String)daf.get("accion");
		EmpresaACargoVO encargado = (EmpresaACargoVO)session.getAttribute("encargado");
		
		
		if ( !(encargado instanceof EmpresaACargoVO) || encargado.getRutEncargado()==0) {
			return mapping.findForward("welcome");
		}
		
		usBO = new UsuarioBO();
		
		if (accion.equals("Aceptar")) {
			lstServicios = usBO.consultaServicios(encargado);
			session.setAttribute("lstServicios", lstServicios);

			return mapping.findForward(target);
		} else 
		if (accion.equals("Asignar")) {
			
			
			Collection srvLista = new ArrayList();
			String []arServ = (String[])daf.get("accesos");
			for (int i=0; i< arServ.length; i ++) {
				srvLista.add(new AccesoServicioVO("" + arServ[i], ""));
			}

			
			logger.info("[ " + encargado.getRut() + "][" + encargado.getRutEncargado() + "]");
			
			Collection uServicio = (ArrayList)session.getAttribute("lstServicios");
			if (srvLista.size()!=0) {
				if (uServicio instanceof Collection && uServicio.size()>0) {
					logger.info("Actualizando servicios");
					if (usBO.actualizaServicios(encargado, srvLista)) {
						session.setAttribute( "validation.message", "Actualización de servicios exitoso.");
					} else {
						session.setAttribute( "validation.message", "Problemas al actualizar servicios.");
					}
				} else {
					logger.info("Insertando servicios");
					if (usBO.insertarServicios(encargado, srvLista)) {
						session.setAttribute( "validation.message", "Asignación de servicios exitosa.");
					} else {
						session.setAttribute( "validation.message", "Problemas al asignar servicios.");
					}
				}

			} else {
				logger.info("Eliminando servicios");
				if (uServicio instanceof Collection && uServicio.size()>0) {
					if (usBO.eliminaServicios(encargado)) {
						session.setAttribute( "validation.message", "Desvinculación de servicios exitosa.");
					} else {
						session.setAttribute( "validation.message", "Problemas al desvincular servicios.");
					}
				}
			}
			lstServicios = usBO.consultaServicios(encargado);
			session.setAttribute("lstServicios", lstServicios);
		}
		logger.info("Enviado respuesta en " + target);
		return mapping.findForward(target);
		
	}

}
