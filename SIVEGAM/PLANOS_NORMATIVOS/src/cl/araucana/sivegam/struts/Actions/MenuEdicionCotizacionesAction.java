package cl.araucana.sivegam.struts.Actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import cl.araucana.sivegam.dao.ParametrosDAO;
import cl.araucana.sivegam.struts.Forms.MenuEdicionCotizacionesForm;

public class MenuEdicionCotizacionesAction extends Action {

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();

        String usuarioLogeado = (String) session.getAttribute("IDAnalista");

        if (usuarioLogeado == null) {

            request.setAttribute("Error", "Sesion cerrada. Por favor vuelva a ingresar");
            return mapping.findForward("logout");
        }

        MenuEdicionCotizacionesForm genEditarCotizForm = (MenuEdicionCotizacionesForm) form;

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
            opcion = Integer.parseInt(genEditarCotizForm.getOpcion());

            switch (opcion) {

            case -1:

                session.invalidate();

                return mapping.findForward("logout");

            case 0:
                return mapping.findForward("menuEdicionReportes");

            case 1:
                return mapping.findForward("modifSif016");

            case 2:

                return mapping.findForward("modifSif017");

            case 3:

                return mapping.findForward("modifSif018");

            case 4:

                return mapping.findForward("modifSif019");

            default:
                return mapping.findForward("menuEdicionReportes");

            }
        }
    }
}
