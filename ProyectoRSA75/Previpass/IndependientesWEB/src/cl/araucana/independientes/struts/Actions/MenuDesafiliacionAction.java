package cl.araucana.independientes.struts.Actions;

import javax.servlet.http.*;

import org.apache.struts.action.*;

import cl.araucana.independientes.struts.Forms.MenuBenefForm;
import cl.araucana.independientes.struts.Forms.MenuDesafiliacionForm;

/*Implementación de la clase MenuPpalAction*/
public class MenuDesafiliacionAction extends Action{

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

        MenuDesafiliacionForm menuDesafiliacionForm = (MenuDesafiliacionForm) form;

        int opcion;

        opcion = Integer.parseInt(menuDesafiliacionForm.getOpcion());

        switch (opcion){

        case -1:

            session.invalidate();

            return mapping.findForward("logout");

        case 1: return mapping.findForward("solDesafiliacion");

        case 2: return mapping.findForward("consDesafiliacion");

        case 3: return mapping.findForward("RepNominaSolDesafAfi");

        default: return mapping.findForward("menuPpal");

        }

    }
}
