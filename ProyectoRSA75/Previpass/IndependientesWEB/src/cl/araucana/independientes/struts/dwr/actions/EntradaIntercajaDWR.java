package cl.araucana.independientes.struts.dwr.actions;

import java.io.IOException;
import java.net.MalformedURLException;
import java.sql.SQLException;
import cl.araucana.independientes.helper.EnviaMailUtil;
import cl.araucana.independientes.impl.EntradaIntercajaImpl;
import cl.araucana.independientes.impl.SalidaIntercajaImpl;
import cl.araucana.independientes.vo.EntradaIntercajaVO;
import cl.araucana.independientes.vo.SalidaIntercajaVO;
import cl.araucana.independientes.vo.param.ListadoParametros;
import cl.araucana.independientes.vo.param.Parametro;


public class EntradaIntercajaDWR {

    public EntradaIntercajaVO insertarMaestroIntercaja(String nombreArchivo, String fechaCabecera, String fechaProceso, String tipoArchivo, String rutAnalista)
    {
        return EntradaIntercajaImpl.insertarMaestroIntercaja(nombreArchivo, fechaCabecera, fechaProceso, tipoArchivo, rutAnalista);
    }

    public String enviarArchivoFTP(String fileName, String source, String tipoArchivo) throws MalformedURLException, IOException
    {
        return EntradaIntercajaImpl.enviarArchivoFTP(fileName, source, tipoArchivo);
    }

    public String transferirFileToAS400(String rutaArchivo, String nombreArchivo, String tipoArchivo)
    {
        return EntradaIntercajaImpl.transferirFileToAS400(rutaArchivo, nombreArchivo, tipoArchivo);
    }

    public String transferirFileToProccessAS400(String rutaArchivo, String nombreArchivo)
    {
        return EntradaIntercajaImpl.transferirFileToProccessAS400(rutaArchivo, nombreArchivo);
    }

    public EntradaIntercajaVO leerArchivo(String rutaArchivo, String tipoArchivo)
    {
        return EntradaIntercajaImpl.leerArchivo(rutaArchivo,tipoArchivo);
    }

    public EntradaIntercajaVO obtenerUltimaDiaMes(String fechaActual)
    {
        return EntradaIntercajaImpl.obtenerUltimaDiaMes(fechaActual); 
    }

    /*funcion que obtiene los datos para la grilla.*/
    public EntradaIntercajaVO obtenerDataArchivosProcesados(String fechaInicio, String fechaFin)
    {
        EntradaIntercajaImpl impl = new EntradaIntercajaImpl();
        EntradaIntercajaVO resp = new EntradaIntercajaVO();

        try
        {
            resp = impl.obtenerDataArchivosProcesados(fechaInicio, fechaFin);

            return resp;

        }catch(IOException e){

        }catch(SQLException f){

        }

        return resp;

    }

    /*funcion que verifica si un registro ya esta insertado en la tabla maestro intercaja*/
    public EntradaIntercajaVO validaRegistroMaestroIntercaja(String nombreArchivo, String tipoArchivo)
    {
        return EntradaIntercajaImpl.validaRegistroMaestroIntercaja(nombreArchivo, tipoArchivo);
    }

    /*Programa para invocar proceso Cobol*/
    public static EntradaIntercajaVO invocarProcesoCobol(String idTipoArchivo, String nombreArchivo, String idMaestroArchivo, String idUsuarioLlamada) throws IOException
    {
        EntradaIntercajaVO resp = new EntradaIntercajaVO();

        resp = EntradaIntercajaImpl.invocarProcesoCobol(idTipoArchivo, nombreArchivo, idMaestroArchivo, idUsuarioLlamada);

        return resp;
    }

    /*Funcion que consulta por estadistica de registros procesados*/
    public static EntradaIntercajaVO estadisticaTipoArchivoprocesadoCobol(String idTipoArchivo, String idMaestroArchivo)
    {
        return EntradaIntercajaImpl.estadisticaTipoArchivoprocesadoCobol(idTipoArchivo, idMaestroArchivo);
    }

    /*Funcion que obtiene el detalle del archivo procesado*/
    public EntradaIntercajaVO obtenerCabeceraDetalleFile(String nombreArchivo)
    {
        return EntradaIntercajaImpl.obtenerCabeceraDetalleFile(nombreArchivo);
    }

    public EntradaIntercajaVO obtenerCabeceraDetalleFileFallido(String nombreArchivo, String statusProceso){

        return EntradaIntercajaImpl.obtenerCabeceraDetalleFileFallido(nombreArchivo, statusProceso);
    }

    public EntradaIntercajaVO obtenerCabeceraDetalleFileCobol(String nombreArchivo, String idMaestroArchivo){

        return EntradaIntercajaImpl.obtenerCabeceraDetalleFileCobol(nombreArchivo, idMaestroArchivo);
    }

    /*Funcion que lee el archivo del flujo 4*/
    public EntradaIntercajaVO leerArchivoFlujo4(String rutaArchivo){

        return EntradaIntercajaImpl.leerArchivoFlujo4(rutaArchivo);
    }

    /*funcion que envia un mail luego de haber un archivo fallido. Dicho correo envia el idlogas400 para identificar el error.*/
    public String enviarMailIntercaja(String idLogAs400)
    {
        int flag = 1;

        ListadoParametros listaParamMailIntercaja = ListadoParametros.getInstancia();
        Parametro[] mailToIntercaja = listaParamMailIntercaja.getListMailToSalidaIntercaja();

        String mailTokens = new String();
        mailTokens = "";

        for(int i=0; i<mailToIntercaja.length; i++){
            if (mailToIntercaja[i].getEstado() == 1){
                mailTokens = mailTokens + mailToIntercaja[i].getGlosa() + "#";
            }
        }

        String subject = "Error Intercaja - Procesamiento de archivo de entrada.";

        String cuerpo = "Estimado:\n\n";
        cuerpo = cuerpo + "Se ha producido un error en la depuración del archivo.\n";
        cuerpo = cuerpo + "El código de error informado es: " + idLogAs400 + ".";
        cuerpo = cuerpo + "\n\n\n";
        cuerpo = cuerpo + "Atte\n";
        cuerpo = cuerpo + "Sistema Trabajador Independiente.";

        //cuerpo = EntradaIntercajaDWR.retornaCuerpoMailError(idLogAs400);	
        String ruta = "";
        String archivo = "";

        EnviaMailUtil.EnviarMail(mailTokens, subject, cuerpo, ruta, archivo, flag);

        return "OK";
    }

    public static String retornaCuerpoMailError(String idLogAs400)
    {
        String body = "";
        body = "<html>"
            + "<head>"
            + "<title>Archivo Salida Intercaja.</title>"
            + "</head>"
            + "<body >"
            + "	<table border='1' width='100%' bgcolor='#FFFFFF'>"
            + "<p align='left'>" 
            + "Estimado:" 
            + "</p>"
            + "<p align='left'>" 
            + "Se ha producido un error en la depuración del archivo."
            + "<br><br>"
            + "El código de error informado es: " + idLogAs400 + "."
            + "</p>"
            + "<p align='left'>"
            + "Atte."
            + "<br>" 
            + "Sistema Trabajador Independiente."
            + "</p>"
            + "	</table>"
            + "</body>"
            + "</html>";

        return body;
    }

    public String enviarArchivoAProcesarFTP(String nombreArchivo, String rutaArchivo)throws MalformedURLException, IOException
    {
        return EntradaIntercajaImpl.enviarArchivoAProcesarFTP(nombreArchivo, rutaArchivo);
    }

    public String deleteFileServer(String rutaArchivo)
    {
        return EntradaIntercajaImpl.deleteFileServer(rutaArchivo);
    }

    public EntradaIntercajaVO buscarRutaArchivo()
    {
        return EntradaIntercajaImpl.buscarRutaArchivo();
    }

    public EntradaIntercajaVO obtenerRangoIntercaja()
    {
        return EntradaIntercajaImpl.obtenerRangoIntercaja();
    }

    public EntradaIntercajaVO consultarStatusProceso(String nombreArchivo, String idTipoArchivo)
    {
        return EntradaIntercajaImpl.consultarStatusProceso(nombreArchivo, idTipoArchivo);
    }

    public EntradaIntercajaVO borrarArchivos()
    {
        return EntradaIntercajaImpl.borrarArchivos();

    }

    public static EntradaIntercajaVO obtenerPrimerYUltimoDiaMes(String fechaSistema)
    {
        return EntradaIntercajaImpl.obtenerPrimerYUltimoDiaMes(fechaSistema); 
    }
}
