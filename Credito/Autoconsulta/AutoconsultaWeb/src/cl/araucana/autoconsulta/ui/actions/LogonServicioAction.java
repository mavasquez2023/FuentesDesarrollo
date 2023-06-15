package cl.araucana.autoconsulta.ui.actions;
import java.util.Collection;
import java.util.Iterator;

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
import cl.araucana.autoconsulta.vo.AccesoServicioVO;
import cl.araucana.autoconsulta.vo.EmpresaACargoVO;
import cl.araucana.autoconsulta.vo.UsuarioVO;
import cl.laaraucana.utils.ValidaSesionServicio;

public class LogonServicioAction extends Action {
	
	private static Logger logger = Logger.getLogger(LogonServicioAction.class);
	
	public ActionForward execute(
			ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response)
			throws Exception {
		
		HttpSession session = request.getSession(true);
		String subapp = (String)session.getAttribute("struts.subapplication");
		String devolver = (String)session.getAttribute("servicioLogon.devolverse");
		int iServicio = (Integer.parseInt((String)session.getAttribute("servicioLogon.servicioValidar")));
		String serviciosValidos = (String)session.getAttribute("servicioLogon.serviciosValidos");
		
		DynaValidatorActionForm daf = (DynaValidatorActionForm)form;
		String sRutUsuario = (String)daf.get("rutUsuarioServicio");
		String sClave = (String)daf.get("claveUsuarioServicio");
		UsuarioVO empUsuario = (UsuarioVO) session.getAttribute("datosUsuario");
		IUsuarioBO userBO = new UsuarioBO();
		EmpresaACargoVO usuarioEncargado = null;
		long lRut = 0;
		int iClave = 0;
		logger.info("inicio dese " + devolver);

		if (( sRutUsuario==null || sRutUsuario.equals("") || sClave==null || sClave.equals("") ) && empUsuario.getRutusuarioAutenticado()==0) {
			return mapping.findForward("pideusuario");
		}
		if (empUsuario.getRutusuarioAutenticado()!=0) {
			lRut = empUsuario.getRutusuarioAutenticado();
			if (!ValidaSesionServicio.validar(empUsuario, serviciosValidos, iServicio) ) {
				if (serviciosValidos==null || serviciosValidos.length()==0) {
					usuarioEncargado = new EmpresaACargoVO();
					usuarioEncargado.setRut(empUsuario.getRut());
					usuarioEncargado.setRutEncargado(empUsuario.getRutusuarioAutenticado());
					Collection lstServ = userBO.consultaServicios(usuarioEncargado);
					Iterator it = lstServ.iterator();
					StringBuffer sb = new StringBuffer();
					while (it.hasNext()) {
						AccesoServicioVO asVO = (AccesoServicioVO) it.next();
						if (asVO.isHabilitado()) {
							sb.append(asVO.getCodigo() + ", ");
							logger.info("servicios " + asVO.getCodigo());
						}
					}
					serviciosValidos = sb.toString();
					session.setAttribute("servicioLogon.serviciosValidos", serviciosValidos);
					if (!ValidaSesionServicio.validar(empUsuario, serviciosValidos, iServicio) ) {
						usuarioEncargado = null;
					}
				}

			} else {
				return mapping.findForward(devolver);
			}
		} else {
			lRut = Long.parseLong(sRutUsuario.substring(0, sRutUsuario.length()-2));
			try { 
				iClave = Integer.parseInt(sClave);
				usuarioEncargado = userBO.autenticar(empUsuario.getRut(), lRut, iClave, iServicio);
			} catch (Exception ex) {
				iClave=0;
			} 
			
		}
		
		
		logger.info("getAutenticacion: " + lRut);
		if (usuarioEncargado==null) {
			if (empUsuario.getRutusuarioAutenticado()!=0) {
				session.setAttribute("message", "label.consulta.creditos.noAcceso");
				session.setAttribute("info", "label.consulta.creditos.noAccesoInfo");
				return mapping.findForward("message");				
			} else {
				session.setAttribute("servicioLogon.mensaje", "label.consulta.creditos.noAcceso");
				return mapping.findForward("pideusuario");
			}
		}
		
		System.out.println("LogonServicioAction: devolviendome a " + devolver);
		empUsuario.setRutusuarioAutenticado(lRut);
		session.setAttribute("datosUsuario", empUsuario);


		return mapping.findForward(devolver);
		
	}

	

}
