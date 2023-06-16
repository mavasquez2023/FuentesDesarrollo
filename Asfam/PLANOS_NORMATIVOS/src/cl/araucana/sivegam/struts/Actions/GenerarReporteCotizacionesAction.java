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
import cl.araucana.sivegam.struts.Forms.GenerarReporteCotizacionesForm;

public class GenerarReporteCotizacionesAction extends Action {

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();

        String usuarioLogeado = (String) session.getAttribute("IDAnalista");

        if (usuarioLogeado == null) {

            request.setAttribute("Error", "Sesion cerrada. Por favor vuelva a ingresar");
            return mapping.findForward("logout");
        }

        GenerarReporteCotizacionesForm genReporteCotizacionesForm = (GenerarReporteCotizacionesForm) form;

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
            opcion = Integer.parseInt(genReporteCotizacionesForm.getOpcion());

            switch (opcion) {

            case -1:

                session.invalidate();

                return mapping.findForward("logout");

            case 1:
                return mapping.findForward("menuGeneracionReporte");

            case 2:

                ManipuladorArchivo bajar = new ManipuladorArchivo();

                try {
                    String archivoEntrada = genReporteCotizacionesForm.getRutaArchivo();

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
                    String archivoEntrada = genReporteCotizacionesForm.getRutaArchivo();

                    String ruta = archivoEntrada;
                    String archivo = archivoEntrada.substring(archivoEntrada.lastIndexOf("\\") + 1, archivoEntrada.length());

                    desc.download(request, response, ruta, archivo);

                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ServletException f) {
                    f.printStackTrace();
                }
                /*
                 * case 2:
                 * 
                 * ManipuladorArchivo bajarReporte16XLS = new
                 * ManipuladorArchivo();
                 * 
                 * try{ String fechaReporte16 = ""; String periodoReporte16 =
                 * ""; String mesTemp16 = ""; String DATE_FORMAT2 =
                 * "dd/MM/yyyy"; SimpleDateFormat sdf2 = new
                 * SimpleDateFormat(DATE_FORMAT2);
                 * 
                 * Calendar cal = Calendar.getInstance(); Date fecha = new
                 * Date();
                 * 
                 * cal.set(cal.get(Calendar.YEAR),cal.get(Calendar.MONTH),cal.getActualMinimum(Calendar.DAY_OF_MONTH));
                 * 
                 * fecha = cal.getTime(); fechaReporte16 = sdf2.format(fecha);
                 * 
                 * int mes16 = Integer.parseInt(fechaReporte16.substring(3,5));
                 * 
                 * 
                 * if(mes16 == 1){
                 * 
                 * mesTemp16 = "12"; }
                 * 
                 * if(mes16 < 10){
                 * 
                 * mesTemp16 = "0" + Integer.toString(mes16 - 1);
                 * 
                 * }else{
                 * 
                 * mesTemp16 = Integer.toString(mes16 -1); }
                 * 
                 * periodoReporte16 = fechaReporte16.substring(6, 10) +
                 * mesTemp16; logger.debug(periodoReporte16);
                 * 
                 * File f = new File(""); StringBuffer rutaReporte = new
                 * StringBuffer(""); rutaReporte.append(f.getAbsolutePath());
                 * rutaReporte.append(IND_Constants.DIR_SIVEGAM_EXCEL_SIF018);
                 * rutaReporte.append(IND_Constants.SUF_SIVEGAM_SIF018);
                 * rutaReporte.append(IND_Constants.EXT_Excel);
                 * 
                 * String rutaDelArchivo = rutaReporte.toString(); String
                 * archivo =
                 * rutaDelArchivo.substring(rutaDelArchivo.lastIndexOf("\\") +
                 * 1, rutaDelArchivo.length());
                 * bajarReporte16XLS.download(request, response, rutaDelArchivo,
                 * archivo);
                 * 
                 * }catch(IOException e){ e.printStackTrace(); }
                 * catch(ServletException f){ f.printStackTrace(); }
                 * 
                 * case 3: ManipuladorArchivo bajarReporte17XLS = new
                 * ManipuladorArchivo();
                 * 
                 * try{ String fechaReporte17 = ""; String periodoReporte17 =
                 * ""; String mesTemp17 = ""; String DATE_FORMAT2 =
                 * "dd/MM/yyyy"; SimpleDateFormat sdf2 = new
                 * SimpleDateFormat(DATE_FORMAT2);
                 * 
                 * Calendar cal = Calendar.getInstance(); Date fecha = new
                 * Date();
                 * 
                 * cal.set(cal.get(Calendar.YEAR),cal.get(Calendar.MONTH),cal.getActualMinimum(Calendar.DAY_OF_MONTH));
                 * 
                 * fecha = cal.getTime(); fechaReporte17 = sdf2.format(fecha);
                 * 
                 * int mes17 = Integer.parseInt(fechaReporte17.substring(3,5));
                 * 
                 * 
                 * if(mes17 == 1){
                 * 
                 * mesTemp17 = "12"; }
                 * 
                 * if(mes17 < 10){
                 * 
                 * mesTemp17 = "0" + Integer.toString(mes17 - 1);
                 * 
                 * }else{
                 * 
                 * mesTemp17 = Integer.toString(mes17 -1); }
                 * 
                 * periodoReporte17 = fechaReporte17.substring(6, 10) +
                 * mesTemp17; logger.debug(periodoReporte17); File f = new
                 * File(""); StringBuffer rutaReporte = new StringBuffer("");
                 * rutaReporte.append(f.getAbsolutePath());
                 * rutaReporte.append(IND_Constants.DIR_SIVEGAM_EXCEL_SIF018);
                 * rutaReporte.append(IND_Constants.SUF_SIVEGAM_SIF018);
                 * rutaReporte.append(IND_Constants.EXT_Excel);
                 * 
                 * String rutaDelArchivo = rutaReporte.toString(); String
                 * archivo =
                 * rutaDelArchivo.substring(rutaDelArchivo.lastIndexOf("\\") +
                 * 1, rutaDelArchivo.length());
                 * bajarReporte17XLS.download(request, response, rutaDelArchivo,
                 * archivo);
                 * 
                 * }catch(IOException e){ e.printStackTrace(); }
                 * catch(ServletException f){ f.printStackTrace(); }
                 * 
                 * case 4: ManipuladorArchivo bajarReporte18XLS = new
                 * ManipuladorArchivo();
                 * 
                 * try{ String fechaReporte18 = ""; String periodoReporte18 =
                 * ""; String mesTemp18 = ""; String DATE_FORMAT2 =
                 * "dd/MM/yyyy"; SimpleDateFormat sdf2 = new
                 * SimpleDateFormat(DATE_FORMAT2);
                 * 
                 * Calendar cal = Calendar.getInstance(); Date fecha = new
                 * Date();
                 * 
                 * cal.set(cal.get(Calendar.YEAR),cal.get(Calendar.MONTH),cal.getActualMinimum(Calendar.DAY_OF_MONTH));
                 * 
                 * fecha = cal.getTime(); fechaReporte18 = sdf2.format(fecha);
                 * 
                 * int mes18 = Integer.parseInt(fechaReporte18.substring(3,5));
                 * 
                 * 
                 * if(mes18 == 1){
                 * 
                 * mesTemp18 = "12"; }
                 * 
                 * if(mes18 < 10){
                 * 
                 * mesTemp18 = "0" + Integer.toString(mes18 - 1);
                 * 
                 * }else{
                 * 
                 * mesTemp18 = Integer.toString(mes18 -1); }
                 * 
                 * periodoReporte18 = fechaReporte18.substring(6, 10) +
                 * mesTemp18; logger.debug(periodoReporte18); File f = new
                 * File(""); StringBuffer rutaReporte = new StringBuffer("");
                 * rutaReporte.append(f.getAbsolutePath());
                 * rutaReporte.append(IND_Constants.DIR_SIVEGAM_EXCEL_SIF018);
                 * rutaReporte.append(IND_Constants.SUF_SIVEGAM_SIF018);
                 * rutaReporte.append(IND_Constants.EXT_Excel);
                 * 
                 * String rutaDelArchivo = rutaReporte.toString(); String
                 * archivo =
                 * rutaDelArchivo.substring(rutaDelArchivo.lastIndexOf("\\") +
                 * 1, rutaDelArchivo.length());
                 * bajarReporte18XLS.download(request, response, rutaDelArchivo,
                 * archivo);
                 * 
                 * }catch(IOException e){ e.printStackTrace(); }
                 * catch(ServletException f){ f.printStackTrace(); }
                 * 
                 * case 5: ManipuladorArchivo bajarReporte19XLS = new
                 * ManipuladorArchivo();
                 * 
                 * try{ String fechaReporte19 = ""; String periodoReporte19 =
                 * ""; String mesTemp19 = ""; String DATE_FORMAT2 =
                 * "dd/MM/yyyy"; SimpleDateFormat sdf2 = new
                 * SimpleDateFormat(DATE_FORMAT2);
                 * 
                 * Calendar cal = Calendar.getInstance(); Date fecha = new
                 * Date();
                 * 
                 * cal.set(cal.get(Calendar.YEAR),cal.get(Calendar.MONTH),cal.getActualMinimum(Calendar.DAY_OF_MONTH));
                 * 
                 * fecha = cal.getTime(); fechaReporte19 = sdf2.format(fecha);
                 * 
                 * int mes19 = Integer.parseInt(fechaReporte19.substring(3,5));
                 * 
                 * 
                 * if(mes19 == 1){
                 * 
                 * mesTemp19 = "12"; }
                 * 
                 * if(mes19 < 10){
                 * 
                 * mesTemp19 = "0" + Integer.toString(mes19 - 1);
                 * 
                 * }else{
                 * 
                 * mesTemp19 = Integer.toString(mes19 -1); }
                 * 
                 * periodoReporte19 = fechaReporte19.substring(6, 10) +
                 * mesTemp19; logger.debug(periodoReporte19); File f = new
                 * File(""); StringBuffer rutaReporte = new StringBuffer("");
                 * rutaReporte.append(f.getAbsolutePath());
                 * rutaReporte.append(IND_Constants.DIR_SIVEGAM_EXCEL_SIF018);
                 * rutaReporte.append(IND_Constants.SUF_SIVEGAM_SIF018);
                 * rutaReporte.append(IND_Constants.EXT_Excel);
                 * 
                 * String rutaDelArchivo = rutaReporte.toString(); String
                 * archivo =
                 * rutaDelArchivo.substring(rutaDelArchivo.lastIndexOf("\\") +
                 * 1, rutaDelArchivo.length());
                 * bajarReporte19XLS.download(request, response, rutaDelArchivo,
                 * archivo);
                 * 
                 * }catch(IOException e){ e.printStackTrace(); }
                 * catch(ServletException f){ f.printStackTrace(); }
                 */
            default:
                return mapping.findForward("menuGeneracionReporte");

            }
        }
    }
}
