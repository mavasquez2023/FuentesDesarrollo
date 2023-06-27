package cl.araucana.independientes.struts.Actions;

import javax.servlet.http.*;
import org.apache.struts.action.*;
import cl.araucana.independientes.struts.Forms.RepNominaApoAfiForm;

public class RepNominaApoAfiAction extends Action{

    public ActionForward execute(
            ActionMapping mapping
            , ActionForm form
            , HttpServletRequest request
            , HttpServletResponse response)
    {
        int opcion;

        HttpSession session = request.getSession();

        String usuarioLogeado = (String)session.getAttribute("IDAnalista");

        if(usuarioLogeado == null){

            request.setAttribute("Error", "Sesion cerrada. Por favor vuelva a ingresar");
            return mapping.findForward("logout");
        }

        RepNominaApoAfiForm repNominaApoAfiForm = (RepNominaApoAfiForm) form;

        opcion = Integer.parseInt(repNominaApoAfiForm.getOpcion());

        switch (opcion){

        case -1:

            session.invalidate();

            return mapping.findForward("logout");

        case 1:

            return mapping.findForward("menuAporte");

        default:

            return mapping.findForward("menuAporte");
        }
    }
}
