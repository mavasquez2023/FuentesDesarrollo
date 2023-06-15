package cl.laaraucana.autoconsulta.ui.actions.modulo2;

import cl.araucana.autoconsulta.serv.ServicesAutoconsultaDelegate;
import cl.araucana.autoconsulta.vo.AfiliadoVO;
import cl.araucana.autoconsulta.vo.CertificadoAsignacionFamiliarVO;
import cl.araucana.common.BusinessException;
import cl.araucana.autoconsulta.vo.CertificadoDeudaVigenteVO;
import cl.araucana.autoconsulta.vo.CodigoDescripcionVO;
import cl.araucana.autoconsulta.vo.EmpresaACargoVO;
import cl.araucana.autoconsulta.vo.LicenciaMedicaVO;
import cl.araucana.autoconsulta.vo.StringVO;
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

public class GetEstadoLicenciasMedica extends Action
{
  private static Logger logger = Logger.getLogger(GetEstadoLicenciasMedica.class);
  public static final String GLOBAL_FORWARD_exito = "listaLicencias";
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

		try {lRutAfiliado = Long.parseLong((String)session.getAttribute("afiliado.rut"));} catch (Exception ex) {}
		lRutEmpleador = usuario.getRut();

	    request.removeAttribute("message");
	    request.removeAttribute("info");
	

	System.out.println("lRutAfiliado [" + lRutAfiliado + "]");

	  boolean tieneLicencias = false;

	  Collection licencias = delegate.getConsultaLicenciasMedicas(lRutAfiliado, dispositivo);
	  logger.debug("licencias: " + licencias.size());
	  Iterator it = licencias.iterator();

	  while (it.hasNext()) {
	    LicenciaMedicaVO licencia = (LicenciaMedicaVO)it.next();

	    if ((licencia.getCodigoEstadoLicencia().equals("1")) && 
	      (licencia.getVisada() != 2))
	    {
	      Collection listaOficinasPago = 
	        (Collection)session.getAttribute("lista.oficinas.pago");

	      if ((listaOficinasPago == null) || (listaOficinasPago.isEmpty())) {
	        listaOficinasPago = delegate.getListaOficinasPago();
	        session.setAttribute("lista.oficinas.pago", listaOficinasPago);
	      }

	      if (licencia.getOficinaPago() == null) {
	        System.out.println("Entro al If");
	        licencia.setOficinaPago(
	          traducirOficinaPago(listaOficinasPago, 
	          licencia.getCodOficinaPago()));
	      }
	      Collection listaObservaciones = 
	        (Collection)session.getAttribute("lista.observaciones");

	      if ((listaObservaciones == null) || (listaObservaciones.isEmpty())) {
	        listaObservaciones = delegate.getListaObservaciones();
	        session.setAttribute("lista.observaciones", listaObservaciones);
	      }
	      licencia = traducirObservaciones(listaObservaciones, licencia);
	    }
	    else {
	      if (licencia.getVisada() != 1)
	        continue;
	      Collection listaObservaciones = 
	        (Collection)session.getAttribute("lista.observaciones");

	      if ((listaObservaciones == null) || (listaObservaciones.isEmpty())) {
	        listaObservaciones = delegate.getListaObservaciones();
	        session.setAttribute("lista.observaciones", 
	          listaObservaciones);
	      }

	      licencia = traducirObservaciones(listaObservaciones, licencia);
	      logger.debug("rut Afiliado***************: " + lRutAfiliado);
	      logger.debug("Numero Licencia***************: " + licencia.getNumeroLicencia());
	      Collection listaObs = delegate.listaObservacionesCompin(
	        lRutAfiliado, licencia.getNumeroLicencia());
	      StringVO newStringVO = new StringVO();

	      if (!listaObs.isEmpty()) {
	        Iterator i = listaObs.iterator();

	        while (i.hasNext()) {
	          StringVO stringVO = (StringVO)i.next();
	          if (newStringVO.getTexto() == null)
	            newStringVO.setTexto(stringVO.getTexto());
	          else {
	            newStringVO.setTexto(newStringVO.getTexto() + " " + 
	              stringVO.getTexto());
	          }
	        }

	        licencia.getListaObservacionesCompin().add(newStringVO);
	      }
	    }

	  }

	  session.setAttribute("lista.licencias", licencias);
	  session.setAttribute("nombre", nombreConsulta);
	  session.setAttribute("rut", rutConsulta);
	  session.setAttribute("fechaHoy", new Date());
	  System.out.println("A desplegar Lista licencias [" +  target + "]");
	  return mapping.findForward(target);
	}

	public String traducirOficinaPago(Collection listaOficinasPago, int codigo)
	{
	  String glosa = null;

	  logger.debug("Son: " + listaOficinasPago.size() + " Oficinas");
	  Iterator iOficina = listaOficinasPago.iterator();
	  while (iOficina.hasNext()) {
	    CodigoDescripcionVO traductor = (CodigoDescripcionVO)iOficina.next();
	    logger.debug("Licencia Oficina Codigo: " + codigo);
	    logger.debug("Traductor Codigo: " + traductor.getCodigo());
	    if (codigo == traductor.getCodigo()) {
	      glosa = traductor.getDescripcion();
	      break;
	    }
	  }
	  return glosa;
	}

	public LicenciaMedicaVO traducirObservaciones(Collection listaObservaciones, LicenciaMedicaVO licencia)
	{
	  logger.debug("Son: " + listaObservaciones.size() + " Observaciones Definidas");
	  boolean encontroObs1 = false;
	  boolean encontroObs2 = false;
	  boolean encontroObs3 = false;
	  Iterator iObservaciones = listaObservaciones.iterator();

	  while (iObservaciones.hasNext()) {
	    if ((encontroObs1) && (encontroObs2) && (encontroObs3)) break;
	    CodigoDescripcionVO traductor = 
	      (CodigoDescripcionVO)iObservaciones.next();
	    logger.debug("Licencia Observacion1 Codigo: " + 
	      licencia.getCodigoObservacion1());
	    logger.debug("Licencia Observacion2 Codigo: " + 
	      licencia.getCodigoObservacion2());
	    logger.debug("Licencia Observacion3 Codigo: " + 
	      licencia.getCodigoObservacion3());
	    logger.debug("Traductor Codigo: " + traductor.getCodigo());
	    logger.debug("Traductor Descripcion: " + traductor.getDescripcion());

	    if (licencia.getCodigoObservacion1() == traductor.getCodigo()) {
	      licencia.setObservacion1(traductor.getDescripcion());
	      encontroObs1 = true;
	    }
	    if (licencia.getCodigoObservacion2() == traductor.getCodigo()) {
	      licencia.setObservacion2(traductor.getDescripcion());
	      encontroObs2 = true;
	    }
	    if (licencia.getCodigoObservacion3() == traductor.getCodigo()) {
	      licencia.setObservacion3(traductor.getDescripcion());
	      encontroObs3 = true; continue;

	      //break;
	    }
	  }

	  return licencia;
	}

}


