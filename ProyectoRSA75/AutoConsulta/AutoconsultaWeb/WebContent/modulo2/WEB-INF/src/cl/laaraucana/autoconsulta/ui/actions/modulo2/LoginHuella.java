package cl.laaraucana.autoconsulta.ui.actions.modulo2;

import cl.araucana.autoconsulta.serv.ServicesAutoconsultaDelegate;
import cl.araucana.autoconsulta.vo.AfiliadoVO;
import cl.araucana.autoconsulta.vo.CuentaAhorroVO;
import cl.araucana.autoconsulta.vo.CuentasAhorroRutVO;
import cl.araucana.autoconsulta.vo.EmpresaACargoVO;
import cl.araucana.autoconsulta.vo.EmpresaVO;
import cl.araucana.autoconsulta.vo.UsuarioVO;
import cl.araucana.core.util.UserPrincipal;
import cl.araucana.core.util.http.DecodedHttpServletRequest;
import cl.araucana.core.util.http.HttpUserPrincipal;
import cl.araucana.core.util.http.LoginFilter;
import cl.araucana.core.util.http.Router;

import java.security.Principal;
import java.util.Collection;
import java.util.Date;
import java.util.Hashtable;
import java.util.Iterator;

import javax.security.auth.login.LoginContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorActionForm;



public class LoginHuella extends Action
{
  private static Logger logger = Logger.getLogger(LoginHuella.class);
  public static final String GLOBAL_FORWARD_welcome = "welcome";
  public static final String GLOBAL_FORWARD_loginpage = "loginHuellaPage";
  public static final String GLOBAL_FORWARD_logoutpage = "logoutHuellaPage";
  public static final String GLOBAL_FORWARD_seleccionaPerfil = "seleccionaPerfil";
  public static final String GLOBAL_FORWARD_seleccionaEmpresaACargo = "seleccionaEmpresaACargo";

  
  public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  throws Exception
{
  HttpSession session = request.getSession(true);

  String subapp = (String)session.getAttribute("struts.subapplication");
  System.out.println("---> LoginHuella *****subapp: " + subapp);
  

  session.removeAttribute("security.message");

  ServicesAutoconsultaDelegate delegate = new ServicesAutoconsultaDelegate();

  String target = null;
  UsuarioVO usuario = null;
  String user = null;
  String tipoUsuario = null;
  long rutUsuario = -1L;
  
  
  System.out.println("sess [" + (String)session.getAttribute("chkUser") + "] param - rq att[" + (String)request.getAttribute("chkUser") + "] rq par[ " + (String)request.getParameter("chkUser") + "]");
  if (session.getAttribute("chkUser") != null) {
    user = (String)session.getAttribute("chkUser");
    logger.debug("En: LoginHuella con user: [" + user + "]");
    try {
    	rutUsuario = Long.parseLong(user);
    } catch (Exception ex) {
    	return mapping.findForward(GLOBAL_FORWARD_loginpage);
    }
  } else {
	  return mapping.findForward(GLOBAL_FORWARD_loginpage);
  }
   
    
    usuario = delegate.getDatosUsuario(rutUsuario);
    if (usuario == null) {
       return mapping.findForward(GLOBAL_FORWARD_loginpage);
    }
    Principal principal = new HttpUserPrincipal(String.valueOf(usuario.getRut()));    
    UserPrincipal urPrincipal = new UserPrincipal(String.valueOf(usuario.getRut()),"1234");
    session.setAttribute("userPrincipal", principal);
    DecodedHttpServletRequest httpRequest = new DecodedHttpServletRequest( (HttpServletRequest)request, true);
    Router.inject(request, urPrincipal);
    
    
    session.removeAttribute("encargadoEmpresa");
    session.removeAttribute("nombreEmpresas");
    session.removeAttribute("empresasACargo");
    session.removeAttribute("tips");

  if (usuario == null) {
    target = "loginpage";
    System.out.println("---> LoginHuella *****usuario=null **target: " + target);
    session.setAttribute("security.message", "errors.security.nouser");
  }
  else
  {
      session.setAttribute("datosUsuario", usuario);

      logger.debug("Usuario empresa?: " + usuario.isEsEmpresa());
      logger.debug("Usuario AfilAct?: " + usuario.isEsAfiliadoActivo());
      logger.debug("Usuario AfilPas?: " + usuario.isEsAfiliadoCesado());
      logger.debug("Usuario Pensionado?: " + usuario.isEsPensionado());
      logger.debug("Usuario Ahorrante?: " + usuario.isEsAhorrante());

      if ((usuario.isEsEmpresa()) && (
        (usuario.isEsAfiliadoActivo()) || 
        (usuario.isEsAfiliadoCesado()) || 
        (usuario.isEsPensionado()) || 
        (usuario.isEsAhorrante()))) {
        if ((tipoUsuario == null) || 
          (tipoUsuario.length() == 0)) {
          target = "seleccionaPerfil";
          return mapping.findForward(target);
        }
        if (Integer.parseInt(tipoUsuario) == 1) {
          usuario.setEsAfiliadoActivo(false);
          usuario.setEsAfiliadoCesado(false);
          usuario.setEsAhorrante(false);
          usuario.setEsPensionado(false);
        }
        else {
          usuario.setEsEmpresa(false);
        }
        session.setAttribute("datosUsuario", usuario);
      }

      if (!usuario.isEsEmpresa()) {
        Collection empresas = delegate.getEmpresaACargo(usuario.getRut());
        session.setAttribute("empresasACargo", empresas);

        if ((empresas != null) && (empresas.size() > 0))
        {
          String empresaACargo = request.getParameter("empresaACargo");
          if ((empresaACargo == null) || 
            (empresaACargo.length() == 0))
          {
            Hashtable nomEmpresas = new Hashtable();
            Iterator it = empresas.iterator();
            while (it.hasNext()) {
              EmpresaACargoVO emp = (EmpresaACargoVO)it.next();

              Collection listaEmpresas = delegate.getDatosEmpresa(emp.getRut());
              if ((listaEmpresas != null) && (listaEmpresas.size() > 0)) {
                EmpresaVO empresa = (EmpresaVO)listaEmpresas.iterator().next();
                nomEmpresas.put(String.valueOf(emp.getRut()), empresa.getNombre());
              } else {
                nomEmpresas.put(String.valueOf(emp.getRut()), "-- sin informacion --");
              }
              session.setAttribute("nombreEmpresas", nomEmpresas);
            }

            target = "seleccionaEmpresaACargo";
            return mapping.findForward(target);
          }
          logger.debug("Seleccion de Encargado: " + empresaACargo);
          user = String.valueOf(usuario.getRut());
          if (Long.parseLong(empresaACargo) != -1L) {
            usuario.setEsAfiliadoActivo(false);
            usuario.setEsAfiliadoCesado(false);
            usuario.setEsAhorrante(false);
            usuario.setEsPensionado(false);
            usuario.setEsEncargadoEmpresa(true);
            logger.debug("Ajustando modo a Empresa a Cargo");
            session.setAttribute("encargadoEmpresa", String.valueOf(usuario.getRut()));

            Iterator it = empresas.iterator();
            boolean found = false;
            while ((!found) && (it.hasNext())) {
              found = ((EmpresaACargoVO)it.next()).getRut() == Long.parseLong(empresaACargo);
            }

            Collection listaEmpresas = delegate.getDatosEmpresa(Long.parseLong(empresaACargo));
            if ((!found) || (listaEmpresas == null) || (listaEmpresas.size() <= 0)) {
              target = "loginpage";
              session.setAttribute("security.message", "errors.security.empresaacargonovalida");
              session.removeAttribute("datosUsuario");
              session.removeAttribute("application.username");
              session.removeAttribute("encargadoEmpresa");
              session.removeAttribute("nombreEmpresas");
              session.removeAttribute("empresasACargo");
              session.removeAttribute("lista.resumen");
              session.removeAttribute("empresa.convenio");
              session.removeAttribute("periodo");
              session.removeAttribute("tips");

              return mapping.findForward(target);
            }
            EmpresaVO empresa = (EmpresaVO)listaEmpresas.iterator().next();

            usuario.setDv(empresa.getDv());
            usuario.setEsEmpresa(true);
            usuario.setNombre(empresa.getNombre());

            usuario.setRutusuarioAutenticado(usuario.getRut());

            usuario.setRut(empresa.getRut());
            session.setAttribute("datosUsuario", usuario);
            user = String.valueOf(usuario.getRut());
          }

        }

      }

      session.removeAttribute("forzarCambio");
      session.setAttribute("application.username", user.toUpperCase().trim());

      logger.debug("Obtencion de los Servicios para el usuario");
      session.removeAttribute("servicios");

      System.out.println("---> LoginHuella ***** ENTRA A Obtener los servicios para el usuario y para el ambiente de ejecucion (web o modulo)");
      //session.setAttribute("servicios", getServicios(usuario, subapp));
      System.out.println("---> LoginHuella ***** SALE DE Obtener los servicios para el usuario y para el ambiente de ejecucion (web o modulo)");
      session.removeAttribute("security.tries." + usuario.getRut());

      target = "welcome";
      System.out.println("---> SALE DE LoginHuella ***** target: " + target);
  }
  
  return mapping.findForward(target);
}

  
}
