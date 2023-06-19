/**
 * 
 */
package cl.laaraucana.muvu.services;

/**
 * @author IBM Software Factory
 *
 */
public interface ProcesosMuvu {
	public boolean procesarAltas(String periodo) throws Exception;

	public boolean procesarStock(String periodo) throws Exception;

	public void sincronizacionUsuarios(String periodo) throws Exception;

	public boolean procesarBajas(String periodo) throws Exception;
}
