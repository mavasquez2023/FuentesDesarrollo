package cl.araucana.sivegam.struts.Actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import cl.araucana.sivegam.dao.ParametrosDAO;
import cl.araucana.sivegam.struts.Forms.AgregarSif011Form;

public class AgregarSif011Action extends Action {

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();

        String usuarioLogeado = (String) session.getAttribute("IDAnalista");

        if (usuarioLogeado == null) {

            request.setAttribute("Error", "Sesion cerrada. Por favor vuelva a ingresar");
            return mapping.findForward("logout");
        }

        AgregarSif011Form AgregarSif011 = (AgregarSif011Form) form;

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
            String idSelectedItem = AgregarSif011.getIdSelectedItem();
            String idSif011_glob = AgregarSif011.getIdSif011_glob();
            String rutSearch = AgregarSif011.getRutSearch();
            opcion = Integer.parseInt(AgregarSif011.getOpcion());

            String dbx_filtroBusqueda = AgregarSif011.getDbx_filtroBusqueda();
            String txt_rut = AgregarSif011.getTxt_rut();
            String txt_digitoVerificador = AgregarSif011.getTxt_digitoVerificador();
            String txt_primerRango = AgregarSif011.getTxt_primerRango();
            String txt_segundoRango = AgregarSif011.getTxt_segundoRango();

            switch (opcion) {

            case -1:

                session.invalidate();

                return mapping.findForward("logout");

            case 1:

                return mapping.findForward("modifSif011");

            case 2:
                session.setAttribute("idSelectedItem", idSelectedItem);
                session.setAttribute("idSif011_glob", idSif011_glob);
                session.setAttribute("rutSearch", rutSearch);

                session.setAttribute("dbx_filtroBusqueda", dbx_filtroBusqueda);
                session.setAttribute("txt_rut", txt_rut);
                session.setAttribute("txt_digitoVerificador", txt_digitoVerificador);
                session.setAttribute("txt_primerRango", txt_primerRango);
                session.setAttribute("txt_segundoRango", txt_segundoRango);
                return mapping.findForward("modifSif011");
            default:

                return mapping.findForward("modifSif011");

            }
        }
    }
}
