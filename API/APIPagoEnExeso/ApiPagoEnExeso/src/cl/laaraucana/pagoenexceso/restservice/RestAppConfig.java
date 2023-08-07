package cl.laaraucana.pagoenexceso.restservice;

import java.util.HashSet;
import java.util.Set;



	public class RestAppConfig extends javax.ws.rs.core.Application {
	    
		public Set<Class<?>> getClasses() {			
			System.out.println("Llego a RestAppConfig consultarPagoEnExceso");			
			Set<Class<?>> classes = new HashSet<Class<?>>();
	        classes.add(cl.laaraucana.pagoenexceso.restservice.ConsultaPagoEnExesoREST.class);
	        return classes;
	    }
	}
	