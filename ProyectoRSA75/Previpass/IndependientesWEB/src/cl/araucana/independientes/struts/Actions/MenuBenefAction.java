package cl.araucana.independientes.struts.Actions;

import javax.servlet.http.*;

import org.apache.struts.action.*;

import cl.araucana.independientes.struts.Forms.MenuBenefForm;

/*Implementación de la clase MenuPpalAction*/
public class MenuBenefAction extends Action{

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

        MenuBenefForm menuBenefForm = (MenuBenefForm) form;

        int opcion;

        opcion = Integer.parseInt(menuBenefForm.getOpcion());

        switch (opcion){

        case -1:

            session.invalidate();

            return mapping.findForward("logout");

        case 1: return mapping.findForward("solBeneficios");

        case 2: return mapping.findForward("consBenefProg");

        case 3: return mapping.findForward("consHistorica");

        default: return mapping.findForward("menuPpal");

        }

    }
}
