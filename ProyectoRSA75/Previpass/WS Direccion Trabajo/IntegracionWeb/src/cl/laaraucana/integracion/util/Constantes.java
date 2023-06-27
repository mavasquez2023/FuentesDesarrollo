package cl.laaraucana.integracion.util;

import java.util.ResourceBundle;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class Constantes {

	public static Log log = LogFactory.getLog(Constantes.class);
	private static ResourceBundle resource = ResourceBundle.getBundle("cl.laaraucana.integracion.resources.integracionDT");
	private static Constantes instancia = null;
	
	
	public static String COD19000= "";
	public static String COD19010= "";
	public static String COD19020= "";
	public static String usuario= "";
	public static String password= "";
	public static String jndimail= "";
	public static String idparametrocp= "";
	public static String maildefault= "";
	
	public Constantes(){
		
		COD19000 = resource.getString("cod19000.codigo");
		COD19010 = resource.getString("cod19010.codigo");
		COD19020 = resource.getString("cod19020.codigo");
		usuario = resource.getString("usuario");
		password = resource.getString("password");
		jndimail = resource.getString("jndimail");
		idparametrocp= resource.getString("idparametrocp");
		maildefault= resource.getString("maildefault");
		log.info("Properties cargadas..");
		
		
	}
	public static Constantes getInstancia(){
		if(instancia == null)
			instancia = new Constantes();
		
		return instancia;
	}
}
