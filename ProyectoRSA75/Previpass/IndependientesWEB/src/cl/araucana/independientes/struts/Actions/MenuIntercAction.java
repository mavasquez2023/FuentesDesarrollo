package cl.araucana.independientes.struts.Actions;

import javax.servlet.http.*;

import org.apache.struts.action.*;

import cl.araucana.independientes.struts.Forms.MenuIntercForm;

/*Implementación de la clase MenuIntercAction*/
public class MenuIntercAction extends Action{

    public ActionForward execute(
            ActionMapping mapping
            , ActionForm form
            , HttpServletRequest request
            , HttpServletResponse response)
    {
        HttpSession session = request.getSession();

        //--- INI - Validacion de usuario logueado ---

        String usuarioLogeado = (String)session.getAttribute("IDAnalista");

        if(usuarioLogeado == null){

            request.setAttribute("Error", "Sesion cerrada. Por favor vuelva a ingresar");
            return mapping.findForward("logout");
        }

        //--- FIN - Validacion de usuario logueado ---

        MenuIntercForm menuIntercajaForm = (MenuIntercForm) form;

        int opcion;

        opcion = Integer.parseInt(menuIntercajaForm.getOpcion());

        switch (opcion){

        case 0:

            return mapping.findForward("menuPpal");

        case -1:

            session.invalidate();

            return mapping.findForward("logout");

        case 1: return mapping.findForward("generarArchSalida");

        //case 2: return mapping.findForward("generarArchEntrada");
        case 2: return mapping.findForward("SubirArchivoIntercaja");

        case 3: return mapping.findForward("generarCasosPend");

        default: return mapping.findForward("menuPpal");

        }

    }
}