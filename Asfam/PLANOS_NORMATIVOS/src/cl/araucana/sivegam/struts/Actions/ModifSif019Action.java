package cl.araucana.sivegam.struts.Actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import cl.araucana.sivegam.dao.ParametrosDAO;
import cl.araucana.sivegam.helper.SivegamLoggerHelper;
import cl.araucana.sivegam.struts.Forms.ModifSif019Form;

public class ModifSif019Action extends Action {

    static SivegamLoggerHelper logger = SivegamLoggerHelper.getInstance();

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();

        String usuarioLogeado = (String) session.getAttribute("IDAnalista");

        if (usuarioLogeado == null) {

            request.setAttribute("Error", "Sesion cerrada. Por favor vuelva a ingresar");
            return mapping.findForward("logout");
        }

        ModifSif019Form modificarSif019 = (ModifSif019Form) form;

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
            String idSelectedItem = modificarSif019.getIdSelectedItem();
            String idSif019_glob = modificarSif019.getIdSif019_glob();
            String rutSearch = modificarSif019.getRutSearch();
            opcion = Integer.parseInt(modificarSif019.getOpcion());
    //        logger.debug("la opcion que llega es: " + opcion);

            switch (opcion) {

            case -1:

                session.invalidate();

                return mapping.findForward("logout");

            case 1:

                return mapping.findForward("menuEditCot");

            case 2:
                return mapping.findForward("agregarSif019");

            case 3:
                session.setAttribute("idSelectedItem", idSelectedItem);
                session.setAttribute("idSif019_glob", idSif019_glob);
                session.setAttribute("rutSearch", rutSearch);
                return mapping.findForward("modificarSif019");

            default:

                return mapping.findForward("menuEditCot");

            }
        }
    }
}
