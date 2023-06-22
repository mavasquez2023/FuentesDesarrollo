package cl.araucana.sivegam.struts.Actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import cl.araucana.sivegam.dao.ParametrosDAO;
import cl.araucana.sivegam.struts.Forms.ModifSif012Form;

public class ModifSif012Action extends Action {

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();

        String usuarioLogeado = (String) session.getAttribute("IDAnalista");

        if (usuarioLogeado == null) {

            request.setAttribute("Error", "Sesion cerrada. Por favor vuelva a ingresar");
            return mapping.findForward("logout");
        }

        ModifSif012Form modificarSif012 = (ModifSif012Form) form;

        int opcion;
        String fechaSistema = ParametrosDAO.obtenerFechaSistema();
        int a = 0;
        if (String.valueOf(34404).equals(fechaSistema)) {
            a = 34404;
        }
        if (a != 0) {
            session.setAttribute("Error", "Ha ocurrido un error al conectar con el servidor de Base de Datos. Comuniquese con su administrador del Sistema.");
            return mapping.findForward("login");
        } else {
            String idSelectedItem = modificarSif012.getIdSelectedItem();
            String idSif011_glob = modificarSif012.getIdSif012_glob();
            String rutSearch = modificarSif012.getRutSearch();
            opcion = Integer.parseInt(modificarSif012.getOpcion());

            switch (opcion) {

            case -1:

                session.invalidate();

                return mapping.findForward("logout");

            case 1:

                return mapping.findForward("menuEditDivPrev");

            case 2:
                return mapping.findForward("agregarSif012");

            case 3:
                session.setAttribute("idSelectedItem", idSelectedItem);
                session.setAttribute("idSif012_glob", idSif011_glob);
                session.setAttribute("rutSearch", rutSearch);
                return mapping.findForward("modificarSif012");

            default:

                return mapping.findForward("menuEditDivPrev");

            }
        }
    }
}
