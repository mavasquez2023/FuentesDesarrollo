/**
 * 
 */
package cl.laaraucana.ivr.rest;


@javax.ws.rs.Path("/licenciaIVR")
public class LicenciaIVRRest {
	
	@javax.ws.rs.GET
	public String getLicencia() {
        return "Ingrese nro licencia!";
    }
}
