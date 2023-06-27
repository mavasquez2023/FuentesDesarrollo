package cl.laaraucana.autoconsulta.ui.actions.modulo2;

import cl.araucana.autoconsulta.serv.ServicesAutoconsultaDelegate;
import cl.araucana.autoconsulta.vo.AfiliadoVO;
import cl.araucana.autoconsulta.vo.UsuarioVO;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class Consultas extends Action
{
  private static Logger logger = Logger.getLogger(Consultas.class);
  private static final String SUCCESS = "consultaOk";
  private static final String MONTO_PRE = "montoPre";

  
  public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
	  
    HttpSession session = request.getSession(true);
    ServicesAutoconsultaDelegate delegate = new ServicesAutoconsultaDelegate();
    String target = SUCCESS;
    UsuarioVO usuario = (UsuarioVO)session.getAttribute("datosUsuario");
    AfiliadoVO afiliado = null;
    boolean esEmpresa = usuario.isEsEmpresa() || usuario.isEsEmpresaPublica() || usuario.isEsEncargadoEmpresa();
    long lRutAfiliado = 0; 


    session.removeAttribute("message");
    session.removeAttribute("info");
    session.removeAttribute("validation.message");
    session.removeAttribute("validation.info");

	String sMnu = (String)request.getParameter("md2_opcion");
	if (sMnu==null || sMnu=="") {
		session.setAttribute("md2_opcion","cns");
		session.setAttribute("md2_opcionMnu","0");
	}

	if ((!usuario.isEsEmpresa() || !usuario.isEsEmpresaPublica()) && (sMnu==null || sMnu=="")) {
		long montoPreaprobado = delegate.getMontoCreditoPreaprobado(usuario.getRut());
		if (montoPreaprobado > 0L) {
			session.setAttribute("afiliado.rut", new Long(lRutAfiliado).toString());
			session.setAttribute("afiliado.fullRut", usuario.getFullRut());
			session.setAttribute("afiliado.nombre",usuario.getNombre());
			System.out.println("consultas montoPreaprobado [" + montoPreaprobado + "]");
			session.setAttribute("montoPreAprobado", new Long(montoPreaprobado));
			return mapping.findForward(MONTO_PRE);
		}
	}

    session.setAttribute("usuario.nombre", usuario.getNombre());
    session.setAttribute("usuario.rut", usuario.getFullRut());
    session.setAttribute("usuario.rol", "esPersona");
    session.removeAttribute("empresa.nombre");
    

    SimpleDateFormat formateador = new SimpleDateFormat(
    		   "dd 'de' MMMM 'de' yyyy", new Locale("es_ES"));
    Date fechaDate = new Date();
    String fecha = formateador.format(fechaDate);
    session.setAttribute("modulo2.fechahoy", fecha);
    
    session.removeAttribute("pideEmpleado");
    
    if (session.getAttribute("afiliado.fullRut") == null) {
    	session.setAttribute("afiliado.fullRut", "");
    }

    if (esEmpresa) {
    	session.setAttribute("pideEmpleado", "pideEmpleado");
    	session.setAttribute("afiliado.rut", "");
    	//session.setAttribute("afiliado.fullRut", "");
    	session.setAttribute("afiliado.nombre", "");

    	String sRut = (String)request.getParameter("rutAfiliado");
    	String sMnuOpc = (String)request.getParameter("md2_opcionMnu");
    	if (sMnuOpc==null || sMnuOpc=="") sMnuOpc="0"; 
    	session.setAttribute("md2_opcionMnu", sMnuOpc);
    	
    	if (sRut==null || sRut.length()<5) {
    		return mapping.findForward(target);
    	}
    	
    	session.setAttribute("afiliado.fullRut", sRut);
    	try {lRutAfiliado = Long.parseLong(sRut.substring(0, sRut.indexOf('-')).trim()); } catch(Exception ex) {lRutAfiliado=0;}
		if (lRutAfiliado==0) {
			return mapping.findForward(target);
		}
		afiliado = delegate.getDatosEmpleado( lRutAfiliado, usuario.getRut());
		if (afiliado==null) {
			session.setAttribute( "validation.message", "label.consulta.creditos.noPertenece");
			session.setAttribute( "validation.info", "label.consulta.creditos.noPertenece");
			return mapping.findForward(target);	
		}
		if (usuario.isEsEncargadoEmpresa()) {
			if (!delegate.usuarioPuedeConsultarPorAfiliado(usuario.getRutusuarioAutenticado(), usuario.getRut(), afiliado) ) {
				session.setAttribute( "validation.message", "label.consulta.creditos.noPertenece");
				session.setAttribute( "validation.info", "label.consulta.creditos.noPertenece");
				return mapping.findForward(target);	
			}
		}
		target = SUCCESS;
		session.setAttribute("afiliado.rut", new Long(lRutAfiliado).toString());
    	session.setAttribute("afiliado.fullRut", afiliado.getFullRut());
    	session.setAttribute("afiliado.nombre",afiliado.getFullNombre());

    } else {
    	target = SUCCESS;
    	lRutAfiliado = usuario.getRut();
    	session.setAttribute("afiliado.rut",new Long(usuario.getRut()).toString() );
    	session.setAttribute("afiliado.fullRut",usuario.getFullRut());
    	session.setAttribute("afiliado.nombre",usuario.getNombre());    	
    }
    session.removeAttribute("pideEmpleado");

    System.out.println("en Consultas voy a [" + target + "]");

    logger.debug("Presenta pagina: " + target);
    return mapping.findForward(target);
  }
  
}
