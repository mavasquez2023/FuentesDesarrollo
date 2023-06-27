package cl.laaraucana.autoconsulta.ui.actions.modulo2;

import cl.araucana.autoconsulta.serv.ServicesAutoconsultaDelegate;
import cl.araucana.autoconsulta.vo.AfiliadoVO;
import cl.araucana.autoconsulta.vo.EmpresaVO;
import cl.araucana.autoconsulta.vo.UsuarioVO;
import java.util.Collection;
import java.util.Date;
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

public class Modulo2Action extends Action
{
  private static Logger logger = Logger.getLogger(Modulo2Action.class);
  private static final String SUCCESS = "certificadoOk";
  private static final String NO_SUCCESS = "logoutpage";
  private static final String PIDEEMPLEADO = "pideEmpleado";

  
  public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
	  
    HttpSession session = request.getSession(true);
    ServicesAutoconsultaDelegate delegate = new ServicesAutoconsultaDelegate();
    String target = null;

    String dispositivo = request.getRemoteAddr();

    UsuarioVO usuario=null;
    boolean esEmpresa;
    boolean byPass=true;
    long lRutAfiliado = 0; 
    Collection empleadores;
    AfiliadoVO afiliado = null;


    try {
    	usuario = (UsuarioVO)session.getAttribute("datosUsuario");
    	esEmpresa=usuario.isEsEmpresa() || usuario.isEsEmpresaPublica() || usuario.isEsEncargadoEmpresa();
    } catch (Exception ex) {
    	return mapping.findForward(NO_SUCCESS);
    }

    String sMnu = (String)request.getParameter("md2_opcion");
    if (sMnu==null || sMnu=="") {
    	session.setAttribute("md2_opcion","cert");
    	session.setAttribute("md2_opcionMnu","0");
    }
    
    if (esEmpresa) {
    	target =  PIDEEMPLEADO;
    	session.setAttribute("afiliado.rut", "");
    	session.setAttribute("afiliado.fullRut", "");
    	session.setAttribute("afiliado.nombre", "");

    	String sRut = (String)request.getParameter("rutAfiliado");
    	String sMnuOpc = (String)request.getParameter("md2_opcionMnu");
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
			session.setAttribute( "validation.info", ".");
			return mapping.findForward(target);	
		}
		if (usuario.isEsEncargadoEmpresa()) {
			if (!delegate.usuarioPuedeConsultarPorAfiliado(usuario.getRutusuarioAutenticado(), usuario.getRut(), afiliado) ) {
				session.setAttribute( "validation.message", "label.consulta.creditos.noPertenece");
				session.setAttribute( "validation.info", "..");
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
    

    session.setAttribute("usuario.nombre", usuario.getNombre());
    session.setAttribute("usuario.rut", usuario.getFullRut());
    session.removeAttribute("empresa.nombre");

    session.setAttribute("modulo2.fechahoy", new Date());

    
    empleadores = delegate.getEmpleadoresByEmpleado(lRutAfiliado, dispositivo);
    session.setAttribute("empleadores", empleadores);
	empleadores = (Collection)session.getAttribute("empleadores");
	Iterator i = empleadores.iterator();
	EmpresaVO empleador = null;
	while (i.hasNext()) {
		empleador = (EmpresaVO) i.next();
	}
	session.setAttribute("empleador", empleador);

    logger.debug("Presenta pagina: " + target);
    return mapping.findForward(target);
  }
  
}
