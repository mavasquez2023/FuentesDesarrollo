package cl.laaraucana.autoconsulta.ui.actions.modulo2;

import cl.araucana.autoconsulta.serv.ServicesAutoconsultaDelegate;
import cl.araucana.autoconsulta.vo.AfiliadoVO;
import cl.araucana.autoconsulta.vo.CuentaAhorroVO;
import cl.araucana.autoconsulta.vo.CuentasAhorroRutVO;
import cl.araucana.autoconsulta.vo.EmpresaVO;
import cl.araucana.autoconsulta.vo.UsuarioVO;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
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
import cl.araucana.autoconsulta.vo.CertificadoDeudaVigenteVO;
import cl.laaraucana.credito.to.CreditoTO;

public class Certificados extends Action
{
  private static Logger logger = Logger.getLogger(Certificados.class);
  private static final String SUCCESS = "certificadoOk";
  private static final String PIDEEMPLEADO = "pideEmpleado";

  
  
  public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
	  
    HttpSession session = request.getSession(true);
    ServicesAutoconsultaDelegate delegate = new ServicesAutoconsultaDelegate();
    String target = SUCCESS;
    String dispositivo = request.getRemoteAddr();
    UsuarioVO usuario = (UsuarioVO)session.getAttribute("datosUsuario");
    AfiliadoVO afiliado = null;
    boolean esEmpresa = usuario.isEsEmpresa() || usuario.isEsEmpresaPublica() || usuario.isEsEncargadoEmpresa();
    long lRutAfiliado = 0; 
    String sRutAfiliadoDigito="";
    Collection empleadores;

    session.removeAttribute("message");
    session.removeAttribute("info");
    session.removeAttribute("validation.message");
    session.removeAttribute("validation.info");
    session.removeAttribute("pideEmpleado");

    String sMnu = (String)request.getParameter("md2_opcion");
    if (sMnu==null || sMnu=="") {
    	session.setAttribute("md2_opcion","cert");
    	session.setAttribute("md2_opcionMnu","0");
    }
	String sMnuOpc = (String)request.getParameter("md2_opcionMnu");
	if (sMnuOpc==null || sMnuOpc=="") sMnuOpc="0"; 
	session.setAttribute("md2_opcionMnu", sMnuOpc);

    if (esEmpresa) {
    	session.setAttribute("pideEmpleado", "pideEmpleado");
    	session.setAttribute("afiliado.rut", "");
    	//session.setAttribute("afiliado.fullRut", "");
    	session.setAttribute("afiliado.nombre", "");

    	String sRut = (String)request.getParameter("rutAfiliado");

    	if (sRut==null || sRut.length()<5) {
    		
    		return mapping.findForward(target);
    	}
    	
    	sRut = sRut.trim();
    	session.setAttribute("afiliado.fullRut", sRut);
    	try {lRutAfiliado = Long.parseLong(sRut.substring(0, sRut.indexOf('-')).trim()); } catch(Exception ex) {lRutAfiliado=0;}
    	try {sRutAfiliadoDigito = sRut.substring(sRut.indexOf('-')+1,sRut.indexOf('-')+2); } catch(Exception ex) {sRutAfiliadoDigito="";}
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
		session.setAttribute("afiliado.digito",  sRutAfiliadoDigito);
		System.out.println("sRutAfiliadoDigito [" + sRutAfiliadoDigito + "]");
    	session.setAttribute("afiliado.fullRut", afiliado.getFullRut());
    	session.setAttribute("afiliado.nombre",afiliado.getFullNombre());

    } else {
    	target = SUCCESS;
    	lRutAfiliado = usuario.getRut();
    	session.setAttribute("afiliado.rut",new Long(usuario.getRut()).toString() );
    	session.setAttribute("afiliado.digito",  usuario.getDv());
    	session.setAttribute("afiliado.fullRut",usuario.getFullRut());
    	session.setAttribute("afiliado.nombre",usuario.getNombre());
    }
    

    session.setAttribute("usuario.nombre", usuario.getNombre());
    session.setAttribute("usuario.rut", usuario.getFullRut());
    session.removeAttribute("empresa.nombre");

    
    SimpleDateFormat formateador = new SimpleDateFormat(
 		   "dd 'de' MMMM 'de' yyyy", new Locale("es_ES"));
    Date fechaDate = new Date();
    String fecha = formateador.format(fechaDate);
    session.setAttribute("modulo2.fechahoy", fecha);

    
    empleadores = delegate.getEmpleadoresByEmpleado(lRutAfiliado, dispositivo);
    session.setAttribute("empleadores", empleadores);
	empleadores = (Collection)session.getAttribute("empleadores");
	Iterator i = empleadores.iterator();
	EmpresaVO empleador = null;
	while (i.hasNext()) {
		empleador = (EmpresaVO) i.next();
	}
	session.setAttribute("empleador", empleador);
	session.removeAttribute("pideEmpleado");

    System.out.println("Presenta pagina: " + target);
    return mapping.findForward(target);
  }
  
}
