package cl.laaraucana.autoconsulta.ui.actions.modulo2;

import cl.araucana.autoconsulta.serv.ServicesAutoconsultaDelegate;
import cl.araucana.autoconsulta.vo.AfiliadoVO;
import cl.araucana.autoconsulta.vo.CertificadoAsignacionFamiliarVO;
import cl.araucana.autoconsulta.vo.CertificadoDeudaVigenteVO;
import cl.araucana.autoconsulta.vo.EmpresaACargoVO;
import cl.araucana.autoconsulta.vo.EmpresaVO;
import cl.araucana.autoconsulta.vo.UsuarioVO;
import cl.araucana.autoconsulta.ws.CertificadoProxy;
import cl.araucana.autoconsulta.ws.to.ResultadoTO;
import cl.araucana.common.BusinessException;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.MessageResources;
import org.apache.struts.validator.DynaValidatorActionForm;

public class GetAfiliacion extends Action
{
  private static Logger logger = Logger.getLogger(GetAfiliacion.class);
  public static final String GLOBAL_FORWARD_exito = "certificadoAfiliacion";
  public static final String GLOBAL_FORWARD_definirEmpleado = "definirEmpleado";

  public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
		HttpSession session = request.getSession(true);
		UsuarioVO usuario = (UsuarioVO)session.getAttribute("datosUsuario");
		ServicesAutoconsultaDelegate delegate = new ServicesAutoconsultaDelegate();
		AfiliadoVO afiliado = null;
		String dispositivo = request.getRemoteAddr();
		EmpresaVO empleador=null;
		long lRutAfiliado = 0;
		long lRutEmpleador = 0;
		String target=GLOBAL_FORWARD_exito;
		String nombreConsulta = "";
		String rutConsulta= "";
		

		
		boolean bAfiliado = false;
		boolean bEmpleadoP = false;
		boolean bPensionado = false;
		ResultadoTO afiliacion = null;
		
			
        request.removeAttribute("message");
        request.removeAttribute("info");

		CertificadoProxy proxy = new CertificadoProxy();

		int wsErrorCode = 0;
		String wsErrorMsg = null;
		try
		{
		  MessageResources messageResources = getResources(request);
		  proxy.setEndpoint(messageResources.getMessage("ws.certificado.afiliacion"));

		  afiliacion = proxy.obtenerDataCertificado(String.valueOf(lRutAfiliado), usuario.getDv());

		  wsErrorCode = afiliacion.getCodigo();
		  wsErrorMsg = afiliacion.getError();

		  if (afiliacion.getCertificadoTO().getTipo().equals("AF")) {
		    bAfiliado = true;
		  } else if (afiliacion.getCertificadoTO().getTipo().toString().equals("EP")) {
		    bEmpleadoP = true;
		  }
		  else if (afiliacion.getCertificadoTO().getTipo().toString().equals("PE")) {
		    bPensionado = true;
		  }
		  logger.debug("resultado :" + afiliacion.getCertificadoTO().getNombre());
		  logger.debug("resultado :" + afiliacion.getCertificadoTO().getTipo());
		  logger.debug("resultado :" + afiliacion.getCertificadoTO().getFechaAfiliacion());
		  logger.debug("resultado :" + afiliacion.getCertificadoTO().getRut());
		  logger.debug("resultado :" + afiliacion.getCertificadoTO().getDv());
		}
		catch (Exception be)
		{
		  if ((wsErrorCode != 0) && (!wsErrorMsg.equals(""))) {
		    request.setAttribute("message", "Error al obtener informacion del Certificadox");
		    request.setAttribute("info", wsErrorMsg);
		  }
		  else {
		    request.setAttribute("message", "Error al obtener informacion del Certificadox");
		    request.setAttribute("info", "No se ha podido establecer la conexion, por favor intente nuevamente");
		  }
		  return mapping.findForward(target);
		}
		SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
		SimpleDateFormat unformat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());

		Date fechaDesdeDate = unformat.parse(afiliacion.getCertificadoTO().getFechaAfiliacion());
		String fechaDesde = formatoFecha.format(fechaDesdeDate);

		logger.debug("Fecha Desde: " + fechaDesde);
		session.setAttribute("fechaDesde", fechaDesde);
		logger.debug("*******************************************");
		System.out.println("Fecha Afiliacion: " + fechaDesde);

		if (bAfiliado) {
		  System.out.println("Es afiliado: ");
		  logger.debug("Es Afiliado");
		  session.setAttribute("afiliacion", afiliacion);
		  session.setAttribute("tipo", "Afiliado");
		} else if (bEmpleadoP) {
		  logger.debug("Es Empleado Publico");
		  session.setAttribute("afiliacion", afiliacion);
		  session.setAttribute("tipo", "Afiliado");
		} else if (bPensionado) {
		  logger.debug("Es Pensionado");
		  session.setAttribute("pensionado", afiliacion);
		  session.setAttribute("tipo", "Pensionado");
		} else {
			  logger.debug("Es otro");
			  session.setAttribute("afiliacion", afiliacion);
			  session.setAttribute("tipo", "Afiliado");			
		}


		session.setAttribute("fechaHoy", new Date());

		logger.debug("A desplegar Afiliacion");

		return mapping.findForward(target);


  }
}


