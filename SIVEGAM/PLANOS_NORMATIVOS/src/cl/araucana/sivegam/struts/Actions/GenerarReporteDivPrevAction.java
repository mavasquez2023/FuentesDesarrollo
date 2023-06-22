package cl.araucana.sivegam.struts.Actions;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import cl.araucana.sivegam.dao.ParametrosDAO;
import cl.araucana.sivegam.helper.ManipuladorArchivo;
import cl.araucana.sivegam.helper.SivegamLoggerHelper;
import cl.araucana.sivegam.struts.Forms.GenerarReporteDivPrevForm;

public class GenerarReporteDivPrevAction extends Action {

    static SivegamLoggerHelper logger = SivegamLoggerHelper.getInstance();

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();

        String usuarioLogeado = (String) session.getAttribute("IDAnalista");

        if (usuarioLogeado == null) {

            request.setAttribute("Error", "Sesion cerrada. Por favor vuelva a ingresar");
            return mapping.findForward("logout");
        }

        GenerarReporteDivPrevForm genReporteDivPrevForm = (GenerarReporteDivPrevForm) form;

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
            opcion = Integer.parseInt(genReporteDivPrevForm.getOpcion());
    //        logger.debug("la opcion que llega es: " + opcion);

            switch (opcion) {

            case -1:

                session.invalidate();

                return mapping.findForward("logout");

            case 1:
                return mapping.findForward("menuTipoReportePorArea");

            case 2:
                ManipuladorArchivo bajar = new ManipuladorArchivo();

                try {
                    String archivoEntrada = genReporteDivPrevForm.getRutaArchivo();

                    String ruta = archivoEntrada;
                    String archivo = archivoEntrada.substring(archivoEntrada.lastIndexOf("\\") + 1, archivoEntrada.length());

                    bajar.download(request, response, ruta, archivo);

                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ServletException f) {
                    f.printStackTrace();
                }

            case 3:

                ManipuladorArchivo desc = new ManipuladorArchivo();

                try {
                    String archivoEntrada = genReporteDivPrevForm.getRutaArchivo();

                    String ruta = archivoEntrada;
                    String archivo = archivoEntrada.substring(archivoEntrada.lastIndexOf("\\") + 1, archivoEntrada.length());

   //                 logger.debug("Ruta = " + ruta);
   //                 logger.debug("Archivo = " + archivo);

                    desc.download(request, response, ruta, archivo);

                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ServletException f) {
                    f.printStackTrace();
                }

            case 4:
                return mapping.findForward("genRepDivPrev");
            default:
                return mapping.findForward("menuTipoReportePorArea");

            }
        }
    }
}
