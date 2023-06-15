package cl.liv.comun.utiles;

import java.util.Enumeration;
import java.util.ResourceBundle;

public class PropertiesUtil {

	public static String VERSION = "20210628 Mejoras NRP ";
	
	
	public static ResourceBundle workflowProperties = null ;
	public static ResourceBundle propertiesArchivosAS400 = null ;
	public static ResourceBundle propertiesOutputNominas = null ;
	public static ResourceBundle propertiesVariables = null ;
	public static ResourceBundle propertiesNominas = null ;
	public static ResourceBundle propertiesGuardaArchivoJSON= null ;
	public static ResourceBundle propertiesPlanillas = null ;
	public static ResourceBundle propertiesCoreRequest = null ;
	public static ResourceBundle propertiesComun = null ;
	public static ResourceBundle propertiesTXT = null ;
	public static ResourceBundle propertiesXLS = null ;
	public static ResourceBundle configuracionesMail = null ;
	public static ResourceBundle propertiesIbatis = null ;
	public static ResourceBundle querysJDBC = null ;
	public static ResourceBundle connectorJDBC = null ;
	public static ResourceBundle propertiesMemoria = null;
	public static ResourceBundle propertiesAutenticacion = null;
	

	
	
	static{
		cargar();
	}
	
	public static ResourceBundle inicializarProperties( String url){
		try{
			ResourceBundle properties = ResourceBundle.getBundle(url);
			return properties ;
		}
		catch(Exception e){
		}
		return null;
	}
	
	public static void mostrarProperties(ResourceBundle props, String name){
		if(props == null) return;
		String espacio = "    ";
		Enumeration enumeration = props.getKeys();

		// print all the keys
		while (enumeration.hasMoreElements()) {
			String element = (String) enumeration.nextElement(); 
	  	}
	}
	
	public static String fill(String texto, int largo){
		if(texto != null && texto.length() < largo){
			for(int i=texto.length(); i<largo; i++){
				texto = texto + " ";
			}
			return texto;
		}
		
		return "   ";
	}
	
	public synchronized static void cargar(){
		
		System.out.println("################    INICIO CARGA DE PROPERTIES  ################");
		
		workflowProperties 			= null;
		propertiesArchivosAS400 	= null;
		propertiesOutputNominas 	= null;
		propertiesVariables 		= null;
		propertiesNominas 			= null;
		propertiesGuardaArchivoJSON = null;
		propertiesPlanillas 		= null;
		propertiesCoreRequest 		= null;
		propertiesComun 			= null;
		propertiesTXT 				= null;
		propertiesXLS 				= null;
		configuracionesMail 		= null;
		propertiesIbatis 			= null;
		querysJDBC 					= null;
		connectorJDBC 				= null;
		propertiesMemoria 			= null;
		propertiesAutenticacion		= null;
		
		
		workflowProperties 			= inicializarProperties( "etc/workflow_configuraciones");
		propertiesArchivosAS400 	= inicializarProperties( "etc/config_archivos_as400");
		propertiesOutputNominas 	= inicializarProperties( "etc/config_output_nominas");
		propertiesVariables 		= inicializarProperties( "etc/variables_sistemas");
		propertiesNominas 			= inicializarProperties( "etc/config_output_nominas");
		propertiesGuardaArchivoJSON = inicializarProperties( "cl/jfactory/planillas/etc/guardar_archivo_json");
		propertiesPlanillas 		= inicializarProperties( "etc/liv_carga_masiva_planillas");
		propertiesCoreRequest 		= inicializarProperties( "etc/request_configuraciones");
		propertiesComun 			= inicializarProperties( "etc/config_comun");
		propertiesTXT 				= inicializarProperties( "etc/config_txt");
		propertiesXLS 				= inicializarProperties( "etc/config_xls");
		configuracionesMail 		= inicializarProperties( "etc/mail");
		propertiesIbatis 			= inicializarProperties( "etc/ibatis");
		querysJDBC 					= inicializarProperties( "etc/querys_jdbc");
		connectorJDBC 				= inicializarProperties( "etc/connector_jdbc");
		propertiesMemoria 			= inicializarProperties( "etc/memoria");
		propertiesAutenticacion		= inicializarProperties( "etc/autenticacion");
		

		
		mostrarProperties( workflowProperties ,"wf");
		mostrarProperties( propertiesArchivosAS400 ,"as400");
		mostrarProperties( propertiesOutputNominas ,"output");
		mostrarProperties( propertiesVariables ,"variables");
		mostrarProperties( propertiesNominas ,"nominas");
		mostrarProperties( propertiesGuardaArchivoJSON ,"guardaJSON");
		mostrarProperties( propertiesPlanillas ,"planillas");
		mostrarProperties( propertiesCoreRequest ,"coreRequest");
		mostrarProperties( propertiesComun , "comun");
		mostrarProperties( propertiesTXT ,"exportTXT");
		mostrarProperties( propertiesXLS ,"exportXLS");
		mostrarProperties( configuracionesMail , "mail");
		mostrarProperties( propertiesIbatis ,"ibatis");
		mostrarProperties( querysJDBC ,"querysJDBC");
		mostrarProperties( connectorJDBC , "connectorJDBC");
		mostrarProperties( propertiesMemoria , "memoria");
		mostrarProperties( propertiesAutenticacion , "autenticacion");
		System.out.println("################    FIN CARGA DE PROPERTIES  ################");	
		
		
		System.out.println("**************** <VERSION NRP> *******************");
		System.out.println(VERSION);
		System.out.println("**************** </VERSION NRP> *******************");
		
	}
}
