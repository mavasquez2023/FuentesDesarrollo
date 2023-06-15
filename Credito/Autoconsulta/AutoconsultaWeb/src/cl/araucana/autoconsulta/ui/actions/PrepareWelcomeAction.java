package cl.araucana.autoconsulta.ui.actions;

import cl.araucana.autoconsulta.common.UserEnvLoader;
import cl.araucana.autoconsulta.serv.ServicesAutoconsultaDelegate;
import cl.araucana.autoconsulta.vo.EmpresaACargoVO;
import cl.araucana.autoconsulta.vo.EmpresaPublicaVO;
import cl.araucana.autoconsulta.vo.EmpresaVO;
import cl.araucana.autoconsulta.vo.UsuarioVO;
import java.io.File;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Hashtable;
import java.util.Iterator;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionServlet;
import org.apache.struts.validator.DynaValidatorActionForm;

public class PrepareWelcomeAction extends Action
{
  private static Logger logger = Logger.getLogger(PrepareWelcomeAction.class);
  public static final String GLOBAL_FORWARD_welcome = "welcome";
  public static final String GLOBAL_FORWARD_changeClave = "changeClave";
  public static final String GLOBAL_FORWARD_seleccionaPerfil = "seleccionaPerfil";
  public static final String GLOBAL_FORWARD_changeClave2 = "changeClave2";
  public static final String GLOBAL_FORWARD_seleccionaEmpresaACargo = "seleccionaEmpresaACargo";
  public static final String GLOBAL_FORWARD_publicidad_modulo = "publicidadModulo";

  public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    HttpSession session = request.getSession(true);

    String subapp = (String)session.getAttribute("struts.subapplication");

    System.out.println("En: PrepareWelcomeAction");

    request.getSession().removeAttribute("isAdminCosal");

    UsuarioVO usuario = (UsuarioVO)session.getAttribute("datosUsuario");

    String isAdminCosal = 
      getAdminCosal(usuario.getRut() + "-" + usuario.getDv());
    logger.debug("isAdminCosal" + isAdminCosal);
    request.getSession().setAttribute("isAdminCosal", isAdminCosal);

    ServicesAutoconsultaDelegate delegate = 
      new ServicesAutoconsultaDelegate();

    System.out.println("PrepareWelcomeAction: despues de delegate..");

    String target = GLOBAL_FORWARD_welcome;
    DynaValidatorActionForm daf = (DynaValidatorActionForm)form;
    String tipoUsuario = null;

    if (request.getParameter("empresaACargo") == null){
      tipoUsuario = (String)daf.get("tipoUsuario");
      //Agregado para saber cuando enviar rut de empresa a cargo para validar en satelites
     session.setAttribute("seleccionaEmpresaACargo", "false");
    }else {
      tipoUsuario = "1";
    //Agregado para saber cuando enviar rut de empresa a cargo para validar en satelites
      if(!request.getParameter("empresaACargo").equals("-1")){
    	  session.setAttribute("seleccionaEmpresaACargo", "true");
      }else{
    	  session.setAttribute("seleccionaEmpresaACargo", "false");
      }
    }

    String accion = (String)daf.get("accion");
    String textNewPassword = (String)daf.get("newClave");
    String textReNewPassword = (String)daf.get("reNewClave");

    System.out.println("tipoUsuario: " + tipoUsuario);
    System.out.println("accion: " + accion);

    if (accion.equals("changeInicial")) {
      target = "changeClave";

      String pasostr = (String)daf.get("paso");
      if ((pasostr != null) && (!pasostr.equalsIgnoreCase(""))) {
        int paso = Integer.parseInt(pasostr);
        if (paso == 1)
        {
          target = "changeClave2";

          session.setAttribute("volverA", "Welcome.do");
          session.setAttribute("destino", "changeInicial");
          return mapping.findForward(target);
        }
      }

      if (((textNewPassword == null) || (textNewPassword.length() == 0)) && (
        (textReNewPassword == null) || 
        (textReNewPassword.length() == 0)))
      {
        session.setAttribute("volverA", "Welcome.do");
        session.setAttribute("destino", "changeInicial");
        return mapping.findForward(target);
      }

      if ((textNewPassword.length() < 4) || 
        (textReNewPassword.length() < 4)) {
        session.setAttribute(
          "security.message", 
          "errors.security.largo.invalido");

        session.setAttribute("volverA", "Welcome.do");
        session.setAttribute("destino", "changeInicial");
        return mapping.findForward(target);
      }

      int newPassword = Integer.parseInt(textNewPassword);
      int reNewPassword = Integer.parseInt(textReNewPassword);

      if (newPassword != reNewPassword)
      {
        session.setAttribute(
          "security.message", 
          "errors.security.claves.nuevas.no.coinciden");

        session.setAttribute("volverA", "Welcome.do");
        session.setAttribute("destino", "changeInicial");

        return mapping.findForward(target);
      }

      usuario.setAutenticacion(4);
      session.setAttribute("datosUsuario", usuario);
      delegate.modificarClavePersonal(usuario.getRut(), newPassword);
      session.removeAttribute("volverA");
      session.removeAttribute("destino");
    }

    switch (usuario.getAutenticacion())
    {
    case 3:

		/*
		 * Deshabilitación del cambio de clave inicial para la
		 * subaplicación 'web' (REQ4369, gpavez@hotmail.com. 16/11/2007).
		 */ 
//		session.setAttribute("forzarCambio","ok");
//		session.setAttribute("volverA","Welcome.do");
//		session.setAttribute("destino","changeInicial");
//		if (subapp.equalsIgnoreCase("modulo"))
//			target=GLOBAL_FORWARD_changeClave2;
//		else
//			target=GLOBAL_FORWARD_changeClave;
//		break;
		if (subapp.equalsIgnoreCase("modulo")) {
			session.setAttribute("forzarCambio", "ok");
			session.setAttribute("volverA", "Welcome.do");
			session.setAttribute("destino", "changeInicial");

			target = GLOBAL_FORWARD_changeClave2;
			
			break;
		}

		/*
		 * Para el caso subaplicación 'web' continúa con la
		 * misma lógica de AUT_CLAVE_PERSONAL_CORRECTA.
		 */
    case 4:
      System.out.println("Usuario empresa?: " + usuario.isEsEmpresa());
      System.out.println("Usuario AfilAct?: " + usuario.isEsAfiliadoActivo());
      System.out.println("Usuario AfilPas?: " + usuario.isEsAfiliadoCesado());
      System.out.println("Usuario Pensionado?: " + usuario.isEsPensionado());
      System.out.println("Usuario Ahorrante?: " + usuario.isEsAhorrante());
      System.out.println("Usuario EmpleadoPublico?: " + usuario.isEsPublico());
      System.out.println("Usuario EmpresaPublica?: " + usuario.isEsEmpresaPublica());

      if (((usuario.isEsEmpresa()) || (usuario.isEsEmpresaPublica())) && (
        (usuario.isEsAfiliadoActivo()) || 
        (usuario.isEsAfiliadoCesado()) || 
        (usuario.isEsPensionado()) || 
        (usuario.isEsAhorrante()) || 
        (usuario.isEsPublico()))) {
        System.out.println("AAA");
        if ((tipoUsuario == null) || (tipoUsuario.length() == 0)) {
          if (usuario.isEsEmpresa())
            session.setAttribute("isEsEmpresa", "SI");
          if (usuario.isEsEmpresaPublica())
            session.setAttribute("isEsEmpresaPublica", "SI");
          target = "seleccionaPerfil";

          System.out.println("BBB");

          System.out.println("BBB");
          return mapping.findForward(target);
        }
        System.out.println("CCC");
        if (Integer.parseInt(tipoUsuario) == 1)
        {
          System.out.println("DDD");
          usuario.setEsAfiliadoActivo(false);
          usuario.setEsAfiliadoCesado(false);
          usuario.setEsAhorrante(false);
          usuario.setEsPensionado(false);
          usuario.setEsEmpresaPublica(false);
        }
        else if (Integer.parseInt(tipoUsuario) == 3)
        {
          System.out.println("DDD");
          usuario.setEsAfiliadoActivo(false);
          usuario.setEsAfiliadoCesado(false);
          usuario.setEsAhorrante(false);
          usuario.setEsPensionado(false);
          usuario.setEsEmpresa(false);
        }
        else {
          System.out.println("EEE");
          usuario.setEsEmpresa(false);
        }
        session.setAttribute("datosUsuario", usuario);
      }

      System.out.println("FFF");

      if ((!usuario.isEsEmpresa()) && (!usuario.isEsEmpresaPublica())) {
        System.out.println("GGG");
        Collection empresas = 
          delegate.getEmpresaACargo(usuario.getRut());
        session.setAttribute("empresasACargo", empresas);

        if ((empresas != null) && (empresas.size() > 0)) {
          System.out.println("HHH");

          String empresaACargo = 
            request.getParameter("empresaACargo");
          System.out.println("empresaACargo: " + empresaACargo);
          if ((empresaACargo == null) || 
            (empresaACargo.length() == 0))
          {
            System.out.println("III");

            Hashtable nomEmpresas = new Hashtable();
            Iterator it = empresas.iterator();
            while (it.hasNext()) {
              EmpresaACargoVO emp = 
                (EmpresaACargoVO)it.next();

              Collection listaEmpresas = 
                delegate.getDatosEmpresa(emp.getRut());
              if ((listaEmpresas != null) && 
                (listaEmpresas.size() > 0)) {
                EmpresaVO empresa = 
                  (EmpresaVO)listaEmpresas
                  .iterator()
                  .next();
                nomEmpresas.put(
                  String.valueOf(emp.getRut()), 
                  empresa.getNombre());
              } else {
                listaEmpresas = delegate.getDatosEmpresaPublica(emp.getRut());
                if ((listaEmpresas != null) && (listaEmpresas.size() > 0)) {
                  EmpresaPublicaVO empresa = 
                    (EmpresaPublicaVO)listaEmpresas
                    .iterator()
                    .next();
                  nomEmpresas.put(
                    String.valueOf(emp.getRut()), 
                    empresa.getNombre());
                } else {
                  nomEmpresas.put(
                    String.valueOf(emp.getRut()), 
                    "-- sin informaciÃ³n --");
                }
              }
              session.setAttribute(
                "nombreEmpresas", 
                nomEmpresas);
            }

            target = "seleccionaEmpresaACargo";
            return mapping.findForward(target);
          }
          session.setAttribute(
            "usuarioEncargadoEmpresa", 
            new UsuarioVO((UsuarioVO)session.getAttribute("datosUsuario")));
          System.out.println("JJJ");
          System.out.println(
            "Seleccion de Encargado: " + empresaACargo);
          if (Long.parseLong(empresaACargo) != -1L)
          {
            System.out.println("KKK");
            usuario.setEsAfiliadoActivo(false);
            usuario.setEsAfiliadoCesado(false);
            usuario.setEsAhorrante(false);
            usuario.setEsPensionado(false);
            usuario.setEsPublico(false);
            usuario.setEsEncargadoEmpresa(true);
            System.out.println(
              "Ajustando modo a Empresa a Cargo");
            session.setAttribute(
              "encargadoEmpresa", 
              String.valueOf(usuario.getRut()));

            Iterator it = empresas.iterator();
            boolean found = false;
            while ((!found) && (it.hasNext())) {
              found = 
                ((EmpresaACargoVO)it.next()).getRut() == 
                Long.parseLong(empresaACargo);
            }

            EmpresaVO empresa = new EmpresaVO();

            Collection listaEmpresas = 
              delegate.getDatosEmpresa(
              Long.parseLong(empresaACargo));

            if ((listaEmpresas != null) && 
              (listaEmpresas.size() > 0)) {
              empresa = 
                (EmpresaVO)listaEmpresas.iterator().next();
              usuario.setEsEmpresa(true);
            }

            Collection listaEmpresasPublicas = 
              delegate.getDatosEmpresaPublica(Long.parseLong(empresaACargo));

            if ((listaEmpresasPublicas != null) && 
              (listaEmpresasPublicas.size() > 0)) {
              usuario.setEsEmpresaPublica(true);
              if ((listaEmpresas == null) || 
                (listaEmpresas.size() <= 0)) {
                EmpresaPublicaVO empresaPublica = 
                  (EmpresaPublicaVO)listaEmpresasPublicas.iterator().next();
                empresa.setDv(empresaPublica.getDv());
                empresa.setEstado(empresaPublica.getEstado());
                empresa.setNombre(empresaPublica.getNombre());
                empresa.setRut(empresaPublica.getRut());
                empresa.setTipo(empresaPublica.getTipo());
              }
            }

            if (((listaEmpresas == null) || (listaEmpresas.size() <= 0)) && 
              ((listaEmpresasPublicas == null) || (listaEmpresasPublicas.size() <= 0)) && (
              (!found) || 
              (listaEmpresas == null) || 
              (listaEmpresas.size() <= 0))) {
              System.out.println("LLL");

              System.out.println(
                "listaEmpresas: " + listaEmpresas);
              session.setAttribute(
                "security.message", 
                "errors.security.empresaacargonovalida");
              session.removeAttribute("datosUsuario");
              session.removeAttribute(
                "application.username");
              session.removeAttribute("encargadoEmpresa");
              session.removeAttribute("nombreEmpresas");
              session.removeAttribute("empresasACargo");
              session.removeAttribute("lista.resumen");
              session.removeAttribute("empresa.convenio");
              session.removeAttribute("periodo");
              session.removeAttribute("tips");

              System.out.println(
                "forwar a empresa a cargo no valida");
            }

            usuario.setDv(empresa.getDv());

            usuario.setNombre(empresa.getNombre());

            usuario.setRutusuarioAutenticado(
              usuario.getRut());

            usuario.setRut(empresa.getRut());
            session.setAttribute("datosUsuario", usuario);
          }

        }

      }

      System.out.println("MMM");

      System.out.println("Obtencion de los Servicios para el usuario");
      session.removeAttribute("servicios");

      session.setAttribute(
        "servicios", 
        getServicios(usuario, subapp));
      target = "welcome";
    }

    if ((target.equals("welcome")) && 
      (!usuario.isEsEmpresa()) && (!usuario.isEsEmpresaPublica())) {
      long monto = delegate.getMontoCreditoPreaprobado(usuario.getRut());
      if (monto > 0L) {
        session.setAttribute("montoPreAprobado", new Long(monto));

        if ((subapp != null) && (subapp.equals("modulo"))) {
          target = "publicidadModulo";
        }

        Context contextoInicial = new InitialContext();
        Context contexto = 
          (Context)contextoInicial.lookup("java:comp/env");

        String path = (String)contexto.lookup("conf/imgTestdesa");
        session.setAttribute("pathPublicidad", path);

        String path2 = getServlet().getServletContext().getRealPath("/") + "/web/images/publicidad/";
        session.setAttribute("pathPubEditada", path2);

        String pathAfiliado = 
          request.getContextPath() + "/web/PDF/SolicitudesAfiliados.pdf";
        String pathPensionado = 
          request.getContextPath() + "/web/PDF/SolicitudesPensionados.pdf";

        File fileAso = new File(pathAfiliado);
        String pathAbsFisicoAso = fileAso.getAbsolutePath();

        File filePen = new File(pathPensionado);
        String pathAbsFisicoPen = filePen.getAbsolutePath();

        session.setAttribute("pathPDFAfiliado", pathAbsFisicoAso);

        session.setAttribute("pathPDFPensionado", pathAbsFisicoPen);
      }

    }

    return mapping.findForward(target);
  }

  public String getAdminCosal(String rut)
  {
    UserEnvLoader userEnvLoader = new UserEnvLoader();

    return userEnvLoader.getAdminCosal(rut);
  }

  public ArrayList getServicios(UsuarioVO usuario, String subapp) {
    UserEnvLoader userEnvLoader = new UserEnvLoader();

    return userEnvLoader.getServicios(usuario, subapp);
  }
}
