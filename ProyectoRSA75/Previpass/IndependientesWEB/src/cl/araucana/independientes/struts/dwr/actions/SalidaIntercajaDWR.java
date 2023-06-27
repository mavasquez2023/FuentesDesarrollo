package cl.araucana.independientes.struts.dwr.actions;

import java.io.IOException;
import java.net.MalformedURLException;
import java.sql.SQLException;

import cl.araucana.independientes.helper.EnviaMailUtil;
import cl.araucana.independientes.impl.SalidaIntercajaImpl;
import cl.araucana.independientes.vo.RespuestaVO;
import cl.araucana.independientes.vo.SalidaIntercajaVO;
import cl.araucana.independientes.vo.param.ListadoParametros;
import cl.araucana.independientes.vo.param.Parametro;

public class SalidaIntercajaDWR {

    public static SalidaIntercajaVO consultaSalidaAfiliadosIntercaja(String fechaInicio, String fechaFin, String user)
    {
        SalidaIntercajaVO ret = new SalidaIntercajaVO();

        try{
            ret = SalidaIntercajaImpl.consultaSalidaAfiliadosIntercaja(fechaInicio, fechaFin, user);

            return ret;

        }catch(IOException e){

        }catch(SQLException f){

        }

        return ret;	
    }

    /*Funcion que envia e-mail con archivo de salida*/
    public static String  enviarMailIntercaja(String archivoIntercaja, String fechaInicio, String fechaFin, String nueva, String desafi, String cambio, String fallecido, String nombreMes, String anioEnvio, String nombreCompleto)
    {
        int flag = 1;
        ListadoParametros listaParamMailIntercaja = ListadoParametros.getInstancia();
        Parametro[] mailToIntercaja = listaParamMailIntercaja.getListMailToSalidaIntercaja();

        String mailTokens = new String();
        mailTokens = "";

        for(int i=0; i<mailToIntercaja.length; i++)			
        {
            if (mailToIntercaja[i].getEstado() == 1){
                mailTokens = mailTokens + mailToIntercaja[i].getGlosa() + "#";
            }
        }

        String subject = "Proceso de Intercaja" + " " + nombreMes + " " + "del" + " " + anioEnvio;
        String cuerpo = "";

        cuerpo = SalidaIntercajaDWR.retornaCuerpoMail(nueva, desafi, cambio, fallecido, nombreMes, anioEnvio, nombreCompleto);	
        String ruta = "";
        String archivo = "";

        ruta = archivoIntercaja;
        archivo = archivoIntercaja.substring(archivoIntercaja.lastIndexOf("\\") + 1, archivoIntercaja.length());

        //EnviaMailUtil.EnviarMailParaIntercaja(mailTokens, subject, cuerpo, ruta, archivo);
        System.out.println("mailTokens: "+ mailTokens);
        System.out.println("subject: "+ subject);
        System.out.println("cuerpo: "+ cuerpo);
        System.out.println("ruta: "+ ruta);
        System.out.println("archivo: "+ archivo);
        System.out.println("flag: "+ flag);

        EnviaMailUtil.EnviarMail(mailTokens, subject, cuerpo, ruta, archivo, flag);
        return "OK";
    }

    public static SalidaIntercajaVO insertaRegistroLogIntercaja(String nombreArchivo, String numeroSesion, String fechaSesion, String fechaInicio, String fechaCorte, String fechaProceso, String rutAnalista, String afiliadosNuevos, String desafiliados, String cambioCCAF, String fallecidos, String desafiliadosTipoUno, String desafiliadosTipoDos)
    {
        return SalidaIntercajaImpl.insertaRegistroLogIntercaja(nombreArchivo, numeroSesion, fechaSesion, fechaInicio, fechaCorte, fechaProceso, rutAnalista, afiliadosNuevos, desafiliados, cambioCCAF, fallecidos, desafiliadosTipoUno, desafiliadosTipoDos);
    }

    public static SalidaIntercajaVO verificaNumeroSesion(String numeroSesion)
    {
        return SalidaIntercajaImpl.verificaNumeroSesion(numeroSesion); 
    }

    public static SalidaIntercajaVO obtenerPrimerYUltimoDiaMes(String fechaSistema)
    {
        return SalidaIntercajaImpl.obtenerPrimerYUltimoDiaMes(fechaSistema); 
    }

    public static SalidaIntercajaVO updateStatusSendMail(String nombreArchivo)
    {
        return SalidaIntercajaImpl.updateStatusSendMail(nombreArchivo);
    }

    /*Funcion que retorna el cuerpo del mensaje que se va a enviar en el archivo de salida de intercaja
     * Se construye un cuerpo en html.
     * Parametros de entrada:
     * 	.- estadistica1: corresponde al total de las afiliaciones nuevas.
     *  .- estadistica2: corresponde al total de las desafiliaciones.
     *  .- estadistica3: corresponde al total de los cambios de CCAF.
     *  .- estadistica4: corresponde al total de los fallecidos.
     *  .- nombre: corresponde al nombre del mes actual que se consultó para el proceso de intercaja.
     *  .- anio: corresponde al año.
     *  .- nombreUser: corresponde al analista que se logueó en el sistema y es el que firma el correo al enviar el archivo de salida hacia intercaja.
     *  
     * La funcion retorna un String que contiene un formato HTML. 
     * */
    public static String retornaCuerpoMail(String estadistica1, String estadistica2, String estadistica3, String estadistica4, String nombre, String anio, String nombreUser)
    {
        String body = "";
        body = "<html>"
            + "<head>"
            + "<title>Archivo Salida Intercaja.</title>"
            + "</head>"
            + "<body >"
            + "	<table border='1' width='100%' bgcolor='#FFFFFF'>"
            + "<p align='left'>" 
            + "Don Eduardo:" 
            + "</p>"
            + "<p align='left'>" 
            + "En este correo se adjunta el archivo de proceso de afiliación de Independientes correspondiente al mes de " + nombre + " " + anio + "."
            + "<br><br>"
            + "El Resumen de movimientos de afiliados contenidos en el archivo son:"
            + "</p>"
            + "<p align='left'>" 
            + "&nbsp;&nbsp;&nbsp;" + ".- Afiliaciones Nuevas: " + estadistica1 + "<br>"
            + "&nbsp;&nbsp;&nbsp;" + ".- Desafiliaciones: " + estadistica2 + "<br>"
            + "&nbsp;&nbsp;&nbsp;" + ".- Cambio CCAF: " + estadistica3 + "<br>"
            + "&nbsp;&nbsp;&nbsp;" + ".- Fallecidos: " + estadistica4 + "<br>"
            + "</p>"
            + "<p align='left'>"
            + "Favor confirmar recepción a los remitentes copiados."
            + "</p>"
            + "<p align='left'>"
            + "Atte."
            + "<br>" 
            + nombreUser + "."
            + "</p>"
            + "	</table>"
            + "</body>"
            + "</html>";

        return body;
    }

    public String enviarArchivoHistoricoAFTP(String nombreArchivo, String rutaArchivo)throws MalformedURLException, IOException
    {
        return SalidaIntercajaImpl.enviarArchivoHistoricoAFTP(nombreArchivo, rutaArchivo);
    }

    public int eliminarArchivoServer()
    {
        return SalidaIntercajaImpl.eliminarArchivoServer();
    }

    public RespuestaVO verificarFlagCorreo(String nombreArchivoEnviado)
    {
        return SalidaIntercajaImpl.verificarFlagCorreo(nombreArchivoEnviado);
    }

    public SalidaIntercajaVO obtenerRangoIntercaja()
    {
        return SalidaIntercajaImpl.obtenerRangoIntercaja();
    }

    public String transferirFileToAS400(String rutaArchivo, String nombreArchivo, String tipoArchivo)
    {
        return SalidaIntercajaImpl.transferirFileToAS400(rutaArchivo, nombreArchivo, tipoArchivo);
    }
}
