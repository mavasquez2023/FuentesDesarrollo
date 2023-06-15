package cl.araucana.autoconsulta.test;

import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.rmi.PortableRemoteObject;

import com.schema.util.FileSettings;
import com.schema.util.GeneralException;


import cl.araucana.autoconsulta.serv.ServicesAutoconsultaSLBean.ServicesAutoconsulta;
import cl.araucana.autoconsulta.serv.ServicesAutoconsultaSLBean.ServicesAutoconsultaHome;
import cl.araucana.common.BusinessException;
import cl.araucana.common.env.AppConfig;

public class TestEjbCredito {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		test0();
		
	}

	public static void test0() {
		// Preparación de propiedades para construir el contexto inicial de JNDI
		Properties prop = new Properties();
		prop.put(Context.INITIAL_CONTEXT_FACTORY, "com.ibm.websphere.naming.WsnInitialContextFactory");
		prop.put(Context.PROVIDER_URL, "iiop://localhost:2809"); 		

		try{
			System.out.println("Intentamos obtener el contexto inicial antes preparado");
			InitialContext jndiContext = new InitialContext(prop);
			System.out.println(" >> Obtenido un contexto JNDI");
			
			// Obtener una referencia al Bean
			//Object homeObject = jndiContext.lookup("ejb/Credito"); // Nombre del ejb en el ejb-jar.xml
			// System.out.println(" >> Obtenida una referencia al Bean \"ejb/Credito\"");

			// InitialContext initCtx = new InitialContext(prop);
	        Object homeObject = jndiContext.lookup("java:comp/env/ejb/Credito");
			
			// Obtener una referencia desde aquí a la interfaz Home del Bean
			//CreditoHome home = (CreditoHome) PortableRemoteObject.narrow(homeObject, CreditoHome.class);
			
			// Creación de la interfaz remota a partir de la interfaz Home:
			//CreditoRemote h = home.create();
			
			// Invocamos finalmente al Bean 
			//System.out.println("Invocando el EJB: [" + h.getCreditos(10286991).size() + "]");
		}catch(Exception ex){
			System.out.println(ex.toString());
		}
		}

}
