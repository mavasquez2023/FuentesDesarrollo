package cl.laaraucana.autoconsulta.ui.actions.modulo2;

import cl.araucana.autoconsulta.serv.ServicesAutoconsultaDelegate;
import cl.araucana.autoconsulta.vo.AfiliadoVO;
import cl.araucana.common.BusinessException;

import cl.araucana.autoconsulta.vo.CertificadoLicenciasMedicasVO;
import cl.araucana.autoconsulta.vo.CodigoDescripcionVO;
import cl.araucana.autoconsulta.vo.LicenciaMedicaCertificadoVO;

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

public class GetCertificadoLicenciasMedica extends Action
{
  private static Logger logger = Logger.getLogger(GetCertificadoLicenciasMedica.class);
  public static final String GLOBAL_FORWARD_exito = "certificadoLicencias";
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
			empleadores = delegate.getEmpleadoresByEmpleado(lRutAfiliado, dispositivo);

			/*
			session.setAttribute("afiliado.nombre", usuario.getNombre());
			session.setAttribute("afiliado.rut", usuario.getFullRut());
			session.setAttribute("empleadores", empleadores);
			session.setAttribute("modulo2.fechahoy", "hoy");
			session.setAttribute("numero.certificado", "1231231231");
*/
	        request.removeAttribute("message");
	        request.removeAttribute("info");


	System.out.println("lRutAfiliado [" + lRutAfiliado + "]");

		nombreConsulta = usuario.getNombre();
		rutConsulta = usuario.getFullRut();

	    
	    CertificadoLicenciasMedicasVO certificado = 
	    	  delegate.getCertificadoLicenciasMedicas(
	    	  lRutAfiliado, 
	    	  dispositivo, 
	    	  nombreConsulta, 
	    	  rutConsulta);
	    	if (certificado.isTieneLicencias())
	    	{
	    	  Collection listaAFP = 
	    	    (Collection)session.getAttribute(
	    	    "lista.instituciones.previsionales");
	    	  if ((listaAFP == null) || (listaAFP.isEmpty())) {
	    	    listaAFP = delegate.getListaInstitucionesPrevicionales();
	    	    session.setAttribute(
	    	      "lista.instituciones.previsionales", 
	    	      listaAFP);
	    	  }

	    	  certificado.setLicencias(
	    	    traducirAFP(listaAFP, certificado.getLicencias()));
	    	  session.setAttribute("tieneLicencias", "yes");
	    	} else {
	    	  session.removeAttribute("tieneLicencias");
	    	}
	    	session.setAttribute("certificado", certificado);
	    	session.setAttribute("nombre", nombreConsulta);
	    	session.setAttribute("rut", rutConsulta);      
		      

	    return mapping.findForward(target);
	    
  }
  
  
  public Collection traducirAFP(Collection listaAFP, Collection licencias)
  {
    int codigoParaTraducir = 0;
    logger.debug("Son: " + listaAFP.size() + " AFP");
    logger.debug("Son: " + licencias.size() + " licencias");
    Iterator ilicencias = licencias.iterator();
    while (ilicencias.hasNext()) {
      logger.debug("Otra de la lista de licencias");
      LicenciaMedicaCertificadoVO licencia = 
        (LicenciaMedicaCertificadoVO)ilicencias.next();
      logger.debug(
        "Código Licencia Original: " + 
        licencia.getCodInstitutoPrevisional());
      if (licencia.getCodInstitutoPrevisional() == 2000) {
        licencia.setNomInstitutoPrevisional("");
      } else {
        if (licencia.getCodInstitutoPrevisional() < 1000)
          codigoParaTraducir = licencia.getCodInstitutoPrevisional();
        else {
          codigoParaTraducir = 
            licencia.getCodInstitutoPrevisional() - 900;
        }
        Iterator iAFP = listaAFP.iterator();
        while (iAFP.hasNext()) {
          CodigoDescripcionVO traductor = 
            (CodigoDescripcionVO)iAFP.next();
          logger.debug("Licencia AFP Código: " + codigoParaTraducir);
          logger.debug(
            "Traductor AFP Código: " + traductor.getCodigo());
          if (codigoParaTraducir == traductor.getCodigo()) {
            licencia.setNomInstitutoPrevisional(
              traductor.getDescripcion());
            logger.debug("Encontro");
            break;
          }
        }
      }
    }
    return licencias;
  }

}


