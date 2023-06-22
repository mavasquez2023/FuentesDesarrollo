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
import cl.araucana.sivegam.helper.IND_Constants;
import cl.araucana.sivegam.helper.ManipuladorArchivo;
import cl.araucana.sivegam.helper.SendFileToAS400;
import cl.araucana.sivegam.helper.SivegamLoggerHelper;
import cl.araucana.sivegam.impl.GenerarReportesAFCImpl;
import cl.araucana.sivegam.struts.Forms.CargarArchivosAFCForm;
import cl.araucana.sivegam.struts.dwr.actions.GenerarReporteAFCDWR;
import cl.araucana.sivegam.vo.FormatoReporteVO;
import cl.araucana.sivegam.vo.MaestroSivegamVO;
import cl.araucana.sivegam.vo.ProcesosAFCCesantiaVO;
import cl.araucana.sivegam.vo.RespuestaVO;
import cl.araucana.sivegam.vo.ValoresConexionAS400VO;
import cl.araucana.sivegam.vo.param.ListadoParametros;

public class CargarArchivosAFCAction extends Action {

    static SivegamLoggerHelper logger = SivegamLoggerHelper.getInstance();

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();

        String usuarioLogeado = (String) session.getAttribute("IDAnalista");

        if (usuarioLogeado == null) {

            request.setAttribute("Error", "Sesion cerrada. Por favor vuelva a ingresar");
            return mapping.findForward("logout");
        }

        CargarArchivosAFCForm cargaArchivosAFCForm = (CargarArchivosAFCForm) form;
        String anio = cargaArchivosAFCForm.getAnio();
        String mes = cargaArchivosAFCForm.getMes();
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
            opcion = Integer.parseInt(cargaArchivosAFCForm.getOpcion());

            switch (opcion) {

            case -1:

                session.invalidate();

                return mapping.findForward("logout");

            case 1:
                session.setAttribute("anio", anio);
                session.setAttribute("mes", mes);
                session.setAttribute("tipoReporteEnv", null);
                return mapping.findForward("GenRepAreaDivPrev");

            case 2:
                session.setAttribute("anio", anio);
                session.setAttribute("mes", mes);
                session.setAttribute("tipoReporteEnv", null);
                CargarArchivosAFCForm fileForm = cargaArchivosAFCForm;
                String periodo = String.valueOf(Integer.parseInt(anio) * 100 + Integer.parseInt(mes));

                try {

                    String extensionArchivo = "";
                    FormFile theFile = fileForm.getTheFile();
                    String contentType = theFile.getContentType();
                    String fileName = theFile.getFileName();
                    byte[] fileData = theFile.getFileData();

       //             logger.debug("FILE NAME: " + fileName);
                    String extension = fileName.substring(fileName.lastIndexOf(".") + 1).toUpperCase();
      //              logger.debug("extension: " + extension);

                    ListadoParametros listaParam = ListadoParametros.getInstancia();
                    FormatoReporteVO[] formatoReporte = listaParam.getListFormatoReportes();

                    for (int i = 0; i < formatoReporte.length; i++) {
                        if (formatoReporte[i].getFormato_reporte() == Long.parseLong((String) session.getAttribute("IdExtesionInformadoAFC"))) {
                            extensionArchivo = formatoReporte[i].getDescripcion_formato_reporte();
        //                    logger.debug("extension archivo: " + extensionArchivo);
                            break;
                        }
                    }
      //              logger.debug("extension archivo: " + extensionArchivo);
                    if (extension.equals(extensionArchivo)) {
                        if ("DAT".equals(extensionArchivo)) {
                            //logger.debug("ENTRA A .DAT");
                            ManipuladorArchivo subir = new ManipuladorArchivo();

                            File file = new File("");
                            StringBuffer rutaFile = new StringBuffer();
                            StringBuffer nameFile = new StringBuffer();

                            rutaFile.append(file.getAbsolutePath());
                            rutaFile.append(IND_Constants.DIR_AFC_UPLOADFILE);

                            String filePath = rutaFile.toString();
                            nameFile.append(fileName);

                            String fileNameOutput = nameFile.toString();

                            subir.upload(request, response, filePath, fileNameOutput, contentType, fileName, fileData);

                            String ip_conexion = null;
                            String user_conexion = null;
                            String pass_conexion = null;
                            ListadoParametros listaP = ListadoParametros.getInstancia();
                            ValoresConexionAS400VO[] conexion = listaP.getListValoresConexionToAS400();
                            for (int i = 0; i < conexion.length; i++) {
                                if (conexion[i].getId_conexion_as400() == 1) {
                                    ip_conexion = conexion[i].getDescripcion_conexion_as400();
                                }
                                if (conexion[i].getId_conexion_as400() == 2) {
                                    user_conexion = conexion[i].getDescripcion_conexion_as400();
                                }
                                if (conexion[i].getId_conexion_as400() == 3) {
                                    pass_conexion = conexion[i].getDescripcion_conexion_as400();
                                }
                            }
                            SendFileToAS400 server = new SendFileToAS400(ip_conexion, user_conexion, pass_conexion);

                            StringBuffer origen = new StringBuffer();
                            StringBuffer destino = new StringBuffer();

                            destino.append("//QDLS//INTPROC//");
                            destino.append("AFCINPA.TXT");

                            origen.append(rutaFile.toString());
                            origen.append(nameFile.toString());
                            //logger.debug("origen: " + origen);
                            //logger.debug("destino: " + destino);

                            //logger.debug("ANTES DE SERVER.COPIARARCHIVOTOAS400");

                            boolean respuesta = server.copiarArchivoToAS400(origen.toString(), destino.toString());
                            //logger.debug("PASO, VERIFICAR EN AS400 SI ARCHIVO FUE COPIADO");

                            //logger.debug("INI:cerrando conexion con AS400");
                            server.closeConnection();
                            //logger.debug("FIN:cerrando conexion con AS400");
                            if (respuesta) {
                                session.setAttribute("controlCobol", "0");
                            } else {
                                session.setAttribute("controlCobol", "9");
                            }
                            if ("R".equals(session.getAttribute("flagReporteInformadoAFC"))) {
                                session.setAttribute("identificadorFlagReporte", "R");
                                session.setAttribute("identificadorIdReporte", "8");
                                logger.debug("control: " + session.getAttribute("controlCobol"));
                                logger.debug("reporte informado: Retroactivo");
                            } else {
                                session.setAttribute("identificadorFlagReporte", "M");
                                session.setAttribute("identificadorIdReporte", "9");
                                logger.debug("reporte informado: Mensual");
                                logger.debug("control: " + session.getAttribute("controlCobol"));
                            }

                        } else {
                            if ("XLSX".equals(extension)) {

                                MaestroSivegamVO maestro = new MaestroSivegamVO();
                                ManipuladorArchivo subir = new ManipuladorArchivo();

                                File file = new File("");
                                StringBuffer rutaFile = new StringBuffer();
                                StringBuffer nameFile = new StringBuffer();
                                RespuestaVO respuesta = new RespuestaVO();
                                String filenameTemp = "";
                                if ("R".equals(session.getAttribute("flagReporteInformadoAFC"))) {
                                    session.setAttribute("idReporteReproceso", "8");

                                    ListadoParametros param = ListadoParametros.getInstancia();
                                    ProcesosAFCCesantiaVO[] procesosCesantia = param.getListProcesoAfcCesantia();
                                    FormatoReporteVO[] formato = param.getListFormatoReportes();
                                    for (int i = 0; i < procesosCesantia.length; i++) {
                                        if (procesosCesantia[i].getId_tipo_proceso() == 8) {
                                            filenameTemp = procesosCesantia[i].getNomenclatura_nombre_xls();
                                            break;
                                        }
                                    }
                                    for (int i = 0; i < formato.length; i++) {
                                        if (formato[i].getFormato_reporte() == 1) {
                                            extension = formato[i].getDescripcion_formato_reporte();
                                            break;
                                        }
                                    }

                                    rutaFile.append(file.getAbsolutePath());
                                    rutaFile.append(GenerarReportesAFCImpl.retornaRutaCargarArchivo(Integer.parseInt((String) session.getAttribute("idReporteReproceso"))));

                                    String filePath = rutaFile.toString();
                                    nameFile.append(fileName);

                                    fileName = filenameTemp.replaceAll("#", periodo) + "." + extension;

                                    String fileNameOutput = nameFile.toString();

                                    logger.debug("nameFile.toString() " + nameFile.toString());

                                    respuesta = GenerarReporteAFCDWR.deleteLogicoSegunReporteAFC(8);

                                    if (respuesta.getCodRespuesta() == 0) {

                                        if (respuesta.getCodRespuesta() == 0) {

                                            maestro = GenerarReporteAFCDWR.selectMaxIdMaestroSivegam((String) session.getAttribute("idReporteReproceso"), periodo);

                                            if (maestro.getMaestro_sivegam() == 0) {
                                                session.setAttribute("controlCobolReproceso", " ");

                                            } else {

                                                session.setAttribute("controlCobolReproceso", "0");
                                                session.setAttribute("idSivegamReprocesoAFC", Long.toString(maestro.getMaestro_sivegam()));
                                                session.setAttribute("idFlagReporteReproceso", "R");

                                                //logger.debug("" + maestro.getMaestro_sivegam());

                                                subir.upload(request, response, filePath, fileNameOutput, contentType, fileName, fileData);
                                                //logger.debug("PASO POR ACA AFC: " + "DESPUES DE DELETE LOGICO");
                                                respuesta = GenerarReporteAFCDWR.insertarReporte("R", fileName, periodo);
                                                //logger.debug("PASO POR ACA AFC: " + "DESPUES DE INSERTA REPORTE");

                                            }
                                        }
                                    }
                                } else {

                                    session.setAttribute("idReporteReproceso", "9");

                                    ListadoParametros param = ListadoParametros.getInstancia();
                                    ProcesosAFCCesantiaVO[] procesosCesantia = param.getListProcesoAfcCesantia();
                                    FormatoReporteVO[] formato = param.getListFormatoReportes();
                                    for (int i = 0; i < procesosCesantia.length; i++) {
                                        if (procesosCesantia[i].getId_tipo_proceso() == 9) {
                                            filenameTemp = procesosCesantia[i].getNomenclatura_nombre_xls();
                                            break;
                                        }
                                    }
                                    for (int i = 0; i < formato.length; i++) {
                                        if (formato[i].getFormato_reporte() == 1) {
                                            extension = formato[i].getDescripcion_formato_reporte();
                                            break;
                                        }
                                    }

                                    rutaFile.append(file.getAbsolutePath());
                                    rutaFile.append(GenerarReportesAFCImpl.retornaRutaCargarArchivo(Integer.parseInt((String) session.getAttribute("idReporteReproceso"))));

                                    String filePath = rutaFile.toString();
                                    nameFile.append(fileName);

                                    fileName = filenameTemp.replaceAll("#", periodo) + "." + extension;

                                    String fileNameOutput = nameFile.toString();

                                    logger.debug("nameFile.toString() " + nameFile.toString());

                                    respuesta = GenerarReporteAFCDWR.deleteLogicoSegunReporteAFC(9);

                                    if (respuesta.getCodRespuesta() == 0) {
                                        if (respuesta.getCodRespuesta() == 0) {
                                            maestro = GenerarReporteAFCDWR.selectMaxIdMaestroSivegam((String) session.getAttribute("idReporteReproceso"), periodo);
                                            if (maestro.getMaestro_sivegam() == 0) {
                                                session.setAttribute("controlCobolReproceso", " ");
                                            } else {
                                                session.setAttribute("controlCobolReproceso", "0");
                                                session.setAttribute("idSivegamReprocesoAFC", Long.toString(maestro.getMaestro_sivegam()));
                                                session.setAttribute("idFlagReporteReproceso", "M");
                                                //logger.debug("" + maestro.getMaestro_sivegam());
                                                subir.upload(request, response, filePath, fileNameOutput, contentType, fileName, fileData);
                                                //logger.debug("PASO POR ACA AFC: " + "DESPUES DE DELETE LOGICO");
                                                respuesta = GenerarReporteAFCDWR.insertarReporte("M", fileName, periodo);
                                                //logger.debug("PASO POR ACA AFC: " + "DESPUES DE INSERTA REPORTE");

                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ServletException f) {
                    f.printStackTrace();
                }

                return mapping.findForward("GenRepAreaDivPrev");

            default:
                return mapping.findForward("GenRepAreaDivPrev");

            }
        }
    }
}
