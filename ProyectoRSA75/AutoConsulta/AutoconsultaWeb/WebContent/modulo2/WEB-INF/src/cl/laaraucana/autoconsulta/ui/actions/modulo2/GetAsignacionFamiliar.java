package cl.laaraucana.autoconsulta.ui.actions.modulo2;

import cl.araucana.autoconsulta.serv.ServicesAutoconsultaDelegate;
import cl.araucana.autoconsulta.vo.AfiliadoVO;
import cl.araucana.autoconsulta.vo.CertificadoAsignacionFamiliarVO;
import cl.araucana.autoconsulta.vo.CertificadoDeudaVigenteVO;
import cl.araucana.autoconsulta.vo.EmpresaACargoVO;
import cl.araucana.autoconsulta.vo.EmpresaVO;
import cl.araucana.autoconsulta.vo.UsuarioVO;
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
import org.apache.struts.validator.DynaValidatorActionForm;

public class GetAsignacionFamiliar extends Action
{
  private static Logger logger = Logger.getLogger(GetAsignacionFamiliar.class);
  public static final String GLOBAL_FORWARD_exito = "certificadoAsignacionFamiliar";
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

		int tipoConsulta = 1;

			lRutAfiliado = Long.parseLong((String)session.getAttribute("afiliado.rut"));
			empleador = (EmpresaVO)session.getAttribute("empleador");
		
	        request.removeAttribute("message");
	        request.removeAttribute("info");


	System.out.println("lRutAfiliado [" + lRutAfiliado + "]");

			nombreConsulta = usuario.getNombre();
			rutConsulta = usuario.getFullRut();
		    session.setAttribute("nombre", nombreConsulta);
		    session.setAttribute("rut", rutConsulta);

		    CertificadoAsignacionFamiliarVO asignacionFamiliar = null;
		    try {
		      asignacionFamiliar = 
		        delegate.getCertificadoAsignacionFamiliarByRut(
		        		lRutAfiliado, 
		        		empleador.getRut(), 
		        tipoConsulta, 
		        nombreConsulta, 
		        rutConsulta);
		    }         
		    catch (BusinessException be)
		    {
		      if (be
		        .getCode()
		        .equalsIgnoreCase("CCAF_AUTO_ASFAMINCONSISTENTE")) {
		        request.setAttribute("message", be.getMessage());
		        request.setAttribute("info", be.getMsgAdic());

		        return mapping.findForward(target);
		      }

		      throw be;
		    }

		    if (asignacionFamiliar
		      .getTipoCargaConsultado()
		      .equals("A"))
		      session.setAttribute("activa", "yes");
		    else {
		      session.removeAttribute("activa");
		    }

		    if ((!asignacionFamiliar
		      .getCodigoEstadoEmpresa()
		      .equals("A")) && 
		      (asignacionFamiliar.getTipoCargaConsultado().equals(
		      "A")))
		    {
		      GregorianCalendar fechaAfiliacion = 
		        new GregorianCalendar();
		      SimpleDateFormat formatoFecha = 
		        new SimpleDateFormat(
		        "dd/MM/yyyy", 
		        Locale.getDefault());
		      int dia = 
		        Integer.parseInt(
		        asignacionFamiliar
		        .getFechaAfiliacion()
		        .substring(
		        8, 
		        10));
		      int mes = 
		        Integer.parseInt(
		        asignacionFamiliar
		        .getFechaAfiliacion()
		        .substring(
		        5, 
		        7));
		      int anio = 
		        Integer.parseInt(
		        asignacionFamiliar
		        .getFechaAfiliacion()
		        .substring(
		        0, 
		        4));
		      fechaAfiliacion.set(anio, mes - 1, dia);
		      fechaAfiliacion.add(5, -1);
		      Date fechaDesde = 
		        formatoFecha.parse(
		        fechaAfiliacion.get(5) + 
		        "/" + (
		        fechaAfiliacion.get(2) + 1) + 
		        "/" + 
		        fechaAfiliacion.get(1));
		      logger.debug("Fecha Desde: " + fechaDesde);
		      session.setAttribute("fechaDesde", fechaDesde);
		    } else {
		      session.removeAttribute("fechaDesde");
		    }

		    logger.debug("*******************************************");
		    logger.debug("Check para nombre de empresa");
		    if (asignacionFamiliar != null)
		    {
		      if (asignacionFamiliar
		        .getCodigoEstadoEmpresa()
		        .equalsIgnoreCase("S")) {
		        asignacionFamiliar.setNombreEmpresa("");
		        logger.debug("Se ha limpiado nombre de empresa");
		      }
		      else if (asignacionFamiliar
		        .getCodigoEstadoEmpresa()
		        .equalsIgnoreCase(
		        "C"))
		      {
		        Collection historico = 
		          delegate.getUltimaEmpresaHistorica(lRutAfiliado);
		        logger.debug(
		          "Se encontro: " + (
		          historico != null ? historico.size() : 0) + 
		          " registros");
		        if ((historico != null) && (historico.size() > 0)) {
		          EmpresaVO emp = 
		            (EmpresaVO)historico.iterator().next();
		          logger.debug(
		            "***************** Empresa Encontrada!! => ");
		          logger.debug(
		            emp.getRut() + 
		            "/" + 
		            emp.getEstado() + 
		            "/" + 
		            emp.getNombre());
		          if (emp.getEstado().equalsIgnoreCase("S"))
		            asignacionFamiliar.setNombreEmpresa("");
		          logger.debug(
		            "Se ha limpiado nombre de empresa");
		        }
		      }

		    }

		    session.setAttribute(
		      "asignacionFamiliar", 
		      asignacionFamiliar);
		    logger.debug("A desplegar Asignación Familiar");


	    return mapping.findForward(target);

  }
}


