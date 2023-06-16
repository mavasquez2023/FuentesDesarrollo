/**
 * 
 */
package cl.araucana.fonasa.business;

import cl.araucana.fonasa.business.to.ResponseFormFonasaTO;

/**
 * @author IBM Software Factory
 *
 */
	public interface WSConsultaFonasa {
		public abstract ResponseFormFonasaTO consultarEstadoFormulario(int color, int numeroFormulario, boolean reintento)  throws Exception;
	}
