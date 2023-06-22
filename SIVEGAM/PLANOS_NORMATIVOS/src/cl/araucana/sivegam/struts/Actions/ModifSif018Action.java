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
import cl.araucana.sivegam.struts.Forms.ModifSif018Form;

public class ModifSif018Action extends Action {

    static SivegamLoggerHelper logger = SivegamLoggerHelper.getInstance();

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();

        String usuarioLogeado = (String) session.getAttribute("IDAnalista");

        if (usuarioLogeado == null) {

            request.setAttribute("Error", "Sesion cerrada. Por favor vuelva a ingresar");
            return mapping.findForward("logout");
        }

        ModifSif018Form modificarSif018 = (ModifSif018Form) form;
        String fechaSistema = ParametrosDAO.obtenerFechaSistema();
        int a = 0;
        if (String.valueOf(34404).equals(fechaSistema)) {
            a = 34404;
        }
        int opcion;
        if (a != 0) {
            session.setAttribute("Error", "Ha ocurrido un error al conectar con el servidor de Base de Datos. Comuniquese con su administrador del Sistema.");
            return mapping.findForward("login");
        } else {
            String idSelectedItem = modificarSif018.getIdSelectedItem();
            String idSif018_glob = modificarSif018.getIdSif018_glob();
            String rutSearch = modificarSif018.getRutSearch();
            opcion = Integer.parseInt(modificarSif018.getOpcion());
  //          logger.debug("la opcion que llega es: " + opcion);

            switch (opcion) {

            case -1:

                session.invalidate();

                return mapping.findForward("logout");

            case 1:

                return mapping.findForward("menuEditCot");

            case 2:
                return mapping.findForward("agregarSif018");

            case 3:
                session.setAttribute("idSelectedItem", idSelectedItem);
                session.setAttribute("idSif018_glob", idSif018_glob);
                session.setAttribute("rutSearch", rutSearch);
                return mapping.findForward("modificarSif018");

            default:

                return mapping.findForward("menuEditCot");

            }
        }
    }
}
