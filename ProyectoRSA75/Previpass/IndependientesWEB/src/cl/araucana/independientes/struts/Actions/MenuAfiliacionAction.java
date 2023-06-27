package cl.araucana.independientes.struts.Actions;

import javax.servlet.http.*;

import org.apache.struts.action.*;

import cl.araucana.independientes.struts.Forms.MenuAfiliacionForm;

/*Implementación de la clase MenuPpalAction*/
public class MenuAfiliacionAction extends Action{

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

        MenuAfiliacionForm menuAfiliacionForm = (MenuAfiliacionForm) form;

        int opcion;

        opcion = Integer.parseInt(menuAfiliacionForm.getOpcion());

        switch (opcion){

        case -1:

            session.invalidate();

            return mapping.findForward("logout");

        case 1: return mapping.findForward("ingresoSolicitud");

        case 2: return mapping.findForward("modificaSolicitud");

        case 3: return mapping.findForward("modificaAfiliado");

        case 4: return mapping.findForward("repNominaSolAfi");

        case 5: return mapping.findForward("consModMasivaSol");

        default: return mapping.findForward("menuPpal");

        }

    }
}
