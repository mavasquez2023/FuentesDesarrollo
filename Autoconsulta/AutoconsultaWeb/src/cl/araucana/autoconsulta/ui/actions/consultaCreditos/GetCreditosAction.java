package cl.araucana.autoconsulta.ui.actions.consultaCreditos;

import java.util.ArrayList;
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

import com.schema.util.FileSettings;

import cl.araucana.autoconsulta.bo.ICreditoBO;
import cl.araucana.autoconsulta.bo.impl.CreditoBO;
import cl.araucana.autoconsulta.serv.ServicesAutoconsultaDelegate;
import cl.araucana.autoconsulta.serv.ServicesCreditoDelegate;
import cl.araucana.autoconsulta.vo.AfiliadoVO;
import cl.araucana.autoconsulta.vo.EmpresaVO;
import cl.araucana.autoconsulta.vo.UsuarioVO;
import cl.araucana.common.env.AppConfig;
import cl.laaraucana.utils.ConstantesServicios;
import cl.laaraucana.utils.GetValueXML;
import cl.laaraucana.utils.ValidaSesionServicio;

/**
 * @version 	1.0
 * @author asepulveda
 */
public class GetCreditosAction extends Action {

	private static Logger logger =
		Logger.getLogger(GetCreditosAction.class);

	public static final String GLOBAL_FORWARD_consultaCreditos =
		"consultaCreditos";
	public static final String GLOBAL_FORWARD_definirEmpleado =
		"definirEmpleado";

	public static final String GLOBAL_FORWARD_autenticarServicio =
		"logonServicio";

	public ActionForward execute(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response)
		throws Exception {
		
		HttpSession session = request.getSession(true);
		DynaValidatorActionForm daf = (DynaValidatorActionForm) form;
		ServicesAutoconsultaDelegate delegate = new ServicesAutoconsultaDelegate();
		String target = GLOBAL_FORWARD_definirEmpleado;
		Collection empleadores;
		AfiliadoVO afiliado = null;
		//long lRutAfiliado = 0;
		//long lRutEmpleador = 0;

		logger.debug("inicio crd");
		logger.info("inicio crd");
		//String dispositivo = request.getRemoteAddr();
		
		session.removeAttribute("validation.message");
		session.removeAttribute("servicioLogon.devolverse");
		session.removeAttribute("servicioLogon.mensaje");
		session.removeAttribute("servicioLogon.titulo");
		session.removeAttribute("cnsCredito.volver");
		
		UsuarioVO usuario = (UsuarioVO) session.getAttribute("datosUsuario");
		usuario.setIpConexion(request.getRemoteAddr());
		String serviciosValidos = (String)session.getAttribute("servicioLogon.serviciosValidos");
		
		if (usuario.isEsEmpresa() || usuario.isEsEmpresaPublica()) {
			session.setAttribute("cnsCredito.volver", "si");
			session.setAttribute("servicioLogon.titulo", "label.consulta.creditos.tituloConsultaEmpresa");
			session.setAttribute("cnsCredito.titulo", "label.consulta.creditos.tituloConsultaEmpresa");
			if (!ValidaSesionServicio.validar(usuario, serviciosValidos, ConstantesServicios.SRV_CNSCREDITO)) {
				// se debe identificar el usuario de empresa
				session.setAttribute("servicioLogon.devolverse", "consultaCreditos");
				session.setAttribute("servicioLogon.servicioValidar", "" + ConstantesServicios.SRV_CNSCREDITO);
				target = GLOBAL_FORWARD_autenticarServicio;
				return mapping.findForward(target);
				
			} else {
				
				if (!daf.get("rut").equals("")) {
					//lRutAfiliado = Long.parseLong((String)daf.get("rut"));
					usuario.setRutAfiliado(Long.parseLong((String)daf.get("rut")));
					
					afiliado = delegate.getDatosEmpleado( usuario.getRutAfiliado(), usuario.getRut());
					if (afiliado==null) {
						session.setAttribute( "validation.message", "label.consulta.creditos.noPertenece");
						session.setAttribute( "volverA", "consultaCreditos");
						return mapping.findForward(target);
					}

					if (!delegate.usuarioPuedeConsultarPorAfiliado(usuario.getRutusuarioAutenticado(), usuario.getRut(), afiliado) ) {
						session.setAttribute( "validation.message", "label.consulta.creditos.noAutorizado");
						session.setAttribute( "volverA", "consultaCreditos");
						return mapping.findForward(target);
					}

				} else {
					return mapping.findForward(GLOBAL_FORWARD_definirEmpleado);
				}
			}

			session.removeAttribute("empleadores");
			session.setAttribute("afiliado.nombre", afiliado.getFullNombre());
			session.setAttribute("afiliado.rut", afiliado.getFullRut());
			//lRutEmpleador = usuario.getRut();
			usuario.setRutEmpresa(usuario.getRut());
			
		} else {
			// el usuario es afiliado
			//lRutAfiliado = usuario.getRut();
			//lRutEmpleador = usuario.getRut();
			usuario.setRutAfiliado(usuario.getRut());
			usuario.setRutEmpresa(usuario.getRut());
			
			empleadores = delegate.getEmpleadoresByEmpleado(usuario);
			if (!empleadores.isEmpty()) {
					Iterator iempleadores = empleadores.iterator();
					EmpresaVO empleador = (EmpresaVO) iempleadores.next();
					usuario.setRutEmpresa(empleador.getRut());
			}
			session.setAttribute("cnsCredito.titulo", "label.consulta.creditos.tituloConsultaAfiliado");
			session.setAttribute("afiliado.nombre", usuario.getNombre());
			session.setAttribute("afiliado.rut", usuario.getFullRut());
			session.setAttribute("empleadores", empleadores);
		}
		ICreditoBO creditoBO = new CreditoBO();
		//creditoBO.setRut(lRutAfiliado, lRutEmpleador);
		creditoBO.setRut(usuario);
		//logger.info("consultado creditos [" + lRutAfiliado + "," + lRutEmpleador + "]");
		delegate.insertarActividad(usuario, cl.araucana.autoconsulta.vo.ActividadVO.CONSULTA_CREDITOS_VIGENTES);
		Collection col = creditoBO.getCreditos();

		
		session.setAttribute("listaCreditos", col);
		session.setAttribute("fechaHoy", new java.util.Date());
		
		session.setAttribute("vistaDespliegue", "");
		
		
		logger.debug("A desplegar Consulta Créditos Vigentes");

		target = GLOBAL_FORWARD_consultaCreditos;

		return mapping.findForward(target);
	}

}
