package cl.araucana.independientes.struts.Actions;

import javax.servlet.http.*;

import org.apache.struts.action.*;

import cl.araucana.independientes.struts.Forms.ConsDesafiliacionForm;

/*Implementación de la clase ModSolicitudAction*/
public class ConsDesafiliacionAction extends Action{

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

        ConsDesafiliacionForm consDesafiliacionForm = (ConsDesafiliacionForm) form;

        opcion = Integer.parseInt(consDesafiliacionForm.getOpcion());

        switch (opcion){

        case -1:

            session.invalidate();

            return mapping.findForward("logout");

        case 1:

            mapping.findForward("menuDesaf");

        default: 	

            return mapping.findForward("menuDesaf");

        }
    }
}
