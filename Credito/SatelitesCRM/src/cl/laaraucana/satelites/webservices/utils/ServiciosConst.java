package cl.laaraucana.satelites.webservices.utils;
import java.util.ResourceBundle;

import cl.laaraucana.satelites.Utils.Configuraciones;

public class ServiciosConst {
	
	public static final ResourceBundle RES_SERVICIOS = ResourceBundle.getBundle("cl.laaraucana.satelites.webservices.confServicios");
	
	public static final String SAP_USER = Configuraciones.getConfig("servicios.sap.user");
	public static final String SAP_HOST = Configuraciones.getConfig("servicios.sap.host");
	public static final String SAP_INTERNALORG = Configuraciones.getConfig("servicios.sap.internalOrg");
	public static final String SAP_USERNAME	= Configuraciones.getConfig("servicios.sap.username");
	public static final String SAP_PASSWORD = Configuraciones.getConfig("servicios.sap.pass");
	public static final String CREDITOS_CONDEUDA = Configuraciones.getConfig("sap.obtencioncredito.condeuda");
	public static final String CREDITOS_DISUELTOS = Configuraciones.getConfig("sap.obtencioncredito.disueltos");
	public static final String CREDITOS_TODOS = Configuraciones.getConfig("sap.obtencioncredito.todos");
	public static final String CERTIFICADO_PREPAGO = Configuraciones.getConfig("sap.tipocertificado.prepago");
	public static final String CERTIFICADO_FINIQUITO = Configuraciones.getConfig("sap.tipocertificado.finiquito");
	public static final String CERTIFICADO_DEUDA = Configuraciones.getConfig("sap.tipocertificado.deuda");
	public static final String URL_COMPTOTAL = Configuraciones.getConfig("url.compTotal");
	public static final String URL_ACHOMEPAGE = Configuraciones.getConfig("url.achomepage");
}
