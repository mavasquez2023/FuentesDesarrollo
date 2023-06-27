package conector.lme.ws.cliente.operador;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TimeZone;

import javax.xml.rpc.ServiceException;

import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlOptions;
import org.apache.xmlbeans.XmlValidationError;
import org.w3c.dom.Node;
import wwwLmeGovClLme.CTZONA0;
import wwwLmeGovClLme.CTZONAA;
import wwwLmeGovClLme.CTZONAB;
import wwwLmeGovClLme.CTZONAC;
import wwwLmeGovClLme.CTZONAD;
import wwwLmeGovClLme.LMEDocument;
import conector.configuracion.ClasspathConfig;
import conector.configuracion.excepciones.ConfiguracionException;
import conector.lme.ws.cliente.operador.excepciones.ErrorInvocacionOperadorException;
import conector.lme.ws.cliente.operador.excepciones.ErrorRespuestaOperadorException;
import conector.utils.ConstantesTimeOut;
import conector.vo.SalidaLMEDetLcc;
import conector.vo.SalidaLMEDevEmp;
import conector.vo.SalidaLMEEvenLcc;
import conector.vo.SalidaLMEInfLiquid;
import conector.vo.SalidaLMEInfSeccC;
import conector.vo.SalidaLMEInfValCCAF;

/**
 * Cliente Multioperador de invocación de servicios a medipass.
 */
public class ServiciosMultiOperador {

    /**
     * Nombre del recurso de configuración.
     */
    protected String RECURSO_CONFIG = "/medipass.properties";

    /**
     * Nombre parámetro url servicio.
     */
    protected String URL_SERVICIO = "urlServicio";
    protected String URL_SERVICIO2 = "urlServicio2";

    /**
     * Nombre parámetro tipo institución.
     */
    protected String TIPO_INSTITUCION = "tipoInstitucion";

    /**
     * Nombre parámetro código usuario.
     */
    protected String COD_USUARIO = "codUsuario";

    /**
     * Nombre parámetro clave.
     */
    protected String CLAVE = "clave";

    /**
     * Nombre parámetro código operador.
     */
    protected String CODIGO_OPERADOR = "codigoOperador";
    
    /**
     * Nombre parámetro tipo liquidación.
     */
    protected String TIPO_LIQUIDACION = "tipoLiquidacion";

    /**
     * Instancia única de configuración.
     */
    protected ClasspathConfig config;

    
    public ServiciosMultiOperador(){
    }

    public ServiciosMultiOperador(String recurso_config){
        RECURSO_CONFIG = recurso_config;
    }

    /**
     * Constructor de ServiciosMultiOperador, donde se agrego la segunda url para la prioridad 2
     * @param codigoOperador
     * @param tipoInstitucion
     * @param codUsuario
     * @param clave
     * @param urlServicio
     * @param urlServicio2
     */
    public ServiciosMultiOperador(String codigoOperador, String tipoInstitucion, String codUsuario, String clave, String urlServicio, String urlServicio2){
        config = new ClasspathConfig(new String[]{
                "codigoOperador", codigoOperador,
                "tipoInstitucion", tipoInstitucion,
                "codUsuario", codUsuario,
                "clave", clave,
                "urlServicio", urlServicio,
                "urlServicio2", urlServicio2
                });
    }
    
    /**
     * Obtiene el recurso de configuración para este servicio.
     * 
     * @return el recurso de configuración.
     * @throws ConfiguracionException
     *             si hay algún problema con la configuración.
     */
    protected ClasspathConfig getConfig() throws ConfiguracionException {
        if (config == null) {
            config = new ClasspathConfig(RECURSO_CONFIG);
        }
        return config;
    }
    

    /**
     * Crea un objeto proxy para poder invocar el webservice remoto asignando tambien el timeout del servicio y la url por parametros.
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
    protected WsLMEInet_PortType obtenerCliente(String nombreServicio, String parametros, int timeOut, String url)
            throws ConfiguracionException, ErrorInvocacionOperadorException {
        try {
            WsLMEInet_ServiceLocator lmeLocator = new WsLMEInet_ServiceLocator();
            // workaround: deshabilitamos multirefs :/
            lmeLocator.getEngine().setOption(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
            //URL urlServicio = getConfig().getURL(URL_SERVICIO);
            URL urlServicio = null;
			try {
				urlServicio = new URL(url);
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
            WsLMEInet_PortType cliente = lmeLocator.getWsLMEInetSOAP(urlServicio);
            WsLMEInetSOAPStub stub = (WsLMEInetSOAPStub) cliente;
            stub.setTimeout(timeOut);
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
    protected BigInteger biginteger(long l) {
        return BigInteger.valueOf(l);
    }
    
    
    /**
     * invoca el servicio consultaEventosLicencias realizando el manejo de timeout
     * @param year
     * @param month
     * @param dayOfMonth
     * @param hour
     * @param minute
     * @return
     * @throws ConfiguracionException
     * @throws ErrorInvocacionOperadorException
     */
    public SalidaLMEEvenLcc consultaEventosLicencias2(int year, int month, int dayOfMonth, int hour,int minute)
            throws ConfiguracionException, ErrorInvocacionOperadorException {
    	SalidaLMEEvenLcc salida = new SalidaLMEEvenLcc();
    	
    	String urlServicio = getConfig().getString(URL_SERVICIO);
        String urlServicio2 = getConfig().getString(URL_SERVICIO2);
        long inicio = 0;
        long termino = 0;
        
        if(urlServicio != null && urlServicio.length()>0){
        	try {
        		inicio = System.currentTimeMillis();
        		LicenciaType[] licencias = consultaEventosLicencias(year, month, dayOfMonth, hour, minute, urlServicio);
        		termino = System.currentTimeMillis();
        		salida.setTiempo1(termino - inicio);
        		salida.setListaLicencias(licencias);
        	} catch (java.net.SocketTimeoutException e) {
        		//System.out.println("timeout en la primera ===");
				salida.setError1(true);
				if(urlServicio2 != null && urlServicio2.length()>0){
	        		try {
	        			inicio = System.currentTimeMillis();
	            		LicenciaType[] licencias = consultaEventosLicencias(year, month, dayOfMonth, hour, minute, urlServicio2);
	            		termino = System.currentTimeMillis();
	            		salida.setTiempo2(termino - inicio);
	            		salida.setListaLicencias(licencias);
	            	} catch (java.net.SocketTimeoutException ex) {
	            		//System.out.println("time out en la segunda === ");
	    				salida.setError2(true);
	    			}
	        	}else{
	        		salida.setError2(true);
	        	}
			}
        }else{
        	salida.setError1(true);
        	if(urlServicio2 != null && urlServicio2.length()>0){
        		try {
        			inicio = System.currentTimeMillis();
            		LicenciaType[] licencias = consultaEventosLicencias(year, month, dayOfMonth, hour, minute, urlServicio2);
            		termino = System.currentTimeMillis();
            		salida.setTiempo2(termino - inicio);
            		salida.setListaLicencias(licencias);
            	} catch (java.net.SocketTimeoutException e) {
            		//System.out.println("tme out en la segunda ===");
    				salida.setError2(true);
    			}
        	}else{
        		salida.setError2(true);
        	}
        }
    	return salida;
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
     * @throws SocketTimeoutException 
     */
    private LicenciaType[] consultaEventosLicencias(int year, int month, int dayOfMonth, int hour,int minute, String url)
            throws ConfiguracionException, ErrorInvocacionOperadorException, SocketTimeoutException {
        String nombreServicio = "LMEEvenLcc";
        //String nombre = "CONSULTA";
        
        String parametros = year + "-" + month + "-" + dayOfMonth;

        try {
        	String codigoOperador = getConfig().getString(CODIGO_OPERADOR);
            String tipoInstitucion = getConfig().getString(TIPO_INSTITUCION);
            String codUsuario = getConfig().getString(COD_USUARIO);
            String clave = getConfig().getString(CLAVE);
            
        	int timeOut = Integer.parseInt(ConstantesTimeOut.getInstancia().CONSULTA);
        	
        	WsLMEInet_PortType cliente = obtenerCliente(nombreServicio, parametros, timeOut, url);

            Calendar fecConsulta = Calendar.getInstance();
            fecConsulta.clear();
            fecConsulta.set(Calendar.YEAR, year);
            fecConsulta.set(Calendar.MONTH, month - 1);
            fecConsulta.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            fecConsulta.set(Calendar.HOUR_OF_DAY, hour);
            fecConsulta.set(Calendar.MINUTE, minute);
            
            log("Servicio: LMEEvenLcc,  URL: " +  url);
            log("HORA CALENDAR FINAL: " + fecConsulta.get(Calendar.YEAR) + "/" + (fecConsulta.get(Calendar.MONTH) + 1) + "/" + fecConsulta.get(Calendar.DAY_OF_MONTH) + " - " + fecConsulta.get(Calendar.HOUR_OF_DAY) + ":" + fecConsulta.get(Calendar.MINUTE));
            
            LMEEvenLcc LMEEvenLcc = new LMEEvenLcc(codigoOperador, tipoInstitucion, codUsuario, clave, fecConsulta);
            
            LMEEvenLccResponse respuesta = cliente.LMEEvenLcc(LMEEvenLcc);

            if (respuesta == null) {
            	//System.out.println("retorna este");
                throw new ErrorInvocacionOperadorException(nombreServicio, parametros, 9999,
                        "Servicio no entrega respuesta.");
            }
            
            LicenciaType[] listaLicencias = respuesta.getListaLicencias();
            
            return listaLicencias;
        } catch (RemoteException e) {
        	if (e.getCause() instanceof java.net.SocketTimeoutException) {
        		throw new java.net.SocketTimeoutException();
	        }
        	throw new ErrorInvocacionOperadorException(e, nombreServicio, parametros);
        } catch (NumberFormatException e) {
			e.printStackTrace();
			throw new ErrorInvocacionOperadorException(e, nombreServicio, parametros);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ErrorInvocacionOperadorException(e, nombreServicio, parametros);
		}
    }
    
    /**
     * invoca el servicio consultaEventosLicencias realizando el manejo de timeout
     * @param year
     * @param month
     * @param dayOfMonth
     * @param hour
     * @param minute
     * @return
     * @throws ConfiguracionException
     * @throws ErrorInvocacionOperadorException
     */
    public SalidaLMEEvenLcc consultaEventosLicenciasFec(Calendar fecDesde, Calendar fecHasta)
            throws ConfiguracionException, ErrorInvocacionOperadorException {
    	SalidaLMEEvenLcc salida = new SalidaLMEEvenLcc();
    	
    	String urlServicio = getConfig().getString(URL_SERVICIO);
        String urlServicio2 = getConfig().getString(URL_SERVICIO2);
        long inicio = 0;
        long termino = 0;
        
        if(urlServicio != null && urlServicio.length()>0){
        	try {
        		inicio = System.currentTimeMillis();
        		LicenciaType[] licencias = consultaEventosLicenciasFec(fecDesde, fecHasta, urlServicio);
        		termino = System.currentTimeMillis();
        		salida.setTiempo1(termino - inicio);
        		salida.setListaLicencias(licencias);
        	} catch (java.net.SocketTimeoutException e) {
        		//System.out.println("timeout en la primera ===");
				salida.setError1(true);
				if(urlServicio2 != null && urlServicio2.length()>0){
	        		try {
	        			inicio = System.currentTimeMillis();
	            		LicenciaType[] licencias = consultaEventosLicenciasFec(fecDesde, fecHasta, urlServicio2);
	            		termino = System.currentTimeMillis();
	            		salida.setTiempo2(termino - inicio);
	            		salida.setListaLicencias(licencias);
	            	} catch (java.net.SocketTimeoutException ex) {
	            		//System.out.println("time out en la segunda === ");
	    				salida.setError2(true);
	    			}
	        	}else{
	        		salida.setError2(true);
	        	}
			}
        }else{
        	salida.setError1(true);
        	if(urlServicio2 != null && urlServicio2.length()>0){
        		try {
        			inicio = System.currentTimeMillis();
            		LicenciaType[] licencias = consultaEventosLicenciasFec(fecDesde, fecHasta, urlServicio2);
            		termino = System.currentTimeMillis();
            		salida.setTiempo2(termino - inicio);
            		salida.setListaLicencias(licencias);
            	} catch (java.net.SocketTimeoutException e) {
            		//System.out.println("tme out en la segunda ===");
    				salida.setError2(true);
    			}
        	}else{
        		salida.setError2(true);
        	}
        }
    	return salida;
    }
    
    
    /**
     * Invoca el servicio LMEEvenFec: consulta eventos asociados a LME.
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
     * @throws SocketTimeoutException 
     */
    private LicenciaType[] consultaEventosLicenciasFec(Calendar fecDesde, Calendar fecHasta, String url)
            throws ConfiguracionException, ErrorInvocacionOperadorException, SocketTimeoutException {
        String nombreServicio = "LMEEvenFec";
        //String nombre = "CONSULTA";
        
        String parametros = fecDesde.get(Calendar.YEAR) + "-" + (fecDesde.get(Calendar.MONTH) + 1) + "-" + fecDesde.get(Calendar.DAY_OF_MONTH) ;

        try {
        	String codigoOperador = getConfig().getString(CODIGO_OPERADOR);
            String tipoInstitucion = getConfig().getString(TIPO_INSTITUCION);
            String codUsuario = getConfig().getString(COD_USUARIO);
            String clave = getConfig().getString(CLAVE);
            
        	int timeOut = Integer.parseInt(ConstantesTimeOut.getInstancia().CONSULTA);
        	
        	WsLMEInet_PortType cliente = obtenerCliente(nombreServicio, parametros, timeOut, url);
            
            log("Servicio: LMEEvenFec,  URL: " +  url);
            log("HORA CALENDAR DESDE: " + fecDesde.get(Calendar.YEAR) + "/" + (fecDesde.get(Calendar.MONTH) + 1) + "/" + fecDesde.get(Calendar.DAY_OF_MONTH) + " - " + fecDesde.get(Calendar.HOUR_OF_DAY) + ":" + fecDesde.get(Calendar.MINUTE));
            log("HORA CALENDAR HASTA: " + fecHasta.get(Calendar.YEAR) + "/" + (fecHasta.get(Calendar.MONTH) + 1) + "/" + fecHasta.get(Calendar.DAY_OF_MONTH) + " - " + fecHasta.get(Calendar.HOUR_OF_DAY) + ":" + fecHasta.get(Calendar.MINUTE));
            
            LMEEvenFec LMEEvenFec = new LMEEvenFec(codigoOperador, tipoInstitucion, codUsuario, clave, fecDesde, fecHasta);
            
            LMEEvenFecResponse respuesta = cliente.LMEEvenFec(LMEEvenFec);

            if (respuesta == null) {
            	//System.out.println("retorna este");
                throw new ErrorInvocacionOperadorException(nombreServicio, parametros, 9999,
                        "Servicio no entrega respuesta.");
            }
            
            LicenciaType[] listaLicencias = respuesta.getListaLicencias();
            
            return listaLicencias;
        } catch (RemoteException e) {
        	if (e.getCause() instanceof java.net.SocketTimeoutException) {
        		throw new java.net.SocketTimeoutException();
	        }
        	throw new ErrorInvocacionOperadorException(e, nombreServicio, parametros);
        } catch (NumberFormatException e) {
			e.printStackTrace();
			throw new ErrorInvocacionOperadorException(e, nombreServicio, parametros);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ErrorInvocacionOperadorException(e, nombreServicio, parametros);
		}
    }
    
    
    /**
     * realiza la invocaci�n al metodo consultaDetalleLicencia manejando los timeout del servicio
     * @param numLicencia
     * @param digLicencia
     * @return
     * @throws ConfiguracionException
     * @throws ErrorInvocacionOperadorException
     * @throws ErrorRespuestaOperadorException
     */
    public SalidaLMEDetLcc consultaDetalleLicencia2(BigInteger numLicencia, String digLicencia)
            throws ConfiguracionException, ErrorInvocacionOperadorException, ErrorRespuestaOperadorException {
    	SalidaLMEDetLcc salida = new SalidaLMEDetLcc();
    	
    	String urlServicio = getConfig().getString(URL_SERVICIO);
        String urlServicio2 = getConfig().getString(URL_SERVICIO2);
        long inicio = 0;
        long termino = 0;
        
        if(urlServicio != null && urlServicio.length()>0){
        	try {
        		inicio = System.currentTimeMillis();
        		RespuestaDetalleLicencia respuesta = consultaDetalleLicencia(numLicencia, digLicencia, urlServicio);
        		termino = System.currentTimeMillis();
        		salida.setTiempo1(termino - inicio);
        		salida.setRespuesta(respuesta);
        	} catch (java.net.SocketTimeoutException e) {
        		//System.out.println("timeout en la primera ===");
				salida.setError1(true);
				if(urlServicio2 != null && urlServicio2.length()>0){
	        		try {
	        			inicio = System.currentTimeMillis();
	        			RespuestaDetalleLicencia respuesta = consultaDetalleLicencia(numLicencia, digLicencia, urlServicio2);
	        			termino = System.currentTimeMillis();
	            		salida.setTiempo2(termino - inicio);
	            		salida.setRespuesta(respuesta);
	            	} catch (java.net.SocketTimeoutException ex) {
	            		//System.out.println("time out en la segunda === ");
	    				salida.setError2(true);
	    			}
	        	}else{
	        		salida.setError2(true);
	        	}
			}
        }else{
        	salida.setError1(true);
        	if(urlServicio2 != null && urlServicio2.length()>0){
        		try {
        			inicio = System.currentTimeMillis();
        			RespuestaDetalleLicencia respuesta = consultaDetalleLicencia(numLicencia, digLicencia, urlServicio2);
        			termino = System.currentTimeMillis();
            		salida.setTiempo2(termino - inicio);
            		salida.setRespuesta(respuesta);
            	} catch (java.net.SocketTimeoutException e) {
            		//System.out.println("tme out en la segunda ===");
    				salida.setError2(true);
    			}
        	}else{
        		salida.setError2(true);
        	}
        }
        
    	return salida;
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
     * @throws ErrorRespuestaOperadorException 
     * @throws SocketTimeoutException 
     */
    private RespuestaDetalleLicencia consultaDetalleLicencia(BigInteger numLicencia, String digLicencia, String url)
            throws ConfiguracionException, ErrorInvocacionOperadorException, ErrorRespuestaOperadorException, SocketTimeoutException {
        String nombreServicio = "LMEDetLcc";
        String nombre = "DETALLE";
        String parametros = numLicencia + "-" + digLicencia;
        try {
        	
        	String codigoOperador = getConfig().getString(CODIGO_OPERADOR);
            String tipoInstitucion = getConfig().getString(TIPO_INSTITUCION);
            String codUsuario = getConfig().getString(COD_USUARIO);
            String clave = getConfig().getString(CLAVE);
        	
        	
        	int timeOut = Integer.parseInt(ConstantesTimeOut.getInstancia().DETALLE);
            WsLMEInet_PortType cliente = obtenerCliente(nombreServicio, parametros, timeOut, url);


            LMEDetLcc LMEDetLcc = new LMEDetLcc(codigoOperador, tipoInstitucion, codUsuario, clave,
                    //biginteger(numLicencia), digLicencia);
                    numLicencia, digLicencia);
            LMEDetLccResponse respuesta = cliente.LMEDetLcc(LMEDetLcc);

            if (respuesta == null || respuesta.getEstado() == null) {
                throw new ErrorInvocacionOperadorException(nombreServicio, parametros, 9999,
                        "Servicio no entrega respuesta.");
            }

            if (respuesta.getEstado().intValue() != 0) {
                throw new ErrorRespuestaOperadorException(nombreServicio, parametros,
                        respuesta.getEstado().intValue(), respuesta.getGloEstado());
            }

            
            
            XmlOptions options = new XmlOptions();
            Map substNamespaces = new HashMap();
            substNamespaces.put("http://www.lme.gov.cl/lme", "urn:www:lme:gov:cl:lme");
            substNamespaces.put("", "urn:www:lme:gov:cl:lme");
            options.setLoadSubstituteNamespaces(substNamespaces);
                      
            LMEDocument lmeDocument = null;
			String xmlAsString = null;

			try {
				xmlAsString = decodeXmlasString(respuesta.getDctoLme(), "UTF-8");
				lmeDocument = LMEDocument.Factory.parse(xmlAsString, options);
			} catch (Exception e) {
				log("Error al decodificar xml de licencia: " + numLicencia + " en UTF-8");
				try {
					log("Se intenta decodificar xml en ISO-8859-1");
					xmlAsString = decodeXmlasString(respuesta.getDctoLme(), "ISO-8859-1");
					lmeDocument = LMEDocument.Factory.parse(xmlAsString, options);
					log("Decodificacion exitosa de xml en ISO-8859-1");
				} catch (Exception e2) {
					log("Error al decodificar xml de licencia: " + numLicencia + " en ISO-8859-1");
					throw new XmlException("Error en parse Xml");
				}
			}

            CTZONA0 zona0 = lmeDocument.getLME().getZONA0();
            CTZONAA zonaA = lmeDocument.getLME().getZONAA();
            CTZONAB zonaB = lmeDocument.getLME().getZONAB();
            CTZONAC zonaC = lmeDocument.getLME().getZONAC();
            CTZONAD[] zonaD = lmeDocument.getLME().getZONADArray();

            RespuestaDetalleLicencia respuestaDetalleLicencia = new RespuestaDetalleLicencia(numLicencia, digLicencia,
                    zona0, zonaA, zonaB, zonaC, zonaD, xmlAsString);

            log("Licencia:" + numLicencia);
            validar("Zona A", respuestaDetalleLicencia.getZonaA());
            validar("Zona B", respuestaDetalleLicencia.getZonaB());
            validar("Zona C", respuestaDetalleLicencia.getZonaC());
            validar("Zona D", respuestaDetalleLicencia.getZonaD());            
            
            return respuestaDetalleLicencia;
        } catch (RemoteException e) {
        	if (e.getCause() instanceof java.net.SocketTimeoutException) {
        		throw new java.net.SocketTimeoutException();
	        }
            throw new ErrorInvocacionOperadorException(e, nombreServicio, parametros);
        } catch (XmlException e) {
            throw new ErrorInvocacionOperadorException(e, nombreServicio, parametros);
        } catch (UnsupportedEncodingException e) {
            throw new ErrorInvocacionOperadorException(e, nombreServicio, parametros);
        } catch (NumberFormatException e) {
        	throw new ErrorInvocacionOperadorException(e, nombreServicio, parametros);
		} catch (Exception e) {
			throw new ErrorInvocacionOperadorException(e, nombreServicio, parametros);
		}
    }

    private String decodeXmlasString(byte[] bs, String charset) throws UnsupportedEncodingException {
		String xmlAsString = new String(bs, charset);

		return xmlAsString;

	}
    
    /**
     * realiza la invocaci�n al metodo devolucionLicencia manejando los timeout del servicio
     * @param idLicencia
     * @param dvLicencia
     * @param motivoDevolucion
     * @param fechaDevolucion
     * @return
     * @throws ConfiguracionException
     * @throws ErrorInvocacionOperadorException
     */
    public SalidaLMEDevEmp devolucionLicencia2(int idLicencia, String dvLicencia, int motivoDevolucion,
            Date fechaDevolucion) throws ConfiguracionException, ErrorInvocacionOperadorException {
    	SalidaLMEDevEmp salida = new SalidaLMEDevEmp();
    	
    	
    	String urlServicio = getConfig().getString(URL_SERVICIO);
        String urlServicio2 = getConfig().getString(URL_SERVICIO2);
        long inicio = 0;
        long termino = 0;
        
        if(urlServicio != null && urlServicio.length()>0){
        	try {
        		inicio = System.currentTimeMillis();
        		RespuestaServicio respuesta = devolucionLicencia(idLicencia, dvLicencia, motivoDevolucion, fechaDevolucion, urlServicio);
        		termino = System.currentTimeMillis();
        		salida.setTiempo1(termino - inicio);
        		salida.setRespuesta(respuesta);
        	} catch (Exception e) {
        		//System.out.println("timeout en la primera ===");
				salida.setError1(true);
				if(urlServicio2 != null && urlServicio2.length()>0){
	        		try {
	        			inicio = System.currentTimeMillis();
	        			RespuestaServicio respuesta = devolucionLicencia(idLicencia, dvLicencia, motivoDevolucion, fechaDevolucion, urlServicio2);
	        			termino = System.currentTimeMillis();
	            		salida.setTiempo2(termino - inicio);
	            		salida.setRespuesta(respuesta);
	            	} catch (Exception ex) {
	            		//System.out.println("time out en la segunda === ");
	    				salida.setError2(true);
	    			}
	        	}else{
	        		salida.setError2(true);
	        	}
			}
        }else{
        	salida.setError1(true);
        	if(urlServicio2 != null && urlServicio2.length()>0){
        		try {
        			inicio = System.currentTimeMillis();
        			RespuestaServicio respuesta = devolucionLicencia(idLicencia, dvLicencia, motivoDevolucion, fechaDevolucion, urlServicio2);
        			termino = System.currentTimeMillis();
            		salida.setTiempo2(termino - inicio);
            		salida.setRespuesta(respuesta);
            	} catch (Exception e) {
            		//System.out.println("tme out en la segunda ===");
    				salida.setError2(true);
    			}
        	}else{
        		salida.setError2(true);
        	}
        }
    	
    	return salida;
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
     * @throws SocketTimeoutException 
     */
    private RespuestaServicio devolucionLicencia(int idLicencia, String dvLicencia, int motivoDevolucion,
            Date fechaDevolucion, String url) throws ConfiguracionException, ErrorInvocacionOperadorException, SocketTimeoutException {
        String nombreServicio = "LMEDevEmp";
        String nombre = "DEVOLUCION";
        String parametros = "idLicencia: " + idLicencia + " digLicencia: " + dvLicencia + " motivoDevolucion: "
                + motivoDevolucion + " fechaDevolucion: " + fechaDevolucion;
        try {
        	
        	String codigoOperador = getConfig().getString(CODIGO_OPERADOR);
            String tipoInstitucion = getConfig().getString(TIPO_INSTITUCION);
            String codUsuario = getConfig().getString(COD_USUARIO);
            String clave = getConfig().getString(CLAVE);
            
            
        	int timeOut = Integer.parseInt(ConstantesTimeOut.getInstancia().DEVOLUCION);
            WsLMEInet_PortType cliente = obtenerCliente(nombreServicio, parametros, timeOut, url);

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
        	if (e.getCause() instanceof java.net.SocketTimeoutException) {
        		throw new java.net.SocketTimeoutException();
	        }
            throw new ErrorInvocacionOperadorException(e, nombreServicio, parametros);
        } catch (NumberFormatException e) {
        	throw new ErrorInvocacionOperadorException(e, nombreServicio, parametros);
		} catch (Exception e) {
			throw new ErrorInvocacionOperadorException(e, nombreServicio, parametros);
		} 
    }
    
    
    
    
    /**
     * realiza la invocaci�n al metodo informaValidacionLicencias manejando los timeout del servicio
     * @param listaLicencias
     * @return
     * @throws ConfiguracionException
     * @throws ErrorInvocacionOperadorException
     */
    public SalidaLMEInfValCCAF informaValidacionLicencias2(LicenciaSimpleType[] listaLicencias)
            throws ConfiguracionException, ErrorInvocacionOperadorException {
    	SalidaLMEInfValCCAF salida = new SalidaLMEInfValCCAF();
    	
    	String urlServicio = getConfig().getString(URL_SERVICIO);
        String urlServicio2 = getConfig().getString(URL_SERVICIO2);
        long inicio = 0;
        long termino = 0;
        
        if(urlServicio != null && urlServicio.length()>0){
        	try {
        		inicio = System.currentTimeMillis();
        		LMEInfValCCAFResponse respuesta = informaValidacionLicencias(listaLicencias, urlServicio);
        		termino = System.currentTimeMillis();
        		salida.setTiempo1(termino - inicio);
        		salida.setRespuesta(respuesta);
        	} catch (java.net.SocketTimeoutException e) {
        		//System.out.println("timeout en la primera ===");
				salida.setError1(true);
				if(urlServicio2 != null && urlServicio2.length()>0){
	        		try {
	        			inicio = System.currentTimeMillis();
	        			LMEInfValCCAFResponse respuesta = informaValidacionLicencias(listaLicencias, urlServicio2);
	        			termino = System.currentTimeMillis();
	            		salida.setTiempo2(termino - inicio);
	            		salida.setRespuesta(respuesta);
	            	} catch (java.net.SocketTimeoutException ex) {
	            		//System.out.println("time out en la segunda === ");
	    				salida.setError2(true);
	    			}
	        	}else{
	        		salida.setError2(true);
	        	}
			}
        }else{
        	salida.setError1(true);
        	if(urlServicio2 != null && urlServicio2.length()>0){
        		try {
        			inicio = System.currentTimeMillis();
        			LMEInfValCCAFResponse respuesta = informaValidacionLicencias(listaLicencias, urlServicio2);
        			termino = System.currentTimeMillis();
            		salida.setTiempo2(termino - inicio);
            		salida.setRespuesta(respuesta);
            	} catch (java.net.SocketTimeoutException e) {
            		//System.out.println("tme out en la segunda ===");
    				salida.setError2(true);
    			}
        	}else{
        		salida.setError2(true);
        	}
        }
    	
    	return salida;
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
     * @throws SocketTimeoutException 
     */
    private LMEInfValCCAFResponse informaValidacionLicencias(LicenciaSimpleType[] listaLicencias, String url)
            throws ConfiguracionException, ErrorInvocacionOperadorException, SocketTimeoutException {
        String nombreServicio = "LMEInfValCCAF";
        String nombre = "VALIDACION";
        String parametros = listaLicencias.length + " licencias:";
        for (int i = 0; i < listaLicencias.length; i++) {
            LicenciaSimpleType licencia = listaLicencias[i];
            parametros += " [" + licencia.getIdLicencia() + "-" + licencia.getDvLicencia() + ": "
                    + licencia.getEstadoVal() + "]";
        }
        try {
        	
        	String codigoOperador = getConfig().getString(CODIGO_OPERADOR);
            String tipoInstitucion = getConfig().getString(TIPO_INSTITUCION);
            String codUsuario = getConfig().getString(COD_USUARIO);
            String clave = getConfig().getString(CLAVE);
            
            
        	int timeOut = Integer.parseInt(ConstantesTimeOut.getInstancia().VALIDACION);
            WsLMEInet_PortType cliente = obtenerCliente(nombreServicio, parametros, timeOut, url);

            LMEInfValCCAF LMEInfValCCAF = new LMEInfValCCAF(codigoOperador, tipoInstitucion, codUsuario, clave,
                    listaLicencias);
            LMEInfValCCAFResponse respuesta = cliente.LMEInfValCCAF(LMEInfValCCAF);
            if (respuesta == null || respuesta.getEstado() == null) {
                throw new ErrorInvocacionOperadorException(nombreServicio, parametros, 9999,
                        "Servicio no entrega respuesta.");
            }

            return respuesta;
        } catch (RemoteException e) {
        	if (e.getCause() instanceof java.net.SocketTimeoutException) {
        		throw new java.net.SocketTimeoutException();
	        }
            throw new ErrorInvocacionOperadorException(e, nombreServicio, parametros);
        } catch (NumberFormatException e) {
        	throw new ErrorInvocacionOperadorException(e, nombreServicio, parametros);
		} catch (Exception e) {
			throw new ErrorInvocacionOperadorException(e, nombreServicio, parametros);
		}
    }
    
    
    
    
    
    
    /**
     * realiza la invocaci�n al metodo liquidacionLicencia manejando los timeout del servicio
     * @param idLicencia
     * @param dvLicencia
     * @param fechaProceso
     * @param tipoLiquidacion
     * @param zonadArray
     * @return
     * @throws ConfiguracionException
     * @throws ErrorInvocacionOperadorException
     */
    public SalidaLMEInfLiquid liquidacionLicencia2(int idLicencia, String dvLicencia, Date fechaProceso, int tipoLiquidacion,
            CTZONAD[] zonadArray) throws ConfiguracionException, ErrorInvocacionOperadorException {
    	SalidaLMEInfLiquid salida = new SalidaLMEInfLiquid();
    	
    	
    	String urlServicio = getConfig().getString(URL_SERVICIO);
        String urlServicio2 = getConfig().getString(URL_SERVICIO2);
        long inicio = 0;
        long termino = 0;
        
        if(urlServicio != null && urlServicio.length()>0){
        	try {
        		inicio = System.currentTimeMillis();
        		RespuestaServicio respuesta = liquidacionLicencia(idLicencia, dvLicencia, fechaProceso, tipoLiquidacion, zonadArray, urlServicio);
        		termino = System.currentTimeMillis();
        		salida.setTiempo1(termino - inicio);
        		salida.setRespuesta(respuesta);
        	} catch (java.net.SocketTimeoutException e) {
        		//System.out.println("timeout en la primera ===");
				salida.setError1(true);
				if(urlServicio2 != null && urlServicio2.length()>0){
	        		try {
	        			inicio = System.currentTimeMillis();
	        			RespuestaServicio respuesta = liquidacionLicencia(idLicencia, dvLicencia, fechaProceso, tipoLiquidacion, zonadArray, urlServicio2);
	        			termino = System.currentTimeMillis();
	            		salida.setTiempo2(termino - inicio);
	        			salida.setRespuesta(respuesta);
	            	} catch (java.net.SocketTimeoutException ex) {
	            		//System.out.println("time out en la segunda === ");
	    				salida.setError2(true);
	    			}
	        	}else{
	        		salida.setError2(true);
	        	}
			}
        }else{
        	salida.setError1(true);
        	if(urlServicio2 != null && urlServicio2.length()>0){
        		try {
        			inicio = System.currentTimeMillis();
        			RespuestaServicio respuesta = liquidacionLicencia(idLicencia, dvLicencia, fechaProceso, tipoLiquidacion, zonadArray, urlServicio2);
        			termino = System.currentTimeMillis();
            		salida.setTiempo2(termino - inicio);
        			salida.setRespuesta(respuesta);
            	} catch (java.net.SocketTimeoutException e) {
            		//System.out.println("tme out en la segunda ===");
    				salida.setError2(true);
    			}
        	}else{
        		salida.setError2(true);
        	}
        }
    	
    	return salida;
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
     * @throws SocketTimeoutException 
     */

    private RespuestaServicio liquidacionLicencia(int idLicencia, String dvLicencia, Date fechaProceso, int tipoLiquidacion,
            CTZONAD[] zonadArray, String url) throws ConfiguracionException, ErrorInvocacionOperadorException, SocketTimeoutException {
        String nombreServicio = "LMEInfLiquid";
        String nombre = "LIQUIDACION";
        String parametros = "idLicencia: " + idLicencia + " dvLicencia: " + dvLicencia + " fechaProceso: "
                + fechaProceso + " zonaD: " + zonadArray;
        try {
        	
        	String codigoOperador = getConfig().getString(CODIGO_OPERADOR);
            String tipoInstitucion = getConfig().getString(TIPO_INSTITUCION);
            String codUsuario = getConfig().getString(COD_USUARIO);
            String clave = getConfig().getString(CLAVE);
            
            
        	int timeOut = Integer.parseInt(ConstantesTimeOut.getInstancia().LIQUIDACION);
            WsLMEInet_PortType cliente = obtenerCliente(nombreServicio, parametros, timeOut, url);

            
            //bug LMEInfLiquid
            //String tipoLiquidacion = getConfig().getString(TIPO_LIQUIDACION);

            Calendar fecProceso = Calendar.getInstance();
            fecProceso.setTime(fechaProceso);

            LMEDocument.LME lme = LMEDocument.LME.Factory.newInstance();
            lme.setZONADArray(zonadArray);
                        
            String xmlZonaD = lme.toString();
            validar("LMEInfLiquid ZonaD", zonadArray);
            log("LMEInfLiquid ZonaD XML :" + xmlZonaD);
            
            /*
            //Parche Zona Firma
            xmlZonaD = xmlZonaD.replace("<ZONA_F/>", "<ZONA_DF/>");
            */
            //Parche ruta            
            //String carOld = "xmlns=\"" + "urn:www:lme:gov:cl:lme\"";
            //xmlZonaD = xmlZonaD.replace(carOld, "");
            //xmlZonaD = xmlZonaD.replaceAll(carOld, "");
            xmlZonaD = xmlZonaD.replaceAll("urn:www:lme:gov:cl:lme","").replaceAll("xmlns=\"\"", "");
            //log("LMEInfLiquid ZonaD XML con parches :" + xmlZonaD);            
            xmlZonaD = xmlZonaD.replaceAll("Z</ano","</ano").replaceAll("Z</periodo", "</periodo").replaceAll("Z</fecha", "</fecha");            
            
            byte[] datosLiquidacion = xmlZonaD.getBytes("UTF-8");
            
            log("XML corregido:" + openFileToString(datosLiquidacion));
            
            LMEInfLiquid LMEInfLiquid = new LMEInfLiquid(codigoOperador, tipoInstitucion, codUsuario, clave,
                    biginteger(idLicencia), dvLicencia, fecProceso, biginteger(tipoLiquidacion), datosLiquidacion);
            LMEInfLiquidResponse respuesta = cliente.LMEInfLiquid(LMEInfLiquid);
            if (respuesta == null || respuesta.getEstado() == null) {
                throw new ErrorInvocacionOperadorException(nombreServicio, parametros, 9999,
                        "Servicio no entrega respuesta.");
            }

            return new RespuestaServicio(respuesta.getEstado().intValue(), respuesta.getGloEstado());
        } catch (RemoteException e) {
        	if (e.getCause() instanceof java.net.SocketTimeoutException) {
        		throw new java.net.SocketTimeoutException();
	        }
            throw new ErrorInvocacionOperadorException(e, nombreServicio, parametros);
        } catch (NumberFormatException e) {
        	throw new ErrorInvocacionOperadorException(e, nombreServicio, parametros);
		} catch (Exception e) {
			throw new ErrorInvocacionOperadorException(e, nombreServicio, parametros);
		}
    }
    
    public String openFileToString(byte[] _bytes)
    {
        String file_string = "";

        for(int i = 0; i < _bytes.length; i++)
        {
            file_string += (char)_bytes[i];
        }

        return file_string;    
    }
    
    /**
     * realiza la invocaci�n al metodo informaRentaLicencia manejando los timeout del servicio
     * @param idLicencia
     * @param dvLicencia
     * @param fechaProceso
     * @param zonaC
     * @return
     * @throws ConfiguracionException
     * @throws ErrorInvocacionOperadorException
     */
    public SalidaLMEInfSeccC informaRentaLicencia2(int idLicencia, String dvLicencia, Date fechaProceso,
            CTZONAC zonaC) throws ConfiguracionException, ErrorInvocacionOperadorException {
    	SalidaLMEInfSeccC salida = new SalidaLMEInfSeccC();
    	
    	
    	String urlServicio = getConfig().getString(URL_SERVICIO);
        String urlServicio2 = getConfig().getString(URL_SERVICIO2);
        long inicio = 0;
        long termino = 0;
        
        if(urlServicio != null && urlServicio.length()>0){
        	try {
        		inicio = System.currentTimeMillis();
        		RespuestaServicio respuesta = informaRentaLicencia(idLicencia, dvLicencia, fechaProceso, zonaC, urlServicio);
        		termino = System.currentTimeMillis();
        		salida.setTiempo1(termino - inicio);
        		salida.setRespuesta(respuesta);
        	} catch (java.net.SocketTimeoutException e) {
        		//System.out.println("timeout en la primera ===");
				salida.setError1(true);
				if(urlServicio2 != null && urlServicio2.length()>0){
	        		try {
	        			inicio = System.currentTimeMillis();
	        			RespuestaServicio respuesta = informaRentaLicencia(idLicencia, dvLicencia, fechaProceso, zonaC, urlServicio2);
	        			termino = System.currentTimeMillis();
	            		salida.setTiempo2(termino - inicio);
	            		salida.setRespuesta(respuesta);
	            	} catch (java.net.SocketTimeoutException ex) {
	            		//System.out.println("time out en la segunda === ");
	    				salida.setError2(true);
	    			}
	        	}else{
	        		salida.setError2(true);
	        	}
			}
        }else{
        	salida.setError1(true);
        	if(urlServicio2 != null && urlServicio2.length()>0){
        		try {
        			inicio = System.currentTimeMillis();
        			RespuestaServicio respuesta = informaRentaLicencia(idLicencia, dvLicencia, fechaProceso, zonaC, urlServicio2);
        			termino = System.currentTimeMillis();
            		salida.setTiempo2(termino - inicio);
            		salida.setRespuesta(respuesta);
            	} catch (java.net.SocketTimeoutException e) {
            		//System.out.println("tme out en la segunda ===");
    				salida.setError2(true);
    			}
        	}else{
        		salida.setError2(true);
        	}
        }
    	
    	return salida;
    	
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
     * @throws SocketTimeoutException 
     */

    private RespuestaServicio informaRentaLicencia(int idLicencia, String dvLicencia, Date fechaProceso,
            CTZONAC zonaC, String url) throws ConfiguracionException, ErrorInvocacionOperadorException, SocketTimeoutException {
        String nombreServicio = "LMEInfSeccC";
        String nombre = "ZONA_C";
        String parametros = "idLicencia: " + idLicencia + " dvLicencia: " + dvLicencia + " fechaProceso: "
                + fechaProceso + " zonaC: " + zonaC;
        try {
        	
        	String codigoOperador = getConfig().getString(CODIGO_OPERADOR);
            String tipoInstitucion = getConfig().getString(TIPO_INSTITUCION);
            String codUsuario = getConfig().getString(COD_USUARIO);
            String clave = getConfig().getString(CLAVE);
            
        	int timeOut = Integer.parseInt(ConstantesTimeOut.getInstancia().ZONA_C);
            WsLMEInet_PortType cliente = obtenerCliente(nombreServicio, parametros, timeOut, url);

            Calendar fecProceso = Calendar.getInstance();
            fecProceso.setTime(fechaProceso);

            LMEDocument.LME lme = LMEDocument.LME.Factory.newInstance();
            lme.setZONAC(zonaC);
            String xmlZonaC = lme.toString();
            
            validar("LMEInfSeccC ZonaC:", zonaC);

            //Parche MEDIPASS
//            String carOld = "xmlns=\"" + "urn:www:lme:gov:cl:lme\"";
//            xmlZonaC = xmlZonaC.replace(carOld, "");
//            xmlZonaC = xmlZonaC.replaceAll(carOld, "");
            xmlZonaC = xmlZonaC.replaceAll("urn:www:lme:gov:cl:lme","").replaceAll("xmlns=\"\"", "");
            
            //log(":::LMEInfLiquid ZonaC XML:" + xmlZonaC);
            
            //Parche i-med
            xmlZonaC = xmlZonaC.replaceAll("urn:", "lme:");
            
            byte[] datosZonaC = xmlZonaC.getBytes("UTF-8");
            
            LMEInfSeccC LMEInfSeccC = new LMEInfSeccC(codigoOperador, tipoInstitucion, codUsuario, clave,
                    biginteger(idLicencia), dvLicencia, fecProceso, datosZonaC);
            LMEInfSeccCResponse respuesta = cliente.LMEInfSeccC(LMEInfSeccC);

            if (respuesta == null || respuesta.getEstado() == null) {
                throw new ErrorInvocacionOperadorException(nombreServicio, parametros, 9999,
                        "Servicio no entrega respuesta.");
            }

            return new RespuestaServicio(respuesta.getEstado().intValue(), respuesta.getGloEstado());
        } catch (RemoteException e) {
        	if (e.getCause() instanceof java.net.SocketTimeoutException) {
        		throw new java.net.SocketTimeoutException();
	        }
            throw new ErrorInvocacionOperadorException(e, nombreServicio, parametros);
        } catch (NumberFormatException e) {
        	throw new ErrorInvocacionOperadorException(e, nombreServicio, parametros);
		} catch (Exception e) {
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

        log(prefijo + " Validacion: " + root.validate(m_validationOptions));

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
