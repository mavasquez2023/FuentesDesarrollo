package cl.araucana.autoconsulta.ui.actions.saldoCapitalFiniquito;


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

import cl.araucana.autoconsulta.serv.ServicesAutoconsultaDelegate;
import cl.araucana.autoconsulta.vo.AfiliadoVO;
import cl.araucana.autoconsulta.vo.EmpresaACargoVO;
import cl.araucana.autoconsulta.vo.UsuarioVO;

import cl.araucana.core.util.AbsoluteDate;

/**
 * @version 	1.0
 * @author advise
 */
public class GetSaldoCapitalFiniquitoAction extends Action {
	
	private static Logger logger =
		Logger.getLogger(GetSaldoCapitalFiniquitoAction.class);

	public static final String GLOBAL_FORWARD_certificadoSaldoCapitalFiniquito =
		"certificadoSaldoCapitalFiniquito";
	
	/*public static final String GLOBAL_FORWARD_certificadoSaldoCapitalFiniquito =
		"certificadoAfiliacion";*/
	
	public static final String GLOBAL_FORWARD_definirEmpleado =
		"definirEmpleado";
			
	public static final String MESSAGE_PAGE = "message";
	
	public static final String ERROR_MESSAGE_WS = "Error al obtener información del Certificado";

	public ActionForward execute(
			ActionMapping mapping
		  , ActionForm form
		  , HttpServletRequest request
		  , HttpServletResponse response
		  )throws Exception
		  {

		System.out.println("Ingresa Action Capital Finiquito");
		
		HttpSession session = request.getSession(true);
		//LPC 2011-03-17
		ServicesAutoconsultaDelegate delegate =
			new ServicesAutoconsultaDelegate();
		
		boolean Afiliado = false;
		boolean EmpleadoP = false;
		boolean Pensionado = false;
		String nombreCertificado = null;
		long rutCertificado = 0;
		String textRutCertificado = null;
		String dvCertificado = null;
		String target = null;
		DynaValidatorActionForm daf = (DynaValidatorActionForm) form;
		//LPC 2011-03-17 nueva variable
		boolean empleadoEncontrado = false;

		if (!daf.get("rut").equals("")) {
			session.setAttribute("rutDelEmpleado", daf.get("rut"));
			if (!daf.get("dv").equals(""))
			session.setAttribute("rutDVDelEmpleado", daf.get("dv"));
		}
		
		//cl.azurian.sce.manager.SimulacionManager sm;
		//String dispositivo = request.getRemoteAddr();
		//logger.debug("IP: " + dispositivo);

		session.removeAttribute("validation.message");
		session.setAttribute("flag", "1");
		
		//LPC 2011-03-17 nueva variable
		String textRutAfiliado = null;
		String textRutEmpresa = null;
		String subapp = (String) session.getAttribute("struts.subapplication");
		String specialDv = "?";

		UsuarioVO usuario = (UsuarioVO) session.getAttribute("datosUsuario");
		usuario.setIpConexion(request.getRemoteAddr());
		System.out.println("RutEncargado:"+ usuario.getRutusuarioAutenticado());
		
		
		
		if( usuario.getRutusuarioAutenticado() != 0){
								
			session.setAttribute("RutEncargado",""+usuario.getRutusuarioAutenticado());
		}else{
			session.setAttribute("RutEncargado",""+usuario.getRut());
		}
		
		
		System.out.println("RutEmpresa:"+ usuario.getRut());
		session.setAttribute("RutEmpresa",""+usuario.getRut());
		AbsoluteDate fechahoy = new AbsoluteDate();
		session.setAttribute("fechahoy", fechahoy);
		logger.debug("usuario.isEsEmpresa: " + usuario.isEsEmpresa());

		Collection listaDeEmpresas =
			(Collection) session.getAttribute("empresasACargo");

		Iterator empresaSeleccionada = null;
		//Solo se entra cuando es un encargado de empresa
		if (listaDeEmpresas != null)
			empresaSeleccionada = listaDeEmpresas.iterator();

		if (usuario.isEsEmpresa()) 
		{
			//LPC 2011-03-17 nuevo
//			Se itera si es que fuese encargado de empresa
			//Se hace al menos una vez, que es el caso de las empresas
			//System.out.println("bandera 1");
			do {
				//Si entra, es encargado empresa
				//System.out.println("bandera 2");
				if (empresaSeleccionada != null) {
					//System.out.println("bandera 3");
					textRutEmpresa =
						""
							+ ((EmpresaACargoVO) empresaSeleccionada.next())
								.getRut();
				//sino, es una empresa
				} else{
					//System.out.println("bandera 4");
					textRutEmpresa = "" + usuario.getRut();
				}
				textRutAfiliado = (String) daf.get("rut");
				
				logger.debug("textRutEmpresa: " + textRutEmpresa);
				logger.debug("textRutAfiliado: " + textRutAfiliado);
				//System.out.println("bandera 5");
				if (textRutAfiliado != null && textRutAfiliado.length() > 0) {
					// Esto ocurre cuando ya indico el rut del empleado
					// para el cual está sacando el certificado
					//System.out.println("bandera 6");
					logger.debug("textRutAfiliado: " + textRutAfiliado);
					// Ajusta Rut si es que viene desde el Módulo

					if (subapp != null
						&& subapp.equals("modulo")
						&& textRutAfiliado != null
						&& textRutAfiliado.length() > 3) {
						//System.out.println("bandera 7");
						logger.debug(
							"Ajustando rut por canal 'modulo': "
								+ textRutAfiliado);
						specialDv =
							textRutAfiliado.substring(
								textRutAfiliado.length() - 1);
						textRutAfiliado =
							textRutAfiliado.substring(
								0,
								textRutAfiliado.length() - 1);
						logger.debug("Nuevo rut: " + textRutAfiliado);
					}
					//System.out.println("bandera 8");
					AfiliadoVO afiliado =
						delegate.getDatosEmpleado(
							Long.parseLong(textRutAfiliado),
							Long.parseLong(textRutEmpresa));
					if (afiliado != null) {
						//System.out.println("bandera 9");
						empleadoEncontrado = true;
						if (usuario.isEsEmpresa()
							&& !usuario.isEsEncargadoEmpresa()) {
							nombreCertificado = afiliado.getFullNombre();
							textRutCertificado = afiliado.getFullRut();
							
							//LPC nuevo
							rutCertificado = afiliado.getRut();							
							dvCertificado = afiliado.getDv();
							
						} else if (
							usuario.isEsEncargadoEmpresa()
								&& delegate.usuarioPuedeConsultarPorAfiliado(
									usuario.getRutusuarioAutenticado(),
									Long.parseLong(textRutEmpresa),
									afiliado)) {
							//System.out.println("bandera 10");
							nombreCertificado = afiliado.getFullNombre();
							textRutCertificado = afiliado.getFullRut();
							
							//LPC nuevo
							rutCertificado = afiliado.getRut();
							dvCertificado = afiliado.getDv();
							
						} else {
							// Si el rut del empleado pertenece a la empresa, pero el encargado no
							// puede consultar por el
							// envía mensaje de error y solicita nuevo rut
							//System.out.println("bandera 11");
							target = GLOBAL_FORWARD_definirEmpleado;
							session.setAttribute(
								"validation.message",
								"errors.validation.noPertenceSucursalAutorizada");
							session.setAttribute(
								"volverA",
								"getCertificadoSaldoFiniquito");
							
							return mapping.findForward(target);
						}
						//System.out.println("bandera 12");
						break;
					} else {
						//System.out.println("bandera 13");
						continue;
						// Si el rut del empleado no pertenece a la empresa
						// envía mensaje de error y solicita nuevo rut
						//target = GLOBAL_FORWARD_definirEmpleado;
						//session.setAttribute(
						//"validation.message",
						//"errors.validation.noPertenceEmpresa");
						//session.setAttribute(
						//"volverA",
						//"getAsignacionFamiliar");
						//return mapping.findForward(target);
					}
				}
			} while (
				!empleadoEncontrado
					&& empresaSeleccionada != null
					&& empresaSeleccionada.hasNext());
			//System.out.println("bandera 14");
			if (!empleadoEncontrado && textRutAfiliado.length() != 0) {
				//Si el rut del empleado no pertenece a la empresa
				// envía mensaje de error y solicita nuevo rut
				//System.out.println("bandera 15");
				target = GLOBAL_FORWARD_definirEmpleado;
				session.setAttribute(
					"validation.message",
					"errors.validation.noPertenceEmpresa");
				session.setAttribute("volverA", "getCertificadoSaldoFiniquito");
				return mapping.findForward(target);
			}
			//System.out.println("bandera 16");
			
			//LPC 2011-03-17 el codigo original del if (usuario.isEsEmpresa()) 
			/*
			target = GLOBAL_FORWARD_definirEmpleado;
			session.setAttribute("validation.message","errors.validation.noPertenceSucursalAutorizada");
			session.setAttribute("volverA",	"getCertificadoAfiliacion");
			return mapping.findForward(target);
			*/
			
//			LPC 2011-03-17
			if (textRutAfiliado == null || textRutAfiliado.length() == 0) {
				// Esto ocurre si es empresa y no ha indicado para quien
				// es el el certificado
				logger.debug("Debe indicar rut del empleado");
				session.setAttribute("volverA", "getCertificadoSaldoFiniquito");
				target = GLOBAL_FORWARD_definirEmpleado;
				return mapping.findForward(target);
			}
			
		} 
	
		target = GLOBAL_FORWARD_certificadoSaldoCapitalFiniquito;
	

						
		return mapping.findForward(target);
	}		
}
