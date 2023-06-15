package cl.jfactory.planillas.service.util;

import cl.liv.comun.utiles.PropertiesUtil;

public class EstadosWorkflow {
	private static String sufijo = PropertiesUtil.workflowProperties.getString("config.sufijo.estados.workflow");
	
	public static String NO_INICIADO 				= "NI";
	public static String ESTRUCTURA_CREADA 			= "E0";
	public static String RECURSOS_CERTIFICADOS 		= "E1";
	public static String DATA_CONSOLIDADA 			= "E2";
	public static String ANTES_GENERACION_NOMINA 	= "E_AG";
	public static String DESPUES_GENERACION_NOMINA 	= "E_GG";
	public static String ANTES_ENVIO_NOMINAS 		= "E_AE";
	public static String DESPUES_ENVIO_NOMINAS 		= "E_DE";
	public static String ANTES_ENVIO_SAP 			= "E_AES";
	public static String DESPUES_ENVIO_SAP 			= "E_DES";
	public static String PROCESO_TERMINADO 			= "E_PF";
	
	public static String ERROR_GENERICO 			= "ERROR_GENERICO";
	
	static{
		if(sufijo != null){
			NO_INICIADO 				= sufijo + NO_INICIADO;
			ESTRUCTURA_CREADA 			= sufijo + ESTRUCTURA_CREADA;
			RECURSOS_CERTIFICADOS 		= sufijo + RECURSOS_CERTIFICADOS;
			DATA_CONSOLIDADA 			= sufijo + DATA_CONSOLIDADA;
			ANTES_GENERACION_NOMINA 	= sufijo + ANTES_GENERACION_NOMINA;
			DESPUES_GENERACION_NOMINA 	= sufijo + DESPUES_GENERACION_NOMINA;
			ANTES_ENVIO_NOMINAS 		= sufijo + ANTES_ENVIO_NOMINAS;
			DESPUES_ENVIO_NOMINAS 		= sufijo + DESPUES_ENVIO_NOMINAS;
			ANTES_ENVIO_SAP 			= sufijo + ANTES_ENVIO_SAP;
			DESPUES_ENVIO_SAP 			= sufijo + DESPUES_ENVIO_SAP;
			PROCESO_TERMINADO 			= sufijo + PROCESO_TERMINADO;
			
			ERROR_GENERICO = sufijo + ERROR_GENERICO;
			
			
		}
	}
}
