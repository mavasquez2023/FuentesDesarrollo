/**
 * 
 *//*
package conector.test.manual;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.SocketTimeoutException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


import org.apache.commons.beanutils.PropertyUtils;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlOptions;
import org.apache.xmlbeans.XmlValidationError;
import org.w3c.dom.Node;

import conector.excepciones.SysException;
import conector.lme.ws.cliente.operador.LMEInfValCCAFResponse;
import conector.lme.ws.cliente.operador.LicenciaSimpleType;
import conector.lme.ws.cliente.operador.LicenciaType;
import conector.lme.ws.cliente.operador.RespuestaDetalleLicencia;
import conector.lme.ws.cliente.operador.RespuestaServicio;
import conector.lme.ws.cliente.operador.ResultadoType;
import conector.lme.ws.cliente.operador.ServiciosMultiOperador;
import conector.lme.ws.cliente.operador.ServiciosOperador;
import conector.lme.ws.cliente.operador.excepciones.ErrorInvocacionOperadorException;

import wwwLmeGovClLme.CTDetalleHaber;
import wwwLmeGovClLme.CTDireccionArchivo;
import wwwLmeGovClLme.CTEstado;
import wwwLmeGovClLme.CTHaberes;
import wwwLmeGovClLme.CTLugarReposo;
import wwwLmeGovClLme.CTRemuneracion;
import wwwLmeGovClLme.CTResolucion;
import wwwLmeGovClLme.CTTelefono;
import wwwLmeGovClLme.CTZONA0;
import wwwLmeGovClLme.CTZONAA;
import wwwLmeGovClLme.CTZONAB;
import wwwLmeGovClLme.CTZONAC;
import wwwLmeGovClLme.CTZONAC1;
import wwwLmeGovClLme.CTZONAC2;
import wwwLmeGovClLme.CTZONAC3;
import wwwLmeGovClLme.CTZONAC4;
import wwwLmeGovClLme.CTZONACC;
import wwwLmeGovClLme.CTZONAD;
import wwwLmeGovClLme.CTZONAD1;
import wwwLmeGovClLme.CTZONAF;
import wwwLmeGovClLme.LMEDocument;
import wwwLmeGovClLme.STCodigoComuna;
import wwwLmeGovClLme.STEntidadPagadora;
import wwwLmeGovClLme.STJornadaReposo;

*//**
 * 
 * 
 * @author jcea@microsystem.cl
 *//*
public class Prueba0A {
    *//** Serial Version UID. *//*
    private static final long serialVersionUID = 1L;

    *//**
     * @param args
     * @throws ErrorInvocacionOperadorException
     * @throws ConfiguracionMedipassException
     *//*
    public static void main(String[] args) {
        System.getProperties().put("http.proxyHost", "proxy.int.msys.cl");
        System.getProperties().put("http.proxyPort", "3128");
         3.1 
        //pruebaLMEEvenLcc(); //ok

         3.2 
        pruebaLMEDetLcc(new BigInteger("719253"));//667230 654421/2 IMG/PDF
        //pruebaLMEDetLcc(1350);
        //pruebaLMEDetLcc(1136);
        //pruebaLMEDetLcc(79); // zona B
        //pruebaLMEDetLcc(1838); //medipass
//        pruebaLMEDetLcc(245); //msys
        //pruebaLMEDetLcc(26); //imed

         3.4 
        //pruebaLMEDevEmp(1347); //ok

         3.5 
//        pruebaLMEInfValCCAF(674474); //ok

         3.6 
//        pruebaLMEInfLiquid(719253);// 667230);//(1346); 

         3.7 
//        pruebaLMEInfSeccC(1693);

         otros 
        //listarLMEDetLcc(1131, 1200);
        //pruebaXML("a.xml");
        //beanInspector(RespuestaDetalleLicencia.class, "respuesta", 10, null);
        //pruebaXMLBean(2);
        //pruebaMultiOperador();
    }

     Multioperador Rocarras     
    public static void pruebaMultiOperador() throws SocketTimeoutException {
        ServiciosMultiOperador s1 = new ServiciosMultiOperador();
        ServiciosMultiOperador s2 = new ServiciosMultiOperador("/microsystem.properties");
        ServiciosMultiOperador s3 = new ServiciosMultiOperador("/imed.properties");
        ServiciosMultiOperador s4 = new ServiciosMultiOperador("3","C","10105","12345","http://200.0.156.60:8080/lme2_fonasa/pronunciamiento/lmes/service");        
        log("Configuracion: MEDIPASS (DEFAULT)");
        pruebaMultiOperador(s1);
        log("Configuracion: Microsystem");
        pruebaMultiOperador(s2);
        log("Configuracion: IMED");
        pruebaMultiOperador(s3);
        log("Configuracion: From String");
        pruebaMultiOperador(s4);        
    }
    
    public static void pruebaMultiOperador(ServiciosMultiOperador servicio) throws SocketTimeoutException {
        try {
            LicenciaType[] listaLicencias = servicio.consultaEventosLicencias(2011, 8, 24,0,0);
            for (int i = 0; i < listaLicencias.length; i++) {
                LicenciaType estadoLicencia = listaLicencias[i];
                log("lic: " + estadoLicencia.getNumLicencia() + "-" + estadoLicencia.getDigLicencia() + "   "
                        + estadoLicencia.getFecha().getTime() + " " + estadoLicencia.getEstado());
            }
            log("");
        } catch (SysException e) {
            log("EL SISTEMA NO ESTA DISPONIBLE, REINTENTE");
            System.err.println("REGISTRO OPERADOR: " + e);
            e.printStackTrace();
        }
    }    
    
    
    *//**
     * 3.1
     * @throws SocketTimeoutException 
     *//*
    public static void pruebaLMEEvenLcc() throws SocketTimeoutException {
        log("--------- pruebaLMEEvenLcc");

        try {
//            ServiciosMultiOperador servicio = new ServiciosMultiOperador("4", "C", "10105", "10105", "http://desarrollo.medipass.cl/webapp/ws/ws_fonasa.php"); //MEDIPASS            
            //ServiciosMultiOperador servicio = new ServiciosMultiOperador("4", "C", "10105", "10105", "http://www.medipass.cl/webapp/ws/ws_fonasa.php"); //MEDIPASS       
//            ServiciosMultiOperador servicio = new ServiciosMultiOperador("3","C","10105","12345","http://ws.fonasa.cl:8080/LME/MSEvenLcc"); //IMED //200.0.156.60:8080/lme2_fonasa/pronunciamiento/lmes/service
            ServiciosMultiOperador servicio = new ServiciosMultiOperador("3","C","10105","petrohue","http://www.licencia.cl/v2/produccion/pronunciamiento/lmes/service");
            //ServiciosMultiOperador servicio = new ServiciosMultiOperador("5", "C", "70016160", "1", "http://moon.cloud.msys.cl:8080/ServiciosMicrosystemWeb/services/WsLMEInetSOAP"); //MSYS            
            //LicenciaType[] listaLicencias = ServiciosOperador.consultaEventosLicencias(2011, 8, 01);
            LicenciaType[] listaLicencias = servicio.consultaEventosLicencias(2011, 8, 23,0,1);            
            for (int i = 0; i < listaLicencias.length; i++) {
                LicenciaType estadoLicencia = listaLicencias[i];
                log("lic: " + estadoLicencia.getNumLicencia() + "-" + estadoLicencia.getDigLicencia() + "   "
                        + estadoLicencia.getFecha().getTime() + " " + estadoLicencia.getEstado());
            }
        } catch (SysException e) {
            log("EL SISTEMA NO ESTA DISPONIBLE, REINTENTE");
            System.err.println("REGISTRO OPERADOR: " + e);
            e.printStackTrace();
        }
    }

    *//**
     * 3.2
     *//*
    public static void pruebaLMEDetLcc(BigInteger numLicencia) {
        log("--------- pruebaLMEDetLcc");
        try {
            String digLicencia = dv(numLicencia.intValue());

            //RespuestaDetalleLicencia respuesta = ServiciosOperador.consultaDetalleLicencia(numLicencia, digLicencia); OLD Aldrin
            //ServiciosMultiOperador servicio = new ServiciosMultiOperador("3","C","10105","12345","http://200.0.156.60:8080/lme2_fonasa/pronunciamiento/lmes/service"); //IMED
            ServiciosMultiOperador servicio = new ServiciosMultiOperador("3","C","10105","petrohue","http://www.licencia.cl/v2/produccion/pronunciamiento/lmes/service"); //IMED
            //ServiciosMultiOperador servicio = new ServiciosMultiOperador("4", "C", "10105", "10105", "http://www.medipass.cl/webapp/ws/ws_fonasa.php"); //MEDIPASS
            //ServiciosMultiOperador servicio = new ServiciosMultiOperador("5", "C", "70016160", "1", "http://moon.cloud.msys.cl:8080/ServiciosMicrosystemWeb/services/WsLMEInetSOAP"); //MSYS            
            RespuestaDetalleLicencia respuesta = servicio.consultaDetalleLicencia(numLicencia, digLicencia);
            log(respuesta.getXmlLME());
            log("--Ini ---------------------------------------------");
            CTEstado[] estado = respuesta.getZona0().getZONA01().getEstadoArray();

            int ultimoEstado = 0;
            Date fechaUtimoEstado = null;
            for (int i = 0; i < estado.length; i++) {

                if (fechaUtimoEstado == null) {
                    ultimoEstado = estado[i].getEstadoLicencia().intValue();
                    fechaUtimoEstado = estado[i].getFechaEstado().getTime();
               }
                log("fechaUtimoEstado:" + fechaUtimoEstado);
                
                log("estados:" + estado[i].getEstadoLicencia() + " Fecha:" + estado[i].getFechaEstado().getTime());
                //if (fechaUtimoEstado == null || fechaUtimoEstado.compareTo(estado[i].getFechaEstado().getTime()) > 0) {                
                if (fechaUtimoEstado.compareTo(estado[i].getFechaEstado().getTime()) < 0) {
                      ultimoEstado = estado[i].getEstadoLicencia().intValue();
                      fechaUtimoEstado = estado[i].getFechaEstado().getTime();
                }
                
            }
            log("ultimo estado:" + ultimoEstado);

            CTLugarReposo[] lugarReposo = respuesta.getZonaA().getZONAA4().getLugarReposoArray();
            for (int i = 0; i < lugarReposo.length; i++) {
                log("respuesta.getZonaA().getZONAA4().getCodigoLugarReposo:" + lugarReposo[i].getCodigoLugarReposo());
                log("respuesta.getZonaA().getZONAA4().getJustificaDomicilio:" + lugarReposo[i].getJustificaDomicilio());
                log("respuesta.getZonaA().getZONAA4().getCalle:" + lugarReposo[i].getDireccionReposo().getCalle());
                log("respuesta.getZonaA().getZONAA4().getComuna:" + lugarReposo[i].getDireccionReposo().getComuna());
            }

            CTTelefono[] telefono = respuesta.getZonaA().getZONAA4().getTelefonoReposoArray();
            for (int i = 0; i < telefono.length; i++) {
                log("respuesta.getZonaA().getZONAA4().getTelefono:" + telefono[i].getTelefono());

            }

            log("respuesta.zonaA.ZONAA3.codigoTipoLicencia:" + respuesta.getZonaA().getZONAA3().getCodigoTipoLicencia());

            if (respuesta.getZonaC() != null) {
                CTRemuneracion[] remuneracion = respuesta.getZonaC().getZONAC3().getRemuneracionArray();
                for (int i = 0; i < remuneracion.length; i++) {
                    //parche msys
                    log("respuesta.zonaC.ZONAC3.remuneracionArray[].anoMesRemAnt:" + remuneracion[i].getAnoMesRemAnt());
                    log("respuesta.zonaC.ZONAC3.remuneracionArray[].ndiasRemAnt:" + remuneracion[i].getNdiasRemAnt());
                    log("respuesta.zonaC.ZONAC3.remuneracionArray[].montoImponibleRemAnt:"
                            + remuneracion[i].getMontoImponibleRemAnt());
                    log("respuesta.zonaC.ZONAC3.remuneracionArray[].montoTotalRemAnt:"
                            + remuneracion[i].getMontoTotalRemAnt());
                    log("respuesta.zonaC.ZONAC3.remuneracionArray[].ndiasIncapacidadRemAnt:"
                            + remuneracion[i].getNdiasIncapacidadRemAnt());
                    log("respuesta.zonaC.ZONAC3.remuneracionArray[].montoIncapacidadRemAnt:"
                            + remuneracion[i].getMontoIncapacidadRemAnt());
                }                
            }

            //if (respuesta.getZonaC().getZONACC().getHaberes() != null) {
            if (respuesta.getZonaC() != null) {
                log("respuesta.zonaC.ZONAC2.codigoLetraCaja:" + respuesta.getZonaC().getZONAC2().getCodigoLetraCaja());                                

                CTDireccionArchivo[] archivo = respuesta.getZonaC().getZONACC().getHaberes().getArchivoArray();
                for (int i = 0; i < archivo.length; i++) {
                    log("respuesta.zonaC.ZONACC.haberes.archivoArray[].tipoArchivo:" + archivo[i].getTipoArchivo());
                    log("respuesta.zonaC.ZONACC.haberes.archivoArray[].urlArchivo:" + archivo[i].getUrlArchivo());                    
                }

                CTDetalleHaber[] detalleHabers = respuesta.getZonaC().getZONACC().getHaberes().getDetalleArray();
                for (int i = 0; i < detalleHabers.length; i++) {
                    log("respuesta.zonaC.ZONACC.haberes.detalleArray[].anoMesRenta:"
                            + detalleHabers[i].getAnoMesRenta());
                    log("respuesta.zonaC.ZONACC.haberes.detalleArray[].nombreHaber:"
                            + detalleHabers[i].getNombreHaber());
                    //log("respuesta.zonaC.ZONACC.haberes.detalleArray[].montoHaber:" + detalleHabers[i].getMontoHaber());
                }
            }

            if (respuesta.getZonaB() != null) {

                log("respuesta.getZonaB().getZONAB1():" + respuesta.getZonaB().getZONAB1());

                CTResolucion[] resolucions = respuesta.getZonaB().getZONAB1().getResolucionArray();
                for (int i = 0; i < resolucions.length; i++) {
                    log("respuesta.zonaB.ZONAB1.resolucionArray[].codigoCausaRechazo:"
                            + resolucions[i].getCodigoCausaRechazo());
                    log("respuesta.zonaB.ZONAB1.resolucionArray[].codigoContinuacion:"
                            + resolucions[i].getCodigoContinuacion());
                    log("respuesta.zonaB.ZONAB1.resolucionArray[].codigoDerechoSubsidio:"
                            + resolucions[i].getCodigoDerechoSubsidio());
                }

                //STJornadaReposo.Enum x = resolucions[0].getCodigoJornadaReposoAutorizadoArray(0);                    
                //log("--->x" + x);

            }

            log("--Fin ---------------------------------------------");

            log("respuesta.getZonaA().getZONAA1().getTrabajador().getRut(): "
                    + respuesta.getZonaA().getZONAA1().getTrabajador().getRut());
            log("respuesta.getZonaA().getZONAA1().getTrabajador().getApellidoPaterno(): "
                    + respuesta.getZonaA().getZONAA1().getTrabajador().getApellidoPaterno());
            log("respuesta.getZonaA().getZONAA1().getTrabajador().getApellidoMaterno(): "
                    + respuesta.getZonaA().getZONAA1().getTrabajador().getApellidoMaterno());
            log("respuesta.getZonaA().getZONAA1().getTrabajador().getNombres(): "
                    + respuesta.getZonaA().getZONAA1().getTrabajador().getNombres());
            log("respuesta.getZonaA().getZONAA1().getFechaEmision(): "
                    + respuesta.getZonaA().getZONAA1().getFechaEmision());
            log("respuesta.getZonaA().getZONAA1().getFechaInicioReposo(): "
                    + respuesta.getZonaA().getZONAA1().getFechaInicioReposo());
            log("respuesta.getZonaA().getZONAA1().getTrabajador().getEdad(): "
                    + respuesta.getZonaA().getZONAA1().getTrabajador().getEdad());
            log("respuesta.getZonaA().getZONAA1().getTrabajador().getSexo(): "
                    + respuesta.getZonaA().getZONAA1().getTrabajador().getSexo());
            log("respuesta.getZonaA().getZONAA1().getTraNdias(): " + respuesta.getZonaA().getZONAA1().getTraNdias());
            log("respuesta.getZonaA().getZONAA3().getCodigoTipoLicencia(): "
                    + respuesta.getZonaA().getZONAA3().getCodigoTipoLicencia());
            log("respuesta.getZonaA().getZONAA3().getCodigoRecuperabilidad(): "
                    + respuesta.getZonaA().getZONAA3().getCodigoRecuperabilidad());
            log("respuesta.getZonaA().getZONAA3().getCodigoInicioTramInv(): "
                    + respuesta.getZonaA().getZONAA3().getCodigoInicioTramInv());
            log("respuesta.getZonaA().getZONAA4().getCodigoTipoReposo(): "
                    + respuesta.getZonaA().getZONAA4().getCodigoTipoReposo());

            log("respuesta.getZonaA().getZONAA5().getProfesional().getRut(): "
                    + respuesta.getZonaA().getZONAA5().getProfesional().getRut());
            log("respuesta.getZonaA().getZONAA5().getProfesional().getApellidoPaterno(): "
                    + respuesta.getZonaA().getZONAA5().getProfesional().getApellidoPaterno());
            log("respuesta.getZonaA().getZONAA5().getProfesional().getApellidoMaterno(): "
                    + respuesta.getZonaA().getZONAA5().getProfesional().getApellidoMaterno());
            log("respuesta.getZonaA().getZONAA5().getProfesional().getNombres(): "
                    + respuesta.getZonaA().getZONAA5().getProfesional().getNombres());
            log("respuesta.getZonaA().getZONAA5().getProfDireccion().getCalle(): "
                    + respuesta.getZonaA().getZONAA5().getProfDireccion().getCalle());
            log("respuesta.getZonaA().getZONAA5().getProfDireccion().getComuna(): "
                    + respuesta.getZonaA().getZONAA5().getProfDireccion().getComuna());
            log("respuesta.getZonaA().getZONAA5().getProfTelefono().getTelefono(): "
                    + respuesta.getZonaA().getZONAA5().getProfTelefono().getTelefono());
            log("respuesta.getZonaA().getZONAA5().getProfEmail(): " + respuesta.getZonaA().getZONAA5().getProfEmail());
            log("respuesta.getZonaA().getZONAA5().getProfEspecialidad(): "
                    + respuesta.getZonaA().getZONAA5().getProfEspecialidad());
            log("respuesta.getZonaA().getZONAA5().getCodigoTipoProfesional(): "
                    + respuesta.getZonaA().getZONAA5().getCodigoTipoProfesional());
            log("respuesta.getZonaA().getZONAA5().getProfRegistroColegio(): "
                    + respuesta.getZonaA().getZONAA5().getProfRegistroColegio());
            log("respuesta.getZonaA().getZONAA6().getDiagnosticoPrincipal(): "
                    + respuesta.getZonaA().getZONAA6().getDiagnosticoPrincipal());
            log("respuesta.getZonaA().getZONAA6().getDiagnosticoOtro(): "
                    + respuesta.getZonaA().getZONAA6().getDiagnosticoOtro());
            log("respuesta.getZonaA().getZONAA6().getAntecedentesClinicos(): "
                    + respuesta.getZonaA().getZONAA6().getAntecedentesClinicos());
            log("respuesta.getZonaA().getZONAA6().getExamenesApoyo(): "
                    + respuesta.getZonaA().getZONAA6().getExamenesApoyo());

            // respuesta.getZonaB().getZONAB1().getResolucionArray()

            // Resolucion[] resolucions = respuesta.getZonaB().getZONAB1().
            // for (int i=0; i < resolucions.length; i++) {
            // resolucions.
            // }

            if (respuesta.getZonaC() != null) {
                log("respuesta.getZonaC().getZONAC1().getEmpRut(): " + respuesta.getZonaC().getZONAC1().getEmpRut());
                log("respuesta.getZonaC().getZONAC1().getEmpNombre(): "
                        + respuesta.getZonaC().getZONAC1().getEmpNombre());
                log("respuesta.getZonaC().getZONAC1().getEmpDireccion().getCalle(): "
                        + respuesta.getZonaC().getZONAC1().getEmpDireccion().getCalle());
                log("respuesta.getZonaC().getZONAC1().getEmpDireccion().getComuna(): "
                        + respuesta.getZonaC().getZONAC1().getEmpDireccion().getComuna());
                log("respuesta.getZonaC().getZONAC1().getEmpTelefono(): "
                        + respuesta.getZonaC().getZONAC1().getEmpTelefono());
                // revisando...
                log("respuesta.getZonaC().getZONAC1().getEmpFechaRecepcion(): "
                        + respuesta.getZonaC().getZONAC1().getEmpFechaRecepcion());
                log("respuesta.getZonaC().getZONAC1().getCodigoOcupacion(): "
                        + respuesta.getZonaC().getZONAC1().getCodigoOcupacion());
                log("respuesta.getZonaC().getZONAC1().getCodigoActividadLaboral(): "
                        + respuesta.getZonaC().getZONAC1().getCodigoActividadLaboral());
                log("respuesta.getZonaC().getZONAC2().getCodigoRegimenPrevisional(): "
                        + respuesta.getZonaC().getZONAC2().getCodigoRegimenPrevisional());
                log("respuesta.getZonaC().getZONAC2().getCodigoLetraCaja(): "
                        + respuesta.getZonaC().getZONAC2().getCodigoLetraCaja());
                log("respuesta.getZonaC().getZONAC2().getCodigoCalidadTrabajador(): "
                        + respuesta.getZonaC().getZONAC2().getCodigoCalidadTrabajador());
                log("respuesta.getZonaC().getZONAC2().getCodigoSeguroAfc(): "
                        + respuesta.getZonaC().getZONAC2().getCodigoSeguroAfc());
                //parche msys
                log("respuesta.getZonaC().getZONAC2().getFechaContrato(): "
                        + respuesta.getZonaC().getZONAC2().getFechaContrato());
                //parche msys
                log("respuesta.getZonaC().getZONAC2().getFechaAfiliacion(): "
                        + respuesta.getZonaC().getZONAC2().getFechaAfiliacion());
                log("respuesta.getZonaC().getZONAC2().getCodigoEntidadPagadora(): "
                        + respuesta.getZonaC().getZONAC2().getCodigoEntidadPagadora());
            }


        } catch (SysException e) {
            log("EL SISTEMA NO ESTA DISPONIBLE, REINTENTE");
            System.out.flush();
            System.err.println("REGISTRO OPERADOR: " + e);
            e.printStackTrace();
        } catch (Throwable t) {
            log("ERROR INESPERADO :(" + t);
            t.printStackTrace();
        }
    }

    *//**
     * 3.4
     *//*
    public static void pruebaLMEDevEmp(int numLicencia) {
        log("--------- pruebaLMEDevEmp");
        try {
            String digLicencia = dv(numLicencia);
            int motivoDevolucion = 1;
            Date fechaDevolucion = new Date();

            RespuestaServicio respuesta = ServiciosOperador.devolucionLicencia(numLicencia, digLicencia,
                    motivoDevolucion, fechaDevolucion);

            log("ESTADO       : " + respuesta.getEstado());
            log("GLOSA ESTADO : " + respuesta.getGloEstado());

        } catch (SysException e) {
            log("EL SISTEMA NO ESTA DISPONIBLE, REINTENTE");
            System.out.flush();
            System.err.println("REGISTRO OPERADOR: " + e);
            e.printStackTrace();
        } catch (Throwable t) {
            log("ERROR INESPERADO :(" + t);
            t.printStackTrace();
        }
    }

    *//**
     * 3.5
     *//*
    public static void pruebaLMEInfValCCAF(int idLicencia) {
        log("--------- pruebaLMEInfValCCAF");
        try {
            LicenciaSimpleType[] listaLicencias = new LicenciaSimpleType[1];
            BigInteger numLicencia = BigInteger.valueOf(idLicencia);
            String dvLicencia = dv(idLicencia);

            listaLicencias[0] = new LicenciaSimpleType(numLicencia, dvLicencia, "T");

            log("listaLicencias[0]: " + listaLicencias[0]);

            ServiciosMultiOperador servicio = new ServiciosMultiOperador("3", "C", "10105", "12345", "http://www.licencia.cl/v2/produccion/pronunciamiento/lmes/service");
            LMEInfValCCAFResponse respuesta = servicio.informaValidacionLicencias(listaLicencias);

            String estado = respuesta.getEstado().intValue() == 0 ? "OK" : "ERROR";
            String procesado = respuesta.getCodResultado().intValue() == 1 ? "PROCESADO TOTAL" : "PROCESADO PARCIAL";

            log("ESTADO: " + estado + " CODRESULTADO: " + procesado);
            ResultadoType[] resultados = respuesta.getListaResultado();

            for (int i = 0; i < resultados.length; i++) {
                ResultadoType resultado = resultados[i];
                log(" -> IdLicencia: " + resultado.getIdLicencia());
                log("    DvLicencia: " + resultado.getDvLicencia());
                log("    CodEstado: " + resultado.getCodEstado());
                log("    GloEstado: " + resultado.getGloEstado());
            }
        } catch (SysException e) {
            log("EL SISTEMA NO ESTA DISPONIBLE, REINTENTE");
            System.err.println("REGISTRO OPERADOR: " + e);
            e.printStackTrace();
        }
    }

    *//**
     * 3.6
     *//*

    public static void pruebaLMEInfLiquid(int idLicencia) {
        log("--------- pruebaLMEInfLiquid");
        try {
            // Datos de negocio
            String digLicencia = dv(idLicencia);
            // liquidacion.
            Date fechaProceso = date("2011-07-22");
            Date fechaDesdeLiquidacion = date("2011-07-21");
            Date fechaHastaLiquidacion = date("2011-08-21");
            Date fechaPagoProbable = date("2011-08-01");
            int montoApagarSubsidio = 300000;
            int montoAportePensiones = 30000;
            int montoAporteSalud = 0;
            int montoSeguroCesantia = 0;
            int montoSubsidioDiario = 10000;
            int ndiasApagarPrevision = 5;
            int ndiasApagarSubsidios = 10;
            Date periodoRenta = date("2011-07-01");

            // remuneracionesArray[0]
            Date anoMesRemAnt = date("2011-07-01");
            int codigoPrevisionRemAnt = 14;
            int montoImponibleRemAnt = 300000;
            int montoIncapacidadRemAnt = 0;
            int montoTotalRemAnt = 300000;
            int ndiasIncapacidadRemAnt = 0;
            int ndiasRemAnt = 20;

            // Crear Zona D
            CTZONAD zonaD = CTZONAD.Factory.newInstance();
            CTZONAD1 ctzonad1 = zonaD.addNewZONAD1();
            CTZONAF ctzonaf = zonaD.addNewZONADF();
            //
            CTZONAD[] zonaDArray = {zonaD};
            
            //
            // validar("zonaD", zonaD);

            beanInspector(CTZONAD.class, "zonaD", 10, null);

            ctzonad1.addNewRemuneraciones();
            set(zonaD, "ZONAD1.remuneracionesArray[0].codigoPrevisionRemAnt", integer(codigoPrevisionRemAnt));
            set(zonaD, "ZONAD1.remuneracionesArray[0].anoMesRemAnt", cal(anoMesRemAnt));
            set(zonaD, "ZONAD1.remuneracionesArray[0].ndiasRemAnt", integer(ndiasRemAnt));
            set(zonaD, "ZONAD1.remuneracionesArray[0].montoImponibleRemAnt", integer(montoImponibleRemAnt));
            set(zonaD, "ZONAD1.remuneracionesArray[0].montoTotalRemAnt", integer(montoTotalRemAnt));
            set(zonaD, "ZONAD1.remuneracionesArray[0].montoIncapacidadRemAnt", integer(montoIncapacidadRemAnt));
            set(zonaD, "ZONAD1.remuneracionesArray[0].ndiasIncapacidadRemAnt", integer(ndiasIncapacidadRemAnt));

            ctzonad1.addNewLiquidacion();
            set(zonaD, "ZONAD1.liquidacion.periodoRenta", cal(periodoRenta));
            set(zonaD, "ZONAD1.liquidacion.montoSubsidioDiario", integer(montoSubsidioDiario));
            set(zonaD, "ZONAD1.liquidacion.montoAportePensiones", integer(montoAportePensiones));
            set(zonaD, "ZONAD1.liquidacion.montoAporteSalud", integer(montoAporteSalud));
            set(zonaD, "ZONAD1.liquidacion.montoSeguroCesantia", integer(montoSeguroCesantia));
            set(zonaD, "ZONAD1.liquidacion.montoApagarSubsidio", integer(montoApagarSubsidio));
            set(zonaD, "ZONAD1.liquidacion.fechaDesdeLiquidacion", cal(fechaDesdeLiquidacion));
            set(zonaD, "ZONAD1.liquidacion.fechaHastaLiquidacion", cal(fechaHastaLiquidacion));
            set(zonaD, "ZONAD1.liquidacion.fechaPagoProbable", cal(fechaPagoProbable));
            set(zonaD, "ZONAD1.liquidacion.ndiasApagarSubsidios", integer(ndiasApagarSubsidios));
            set(zonaD, "ZONAD1.liquidacion.ndiasApagarPrevision", integer(ndiasApagarPrevision));
            
            ctzonaf.addNewFirma();
            set(zonaD, "ZONADF.firmaArray[0].descripcion", "Documento respaldado por antecedentes en papel");

            //log(":::zona D:" + zonaD);

            validar("zonaD", zonaD);
int tipoLiquidacion=1;
            //ServiciosMultiOperador servicio = new ServiciosMultiOperador("5","C","123","123","http://moon.cloud.msys.cl:8080/ServiciosMicrosystemWeb/services/WsLMEInetSOAP"); //IMED
            //ServiciosMultiOperador servicio = new ServiciosMultiOperador("4","C","5","123456","http://desarrollo.medipass.cl/WebApp/ws/ws_fonasa.php");
//            ServiciosMultiOperador servicio = new ServiciosMultiOperador("4","C","10105","inicio","http://ws.fonasa.cl:8080/LME/IMDetLcc");//10105       12345 
            ServiciosMultiOperador servicio = new ServiciosMultiOperador("3","C","10105","petrohue","http://www.licencia.cl/v2/produccion/pronunciamiento/lmes/service"); //IMED
            
            RespuestaServicio respuesta = servicio.liquidacionLicencia(idLicencia, digLicencia, fechaProceso, tipoLiquidacion, zonaDArray);

            log(":::Respuesta LMEInfLiquid::: Estado: " + respuesta.getEstado() + " | Glosa Estado: "
                    + respuesta.getGloEstado());

        } catch (SysException e) {
            log("EL SISTEMA NO ESTA DISPONIBLE, REINTENTE");
            System.err.println("REGISTRO OPERADOR: " + e);
            e.printStackTrace();
        } catch (Throwable e) {
            log("ERROR INESPERADO: " + e);
            e.printStackTrace();
        }

    }

    *//**
     * 3.7
     *//*
    public static void pruebaLMEInfSeccC(int numLicencia) {
        log("--------- pruebaLMEInfSeccC");
        try {
            // Datos de negocio
            String digLicencia = dv(numLicencia);
            Date fechaProceso = new Date();

            // C1
            int codigoActividadLaboral = 1;
            int codigoComunaCompin = 2;
            int codigoOcupacion = 3;

            String empDireccionCalle = "4";
            String empDireccionCiudad = "5";
            String empDireccionComuna = "01101";
            String empDireccionDepto = "6";
            int empDireccionNumero = 7;
            String empDireccionPais = "8";
            Date empFechaRecepcion = date("2009-01-09");
            String empNombre = "10";
            String empOtraOcupacion = "11";
            String empRut = "12";

            int empTelefonoCodigoArea = 13;
            int empTelefonoCodigoPais = 14;
            int empTelefonoTelefono = 15;

            // C2
            int codigoCalidadTrabajador = 16;
            String codigoEntidadPagadora = "H";
            String codigoLetraCaja = "18";
            int codigoRegimenPrevisional = 19;
            int codigoSeguroAfc = 20;
            int codigoSeguroIndef = 21;
            int codigoTipoRegimenPrevisional = 22;
            Date fechaAfiliacion = date("2023-01-23");
            Date fechaContrato = date("2024-01-24");
            Date prevFechaRecepcionCcaf = date("2025-01-25");
            String prevNombre = "26";
            String prevNombrePagador = "27";

            // C3
            int montoImponibleMesAnterior = 28;
            double porcenDesahucio = 29;
            Date anoMesRemAnt = date("2030-01-30");
            int codigoPrevisionRemAnt = 31;
            int montoImponibleRemAnt = 32;
            int montoIncapacidadRemAnt = 33;
            int montoTotalRemAnt = 34;
            int ndiasIncapacidadRemAnt = 35;
            int ndiasRemAnt = 36;

            // C4
            Date lmaFechaDesde = date("2037-03-07");
            Date lmaFechaHasta = date("2038-03-08");
            int lmaNdias = 39;
            int lmaLicenciasAnt = 40;

            // CC
            int codigoTramitacionCCAF = 41;
            int tipoArchivo = 42;
            String urlArchivo = "43";
            Date anoMesRenta = date("2044-04-04");
            int montoHaber = 45;
            String nombreHaber = "46";
            int tieneMas100 = 47;

            beanInspector(CTZONAC.class, "zonaC", 10, null);

            // Crear Zona C
            CTZONAC zonaC = CTZONAC.Factory.newInstance();

            // ZonaC1
            CTZONAC1 ctzonac1 = zonaC.addNewZONAC1();

            set(zonaC, "ZONAC1.codigoActividadLaboral", integer(codigoActividadLaboral));
            set(zonaC, "ZONAC1.codigoComunaCompin", integer(codigoComunaCompin));
            set(zonaC, "ZONAC1.codigoOcupacion", integer(codigoOcupacion));

            ctzonac1.addNewEmpDireccion();
            set(zonaC, "ZONAC1.empDireccion.calle", str(empDireccionCalle));
            set(zonaC, "ZONAC1.empDireccion.ciudad", str(empDireccionCiudad));
            ctzonac1.getEmpDireccion().setComuna(STCodigoComuna.Enum.forString(empDireccionComuna));
            set(zonaC, "ZONAC1.empDireccion.depto", str(empDireccionDepto));
            set(zonaC, "ZONAC1.empDireccion.numero", integer(empDireccionNumero));
            set(zonaC, "ZONAC1.empDireccion.pais", str(empDireccionPais));
            set(zonaC, "ZONAC1.empFechaRecepcion", cal(empFechaRecepcion));
            set(zonaC, "ZONAC1.empNombre", str(empNombre));
            set(zonaC, "ZONAC1.empOtraOcupacion", str(empOtraOcupacion));
            set(zonaC, "ZONAC1.empRut", str(empRut)+"-"+dv(Integer.valueOf(empRut).intValue()));

            ctzonac1.addNewEmpTelefono();
            set(zonaC, "ZONAC1.empTelefono.codigoArea", integer(empTelefonoCodigoArea));
            set(zonaC, "ZONAC1.empTelefono.codigoPais", integer(empTelefonoCodigoPais));
            set(zonaC, "ZONAC1.empTelefono.telefono", integer(empTelefonoTelefono));

            // ZonaC2
            CTZONAC2 ctzonac2 = zonaC.addNewZONAC2();

            set(zonaC, "ZONAC2.codigoCalidadTrabajador", integer(codigoCalidadTrabajador));
            ctzonac2.setCodigoEntidadPagadora(STEntidadPagadora.Enum.forString(codigoEntidadPagadora));
            set(zonaC, "ZONAC2.codigoLetraCaja", str(codigoLetraCaja));
            set(zonaC, "ZONAC2.codigoRegimenPrevisional", integer(codigoRegimenPrevisional));
            set(zonaC, "ZONAC2.codigoSeguroAfc", integer(codigoSeguroAfc));
            set(zonaC, "ZONAC2.codigoSeguroIndef", integer(codigoSeguroIndef));
            set(zonaC, "ZONAC2.codigoTipoRegimenPrevisional", integer(codigoTipoRegimenPrevisional));
            set(zonaC, "ZONAC2.fechaAfiliacion", cal(fechaAfiliacion));
            set(zonaC, "ZONAC2.fechaContrato", cal(fechaContrato));
            set(zonaC, "ZONAC2.prevFechaRecepcionCcaf", cal(prevFechaRecepcionCcaf));
            set(zonaC, "ZONAC2.prevNombre", str(prevNombre));
            set(zonaC, "ZONAC2.prevNombrePagador", str(prevNombrePagador));

            // ZonaC3
            CTZONAC3 ctzonac3 = zonaC.addNewZONAC3();
            set(zonaC, "ZONAC3.montoImponibleMesAnterior", integer(montoImponibleMesAnterior));
            set(zonaC, "ZONAC3.porcenDesahucio", decimal(porcenDesahucio));

            ctzonac3.addNewRemuneracion();
            set(zonaC, "ZONAC3.remuneracionArray[0].anoMesRemAnt", cal(anoMesRemAnt));
            set(zonaC, "ZONAC3.remuneracionArray[0].codigoPrevisionRemAnt", integer(codigoPrevisionRemAnt));
            set(zonaC, "ZONAC3.remuneracionArray[0].montoImponibleRemAnt", integer(montoImponibleRemAnt));
            set(zonaC, "ZONAC3.remuneracionArray[0].montoIncapacidadRemAnt", integer(montoIncapacidadRemAnt));
            set(zonaC, "ZONAC3.remuneracionArray[0].montoTotalRemAnt", integer(montoTotalRemAnt));
            set(zonaC, "ZONAC3.remuneracionArray[0].ndiasIncapacidadRemAnt", integer(ndiasIncapacidadRemAnt));
            set(zonaC, "ZONAC3.remuneracionArray[0].ndiasRemAnt", integer(ndiasRemAnt));

            // ZonaC4
            CTZONAC4 ctzonac4 = zonaC.addNewZONAC4();
            ctzonac4.addNewLicenciaAnterior();
            set(zonaC, "ZONAC4.licenciaAnteriorArray[0].lmaFechaDesde", cal(lmaFechaDesde));
            set(zonaC, "ZONAC4.licenciaAnteriorArray[0].lmaFechaHasta", cal(lmaFechaHasta));
            set(zonaC, "ZONAC4.licenciaAnteriorArray[0].lmaNdias", integer(lmaNdias));
            set(zonaC, "ZONAC4.lmaLicenciasAnt", integer(lmaLicenciasAnt));

            // ZonaCC
            CTZONACC ctzonacc = zonaC.addNewZONACC();
            set(zonaC, "ZONACC.codigoTramitacionCCAF", integer(codigoTramitacionCCAF));
            CTHaberes ctHaberes = ctzonacc.addNewHaberes();

            ctHaberes.addNewArchivo();
            set(zonaC, "ZONACC.haberes.archivoArray[0].tipoArchivo", integer(tipoArchivo));
            set(zonaC, "ZONACC.haberes.archivoArray[0].urlArchivo", str(urlArchivo));
            ctHaberes.addNewDetalle();
            set(zonaC, "ZONACC.haberes.detalleArray[0].anoMesRenta", cal(anoMesRenta));
            set(zonaC, "ZONACC.haberes.detalleArray[0].montoHaber", integer(montoHaber));
            set(zonaC, "ZONACC.haberes.detalleArray[0].nombreHaber", str(nombreHaber));
            set(zonaC, "ZONACC.tieneMas100", integer(tieneMas100));

            // ZONACF
            zonaC.addNewZONACF();

            validar("ZonaC", zonaC);
            
            //log(":::ZonaC:" + zonaC);
                                                                                                      
            //ServiciosMultiOperador servicio = new ServiciosMultiOperador("4", "C", "10105", "10105", "http://desarrollo.medipass.cl/webapp/ws/ws_fonasa.php"); //MEDIPASS - OLD
            ServiciosMultiOperador servicio = new ServiciosMultiOperador("4", "C", "10105", "10105", "http://www.medipass.cl/webapp/ws/ws_fonasa.php"); //MEDIPASS
            RespuestaServicio respuesta = servicio.informaRentaLicencia(numLicencia, digLicencia,
                    fechaProceso, zonaC);
            log(respuesta.getEstado() + " " + respuesta.getGloEstado());
        } catch (SysException e) {
            log("EL SISTEMA NO ESTA DISPONIBLE, REINTENTE");
            System.err.println("REGISTRO OPERADOR: " + e);
            e.printStackTrace();
        } catch (Throwable e) {
            log("ERROR INESPERADO: " + e);
            e.printStackTrace();
        }
    }

    *//**
     * misc.
     *//*
    public static void listarLMEDetLcc(int desde, int hasta) {
        log("--------- listarLMEDetLcc");
//        for (int numLicencia = desde; numLicencia <= hasta; numLicencia++) {
//            String digLicencia = dv(numLicencia);
//            try {
//                RespuestaDetalleLicencia respuesta = ServiciosOperador
//                        .consultaDetalleLicencia(numLicencia, digLicencia);
//
//                CTZONA0 z0 = respuesta.getZona0();
//                CTZONAA za = respuesta.getZonaA();
//                CTZONAB zb = respuesta.getZonaB();
//                CTZONAC zc = respuesta.getZonaC();
//
//                boolean b0 = z0 != null;
//                boolean ba = za != null;
//                boolean bb = zb != null;
//                boolean bc = zc != null;
//
//                String zona0 = b0 ? "Zona0" : "     ";
//                String zonaa = ba ? "ZonaA" : "     ";
//                String zonab = bb ? "ZonaB" : "     ";
//                String zonac = bc ? "ZonaC" : "     ";
//
//                log("LMEDetLcc: " + numLicencia + "-" + digLicencia + ": " + zona0 + " " + zonaa + " " + zonab + " "
//                        + zonac);
//                if (bc) {
//                    log("   REMUNERACIONES " + respuesta.getZonaC().getZONAC3());
//                    log("   HABERES " + respuesta.getZonaC().getZONACC());
//                }
//            } catch (Exception e) {
//                log("LMEDetLcc: " + numLicencia + "-" + digLicencia + ": " + e);
//            }
//            log("");
//        }
    }

    static String leerArchivo(String archivo) {
        try {
            File file = new File(archivo);
            FileInputStream fileInputStream = new FileInputStream(archivo);
            byte[] data = new byte[(int) file.length()];
            fileInputStream.read(data);
            String xmlAsString = new String(data);
            return xmlAsString;
        } catch (Throwable t) {
            return null;
        }
    }

    static LMEDocument pruebaXML(String archivo) {
        try {
            String xmlAsString = leerArchivo(archivo);

            XmlOptions options = new XmlOptions();
            Map substNamespaces = new HashMap();
            substNamespaces.put("http://www.lme.gov.cl/lme", "urn:www:lme:gov:cl:lme");
            substNamespaces.put("", "urn:www:lme:gov:cl:lme");
            options.setLoadSubstituteNamespaces(substNamespaces);

            LMEDocument lmeDocument = LMEDocument.Factory.parse(xmlAsString, options);
            return lmeDocument;
        } catch (Throwable t) {
            log("Error: " + t);
            return null;
        }
    }

    static void pruebaXMLBean(BigInteger numLicencia) {
        try {
            String digLicencia = dv(numLicencia.intValue());
            RespuestaDetalleLicencia respuesta = ServiciosOperador.consultaDetalleLicencia(numLicencia, digLicencia);

            Map map = beanInspector(RespuestaDetalleLicencia.class, "respuesta", 20, null);
            List props = (List) map.get("props");
            for (Iterator it = props.iterator(); it.hasNext();) {
                String next = (String) it.next();
                //String param = next.replace("respuesta.", "");
                String param = next.replaceAll("respuesta.", "");
                param.replaceAll(" ", "");
                param = param.replaceAll("\\[\\]", "[0]");
                String paramShow = param;

                while (paramShow.length() < 80) {
                    paramShow += " ";
                }
                try {
                    Object o = PropertyUtils.getNestedProperty(respuesta, param);
                    log(paramShow + "= " + o);
                } catch (Throwable t) {
                    log(paramShow + "= ERROR: " + t.toString());
                }
            }
        } catch (Throwable t) {
            log("Error: " + t);
            t.printStackTrace();
        }
    }

    static void log(String message) {
        System.out.println(message);
    }

    static Map beanInspector(Class clazz, String prev, int maxLevel, Map map) {
        if (maxLevel < 0) {
            return map;
        }
        if (map == null) {
            map = new HashMap();
            map.put("props", new ArrayList());
            map.put("types", new ArrayList());
        }
        List props = (List) map.get("props");
        List types = (List) map.get("types");

        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(clazz);

            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            for (int i = 0; i < propertyDescriptors.length; i++) {
                String name = propertyDescriptors[i].getName();

                if (name.startsWith("set") || name.equals("id") || name.equals("class")) {
                    continue;
                }

                Class type = propertyDescriptors[i].getPropertyType();
                Method method = propertyDescriptors[i].getReadMethod();
                boolean isArray = method.getReturnType().isArray();
                type = isArray ? method.getReturnType().getComponentType() : type;
                Package pkg = propertyDescriptors[i].getPropertyType().getPackage();
                pkg = isArray ? type.getPackage() : pkg;

                String nombre = prev + "." + name + (isArray ? "[]" : "");
                String tipocl = type.getSimpleName() + (isArray ? "[]" : "");
                props.add(nombre);
                types.add(tipocl);
                while (nombre.length() < 80) {
                    nombre += " ";
                }
                log(nombre + tipocl);
                if (pkg != null && pkg.toString().contains("wwwLmeGovClLme")) {
                    beanInspector(type, prev + "." + name + (isArray ? "[]" : ""), maxLevel - 1, map);
                }
            }
        } catch (IntrospectionException e) {
            e.printStackTrace();
        } finally {
        }
        return map;
    }

    static void validar(String prefijo, XmlObject root) {
        if (root == null) {
            return;
        }

        ArrayList validationErrors = new ArrayList();
        XmlOptions m_validationOptions = new XmlOptions();
        m_validationOptions.setErrorListener(validationErrors);

        log("Validación zona " + prefijo + ": " + root.validate(m_validationOptions));

        for (Iterator it = validationErrors.iterator(); it.hasNext();) {
            XmlValidationError error = (XmlValidationError) it.next();
            Node node = error.getCursorLocation().getDomNode();
            Node parent = node.getParentNode();
            String path = node.getNodeName();
            while (parent != null && parent != node) {
                path = parent.getNodeName() + "/" + path;
                node = parent;
                parent = parent.getParentNode();
            }
            log(prefijo + "\t" + path + "\n\t" + error);
        }

    }

    protected static Calendar cal(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar;
    }

    protected static String str(String string) {
        return string;
    }

    protected static BigInteger integer(int i) {
        return BigInteger.valueOf(i);
    }

    protected static BigDecimal decimal(double d) {
        return BigDecimal.valueOf(d);
    }

    protected static Date date(String formated) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-DD");
        return simpleDateFormat.parse(formated);
    }

    protected static void set(Object bean, String name, Object value) throws IllegalAccessException,
            InvocationTargetException, NoSuchMethodException {
        PropertyUtils.setNestedProperty(bean, name, value);
    }

    public static String dv(int num) {
        int M = 0, S = 1, T = num;
        for (; T != 0; T /= 10)
            S = (S + T % 10 * (9 - M++ % 6)) % 11;
        char r = (char) (S != 0 ? S + 47 : 75);
        return r + "";
    }
}
*/