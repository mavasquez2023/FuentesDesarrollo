package cl.laaraucana.satelites.webservices.utils;

import cl.laaraucana.satelites.Utils.Configuraciones;

public class ServiciosConst {
	
	public static final String SAP_USER = Configuraciones.getConfig("SAP.user");
	public static final String SAP_HOST = Configuraciones.getConfig("servicios.sap.host");
	public static final String SAP_INTERNALORG = Configuraciones.getConfig("servicios.sap.internalOrg");
	public static final String SAP_USERNAME	= Configuraciones.getConfig("servicios.sap.username");
	public static final String SAP_PASSWORD = Configuraciones.getConfig("SAP.password");
	public static final String CREDITOS_CONDEUDA = "1";

}
