package conector.lme.ws.cliente.operador;

import java.math.BigInteger;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.xml.rpc.ServiceException;


import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlOptions;
import org.apache.xmlbeans.XmlValidationError;
import org.w3c.dom.Node;

import conector.configuracion.ClasspathConfig;
import conector.configuracion.excepciones.ConfiguracionException;
import conector.lme.ws.cliente.operador.excepciones.ErrorInvocacionOperadorException;

import wwwLmeGovClLme.CTZONA0;
import wwwLmeGovClLme.CTZONAA;
import wwwLmeGovClLme.CTZONAB;
import wwwLmeGovClLme.CTZONAC;
import wwwLmeGovClLme.CTZONAD;
import wwwLmeGovClLme.LMEDocument;

/**
 * Cliente de invocación de servicios a medipass.
 * 
 * @author amartoq@microsystem.cl
 */
public class ServiciosOperador {

    /**
     * Nombre del recurso de configuración.
     */
    protected static final String RECURSO_CONFIG = "/medipass.properties";

    /**
     * Nombre parámetro url servicio.
     */
    protected static final String URL_SERVICIO = "urlServicio";

    /**
     * Nombre parámetro tipo institución.
     */
    protected static final String TIPO_INSTITUCION = "tipoInstitucion";

    /**
     * Nombre parámetro código usuario.
     */
    protected static final String COD_USUARIO = "codUsuario";

    /**
     * Nombre parámetro clave.
     */
    protected static final String CLAVE = "clave";

    /**
     * Nombre parámetro código operador.
     */
    protected static final String CODIGO_OPERADOR = "codigoOperador";

    /**
     * Instancia única de configuración.
     */
    private static ClasspathConfig config;

    /**
     * Obtiene el recurso de configuración para este servicio.
     * 
     * @return el recurso de configuración.
     * @throws ConfiguracionException
     *             si hay algún problema con la configuración.
     */
    protected static ClasspathConfig getConfig() throws ConfiguracionException {
        if (config == null) {
            config = new ClasspathConfig(RECURSO_CONFIG);
        }
        return config;
    }

    /**
     * Crea un objeto proxy para poder invocar el webservice remoto.
     * 
     * @param nombreServicio
     *            nombre del servicio al cual conectarse.
     * @param parametros
     *            parámetros de invocación del servicio.
     * @return el objeto proxy para invocar el webservice.
     * @throws ConfiguracionException
     *             si hay algún problema con la configuración.
     * @throws ErrorInvocacionOperadorException
     *             si ocurre algún problema al conectarse con el web service.
     */
    protected static WsLMEInet_PortType obtenerCliente(String nombreServicio, String parametros)
            throws ConfiguracionException, ErrorInvocacionOperadorException {
        try {
            WsLMEInet_ServiceLocator lmeLocator = new WsLMEInet_ServiceLocator();
            // workaround: deshabilitamos multirefs :/
            lmeLocator.getEngine().setOption(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
            URL urlServicio = getConfig().getURL(URL_SERVICIO);
            WsLMEInet_PortType cliente = lmeLocator.getWsLMEInetSOAP(urlServicio);
            return cliente;
        } catch (ServiceException e) {
            throw new ErrorInvocacionOperadorException(e, nombreServicio, parametros);
        }
    }

    /**
     * Convierte el entero a biginteger.
     * 
     * @return un Biginteger ssi int no es nulo.
     */
    protected static BigInteger biginteger(long l) {
        return BigInteger.valueOf(l);
    }

    /**
     * Invoca el servicio LMEEvenLcc: consulta eventos asociados a LME.
     * 
     * El objetivo de este web service publicado por los operadores y consumidos
     * por las entidades encargadas del pago es informar todas las LME que han
     * cambiado de estado desde la última consulta realizada.
     * 
     * @param year
     *            año de la fecha de consulta.
     * @param month
     *            mes de la fecha de consulta.
     * @param dayOfMonth
     *            día del mes de la fecha de consulta.
     * @return la lista de estados de licencia retornadas por el servicio.
     * @throws ConfiguracionException
     *             si hay algún problema con la configuración.
     * @throws ErrorInvocacionOperadorException
     *             si ocurre algún problema al invocar el servicio.
     */
    public static LicenciaType[] consultaEventosLicencias(int year, int month, int dayOfMonth)
            throws ConfiguracionException, ErrorInvocacionOperadorException {
        String nombreServicio = "LMEEvenLcc";
        String parametros = year + "-" + month + "-" + dayOfMonth;

        try {
            WsLMEInet_PortType cliente = obtenerCliente(nombreServicio, parametros);

            String codigoOperador = getConfig().getString(CODIGO_OPERADOR);
            String tipoInstitucion = getConfig().getString(TIPO_INSTITUCION);
            String codUsuario = getConfig().getString(COD_USUARIO);
            String clave = getConfig().getString(CLAVE);

            Calendar fecConsulta = Calendar.getInstance();
            fecConsulta.clear();
            fecConsulta.set(Calendar.YEAR, year);
            fecConsulta.set(Calendar.MONTH, month - 1);
            fecConsulta.set(Calendar.DAY_OF_MONTH, dayOfMonth);

            log("codigoOperador: " + codigoOperador + " | tipoInstitucion: " + tipoInstitucion + " | codUsuario: "
                    + codUsuario + " | clave: " + clave);

            /*
             * LMEEvenLccResponse respuesta = cliente.LMEEvenLcc(codigoOperador,
             * tipoInstitucion, codUsuario, clave, fecConsulta);
             */

            LMEEvenLcc LMEEvenLcc = new LMEEvenLcc(codigoOperador, tipoInstitucion, codUsuario, clave, fecConsulta);
            LMEEvenLccResponse respuesta = cliente.LMEEvenLcc(LMEEvenLcc);

            if (respuesta == null) {
                throw new ErrorInvocacionOperadorException(nombreServicio, parametros, 9999,
                        "Servicio no entrega respuesta.");
            }
            ;

            log("respuesta: " + respuesta.getEstado() + " glosa:" + respuesta.getGloEstado());
            log("");

            /*
             * jcm java.math.BigInteger cero = BigInteger.valueOf(0); * if
             * (respuesta.getEstado() != cero ) { throw new
             * ErrorInvocacionMedipassException(nombreServicio, parametros,
             * respuesta.getEstado(), respuesta.getGloEstado()); }
             */

            LicenciaType[] listaLicencias = respuesta.getListaLicencias();

            return listaLicencias;
        } /*catch (SocketTimeoutException ex) {
        	System.out.println("retorna al timeout");
            throw new ErrorInvocacionOperadorException(ex, nombreServicio, parametros);
        }*/catch (RemoteException e) {
            throw new ErrorInvocacionOperadorException(e, nombreServicio, parametros);
        }
    }

    /**
     * Invoca el servicio LMEDetLcc: consulta detalle de una licencia a
     * pronunciar o validar.
     * 
     * El objetivo de este web service publicado por los operadores y consumido
     * por las entidades encargadas del pago y fiscalizadoras a partir de la
     * lista de las licencias obtenidas desde la consulta de cambios de estado
     * obtener para cada licencia el detalle de la misma, la información que se
     * proporciona depende del estado desde donde se realiza la consulta esta
     * consulta se puede ejecutar por la entidad encargada del pago antes de la
     * tramitación por parte del empleador por lo tanto solo se obtendrá la
     * información de la Zona A o ejecutarla también después de la tramitación
     * por parte del empleador en cuyo caso además de la zona A es posible
     * obtener los datos asociados a la Zona C.
     * 
     * @param numLicencia
     *            número de licencia.
     * @param dvLicencia
     *            dígito de la licencia.
     * @return el detalle de la licencia.
     * @throws ConfiguracionException
     *             si hay algún problema con la configuración.
     * @throws ErrorInvocacionOperadorException
     *             si ocurre algún problema al invocar el servicio.
     */
    public static RespuestaDetalleLicencia consultaDetalleLicencia(BigInteger numLicencia, String digLicencia)
            throws ConfiguracionException, ErrorInvocacionOperadorException {
        String nombreServicio = "LMEDetLcc";
        String parametros = numLicencia + "-" + digLicencia;
        try {
            WsLMEInet_PortType cliente = obtenerCliente(nombreServicio, parametros);

            String codigoOperador = getConfig().getString(CODIGO_OPERADOR).trim();
            String tipoInstitucion = getConfig().getString(TIPO_INSTITUCION);
            String codUsuario = getConfig().getString(COD_USUARIO);
            String clave = getConfig().getString(CLAVE);

            LMEDetLcc LMEDetLcc = new LMEDetLcc(codigoOperador, tipoInstitucion, codUsuario, clave,
                    //biginteger(numLicencia), digLicencia);
                    numLicencia, digLicencia);
            LMEDetLccResponse respuesta = cliente.LMEDetLcc(LMEDetLcc);

            if (respuesta == null || respuesta.getEstado() == null) {
                throw new ErrorInvocacionOperadorException(nombreServicio, parametros, 9999,
                        "Servicio no entrega respuesta.");
            }

            if (respuesta.getEstado().intValue() != 0) {
                throw new ErrorInvocacionOperadorException(nombreServicio, parametros,
                        respuesta.getEstado().intValue(), respuesta.getGloEstado());
            }

            String xmlAsString = new String(respuesta.getDctoLme());
            System.out.println("LME XML (MEDIPASS): " + xmlAsString);
            XmlOptions options = new XmlOptions();
            Map substNamespaces = new HashMap();
            substNamespaces.put("http://www.lme.gov.cl/lme", "urn:www:lme:gov:cl:lme");
            substNamespaces.put("", "urn:www:lme:gov:cl:lme");
            options.setLoadSubstituteNamespaces(substNamespaces);
            System.out.println("");
            
            //Parche: modificar namespace malo de Medipass
            //if ( codigoOperador.equals( "4") ){
            //    xmlAsString = xmlAsString.replace("<LME xmlns:ns2='urn:www:lme:gov:cl:lme' xmlns='http://www.w3.org/2000/09/xmldsig#'>", "<LME xmlns='urn:www:lme:gov:cl:lme' xmlns:medipassvaleyuyo='http://www.w3.org/2000/09/xmldsig#'>");
            //}
            
            //Parche: modificar fecha formato erroneo IMED
            //if ( codigoOperador.equals( "3") ){
            //    xmlAsString = xmlAsString.replace("CLT", "T");
            //}
            
            LMEDocument lmeDocument = LMEDocument.Factory.parse(xmlAsString, options);


            CTZONA0 zona0 = lmeDocument.getLME().getZONA0();
            CTZONAA zonaA = lmeDocument.getLME().getZONAA();
            CTZONAB zonaB = lmeDocument.getLME().getZONAB();
            CTZONAC zonaC = lmeDocument.getLME().getZONAC();
            CTZONAD[] zonaD = lmeDocument.getLME().getZONADArray();

            RespuestaDetalleLicencia respuestaDetalleLicencia = new RespuestaDetalleLicencia(numLicencia, digLicencia,
                    zona0, zonaA, zonaB, zonaC, zonaD, xmlAsString);

            return respuestaDetalleLicencia;
        } catch (RemoteException e) {
            throw new ErrorInvocacionOperadorException(e, nombreServicio, parametros);
        } catch (XmlException e) {
            throw new ErrorInvocacionOperadorException(e, nombreServicio, parametros);
        }
    }

    /**
     * Invoca el servicio LMEDevEmp: devolución licencia médica electrónica.
     * 
     * El siguiente web service publicado por el operador y consumido por la
     * entidad encargada del pago, tiene como informar la devolución de una LME
     * al empleador por falta de antecedentes.
     * 
     * @param idLicencia
     *            número de licencia.
     * @param dvLicencia
     *            dígito de la licencia.
     * @param motivoDevolucion
     *            mótivo devolución al empleador, número del 1 al 10 según
     *            códigos establecidos.
     * @param fechaDevolucion
     *            fecha y hora desde la devolución.
     * @throws ConfiguracionException
     *             si hay algún problema con la configuración.
     * @throws ErrorInvocacionOperadorException
     *             si ocurre algún problema al invocar el servicio.
     */
    public static RespuestaServicio devolucionLicencia(int idLicencia, String dvLicencia, int motivoDevolucion,
            Date fechaDevolucion) throws ConfiguracionException, ErrorInvocacionOperadorException {
        String nombreServicio = "LMEDevEmp";
        String parametros = "idLicencia: " + idLicencia + " digLicencia: " + dvLicencia + " motivoDevolucion: "
                + motivoDevolucion + " fechaDevolucion: " + fechaDevolucion;
        try {
            WsLMEInet_PortType cliente = obtenerCliente(nombreServicio, parametros);

            String codigoOperador = getConfig().getString(CODIGO_OPERADOR);
            String tipoInstitucion = getConfig().getString(TIPO_INSTITUCION);
            String codUsuario = getConfig().getString(COD_USUARIO);
            String clave = getConfig().getString(CLAVE);

            Calendar fecDevolucion = Calendar.getInstance();
            fecDevolucion.setTime(fechaDevolucion);

            LMEDevEmp LMEDevEmp = new LMEDevEmp(codigoOperador, tipoInstitucion, codUsuario, clave,
                    biginteger(idLicencia), dvLicencia, biginteger(motivoDevolucion), fecDevolucion);
            LMEDevEmpResponse respuesta = cliente.LMEDevEmp(LMEDevEmp);

            if (respuesta == null || respuesta.getEstado() == null) {
                throw new ErrorInvocacionOperadorException(nombreServicio, parametros, 9999,
                        "Servicio no entrega respuesta.");
            }

            return new RespuestaServicio(respuesta.getEstado().intValue(), respuesta.getGloEstado());
        } catch (RemoteException e) {
            throw new ErrorInvocacionOperadorException(e, nombreServicio, parametros);
        } finally {

        }
    }

    /**
     * Invoca el servicio LMEInfValCCAF: validación licencia médica electrónica.
     * 
     * El siguiente web service publicado por el operador y consumido por las
     * CCAF tiene como objetivo informar el cumplimiento del evento de
     * validación de las licencias.
     * 
     * @param listaLicencias
     *            lista de licencias a informar validación.
     * @return resultado de validación para cada licencia.
     * @throws ConfiguracionException
     *             si hay algún problema con la configuración.
     * @throws ErrorInvocacionOperadorException
     *             si ocurre algún problema al invocar el servicio.
     */
    public static LMEInfValCCAFResponse informaValidacionLicencias(LicenciaSimpleType[] listaLicencias)
            throws ConfiguracionException, ErrorInvocacionOperadorException {
        String nombreServicio = "LMEInfValCCAF";
        String parametros = listaLicencias.length + " licencias:";
        for (int i = 0; i < listaLicencias.length; i++) {
            LicenciaSimpleType licencia = listaLicencias[i];
            parametros += " [" + licencia.getIdLicencia() + "-" + licencia.getDvLicencia() + ": "
                    + licencia.getEstadoVal() + "]";
        }
        try {
            WsLMEInet_PortType cliente = obtenerCliente(nombreServicio, parametros);

            String codigoOperador = getConfig().getString(CODIGO_OPERADOR);
            String tipoInstitucion = getConfig().getString(TIPO_INSTITUCION);
            String codUsuario = getConfig().getString(COD_USUARIO);
            String clave = getConfig().getString(CLAVE);

            LMEInfValCCAF LMEInfValCCAF = new LMEInfValCCAF(codigoOperador, tipoInstitucion, codUsuario, clave,
                    listaLicencias);
            LMEInfValCCAFResponse respuesta = cliente.LMEInfValCCAF(LMEInfValCCAF);
            if (respuesta == null || respuesta.getEstado() == null) {
                throw new ErrorInvocacionOperadorException(nombreServicio, parametros, 9999,
                        "Servicio no entrega respuesta.");
            }

            return respuesta;
        } catch (RemoteException e) {
            throw new ErrorInvocacionOperadorException(e, nombreServicio, parametros);
        }
    }

    /**
     * Invoca el servicio LMEInfLiquid: liquidación licencia médica electrónica.
     * 
     * El siguiente web service publicado por el operador y consumido por la
     * entidad encargada del pago, tiene como objetivo informar las
     * liquidaciones que son generadas para una LME.
     * 
     * @param idLicencia
     *            número de licencia.
     * @param digLicencia
     *            dígito de la licencia.
     * @param fechaProceso
     *            fecha y hora informe de liquidaciones.
     * @param zonaD
     *            zona D.
     * @throws ConfiguracionException
     *             si hay algún problema con la configuración.
     * @throws ErrorInvocacionOperadorException
     *             si ocurre algún problema al invocar el servicio.
     */

    public static RespuestaServicio liquidacionLicencia(int idLicencia, String dvLicencia, Date fechaProceso, int tipoLiquidacion,
            CTZONAD[] zonaD) throws ConfiguracionException, ErrorInvocacionOperadorException {
        String nombreServicio = "LMEInfLiquid";
        String parametros = "idLicencia: " + idLicencia + " dvLicencia: " + dvLicencia + " fechaProceso: "
                + fechaProceso + " zonaD: " + zonaD;
        try {
            WsLMEInet_PortType cliente = obtenerCliente(nombreServicio, parametros);

            String codigoOperador = getConfig().getString(CODIGO_OPERADOR);
            String tipoInstitucion = getConfig().getString(TIPO_INSTITUCION);
            String codUsuario = getConfig().getString(COD_USUARIO);
            String clave = getConfig().getString(CLAVE);

            Calendar fecProceso = Calendar.getInstance();
            fecProceso.setTime(fechaProceso);

            LMEDocument.LME lme = LMEDocument.LME.Factory.newInstance();
            lme.setZONADArray(zonaD);
            String xmlZonaD = lme.toString();
            validar("LMEInfLiquid ZonaD", zonaD);
            //log("LMEInfLiquid ZonaD XML:" + xmlZonaD);
            
            byte[] datosLiquidacion = xmlZonaD.getBytes();

            LMEInfLiquid LMEInfLiquid = new LMEInfLiquid(codigoOperador, tipoInstitucion, codUsuario, clave,
                    biginteger(idLicencia), dvLicencia, fecProceso, biginteger(tipoLiquidacion), datosLiquidacion);
            LMEInfLiquidResponse respuesta = cliente.LMEInfLiquid(LMEInfLiquid);
            if (respuesta == null || respuesta.getEstado() == null) {
                throw new ErrorInvocacionOperadorException(nombreServicio, parametros, 9999,
                        "Servicio no entrega respuesta.");
            }

            return new RespuestaServicio(respuesta.getEstado().intValue(), respuesta.getGloEstado());
        } catch (RemoteException e) {
            throw new ErrorInvocacionOperadorException(e, nombreServicio, parametros);
        }
    }

    /**
     * Invoca el servicio LMEInfSeccC: informar datos zona C licencia médica
     * semi-electrónica.
     * 
     * El objetivo de este web service publicado por los operadores y consumidos
     * por las entidades encargadas del pago es permitir que estas últimas
     * puedan completar la información de las rentas de las licencias médicas
     * electrónicas tramitadas en la modalidad copia impresa dado que el
     * empleador no estaba adscrito.
     * 
     * @param idLicencia
     *            número de licencia.
     * @param digLicencia
     *            dígito de la licencia.
     * @param fechaProceso
     *            fecha y hora información.
     * @param zonaC
     *            zona C.
     * @throws ConfiguracionException
     *             si hay algún problema con la configuración.
     * @throws ErrorInvocacionOperadorException
     *             si ocurre algún problema al invocar el servicio.
     */

    public static RespuestaServicio informaRentaLicencia(int idLicencia, String dvLicencia, Date fechaProceso,
            CTZONAC zonaC) throws ConfiguracionException, ErrorInvocacionOperadorException {
        String nombreServicio = "LMEInfSeccC";
        String parametros = "idLicencia: " + idLicencia + " dvLicencia: " + dvLicencia + " fechaProceso: "
                + fechaProceso + " zonaC: " + zonaC;
        try {
            WsLMEInet_PortType cliente = obtenerCliente(nombreServicio, parametros);

            String codigoOperador = getConfig().getString(CODIGO_OPERADOR);
            String tipoInstitucion = getConfig().getString(TIPO_INSTITUCION);
            String codUsuario = getConfig().getString(COD_USUARIO);
            String clave = getConfig().getString(CLAVE);

            Calendar fecProceso = Calendar.getInstance();
            fecProceso.setTime(fechaProceso);

            LMEDocument.LME lme = LMEDocument.LME.Factory.newInstance();
            lme.setZONAC(zonaC);
            String xmlZonaC = lme.toString();
            
            validar("LMEInfLiquid ZonaC:", zonaC);
            log(":::LMEInfLiquid ZonaC XML:" + xmlZonaC);
            
            xmlZonaC = xmlZonaC + "<ZONA_C>";
            xmlZonaC = xmlZonaC + "<ZONA_C1>";
            xmlZonaC = xmlZonaC + "    <emp_nombre>COMUNIDAD EDIFICIO HUERFANOS                      </emp_nombre>";
            xmlZonaC = xmlZonaC + "    <emp_rut>50002100-4</emp_rut>";
            xmlZonaC = xmlZonaC + "    <emp_telefono>";
            xmlZonaC = xmlZonaC + "        <telefono>324325</telefono>";
            xmlZonaC = xmlZonaC + "    </emp_telefono>";
            xmlZonaC = xmlZonaC + "    <emp_fecha_recepcion>2011-07-15T00:00:00</emp_fecha_recepcion>";
            xmlZonaC = xmlZonaC + "    <emp_direccion>";
            xmlZonaC = xmlZonaC + "        <calle>ARICA 666</calle>";
            xmlZonaC = xmlZonaC + "        <comuna>15102</comuna>";
            xmlZonaC = xmlZonaC + "        <ciudad></ciudad>";
            xmlZonaC = xmlZonaC + "        <pais>chile</pais>";
            xmlZonaC = xmlZonaC + "    </emp_direccion>";
            xmlZonaC = xmlZonaC + "    <codigo_comuna_compin>15102</codigo_comuna_compin>";
            xmlZonaC = xmlZonaC + "    <codigo_actividad_laboral>1</codigo_actividad_laboral>";
            xmlZonaC = xmlZonaC + "    <codigo_ocupacion>11</codigo_ocupacion>";
            xmlZonaC = xmlZonaC + "</ZONA_C1>";
            xmlZonaC = xmlZonaC + "<ZONA_C2>";
            xmlZonaC = xmlZonaC + "    <codigo_tipo_regimen_previsional>1</codigo_tipo_regimen_previsional>";
            xmlZonaC = xmlZonaC + "    <codigo_regimen_previsional>-1</codigo_regimen_previsional>";
            xmlZonaC = xmlZonaC + "    <prev_nombre>CCAF 18 SEPTIEMBRE</prev_nombre>";
            xmlZonaC = xmlZonaC + "    <codigo_calidad_trabajador>1</codigo_calidad_trabajador>";
            xmlZonaC = xmlZonaC + "    <codigo_seguro_afc>1</codigo_seguro_afc>";
            xmlZonaC = xmlZonaC + "    <codigo_seguro_indef>1</codigo_seguro_indef>";
            xmlZonaC = xmlZonaC + "    <fecha_afiliacion>2011-06-15</fecha_afiliacion>";
            xmlZonaC = xmlZonaC + "    <fecha_contrato>2011-07-14</fecha_contrato>";
            xmlZonaC = xmlZonaC + "    <codigo_entidad_pagadora>C</codigo_entidad_pagadora>";
            xmlZonaC = xmlZonaC + "    <prev_nombre_pagador>CCAF 18 SEPTIEMBRE</prev_nombre_pagador>";
            xmlZonaC = xmlZonaC + "</ZONA_C2>";
            xmlZonaC = xmlZonaC + "<ZONA_C3>";
            xmlZonaC = xmlZonaC + "    <remuneracion>";
            xmlZonaC = xmlZonaC + "        <codigo_prevision_rem_ant>1</codigo_prevision_rem_ant>";
            xmlZonaC = xmlZonaC + "        <ano_mes_rem_ant>2011-01</ano_mes_rem_ant>";
            xmlZonaC = xmlZonaC + "        <ndias_rem_ant>23</ndias_rem_ant>";
            xmlZonaC = xmlZonaC + "        <monto_imponible_rem_ant>23000</monto_imponible_rem_ant>";
            xmlZonaC = xmlZonaC + "        <monto_total_rem_ant>24000</monto_total_rem_ant>";
            xmlZonaC = xmlZonaC + "        <monto_incapacidad_rem_ant>22000</monto_incapacidad_rem_ant>";
            xmlZonaC = xmlZonaC + "        <ndias_incapacidad_rem_ant>23</ndias_incapacidad_rem_ant>";
            xmlZonaC = xmlZonaC + "    </remuneracion>";
            xmlZonaC = xmlZonaC + "    <remuneracion>";
            xmlZonaC = xmlZonaC + "        <codigo_prevision_rem_ant>2</codigo_prevision_rem_ant>";
            xmlZonaC = xmlZonaC + "        <ano_mes_rem_ant>2011-02</ano_mes_rem_ant>";
            xmlZonaC = xmlZonaC + "        <ndias_rem_ant>22</ndias_rem_ant>";
            xmlZonaC = xmlZonaC + "        <monto_imponible_rem_ant>34000</monto_imponible_rem_ant>";
            xmlZonaC = xmlZonaC + "        <monto_total_rem_ant>32000</monto_total_rem_ant>";
            xmlZonaC = xmlZonaC + "        <monto_incapacidad_rem_ant>12000</monto_incapacidad_rem_ant>";
            xmlZonaC = xmlZonaC + "        <ndias_incapacidad_rem_ant>24</ndias_incapacidad_rem_ant>";
            xmlZonaC = xmlZonaC + "    </remuneracion>";
            xmlZonaC = xmlZonaC + "        <remuneracion><codigo_prevision_rem_ant>3</codigo_prevision_rem_ant>";
            xmlZonaC = xmlZonaC + "        <ano_mes_rem_ant>2011-03</ano_mes_rem_ant>";
            xmlZonaC = xmlZonaC + "        <ndias_rem_ant>24</ndias_rem_ant>";
            xmlZonaC = xmlZonaC + "        <monto_imponible_rem_ant>35000</monto_imponible_rem_ant>";
            xmlZonaC = xmlZonaC + "        <monto_total_rem_ant>45000</monto_total_rem_ant>";
            xmlZonaC = xmlZonaC + "        <monto_incapacidad_rem_ant>13000</monto_incapacidad_rem_ant>";
            xmlZonaC = xmlZonaC + "        <ndias_incapacidad_rem_ant>25</ndias_incapacidad_rem_ant>";
            xmlZonaC = xmlZonaC + "    </remuneracion>";
            xmlZonaC = xmlZonaC + "    <remuneracion>";
            xmlZonaC = xmlZonaC + "        <codigo_prevision_rem_ant>4</codigo_prevision_rem_ant>";
            xmlZonaC = xmlZonaC + "        <ano_mes_rem_ant>2011-04</ano_mes_rem_ant>";
            xmlZonaC = xmlZonaC + "        <ndias_rem_ant>26</ndias_rem_ant>";
            xmlZonaC = xmlZonaC + "        <monto_imponible_rem_ant>34000</monto_imponible_rem_ant>";
            xmlZonaC = xmlZonaC + "        <monto_total_rem_ant>56000</monto_total_rem_ant>";
            xmlZonaC = xmlZonaC + "        <monto_incapacidad_rem_ant>45000</monto_incapacidad_rem_ant>";
            xmlZonaC = xmlZonaC + "        <ndias_incapacidad_rem_ant>28</ndias_incapacidad_rem_ant>";
            xmlZonaC = xmlZonaC + "    </remuneracion>";
            xmlZonaC = xmlZonaC + "    <porcen_desahucio>20</porcen_desahucio>";
            xmlZonaC = xmlZonaC + "    <monto_imponible_mes_anterior>600000</monto_imponible_mes_anterior>";
            xmlZonaC = xmlZonaC + "</ZONA_C3>";
            xmlZonaC = xmlZonaC + "<ZONA_C4>";
            xmlZonaC = xmlZonaC + "    <lma_licencias_ant>1</lma_licencias_ant>";
            xmlZonaC = xmlZonaC + "    <licencia_anterior>";
            xmlZonaC = xmlZonaC + "        <lma_ndias>15</lma_ndias>";
            xmlZonaC = xmlZonaC + "        <lma_fecha_desde>2011-07-01</lma_fecha_desde>";
            xmlZonaC = xmlZonaC + "        <lma_fecha_hasta>2011-07-15</lma_fecha_hasta>";
            xmlZonaC = xmlZonaC + "    </licencia_anterior>";
            xmlZonaC = xmlZonaC + "</ZONA_C4>";
            xmlZonaC = xmlZonaC + "<ZONA_CC>";
            xmlZonaC = xmlZonaC + "    <codigo_tramitacion_CCAF>10101</codigo_tramitacion_CCAF>";
            xmlZonaC = xmlZonaC + "    <haberes />";
            xmlZonaC = xmlZonaC + "</ZONA_CC>";
            xmlZonaC = xmlZonaC + "<ZONA_CF/>";
            xmlZonaC = xmlZonaC + "</ZONA_C>";
            
            byte[] datosZonaC = xmlZonaC.getBytes();

            LMEInfSeccC LMEInfSeccC = new LMEInfSeccC(codigoOperador, tipoInstitucion, codUsuario, clave,
                    biginteger(idLicencia), dvLicencia, fecProceso, datosZonaC);
            LMEInfSeccCResponse respuesta = cliente.LMEInfSeccC(LMEInfSeccC);

            if (respuesta == null || respuesta.getEstado() == null) {
                throw new ErrorInvocacionOperadorException(nombreServicio, parametros, 9999,
                        "Servicio no entrega respuesta.");
            }

            return new RespuestaServicio(respuesta.getEstado().intValue(), respuesta.getGloEstado());
        } catch (RemoteException e) {
            throw new ErrorInvocacionOperadorException(e, nombreServicio, parametros);
        }
    }

    static void validar(String prefijo, XmlObject root) {
        if (root == null) {
            return;
        }

        ArrayList validationErrors = new ArrayList();
        XmlOptions m_validationOptions = new XmlOptions();
        m_validationOptions.setErrorListener(validationErrors);

        log(prefijo + " Validación: " + root.validate(m_validationOptions));

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
    
    static void validar(String prefijo, XmlObject[] root) {
        if (root == null) {
            return;
        }

        for (int i = 0; i < root.length; i++) {
            validar( prefijo, root[i]);
        }
    }

    static void log(String message) {
        System.out.println(message);
    }
}
