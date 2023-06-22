/**
 * 
 */
package cl.laaraucana.ivr.rest;

import java.util.HashSet;
import java.util.Set;



	public class RestAppConfig extends javax.ws.rs.core.Application {
	    
		public Set<Class<?>> getClasses() {
			Set<Class<?>> classes = new HashSet<Class<?>>();
	        classes.add(cl.laaraucana.ivr.rest.TokenRest.class);
	        classes.add(cl.laaraucana.ivr.rest.LicenciaIVRRest.class);
	        return classes;
	    }
	}

