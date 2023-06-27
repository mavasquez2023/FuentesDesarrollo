package cl.laaraucana.autoconsulta.ui.actions.modulo2;

import cl.araucana.autoconsulta.serv.ServicesAutoconsultaDelegate;
import cl.araucana.autoconsulta.vo.AfiliadoVO;
import cl.araucana.autoconsulta.vo.CertificadoAsignacionFamiliarVO;
import cl.araucana.common.BusinessException;
import cl.araucana.autoconsulta.vo.CertificadoDeudaVigenteVO;
import cl.araucana.autoconsulta.vo.EmpresaACargoVO;
import cl.araucana.autoconsulta.vo.UsuarioVO;
import cl.araucana.autoconsulta.vo.EmpresaVO;
import java.util.Collection;
import java.util.Date;
import java.text.SimpleDateFormat;
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
import org.apache.struts.validator.DynaValidatorActionForm;

public class GetDeudaVigente extends Action
{
  private static Logger logger = Logger.getLogger(GetDeudaVigente.class);
  public static final String GLOBAL_FORWARD_exito = "certificadoDeudaVigente";
  public static final String GLOBAL_FORWARD_definirEmpleado = "definirEmpleado";

  public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
		HttpSession session = request.getSession(true);
		UsuarioVO usuario = (UsuarioVO)session.getAttribute("datosUsuario");
		ServicesAutoconsultaDelegate delegate = new ServicesAutoconsultaDelegate();
		AfiliadoVO afiliado = null;
		String dispositivo = request.getRemoteAddr();
		Collection empleadores;
		long lRutAfiliado = 0;
		long lRutEmpleador = 0;
		String target=GLOBAL_FORWARD_exito;
		String nombreConsulta = "";
		String rutConsulta= "";

			lRutAfiliado = usuario.getRut();
			lRutEmpleador = usuario.getRut();

	        request.removeAttribute("message");
	        request.removeAttribute("info");
	

	System.out.println("lRutAfiliado [" + lRutAfiliado + "]");

			nombreConsulta = usuario.getNombre();
			rutConsulta = usuario.getFullRut();
			CertificadoDeudaVigenteVO deudaVigente = 
		        delegate.getCertificadoDeudaVigenteByRut(
		        lRutAfiliado, 
		        dispositivo, 
		        nombreConsulta, 
		        rutConsulta);
		      if (deudaVigente.getDeudaTotal() > 0)
		        session.setAttribute("tieneDeuda", "yes");
		      else {
		        session.removeAttribute("tieneDeuda");
		      }
		      session.setAttribute("deudaVigente", deudaVigente);
		    	    
	    return mapping.findForward(target);
	    
  }
}


