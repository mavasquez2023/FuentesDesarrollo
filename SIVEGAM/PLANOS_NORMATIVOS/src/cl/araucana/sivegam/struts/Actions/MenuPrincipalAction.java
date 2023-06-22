package cl.araucana.sivegam.struts.Actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import cl.araucana.sivegam.dao.ParametrosDAO;
import cl.araucana.sivegam.struts.Forms.MenuPrincipalForm;

public class MenuPrincipalAction extends Action {
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();

        String usuarioLogeado = (String) session.getAttribute("IDAnalista");

        if (usuarioLogeado == null) {

            request.setAttribute("Error", "Sesion cerrada. Por favor vuelva a ingresar");
            return mapping.findForward("logout");
        }

        MenuPrincipalForm menuPpalForm = (MenuPrincipalForm) form;

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
            opcion = Integer.parseInt(menuPpalForm.getOpcion());

            switch (opcion) {

            case -1:

                session.invalidate();

                return mapping.findForward("logout");

            case 1:
                return mapping.findForward("menuGeneracionReporte");

            case 2:
                return mapping.findForward("menuEdicionReportes");

                /*
                 * case 4:
                 * 
                 * StringBuffer excelFile = new StringBuffer("");
                 * 
                 * excelFile.append("/archivos/independientes/reportes/");
                 * excelFile.append("rep1"); excelFile.append(".xls");
                 * 
                 * request.setAttribute("excelFile", excelFile.toString() );
                 * 
                 * return mapping.findForward("repNominaSolAfi");
                 */

            default:
                return mapping.findForward("menuPrincipal");

            }
        }
    }
}
