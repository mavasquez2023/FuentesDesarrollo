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
import cl.araucana.sivegam.struts.Forms.GeneracionReportesCesantiaForm;

public class GenerarReporteCesantiaAction extends Action {

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();

        String usuarioLogeado = (String) session.getAttribute("IDAnalista");

        if (usuarioLogeado == null) {

            request.setAttribute("Error", "Sesion cerrada. Por favor vuelva a ingresar");
            return mapping.findForward("logout");
        }

        GeneracionReportesCesantiaForm generarRepCesantiaForm = (GeneracionReportesCesantiaForm) form;

        int opcion;
        String idTipoReporte = "";
        String periodoReporte = "";
        String fechaSistema = ParametrosDAO.obtenerFechaSistema();
        int a = 0;
        if (String.valueOf(34404).equals(fechaSistema)) {
            a = 34404;
        }
        if (a != 0) {
            session.setAttribute("Error", "Ha ocurrido un error al conectar con el servidor de Base de Datos. Comuniquese con su administrador del Sistema.");
            return mapping.findForward("login");
        } else {
            opcion = Integer.parseInt(generarRepCesantiaForm.getOpcion());
            idTipoReporte = generarRepCesantiaForm.getIdReporte();
            periodoReporte = generarRepCesantiaForm.getAnio() + generarRepCesantiaForm.getMes();
            String anio = generarRepCesantiaForm.getAnio();
            String mes = generarRepCesantiaForm.getMes();
            if(Integer.parseInt(mes) < 10){
                mes = '0' + mes;
            }                
            periodoReporte = anio + mes;

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
                    String archivoEntrada = generarRepCesantiaForm.getRutaArchivo();

                    String ruta = archivoEntrada;
                    String archivo = archivoEntrada.substring(archivoEntrada.lastIndexOf("\\") + 1, archivoEntrada.length());

                    bajar.download(request, response, ruta, archivo);

                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ServletException f) {
                    f.printStackTrace();
                }

            case 3:
                session.setAttribute("idReporteCesantiaEnv", idTipoReporte);
                session.setAttribute("periodoRep", periodoReporte);
                session.setAttribute("anio", anio);
                session.setAttribute("mes", mes);
                return mapping.findForward("cargaArchivosCesantia");

            case 4:
                session.setAttribute("respuestaCargaCesantia", "");
                return mapping.findForward("reporteCesantia");

            default:
                return mapping.findForward("menuTipoReportePorArea");

            }
        }
    }
}
