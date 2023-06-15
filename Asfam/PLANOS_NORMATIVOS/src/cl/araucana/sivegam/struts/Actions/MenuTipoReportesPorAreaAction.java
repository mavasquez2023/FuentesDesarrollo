package cl.araucana.sivegam.struts.Actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import cl.araucana.sivegam.dao.ParametrosDAO;
import cl.araucana.sivegam.struts.Forms.MenuTipoReportesPorAreaForm;

public class MenuTipoReportesPorAreaAction extends Action {

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();

        String usuarioLogeado = (String) session.getAttribute("IDAnalista");
        String opc = (String)session.getAttribute("opcion");
        
        if (usuarioLogeado == null) {

            request.setAttribute("Error", "Sesion cerrada. Por favor vuelva a ingresar");
            return mapping.findForward("logout");
        }

        MenuTipoReportesPorAreaForm menuReportesPorAreaForm = (MenuTipoReportesPorAreaForm) form;

        int opcion = Integer.parseInt(opc);
        String fechaSistema = ParametrosDAO.obtenerFechaSistema();
        int a = 0;
        if (String.valueOf(34404).equals(fechaSistema)) {
            a = 34404;
        }
        if (a != 0) {
            session.setAttribute("Error", "Ha ocurrido un error al conectar con el servidor de Base de Datos. Comuniquese con su administrador del Sistema.");
            return mapping.findForward("login");
        } else {
            switch (opcion) {

            case -1:

                session.invalidate();

                return mapping.findForward("logout");

            case 0:
                return mapping.findForward("menuGeneracionReporte");

            case 1:
                return mapping.findForward("genRepDivPrev");

            case 2:
                return mapping.findForward("GenRepAreaDivPrev");

            case 3:
                return mapping.findForward("reporteCesantia");

            default:
                return mapping.findForward("menuGeneracionReporte");

            }
        }
    }
}
