package cl.araucana.sivegam.struts.Actions;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

import cl.araucana.sivegam.dao.ParametrosDAO;
import cl.araucana.sivegam.helper.ManipuladorArchivo;
import cl.araucana.sivegam.helper.SivegamLoggerHelper;
import cl.araucana.sivegam.struts.Forms.CargaArchivosCesantiaForm;
import cl.araucana.sivegam.struts.dwr.actions.GenerarReporteCesantiaDWR;
import cl.araucana.sivegam.vo.FormatoReporteVO;
import cl.araucana.sivegam.vo.MaestroSivegamVO;
import cl.araucana.sivegam.vo.ProcesosAFCCesantiaVO;
import cl.araucana.sivegam.vo.RespuestaVO;
import cl.araucana.sivegam.vo.param.ListadoParametros;

public class CargarArchivosCesantiaAction extends Action {

    static SivegamLoggerHelper logger = SivegamLoggerHelper.getInstance();

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();

        String usuarioLogeado = (String) session.getAttribute("IDAnalista");

        if (usuarioLogeado == null) {

            request.setAttribute("Error", "Sesion cerrada. Por favor vuelva a ingresar");
            return mapping.findForward("logout");
        }

        CargaArchivosCesantiaForm cargaCesantiaForm = (CargaArchivosCesantiaForm) form;

        String anio = cargaCesantiaForm.getAnio();
        String mes = cargaCesantiaForm.getMes();
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
            opcion = Integer.parseInt(cargaCesantiaForm.getOpcion());

            switch (opcion) {

            case -1:

                session.invalidate();

                return mapping.findForward("logout");

            case 1:
                session.setAttribute("anio", anio);
                session.setAttribute("mes", mes);
                return mapping.findForward("reporteCesantia");

            case 2:

                session.setAttribute("anio", anio);
                session.setAttribute("mes", mes);

                CargaArchivosCesantiaForm fileForm = cargaCesantiaForm;

                try {
                    String extensionArchivo = "";
                    String rutaCarpeta = "";
                    FormFile theFile = fileForm.getTheFile();
                    String contentType = theFile.getContentType();
                    String fileName = theFile.getFileName();
                    byte[] fileData = theFile.getFileData();
                    int idReporteCesantiaEnviado = Integer.parseInt((String) session.getAttribute("idReporteCesantiaEnv"));

                    String periodo = (String) session.getAttribute("periodoRep");
                 //   logger.debug("filename: " + fileName);
                    String extension = fileName.substring(fileName.lastIndexOf(".") + 1).toUpperCase();
                //    logger.debug("extension: " + extension);

                    ListadoParametros listaParam = ListadoParametros.getInstancia();
                    FormatoReporteVO[] formatoReporte = listaParam.getListFormatoReportes();

                    for (int i = 0; i < formatoReporte.length; i++) {
                        if (formatoReporte[i].getFormato_reporte() == 1) {
                            extensionArchivo = formatoReporte[i].getDescripcion_formato_reporte();
              //              logger.debug("extension archivo: " + extensionArchivo);
                            break;
                        }
                    }
           //         logger.debug("extension archivo: " + extensionArchivo);
                    if (extension.equals(extensionArchivo)) {

                        ManipuladorArchivo subir = new ManipuladorArchivo();

                        File file = new File("");
                        StringBuffer rutaFile = new StringBuffer();
                        StringBuffer nameFile = new StringBuffer();

                        ListadoParametros param = ListadoParametros.getInstancia();
                        ProcesosAFCCesantiaVO[] procesosCesantia = param.getListProcesoAfcCesantia();
                        for (int i = 0; i < procesosCesantia.length; i++) {
                            if (procesosCesantia[i].getId_tipo_proceso() == idReporteCesantiaEnviado) {
                                rutaCarpeta = procesosCesantia[i].getRuta_xls_servidor();
                                break;
                            }
                        }

                        rutaFile.append(file.getAbsolutePath());
                        rutaFile.append(rutaCarpeta);

                        String filePath = rutaFile.toString();
      //                  logger.debug("FILEPATH: " + filePath.toString());

                        String fileNametemp = "";
                        switch (idReporteCesantiaEnviado) {
                        case 10:
                            fileNametemp = "SC41_" + periodo + ".XLSX";
                            break;
                        case 11:
                            fileNametemp = "SC42_" + periodo + ".XLSX";
                            break;
                        case 12:
                            fileNametemp = "SC43_" + periodo + ".XLSX";
                            break;
                        case 13:
                            fileNametemp = "SC44_" + periodo + ".XLSX";
                            break;
                        default:
                            fileNametemp = "reporte";
                        }

                        nameFile.append(fileName);

                        fileName = fileNametemp;

                        String fileNameOutput = nameFile.toString();

                        subir.upload(request, response, filePath, fileNameOutput, contentType, fileName, fileData);

                        RespuestaVO respuesta = new RespuestaVO();
                        MaestroSivegamVO maestroSivegam = new MaestroSivegamVO();

                        respuesta = GenerarReporteCesantiaDWR.deleteLogicoSegunReporte(idReporteCesantiaEnviado);
                        if (respuesta.getCodRespuesta() == 0) {
                            //logger.debug("PASO POR ACA: " + "DESPUES DE DELETE LOGICO");
                            respuesta = GenerarReporteCesantiaDWR.insertarReporte(idReporteCesantiaEnviado, fileName, periodo);
                            //logger.debug("PASO POR ACA: " + "DESPUES DE INSERTA REPORTE");

                            if (respuesta.getCodRespuesta() == 0) {
                            	session.setAttribute("codRespuestaCargaCesantia", respuesta.getCodRespuesta());
                            	
                                maestroSivegam = GenerarReporteCesantiaDWR.selectMaxIdMaestroSivegamCesantia(idReporteCesantiaEnviado, periodo);
                                if (maestroSivegam.getMaestro_sivegam() == 0) {
                                    session.setAttribute("respuestaCargaCesantia", " ");

                                } else {

                                    logger.debug("IDMAESTROSIVEGAM desde ACTION: " + maestroSivegam.getMaestro_sivegam());
                                    session.setAttribute("respuestaCargaCesantia", "0");
                                    session.setAttribute("controlReporteCes", Integer.toString(idReporteCesantiaEnviado));
                                    session.setAttribute("controlIdSivegam", Long.toString(maestroSivegam.getMaestro_sivegam()));

                                }
                            }
                        }

                        if (respuesta.getCodRespuesta() == 99) {
                            session.setAttribute("codRespuestaCargaCesantia", respuesta.getCodRespuesta());
                            session.setAttribute("respuestaCargaCesantia", respuesta.getMsgRespuesta());
                        }

                        logger.debug("************** FIN CESANTIA ACTION  -  CARGA ARCHIVO ************************");

                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ServletException f) {
                    f.printStackTrace();
                }

                return mapping.findForward("reporteCesantia");

            default:
                return mapping.findForward("reporteCesantia");

            }
        }
    }
}
