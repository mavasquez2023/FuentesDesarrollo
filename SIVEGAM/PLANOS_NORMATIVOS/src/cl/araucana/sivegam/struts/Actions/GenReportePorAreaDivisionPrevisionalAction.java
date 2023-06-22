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
import cl.araucana.sivegam.struts.Forms.GenReportePorAreaDivisionPrevisionalForm;

public class GenReportePorAreaDivisionPrevisionalAction extends Action {

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();

        String usuarioLogeado = (String) session.getAttribute("IDAnalista");

        if (usuarioLogeado == null) {

            request.setAttribute("Error", "Sesion cerrada. Por favor vuelva a ingresar");
            return mapping.findForward("logout");
        }

        GenReportePorAreaDivisionPrevisionalForm menuReportesPorAreaDivPrevForm = (GenReportePorAreaDivisionPrevisionalForm) form;

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
            opcion = Integer.parseInt(menuReportesPorAreaDivPrevForm.getOpcion());

            switch (opcion) {

            case -1:

                session.invalidate();

                return mapping.findForward("logout");

            case 1:
                session.setAttribute("anio", null);
                session.setAttribute("mes", null);
                return mapping.findForward("menuTipoReportePorArea");

            case 2:
                ManipuladorArchivo bajar = new ManipuladorArchivo();

                try {
                    String archivoEntrada = menuReportesPorAreaDivPrevForm.getRutaReporte();

                    String ruta = archivoEntrada;
                    String archivo = archivoEntrada.substring(archivoEntrada.lastIndexOf("\\") + 1, archivoEntrada.length());

                    bajar.download(request, response, ruta, archivo);

                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ServletException f) {
                    f.printStackTrace();
                }

            case 3:
                String flagArchivo = menuReportesPorAreaDivPrevForm.getFlagTipoAFC();
                String anio = menuReportesPorAreaDivPrevForm.getAnio();
                String mes = menuReportesPorAreaDivPrevForm.getMes();
                if ("R".equals(flagArchivo)) {
                    session.setAttribute("IdExtesionInformadoAFC", "3");
                    session.setAttribute("flagReporteInformadoAFC", "R");

                } else {

                    session.setAttribute("IdExtesionInformadoAFC", "3");
                    session.setAttribute("flagReporteInformadoAFC", "M");
                }
                session.setAttribute("anio", anio);
                session.setAttribute("mes", mes);
                return mapping.findForward("cargaArchivosAFC");

            case 4:
                String tipoReporteEnv = "";
                anio = menuReportesPorAreaDivPrevForm.getAnio();
                mes = menuReportesPorAreaDivPrevForm.getMes();
                if(Integer.parseInt(mes) < 10){
                    mes = '0' + mes;
                }                
                String periodoReporte = anio + mes;
                String extArchivo = menuReportesPorAreaDivPrevForm.getFlagTipoAFC();
                if ("R".equals(extArchivo)) {
                    session.setAttribute("IdExtesionInformadoAFC", "1");
                    session.setAttribute("flagReporteInformadoAFC", "R");
                    tipoReporteEnv = "8";
                } else {

                    session.setAttribute("IdExtesionInformadoAFC", "1");
                    session.setAttribute("flagReporteInformadoAFC", "M");
                    tipoReporteEnv = "9";
                }
                session.setAttribute("tipoReporteEnv", tipoReporteEnv);
                session.setAttribute("periodoRep", periodoReporte);
                session.setAttribute("anio", anio);
                session.setAttribute("mes", mes);
                return mapping.findForward("cargaArchivosAFC");

            case 5:
                session.setAttribute("controlCobolReproceso", " ");

                return mapping.findForward("GenRepAreaDivPrev");

            case 6:

                session.setAttribute("controlCobol", " ");

                return mapping.findForward("GenRepAreaDivPrev");

            default:
                return mapping.findForward("menuTipoReportePorArea");

            }
        }
    }
}
