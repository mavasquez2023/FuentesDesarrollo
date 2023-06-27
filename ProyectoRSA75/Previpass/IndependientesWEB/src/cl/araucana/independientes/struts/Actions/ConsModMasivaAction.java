package cl.araucana.independientes.struts.Actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import cl.araucana.independientes.struts.Forms.ConsModMasivaSolForm;

public class ConsModMasivaAction extends Action {

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

        ConsModMasivaSolForm modMasivaForm = (ConsModMasivaSolForm) form;

        opcion = Integer.parseInt(modMasivaForm.getOpcion());

        switch (opcion){

        case -1:

            session.invalidate();

            return mapping.findForward("logout");

        case 1:

            mapping.findForward("menuPpal");

        default: 	

            return mapping.findForward("menuPpal");

        }

    }
}
