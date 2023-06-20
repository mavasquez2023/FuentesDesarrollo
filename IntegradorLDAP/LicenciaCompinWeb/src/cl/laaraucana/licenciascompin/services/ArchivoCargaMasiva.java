/**
 * 
 */
package cl.laaraucana.licenciascompin.services;

import java.util.List;

import cl.laaraucana.licenciascompin.entities.RegistroDocPendientes;
import cl.laaraucana.licenciascompin.entities.RegistroLicencias;

/**
 * @author IBM Software Factory
 *
 */
public interface ArchivoCargaMasiva {
	public List<RegistroDocPendientes> leerRegistrosPendientes() throws Exception;
	
	public List<RegistroLicencias> leerRegistrosNuevos() throws Exception;
	
	public String generarArchivoPendientes(List<RegistroDocPendientes> registros);
	
	public String generarArchivoNuevos(List<RegistroLicencias> registros);

}
